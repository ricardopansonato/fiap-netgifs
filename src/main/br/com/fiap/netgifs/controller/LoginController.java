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
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("redirect:/login");
		return mv;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(User user, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/login");
		session.invalidate();
		return mv;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView auth(User user, HttpSession session) {
		UserData userSession = (UserData) session.getAttribute("user");
		String view = "login";
		if (userSession != null) {
			view = "redirect:/content/main";
		}
		ModelAndView mv = new ModelAndView(view);
		mv.addObject("user", user);
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(User user, HttpSession session) {
		final User dbUser = userRepository.findByUsername(user.getUsername());
		String view = "login";
		if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
			session.setAttribute("user", new UserData(dbUser));
			view = "redirect:/content/main";
		} else {
			user.setMessage("Usuário e/ou senha inválido(s)");
		}

		final ModelAndView mv = new ModelAndView(view);
		mv.addObject("user", user);
		return mv;
	}
}