package br.com.fiap.netgifs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.netgifs.model.Favorito;
import br.com.fiap.netgifs.model.Gif;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
	
	Gif findById(Long id);
	
}

