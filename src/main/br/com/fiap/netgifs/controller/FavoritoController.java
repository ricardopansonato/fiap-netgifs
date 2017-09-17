package br.com.fiap.netgifs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.netgifs.model.Favorito;
import br.com.fiap.netgifs.model.Gif;
import br.com.fiap.netgifs.model.User;
import br.com.fiap.netgifs.repository.FavoritoRepository;
import br.com.fiap.netgifs.repository.GifRepository;
import br.com.fiap.netgifs.repository.UserRepository;
import br.com.fiap.netgifs.vo.UserData;


@Controller
public class FavoritoController {

	@Autowired
	private GifRepository gifRepository;
	//@Autowired
	//private UserRepository userRepository;
	@Autowired
	private FavoritoRepository favoritoRepository;

	
	//@RequestMapping(value="/addfavorito/{id}/gifs", method=RequestMethod.POST)
	@RequestMapping(value="/addfavorito", method=RequestMethod.POST)
	public ModelAndView addFavorito(Gif gif, 
			User user, Model model) {
	//	User user1 = userRepository.findOne((long) 1);
  
		Long id = (long) 1;
		
		//final User dbUser = userRepository.findOne(id);
	//		HttpSession session = null;
//			session.setAttribute("user", new UserData(user));
		
        Gif gif1 = new Gif();
        
        User user1 = new User();
        user1.setId((long) 1);
        
        Favorito favorito = gravaFavorito(gif, user1);		

        System.out.println("addfavorito Descricao: " + gif.getDescricao() );
		System.out.println("addfavorito User:" + user.getUsername());
	//	System.out.println("addfavorito dbUser:" + dbUser.getUsername());

		String view = "/addfavorito";
		ModelAndView mv = new ModelAndView(view);
		mv.addObject("favorito", favorito);

		return mv;

	} 

	public Favorito gravaFavorito(Gif gif, User user) {
		
		Favorito favorito = new Favorito();
		favorito.setGif(gif);
        return favoritoRepository.save(favorito);

}

	
}
	
	
