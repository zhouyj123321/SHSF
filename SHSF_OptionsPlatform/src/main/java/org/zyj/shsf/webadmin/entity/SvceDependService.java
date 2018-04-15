package org.zyj.shsf.webadmin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Svce_DependService")
public class SvceDependService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2204604730228855224L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "System_ID")
	private Integer systemId;
	
	@Column(name = "Provide_Service_ID")
	private Integer provideServiceId;
	
	@Column(name = "OrgID")
	private Integer orgId;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public Integer getProvideServiceId() {
		return provideServiceId;
	}

	public void setProvideServiceId(Integer provideServiceId) {
		this.provideServiceId = provideServiceId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

}
