package br.ufs.esii.toh.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Gestor extends Funcionario{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID gestor_id;
	
	
	@ManyToOne
	@JoinColumn(name = "id_administrador", referencedColumnName = "administrador_id", nullable = true)
	private Administrador administrador_gestor;



	public UUID getGestor_id() {
		return gestor_id;
	}



	public void setGestor_id(UUID gestor_id) {
		this.gestor_id = gestor_id;
	}


	public Administrador getAdministrador_gestor() {
		return administrador_gestor;
	}



	public void setAdministrador_gestor(Administrador administrador_gestor) {
		this.administrador_gestor = administrador_gestor;
	}
	

	
	
	
}
