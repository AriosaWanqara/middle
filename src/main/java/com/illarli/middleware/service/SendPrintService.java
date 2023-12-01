package com.illarli.middleware.service;

import com.illarli.middleware.models.repositories.PrinterLibraryRepository;
import com.illarli.middleware.resolver.*;

public class SendPrintService {
    private final PrinterLibraryRepository printLibraryRepository;

    public SendPrintService(PrinterLibraryRepository printLibraryRepository) {
        this.printLibraryRepository = printLibraryRepository;
    }

    public String[] getSOPrinters() {
        return this.printLibraryRepository.getPrinterList();
    }

    public boolean printTest() {
        return printLibraryRepository.printTest();
    }

    public boolean printText(PrintTextDTO text) {
        return printLibraryRepository.printText(text);
    }

    public boolean printMedia(PrintMediaDTO media) {
        return printLibraryRepository.printMedia(media);
    }

    public boolean printTitle(PrintTitleDTO title) {
        return printLibraryRepository.printTitle(title);
    }

    public boolean cut() {
        return printLibraryRepository.cut();
    }

    public boolean openCashDrawer() {
        return printLibraryRepository.OpenCashDrawerFromPrinter();
    }

    public boolean quotation(PrintQuotationDTO quotation) {
        return printLibraryRepository.printQuotation(quotation);
    }

    public boolean cashDrawerDetail(PrintCashDrawerCloseDetailDTO cashDrawerDetail) {
        return printLibraryRepository.printCashDrawerCloseDetail(cashDrawerDetail);
    }

    public boolean voucher(PrintVoucherDTO voucher) {
        return printLibraryRepository.printVoucher(voucher);
    }

    public boolean electronicInvoice(PrintElectronicInvoiceDTO electronicInvoice) {
        return printLibraryRepository.printElectronicInvoice(electronicInvoice);
    }

    public boolean preTicket(PrintPreTicketDTO preTicket) {
        return printLibraryRepository.printPreTicket(preTicket);
    }

    public boolean command(PrintCommandDTO command) {
        return printLibraryRepository.printCommand(command);
    }
}
