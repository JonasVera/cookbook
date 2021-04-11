package com.dell.cookbook.cookbook.controller;

 
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dell.cookbook.cookbook.model.Receita;
import com.dell.cookbook.cookbook.model.Usuario;
import com.dell.cookbook.cookbook.repository.ReceitaRepository;
import com.dell.cookbook.cookbook.repository.UsuarioRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;

import javax.naming.Binding;
import javax.servlet.http.Part;
import javax.validation.Valid;

@SuppressWarnings("Since15")
@Controller
public class AdminController {
    public static String uploadDirectory = System.getProperty("user.dir")+"/uploadsImg";
    @Autowired
    ReceitaRepository receitaRepository;
    @Autowired
    UsuarioRepository userRepository;
    
    @Autowired
    private FileServer fileSaver;
    
    @GetMapping
    @RequestMapping("/home")
    public String home(Model model) {
    	 
    	model.addAttribute("receitas",receitaRepository.findAll());
        return "home";
    }
    
    @GetMapping
    @RequestMapping("/admin")
    public String admin(Model model) {
       
    	model.addAttribute("receitas",userLogado().getReceitas());
    	
        return "admin";
    }
    
    private Usuario userLogado () {
    	 
    	Object usuarioLogado =  SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
    	String username;
    	if (usuarioLogado instanceof UserDetails ){
    		username= ((UserDetails)usuarioLogado).getUsername();
    	 } else {
    		 username = usuarioLogado .toString(); 
    	 }
    	 Usuario usuarioLOG = new Usuario();
    	 for (Usuario usuario :userRepository.findAll()) {
			if(usuario.getEmail().equals(username)) {
				usuarioLOG = usuario;
			}
		} 
    	 
    	return usuarioLOG;
    }

    @PostMapping
    @RequestMapping("/registrarReceita")
    public String registrarReceita(MultipartFile file,@Valid  Receita receita, BindingResult result){
    	if(result.hasErrors()) {
    		return "admin";
    	} 
    	
    	String webPath =  fileSaver.write(file);
    	 receita.setUrlImagem(webPath);
    	 receita.setUsuario(userLogado());
        receitaRepository.save(receita);
        return "redirect:/admin";
    } 
    @RequestMapping("/pesquisarReceita")
    public String pesquisar(@RequestParam(value = "titulo") String titulo, Model model) {
    	 ArrayList<Receita> receitaPesquisa = new ArrayList<>();
    	 
    	for (Receita receita : receitaRepository.findAll()) {
			if(receita.getTitulo().contains(titulo)) {
				receitaPesquisa.add(receita);
			}
		} 
    	model.addAttribute("receitas",receitaPesquisa);
        return "admin";
    }
      
    @RequestMapping(value = "/deletar/{id}", method = RequestMethod.GET)
    public String deleteReceita(@PathVariable("id") int id) {
    	receitaRepository.deleteById(id);
    	return "redirect:/admin";
    }
     
    
}
