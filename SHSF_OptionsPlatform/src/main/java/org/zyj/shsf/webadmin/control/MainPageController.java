package org.zyj.shsf.webadmin.control;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面导航
 *
 */
@Controller
@RequestMapping(value = "/main")
public class MainPageController {

	protected  final Logger logger = LoggerFactory.getLogger(MainPageController.class);
	
	/**
	 * 登录用户首页。展示会员所参与系统所提供的服务概览。如系统提供的服务列表、依赖的服务列表、SLA等核心信息
	 * @return
	 */
	@RequestMapping(value = "/toMySystemsSumary.html")
	public ModelAndView toIndexPage() {
		ModelAndView view = new ModelAndView("rightPageToMySystemsSumary");
		return view;
	}
	
	@RequestMapping(value = "/toTopPage.html")
	public ModelAndView toTopPage() {
		ModelAndView view = new ModelAndView("top");
		return view;
	}
	
	@RequestMapping(value = "/toLeftPage.html")
	public ModelAndView toLeftPage() {
		ModelAndView view = new ModelAndView("left");
		return view;
	}
	
	
}
