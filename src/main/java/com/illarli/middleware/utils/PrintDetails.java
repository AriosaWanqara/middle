package com.illarli.middleware.utils;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.illarli.middleware.models.Printer;
import com.illarli.middleware.models.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintDetails {

    private Printer printer;
    private final DecimalFormat df = new DecimalFormat("####0.00");
    Style bodyStyle = new Style();

    private PrintDetails() {
    }

    public PrintDetails(Printer printer) {
        this.printer = printer;
        bodyStyle.setFontName(setFontName()).setFontSize(setWidth(), setHeight());
    }

    public void printDetail(EscPos escPos, List<Product> products) {
        switch (this.printer.getDetailType()) {
            case 2 -> {
                detailTwo(escPos, products);
            }
            case 3 -> {
                detailThree(escPos, products);
            }
            default -> {
                defaultDetail(escPos, products);
            }
        }
    }

    private void defaultDetail(EscPos escPos, List<Product> products) {
        try {
            String HeaderLeft = "Cant Descripcion";
            String HeaderRight = "P.U.  Total";
            int witheSpaceNumber = this.printer.getCharacterNumber() - HeaderLeft.length() - HeaderRight.length();
            if (witheSpaceNumber < 0) witheSpaceNumber = 0;
            String[] HeaderWitheSpace = new String[witheSpaceNumber];

            Arrays.fill(HeaderWitheSpace, " ");


            String Head = HeaderLeft + String.join("", HeaderWitheSpace) + HeaderRight;

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), Head);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), makeEqualsDivider());

            List<String> productDetails = new ArrayList<>(List.of());
            for (Product product : products) {
                String[] bodyWitheSpace = new String[6];
                String BodyLeft = product.getQuantity() + " " + product.getName();
                String BodyRight = "";
                String priceToString = new StringBuilder(String.valueOf(
                        this.df.format(product.getPrice() * product.getQuantity()))).reverse().toString();
                for (int j = 0; j < 6; j++) {
                    if (priceToString.length() > j) {
                        bodyWitheSpace[5 - j] = String.valueOf(priceToString.charAt(j));
                    } else {
                        bodyWitheSpace[5 - j] = " ";
                    }
                }
                BodyRight = this.df.format(product.getPrice()) + " " + String.join("", bodyWitheSpace);
                String[] middleWitheSpace = new String[this.printer.getCharacterNumber() - BodyLeft.length() - BodyRight.length()];
                Arrays.fill(middleWitheSpace, " ");
                productDetails.add(BodyLeft + String.join("", middleWitheSpace) + BodyRight);
            }

            for (String productDetail : productDetails) {
                escPos.writeLF(this.bodyStyle.setJustification(EscPosConst.Justification.Left_Default), productDetail);
            }

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), makeEqualsDivider());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void detailTwo(EscPos escPos, List<Product> products) {
        try {

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), makeEqualsDivider());

            String[] underLine = new String[this.printer.getCharacterNumber()];
            Arrays.fill(underLine, "_");
            int totalCharacterToWrite = (this.printer.getCharacterNumber() - 26) / 3;
            String head = "Cant P.U" + makeWitheSpace(totalCharacterToWrite) + "Desc" + makeWitheSpace(totalCharacterToWrite) + "Total";

            for (int i = 0; i < products.size(); i++) {
                Double total = products.get(i).getPrice() * products.get(i).getQuantity();
                String body = writeInWitheSpace(4, String.valueOf(products.get(i).getQuantity())) + " " +
                        writeInWitheSpace(3 + totalCharacterToWrite, this.df.format(products.get(i).getPrice())) +
                        writeInWitheSpace(4 + totalCharacterToWrite, this.df.format(products.get(i).getPrice())) +
                        writeInWitheSpace(5 + totalCharacterToWrite, this.df.format(total));
                escPos.writeLF(bodyStyle, products.get(i).getName());
                escPos.writeLF(bodyStyle, head);
                escPos.writeLF(bodyStyle, body);
                if (i < products.size() - 1) {
                    escPos.writeLF(bodyStyle, String.join("", underLine));
                }
            }

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), makeEqualsDivider());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void detailThree(EscPos escPos, List<Product> products) {

    }

    private Style.FontName setFontName() {
        if (this.printer.getFontSize().equals("B")) {
            return Style.FontName.Font_B;
        }
        return Style.FontName.Font_A_Default;
    }

    private Style.FontSize setWidth() {
        if (this.printer.getFontSize().equals("W")) {
            return Style.FontSize._2;
        }
        return Style.FontSize._1;
    }

    private Style.FontSize setHeight() {
        if (this.printer.getFontSize().equals("H")) {
            return Style.FontSize._2;
        }
        return Style.FontSize._1;
    }

    private String makeWitheSpace(int size) {
        String[] witheSpacesArray = new String[size];
        Arrays.fill(witheSpacesArray, " ");
        return String.join("", witheSpacesArray);
    }

    private String writeInWitheSpace(int size, String data) {
        String[] witheSpacesArray = new String[size];
        for (int i = 0; i < witheSpacesArray.length; i++) {
            if (data.length() > i) {
                witheSpacesArray[i] = String.valueOf(data.charAt(i));
            } else {
                witheSpacesArray[i] = " ";
            }
        }
        return String.join("", witheSpacesArray);
    }

    private String makeEqualsDivider() {
        String[] equalDividerArr = new String[this.printer.getCharacterNumber()];
        Arrays.fill(equalDividerArr, "=");
        return String.join("", equalDividerArr);
    }
}
