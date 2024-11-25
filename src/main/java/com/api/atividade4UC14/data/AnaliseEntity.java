package com.api.atividade4UC14.data;

import jakarta.persistence.Entity; 

import jakarta.persistence.GeneratedValue; 

import jakarta.persistence.GenerationType; 

import jakarta.persistence.Id; 

import jakarta.persistence.Table; 

import jakarta.validation.constraints.Email; 

import jakarta.validation.constraints.NotBlank; 

import jakarta.validation.constraints.NotNull; 

import jakarta.validation.constraints.Size; 

import lombok.Data; 

import org.hibernate.validator.constraints.br.CPF;

@Data
@Entity
@Table(name="Analise") 
public class AnaliseEntity {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Integer id;
    private Integer filmeId;
    private String review;
    private int nota;
    
}
