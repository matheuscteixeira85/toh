package br.ufs.esii.toh.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Funcionario extends Pessoa{

	private int matricula;
	private String tipo;
	
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
