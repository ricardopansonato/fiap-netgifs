package br.com.fiap.netgifs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.netgifs.model.Category;
import br.com.fiap.netgifs.model.Image;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Image findByName(String name);
}