package com.dell.cookbook.cookbook.controller;
 
import java.security.Principal;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dell.cookbook.cookbook.model.DTOUsuario;
import com.dell.cookbook.cookbook.model.Usuario;
import com.dell.cookbook.cookbook.repository.UsuarioRepository;

@Controller
public class IndexController {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    private int IdUSERLOGADO = 0;
    @GetMapping
    @RequestMapping("/login")
    public String login(Principal principal) {
    	
    	
        return "login";
    }
     
    @PostMapping
    @RequestMapping("/registrarUsuario")
    public String registrarUsuario( Model model,@Valid DTOUsuario user, BindingResult result){
        
    	if(result.hasErrors()) {
    		return "login";
    	}
    	
    	Usuario usuario = new Usuario();
        usuario.DTOToUsuario(user);
        
        String msg = "";
        String msgSucesso = "";
        if(user.getEmail().equals(user.getConfirmarEmail())) {
        	
        	Usuario userDB = usuarioRepository.findByEmail(user.getEmail());
         
        	  if(userDB != null && userDB.getEmail().equals(user.getEmail())) {
        		msg = "Já existe um usuario com este e-mail !!";
            	model.addAttribute("feedBackEmail",msg);
            	return "login";
        	 }else {
        		 String senhaByCrp = new BCryptPasswordEncoder().encode(usuario.getSenha());
        		 usuario.setSenha(senhaByCrp);
        		 usuarioRepository.save(usuario);
        		 msgSucesso = "Usuario cadastrado com sucesso !";
        		 model.addAttribute("feedBackCadastro",msgSucesso);
        	}
			  
         }else {
        	msg = "E-mail não corresponde !";
        	model.addAttribute("feedBackEmail",msg);
         }
         
        return "login";
    }
}
