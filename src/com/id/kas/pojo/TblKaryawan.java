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
@Table(name = "TBL_KARYAWAN", schema = "TESTDB")
public class TblKaryawan extends AuditTrail implements java.io.Serializable {
	private String nama;
	private String nik;
	private String unitKerja;

	public TblKaryawan() {
	}

	@Column(name = "NAMA", unique = false, nullable = false, length = 40, scale = 0)
	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Id
	@Column(name = "NIK", unique = true, nullable = false, length = 6, scale = 0)
	public String getNik() {
		return this.nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	@Column(name = "UNIT_KERJA", unique = false, nullable = true, length = 5, scale = 0)
	public String getUnitKerja() {
		return this.unitKerja;
	}

	public void setUnitKerja(String unitKerja) {
		this.unitKerja = unitKerja;
	}
}