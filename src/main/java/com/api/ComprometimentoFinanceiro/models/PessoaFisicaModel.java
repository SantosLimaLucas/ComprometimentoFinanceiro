package com.api.ComprometimentoFinanceiro.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name="TB_PESSOA_FISICA")
@SequenceGenerator(name="sequencia_pf", sequenceName ="sequencia_pf", initialValue = 1, allocationSize = 1)
public class PessoaFisicaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequencia_pf")
    private int id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, length=11)
    private String cpf;

    @Getter
    @Setter
    @Column(nullable = false)
    private String nome;

    @Getter
    @Setter
    @Column(nullable = false)
    private double bensPF;

}
