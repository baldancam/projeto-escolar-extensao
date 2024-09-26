package com.sistema.escolar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "tb_noticia")
public class Noticia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O título é obrigatório!")
	@Size(min = 5, max = 200, message = "O título deve ter entre 5 e 200 caracteres.")
	private String titulo;

	@NotBlank(message = "O conteúdo é obrigatório!")
	@Size(min = 10, max = 5000, message = "O conteúdo deve ter entre 10 e 5000 caracteres.")
	private String conteudo;

	@NotNull(message = "A data de publicação é obrigatória!")
	@PastOrPresent(message = "A data de publicação não pode ser no futuro.")
	private LocalDate dataPublicacao;

	// Relacionamento com Funcionario (autor da notícia)
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	@Size(max = 255, message = "O URL da imagem não pode exceder 255 caracteres.")
	private String imagemUrl; // URL da imagem

	// Construtor padrão
	public Noticia() {
	}

	// Construtor completo
	public Noticia(String titulo, String conteudo, LocalDate dataPublicacao, Funcionario funcionario,
			String imagemUrl) {
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.dataPublicacao = dataPublicacao;
		this.funcionario = funcionario;
		this.imagemUrl = imagemUrl;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}

	@Override
	public String toString() {
		return "Noticia{" + "id=" + id + ", titulo='" + titulo + '\'' + ", conteudo='" + conteudo + '\''
				+ ", dataPublicacao=" + dataPublicacao + ", funcionario=" + funcionario.getNome() + ", imagemUrl='"
				+ imagemUrl + '\'' + '}';
	}
}
