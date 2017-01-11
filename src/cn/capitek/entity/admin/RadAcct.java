package cn.capitek.entity.admin;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;

@Entity
@NamedQuery(name="RadAcct.findAll", query="SELECT r FROM RadAcct r")
public class RadAcct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String radacctid;

	@Length(max=32)
	private String acctauthentic;
	
	@Length(max=20)
	private Long acctinputoctets;
	
	@Length(max=12)
	private Integer acctinterval;
	
	@Length(max=20)
	private Long acctoutputoctets;

	@NotNull
	@Length(max=64)
	private String acctsessionid;
	
	@Length(max=12)
	private Long acctsessiontime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date acctstarttime;

	@Transient
	private String acctstarttimestr;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date acctstoptime;

	@Transient
	private String acctstoptimestr;
	
	@NotNull
	@Length(max=32)
	private String acctterminatecause;
	
	@NotNull
	@Length(max=32)
	private String acctuniqueid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date acctupdatetime;
	
	@Transient
	private String acctupdatetimestr;

	@NotNull
	@Length(max=50)
	private String calledstationid;

	@NotNull
	@Length(max=50)
	private String callingstationid;

	@Column(name="connectinfo_start")
	@Length(max=50)
	private String connectinfoStart;

	@Column(name="connectinfo_stop")
	@Length(max=50)
	private String connectinfoStop;

	@NotNull
	@Length(max=15)
	private String framedipaddress;

	@Length(max=32)
	private String framedprotocol;

	@NotNull
	@Length(max=64)
	private String groupname;

	@NotNull
	@Length(max=15)
	private String nasipaddress;
	
	@Length(max=15)
	private String nasportid;

	@Length(max=32)
	private String nasporttype;

	@Length(max=64)
	private String realm;

	@Length(max=32)
	private String servicetype;
	
	@NotNull
	@Length(max=64)
	private String username;

	public RadAcct() {
	}

	public String getAcctstarttimestr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(this.acctstarttime);
	}

	public void setAcctstarttimestr(String acctstarttimestr) {
		this.acctstarttimestr = acctstarttimestr;
	}

	public String getAcctstoptimestr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(this.acctstoptime);
	}

	public void setAcctstoptimestr(String acctstoptimestr) {
		this.acctstoptimestr = acctstoptimestr;
	}

	public String getAcctupdatetimestr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(this.acctupdatetime);
	}

	public void setAcctupdatetimestr(String acctupdatetimestr) {
		this.acctupdatetimestr = acctupdatetimestr;
	}




	public String getRadacctid() {
		return this.radacctid;
	}

	public void setRadacctid(String radacctid) {
		this.radacctid = radacctid;
	}

	public String getAcctauthentic() {
		return this.acctauthentic;
	}

	public void setAcctauthentic(String acctauthentic) {
		this.acctauthentic = acctauthentic;
	}

	public Long getAcctinputoctets() {
		return this.acctinputoctets;
	}

	public void setAcctinputoctets(Long acctinputoctets) {
		this.acctinputoctets = acctinputoctets;
	}

	public Integer getAcctinterval() {
		return this.acctinterval;
	}

	public void setAcctinterval(Integer acctinterval) {
		this.acctinterval = acctinterval;
	}

	public Long getAcctoutputoctets() {
		return this.acctoutputoctets;
	}

	public void setAcctoutputoctets(Long acctoutputoctets) {
		this.acctoutputoctets = acctoutputoctets;
	}

	public String getAcctsessionid() {
		return this.acctsessionid;
	}

	public void setAcctsessionid(String acctsessionid) {
		this.acctsessionid = acctsessionid;
	}

	public Long getAcctsessiontime() {
		return this.acctsessiontime;
	}

	public void setAcctsessiontime(Long acctsessiontime) {
		this.acctsessiontime = acctsessiontime;
	}

	public Date getAcctstarttime() {
		return this.acctstarttime;
	}

	public void setAcctstarttime(Date acctstarttime) {
		this.acctstarttime = acctstarttime;
	}

	public Date getAcctstoptime() {
		return this.acctstoptime;
	}

	public void setAcctstoptime(Date acctstoptime) {
		this.acctstoptime = acctstoptime;
	}

	public String getAcctterminatecause() {
		return this.acctterminatecause;
	}

	public void setAcctterminatecause(String acctterminatecause) {
		this.acctterminatecause = acctterminatecause;
	}

	public String getAcctuniqueid() {
		return this.acctuniqueid;
	}

	public void setAcctuniqueid(String acctuniqueid) {
		this.acctuniqueid = acctuniqueid;
	}

	public Date getAcctupdatetime() {
		return this.acctupdatetime;
	}

	public void setAcctupdatetime(Date acctupdatetime) {
		this.acctupdatetime = acctupdatetime;
	}

	public String getCalledstationid() {
		return this.calledstationid;
	}

	public void setCalledstationid(String calledstationid) {
		this.calledstationid = calledstationid;
	}

	public String getCallingstationid() {
		return this.callingstationid;
	}

	public void setCallingstationid(String callingstationid) {
		this.callingstationid = callingstationid;
	}

	public String getConnectinfoStart() {
		return this.connectinfoStart;
	}

	public void setConnectinfoStart(String connectinfoStart) {
		this.connectinfoStart = connectinfoStart;
	}

	public String getConnectinfoStop() {
		return this.connectinfoStop;
	}

	public void setConnectinfoStop(String connectinfoStop) {
		this.connectinfoStop = connectinfoStop;
	}

	public String getFramedipaddress() {
		return this.framedipaddress;
	}

	public void setFramedipaddress(String framedipaddress) {
		this.framedipaddress = framedipaddress;
	}

	public String getFramedprotocol() {
		return this.framedprotocol;
	}

	public void setFramedprotocol(String framedprotocol) {
		this.framedprotocol = framedprotocol;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getNasipaddress() {
		return this.nasipaddress;
	}

	public void setNasipaddress(String nasipaddress) {
		this.nasipaddress = nasipaddress;
	}

	public String getNasportid() {
		return this.nasportid;
	}

	public void setNasportid(String nasportid) {
		this.nasportid = nasportid;
	}

	public String getNasporttype() {
		return this.nasporttype;
	}

	public void setNasporttype(String nasporttype) {
		this.nasporttype = nasporttype;
	}

	public String getRealm() {
		return this.realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public String getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}