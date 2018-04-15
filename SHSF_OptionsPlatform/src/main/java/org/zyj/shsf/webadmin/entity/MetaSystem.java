package org.zyj.shsf.webadmin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Meta_System")
public class MetaSystem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4347952457264432798L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer systemId;
	
	@Column(name = "OrgID")
	private Integer orgId;
	
	@Column(name = "System_Name")
	private String systemName;
	
	@Column(name = "System_Owner")
	private String systemOwner;

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemOwner() {
		return systemOwner;
	}

	public void setSystemOwner(String systemOwner) {
		this.systemOwner = systemOwner;
	}
	
	
}
