package br.com.fiap.netgifs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.netgifs.model.Gif;
import br.com.fiap.netgifs.model.User;
import br.com.fiap.netgifs.repository.GifRepository;


@Controller
public class GifController {

	@Autowired
	private GifRepository gifRepository;
	
	@RequestMapping(value = "/detalhegif", method = RequestMethod.GET)
	public ModelAndView detalheGif(User user, HttpSession session) {
		String view = "/detalhe";
		Long id = (long) 1;
		
		Gif gif = new Gif();	
		//gif.setId(id);
		gif.setDescricao("Descricao Gif1");
		gif.setFaixaEtaria("De 10 a 15 anos");
		gif.setGenero("Comédia");
		gif.setIdioma("Português");
		gif.setUrlGif("http://www.gifs.blog.br/imagens/gifs-gifs-animados-oculos-1.gif");
		gravaGifs(gif);
		
		Gif gif2 = new Gif();
		id = (long) 2;
		//gif2.setId(id);
		gif2.setDescricao("Descricao Gif2");
		gif2.setFaixaEtaria("De 10 a 15 anos");
		gif2.setGenero("Comédia");
		gif2.setIdioma("Português");
		gif2.setUrlGif("http://www.gifs.blog.br/imagens/gifs-gifs-animados-oculos-1.gif");
		gravaGifs(gif2);

		System.out.println("User login detalhesgif: " + user.getUsername());

		
		ModelAndView mv = new ModelAndView(view);
		mv.addObject("gif", gif);
		
		System.out.println("entrou no detalhegif");
		
		return mv;
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public ModelAndView cadastrar(Gif gif) {
		ModelAndView mv = new ModelAndView("cadastrar");
		mv.addObject("gif", gif);
		return mv;
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public ModelAndView cadastrarAdd(Gif gif) {
		String view = "cadastrar";
		
		Gif dbGif = gifRepository.findByDescricao(gif.getDescricao());
		if(dbGif != null) {
			gif.setMessage("Gif já existe, favor cadatrar outra imagem!");
			System.out.println("imagem ja existe");
		} else {
			gifRepository.save(gif);
			view = "redirect:/listar"; 
		}
		final ModelAndView mv = new ModelAndView(view);
		mv.addObject("gif", gif);
		return mv;
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar(User user, HttpSession session) {
		String view = "lista";
		
		List<Gif> list = gifRepository.findAll();
		final ModelAndView mv = new ModelAndView(view);
		mv.addObject("gifs", list);
		return mv;
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public ModelAndView menu(User user, HttpSession session) {
		String view = "redirect:/content/main";
		final ModelAndView mv = new ModelAndView(view);
		return mv;
	}
	
	public void gravaGifs(Gif gif) {
		    gifRepository.save(gif);

	}
	
	@RequestMapping(value = "/exibirfull", method = RequestMethod.POST)
	public ModelAndView exibirFull(Gif gif) {
		String view = "/paginafull";
		
		ModelAndView mv = new ModelAndView(view);
		mv.addObject("gif", gif);

		return mv;
	}
	
	@RequestMapping(value = "/exibir", method = RequestMethod.GET)
	public ModelAndView exibir(Long id) {
		String view = "/paginafull";
		
		Gif gif = gifRepository.findById(id);
		final ModelAndView mv = new ModelAndView(view);
		mv.addObject("gif", gif);
		return mv;
	}
	
	
	
}
	
	
