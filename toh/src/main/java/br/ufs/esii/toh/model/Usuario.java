package br.ufs.esii.toh.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario extends Pessoa{

	@ManyToOne
	@JoinColumn(name="gestor_cpf")
	private Gestor gestor;
	
	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	public void visualizarRefeicao() {
		
	}
	
	public void consultarCardapio() {
	
	}
	
	public void visualizarHistorico() {
		
	}
	
	
}


	
	

