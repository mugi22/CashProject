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
 * TblParam generated by hbm2java
 */
@Entity
@Table(name = "TBL_PARAM", schema = "TESTDB")
public class TblParam implements java.io.Serializable {

	private String key;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private BigDecimal versi;
	private String description;
	private String value;

	public TblParam() {
	}

	public TblParam(String key, String description, String value) {
		this.key = key;
		this.description = description;
		this.value = value;
	}

	public TblParam(String key, String createBy, Date createDate,
			String updateBy, Date updateDate, BigDecimal versi,
			String description, String value) {
		this.key = key;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.versi = versi;
		this.description = description;
		this.value = value;
	}

	@Id
	@Column(name = "KEY_", unique = true, nullable = false, length = 100)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
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

	@Column(name = "DESCRIPTION_", nullable = false, length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "VALUE_", nullable = false, length = 400)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
