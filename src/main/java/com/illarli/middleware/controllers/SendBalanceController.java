package com.illarli.middleware.controllers;

import com.illarli.middleware.infrastructure.balance.JSerialComm;
import com.illarli.middleware.models.Balance;
import com.illarli.middleware.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/send-balance")
public class SendBalanceController {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/list")
    public List<String> getComList() {
        JSerialComm jSerialComm = new JSerialComm();
        return this.balanceService.getComList(jSerialComm);
    }

    @MessageMapping("/active")
    @SendTo("/balance/read")
    public void openBalanceWebSocket() {
        JSerialComm.flag = true;
        JSerialComm jSerialComm = new JSerialComm();
        List<Balance> balances = this.balanceService.getAll();
        if (!balances.isEmpty()) {
            Balance balance = balances.get(0);
            Map<String, Object> response = new HashMap<>() {{
                put("balance", balance);
            }};
            if (balance.getGetWeightTimer() > 0) {
                response.put("disconnectTime", balance.getGetWeightTimer());
                CompletableFuture.delayedExecutor(balance.getGetWeightTimer(), TimeUnit.SECONDS).execute(() -> {
                    JSerialComm.flag = false;
                });
            }
            jSerialComm.open(s -> {
                response.put("message", s);
                messagingTemplate.convertAndSend("/balance/read", response);
                return s;
            }, balance);
        }
//        if (!balances.isEmpty()) {
//            Balance balance = balances.get(0);
//            while (JSerialComm.flag) {
//                try {
//                    Map<String, Object> response = new HashMap<>() {{
//                        put("balance", balance);
//                        put("message", "s");
//                    }};
//                    if (balance.getGetWeightTimer() > 0) {
//                        response.put("disconnectTime", balance.getGetWeightTimer());
//                        CompletableFuture.delayedExecutor(balance.getGetWeightTimer(), TimeUnit.SECONDS).execute(() -> {
//                            JSerialComm.flag = false;
//                        });
//                    }
//                    messagingTemplate.convertAndSend("/balance/read", response);
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
    }

    @MessageMapping("/stop")
    public void closeBalanceWebSocket() {
        JSerialComm.flag = false;
    }
}
