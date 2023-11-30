package com.assembleia.votacao.dto;

public class ResultadoSessaoDTO {

    private PautaResponseDTO pauta;
    private long votosSim;
    private long votosNao;

    public PautaResponseDTO getPauta() {
        return pauta;
    }

    public void setPauta(PautaResponseDTO pauta) {
        this.pauta = pauta;
    }

    public long getVotosSim() {
        return votosSim;
    }

    public void setVotosSim(long votosSim) {
        this.votosSim = votosSim;
    }

    public long getVotosNao() {
        return votosNao;
    }

    public void setVotosNao(long votosNao) {
        this.votosNao = votosNao;
    }
}
