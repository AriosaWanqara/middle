package com.illarli.middleware.infrastructure.balance;

import com.fazecast.jSerialComm.SerialPort;
import com.illarli.middleware.models.Balance;
import com.illarli.middleware.models.repositories.BalanceLibraryRepository;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;


public class JSerialComm implements BalanceLibraryRepository {
    public static boolean flag = false;

    @Override
    public List<String> getComList() {
        SerialPort[] AvailablePorts = SerialPort.getCommPorts();
        List<String> serialPorts = new ArrayList<>(Collections.emptyList());
        for (SerialPort S : AvailablePorts) {
            serialPorts.add(S.getSystemPortName());
        }
        return serialPorts;
    }

    @Override
    public void open(Function<String, String> send, Balance balance) {
        SerialPort[] AvailablePorts = SerialPort.getCommPorts();
        SerialPort MySerialPort = null;
        for (SerialPort S : AvailablePorts) {
            if (S.getSystemPortName().equals(balance.getPort())) {
                MySerialPort = S;
            }
        }
        System.out.println(MySerialPort);
        if (MySerialPort != null) {
            MySerialPort.setComPortParameters(balance.getBalanceType().getBaudRate(),
                    balance.getBalanceType().getDataBits(),
                    balance.getBalanceType().getStopBits(),
                    balance.getBalanceType().getParity());
            MySerialPort.openPort();

            if (MySerialPort.isOpen()) {
                try {
                    while (flag) {
                        MySerialPort.writeBytes(balance.getBalanceType().getCommand(), balance.getBalanceType().getCommand().length);
                        byte[] readBuffer = new byte[MySerialPort.getDeviceWriteBufferSize()];
                        int numRead = MySerialPort.readBytes(readBuffer,
                                readBuffer.length);
                        String S = new String(readBuffer, StandardCharsets.UTF_8);
                        String clear = S.replaceAll("[^A-Za-z0-9 . : , ; ]", "");
                        System.out.println(S);
                        System.out.println(clear);
                        send.apply(clear);
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    try {
                        while (flag) {
                            send.apply("Error lectura");
                            Thread.sleep(500);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                MySerialPort.closePort();
            }
        } else {
            try {
                while (flag) {
                    send.apply("Balanza desconnectada");
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
