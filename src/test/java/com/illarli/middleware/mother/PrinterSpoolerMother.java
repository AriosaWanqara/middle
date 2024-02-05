package com.illarli.middleware.mother;

import com.illarli.middleware.models.*;

import java.util.ArrayList;
import java.util.List;

public class PrinterSpoolerMother {

    public static PrinterSpooler create(String id) {
        Client client = new Client("Leo Messi", "1234875901", "0962205012", null);
        List<Details> details = new ArrayList<>() {{
            add(new Details("Subtotal", 4.78D));
            add(new Details("iva 12%", 0.12D));
            add(new Details("total", 4.90D));
        }};
        List<Details> paymentMethods = new ArrayList<>() {{
            add(new Details("Efectivo", 4.90D));
        }};
        List<Product> products = new ArrayList<>() {{
            add(new Product("Pan", 4, 2.45D));
            add(new Product("Cola", 1, 1.00D));
            add(new Product("Queso", 1, 1.45D));
        }};
        Company company = new Company(
                "S.A",
                null,
                "1987467825001",
                false,
                null,
                null,
                "0998761253",
                "s.a@gmail.com",
                false,
                false,
                null
        );
        return new PrinterSpooler(
                id != null ? id : "1",
                "Jorge Ariosa",
                "2024-2-5",
                "kasdm1",
                "01",
                null,
                null,
                details,
                paymentMethods,
                client,
                products,
                company
        );
    }
}
