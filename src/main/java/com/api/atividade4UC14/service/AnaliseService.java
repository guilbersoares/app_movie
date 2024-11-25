package com.api.atividade4UC14.service;

import com.api.atividade4UC14.data.AnaliseEntity;
import com.api.atividade4UC14.data.AnaliseRepository;
import com.api.atividade4UC14.data.FilmeEntity;
import com.api.atividade4UC14.data.FilmeRepository;
import java.util.ArrayList;
import java.util.Objects;



import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.stereotype.Service; 


@Service
public class AnaliseService {
    
    @Autowired
    AnaliseRepository analiseRepository;
    @Autowired
    FilmeService filmeService;
    
    public AnaliseEntity criaAnalise(AnaliseEntity analise, Integer filmeId){
        if(!Objects.isNull(filmeService.getFilmeId(filmeId))){
            analise.setId(null);
            analise.setFilmeId(filmeId);
            analiseRepository.save(analise);
            return analise;
        } else{
            
            return null;
        }
    }
    
    public AnaliseEntity atualizarAnalise(Integer analiseId, AnaliseEntity analiseRequest){
        
        AnaliseEntity analise = getAnaliseId(analiseId);
        analise.setReview(analiseRequest.getReview());
        analise.setNota(analise.getNota());
        analiseRepository.save(analise);
        return analise;
        
    }
    
    public AnaliseEntity getAnaliseId(Integer id){
        
        return analiseRepository.findById(id).orElse(null);
    }
    
    public List<AnaliseEntity> listarTodasAnalise(){
        
        return analiseRepository.findAll();
    }
    
    
    public void deletarAnalise(Integer analiseId){
            
            AnaliseEntity analise = getAnaliseId(analiseId);
            analiseRepository.deleteById(analise.getId());
    
    } 
    
    public List<AnaliseEntity> listarAnaliseFilme(Integer filmeId){
        List<AnaliseEntity> listaAnalise = new ArrayList<>();
        List<AnaliseEntity> todasAnalises = this.listarTodasAnalise();
        for(AnaliseEntity analise : todasAnalises){
            if(analise.getFilmeId() == filmeId){
                listaAnalise.add(analise);
            }
        }
        
        return listaAnalise;
        
    }
    
}
