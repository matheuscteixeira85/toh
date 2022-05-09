package br.ufs.esii.toh.dtos;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import br.ufs.esii.toh.model.Administrador;

public class GestorDTO extends FuncionarioDTO{

	
	
	@NotBlank
	private Administrador administrador;

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = new Administrador();
		this.administrador.setAdministrador_id(UUID.fromString(administrador));
	}
	
	
}
