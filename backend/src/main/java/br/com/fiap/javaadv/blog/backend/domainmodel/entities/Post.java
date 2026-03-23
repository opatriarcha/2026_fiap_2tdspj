package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="POSTS")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @Column( name = "TITLE", nullable = false, length = 60 )
    private @Getter @Setter String title;

    @Column( name = "Content", nullable = false, length = 255 )
    private @Getter @Setter String content;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private @Getter @Setter User user;

    @ManyToMany
    @JoinTable(
        name="POSTS_TAGS",
            joinColumns = @JoinColumn(name="post_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )
    private @Getter @Setter Set<Tag> tags = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
