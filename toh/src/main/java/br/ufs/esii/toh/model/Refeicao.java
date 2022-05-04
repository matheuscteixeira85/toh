package br.ufs.esii.toh.model;

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
	private int id_refeicao;
	
	private boolean consumida = false;
	
	@ManyToOne
	@JoinColumn(name="id_cardapio")
	private Cardapio cardapio;
	@ManyToOne
	@JoinColumn(name="cpf")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name="cpf")
	private Gestor gestor;
	@ManyToOne
	@JoinColumn(name="cpf")
	private Atendente atendente;
	
}
