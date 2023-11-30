package com.assembleia.votacao.dto;


import com.assembleia.votacao.entity.Decisao;

public class VotoRequestDTO {
    private Long sessaoId;
    private String cpf;
    private Decisao decisao;

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

    public Decisao getDecisao() {
        return decisao;
    }

    public void setDecisao(Decisao decisao) {
        this.decisao = decisao;
    }
}
