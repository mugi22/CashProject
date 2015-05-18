package com.id.kas.pojo;

// Generated Jan 21, 2013 10:52:40 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TblFlow generated by hbm2java
 */
@Entity
@Table(name = "tbl_flow", schema = "TESTDB")
public class TblFlow extends AuditTrail implements java.io.Serializable {

	private String flowCode;
	private String flowName;
	private int expireDays;
	private String className;
	private String usingGroovy;
//	private Set<TblGroup> tblGroups = new HashSet<TblGroup>(0);
//	private Set<TblUserEvent> tblUserEvents = new HashSet<TblUserEvent>(0);
//	private Set<TblFlowHist> tblFlowHists = new HashSet<TblFlowHist>(0);

	public TblFlow() {
	}

	public TblFlow(String flowCode) {
		this.flowCode = flowCode;
	}

	public TblFlow(String flowCode, String flowName, int expireDays, String className, String usingGroovy) {
		this.flowCode = flowCode;
		this.flowName = flowName;
		this.expireDays = expireDays;
		this.className = className;
		this.usingGroovy = usingGroovy;
//		this.tblGroups = tblGroups;
//		this.tblUserEvents = tblUserEvents;
//		this.tblFlowHists = tblFlowHists;
	}

	@Id
	@Column(name = "flow_code", nullable = false, length = 100)
	public String getFlowCode() {
		return this.flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	@Column(name = "flow_name", length = 200)
	public String getFlowName() {
		return this.flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	@Column(name = "expire_days")
	public int getExpireDays() {
		return this.expireDays;
	}

	public void setExpireDays(int expireDays) {
		this.expireDays = expireDays;
	}

	@Column(name = "class_name", length = 100)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "using_groovy", length = 1)
	public String getUsingGroovy() {
		return this.usingGroovy;
	}

	public void setUsingGroovy(String usingGroovy) {
		this.usingGroovy = usingGroovy;
	}

//	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//	@JoinTable(name="tbl_group_assignment", joinColumns={@JoinColumn(name="flow_code")},inverseJoinColumns={@JoinColumn(name="group_id")})
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tblFlows")
//	public Set<TblGroup> getTblGroups() {
//		return this.tblGroups;
//	}
//
//	public void setTblGroups(Set<TblGroup> tblGroups) {
//		this.tblGroups = tblGroups;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblFlow")
//	public Set<TblUserEvent> getTblUserEvents() {
//		return this.tblUserEvents;
//	}
//
//	public void setTblUserEvents(Set<TblUserEvent> tblUserEvents) {
//		this.tblUserEvents = tblUserEvents;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblFlow")
//	public Set<TblFlowHist> getTblFlowHists() {
//		return this.tblFlowHists;
//	}
//
//	public void setTblFlowHists(Set<TblFlowHist> tblFlowHists) {
//		this.tblFlowHists = tblFlowHists;
//	}

}