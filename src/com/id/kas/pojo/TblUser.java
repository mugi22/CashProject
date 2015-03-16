package com.id.kas.pojo;

// Generated Mar 2, 2015 11:08:12 AM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblUser generated by hbm2java
 */
@Entity
@Table(name = "TBL_USER", schema = "TESTDB")
public class TblUser implements java.io.Serializable {

	private String userId;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private BigDecimal versi;
	private String branchCode;
	private String enabled;
	private Date endTime;
	private Date lastChangePwd;
	private Date lastLogIn;
	private BigDecimal limitApproval;
	private Long loginFailCount;
	private String branchMobileId;
	private String name;
	private String password;
	private Date startTime;
	private String email;

	public TblUser() {
	}

	public TblUser(String userId, String branchCode, Date endTime, String name,
			String password, Date startTime) {
		this.userId = userId;
		this.branchCode = branchCode;
		this.endTime = endTime;
		this.name = name;
		this.password = password;
		this.startTime = startTime;
	}

	public TblUser(String userId, String createBy, Date createDate,
			String updateBy, Date updateDate, BigDecimal versi,
			String branchCode, String enabled, Date endTime,
			Date lastChangePwd, Date lastLogIn, BigDecimal limitApproval,
			Long loginFailCount, String branchMobileId, String name,
			String password, Date startTime, String email) {
		this.userId = userId;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.versi = versi;
		this.branchCode = branchCode;
		this.enabled = enabled;
		this.endTime = endTime;
		this.lastChangePwd = lastChangePwd;
		this.lastLogIn = lastLogIn;
		this.limitApproval = limitApproval;
		this.loginFailCount = loginFailCount;
		this.branchMobileId = branchMobileId;
		this.name = name;
		this.password = password;
		this.startTime = startTime;
		this.email = email;
	}

	@Id
	@Column(name = "USER_ID", unique = true, nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@Column(name = "BRANCH_CODE", nullable = false, length = 5)
	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	@Column(name = "ENABLED", length = 1)
	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_TIME", nullable = false, length = 7)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_CHANGE_PWD", length = 7)
	public Date getLastChangePwd() {
		return this.lastChangePwd;
	}

	public void setLastChangePwd(Date lastChangePwd) {
		this.lastChangePwd = lastChangePwd;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_LOG_IN", length = 7)
	public Date getLastLogIn() {
		return this.lastLogIn;
	}

	public void setLastLogIn(Date lastLogIn) {
		this.lastLogIn = lastLogIn;
	}

	@Column(name = "LIMIT_APPROVAL", precision = 12)
	public BigDecimal getLimitApproval() {
		return this.limitApproval;
	}

	public void setLimitApproval(BigDecimal limitApproval) {
		this.limitApproval = limitApproval;
	}

	@Column(name = "LOGIN_FAIL_COUNT", precision = 10, scale = 0)
	public Long getLoginFailCount() {
		return this.loginFailCount;
	}

	public void setLoginFailCount(Long loginFailCount) {
		this.loginFailCount = loginFailCount;
	}

	@Column(name = "BRANCH_MOBILE_ID", length = 10)
	public String getBranchMobileId() {
		return this.branchMobileId;
	}

	public void setBranchMobileId(String branchMobileId) {
		this.branchMobileId = branchMobileId;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PASSWORD", nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_TIME", nullable = false, length = 7)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
