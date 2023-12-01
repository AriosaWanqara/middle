package com.illarli.middleware.resolver;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

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
    private Map<@Min(1) Integer, String> products;

    public PrintCommandDTO(int deliveryNumber, String zoneName, String employee, @Nullable String observation, String sellType, Map<@Min(1) Integer, String> products) {
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

    public Map<Integer, String> getProducts() {
        return products;
    }
}
