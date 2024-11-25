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
@Table(name="Filme") 
public class FilmeEntity {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String titulo;
    private String sinopse;
    private String genero;
    private int ano;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    
    
    
    
    
}