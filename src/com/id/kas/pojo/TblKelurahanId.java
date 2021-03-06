package com.id.kas.pojo;

// Generated Mar 2, 2015 11:08:12 AM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TblKelurahanId generated by hbm2java
 */
@Embeddable
public class TblKelurahanId implements java.io.Serializable {

	private String kodeKecamatan;
	private String idKelurahan;

	public TblKelurahanId() {
	}

	public TblKelurahanId(String kodeKecamatan, String idKelurahan) {
		this.kodeKecamatan = kodeKecamatan;
		this.idKelurahan = idKelurahan;
	}

	@Column(name = "KODE_KECAMATAN", nullable = false, length = 6)
	public String getKodeKecamatan() {
		return this.kodeKecamatan;
	}

	public void setKodeKecamatan(String kodeKecamatan) {
		this.kodeKecamatan = kodeKecamatan;
	}

	@Column(name = "ID_KELURAHAN", nullable = false, length = 10)
	public String getIdKelurahan() {
		return this.idKelurahan;
	}

	public void setIdKelurahan(String idKelurahan) {
		this.idKelurahan = idKelurahan;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblKelurahanId))
			return false;
		TblKelurahanId castOther = (TblKelurahanId) other;

		return ((this.getKodeKecamatan() == castOther.getKodeKecamatan()) || (this
				.getKodeKecamatan() != null
				&& castOther.getKodeKecamatan() != null && this
				.getKodeKecamatan().equals(castOther.getKodeKecamatan())))
				&& ((this.getIdKelurahan() == castOther.getIdKelurahan()) || (this
						.getIdKelurahan() != null
						&& castOther.getIdKelurahan() != null && this
						.getIdKelurahan().equals(castOther.getIdKelurahan())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getKodeKecamatan() == null ? 0 : this.getKodeKecamatan()
						.hashCode());
		result = 37
				* result
				+ (getIdKelurahan() == null ? 0 : this.getIdKelurahan()
						.hashCode());
		return result;
	}

}
