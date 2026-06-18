package org.example.exam24hours.dto;

import org.example.exam24hours.model.AlertStatus;

public class UpdateStatusDTO {
    private AlertStatus status;

    public UpdateStatusDTO(AlertStatus status) {
        this.status = status;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public void setStatus(AlertStatus status) {
        this.status = status;
    }
}
