package ia.atarax.administrador;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class AdministradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministradorApplication.class, args);
	}

}
