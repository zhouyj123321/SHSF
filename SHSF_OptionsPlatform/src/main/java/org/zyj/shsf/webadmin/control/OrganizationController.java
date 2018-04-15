package org.zyj.shsf.webadmin.control;

import org.shsf.zk.exception.ZKNodeFullPathIsNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zyj.sfsh.common.StringHelper;
import org.zyj.shsf.webadmin.entity.MetaOrganization;
import org.zyj.shsf.webadmin.service.OrganizationIface;
import org.zyj.shsf.webadmin.vo.OrganizationVO;


@Controller
@RequestMapping(value = "/orgManage")
public class OrganizationController extends BaseController{

	protected  final Logger logger = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	private OrganizationIface orgService;
	
	@RequestMapping(value="/addOrg.html")
	@ResponseBody
	public ModelAndView addOrg(OrganizationVO orgVO) throws ZKNodeFullPathIsNullException {
		if (orgVO != null && !StringHelper.isEmpty(orgVO.getOrgName())) {
			MetaOrganization  org = new MetaOrganization();
			org.setOrgName(org.getOrgName());
			orgService.addOrg(org);
		}
		
		return null;
	}
	
	
	
}
