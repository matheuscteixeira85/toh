package br.ufs.esii.toh.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Funcionario extends Pessoa{
	
	@Column(nullable = false, unique = true)
	private long matricula;
	@Column(nullable = false)
	private String tipo;
/*	
	@OneToOne
	@JoinColumn(name = "pessoa_cpf")
	private Pessoa pessoa;
	
	@OneToOne
	@JoinColumn(name = "atendente_id")
	private Atendente atendente;
	
	@OneToOne
	@JoinColumn(name = "gestor_id")
	private Gestor gestor;
	
	@OneToOne
	@JoinColumn(name = "administrador_id")
	private Administrador administrador;
*/
	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
/*
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	
*/
}
