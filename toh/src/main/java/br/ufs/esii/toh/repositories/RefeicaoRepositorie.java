package br.ufs.esii.toh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Refeicao;
import br.ufs.esii.toh.model.Usuario;

@Repository
public interface RefeicaoRepositorie extends JpaRepository<Refeicao, Integer>{
	List<Refeicao> findByUsuario(Usuario usuario);
}
