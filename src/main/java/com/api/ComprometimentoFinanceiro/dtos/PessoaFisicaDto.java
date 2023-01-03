package com.api.ComprometimentoFinanceiro.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PessoaFisicaDto {

    @NotBlank
    @Size(min = 11, max = 11)
    @Getter
    @Setter
    private String cpf;

    @NotBlank
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Getter
    @Setter
    private double bensPF;
}
