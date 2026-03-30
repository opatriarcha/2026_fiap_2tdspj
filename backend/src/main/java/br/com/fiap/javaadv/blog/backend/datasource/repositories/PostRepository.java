package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    Page<Post> findByTitleContainingIgnoreCase(final String title, Pageable pageable);
    Page<Post> findByTags_NameContainingIgnoreCase(final String tagName, Pageable pageable);
    List<Post> findByUserId(UUID userID);
}
