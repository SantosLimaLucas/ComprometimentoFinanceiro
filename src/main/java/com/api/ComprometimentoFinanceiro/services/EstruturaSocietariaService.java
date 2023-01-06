package com.api.ComprometimentoFinanceiro.services;

import com.api.ComprometimentoFinanceiro.models.EstruturaSocietariaModel;
import com.api.ComprometimentoFinanceiro.models.PessoaFisicaModel;
import com.api.ComprometimentoFinanceiro.models.PessoaJuridicaModel;
import com.api.ComprometimentoFinanceiro.repositories.EstruturaSocietariaRepository;
import com.api.ComprometimentoFinanceiro.repositories.PessoaFisicaRepository;
import com.api.ComprometimentoFinanceiro.repositories.PessoaJuridicaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class EstruturaSocietariaService {

    final EstruturaSocietariaRepository estruturaSocietariaRepository;
    final PessoaJuridicaRepository pessoaJuridicaRepository;
    final PessoaFisicaRepository pessoaFisicaRepository;

    private List<PessoaFisicaModel> pfList = new ArrayList<>();
    private List<PessoaJuridicaModel> pjList = new ArrayList<>();

    private Double bensPj = 0d;

    public EstruturaSocietariaService(EstruturaSocietariaRepository estruturaSocietariaRepository, PessoaJuridicaRepository pessoaJuridicaRepository, PessoaFisicaRepository pessoaFisicaRepository) {
        this.estruturaSocietariaRepository = estruturaSocietariaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    @Transactional
    public EstruturaSocietariaModel salvarEstruturaSocietaria(EstruturaSocietariaModel estruturaSocietariaModel) throws Exception{

        if((estruturaSocietariaModel.getPessoasJuridicasList() == null) && (estruturaSocietariaModel.getPessoasFisicasList() == null) ){
            throw new Exception("Não pode haver ambas as listas de Pessoa Fisica e Pessoa Juridica nulas");
        }
        return estruturaSocietariaRepository.save(estruturaSocietariaModel);
    }
    /**
     * Como apenas é enviado os ids das pessoas fisicas e pessoas juridicas na requisição, esse método é
     * necessário para preencher a lista de objetos PessoaFisica e PessoaJuridica de acordo com os ids passados.
     */
    public void setListaPessoasFisicaEJuridica(EstruturaSocietariaModel estruturaSocietariaModel){
        if(estruturaSocietariaModel.getPessoasFisicasList() != null){
            for(Integer i : estruturaSocietariaModel.getPessoasFisicasList()){
                PessoaFisicaModel pf = pessoaFisicaRepository.findById(i).get();
                estruturaSocietariaModel.addPessoaFisica(pf);
            }
        }
        if(estruturaSocietariaModel.getPessoasJuridicasList() != null){
            for(Integer i : estruturaSocietariaModel.getPessoasJuridicasList()){
                PessoaJuridicaModel pj = pessoaJuridicaRepository.findById(i).get();
                estruturaSocietariaModel.addPessoaJuridica(pj);
            }
        }
    }

    public Double comprometimentoFinanceiro(EstruturaSocietariaModel estruturaSocietariaModel) {
        Double comprometimentoFinanceiro = 0d;
        adicionarPF(estruturaSocietariaModel.getPfList());
        adicionarPJ(estruturaSocietariaModel.getPjList());

        percorrerPj(estruturaSocietariaModel.getPjList());
        this.pfList = new ArrayList<> (new HashSet<>(pfList));
        this.pjList = new ArrayList<> (new HashSet<>(pjList));

        comprometimentoFinanceiro+=calcularBensPF(pfList);
        comprometimentoFinanceiro+=calcularBensPJ(pjList);
        return comprometimentoFinanceiro;
    }

    private void percorrerPj(List<PessoaJuridicaModel> pjs){
        for(PessoaJuridicaModel pj: pjs){
            if(pj.getPfList()!=null){
                for(PessoaFisicaModel pf: pj.getPfList()){
                    this.pfList.add(pf);
                }
            }
            if(pj.getPjList()!= null && pj.getPjList().size()>0){
                for(PessoaJuridicaModel peju: pj.getPjList()){
                    this.pjList.add(peju);
                }
                percorrerPj(pj.getPjList());
            }
        }
    }
    private void adicionarPF(List<PessoaFisicaModel> pfs){
        for(PessoaFisicaModel pf: pfs){
           this.pfList.add(pf);
        }
    }

    private void adicionarPJ(List<PessoaJuridicaModel> pjs){
        for(PessoaJuridicaModel pj: pjs){
            this.pjList.add(pj);
        }
    }

    private Double calcularBensPF(List<PessoaFisicaModel> pfs){
        Double bens = 0d;
        for(PessoaFisicaModel pf: pfs){
            bens += pf.getBensPF();
        }
        return bens;
    }

    private Double calcularBensPJ(List<PessoaJuridicaModel> pjs){
        for(PessoaJuridicaModel pj: pjs){
            bensPj += pj.getBensPJ();
        }
        return bensPj;
    }

    public Optional<EstruturaSocietariaModel> findById(int id) {
        return estruturaSocietariaRepository.findById(id);
    }

    public List<EstruturaSocietariaModel> findAll() {
        return estruturaSocietariaRepository.findAll();
    }

}
