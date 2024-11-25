package com.api.atividade4UC14.controller;

import com.api.atividade4UC14.data.AnaliseEntity;
import com.api.atividade4UC14.data.FilmeEntity;
import com.api.atividade4UC14.service.AnaliseService;
import com.api.atividade4UC14.service.FilmeService;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.http.HttpStatus; 

import org.springframework.http.ResponseEntity; 

import org.springframework.web.bind.annotation.DeleteMapping; 

import org.springframework.web.bind.annotation.GetMapping; 

import org.springframework.web.bind.annotation.PathVariable; 

import org.springframework.web.bind.annotation.PostMapping; 

import org.springframework.web.bind.annotation.PutMapping; 

import org.springframework.web.bind.annotation.RequestBody; 

import org.springframework.web.bind.annotation.RequestMapping; 

import org.springframework.web.bind.annotation.RestController; 

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Objects;
import org.springframework.ui.Model;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/filmes")
public class FilmeController {
    
    @Autowired
    FilmeService filmeService;
    
    @Autowired
    AnaliseService analiseService;
    
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllFilmes(){
        
        List<FilmeEntity> filmes = filmeService.listarTodosFilmes();
        
        return new ResponseEntity<>(filmes, HttpStatus.OK);
        
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<FilmeEntity> getFilmeById(@PathVariable Integer id){
        FilmeEntity filme = filmeService.getFilmeId(id);
        return new ResponseEntity<>(filme, HttpStatus.OK);
        
    }
    
    @PostMapping("/adicionar")
    public ResponseEntity<FilmeEntity> addFilme(@RequestBody FilmeEntity filme){
        var novoFilme = filmeService.criaFilme(filme);
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FilmeEntity> atualizarFilme(@PathVariable Integer id, @RequestBody FilmeEntity filme){
        var filmeAtualizado = filmeService.atualizarFilme(id, filme);
        return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarFilme(@PathVariable Integer id){
    
        filmeService.deletarFilme(id);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }
    
    @PostMapping("/adicionar-analise")
    public ResponseEntity<AnaliseEntity> addAnalise (@RequestBody AnaliseEntity analise){
        
        var novaAnalise = analiseService.criaAnalise(analise, analise.getFilmeId());
        
        if (!Objects.isNull(novaAnalise)){
            return new ResponseEntity<>(novaAnalise, HttpStatus.OK);
        }else{
            return null;
        }
    }
    
    @PutMapping("/atualizar-analise/{id}")
    public ResponseEntity<AnaliseEntity> atualizarAnalise(@PathVariable Integer id, @RequestBody AnaliseEntity analise){
        var analiseAtualizado = analiseService.atualizarAnalise(id, analise);
        return new ResponseEntity<>(analiseAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar-analise/{id}")
    public ResponseEntity deletarAnalise(@PathVariable Integer id){
    
        analiseService.deletarAnalise(id);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }
    
    
    @GetMapping("/listar-analise")
    public ResponseEntity<List> getAllAnalise(){
        
        List<AnaliseEntity> filmes = analiseService.listarTodasAnalise();
        
        return new ResponseEntity<>(filmes, HttpStatus.OK);
        
    }
    
    
    @GetMapping("/pesquisar-analise/{filmeId}")
    public ResponseEntity<List> getAnaliseById(@PathVariable Integer filmeId, Model model){
        List<AnaliseEntity> analises = analiseService.listarAnaliseFilme(filmeId);
        model.addAttribute("analises", analises);
        return new ResponseEntity<>(analises, HttpStatus.OK);
        
    }
    
     
    
}
