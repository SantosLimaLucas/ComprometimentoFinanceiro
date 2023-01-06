package com.api.ComprometimentoFinanceiro.controllers;


import com.api.ComprometimentoFinanceiro.dtos.PessoaJuridicaDto;
import com.api.ComprometimentoFinanceiro.models.PessoaJuridicaModel;
import com.api.ComprometimentoFinanceiro.services.PessoaJuridicaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoaJuridica")
public class PessoaJuridicaController {

    final PessoaJuridicaService pessoaJuridicaService;

    public PessoaJuridicaController(PessoaJuridicaService pessoaJuridicaService) {
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @PostMapping
    public ResponseEntity<Object> salvarPessoaJuridica(@RequestBody @Valid PessoaJuridicaDto pessoaJuridicaDto) throws Exception {
        var pessoaJuridicaModel = new PessoaJuridicaModel();
        BeanUtils.copyProperties(pessoaJuridicaDto, pessoaJuridicaModel);
        pessoaJuridicaService.setListaPessoasFisicaEJuridica(pessoaJuridicaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaJuridicaService.salvarPessoaJuridica(pessoaJuridicaModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPessoaJuridica(@PathVariable(value = "id") int id){
        Optional<PessoaJuridicaModel> pessoaJuridicaModelOptional = pessoaJuridicaService.findById(id);
        if (!pessoaJuridicaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaJuridicaModelOptional);
    }

    @GetMapping
    public ResponseEntity<Object> getListaPessoasJuridicas(){
        List<PessoaJuridicaModel> listaPessoasJuridicas = pessoaJuridicaService.findAll();
        if (listaPessoasJuridicas.isEmpty() || listaPessoasJuridicas == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaPessoasJuridicas);
    }
}
