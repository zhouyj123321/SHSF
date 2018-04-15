package org.zyj.shsf.webadmin.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Meta_Organization")
public class MetaOrganization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6786834959251348426L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrgID")
	private Integer orgId;
	
	@Column(name = "OrgName")
	private String orgName;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "OrgID")
	private Collection<MetaSystem> system = new HashSet<MetaSystem>();

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Collection<MetaSystem> getSystem() {
		return system;
	}

	public void setSystem(Collection<MetaSystem> system) {
		this.system = system;
	}
	
	
}
