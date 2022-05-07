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
	
	
/*	
	@OneToOne(optional = true)
	@JoinColumn(name="funcionario_id")
	private Funcionario funcionario;
	*/
	
	/*
	
	@OneToMany(mappedBy = "gestor_atendente", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Atendente atendene;
	
	@OneToMany(mappedBy = "gestor_usuario", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Usuario usuario;
	
	@OneToMany(mappedBy = "gestor_cardapio", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Cardapio cardapio;
	
	@OneToMany(mappedBy = "gestor_refeicao", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Refeicao refeicao;
	*/
	
	
	@ManyToOne
	@JoinColumn(name = "id_administrador", referencedColumnName = "administrador_id", nullable = true)
	private Administrador administrador_gestor;



	public UUID getGestor_id() {
		return gestor_id;
	}



	public void setGestor_id(UUID gestor_id) {
		this.gestor_id = gestor_id;
	}


/*
	public Funcionario getFuncionario() {
		return funcionario;
	}



	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
*/

/*
	public Atendente getAtendene() {
		return atendene;
	}



	public void setAtendene(Atendente atendene) {
		this.atendene = atendene;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Cardapio getCardapio() {
		return cardapio;
	}



	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}



	public Refeicao getRefeicao() {
		return refeicao;
	}



	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}

*/

	public Administrador getAdministrador_gestor() {
		return administrador_gestor;
	}



	public void setAdministrador_gestor(Administrador administrador_gestor) {
		this.administrador_gestor = administrador_gestor;
	}
	

	
	
	
}
