package org.zyj.shsf.webadmin.control;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zyj.shsf.webadmin.vo.LoginUserVO;


@Controller
@RequestMapping(value = "/login")
public class LoginController {

	protected  final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/userLogin.html",method=RequestMethod.POST)
	public ModelAndView login(LoginUserVO user) {
		logger.info("UserName:" + user.getUserName());
		logger.info("Password:" + user.getPassword());
		
		ModelAndView view = new ModelAndView("main");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("menuOrgName", "Add Organization");
		view.addAllObjects(paramMap);
		return view;
	}
}
