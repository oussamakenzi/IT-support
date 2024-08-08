package supportIT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import supportIT.enums.Role;
import supportIT.model.Admin;
import supportIT.repository.AdminRepository;

@SpringBootApplication
public class SupportItApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupportItApplication.class, args);
	}

	@Autowired
	AdminRepository adminRepository;

	@Bean
	public CommandLineRunner startup() {

		return args -> {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if (adminRepository.count() == 0) {
				Admin admin = new Admin();
				admin.setFullname("admin");
				admin.setEmail("admin@gmail.com");
				admin.setPassword(passwordEncoder.encode("admin"));
				admin.setRole(Role.ADMIN);

				adminRepository.save(admin);
				System.out.println("Admin  created avec success");
			} else {
				System.out.println("Admin  already exists.");
			}

		};

	}



}
