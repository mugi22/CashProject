package com.id.kas.pojo;

// Generated Mar 2, 2015 11:08:12 AM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblKecamatan generated by hbm2java
 */
@Entity
@Table(name = "TBL_KECAMATAN", schema = "TESTDB")
public class TblKecamatan extends AuditTrail implements java.io.Serializable {


	private String namaKecamatan;
	private String kodeKecamatan;
	private String kodeKabupaten;

	public TblKecamatan() {
	}

	@Column(name = "NAMA_KECAMATAN", nullable = false, length = 100)
	public String getNamaKecamatan() {
		return this.namaKecamatan;
	}

	public void setNamaKecamatan(String namaKecamatan) {
		this.namaKecamatan = namaKecamatan;
	}
	@Id
	@Column(name = "KODE_KECAMATAN", nullable = false, length = 6)
	public String getKodeKecamatan() {
		return this.kodeKecamatan;
	}

	public void setKodeKecamatan(String kodeKecamatan) {
		this.kodeKecamatan = kodeKecamatan;
	}
@Id
	@Column(name = "KODE_KABUPATEN", nullable = false, length = 4)
	public String getKodeKabupaten() {
		return this.kodeKabupaten;
	}

	public void setKodeKabupaten(String kodeKabupaten) {
		this.kodeKabupaten = kodeKabupaten;
	}

}
