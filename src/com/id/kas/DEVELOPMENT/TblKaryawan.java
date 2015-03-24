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
@Table(name = "TBL_KARYAWAN", schema = "TESTDB")
public class TblKaryawan extends AuditTrail implements java.io.Serializable {
	private String nik;
	private String nama;
	private String unitKerja;

	public TblKaryawan() {
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

	@Column(name = "UNITKERJA", unique = false, nullable = true, scale = 0)
	public String getUnitKerja() {
		return this.unitKerja;
	}

	public void setUnitKerja(String unitKerja) {
		this.unitKerja = unitKerja;
	}
}