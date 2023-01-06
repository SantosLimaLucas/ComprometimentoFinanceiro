package com.api.ComprometimentoFinanceiro.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TB_PESSOA_JURIDICA")
@SequenceGenerator(name="sequencia_pj", sequenceName ="sequencia_pj", initialValue = 1, allocationSize = 1)
public class PessoaJuridicaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequencia_pj")
    @Getter
    private int id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, length=14)
    private String cnpj;

    @Getter
    @Setter
    @Column(nullable = false)
    private String nome;

    @Getter
    @Setter
    private int[] pessoasFisicasList;

    @Getter
    @Setter
    private int[] pessoasJuridicasList;

    @Setter
    @Getter
    @ManyToMany(targetEntity = PessoaFisicaModel.class)
    @JoinColumn(name="pf_fk", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<PessoaFisicaModel> pfList = new ArrayList<>();

    @Setter
    @Getter
    @ManyToMany(targetEntity = PessoaJuridicaModel.class)
    @JoinColumn(name="pj_fk", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<PessoaJuridicaModel> pjList = new ArrayList<>();

    @Getter
    @Setter
    @Column(nullable = false)
    private double bensPJ;

    public void addPessoaFisica(PessoaFisicaModel pf) {
        this.pfList.add(pf);
    }

    public void addPessoaJuridica(PessoaJuridicaModel pj) {
        this.pjList.add(pj);
    }
}
