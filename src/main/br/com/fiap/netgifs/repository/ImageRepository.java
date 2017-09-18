package br.com.fiap.netgifs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.netgifs.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	
	Image findByUrl(String url);
	Image findById(Long id);
	Image findByDescription(String description);
}