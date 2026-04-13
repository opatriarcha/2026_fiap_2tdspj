package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name="PROFILE")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @Column(name = "BIO", length = 255)
    private @Getter @Setter String bio;

    @Column(name = "PROFILE_IMAGE_URL", length = 255)
    private @Getter @Setter String imagePath;

    @OneToOne
    @JoinColumn( name = "user_id")
    private @Getter @Setter User user;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}