package br.com.fiap.javaadv.blog.backend;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.ProfileRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Post;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Profile;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

@SpringBootApplication
public class BackendApplication {

//	@Autowired
//	private static ProfileRepository profileRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);


		Collection<User> users = new LinkedList<>();

		users.add(new User(UUID.randomUUID(), "sample name", "sample_e mail", "S3kr3t", null, null, null));
		users.add(new User(UUID.randomUUID(), "sample name", "sample_e mail", "S3kr3t", null, null, null));
		users.add(new User(UUID.randomUUID(), "sample name", "sample_e mail", "S3kr3t", null, null, null));
		users.add(new User(UUID.randomUUID(), "sample name", "sample_e mail", "S3kr3t", null, null, null));
		users.add(new User(UUID.randomUUID(), "sample name", "sample_e mail", "S3kr3t", null, null, null));
		users.add(new User(UUID.randomUUID(), "sample name", "sample_e mail", "S3kr3t", null, null, null));
//		users.add( new Post());
		users.add(new User(UUID.randomUUID(), "sample name", "sample_e mail", "S3kr3t", null, null, null));
		users.add(new User(UUID.randomUUID(), "sample name", "sample_e mail", "S3kr3t", null, null, null));
		users.add(new User(UUID.randomUUID(), "sample name", "sample_e mail", "S3kr3t", null, null, null));
		users.add(new User(UUID.randomUUID(), "sample name", "sample_e mail", "S3kr3t", null, null, null));
		users.add(new User(UUID.randomUUID(), "sample name", "sample_e mail", "S3kr3t", null, null, null));

		for( User user : users){
			System.out.println(user);
		}



//		profileRepository.saveAll(profiles);

//		for(Object object : users){
//			User aUser = null;
//			Post post = null;
//			if( object instanceof User )
//				aUser = (User) object;
//            if( object instanceof  Post)
//				post = (Post) object;
//
//			if( aUser != null)
//				System.out.println(aUser.getEmail());
//
//			if( post != null)
//				System.out.println(post);
//		}





	}

}
