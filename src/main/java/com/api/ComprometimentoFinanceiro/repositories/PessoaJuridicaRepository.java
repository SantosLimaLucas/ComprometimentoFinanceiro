package com.api.ComprometimentoFinanceiro.repositories;

import com.api.ComprometimentoFinanceiro.models.PessoaJuridicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridicaModel, Integer> {
}
