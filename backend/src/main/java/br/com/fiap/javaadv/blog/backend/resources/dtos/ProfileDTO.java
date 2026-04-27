package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Profile;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {

    private @Getter @Setter UUID id;

    @NotBlank( message="A Bio é obrigatória.")
    @Size(min=30, max=255, message="Tamanho do texto da bio errôneo. Deve estar entre 30 e 255 caracteres.")
    private @Getter @Setter String bio;

    private @Getter @Setter String imagePath;

    private @Getter @Setter UUID userId;

    public static ProfileDTO fromEntity( final Profile profile ) {
        return ProfileDTO.builder()
                .id(profile.getId())
                .bio(profile.getBio())
                .imagePath(profile.getImagePath())
                .userId(profile.getUser() != null ? profile.getUser().getId() : null)
                .build();
    }

    public static Profile fromDTO( final ProfileDTO dto ){
        return Profile.builder()
                .id(dto.getId())
                .bio(dto.getBio())
                .imagePath( dto.getImagePath())
                //.user()
                .build();
    }


}
