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
@Table(name = "TBL_TARIF", schema = "TESTDB")
public class TblTarif extends AuditTrail implements java.io.Serializable {
	private String idTarif;
	private String grade;
	private Date startDate;
	private BigDecimal tarif;

	public TblTarif() {
	}

	@Id
	@Column(name = "IDTARIF", unique = false, nullable = false, length = 10, scale = 0)
	public String getIdTarif() {
		return this.idTarif;
	}

	public void setIdTarif(String idTarif) {
		this.idTarif = idTarif;
	}

	@Id
	@Column(name = "GRADE", unique = false, nullable = false, length = 5, scale = 0)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTDATE", unique = false, nullable = false, length = 7)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "TARIF", unique = false, nullable = false, length = 8, scale = 0)
	public BigDecimal getTarif() {
		return this.tarif;
	}

	public void setTarif(BigDecimal tarif) {
		this.tarif = tarif;
	}
}