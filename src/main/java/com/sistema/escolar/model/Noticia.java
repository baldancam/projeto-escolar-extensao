package com.sistema.escolar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.sistema.escolar.dto.NoticiasRequestDTO;

@Entity(name = "noticias")
@Table(name = "tb_noticias")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
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

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	@Size(max = 255, message = "O URL da imagem não pode exceder 255 caracteres.")
	private String imagemUrl; // URL da imagem

	// Construtor que usa o DTO
	public Noticia(NoticiasRequestDTO data, Funcionario funcionario) {
		this.titulo = data.titulo();
		this.conteudo = data.conteudo();
		this.dataPublicacao = data.dataPublicacao();
		this.funcionario = funcionario; // Associar o funcionário
		this.imagemUrl = data.imagemUrl();
	}

}
