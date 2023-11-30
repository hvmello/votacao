package com.assembleia.votacao.repository;

import com.assembleia.votacao.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
}
