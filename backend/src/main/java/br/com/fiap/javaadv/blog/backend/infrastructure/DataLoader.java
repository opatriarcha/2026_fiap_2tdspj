package br.com.fiap.javaadv.blog.backend.infrastructure;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.ProfileRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.RoleRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.UserRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Profile;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Role;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(ProfileRepository profileRepository,
                               UserRepository userRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder){
        return args -> {

            User admin = User.builder()
                    .email("admin@gmail.com")
                    .name("TIO FULANO")
                    .password(passwordEncoder.encode("admin123"))
                    .build();

            User zemane = User.builder()
                    .email("zemane@gmail.com")
                    .name("ZE MANE")
                    .password(passwordEncoder.encode("123"))
                    .build();

            Profile userProfile = new Profile();
            userProfile.setBio("SOME ADMIN FUCKIN BIO");
            userProfile.setUser(admin);
            admin.setProfile(userProfile);

            Profile genericProfile = new Profile();
            genericProfile.setBio("SOME GENERIC FUCKIN BIO");
            genericProfile.setUser(zemane);
            zemane.setProfile(genericProfile);

            profileRepository.save(userProfile);

            profileRepository.save(genericProfile);


            userRepository.save(admin);
            userRepository.save(zemane);

//            Collection<Profile> profiles = new LinkedList<>();
//            profiles.add( Profile.builder()
//                    .bio("Some Bio")
//                    .imagePath("some image path")
//                    .user( admin )
//                    .build());
//
//            profiles.add( Profile.builder()
//                    .bio("Some Bio2")
//                    .imagePath("some image path2")
//                    .user( zemane)
//                    .build());
//
//            profileRepository.saveAll(profiles);
//
//            Role role = new Role();
//            role.setName("ROLE_USER");
//
//            Role roleAdmin = new Role();
//            role.setName("ROLE_ADMIN");
//
//            roleRepository.save(role);
//            roleRepository.save(roleAdmin);

//            User user = new User();
////            user.setId(UUID.randomUUID());
//            user.setName("SAMPLE USER");
//            user.setPassword( passwordEncoder.encode("admin123"));
//            user.setEmail("sampleuser@gmail.com");
//
//            user.setRoles(Set.of(role));
//
//            Profile userProfile = new Profile();
//            userProfile.setBio("SOME AWSOME FUCKIN BIO");
//            userProfile.setUser(user);
//            user.setProfile(userProfile);
//
//            profileRepository.save(userProfile)       ;
//            userRepository.save(user);


        };
    }

//    @Bean
//    CommandLineRunner initDataBIGGGGER(ProfileRepository profileRepository, UserRepository userRepository) {
//        return args -> {
//
//            int TOTAL = 10_000;
//            List<User> users = new ArrayList<>(TOTAL);
//
//            for (int i = 1; i <= TOTAL; i++) {
//                users.add(User.builder()
//                        .email("user" + i + "@gmail.com")
//                        .name("User " + i)
//                        .password("password" + i)
//                        .build());
//            }
//
//            userRepository.saveAll(users);
//
//            List<Profile> profiles = new ArrayList<>(TOTAL);
//
//            for (int i = 0; i < TOTAL; i++) {
//                profiles.add(Profile.builder()
//                        .bio("Bio of user " + (i + 1))
//                        .imagePath("/images/user" + (i + 1) + ".png")
//                        .user(users.get(i))
//                        .build());
//            }
//
//            profileRepository.saveAll(profiles);
//        };
//    }
}
