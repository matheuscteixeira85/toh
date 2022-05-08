package br.ufs.esii.toh.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PessoaDTO {

	@NotBlank
	@Size(max=11)
	private String cpf;
	
	@NotBlank
	@Size(max=50)
	private String nome;
	@NotBlank
	@Size(max=50, min=6)
	private String senha;
	
	@Size(max=100, min = 0)
	private String endereco;
	@Size(max=11, min = 0)
	private String telefone;
	@Size(max=130, min = 0)
	private String data_nascimento;
	@Size(max=20, min = 0)
	private String genero;
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	
}
