package com.id.kas.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblGPegadaian generated by hbm2java
 */
@Entity
@Table(name = "TBL_PEGAWAI", schema = "TESTDB")
public class TblPegawai extends AuditTrail implements java.io.Serializable {

	private String nik;
	private String cif;
	private String nama;
	private Date tglLahir;
	private String statusPegawai;
	private String grade;
	private String branchCode; // Cabang dimana user di Tempatkan oleh SDM
	private String statusAktif;
	
	public TblPegawai() {
		
	}
	
	@Id
	@Column(name = "nik", nullable = false, length = 20)
	public String getNik() {
		return nik;
	}
	
	public void setNik(String nik) {
		this.nik = nik;
	}
	
	@Column(name = "cif", length = 16)
	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	@Column(name = "nama", nullable = false, length = 200)
	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tgl_lahir", nullable = false)
	public Date getTglLahir() {
		return tglLahir;
	}
	
	public void setTglLahir(Date tglLahir) {
		this.tglLahir = tglLahir;
	}

	@Column(name = "status_pegawai", length = 3)
	public String getStatusPegawai() {
		return statusPegawai;
	}

	public void setStatusPegawai(String statusPegawai) {
		this.statusPegawai = statusPegawai;
	}
	
	@Column(name = "grade", length = 5)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "branch_code", length = 5)
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	@Column(name = "status_aktif", length = 3)
	public String getStatusAktif() {
		return statusAktif;
	}

	public void setStatusAktif(String statusAktif) {
		this.statusAktif = statusAktif;
	}

}