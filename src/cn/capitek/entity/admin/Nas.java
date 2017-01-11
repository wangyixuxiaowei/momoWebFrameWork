package cn.capitek.entity.admin;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the nas database table.
 * 
 */
@Entity
@NamedQuery(name="Nas.findAll", query="SELECT n FROM Nas n")
public class Nas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(length = 128,nullable=false,unique=true)
	private String nasname;
	
	@Column(length = 32)
	private String shortname;
	
	@Column(length = 30)
	private String type;
	
	@Column(precision = 5)
	private Integer ports;
	
	@Column(length = 60,nullable=false)
	private String secret;
	
	@Column(length = 64)
	private String server;
	
	@Column(length = 50)
	private String community;
	
	@Column(length = 200)
	private String description;

	public Nas() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNasname() {
		return nasname;
	}

	public void setNasname(String nasname) {
		this.nasname = nasname;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPorts() {
		return ports;
	}

	public void setPorts(Integer ports) {
		this.ports = ports;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}