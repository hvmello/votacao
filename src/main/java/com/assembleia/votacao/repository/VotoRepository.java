package com.assembleia.votacao.repository;

import com.assembleia.votacao.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
}

