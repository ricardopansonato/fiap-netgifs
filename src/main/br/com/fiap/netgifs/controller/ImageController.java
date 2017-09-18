package br.com.fiap.netgifs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.netgifs.model.Category;
import br.com.fiap.netgifs.model.Image;
import br.com.fiap.netgifs.model.User;
import br.com.fiap.netgifs.repository.CategoryRepository;
import br.com.fiap.netgifs.repository.ImageRepository;
import br.com.fiap.netgifs.repository.UserRepository;
import br.com.fiap.netgifs.vo.UserData;


@Controller
@RequestMapping(value = "/content")
public class ImageController {

	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired	
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView viewUpload(final HttpSession session, final Image image) {
		final UserData userData = (UserData) session.getAttribute("user");
		final ModelAndView mv = new ModelAndView("upload");
		final List<Category> categories = categoryRepository.findAll();
		mv.addObject("user", userData);
		mv.addObject("image", image);
		mv.addObject("categoryList", categories);
		return mv;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(final HttpSession session, final Image image) {
		final UserData userData = (UserData) session.getAttribute("user");
		final Image dbImage = imageRepository.findByDescription(image.getDescription());
		final List<Category> categories = categoryRepository.findAll();
		
		String view = "upload";
		if(dbImage != null) {
			image.setMessage("Imagem já existe, favor cadastrar outra imagem!");
		} else {
			imageRepository.save(image);
			view = "redirect:/content/main"; 
		}
		final ModelAndView mv = new ModelAndView(view);
		mv.addObject("image", image);
		mv.addObject("user", userData);
		mv.addObject("categoryList", categories);
		return mv;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(final HttpSession session, final Long id) {
		final UserData userData = (UserData) session.getAttribute("user");
		final Image image = imageRepository.findById(id);
		final User user = userRepository.findById(userData.getId());
		
		final ModelAndView mv = new ModelAndView("/detail");
		mv.addObject("user", userData);
		mv.addObject("image", image);
		mv.addObject("favorite", !image.getUsers().contains(user));
		return mv;
	}
	
	@RequestMapping(value = "/favorite", method = RequestMethod.GET)
	public ModelAndView favorite(final HttpSession session, final Long id) {
		final UserData userData = (UserData) session.getAttribute("user");
		final Image dbImage = imageRepository.findById(id);
		final User user = userRepository.findById(userData.getId());
		
		List<User> users = dbImage.getUsers();
		if (users == null) {
			users = new ArrayList<>();
		}
		
		if (!users.contains(user)) {
			List<Image> favorites = user.getFavorites();
			users.add(user);
			dbImage.setUsers(users);
			favorites.add(dbImage);
			userRepository.save(user);
		}
		
		final ModelAndView mv = new ModelAndView("redirect:/content/detail?id=" + id);
		return mv;
	}
}