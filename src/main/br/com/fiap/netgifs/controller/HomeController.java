package br.com.fiap.netgifs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class HomeController {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired	
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(HttpSession session) {
		final UserData userData = (UserData) session.getAttribute("user");
		final List<Image> images = imageRepository.findAll();
		final User user = userRepository.findById(userData.getId());
		
		final ModelAndView mv = new ModelAndView("main");
		final Map<Category, List<Image>> imageByCategory = new HashMap<>();
		final List<Category> categoryList = categoryRepository.findAll();
		
		for (final Category category : categoryList) {
			imageByCategory.put(category, imageRepository.findByCategory(category));
		}
		
		mv.addObject("images", images);
		mv.addObject("user", userData);
		mv.addObject("favorites", user.getFavorites());
		mv.addObject("categoryList", categoryRepository.findAll());
		mv.addObject("imageByCategory", imageByCategory);
		return mv;
	}
}