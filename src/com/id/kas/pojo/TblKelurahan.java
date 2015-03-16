package com.id.kas.pojo;

// Generated Mar 2, 2015 11:08:12 AM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblKelurahan generated by hbm2java
 */
@Entity
@Table(name = "TBL_KELURAHAN", schema = "TESTDB")
public class TblKelurahan implements java.io.Serializable {

	private TblKelurahanId id;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private BigDecimal versi;
	private String kodePos;
	private String namaKelurahan;

	public TblKelurahan() {
	}

	public TblKelurahan(TblKelurahanId id, String namaKelurahan) {
		this.id = id;
		this.namaKelurahan = namaKelurahan;
	}

	public TblKelurahan(TblKelurahanId id, String createBy, Date createDate,
			String updateBy, Date updateDate, BigDecimal versi, String kodePos,
			String namaKelurahan) {
		this.id = id;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.versi = versi;
		this.kodePos = kodePos;
		this.namaKelurahan = namaKelurahan;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "kodeKecamatan", column = @Column(name = "KODE_KECAMATAN", nullable = false, length = 6)),
			@AttributeOverride(name = "idKelurahan", column = @Column(name = "ID_KELURAHAN", nullable = false, length = 10)) })
	public TblKelurahanId getId() {
		return this.id;
	}

	public void setId(TblKelurahanId id) {
		this.id = id;
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

	@Column(name = "KODE_POS", length = 6)
	public String getKodePos() {
		return this.kodePos;
	}

	public void setKodePos(String kodePos) {
		this.kodePos = kodePos;
	}

	@Column(name = "NAMA_KELURAHAN", nullable = false, length = 100)
	public String getNamaKelurahan() {
		return this.namaKelurahan;
	}

	public void setNamaKelurahan(String namaKelurahan) {
		this.namaKelurahan = namaKelurahan;
	}

}
