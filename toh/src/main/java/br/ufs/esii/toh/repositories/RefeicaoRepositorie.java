package br.ufs.esii.toh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Refeicao;

@Repository
public interface RefeicaoRepositorie extends JpaRepository<Refeicao, Long>{

}
