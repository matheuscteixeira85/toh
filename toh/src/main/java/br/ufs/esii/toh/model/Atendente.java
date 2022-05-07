package br.ufs.esii.toh.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Atendente extends Funcionario{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID atendente_id;
	
/*	
	@OneToOne
	@JoinColumn(name = "funcionario_matricula")
	private Funcionario funcionario;

	
	@OneToMany(mappedBy = "atendente_refeicao", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
	private Refeicao refeicao;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   	
	*/
	
	@ManyToOne
	@JoinColumn(name = "id_gestor", referencedColumnName = "gestor_id", nullable = true)
	private Gestor gestor_atendente;


	public UUID getAtendente_id() {
		return atendente_id;
	}


	public void setAtendente_id(UUID atendente_id) {
		this.atendente_id = atendente_id;
	}

/*
	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public Refeicao getRefeicao() {
		return refeicao;
	}


	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}

*/
	public Gestor getGestor_atendente() {
		return gestor_atendente;
	}


	public void setGestor_atendente(Gestor gestor_atendente) {
		this.gestor_atendente = gestor_atendente;
	}
	
	
	
}
