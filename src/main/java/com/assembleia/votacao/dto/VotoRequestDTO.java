package com.assembleia.votacao.dto;


import com.assembleia.votacao.entity.Decisao;

public class VotoRequestDTO {
    private Long sessaoId;
    private String cpf;
    private String decisao;

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDecisao() {
        return decisao;
    }

    public void setDecisao(String decisao) {
        this.decisao = decisao;
    }
}
