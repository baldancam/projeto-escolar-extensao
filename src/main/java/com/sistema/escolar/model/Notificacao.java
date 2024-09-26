package com.sistema.escolar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_notificacao")
public class Notificacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O conteúdo da mensagem é obrigatório!")
	private String conteudo;

	@NotNull(message = "O remetente é obrigatório!")
	@ManyToOne
	@JoinColumn(name = "remetente_id")
	private Funcionario remetente;

	@NotNull(message = "O destinatário é obrigatório!")
	@ManyToOne
	@JoinColumn(name = "destinatario_id")
	private Funcionario destinatario;

	// Construtor padrão
	public Notificacao() {
	}

	// Construtor completo
	public Notificacao(String conteudo, Funcionario remetente, Funcionario destinatario) {
		this.conteudo = conteudo;
		this.remetente = remetente;
		this.destinatario = destinatario;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Funcionario getRemetente() {
		return remetente;
	}

	public void setRemetente(Funcionario remetente) {
		this.remetente = remetente;
	}

	public Funcionario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Funcionario destinatario) {
		this.destinatario = destinatario;
	}

	@Override
	public String toString() {
		return "Notificacao{" + "id=" + id + ", conteudo='" + conteudo + '\'' + ", remetente=" + remetente.getNome()
				+ ", destinatario=" + destinatario.getNome() + '}';
	}
}
