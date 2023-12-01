package com.assembleia.votacao.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, insertable = false, unique = true)
    private Long id;

    @ManyToOne
    private Pauta pauta;
    @Column
    private Integer minutesToExpiration;
    @Column
    private LocalDateTime dataHoraFimVotacaoPauta;

    @OneToMany
    private List<Voto> votos;

    @Column
    private boolean fechada;

    public Sessao() {
        this.votos = new ArrayList<>();
    }

    public Sessao(Pauta pauta, Integer minutesToExpiration) {
        this.pauta = pauta;
        this.minutesToExpiration = minutesToExpiration;
        this.dataHoraFimVotacaoPauta = LocalDateTime.now().plusSeconds(minutesToExpiration * 60);
        this.votos = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Integer getMinutesToExpiration() {
        return minutesToExpiration;
    }

    public void setMinutesToExpiration(Integer minutesToExpiration) {
        this.minutesToExpiration = minutesToExpiration;
    }

    public LocalDateTime getDataHoraFimVotacaoPauta() {
        return dataHoraFimVotacaoPauta;
    }

    public void setDataHoraFimVotacaoPauta(LocalDateTime dataHoraFimVotacaoPauta) {
        this.dataHoraFimVotacaoPauta = dataHoraFimVotacaoPauta;
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

    public void addVote(Voto voto) {
        votos.add(voto);
    }

    public boolean isVotoDuplicado(String cpf) {
        return this.votos.stream().anyMatch(vote -> vote.getCpf().equals(cpf));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sessao sessao = (Sessao) o;
        return Objects.equals(id, sessao.id) &&
                Objects.equals(minutesToExpiration, sessao.minutesToExpiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, minutesToExpiration);
    }


}
