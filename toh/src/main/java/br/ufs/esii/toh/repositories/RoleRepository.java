package br.ufs.esii.toh.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufs.esii.toh.model.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

	List<Role> findByNomeRole(String nomeRole);

}
