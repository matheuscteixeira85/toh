package br.ufs.esii.toh.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
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
public class Usuario extends Pessoa implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long usuario_id;
	@Column(nullable = false, length = 20)
	private String tipo_usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_gestor", referencedColumnName = "gestor_id", nullable = true)
	private Gestor gestor_usuario;
	
	@ManyToMany
	@JoinTable(name="usuarios_roles", joinColumns = @JoinColumn(
			name="usu_login", referencedColumnName = "login"),
			inverseJoinColumns = @JoinColumn(
			name = "role_id", referencedColumnName = "nomeRole"))
	private List<Role> roles;


	public long getUsuario_id() {
		return usuario_id;
	}


	public void setUsuario_id(long usuario_id) {
		this.usuario_id = usuario_id;
	}


	public String getTipo_usuario() {
		return tipo_usuario;
	}


	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	public Gestor getGestor_usuario() {
		return gestor_usuario;
	}


	public void setGestor_usuario(Gestor gestor_usuario) {
		this.gestor_usuario = gestor_usuario;
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


	
	

