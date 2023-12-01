package com.assembleia.votacao.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Pauta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, insertable = false, unique = true)
    private Long id;

    @Column
    private String titulo;

    @Column
    private String descricao;

    public Pauta(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Pauta() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return Objects.equals(id, pauta.id) &&
                Objects.equals(titulo, pauta.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo);
    }
}
