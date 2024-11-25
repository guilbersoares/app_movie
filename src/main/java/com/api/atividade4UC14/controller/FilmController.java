package com.api.atividade4UC14.controller;


import com.api.atividade4UC14.data.AnaliseEntity;
import com.api.atividade4UC14.data.FilmeEntity;
import com.api.atividade4UC14.model.Preferencia;
import com.api.atividade4UC14.service.AnaliseService;
import com.api.atividade4UC14.service.FilmeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "*")
public class FilmController {

    @Autowired
    private FilmeService filmeService;
    
    @Autowired
    private AnaliseService analiseService;
    
    @GetMapping("/")
    public String listarFilmes(@CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema, Model model) {
        List<FilmeEntity> listarFilmes = filmeService.listarTodosFilmes();
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema); 
        model.addAttribute("listarFilmes", listarFilmes);
        return "index";
    }

    @GetMapping("/criarFilmeForm")
    public String criarFilmeForm(@CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema, Model model) {
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema); 
        model.addAttribute("filme", new FilmeEntity());
        return "inserir-filme";
    }

    @PostMapping("/adicionar-filme")
    public String adicionarFilme(@ModelAttribute FilmeEntity filme) {
        filmeService.criaFilme(filme);
        return "redirect:/";
    }

    @GetMapping("/atualizarFilmeForm/{id}")
    public String atualizarFilmeForm(@PathVariable("id") Integer id, @CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema, Model model) {
        FilmeEntity filme = filmeService.getFilmeId(id);
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema); 
        model.addAttribute("filme", filme);
        return "atualizar-filme";
    }

    @PostMapping("/atualizar-filme")
    public String atualizarFilme(@ModelAttribute FilmeEntity filme) {
        filmeService.atualizarFilme(filme.getId(), filme);
        return "redirect:/";
    }

    @GetMapping("/deletarFilme/{id}")
    public String deletarFilme(@PathVariable("id") Integer id) {
        filmeService.deletarFilme(id);
        return "redirect:/";
    }

    @GetMapping("/criarAnaliseForm/{filmeId}")
    public String criarAnaliseForm(@PathVariable("filmeId") Integer filmeId, @CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema, Model model) {
        AnaliseEntity analise = new AnaliseEntity();
        analise.setFilmeId(filmeId);
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema); 
        model.addAttribute("analise", analise);
        return "inserir-analise";
    }

    @PostMapping("/adicionar-analise")
    public String adicionarAnalise(@ModelAttribute AnaliseEntity analise) {
        
        return "redirect:/";
    }
    
    
    @GetMapping("/atualizarAnaliseForm/{id}")
    public String atualizarAnaliseForm(@PathVariable("id") Integer id, @CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema, Model model) {
        AnaliseEntity analise = analiseService.getAnaliseId(id);
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema); 
        model.addAttribute("analise", analise);
        return "atualizar-analise";
    }
    
     
    @GetMapping("/exibeAnalise/{filmeId}") 
    public String mostraAnalises(@PathVariable("filmeId") Integer filmeId, @CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema, Model model) { 
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema); 
        model.addAttribute("listarAnalises", analiseService.listarAnaliseFilme(filmeId)); 
        return "mostra-analise"; 
    } 
    
    @GetMapping("/deletarAnalise/{id}")
    public String deletarAnalise(@PathVariable("id") Integer id) {
        analiseService.deletarAnalise(id);
        return "redirect:/";
    }
    
    
    @PostMapping("/preferencias") 
    public ModelAndView gravaPreferencias(@ModelAttribute Preferencia pref, HttpServletResponse response){ 
        Cookie cookiePrefNome = new Cookie("pref-nome", pref.getNome()); 
        cookiePrefNome.setDomain("localhost");  
        cookiePrefNome.setHttpOnly(true);  
        cookiePrefNome.setMaxAge(86400);  
        response.addCookie(cookiePrefNome); 
        Cookie cookiePrefEstilo = new Cookie("pref-estilo", pref.getEstilo()); 
        cookiePrefEstilo.setDomain("localhost");  
        cookiePrefEstilo.setHttpOnly(true);  
        cookiePrefEstilo.setMaxAge(86400);  
        response.addCookie(cookiePrefEstilo); 
        return new ModelAndView("redirect:/"); 
    }
    
}