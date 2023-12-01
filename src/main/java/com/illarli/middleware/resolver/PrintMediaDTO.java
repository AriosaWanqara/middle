package com.illarli.middleware.resolver;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class PrintMediaDTO {
    @NotNull(message = "The media field is required")
    private Map<String, List<String>> media;
    @NotBlank()
    private String justification;

    public PrintMediaDTO(Map<String, List<String>> media, String justification) {
        this.media = media;
        this.justification = justification;
    }

    private PrintMediaDTO() {
    }

    public Map<String, List<String>> getMedia() {
        return media;
    }

    public String getJustification() {
        return justification;
    }
}
