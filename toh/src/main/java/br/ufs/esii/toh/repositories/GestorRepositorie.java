package br.ufs.esii.toh.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Gestor;

@Repository
public interface GestorRepositorie extends JpaRepository<Gestor, UUID>{
	boolean existsByCpf(String cpf);
	Optional<Gestor> findByCpf(String cpf);
}
