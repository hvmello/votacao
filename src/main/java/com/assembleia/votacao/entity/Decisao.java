package com.assembleia.votacao.entity;

public enum Decisao {

    NAO("Não"),
    SIM("Sim");

    private String voto;

    Decisao(String voto){
        this.voto = voto;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }
}
