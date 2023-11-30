package com.assembleia.votacao.dto;

public class VotoResponseDTO {
    private boolean success;

    public VotoResponseDTO(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
