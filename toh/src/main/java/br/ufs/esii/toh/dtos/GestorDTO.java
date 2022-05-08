package br.ufs.esii.toh.dtos;

import javax.validation.constraints.NotBlank;

import br.ufs.esii.toh.model.Administrador;

public class GestorDTO extends FuncionarioDTO{

	@NotBlank
	private Administrador administrador;

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	
	
}
