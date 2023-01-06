package com.api.ComprometimentoFinanceiro.services;

import com.api.ComprometimentoFinanceiro.models.EstruturaSocietariaModel;
import com.api.ComprometimentoFinanceiro.models.PessoaFisicaModel;
import com.api.ComprometimentoFinanceiro.models.PessoaJuridicaModel;
import com.api.ComprometimentoFinanceiro.repositories.EstruturaSocietariaRepository;
import com.api.ComprometimentoFinanceiro.repositories.PessoaFisicaRepository;
import com.api.ComprometimentoFinanceiro.repositories.PessoaJuridicaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaFisicaService {

    final PessoaFisicaRepository pessoaFisicaRepository;
    final PessoaJuridicaRepository pessoaJuridicaRepository;

    final EstruturaSocietariaRepository estruturaSocietariaRepository;

    public PessoaFisicaService(PessoaFisicaRepository pessoaFisicaRepository, PessoaJuridicaRepository pessoaJuridicaRepository, EstruturaSocietariaRepository estruturaSocietariaRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.estruturaSocietariaRepository = estruturaSocietariaRepository;
    }
    @Transactional
    public PessoaFisicaModel salvarPessoaFisica(PessoaFisicaModel pessoaFisicaModel) {
        return pessoaFisicaRepository.save(pessoaFisicaModel);
    }
    public Optional<PessoaFisicaModel> findById(int id) {
        return pessoaFisicaRepository.findById(id);
    }

    public List<PessoaFisicaModel> findAll() {
        return pessoaFisicaRepository.findAll();
    }

}
