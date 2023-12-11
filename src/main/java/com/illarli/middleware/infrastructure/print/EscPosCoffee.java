package com.illarli.middleware.infrastructure.print;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.escpos.barcode.BarCode;
import com.github.anastaciocintra.escpos.barcode.QRCode;
import com.github.anastaciocintra.escpos.image.*;
import com.github.anastaciocintra.output.PrinterOutputStream;
import com.github.anastaciocintra.output.TcpIpOutputStream;
import com.illarli.middleware.mock.Product;
import com.illarli.middleware.models.Printer;
import com.illarli.middleware.models.repositories.PrinterLibraryRepository;
import com.illarli.middleware.resolver.*;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class EscPosCoffee implements PrinterLibraryRepository {

    private Printer printer;
    private OutputStream outputStream;
    private DecimalFormat df = new DecimalFormat("####0.00");

    public EscPosCoffee(Printer printer) {
        this.printer = printer;
        switch (printer.getType()) {
            case USB -> {
                try {
                    PrintService printService = PrinterOutputStream.getPrintServiceByName(printer.getName());
                    outputStream = new PrinterOutputStream(printService);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            case WIFI, BLUETOOTH -> {
                try {
                    outputStream = new TcpIpOutputStream(printer.getAddress(), printer.getPort());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

    }

    public EscPosCoffee() {
    }

    @Override
    public String[] getPrinterList() {
        return PrinterOutputStream.getListPrintServicesNames();
    }

    @Override
    public boolean OpenCashDrawerFromPrinter() {
        try {
            EscPos escPos = new EscPos(outputStream);
            escPos.write(27).write(112).write(0).write(25).write(250);
            escPos.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean printTest() {
        try {
            EscPos escPos = new EscPos(this.outputStream);

            Style titleStyle = new Style()
                    .setFontSize(Style.FontSize._2, Style.FontSize._2)
                    .setJustification(EscPosConst.Justification.Center);

            Style bodyStyle = new Style()
                    .setFontName(setFontName());

            String[] characters = new String[11];
            Arrays.fill(characters, "000000000-");
            String zeros = String.join("", characters);
            escPos.writeLF(titleStyle, "Test");
            escPos.feed(1);
            escPos.writeLF(bodyStyle, zeros);
            escPos.feed(1);
            escPos.writeLF(bodyStyle, "Cuente los caracteres que entran en la primera linea de texto y coloque el valor en Cantidad de caracteres");
            escPos.feed(1);
            escPos.writeLF(new Style().setJustification(EscPosConst.Justification.Center), "Configuracion Actual");
            escPos.writeLF("Nombre de impresora: " + this.printer.getName());
            escPos.writeLF("Tipo de fuente: " + this.printer.getFontSize());
            escPos.feed(7);
            escPos.cut(EscPos.CutMode.FULL);
            escPos.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean cut() {
        try {
            EscPos escPos = new EscPos(this.outputStream);
            escPos.feed(2);
            escPos.cut(EscPos.CutMode.FULL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean printText(PrintTextDTO text) {
        try {
            EscPos escPos = new EscPos(this.outputStream);
            Style bodyStyle = new Style()
                    .setFontName(setFontName());
            try {
                for (String message : text.getMessages()) {
                    escPos.writeLF(bodyStyle, message);
                }
                escPos.close();
            } catch (IOException exception) {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean printTitle(PrintTitleDTO title) {
        try {
            EscPos escPos = new EscPos(this.outputStream);
            Style titleStyle = new Style()
                    .setJustification(setJustification(title.getJustification()))
                    .setFontSize(setFontSize(title.getSize()), setFontSize(title.getSize()));
            for (String message : title.getTitle()) {
                escPos.writeLF(titleStyle, message);
            }
            escPos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean printMedia(PrintMediaDTO media) {
        try {
            EscPos escPos = new EscPos(this.outputStream);
            media.getMedia().forEach((type, files) -> {
                if (type.equals("imgU")) {
                    files.forEach(s -> {
                        printImageFromUrl(escPos, s, media.getJustification());
                    });
                }
                if (type.equals("imgF")) {
                    files.forEach(s -> {
                        printImageFromFile(escPos, s, media.getJustification());
                    });
                }
                if (type.equals("BC")) {
                    files.forEach(s -> {
                        printBarCode(escPos, s, media.getJustification());
                    });
                }
                if (type.equals("QR")) {
                    files.forEach(s -> {
                        printQRCode(escPos, s, media.getJustification());
                    });
                }
            });
            escPos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean printQuotation(PrintQuotationDTO quotation) {
        try {
            String dateString = getDateString();
            EscPos escPos = new EscPos(this.outputStream);
            Style titleStile = new Style()
                    .setJustification(EscPosConst.Justification.Center)
                    .setFontSize(Style.FontSize._1, Style.FontSize._1);
            Style bodyStyle = new Style()
                    .setFontName(setFontName());

            escPos.writeLF(titleStile, "Cotización");

            escPos.feed(3);

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), quotation.getBusinessName());
            if (quotation.getCommercialName() != null)
                escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), quotation.getCommercialName());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "RUC: " + quotation.getCompanyRUC());
            if (quotation.isForceAccounting()) {
                escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Obligadi a llevar Contabilidad");
            }
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "correo: " + quotation.getCompanyEmail());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Direccion: " + quotation.getCompanyAddress());

            escPos.feed(2);

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Trans No: " + quotation.getTransactionNumber());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Fecha: " + dateString);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Cliente: " + quotation.getClientName());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "RUC: " + quotation.getClientRUC());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "telefono: " + quotation.getClientPhone());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Direccion: " + quotation.getClientAddress());

            escPos.feed(1);

            String HeaderLeft = "Cant Descripcion";
            String HeaderRight = "P.U.  Total";

            String[] HeaderWitheSpace = new String[this.printer.getCharacterNumber() - HeaderLeft.length() - HeaderRight.length()];
            String[] equalDividerArr = new String[this.printer.getCharacterNumber()];

            Arrays.fill(HeaderWitheSpace, " ");
            Arrays.fill(equalDividerArr, "=");

            String equalDivider = String.join("", equalDividerArr);

            String Head = HeaderLeft + String.join("", HeaderWitheSpace) + HeaderRight;

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), Head);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalDivider);

            makeProductDetails(escPos, bodyStyle, quotation.getProducts());

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalDivider);

            this.printDetails(escPos, bodyStyle, quotation.getDetails());

            escPos.feed(5);
            escPos.cut(EscPos.CutMode.FULL);
            escPos.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    @Override
    public boolean printPreTicket(PrintPreTicketDTO preTicket) {
        try {
            EscPos escPos = new EscPos(this.outputStream);

            Style titleStyle = new Style()
                    .setFontSize(Style.FontSize._1, Style.FontSize._2)
                    .setJustification(EscPosConst.Justification.Center);
            Style bodyStyle = new Style()
                    .setFontName(setFontName());

            escPos.writeLF(titleStyle, "Preticket");
            escPos.feed(2);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), preTicket.getBusinessName());
            if (preTicket.getCommercialName() != null)
                escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), preTicket.getCommercialName());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "RUC: " + preTicket.getCompanyRUC());
            if (preTicket.isForceAccounting())
                escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Obligado a llevar contabilidad");
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Direccion: " + preTicket.getCompanyAddress());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Sucursal: " + preTicket.getSubsidiaryAddress());
            escPos.feed(1);


            int totalHead = this.printer.getCharacterNumber() - preTicket.getSellType().length();
            int leftHeadLength = 0;
            int rightHeadLength = 0;
            if (totalHead % 2 == 0) {
                leftHeadLength = totalHead / 2;
            } else {
                leftHeadLength = (totalHead / 2) + 1;
            }
            rightHeadLength = totalHead / 2;

            String[] leftHeadEquals = new String[leftHeadLength];
            String[] rightHeadEquals = new String[rightHeadLength];

            Arrays.fill(leftHeadEquals, "=");
            Arrays.fill(rightHeadEquals, "=");

            String head = String.join("", leftHeadEquals) + preTicket.getSellType() + String.join("", rightHeadEquals);
            escPos.writeLF(bodyStyle, head);
            escPos.feed(1);

            String dateString = getDateString();

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Pedido No: " + preTicket.getDeliveryNumber());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Fecha: " + dateString);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Atendido por: " + preTicket.getEmployee());
            escPos.feed(1);
            String ClientTag = "Datos del Cliente";
            int totalClient = this.printer.getCharacterNumber() - ClientTag.length();
            int leftClientLength = 0;
            int rightClientLength = 0;
            if (totalClient % 2 == 0) {
                leftClientLength = totalClient / 2;
            } else {
                leftClientLength = (totalClient / 2) + 1;
            }
            rightClientLength = totalClient / 2;

            String[] leftClientEquals = new String[leftClientLength];
            String[] rightClientEquals = new String[rightClientLength];

            Arrays.fill(leftClientEquals, "=");
            Arrays.fill(rightClientEquals, "=");

            String client = String.join("", leftClientEquals) + ClientTag + String.join("", rightClientEquals);
            escPos.writeLF(bodyStyle, client);
            escPos.feed(1);
            List<String> clientInfo = new ArrayList<>(List.of(
                    "Nombre",
                    "Identificacion",
                    "Correo",
                    "Telefono",
                    "Direccion"
            ));

            for (String s : clientInfo) {
                String[] underline = new String[this.printer.getCharacterNumber() - s.length() - 2];
                Arrays.fill(underline, "_");
                escPos.writeLF(
                        bodyStyle.setLineSpacing(120),
                        s + ": " + String.join("", underline));
            }

            bodyStyle.resetLineSpacing();

            String HeaderLeft = "Cant Descripcion";
            String HeaderRight = "P.U.  Total";

            String[] HeaderWitheSpace = new String[this.printer.getCharacterNumber() - HeaderLeft.length() - HeaderRight.length()];
            String[] equalDividerArr = new String[this.printer.getCharacterNumber()];

            Arrays.fill(HeaderWitheSpace, " ");
            Arrays.fill(equalDividerArr, "=");

            String equalDivider = String.join("", equalDividerArr);

            String Head = HeaderLeft + String.join("", HeaderWitheSpace) + HeaderRight;
            escPos.feed(1);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), Head);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalDivider);

            makeProductDetails(escPos, bodyStyle, preTicket.getProducts());

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalDivider);

            this.printDetails(escPos, bodyStyle, preTicket.getDetails());

            escPos.feed(5);
            escPos.cut(EscPos.CutMode.FULL);
            escPos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean printCommand(PrintCommandDTO command) {
        try {
            EscPos escPos = new EscPos(this.outputStream);

            Style titleStyle = new Style()
                    .setJustification(EscPosConst.Justification.Center)
                    .setFontSize(Style.FontSize._1, Style.FontSize._1);
            Style bodyStyle = new Style()
                    .setFontName(setFontName());
            escPos.writeLF(titleStyle, "PEDIDO " + command.getDeliveryNumber());
            escPos.writeLF(titleStyle, command.getZoneName());

            escPos.feed(2);

            String dateString = getDateString();
            escPos.writeLF(bodyStyle, "Atendido por: " + command.getEmployee());
            escPos.writeLF(bodyStyle, "Fecha: " + dateString);
            escPos.feed(1);

            String head = "Cant  Descripcion";

            escPos.writeLF(bodyStyle, head);

            String[] equalDividerArr = new String[this.printer.getCharacterNumber()];
            Arrays.fill(equalDividerArr, "=");
            String equalDivider = String.join("", equalDividerArr);

            escPos.writeLF(bodyStyle, equalDivider);
            escPos.feed(1);
            List<String> productToPrint = new LinkedList<>(List.of());
            command.getProducts().forEach((quantity, name) -> {
                String[] leftSideWitheSpace = new String[3];
                Arrays.fill(leftSideWitheSpace, " ");
                for (int i = 0; i < leftSideWitheSpace.length; i++) {
                    if (String.valueOf(quantity).length() > i) {
                        leftSideWitheSpace[i] = String.valueOf(String.valueOf(quantity).charAt(i));
                    } else {
                        leftSideWitheSpace[i] = " ";
                    }
                }
                productToPrint.add(String.join("", leftSideWitheSpace) + " " + name);
            });
            productToPrint.forEach(System.out::println);
            for (String message : productToPrint) {
                escPos.writeLF(bodyStyle, message);
            }

            escPos.feed(1);
            escPos.writeLF(bodyStyle, equalDivider);

            escPos.writeLF(bodyStyle, "OBSERVACIONES: " + command.getObservation());
            escPos.feed(1);
            escPos.writeLF(bodyStyle, "Atendido por: " + command.getSellType());
            escPos.feed(5);
            escPos.cut(EscPos.CutMode.FULL);
            escPos.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    @Override
    public boolean printElectronicInvoice(PrintElectronicInvoiceDTO electronicInvoice) {
        try {
            EscPos escPos = new EscPos(outputStream);

            Style titleStyle = new Style()
                    .setJustification(EscPosConst.Justification.Center)
                    .setFontSize(Style.FontSize._1, Style.FontSize._2);
            Style bodyStyle = new Style()
                    .setFontName(setFontName());

            escPos.writeLF(titleStyle, "FACTURA ELECTRONICA NO:");
            escPos.writeLF(titleStyle, electronicInvoice.getTransactionNumber());

//            escPos.feed(1);
//            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Ambiente: PRUEBA");
            escPos.feed(1);

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), electronicInvoice.getBusinessName());
            if (electronicInvoice.getCommercialName() != null)
                escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), electronicInvoice.getCommercialName());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "RUC: " + electronicInvoice.getCompanyRUC());
            if (electronicInvoice.isForceAccounting())
                escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Obligado a llevar contabilidad");
            if (electronicInvoice.isRimpe())
                escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Contribuyente Regimen RIMPE");
            if (electronicInvoice.isWithholdingAgent()) {
                escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Agente de Retencion");
                escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "No de Resolucion: " + electronicInvoice.getWithholdingAgentResolution());
            }
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Direccion: " + electronicInvoice.getCompanyAddress());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Sucursal: " + electronicInvoice.getSubsidiaryAddress());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "telefono: " + electronicInvoice.getCompanyPhone());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Correo: " + electronicInvoice.getCompanyEmail());

            escPos.feed(1);

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Fecha: " + getDateString());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Cliente: " + electronicInvoice.getClientName());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "RUC: " + electronicInvoice.getClientRUC());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Telefono: " + electronicInvoice.getClientPhone());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Direccion: " + electronicInvoice.getClientAddress());

            escPos.feed(1);

            String leftHeadSide = "Cant Descripcion";
            String rightHeadSide = "P.U. Total";
            String[] headWitheSpace = new String[this.printer.getCharacterNumber() - leftHeadSide.length() - rightHeadSide.length()];
            String[] equalsArr = new String[this.printer.getCharacterNumber()];
            Arrays.fill(headWitheSpace, " ");
            Arrays.fill(equalsArr, "=");

            String equalsDivider = String.join("", equalsArr);
            String head = leftHeadSide + String.join("", headWitheSpace) + rightHeadSide;

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), head);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalsDivider);

            makeProductDetails(escPos, bodyStyle, electronicInvoice.getProducts());

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalsDivider);

            this.printDetails(escPos, bodyStyle, electronicInvoice.getDetails());

            escPos.feed(1);
            electronicInvoice.getPaymentMethod().forEach((paymentMethod, value) -> {
                try {
                    escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Pago en " + paymentMethod + "=" + this.df.format(value));
                } catch (IOException e) {
                    System.out.println(e);
                }
            });
            escPos.feed(1);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Atendido por: " + electronicInvoice.getEmployee());

            escPos.feed(1);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Su comprobante electronico ha sido generado correctamente. Recuerde que tambien puede consultar su comprobante en el portal del SRI https://srienlinea.sri.gob.ec/ dentro de las proximas 24 horas con la siguiente clave de acceso :");

            escPos.feed(1);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), electronicInvoice.getAccessPassword());


            escPos.feed(5);
            escPos.cut(EscPos.CutMode.FULL);
            escPos.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean printVoucher(PrintVoucherDTO voucher) {

        try {
            EscPos escPos = new EscPos(outputStream);

            Style bodyStyle = new Style()
                    .setFontName(setFontName());

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), voucher.getBusinessName());
            if (voucher.getCommercialName() != null)
                escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), voucher.getCommercialName());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "RUC: " + voucher.getCompanyRUC());
            if (voucher.isForceAccounting())
                escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Obligado a llevar contabilidad");
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Direccion: " + voucher.getCompanyAddress());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Sucursal: " + voucher.getSubsidiaryAddress());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Telefono: " + voucher.getCompanyPhone());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Correo: " + voucher.getCompanyEmail());

            escPos.feed(1);

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Trans No: " + voucher.getTransactionNumber());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Fecha: " + getDateString());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Client: " + voucher.getClientName());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "RUC: " + voucher.getClientRUC());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Telefono: " + voucher.getClientPhone());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Direccion: " + voucher.getClientAddress());

            escPos.feed(1);

            String leftHeadSide = "Cant Descripcion";
            String rightHeadSide = "P.U. Total";
            String[] headWitheSpace = new String[this.printer.getCharacterNumber() - leftHeadSide.length() - rightHeadSide.length()];
            String[] equalsArr = new String[this.printer.getCharacterNumber()];
            Arrays.fill(headWitheSpace, " ");
            Arrays.fill(equalsArr, "=");

            String equalsDivider = String.join("", equalsArr);
            String head = leftHeadSide + String.join("", headWitheSpace) + rightHeadSide;

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), head);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalsDivider);

            makeProductDetails(escPos, bodyStyle, voucher.getProducts());

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalsDivider);

            this.printDetails(escPos, bodyStyle, voucher.getDetails());

            escPos.feed(1);

            voucher.getPaymentMethod().forEach((paymentMethod, value) -> {
                try {
                    escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Pago en " + paymentMethod + "=" + value);
                } catch (IOException e) {
                    System.out.println(e);
                }
            });

            escPos.feed(1);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Atendido por: " + voucher.getEmployee());

            escPos.feed(5);
            escPos.cut(EscPos.CutMode.FULL);
            escPos.close();


            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean printCashDrawerCloseDetail(PrintCashDrawerCloseDetailDTO cashDrawerCloseDetail) {
        try {
            EscPos escPos = new EscPos(outputStream);

            Style titleStyle = new Style()
                    .setJustification(EscPosConst.Justification.Center)
                    .setFontSize(Style.FontSize._1, Style.FontSize._1);
            Style bodyStyle = new Style()
                    .setFontName(setFontName());

            escPos.writeLF(titleStyle, "Cierre de Caja");
            escPos.writeLF(titleStyle, "No: " + cashDrawerCloseDetail.getCloseCashDrawerNumber());

            escPos.feed(1);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), cashDrawerCloseDetail.getBusinessName());
            escPos.feed(1);

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "RUC: " + cashDrawerCloseDetail.getCompanyRUC());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "Direccion: " + cashDrawerCloseDetail.getCompanyAddress());
            escPos.feed(1);

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "CAJA: " + cashDrawerCloseDetail.getCashDrawerName());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Responsable: " + cashDrawerCloseDetail.getEmployee());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Fecha de Apertura: " + cashDrawerCloseDetail.getInitialDate());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Fecha de Cierre: " + cashDrawerCloseDetail.getEndDate());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "No Ventas Realizadas: " + cashDrawerCloseDetail.getSuccessfullySelling());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "No Ventas Anuladas: " + cashDrawerCloseDetail.getDeclineSelling());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Valor de Apertura: " + cashDrawerCloseDetail.getOpenCashDrawerValue());
            escPos.feed(1);

            String HeaderLeft = "Cant Descripcion";
            String HeaderRight = "P.U.  Total";
            String[] equalsArr = new String[this.printer.getCharacterNumber()];
            String[] HeaderWitheSpace = new String[this.printer.getCharacterNumber() - HeaderLeft.length() - HeaderRight.length()];

            Arrays.fill(equalsArr, "=");
            Arrays.fill(HeaderWitheSpace, " ");

            String equalsDivider = String.join("", equalsArr);
            String Head = HeaderLeft + String.join("", HeaderWitheSpace) + HeaderRight;

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Detalles de ingreso:");
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalsDivider);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), Head);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalsDivider);

            makeDrawerProductDetails(escPos, bodyStyle, cashDrawerCloseDetail.getIncome());

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalsDivider);
            escPos.feed(1);

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), "Detalles de Egreso:");
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalsDivider);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), Head);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalsDivider);

            makeDrawerProductDetails(escPos, bodyStyle, cashDrawerCloseDetail.getEgress());
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), equalsDivider);

            this.printDetails(escPos, bodyStyle, cashDrawerCloseDetail.getDetails());

            escPos.feed(1);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "El Valor del Dinero en EFECTIVO DEL SISTEMA (a) es igual al valor del Dinero en EFECTIVO CONTADO (b)");
            escPos.feed(3);
            String[] underlineArr = new String[this.printer.getCharacterNumber() / 4];
            Arrays.fill(underlineArr, "_");
            String underline = String.join("", underlineArr);

            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), underline);
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Center), "TPV 3");

            escPos.feed(5);
            escPos.cut(EscPos.CutMode.FULL);
            escPos.close();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private Style.FontName setFontName() {
        if (this.printer.getFontSize().equals("A")) {
            return Style.FontName.Font_A_Default;
        }
        return Style.FontName.Font_B;
    }

    private Style.FontSize setFontSize(String size) {
        switch (size) {
            case "1" -> {
                return Style.FontSize._1;
            }
            case "3" -> {
                return Style.FontSize._3;
            }
            default -> {
                return Style.FontSize._2;
            }
        }
    }

    private EscPosConst.Justification setJustification(String justification) {
        switch (justification) {
            case "C" -> {
                return EscPosConst.Justification.Center;
            }
            case "R" -> {
                return EscPosConst.Justification.Right;
            }
            default -> {
                return EscPosConst.Justification.Left_Default;
            }
        }
    }

    private void makeProductDetails(EscPos escPos, Style bodyStyle, List<Product> mockProducts) throws IOException {
        List<String> productDetails = new ArrayList<>(List.of());
        for (Product mockProduct : mockProducts) {
            String[] bodyWitheSpace = new String[6];
            String BodyLeft = mockProduct.getQuantity() + " " + mockProduct.getName();
            String BodyRight = "";
            String priceToString = new StringBuilder(String.valueOf(
                    this.df.format(mockProduct.getPrice() * mockProduct.getQuantity()))).reverse().toString();
            for (int j = 0; j < 6; j++) {
                if (priceToString.length() > j) {
                    bodyWitheSpace[5 - j] = String.valueOf(priceToString.charAt(j));
                } else {
                    bodyWitheSpace[5 - j] = " ";
                }
            }
            BodyRight = this.df.format(mockProduct.getPrice()) + " " + String.join("", bodyWitheSpace);
            String[] middleWitheSpace = new String[this.printer.getCharacterNumber() - BodyLeft.length() - BodyRight.length()];
            Arrays.fill(middleWitheSpace, " ");
            productDetails.add(BodyLeft + String.join("", middleWitheSpace) + BodyRight);
        }

        for (String productDetail : productDetails) {
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), productDetail);
        }
    }

    private void makeDrawerProductDetails(EscPos escPos, Style bodyStyle, List<Product> mockProducts) throws IOException {
        List<String> productDetails = new ArrayList<>(List.of());
        for (Product mockProduct : mockProducts) {
            String[] bodyWitheSpace = new String[6];
            String BodyLeft = mockProduct.getQuantity() + " " + mockProduct.getName();
            String BodyRight = String.valueOf(mockProduct.getPrice());
            String[] middleWitheSpace = new String[this.printer.getCharacterNumber() - BodyLeft.length() - BodyRight.length()];
            Arrays.fill(middleWitheSpace, " ");
            productDetails.add(BodyLeft + String.join("", middleWitheSpace) + BodyRight);
        }

        for (String productDetail : productDetails) {
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Left_Default), productDetail);
        }
    }

    private static String getDateString() {
        Date utilDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm");
        String dateString = sdf.format(utilDate);
        return dateString;
    }

    private void printDetails(EscPos escPos, Style bodyStyle, Map<String, Double> details) throws IOException {
        List<String> detailsToPrint = new ArrayList<>(List.of());
        details.forEach((s, aFloat) -> {
            String[] detailsWitheSpace = new String[8];
            Arrays.fill(detailsWitheSpace, " ");
            String subtotalToString = new StringBuilder(this.df.format(aFloat)).reverse().toString();
            for (int j = 0; j < 8; j++) {
                if (subtotalToString.length() > j) {
                    detailsWitheSpace[7 - j] = String.valueOf(subtotalToString.charAt(j));
                } else {
                    detailsWitheSpace[7 - j] = " ";
                }
            }
            detailsToPrint.add(s + ": " + String.join("", detailsWitheSpace));
        });
        for (String s : detailsToPrint) {
            escPos.writeLF(bodyStyle.setJustification(EscPosConst.Justification.Right), s);
        }
    }

    private void printBarCode(EscPos escpos, String barCodeMessage, String justification) {
        try {
            BarCode barcode = new BarCode();
            barcode.setJustification(setJustification(justification));
            escpos.feed(1);
            escpos.write(barcode, barCodeMessage);
            escpos.feed(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printQRCode(EscPos escpos, String QRCodeMessage, String justification) {
        try {
            QRCode qrcode = new QRCode();
            qrcode.setJustification(setJustification(justification));
            escpos.feed(1);
            escpos.write(qrcode, QRCodeMessage);
            escpos.feed(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printImageFromUrl(EscPos escpos, String imageUrl, String justification) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage bufferedImage = ImageIO.read(url);

            CoffeeImage coffeeImage = new CoffeeImageImpl(bufferedImage);
            Bitonal algorithm = new BitonalOrderedDither();
            RasterBitImageWrapper imageWrapper = new RasterBitImageWrapper();
            imageWrapper.setJustification(setJustification(justification));

            EscPosImage escposImage = new EscPosImage(coffeeImage, algorithm);
            escpos.write(imageWrapper, escposImage);
            escpos.feed(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printImageFromFile(EscPos escpos, String fileUrl, String justification) {
        try {
            File file = new File(fileUrl);
            BufferedImage bufferedImage = ImageIO.read(file);

            CoffeeImage coffeeImage = new CoffeeImageImpl(bufferedImage);
            Bitonal algorithm = new BitonalOrderedDither();
            RasterBitImageWrapper imageWrapper = new RasterBitImageWrapper();
            imageWrapper.setJustification(setJustification(justification));

            EscPosImage escposImage = new EscPosImage(coffeeImage, algorithm);
            escpos.write(imageWrapper, escposImage);
            escpos.feed(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
