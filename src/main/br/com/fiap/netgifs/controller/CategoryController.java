package br.com.fiap.netgifs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.netgifs.model.Category;
import br.com.fiap.netgifs.model.Image;
import br.com.fiap.netgifs.repository.CategoryRepository;
import br.com.fiap.netgifs.vo.UserData;

@Controller
@RequestMapping(value = "/content")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ModelAndView cadastrar(final HttpSession session, final Category category) {
		final UserData userData = (UserData) session.getAttribute("user");
		final ModelAndView mv = new ModelAndView("category");
		mv.addObject("category", category);
		mv.addObject("user", userData);
		return mv;
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public ModelAndView cadastrarAdd(final HttpSession session, final Category category) {
		final UserData userData = (UserData) session.getAttribute("user");
		Image dbCategory = categoryRepository.findByName(category.getName());
		if(dbCategory != null) {
			category.setMessage("Categoria já existe, favor cadastrar outra!");
		} else {
			category.setMessage("Categoria realizado com sucesso!");
			categoryRepository.save(category);
		}
		final ModelAndView mv = new ModelAndView("category");
		mv.addObject("category", category);
		mv.addObject("user", userData);
		return mv;
	}
}
