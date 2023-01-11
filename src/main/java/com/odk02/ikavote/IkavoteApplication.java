package com.odk02.ikavote;

import com.odk02.ikavote.controllers.AuthController;
import com.odk02.ikavote.messages.request.SignupRequest;
import com.odk02.ikavote.models.ERole;
import com.odk02.ikavote.models.Role;
import com.odk02.ikavote.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

import static com.odk02.ikavote.models.ERole.SUPERADMIN;

@SpringBootApplication
public class IkavoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(IkavoteApplication.class, args);
	}

  @Bean
  CommandLineRunner start(RoleRepository roleRepository, AuthController authController) {

    return args -> {

      if (roleRepository.findByName(SUPERADMIN).isEmpty()) roleRepository.save(new Role(SUPERADMIN));

      if (roleRepository.findByName(ERole.ADMIN).isEmpty()) roleRepository.save(new Role(ERole.ADMIN));

      if (roleRepository.findByName(ERole.JURY).isEmpty()) roleRepository.save(new Role(ERole.JURY));

      if (roleRepository.findByName(ERole.USER).isEmpty()) roleRepository.save(new Role(ERole.USER));

      Set<String> roles = new HashSet<>();
      roles.add("superadmin");
      SignupRequest defaultuser = new SignupRequest();
      defaultuser.setUsername("khadija");
      defaultuser.setEmail("khadysamsup@gmail.com");
      defaultuser.setPassword("1234567");
      defaultuser.setRole(roles);
      authController.registerDefaultUser(defaultuser);

    };
  }
}
