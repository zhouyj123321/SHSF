package org.zyj.shsf.webadmin.control.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.zyj.shsf.webadmin.control.BaseController;

@RestController
@RequestMapping(value = "/testcontroller2")
public class MyTestController2 extends BaseController {

	@RequestMapping(value = "/testpage2")
	public ModelAndView toMyTestPage() {
		System.out.println("===========3333===0000==========");
		return new ModelAndView("testpage");
	}
}
