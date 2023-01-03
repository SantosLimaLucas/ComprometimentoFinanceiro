package com.api.ComprometimentoFinanceiro.controllers;

import com.api.ComprometimentoFinanceiro.dtos.EstruturaSocietariaDto;
import com.api.ComprometimentoFinanceiro.models.EstruturaSocietariaModel;
import com.api.ComprometimentoFinanceiro.services.EstruturaSocietariaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/estruturaSocietaria")
public class EstruturaSocietariaController {
    final EstruturaSocietariaService estruturaSocietariaService;

    public EstruturaSocietariaController(EstruturaSocietariaService estruturaSocietariaService) {
        this.estruturaSocietariaService = estruturaSocietariaService;
    }

    @PostMapping
    public ResponseEntity<Object> setEstruturaSocietaria(@RequestBody @Valid EstruturaSocietariaDto estruturaSocietariaDto) throws Exception {
        var estruturaSocietariaModel = new EstruturaSocietariaModel();
        BeanUtils.copyProperties(estruturaSocietariaDto, estruturaSocietariaModel);
        estruturaSocietariaService.setListaPessoasFisicaEJuridica(estruturaSocietariaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(estruturaSocietariaService.save(estruturaSocietariaModel));
    }
    @GetMapping("/{id}")
    public EstruturaSocietariaModel getEstruturaSocietaria(@PathVariable(value = "id") int id){
        EstruturaSocietariaModel es = estruturaSocietariaService.findById(id).get();
        Double d = estruturaSocietariaService.comprometimentoFinanceiro(es);
        return es;
    }
}
