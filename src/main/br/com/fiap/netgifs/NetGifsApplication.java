package br.com.fiap.netgifs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan({"br.com.fiap.netgifs"})
public class NetGifsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetGifsApplication.class, args);
	}
}
