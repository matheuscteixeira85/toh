package br.ufs.esii.toh.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administrador  extends Funcionario{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID administrador_id;
	
	/*
	@OneToOne(optional = true)
	@JoinColumn(name = "administrador_id")
	private Administrador administrador;
	
	
	@OneToMany(mappedBy = "administrador_gestor", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Gestor gestor;
*/
	public UUID getAdministrador_id() {
		return administrador_id;
	}

	public void setAdministrador_id(UUID administrador_id) {
		this.administrador_id = administrador_id;
	}
/*
	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}
	
	*/
	
	
}
