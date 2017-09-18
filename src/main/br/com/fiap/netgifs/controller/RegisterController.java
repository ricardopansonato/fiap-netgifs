package br.com.fiap.netgifs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.netgifs.model.User;
import br.com.fiap.netgifs.repository.UserRepository;
import br.com.fiap.netgifs.vo.UserData;

@Controller
@RequestMapping(value = "/content")
public class RegisterController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session, User user) {
		final UserData userData = (UserData) session.getAttribute("user");
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("user", user);
		mv.addObject("userData", userData);
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
