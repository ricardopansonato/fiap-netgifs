package br.com.fiap.netgifs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.netgifs.model.Image;
import br.com.fiap.netgifs.repository.ImageRepository;


@Controller
@RequestMapping(value = "/content")
public class ImageController {

	@Autowired
	private ImageRepository imageRepository;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView cadastrar(Image image) {
		ModelAndView mv = new ModelAndView("upload");
		mv.addObject("image", image);
		return mv;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView cadastrarAdd(final Image image) {
		String view = "upload";
		Image dbImage = imageRepository.findByDescription(image.getDescription());
		if(dbImage != null) {
			image.setMessage("Imagem já existe, favor cadatrar outra imagem!");
		} else {
			imageRepository.save(image);
			view = "redirect:/content/main"; 
		}
		final ModelAndView mv = new ModelAndView(view);
		mv.addObject("image", image);
		return mv;
	}
}