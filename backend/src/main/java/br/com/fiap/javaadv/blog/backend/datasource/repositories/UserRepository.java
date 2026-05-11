package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface  UserRepository  extends JpaRepository<User, UUID> {

    public Optional<User> findByEmail(String email);

}
