package br.ufs.esii.toh.dtos;

import javax.validation.constraints.NotBlank;

import br.ufs.esii.toh.model.Atendente;
import br.ufs.esii.toh.model.Cardapio;
import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.model.Usuario;

public class RefeicaoDTO {


	@NotBlank
	private boolean consumida;
	@NotBlank
	private Cardapio cardapio;
	@NotBlank
	private Usuario usuario;
	@NotBlank
	private Atendente atendente;
	@NotBlank
	private Gestor gestor;

	public boolean isConsumida() {
		return consumida;
	}

	public void setConsumida(boolean consumida) {
		this.consumida = consumida;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	
}
