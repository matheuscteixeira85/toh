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
public class Cardapio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_cardapio;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private String turno;
	@Column(nullable = false)
	private LocalDateTime data_refeicao;
	@Column(nullable = false)
	private LocalDateTime data_cadastro;
	@Column(nullable = false)
	private LocalDateTime data_alteracao;
/*
	@OneToMany(mappedBy = "cardapio_refeicao", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
	private Refeicao refeicao;
	*/
	@ManyToOne(optional = true)
	@JoinColumn(name = "id_gestor_cardapio", referencedColumnName = "gestor_id", nullable = true)
	private Gestor gestor_cardapio;

	public int getId_cardapio() {
		return id_cardapio;
	}

	public void setId_cardapio(int id_cardapio) {
		this.id_cardapio = id_cardapio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public LocalDateTime getData_refeicao() {
		return data_refeicao;
	}

	public void setData_refeicao(LocalDateTime data_refeicao) {
		this.data_refeicao = data_refeicao;
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
/*
	public Refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}
*/
	public Gestor getGestor_cardapio() {
		return gestor_cardapio;
	}

	public void setGestor_cardapio(Gestor gestor_cardapio) {
		this.gestor_cardapio = gestor_cardapio;
	}
	
	
}
