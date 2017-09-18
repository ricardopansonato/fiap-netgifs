package br.com.fiap.netgifs.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

@Entity
@Table(name="IMAGE")
public class Image {

	@Id
	@GeneratedValue
	@Column(name = "ID_IMAGE", nullable = false)
	private Long id;
	
	@Column(name = "DS_URL", nullable = false)
	private String url;
	
	@Column(name = "DS_DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "DS_GENRE", nullable = false)
	private String genre;

	@Column(name = "DS_LANGUAGE", nullable = false)
	private String language;

    @ManyToMany(mappedBy="favorites")
    private List<User> users;
	
	@Transient
	private String message;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isError() {
		return !StringUtils.isEmpty(message);
	}
	
}
