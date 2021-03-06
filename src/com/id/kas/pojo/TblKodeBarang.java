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
 * TblKodeBarang generated by hbm2java
 */
@Entity
@Table(name = "TBL_KODE_BARANG", schema = "TESTDB")
public class TblKodeBarang implements java.io.Serializable {

	private String kodeBarang;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private BigDecimal versi;
	private String kelBarang;
	private String namaBarang;

	public TblKodeBarang() {
	}

	public TblKodeBarang(String kodeBarang, String kelBarang, String namaBarang) {
		this.kodeBarang = kodeBarang;
		this.kelBarang = kelBarang;
		this.namaBarang = namaBarang;
	}

	public TblKodeBarang(String kodeBarang, String createBy, Date createDate,
			String updateBy, Date updateDate, BigDecimal versi,
			String kelBarang, String namaBarang) {
		this.kodeBarang = kodeBarang;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.versi = versi;
		this.kelBarang = kelBarang;
		this.namaBarang = namaBarang;
	}

	@Id
	@Column(name = "KODE_BARANG", unique = true, nullable = false, length = 3)
	public String getKodeBarang() {
		return this.kodeBarang;
	}

	public void setKodeBarang(String kodeBarang) {
		this.kodeBarang = kodeBarang;
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

	@Column(name = "KEL_BARANG", nullable = false, length = 20)
	public String getKelBarang() {
		return this.kelBarang;
	}

	public void setKelBarang(String kelBarang) {
		this.kelBarang = kelBarang;
	}

	@Column(name = "NAMA_BARANG", nullable = false, length = 100)
	public String getNamaBarang() {
		return this.namaBarang;
	}

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

}
