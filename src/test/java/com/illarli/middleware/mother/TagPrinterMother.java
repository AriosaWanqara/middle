package com.illarli.middleware.mother;

import com.illarli.middleware.models.TagPrinter;

public class TagPrinterMother {

    public static TagPrinter create() {
        return new TagPrinter("1", "test");
    }
}
