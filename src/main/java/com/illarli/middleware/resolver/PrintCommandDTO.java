package com.illarli.middleware.resolver;

import com.illarli.middleware.models.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PrintCommandDTO {
    @Min(value = 1, message = "The delivery number is required")
    private int deliveryNumber;
    @NotEmpty(message = "The zone name is required")
    private String zoneName;
    @NotEmpty(message = "The employee number is required")
    private String employee;
    private String observation;
    @NotEmpty(message = "The sell type is required")
    private String sellType;
    @NotEmpty(message = "The products are required")
    private List<Product> products;

    public PrintCommandDTO(
            int deliveryNumber,
            String zoneName,
            String employee,
            @Nullable String observation,
            String sellType,
            List<Product> products
    ) {
        this.deliveryNumber = deliveryNumber;
        this.zoneName = zoneName;
        this.employee = employee;
        this.observation = observation;
        this.sellType = sellType;
        this.products = products;
    }

    private PrintCommandDTO() {
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public String getZoneName() {
        return zoneName;
    }

    public String getEmployee() {
        return employee;
    }

    public String getObservation() {
        return observation;
    }

    public String getSellType() {
        return sellType;
    }

    public List<Product> getProducts() {
        return products;
    }

    public PrinterSpooler createPrinterSpooler() {
        List<Details> spoolerDetails = new ArrayList<>(List.of());
        List<Details> spoolerPaymentMethods = new ArrayList<>(List.of());
        Client client = new Client(null, null, null, null);
        Company company = new Company(
                this.zoneName,
                null,
                null,
                false,
                null,
                null,
                null,
                null,
                false,
                false,
                null
        );
        this.products.forEach(product -> {
            spoolerDetails.add(new Details(product.getName(), product.getPrice()));
        });
        this.products.forEach(product -> {
            spoolerDetails.add(new Details(product.getName(), product.getPrice()));
        });

        return new PrinterSpooler(
                UUID.randomUUID().toString(),
                this.employee,
                getDateString(),
                String.valueOf(this.deliveryNumber),
                "05",
                this.getObservation(),
                null,
                spoolerDetails,
                spoolerPaymentMethods,
                client,
                this.getProducts(),
                company
        );
    }

    private static String getDateString() {
        Date utilDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        return sdf.format(utilDate);
    }

    public static PrintCommandDTO serializer(PrinterSpooler printerSpooler) {
        Company company = printerSpooler.getCompany();
        System.out.println(printerSpooler.getCode());
        return new PrintCommandDTO(
                Integer.parseInt(printerSpooler.getCode()),
                company.getBusinessName(),
                printerSpooler.getEmployee(),
                printerSpooler.getObservation(),
                printerSpooler.getEmployee(),
                printerSpooler.getProducts()
        );
    }
}
