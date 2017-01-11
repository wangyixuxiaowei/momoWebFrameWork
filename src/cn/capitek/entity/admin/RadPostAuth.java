package cn.capitek.entity.admin;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


/**
 * The persistent class for the radpostauth database table.
 * 
 */
@Entity
@NamedQuery(name="RadPostAuth.findAll", query="SELECT r FROM RadPostAuth r")
public class RadPostAuth implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private Timestamp authdate;

	private String pass;

	private String reply;

	private String username;

	@Transient
	private String authdatestr;
	
	public String getAuthdatestr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(this.authdate);
	}

	public void setAuthdatestr(String authdatestr) {
		this.authdatestr = authdatestr;
	}

	public RadPostAuth() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getAuthdate() {
		return this.authdate;
	}

	public void setAuthdate(Timestamp authdate) {
		this.authdate = authdate;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getReply() {
		return this.reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}