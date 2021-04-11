package com.dell.cookbook.cookbook.model;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Entity
public class Receita implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    
    private String urlImagem;
    @Transient
    private String idUser;
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getIdUser() {
		return idUser;
	}

    public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
 
}
