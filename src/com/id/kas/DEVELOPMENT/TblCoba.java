package com.id.kas.DEVELOPMENT;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.id.kas.pojo.AuditTrail;

@Entity
@Table(name = "Tbl_Coba", schema = "TESTDB")
public class TblCoba extends AuditTrail implements java.io.Serializable {
	private String nik;
	private String nama;
	private Date tglLahir;
	private BigDecimal tarif;

	public TblCoba() {
	}

	@Id
	@Column(name = "NIK", unique = true, nullable = true, length = 5, scale = 0)
	public String getNik() {
		return this.nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	@Column(name = "NAMA", unique = false, nullable = false, length = 30, scale = 0)
	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TGL_LAHIR", unique = false, nullable = true, length = 18)
	public Date getTglLahir() {
		return this.tglLahir;
	}

	public void setTglLahir(Date tglLahir) {
		this.tglLahir = tglLahir;
	}

	@Column(name = "TARIF", unique = false, nullable = true, length = 10, scale = 0)
	public BigDecimal getTarif() {
		return this.tarif;
	}

	public void setTarif(BigDecimal tarif) {
		this.tarif = tarif;
	}
}