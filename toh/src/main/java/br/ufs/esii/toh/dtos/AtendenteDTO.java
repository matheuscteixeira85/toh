package br.ufs.esii.toh.dtos;

import javax.validation.constraints.NotBlank;

import br.ufs.esii.toh.model.Gestor;

public class AtendenteDTO extends FuncionarioDTO{

	@NotBlank
	private Gestor gestor;

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}
	
	
}
