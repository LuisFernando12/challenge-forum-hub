package hub.forum.api.domain.topic.enums;

import java.util.Arrays;

public enum Status {
    ACTIVE("active"),
    SOLVED("solved"),
    CANCELED("canceled"),
    CLOSED("closed");

    private String status;

    Status(String status) {
        this.status = status;
    }

}
