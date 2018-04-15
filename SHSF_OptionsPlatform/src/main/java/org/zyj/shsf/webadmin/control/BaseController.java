package org.zyj.shsf.webadmin.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BaseController {

	public HttpSession getSession() {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getSession();
		return session;

	}
	
	/**
	 * 跳转到主界面
	 * @return
	 */
	@RequestMapping(value = "/main.html")
	public ModelAndView main() {
		return new ModelAndView("layout/masterPage");
	}
	
	/**
	 * 登录界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/index.jsp", "/login.html" }, method = RequestMethod.GET)
	public ModelAndView redirectLogin(HttpServletRequest request) {
		return new ModelAndView("login", "company", null);
	}
	
}
