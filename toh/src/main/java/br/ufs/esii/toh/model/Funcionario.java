package br.ufs.esii.toh.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Funcionario extends Pessoa{
	

	@Column(nullable = false, unique = true)
	private long matricula;
	@Column(nullable = false)
	private String tipo;

	
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
}
