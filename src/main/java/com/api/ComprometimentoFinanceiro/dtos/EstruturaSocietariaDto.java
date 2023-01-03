package com.api.ComprometimentoFinanceiro.dtos;

import lombok.Getter;
import lombok.Setter;

public class EstruturaSocietariaDto {

    @Getter
    @Setter
    private int[] pessoasFisicasList;

    @Getter
    @Setter
    private int[] pessoasJuridicasList;
}
