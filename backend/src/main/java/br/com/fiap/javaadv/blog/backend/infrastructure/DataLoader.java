package br.com.fiap.javaadv.blog.backend.infrastructure;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.ProfileRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.UserRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Profile;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.LinkedList;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(ProfileRepository profileRepository, UserRepository userRepository){
        return args -> {

            User admin = User.builder()
                    .email("admin@gmailcom")
                    .name("TIO FULADO")
                    .password("123456789DEIZ")
                    .build();

            User zemane = User.builder()
                    .email("zemane@gmailcom")
                    .name("ZE MANE")
                    .password("123456789DEIZDINOVO")
                    .build();

            userRepository.save(admin);
            userRepository.save(zemane);

            Collection<Profile> profiles = new LinkedList<>();
            profiles.add( Profile.builder()
                    .bio("Some Bio")
                    .imagePath("some image path")
                    .user( admin )
                    .build());

            profiles.add( Profile.builder()
                    .bio("Some Bio2")
                    .imagePath("some image path2")
                    .user( zemane)
                    .build());

            profileRepository.saveAll(profiles);
        };
    }
}
