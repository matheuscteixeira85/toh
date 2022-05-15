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
public class Gestor extends Funcionario implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID gestor_id;
	
	
	@ManyToOne
	@JoinColumn(name = "id_administrador", referencedColumnName = "administrador_id", nullable = true)
	private Administrador administrador_gestor;
	
	@ManyToMany
	@JoinTable(name="gestor_roles", joinColumns = @JoinColumn(
			name="ges_login", referencedColumnName = "login"),
			inverseJoinColumns = @JoinColumn(
			name = "role_id", referencedColumnName = "nomeRole"))
	private List<Role> roles;



	public UUID getGestor_id() {
		return gestor_id;
	}



	public void setGestor_id(UUID gestor_id) {
		this.gestor_id = gestor_id;
	}


	public Administrador getAdministrador_gestor() {
		return administrador_gestor;
	}



	public void setAdministrador_gestor(Administrador administrador_gestor) {
		this.administrador_gestor = administrador_gestor;
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
