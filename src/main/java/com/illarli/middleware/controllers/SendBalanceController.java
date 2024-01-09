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

import java.util.List;
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
    @SendTo("/printer-ws/balance-reader")
    public void openBalanceWebSocket() {
        JSerialComm.flag = true;
        JSerialComm jSerialComm = new JSerialComm();
        List<Balance> balances = this.balanceService.getAll();
        if (!balances.isEmpty()) {
            Balance balance = balances.get(0);
            if (balance.getGetWeightTimer() > 0) {
                CompletableFuture.delayedExecutor(balance.getGetWeightTimer(), TimeUnit.SECONDS).execute(() -> {
                    JSerialComm.flag = false;
                });
            }
            jSerialComm.open(s -> {
                messagingTemplate.convertAndSend("/printer-ws/balance-reader", s);
                return s;
            }, balance);
        }
//        JSerialComm.flag = true;
//        while (JSerialComm.flag) {
//            try {
//                messagingTemplate.convertAndSend("/printer-ws/balance-reader", "s");
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    @MessageMapping("/stop")
    public void closeBalanceWebSocket() {
        JSerialComm.flag = false;
    }
}
