package com.api.ComprometimentoFinanceiro.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TB_ESTRUTURA_SOCIETARIA")
@SequenceGenerator(name="sequencia_es", sequenceName ="sequencia_es", initialValue = 1, allocationSize = 1)
public class EstruturaSocietariaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequencia_es")
    private int id;

    @Getter
    @Setter
    private int[] pessoasFisicasList;

    @Getter
    @Setter
    private int[] pessoasJuridicasList;

    @Setter
    @Getter
    @ManyToMany(targetEntity = PessoaFisicaModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name="pf_fk", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<PessoaFisicaModel> pfList = new ArrayList<>();

    @Setter
    @Getter
    @ManyToMany(targetEntity = PessoaJuridicaModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name="pj_fk", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<PessoaJuridicaModel> pjList = new ArrayList<>();

    public void addPessoaFisica(PessoaFisicaModel pf) {
        this.pfList.add(pf);
    }

    public void addPessoaJuridica(PessoaJuridicaModel pj) {
        this.pjList.add(pj);
    }
}
