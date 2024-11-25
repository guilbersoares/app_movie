package com.api.atividade4UC14.exception;

import com.api.atividade4UC14.data.AnaliseEntity;
import com.api.atividade4UC14.data.AnaliseRepository;

import org.springframework.http.HttpStatus; 

import org.springframework.web.bind.annotation.ResponseStatus; 

@ResponseStatus(HttpStatus.NOT_FOUND)

public class ResourceNotFoundException extends RuntimeException {
    AnaliseRepository analiseRepository;
    
    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }
    
    public AnaliseEntity getAnaliseId(Integer anaId) { 
        return analiseRepository.findById(anaId).orElseThrow(() -> new ResourceNotFoundException("Análise não encontrada " + anaId));                
    }
    
    
}
