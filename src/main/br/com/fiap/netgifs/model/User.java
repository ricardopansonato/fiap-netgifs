package br.com.fiap.netgifs.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "ID_USER", nullable = false)
	private Long id;

	@Column(name = "DS_NAME", nullable = false)
	private String name;

	@Column(name = "DS_GENDER", nullable = true)
	private String gender;

	@Column(name = "DS_USERNAME", nullable = false)
	private String username;

	@Column(name = "DS_PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "DS_ADMIN", nullable = true)
	private String admin;
	
	@ManyToMany
	@JoinTable(name = "USER_HAS_FAVORITES", 
		joinColumns = { @JoinColumn(name = "ID_USER") }, 
		inverseJoinColumns = {@JoinColumn(name = "ID_IMAGE") })
	private List<Image> favorites;
	
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
	
	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public List<Image> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Image> favorites) {
		this.favorites = favorites;
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
