package com.illarli.middleware.resolver;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class PrintTextDTO {

    @NotEmpty(message = "The messages field is required")
    private List<String> messages;

    public PrintTextDTO(List<String> messages) {
        this.messages = messages;
    }

    private PrintTextDTO() {
    }

    public List<String> getMessages() {
        return messages;
    }
}
