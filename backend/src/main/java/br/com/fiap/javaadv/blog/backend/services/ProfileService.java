package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Profile;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileService {
    Profile create(Profile profile);

    Profile update(Profile profile);

    Profile partialUpdate(Profile profile);

    void delete(Profile profile);

    void delete(UUID id);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    List<Profile> fetchAll();

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    Optional<Profile> fetchById(UUID id);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    Profile findByUser(User user);
}
