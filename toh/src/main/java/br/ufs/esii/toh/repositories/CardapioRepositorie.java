package br.ufs.esii.toh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Cardapio;

@Repository
public interface CardapioRepositorie extends JpaRepository<Cardapio, Long>{

}
