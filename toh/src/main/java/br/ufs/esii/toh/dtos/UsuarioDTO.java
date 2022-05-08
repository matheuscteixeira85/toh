package br.ufs.esii.toh.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.model.Pessoa;

public class UsuarioDTO extends Pessoa{

	@NotBlank
	@Size(max = 20)
	private String tipo_usuario;
	@NotBlank
	private Gestor gestor;

	public String getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}
	
	
}
