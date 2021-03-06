package com.id.kas.pojo;

// Generated Mar 2, 2015 11:08:12 AM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblGroup generated by hbm2java
 */
@Entity
@Table(name = "TBL_GROUP", schema = "TESTDB")
public class TblGroup implements java.io.Serializable {
	

	private BigDecimal groupId;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private BigDecimal versi;
	private String groupName;
	private String jabatan;
	private String params;
	

	public TblGroup() {
	}

	public TblGroup(BigDecimal groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
	}

	public TblGroup(BigDecimal groupId, String createBy, Date createDate,
			String updateBy, Date updateDate, BigDecimal versi,
			String groupName, String jabatan, String params) {
		this.groupId = groupId;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.versi = versi;
		this.groupName = groupName;
		this.jabatan = jabatan;
		this.params = params;
	}

	@Id
	@Column(name = "GROUP_ID", unique = true, nullable = false, scale = 0)
	public BigDecimal getGroupId() {
		return this.groupId;
	}

	public void setGroupId(BigDecimal groupId) {
		this.groupId = groupId;
	}

	@Column(name = "CREATE_BY", length = 20)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_BY", length = 20)
	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE", length = 7)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "VERSI", scale = 0)
	public BigDecimal getVersi() {
		return this.versi;
	}

	public void setVersi(BigDecimal versi) {
		this.versi = versi;
	}

	@Column(name = "GROUP_NAME", nullable = false, length = 100)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "JABATAN", length = 5)
	public String getJabatan() {
		return this.jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	@Column(name = "PARAMS", length = 500)
	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
