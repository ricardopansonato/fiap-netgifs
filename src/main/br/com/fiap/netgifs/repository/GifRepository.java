package br.com.fiap.netgifs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.netgifs.model.Gif;

public interface GifRepository extends JpaRepository<Gif, Long> {
	
	Gif findByUrlGif(String urlGif);
	Gif findById(Long id);
	Gif findByDescricao(String descricao);
	
	//void addGif(Gif gif);
}