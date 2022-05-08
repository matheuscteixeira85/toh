package br.ufs.esii.toh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario extends Pessoa{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long usuario_id;
	@Column(nullable = false, length = 20)
	private String tipo_usuario;

/*	
	@OneToOne
	@JoinColumn(name = "pessoa_cpf")
	private Pessoa pessoa;
	
	
	@OneToMany(mappedBy = "usuario_refeicao", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
	private Refeicao refeicao;
*/	
	
	@ManyToOne
	@JoinColumn(name = "id_gestor", referencedColumnName = "gestor_id", nullable = true)
	private Gestor gestor_usuario;


	public long getUsuario_id() {
		return usuario_id;
	}


	public void setUsuario_id(long usuario_id) {
		this.usuario_id = usuario_id;
	}


	public String getTipo_usuario() {
		return tipo_usuario;
	}


	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

/*
	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public Refeicao getRefeicao() {
		return refeicao;
	}


	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}
*/

	public Gestor getGestor_usuario() {
		return gestor_usuario;
	}


	public void setGestor_usuario(Gestor gestor_usuario) {
		this.gestor_usuario = gestor_usuario;
	}
	
	
	
/*	public void visualizarRefeicao() {
		
	}
	
	public void consultarCardapio() {
	
	}
	
	public void visualizarHistorico() {
		
	}
	*/
	
}


	
	

