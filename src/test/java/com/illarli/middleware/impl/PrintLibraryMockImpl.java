package com.illarli.middleware.impl;

import com.illarli.middleware.models.repositories.PrinterLibraryRepository;
import com.illarli.middleware.resolver.*;

public class PrintLibraryMockImpl implements PrinterLibraryRepository {
    @Override
    public String[] getPrinterList() {
        return new String[0];
    }

    @Override
    public boolean OpenCashDrawerFromPrinter() {
        return true;
    }

    @Override
    public boolean printTest() {
        return true;
    }

    @Override
    public boolean cut() {
        return true;
    }

    @Override
    public boolean printText(PrintTextDTO printTextDTO) {
        return true;
    }

    @Override
    public boolean printTitle(PrintTitleDTO title) {
        return true;
    }

    @Override
    public boolean printMedia(PrintMediaDTO media) {
        return true;
    }

    @Override
    public boolean printQuotation(PrintQuotationDTO quotation) {
        return true;
    }

    @Override
    public boolean printPreTicket(PrintPreTicketDTO preTicket) {
        return true;
    }

    @Override
    public boolean printCommand(PrintCommandDTO command) {
        return true;
    }

    @Override
    public boolean printElectronicInvoice(PrintElectronicInvoiceDTO electronicInvoice) {
        return true;
    }

    @Override
    public boolean printVoucher(PrintVoucherDTO voucher) {
        return true;
    }

    @Override
    public boolean printCashDrawerCloseDetail(PrintCashDrawerCloseDetailDTO cashDrawerCloseDetail) {
        return true;
    }

    @Override
    public boolean printFlyer(PrintFlyersDTO printFlyersDTO) {
        return true;
    }
}
