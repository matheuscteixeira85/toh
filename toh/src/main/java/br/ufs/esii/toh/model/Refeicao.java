package br.ufs.esii.toh.model;

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
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id_refeicao;

	@Column(nullable = false)
	private boolean consumida = false;
	
	@ManyToOne
	@JoinColumn(name="cardapio_id")
	private Cardapio cardapio;
	@ManyToOne
	@JoinColumn(name="usuario_cpf")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name="gestor_cpf")
	private Gestor gestor;
	@ManyToOne
	@JoinColumn(name="atendente_cpf")
	private Atendente atendente;
	
}
