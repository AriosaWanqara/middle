package com.illarli.middleware.mother;

import com.illarli.middleware.models.CashDrawer;
import com.illarli.middleware.models.Printer;
import com.illarli.middleware.models.PrinterType;

import java.util.LinkedList;

public class CashDrawerMother {

    public static CashDrawer create() {

        Printer printer = new Printer
                (
                        "ad",
                        "null",
                        "null",
                        1,
                        1,
                        null,
                        1,
                        1,
                        PrinterType.WIFI,
                        new LinkedList<>()
                );
        return new CashDrawer("1", null, printer, false);
    }
}
