package com.assembleia.votacao.dto;

public class SessaoRequestDTO {

    private String pautaId;

    private Integer minutosParaFechamentoSessao;

    public String getPautaId() {
        return pautaId;
    }

    public void setPautaId(String pautaId) {
        this.pautaId = pautaId;
    }

    public Integer getMinutosParaFechamentoSessao() {
        return minutosParaFechamentoSessao;
    }

    public void setMinutosParaFechamentoSessao(Integer minutosParaFechamentoSessao) {
        this.minutosParaFechamentoSessao = minutosParaFechamentoSessao;
    }
}
