package com.illarli.middleware.resolver;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class PrintTitleDTO {
    @NotEmpty()
    private List<String> title;
    @NotBlank
    private String justification;
    @Min(1)
    @Max(3)
    private String size;

    public PrintTitleDTO(List<String> title, String justification, String size) {
        this.title = title;
        this.justification = justification;
        this.size = size;
    }

    private PrintTitleDTO() {
    }

    public List<String> getTitle() {
        return title;
    }

    public String getJustification() {
        return justification;
    }

    public String getSize() {
        return size;
    }
}
