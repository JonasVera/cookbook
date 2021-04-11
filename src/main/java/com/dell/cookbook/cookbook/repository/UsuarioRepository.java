package com.dell.cookbook.cookbook.repository;

  
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.dell.cookbook.cookbook.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Usuario  findByEmail(String email);
}
