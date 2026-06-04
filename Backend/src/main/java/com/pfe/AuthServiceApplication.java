package com.pfe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.pfe.model.Role;
import com.pfe.model.User;
import com.pfe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class AuthServiceApplication {

	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return args -> {

			String adminEmail = "admin@gmail.com";

			// éviter duplication à chaque démarrage
			if (userRepository.findByEmail(adminEmail).isEmpty()) {

				User admin = new User();

				admin.setFirstname("Admin");
				admin.setLastname("System");
				admin.setEmail(adminEmail);

				// IMPORTANT : encoder le mot de passe
				admin.setPassword(passwordEncoder.encode("admin123"));

				admin.setTelephone("00000000");
				admin.setStatus("ACTIVE");

				admin.setEnabled(true);
				admin.setAccountLocked(false);

				admin.setRole(Role.RH);

				userRepository.save(admin);

				System.out.println("ADMIN créé avec succès");
			}
		};
	}
}