package br.ufs.esii.toh.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FuncionarioDTO extends PessoaDTO{
	
	
	@NotBlank
	@Size(max=20)
	private String tipo;
	@NotBlank
	private long matricula;
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public long getMatricula() {
		return matricula;
	}
	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}
	
	
}
