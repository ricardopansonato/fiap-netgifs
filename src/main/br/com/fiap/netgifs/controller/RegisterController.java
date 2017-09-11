package br.com.fiap.netgifs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.netgifs.model.User;
import br.com.fiap.netgifs.repository.UserRepository;

@Controller
public class RegisterController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView index(User user) {
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("user", user);
		return mv;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView auth(User user) {
		String view = "register";
		User dbUser = userRepository.findByUsername(user.getUsername());
		
		if (dbUser != null) {
			user.setMessage("Usuário já existe, favor efetuar o login");
		} else if (!user.confirmPassword()) {
			user.setMessage("Senha e confirmação de senha não conferem");
		} else {
			userRepository.save(user);
			view = "redirect:/login";
		}
		
		final ModelAndView mv = new ModelAndView(view);
		mv.addObject("user", user);
		return mv;
	}
	
}
