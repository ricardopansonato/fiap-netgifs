package br.com.fiap.netgifs.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.fiap.netgifs.model.User;
import br.com.fiap.netgifs.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		User admin = new User();
		admin.setAdmin("YES");
		admin.setGender("M");
		admin.setName("Administrador");
		admin.setUsername("admin");
		admin.setPassword("admin");
		userRepository.save(admin);
	}

}
