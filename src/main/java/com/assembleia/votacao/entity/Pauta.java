package com.assembleia.votacao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Pauta {


    @Id
    @Column
    private Long pautaId;

    private String titulo;

    private String descricao;

    public Pauta(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Pauta() {

    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long id) {
        this.pautaId = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pauta pauta = (Pauta) o;
        return Objects.equals(pautaId, pauta.pautaId) &&
                Objects.equals(titulo, pauta.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pautaId, titulo);
    }
}
