package br.com.fiap.netgifs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

@Entity
@Table(name="USUARIO")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "DS_NOME", nullable = false)
	private String name;

	@Column(name = "DS_GENDER", nullable = false)
	private String gender;
	
	@Column(name = "DS_USUARIO", nullable = false)
	private String username;
	
	@Column(name = "DS_SENHA", nullable = false)
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Transient
	private String message;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean confirmPassword() {
		return !StringUtils.isEmpty(password) && password.equals(confirmPassword);
	}
	
	public boolean isError() {
		return !StringUtils.isEmpty(message);
	}
}
