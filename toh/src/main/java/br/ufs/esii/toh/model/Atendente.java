package br.ufs.esii.toh.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Atendente extends Funcionario{

	@ManyToOne
	@JoinColumn(name="gestor_cpf")
	private Gestor gestor;
	
	public void validarRefeicao() {
		
	}
	
}
