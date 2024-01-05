package com.illarli.middleware.models.repositories;

import com.illarli.middleware.resolver.*;

public interface PrinterLibraryRepository {
    String[] getPrinterList();

    boolean OpenCashDrawerFromPrinter();

    boolean printTest();

    boolean cut();

    boolean printText(PrintTextDTO printTextDTO);

    boolean printTitle(PrintTitleDTO title);

    boolean printMedia(PrintMediaDTO media);

    boolean printQuotation(PrintQuotationDTO quotation);

    boolean printPreTicket(PrintPreTicketDTO preTicket);

    boolean printCommand(PrintCommandDTO command);

    boolean printElectronicInvoice(PrintElectronicInvoiceDTO electronicInvoice);

    boolean printVoucher(PrintVoucherDTO voucher);

    boolean printCashDrawerCloseDetail(PrintCashDrawerCloseDetailDTO cashDrawerCloseDetail);

    boolean printFlyer(PrintFlyersDTO printFlyersDTO);
}
