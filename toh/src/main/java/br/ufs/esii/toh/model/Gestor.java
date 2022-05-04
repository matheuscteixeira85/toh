package br.ufs.esii.toh.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Gestor extends Funcionario{


	@ManyToOne
	@JoinColumn(name="administrador_cpf")
	private Administrador administrador;
	
	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public void cadastrarUsuario() {
	}
	
	public void consultarUsuario() {
		
	}
	
	public void atualizarUsuario() {
		
	}
	
	public void deletarUsuario() {
		
	}

	public void cadastrarAtendente() {
		
	}
	
	public void consultarAtendente() {
		
	}
	
	public void atualizarAtendente() {
		
	}
	
	public void deletarAtendente() {
		
	}

	public void cadastrarCardapio() {
		
	}
	
	public void consultarCardapio() {
		
	}
	
	public void atualizarCardapio() {
		
	}
	
	public void deletarCardapio() {
		
	}

	public void cadastrarRefeicao() {
		
	}
	
	public void consultarRefeicao() {
		
	}
	
	public void atualizarRefeicao() {
		
	}
	
	public void deletarRefeicao() {
		
	}
	
}
