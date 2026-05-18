package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Profile;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.User;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    public Profile findByUser(User user);

    @QueryHints(@QueryHint(name="org.hibernate.cacheable", value="true"))
    public Profile findAllByBioContaining(String bio );
}
