package cn.capitek.entity.admin;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the raduser database table.
 * 
 */
@Entity
@NamedQuery(name="RadUser.findAll", query="SELECT r FROM RadUser r")
public class RadUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String username;

	public RadUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}