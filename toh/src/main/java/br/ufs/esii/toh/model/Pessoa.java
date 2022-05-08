package br.ufs.esii.toh.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Pessoa {

	@Column(nullable = false, unique = true)
	private String cpf;
	
	
	@Column(nullable = false, length = 50)
	private String nome;
	@Column(nullable = false, length = 130)
	private LocalDateTime data_cadastro;
	@Column(nullable = false, length = 130)
	private LocalDateTime data_alteracao;
	@Column(nullable = false, length = 50)
	private String senha;
	
	@Column(length = 100)
	private String endereco;
	@Column(length = 11)
	private String telefone;
	@Column(length = 130)
	private String data_nascimento;
	@Column(length = 20)
	private String genero;
	
	
/*	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@OneToOne
	@JoinColumn(name = "matricula")
	private Funcionario funcionario;
*/
	
	
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

	public LocalDateTime getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(LocalDateTime data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public LocalDateTime getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(LocalDateTime data_alteracao) {
		this.data_alteracao = data_alteracao;
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
/*
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
		
	*/
	
}
