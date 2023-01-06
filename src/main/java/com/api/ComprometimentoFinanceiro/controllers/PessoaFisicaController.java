package com.api.ComprometimentoFinanceiro.controllers;

import com.api.ComprometimentoFinanceiro.dtos.PessoaFisicaDto;
import com.api.ComprometimentoFinanceiro.models.PessoaFisicaModel;
import com.api.ComprometimentoFinanceiro.services.PessoaFisicaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoaFisica")

public class PessoaFisicaController {

    final PessoaFisicaService pessoaFisicaService;

    public PessoaFisicaController(PessoaFisicaService pessoaFisicaService) {
        this.pessoaFisicaService = pessoaFisicaService;
    }


    @PostMapping
    public ResponseEntity<Object> salvarPessoaFisica(@RequestBody @Valid PessoaFisicaDto pessoaFisicaDto){
        var pessoaFisicaModel = new PessoaFisicaModel();
        BeanUtils.copyProperties(pessoaFisicaDto, pessoaFisicaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaFisicaService.salvarPessoaFisica(pessoaFisicaModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPessoaFisica(@PathVariable(value = "id") int id){
        Optional<PessoaFisicaModel> pessoaFisicaModelOptional = pessoaFisicaService.findById(id);
        if (!pessoaFisicaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaFisicaModelOptional);
    }

    @GetMapping
    public ResponseEntity<Object> getListaPessoasFisicas(){
        List<PessoaFisicaModel> listaPessoasFisicas = pessoaFisicaService.findAll();
        if (listaPessoasFisicas.isEmpty() || listaPessoasFisicas == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista de pessoas não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaPessoasFisicas);
    }

}
