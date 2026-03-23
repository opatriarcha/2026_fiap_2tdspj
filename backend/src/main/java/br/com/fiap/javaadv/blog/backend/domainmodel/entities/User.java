package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name="SYSTEM_LOCAL_FUCKING_USERS")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @NotBlank(message= "O nome é obrigatorio")
    @Size(max=100, message="Name must have 100 chars")
    @Column(name="NAME", length = 100, nullable = false)
    private @Getter @Setter String name;

    @NotBlank( message = "O email é Obrigatorio")
    @Email(message="O email deve ser válido")
    @Size(max = 100, message="O email deve ter no máximo 100 caracteres")
    @Column(name="EMAIL", length = 100, nullable = false)
    private @Getter @Setter String email;

    @NotBlank(message="O password é obrigatorio")
    @Size( min=6, message="O password deve tger ao menos 6 posicoes.")
    @Column( name = "PASSWORD", length = 20, nullable = false)
    private @Getter @Setter String password;

    @OneToOne( mappedBy = "user", cascade = CascadeType.ALL)
    private @Getter @Setter Profile profile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private @Getter @Setter Set<Post> posts;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private @Getter @Setter Set<Role> roles;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}