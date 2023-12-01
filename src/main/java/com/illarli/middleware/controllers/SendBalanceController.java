package com.illarli.middleware.controllers;

import com.illarli.middleware.infrastructure.balance.JSerialComm;
import com.illarli.middleware.models.Balance;
import com.illarli.middleware.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(@Payload String text) throws InterruptedException {
        JSerialComm.flag = true;
        JSerialComm jSerialComm = new JSerialComm();
        List<Balance> balances = this.balanceService.getAll();
        if (!balances.isEmpty()) {
            Balance balance = balances.get(0);
            jSerialComm.open(s -> {
                messagingTemplate.convertAndSend("/topic/greetings", s);
                return s;
            }, balance);
        }
        return "";
    }

    @MessageMapping("/stop")
    public void send() {
        JSerialComm.flag = false;
    }
}
