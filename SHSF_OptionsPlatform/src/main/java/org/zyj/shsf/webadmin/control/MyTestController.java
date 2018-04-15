package org.zyj.shsf.webadmin.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.zyj.shsf.webadmin.control.BaseController;

@Controller
@RequestMapping(value = "/testcontroller")
public class MyTestController extends BaseController {

	@RequestMapping(value = "/testpage")
	public ModelAndView toMyTestPage() {
		return new ModelAndView("testpage");
	}
}
