package com.assembleia.votacao.dto;

import com.assembleia.votacao.entity.Voto;

import java.time.LocalDateTime;
import java.util.List;

public class SessaoResponseDTO {

    private String id;

    private PautaResponseDTO pauta;

    private Integer minutosParaFechamentoSessao;

    private LocalDateTime dataHoraFechamentoSessao;

    private List<Voto> votos;

    private boolean fechada;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PautaResponseDTO getPauta() {
        return pauta;
    }

    public void setPauta(PautaResponseDTO pauta) {
        this.pauta = pauta;
    }

    public Integer getMinutosParaFechamentoSessao() {
        return minutosParaFechamentoSessao;
    }

    public void setMinutosParaFechamentoSessao(Integer minutosParaFechamentoSessao) {
        this.minutosParaFechamentoSessao = minutosParaFechamentoSessao;
    }

    public LocalDateTime getDataHoraFechamentoSessao() {
        return dataHoraFechamentoSessao;
    }

    public void setDataHoraFechamentoSessao(LocalDateTime dataHoraFechamentoSessao) {
        this.dataHoraFechamentoSessao = dataHoraFechamentoSessao;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }

    public boolean isFechada() {
        return fechada;
    }

    public void setFechada(boolean fechada) {
        this.fechada = fechada;
    }

    public SessaoResponseDTO(String id, PautaResponseDTO pauta, Integer minutosParaFechamentoSessao, LocalDateTime dataHoraFechamentoSessao, List<Voto> votos, boolean fechada) {
        this.id = id;
        this.pauta = pauta;
        this.minutosParaFechamentoSessao = minutosParaFechamentoSessao;
        this.dataHoraFechamentoSessao = dataHoraFechamentoSessao;
        this.votos = votos;
        this.fechada = fechada;
    }
}
