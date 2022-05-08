package br.ufs.esii.toh.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.ufs.esii.toh.model.Gestor;

public class CardapioDTO {

	
	@NotBlank
	@Size(max=50)
	private String nome;
	@NotBlank
	@Size(max=150)
	private String descricao;
	@NotBlank
	@Size(max=5)
	private String turno;
	@NotBlank
	private Gestor gestor;
	
	
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
	public Gestor getGestor() {
		return gestor;
	}
	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	
}
