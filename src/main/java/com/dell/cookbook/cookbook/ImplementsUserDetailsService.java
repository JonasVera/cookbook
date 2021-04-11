package com.dell.cookbook.cookbook;

 
import javax.transaction.Transactional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.dell.cookbook.cookbook.model.Usuario;
import com.dell.cookbook.cookbook.repository.UsuarioRepository;
 

@Repository
public class ImplementsUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuarioLog = new Usuario();
				
		for (Usuario usua : ur.findAll()) {
			if(usua.getEmail().equals(login)) {
				usuarioLog = usua;
			}
		}
				
		 System.out.println("TESTE DE LOGIN !! "+usuarioLog.getEmail());
		
		 if(usuarioLog == null)
			throw new UsernameNotFoundException("Usuario não encontrado!");
		  
		return  usuarioLog;
	}

	
}