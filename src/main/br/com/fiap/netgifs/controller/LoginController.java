package br.com.fiap.netgifs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.netgifs.model.Gif;
import br.com.fiap.netgifs.model.User;
import br.com.fiap.netgifs.repository.GifRepository;
import br.com.fiap.netgifs.repository.UserRepository;
import br.com.fiap.netgifs.vo.UserData;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GifRepository gifRepository;

	
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
			session.setAttribute("user", new UserData(user));
			view = "redirect:/content/main";
		} else {
			user.setMessage("Usuário e/ou senha inválido(s)");
		}

		System.out.println("User login: " + user.getUsername());

		//criarGifs();
		
		final ModelAndView mv = new ModelAndView(view);
		mv.addObject("user", user);
		return mv;
	}

	void criarGifs() {
		//Criar Gifs
		Gif gif = new Gif();	
		gif.setDescricao("Descricao Gif1");
		gif.setFaixaEtaria("De 10 a 15 anos");
		gif.setGenero("Comédia");
		gif.setIdioma("Português");
		gif.setUrlGif("http://www.gifs.blog.br/imagens/gifs-gifs-animados-oculos-1.gif");
		gravaGifs(gif);

		gif.setDescricao("Descricao Gif2");
		gif.setFaixaEtaria("De 10 a 15 anos");
		gif.setGenero("Comédia");
		gif.setIdioma("Português");
		gif.setUrlGif("http://www.gifs.blog.br/imagens/gifs-gifs-animados-oculos-1.gif");
		gravaGifs(gif);

		gif.setDescricao("Descricao Gif3");
		gif.setFaixaEtaria("De 10 a 15 anos");
		gif.setGenero("Comédia");
		gif.setIdioma("Português");
		gif.setUrlGif("http://www.gifs.blog.br/imagens/gifs-gifs-animados-oculos-1.gif");
		gravaGifs(gif);

		gif.setDescricao("Descricao Gif4");
		gif.setFaixaEtaria("De 10 a 15 anos");
		gif.setGenero("Comédia");
		gif.setIdioma("Português");
		gif.setUrlGif("http://www.gifs.blog.br/imagens/gifs-gifs-animados-oculos-1.gif");
		gravaGifs(gif);
		
	}
	
	public void gravaGifs(Gif gif) {
	    gifRepository.save(gif);

}

	
}
