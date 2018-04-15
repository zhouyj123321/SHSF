package org.zyj.shsf.webadmin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.zyj.sfsh.common.JsonValidator;
import org.zyj.sfsh.common.StringHelper;
import org.zyj.shsf.http.common.RequestType;

@Entity
@Table(name = "Svce_ProvideService")
public class SvceProvideService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6238352089937348983L;
	
	@Id
	@Column(name = "Provide_Service_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer provideServiceId;
	
	@Column(name = "OrgID")
	private Integer orgId;
	
	@Column(name = "System_ID")
	private Integer systemId;
	
	@Column(name = "Service_URL")
	private String serviceURL;
	
	@Column(name = "ZK_Path")
	private String zkPath;
	
	//请求类型
	@Column(name = "Request_Type")
	private String reqType;
	
	//针对Post请求的参数模板， JSON格式。服务消费者按照此模板传参数
	@Column(name = "Post_Param_Template")
	private String jsonParamTemplateForPost;
	//GET请求参数模板。服务消费者按照此模板传参数
	@Column(name = "Get_Param_Template")
	private String paramTemplateForGet;
	
	/**
	 * 验证JSON参数格式的合法性
	 * @return
	 */
	public boolean validateJsonParam() {
		boolean isLegal = false;
		if (!StringHelper.isEmpty(this.reqType) &&
				this.reqType.toUpperCase().equals(RequestType.POST.toString())) {
			if (new JsonValidator().validate(jsonParamTemplateForPost)) {
				isLegal = true;
			}
		}else {
			return true;
		}
		return isLegal;
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

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	public String getZkPath() {
		return zkPath;
	}

	public void setZkPath(String zkPath) {
		this.zkPath = zkPath;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getJsonParamTemplateForPost() {
		return jsonParamTemplateForPost;
	}

	public void setJsonParamTemplateForPost(String jsonParamTemplateForPost) {
		this.jsonParamTemplateForPost = jsonParamTemplateForPost;
	}

	public String getParamTemplateForGet() {
		return paramTemplateForGet;
	}

	public void setParamTemplateForGet(String paramTemplateForGet) {
		this.paramTemplateForGet = paramTemplateForGet;
	}

	
}
