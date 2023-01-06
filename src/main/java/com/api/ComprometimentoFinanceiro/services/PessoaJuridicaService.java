package com.api.ComprometimentoFinanceiro.services;

import com.api.ComprometimentoFinanceiro.models.PessoaFisicaModel;
import com.api.ComprometimentoFinanceiro.models.PessoaJuridicaModel;
import com.api.ComprometimentoFinanceiro.repositories.EstruturaSocietariaRepository;
import com.api.ComprometimentoFinanceiro.repositories.PessoaFisicaRepository;
import com.api.ComprometimentoFinanceiro.repositories.PessoaJuridicaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaJuridicaService {

    final PessoaJuridicaRepository pessoaJuridicaRepository;

    final PessoaFisicaRepository pessoaFisicaRepository;

    final EstruturaSocietariaRepository estruturaSocietariaRepository;

    public PessoaJuridicaService(PessoaJuridicaRepository pessoaJuridicaRepository, PessoaFisicaRepository pessoaFisicaRepository, EstruturaSocietariaRepository estruturaSocietariaRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.estruturaSocietariaRepository = estruturaSocietariaRepository;
    }
    @Transactional
    public PessoaJuridicaModel salvarPessoaJuridica(PessoaJuridicaModel pessoaJuridicaModel) throws Exception{

        if((pessoaJuridicaModel.getPessoasJuridicasList() == null) && (pessoaJuridicaModel.getPessoasFisicasList() == null) ){
            throw new Exception("Não pode haver ambas as listas de Pessoa Fisica e Pessoa Juridica nulas");
        }
        return pessoaJuridicaRepository.save(pessoaJuridicaModel);
    }

    /**
     * Como apenas é enviado os ids das pessoas fisicas e pessoas juridicas na requisição, esse método é
     * necessário para preencher a lista de objetos PessoaFisica e PessoaJuridica de acordo com os ids passados.
     */
    public void setListaPessoasFisicaEJuridica(PessoaJuridicaModel pessoaJuridicaModel){
        if(pessoaJuridicaModel.getPessoasFisicasList() != null){
            for(Integer i : pessoaJuridicaModel.getPessoasFisicasList()){
                PessoaFisicaModel pf = pessoaFisicaRepository.findById(i).get();
                pessoaJuridicaModel.addPessoaFisica(pf);
            }
        }
        if(pessoaJuridicaModel.getPessoasJuridicasList() != null){
            for(Integer i : pessoaJuridicaModel.getPessoasJuridicasList()){
                PessoaJuridicaModel pj = pessoaJuridicaRepository.findById(i).get();
                pessoaJuridicaModel.addPessoaJuridica(pj);
            }
        }
    }
    public Optional<PessoaJuridicaModel> findById(int id) {
        return pessoaJuridicaRepository.findById(id);
    }
    public List<PessoaJuridicaModel> findAll() {
        return pessoaJuridicaRepository.findAll();
    }

}
