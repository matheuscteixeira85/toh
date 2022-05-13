package br.ufs.esii.toh.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Refeicao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_refeicao;
	
	
	@Column(nullable = false)
	private boolean consumida;
	@Column(nullable = false, length = 130)
	private LocalDateTime data_cadastro;
	@Column(nullable = false, length = 130)
	private LocalDateTime data_alteracao;
	

	@ManyToOne(optional = true)
	@JoinColumn(name = "id_usuario_refeicao", referencedColumnName = "usuario_id", nullable = true)
	private Usuario usuario_refeicao;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "id_gestor_refeicao", referencedColumnName = "gestor_id", nullable = true)
	private Gestor gestor_refeicao;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "id_cardapio_refeicao", referencedColumnName = "id_cardapio", nullable = true)
	private Cardapio cardapio_refeicao;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "id_atendente_refeicao", referencedColumnName = "atendente_id", nullable = true)
	private Atendente atendente_refeicao;

	public int getId_refeicao() {
		return id_refeicao;
	}

	public void setId_refeicao(int id_refeicao) {
		this.id_refeicao = id_refeicao;
	}

	public boolean isConsumida() {
		return consumida;
	}

	public void setConsumida(boolean consumida) {
		this.consumida = consumida;
	}
	
	public LocalDateTime getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(LocalDateTime data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public LocalDateTime getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(LocalDateTime data_alteracao) {
		this.data_alteracao = data_alteracao;
	}

	public Usuario getUsuario_refeicao() {
		return usuario_refeicao;
	}

	public void setUsuario_refeicao(Usuario usuario_refeicao) {
		this.usuario_refeicao = usuario_refeicao;
	}

	public Gestor getGestor_refeicao() {
		return gestor_refeicao;
	}

	public void setGestor_refeicao(Gestor gestor_refeicao) {
		this.gestor_refeicao = gestor_refeicao;
	}

	public Cardapio getCardapio_refeicao() {
		return cardapio_refeicao;
	}

	public void setCardapio_refeicao(Cardapio cardapio_refeicao) {
		this.cardapio_refeicao = cardapio_refeicao;
	}

	public Atendente getAtendente_refeicao() {
		return atendente_refeicao;
	}

	public void setAtendente_refeicao(Atendente atendente_refeicao) {
		this.atendente_refeicao = atendente_refeicao;
	}
	
	
	
}
