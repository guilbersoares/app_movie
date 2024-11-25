package com.api.atividade4UC14.service;


import com.api.atividade4UC14.data.AnaliseEntity;
import com.api.atividade4UC14.data.FilmeEntity;
import com.api.atividade4UC14.data.FilmeRepository;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.stereotype.Service; 


@Service
public class FilmeService {
    
    @Autowired
    FilmeRepository filmeRepository;
    
    AnaliseService analiseService;
    
    public FilmeEntity criaFilme(FilmeEntity film){
        film.setId(null);
        filmeRepository.save(film);
        return film;
    }
    
    public FilmeEntity atualizarFilme(Integer filmId, FilmeEntity filmeRequest) {
        FilmeEntity filme = getFilmeId(filmId);

        // Atualizar os campos com os valores fornecidos na requisição
        filme.setTitulo(filmeRequest.getTitulo());
        filme.setAno(filmeRequest.getAno());
        filme.setGenero(filmeRequest.getGenero());
        filme.setSinopse(filmeRequest.getSinopse());

        // Salvar as alterações no banco de dados
        filmeRepository.save(filme);

        return filme;
    }
    
    public FilmeEntity getFilmeId(Integer filmId){
        
        return filmeRepository.findById(filmId).orElse(null);
    }
    
    public List<FilmeEntity> listarTodosFilmes(){
        return filmeRepository.findAll();
    }
    
    public void deletarFilme(Integer filmeId) {
        FilmeEntity filme = getFilmeId(filmeId);

        if (analiseService != null && analiseService.listarTodasAnalise() != null) {
            for (AnaliseEntity analise : analiseService.listarTodasAnalise()) {
                if (analise.getFilmeId() == filmeId) {
                    analiseService.deletarAnalise(analise.getId());
                }
            }
        }

        filmeRepository.deleteById(filme.getId());
    }
  
}
