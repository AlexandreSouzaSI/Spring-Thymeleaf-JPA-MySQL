package br.com.espaguetedavovo.domain.restaurante;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categoria_restaurante")
public class CategoriaRestaurante implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@NotNull
	@Size(max = 20)
	private String nome;
	
	@NotNull
	@Size(max = 50)
	private String imagem;
	
	@ManyToMany(mappedBy = "categorias")
	private Set<Restaurante> restaurantes = new HashSet<>(0);
	
	

	public Set<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(Set<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	
}
