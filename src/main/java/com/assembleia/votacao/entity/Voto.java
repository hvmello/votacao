package com.assembleia.votacao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Voto {

    @Id
    private Long id;
    private String cpf;
    private Decisao decisao;

    public Voto(String cpf, Decisao decisao) {
        this.cpf = cpf;
        this.decisao = decisao;
    }

    public Voto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return Objects.equals(id, voto.id) &&
                Objects.equals(cpf, voto.cpf) &&
                decisao == voto.decisao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, decisao);
    }
}
