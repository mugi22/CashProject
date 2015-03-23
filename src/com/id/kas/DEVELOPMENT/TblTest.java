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
@Table(name = "TBL_TEST", schema = "TESTDB")
public class TblTest extends AuditTrail implements java.io.Serializable {
	private String nik;
	private String nama;
	private Date tglLahir;

	public TblTest() {
	}

	@Id
	@Column(name = "NIK", unique = true, nullable = false, scale = 0)
	public String getNik() {
		return this.nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	@Column(name = "NAMA", unique = false, nullable = false, scale = 0)
	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TGLLAHIR", unique = false, nullable = true, scale = 0)
	public Date getTglLahir() {
		return this.tglLahir;
	}

	public void setTglLahir(Date tglLahir) {
		this.tglLahir = tglLahir;
	}
}