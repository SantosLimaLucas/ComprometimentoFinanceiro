package com.api.ComprometimentoFinanceiro.services;

import com.api.ComprometimentoFinanceiro.models.PessoaFisicaModel;
import com.api.ComprometimentoFinanceiro.models.PessoaJuridicaModel;
import com.api.ComprometimentoFinanceiro.repositories.PessoaFisicaRepository;
import com.api.ComprometimentoFinanceiro.repositories.PessoaJuridicaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PessoaJuridicaService {

    final PessoaJuridicaRepository pessoaJuridicaRepository;

    final PessoaFisicaRepository pessoaFisicaRepository;

    public PessoaJuridicaService(PessoaJuridicaRepository pessoaJuridicaRepository, PessoaFisicaRepository pessoaFisicaRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }
    @Transactional
    public PessoaJuridicaModel save(PessoaJuridicaModel pessoaJuridicaModel) throws Exception{

        if((pessoaJuridicaModel.getPessoasJuridicasList() == null) && (pessoaJuridicaModel.getPessoasFisicasList() == null) ){
            throw new Exception("Não pode haver ambas as listas de Pessoa Fisica e Pessoa Juridica nulas");
        }
        return pessoaJuridicaRepository.save(pessoaJuridicaModel);
    }
    public void setListaPessoasFisicaEJuridica(PessoaJuridicaModel pessoaJuridicaModel){
        if(pessoaJuridicaModel.getPessoasFisicasList() != null){
            for(Integer i : pessoaJuridicaModel.getPessoasFisicasList()){
                PessoaFisicaModel pf = pessoaFisicaRepository.getOne(i);
                pessoaJuridicaModel.addPessoaFisica(pf);
            }
        }
        if(pessoaJuridicaModel.getPessoasJuridicasList() != null){
            for(Integer i : pessoaJuridicaModel.getPessoasJuridicasList()){
                PessoaJuridicaModel pj = pessoaJuridicaRepository.getOne(i);
                pessoaJuridicaModel.addPessoaJuridica(pj);
            }
        }
    }
    public Optional<PessoaJuridicaModel> findById(int id) {
        return pessoaJuridicaRepository.findById(id);
    }
}
