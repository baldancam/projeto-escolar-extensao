package com.sistema.escolar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "tb_evento_calendario")
public class EventoCalendario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome do evento é obrigatório!")
	private String nomeEvento;

	@NotNull(message = "A data do evento é obrigatória!")
	@FutureOrPresent(message = "A data do evento deve ser no presente ou no futuro!")
	private LocalDate dataEvento;

	@NotBlank(message = "A descrição do evento é obrigatória!")
	private String descricao;

	// Construtor padrão
	public EventoCalendario() {
	}

	// Construtor completo
	public EventoCalendario(String nomeEvento, LocalDate dataEvento, String descricao) {
		this.nomeEvento = nomeEvento;
		this.dataEvento = dataEvento;
		this.descricao = descricao;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public LocalDate getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "EventoCalendario{" + "id=" + id + ", nomeEvento='" + nomeEvento + '\'' + ", dataEvento=" + dataEvento
				+ ", descricao='" + descricao + '\'' + '}';
	}
}
