package com.api.ComprometimentoFinanceiro.repositories;

import com.api.ComprometimentoFinanceiro.models.PessoaFisicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaModel, Integer> {
}
