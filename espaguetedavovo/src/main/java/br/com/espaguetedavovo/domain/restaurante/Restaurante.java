package br.com.espaguetedavovo.domain.restaurante;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.espaguetedavovo.domain.usuario.Usuario;
import br.com.espaguetedavovo.infrastructure.web.validator.UploadConstraint;
import br.com.espaguetedavovo.util.FileType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Restaurante extends Usuario {

	@NotBlank(message = "O CNPJ não pode ser vazio")
	@Pattern(regexp = "[0-9]{14}", message = "O CNPJ possui formato inválido")
	@Column(length = 14)
	private String cnpj;
	
	@Size(max = 80)
	private String logotipo;
	
	@UploadConstraint(acceTypes = {FileType.JPG, FileType.PNG}, message = "O arquivo não é um arquivo de imagem válido")
	private transient MultipartFile logotipoFile;
	
	@NotNull(message = "A Taxa de entrega não pode ser vazia")
	@Min(0)
	@Max(20)
	private BigDecimal taxaEntrega;
	
	@NotNull(message = "O tempo de entrega não pode ser vazio")
	@Min(0)
	@Max(120)
	private Integer tempoEntrega;
	
	@ManyToMany
	@JoinTable(
			name = "restaurante_has_categoria",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "categoria_restaurante_id")
			)
	@Size(min = 1, message = "O Restaurante precisa ter pelo menos uma categoria")
	private Set<CategoriaRestaurante> categorias = new HashSet<>(0);
	
	
	public void setLogotipoFileName() {
		if (getId() == null) {
			throw new IllegalStateException("É preciso primeiro gravar o registro");
		}
		
		this.logotipo = String.format("%04d-logo,%s", getId(), FileType.of(logotipoFile.getContentType()).getExtension());
	}

	
	
	public Set<CategoriaRestaurante> getCategorias() {
		return categorias;
	}



	public void setCategorias(Set<CategoriaRestaurante> categorias) {
		this.categorias = categorias;
	}



	public MultipartFile getLogotipoFile() {
		return logotipoFile;
	}

	public void setLogotipoFile(MultipartFile logotipoFile) {
		this.logotipoFile = logotipoFile;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	public BigDecimal getTaxaEntrega() {
		return taxaEntrega;
	}

	public void setTaxaEntrega(BigDecimal taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}

	public Integer getTempoEntrega() {
		return tempoEntrega;
	}

	public void setTempoEntrega(Integer tempoEntrega) {
		this.tempoEntrega = tempoEntrega;
	}
	
	
}
