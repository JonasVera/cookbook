package com.dell.cookbook.cookbook.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

@Entity
public class Usuario implements Serializable, UserDetails{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nomeCompleto;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
   
    private Date dataNascimento;
    @NotBlank
    private String sexo;
    
    @OneToMany(mappedBy = "usuario")
    private List<Receita> receitas = new ArrayList<Receita>();

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public void DTOToUsuario(DTOUsuario usuario){
        setNomeCompleto(usuario.getNomeCompleto());
        setSenha(usuario.getSenha());
        setEmail(usuario.getEmail());
        setSexo(usuario.getSexo());
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
       
        try {
            Date dataFormatada = formato.parse(usuario.getDataNascimento());
            setDataNascimento(dataFormatada);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		 
		return this.senha;
	}

	@Override
	public String getUsername() {
		 
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
