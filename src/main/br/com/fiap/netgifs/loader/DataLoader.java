package br.com.fiap.netgifs.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.fiap.netgifs.model.Image;
import br.com.fiap.netgifs.model.User;
import br.com.fiap.netgifs.repository.ImageRepository;
import br.com.fiap.netgifs.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {

	private UserRepository userRepository;

	private ImageRepository imageRepository;

	@Autowired
	public DataLoader(UserRepository userRepository, ImageRepository imageRepository) {
		this.userRepository = userRepository;
		this.imageRepository = imageRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		final User admin = new User();
		admin.setAdmin("Y");
		admin.setGender("M");
		admin.setName("Administrador");
		admin.setUsername("admin");
		admin.setPassword("admin");
		userRepository.save(admin);

		Image image = new Image();
		image.setDescription("Legal");
		image.setGenre("Comédia");
		image.setLanguage("Inglês");
		image.setRatings("Livre");
		image.setUrl("https://media0.giphy.com/avatars/100soft/WahNEDdlGjRZ.gif");
		imageRepository.save(image);
		
		image = new Image();
		image.setDescription("Simba");
		image.setGenre("Comédia");
		image.setLanguage("Inglês");
		image.setRatings("Livre");
		image.setUrl("https://img.buzzfeed.com/buzzfeed-static/static/2014-07/18/10/enhanced/webdr09/anigif_enhanced-buzz-22799-1405693809-7.gif");
		imageRepository.save(image);
		
		image = new Image();
		image.setDescription("Pomba");
		image.setGenre("Comédia");
		image.setLanguage("Português");
		image.setRatings("Livre");
		image.setUrl("https://media4.giphy.com/avatars/nikdudukovic/ylDRTR05sy6M.gif");
		imageRepository.save(image);
	}

}
