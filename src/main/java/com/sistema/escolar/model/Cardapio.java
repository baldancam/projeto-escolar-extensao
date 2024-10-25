package com.sistema.escolar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "tb_cardapio")
public class Cardapio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome do item é obrigatório!")
	private String nomeItem;

	@NotNull(message = "A data do cardápio é obrigatória!")
	private LocalDate data;

	@NotBlank(message = "A descrição do item é obrigatória!")
	private String descricaoItem;

	// Construtor completo
	public Cardapio(String nomeItem, LocalDate data, String descricaoItem) {
		this.nomeItem = nomeItem;
		this.data = data;
		this.descricaoItem = descricaoItem;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getDescricaoItem() {
		return descricaoItem;
	}

	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}

}
