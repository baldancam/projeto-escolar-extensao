package com.sistema.escolar.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Professor {

	@NotBlank(message = "O nome é obrigatório!")
	@Size(min = 3, max = 100, message = "O nome não pode ter menos de 3 caracteres e mais de 100!")
    private String nome;
	
	@NotBlank(message = "O matricula é obrigatório!")
    private String matricula;
	
	@NotNull(message = "A data de nascimento é obrigatória!")
    private LocalDate dataNascimento;
	
	@NotBlank(message = "A turma é obrigatório!")
    private String turma;
	
	@NotBlank(message = "O telefone é obrigatório!")
    private String telefone;
	
	@Email(message = "E-mail inválido!")
    private String email;

    // Construtor
    public Professor(String nome, String matricula, LocalDate dataNascimento, String turma, String telefone, String email) {
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.turma = turma;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString
    @Override
    public String toString() {
        return "Professor{" +
                "nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", turma='" + turma + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
