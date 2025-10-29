package com.generation.blogpessoal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_temas") //CREATE TABLE tb_temas(
public class Tema {

	
	@Id // primary key (id)
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private Long id;
	
	@Column(length = 100)
	@NotBlank(message = "O atributo descrição é obrigatório!")
	@Size(min = 10, max = 100, message = "O atributo Descricao deve conter no mínimo 10 e no máximo 100 caracteres") 
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tema", cascade = CascadeType.REMOVE)	// FetchType.LAZY melhora performance. mappedBy indica chave extrangeira. 
	//CascadeType.REMOVE toda vez que eu apagar um tema todas as postagens associadas a esse tema tbm serão excluídas
	@JsonIgnoreProperties(value = "tema", allowSetters = true)  //anotação utilizada para evitar a recursividade na consulta dos dados de uma entidade. Ignora só o set, deixa o get
	private List<Postagem> postagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
	
}
