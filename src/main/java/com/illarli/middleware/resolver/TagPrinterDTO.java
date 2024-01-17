package com.illarli.middleware.resolver;

import com.illarli.middleware.models.TagPrinter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class TagPrinterDTO {
    private String id;
    @NotBlank(message = "The name field is required")
    private String name;

    public TagPrinterDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private TagPrinterDTO() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TagPrinter createTagPrinter() {
        return new TagPrinter(
                this.id != null ? this.id : UUID.randomUUID().toString(),
                this.name
        );
    }
}
