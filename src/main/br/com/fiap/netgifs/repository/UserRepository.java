package br.com.fiap.netgifs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.netgifs.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
}