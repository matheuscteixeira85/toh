package br.ufs.esii.toh.model;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Atendente extends Funcionario implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID atendente_id;
	
	@ManyToOne
	@JoinColumn(name = "id_gestor", referencedColumnName = "gestor_id", nullable = true)
	private Gestor gestor_atendente;
	
	@ManyToMany
	@JoinTable(name="atendentes_roles", joinColumns = @JoinColumn(
			name="ate_login", referencedColumnName = "login"),
			inverseJoinColumns = @JoinColumn(
			name = "role_id", referencedColumnName = "nomeRole"))
	private List<Role> roles;


	public UUID getAtendente_id() {
		return atendente_id;
	}


	public void setAtendente_id(UUID atendente_id) {
		this.atendente_id = atendente_id;
	}
	public Gestor getGestor_atendente() {
		return gestor_atendente;
	}


	public void setGestor_atendente(Gestor gestor_atendente) {
		this.gestor_atendente = gestor_atendente;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return (Collection<? extends GrantedAuthority>) this.roles;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.getSenha();
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getCpf();
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
