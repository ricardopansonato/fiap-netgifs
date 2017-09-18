package br.com.fiap.netgifs.vo;

import br.com.fiap.netgifs.model.User;

public class UserData {
	private Long id;
	private String name;
	private boolean admin;
	
	public UserData(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.admin = "YES".equals(user.getAdmin());
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public boolean isAdmin() {
		return admin;
	}
}
