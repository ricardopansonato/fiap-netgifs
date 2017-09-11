package br.com.fiap.netgifs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.netgifs.model.User;

@Controller
@RequestMapping(value = "/content")
public class HomeController {
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(User user) {
		ModelAndView mv = new ModelAndView("main");
		mv.addObject("user", user);
		return mv;
	}
}
