package com.api.ComprometimentoFinanceiro.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class PessoaJuridicaDto {

    @Getter
    @Setter
    @NotBlank
    private String cnpj;

    @Getter
    @Setter
    @NotBlank
    private String nome;

    @Getter
    @Setter
    private int[] pessoasFisicasList;

    @Getter
    @Setter
    private int[] pessoasJuridicasList;

    @Getter
    @Setter
    @NotNull
    private double bensPJ;
}
