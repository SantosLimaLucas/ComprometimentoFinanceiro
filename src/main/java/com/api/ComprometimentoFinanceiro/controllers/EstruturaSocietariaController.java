package com.api.ComprometimentoFinanceiro.controllers;

import com.api.ComprometimentoFinanceiro.dtos.EstruturaSocietariaDto;
import com.api.ComprometimentoFinanceiro.models.EstruturaSocietariaModel;
import com.api.ComprometimentoFinanceiro.services.EstruturaSocietariaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estruturaSocietaria")
public class EstruturaSocietariaController {
    final EstruturaSocietariaService estruturaSocietariaService;

    public EstruturaSocietariaController(EstruturaSocietariaService estruturaSocietariaService) {
        this.estruturaSocietariaService = estruturaSocietariaService;
    }

    @PostMapping
    public ResponseEntity<Object> salvarEstruturaSocietaria(@RequestBody @Valid EstruturaSocietariaDto estruturaSocietariaDto) throws Exception {
        var estruturaSocietariaModel = new EstruturaSocietariaModel();
        BeanUtils.copyProperties(estruturaSocietariaDto, estruturaSocietariaModel);
        estruturaSocietariaService.setListaPessoasFisicaEJuridica(estruturaSocietariaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(estruturaSocietariaService.salvarEstruturaSocietaria(estruturaSocietariaModel));
    }

    @GetMapping("/{id}")
    public EstruturaSocietariaModel getEstruturaSocietaria(@PathVariable(value = "id") int id){
        EstruturaSocietariaModel es = estruturaSocietariaService.findById(id).get();
        return es;
    }

    @GetMapping("/comprometimento-financeiro/{id}")
    public Double getComprometimentoFinanceiro(@PathVariable(value = "id") int id){
        EstruturaSocietariaModel es = estruturaSocietariaService.findById(id).get();
        Double d = estruturaSocietariaService.comprometimentoFinanceiro(es);
        return d;
    }

    @GetMapping
    public List<EstruturaSocietariaModel> getEstruturaSocietaria(){
        List<EstruturaSocietariaModel> listaEstruturasSocietarias = estruturaSocietariaService.findAll();
        return listaEstruturasSocietarias;
    }
}
