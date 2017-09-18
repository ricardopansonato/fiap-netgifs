package br.com.fiap.netgifs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

@Entity
@Table(name="CATEGORY")
public class Category {
	
	@Id
	@GeneratedValue
	@Column(name = "ID_CATEGORY", nullable = false)
	private Long id;
	
	@Column(name = "DS_CATEGORY", nullable = false)
	private String name;

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
