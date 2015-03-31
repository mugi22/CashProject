package com.id.kas.pojo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBL_TAGIHAN", schema = "TESTDB")
public class TblTagihan extends AuditTrail implements java.io.Serializable {
	private String yemm;
	private String branchcode;
	private String nik;
	private String grade;
	private BigDecimal jumlah;
	private Date tglBayar;
	private String satusBayar;
	private String kasir;

	public TblTagihan() {
	}

	@Id
	@Column(name = "YEMM", unique = false, nullable = false, length = 6, scale = 0)
	public String getYemm() {
		return this.yemm;
	}

	public void setYemm(String yemm) {
		this.yemm = yemm;
	}

	@Id
	@Column(name = "BRANCH_CODE", unique = false, nullable = false, length = 5, scale = 0)
	public String getBranchcode() {
		return this.branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

	@Id
	@Column(name = "NIK", unique = false, nullable = false, length = 6, scale = 0)
	public String getNik() {
		return this.nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	@Column(name = "GRADE", unique = false, nullable = true, length = 5, scale = 0)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "JUMLAH", unique = false, nullable = true, length = 10, scale = 0)
	public BigDecimal getJumlah() {
		return this.jumlah;
	}

	public void setJumlah(BigDecimal jumlah) {
		this.jumlah = jumlah;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TGL_BAYAR", unique = false, nullable = true, length = 13)
	public Date getTglBayar() {
		return this.tglBayar;
	}

	public void setTglBayar(Date tglBayar) {
		this.tglBayar = tglBayar;
	}

	@Column(name = "SATUS_BAYAR", unique = false, nullable = true, length = 2, scale = 0)
	public String getSatusBayar() {
		return this.satusBayar;
	}

	public void setSatusBayar(String satusBayar) {
		this.satusBayar = satusBayar;
	}

	@Column(name = "KASIR", unique = false, nullable = true, length = 10, scale = 0)
	public String getKasir() {
		return this.kasir;
	}

	public void setKasir(String kasir) {
		this.kasir = kasir;
	}
}