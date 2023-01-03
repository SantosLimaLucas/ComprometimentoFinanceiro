package com.api.ComprometimentoFinanceiro.services;

import com.api.ComprometimentoFinanceiro.models.PessoaFisicaModel;
import com.api.ComprometimentoFinanceiro.repositories.PessoaFisicaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaFisicaService {

    final PessoaFisicaRepository pessoaFisicaRepository;

    public PessoaFisicaService(PessoaFisicaRepository pessoaFisicaRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }
    @Transactional
    public PessoaFisicaModel save(PessoaFisicaModel pessoaFisicaModel) {
        return pessoaFisicaRepository.save(pessoaFisicaModel);
    }
    public Optional<PessoaFisicaModel> findById(int id) {
        return pessoaFisicaRepository.findById(id);
    }
}
