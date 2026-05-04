package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Profile;
import br.com.fiap.javaadv.blog.backend.resources.dtos.ProfileDTO;
import br.com.fiap.javaadv.blog.backend.services.ProfileService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileResource {


    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> fetchAll(){
        return ResponseEntity.ok(
                this.profileService.fetchAll().
                        stream()
                        .map(ProfileDTO::fromEntity)
                        .collect(Collectors.toList())
        );
    }


    //HATEOAS
    @GetMapping("/find")
    public ResponseEntity<List<ProfileDTO>> fetchAll(Pageable pageable){
        return ResponseEntity.ok(
                this.profileService.fetchAll(pageable)
                        .stream()
                        .map(ProfileDTO::fromEntity)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}") //http://localhost:8080/api/v1/profiles/{id}
    public ResponseEntity<ProfileDTO> fetchById( @PathVariable UUID id ){
        return this.profileService.fetchById(id)
                .map(profile -> ResponseEntity.ok(ProfileDTO.fromEntity(profile)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> create( @Valid @RequestBody ProfileDTO profileDTO ){
        Profile profile = ProfileDTO.fromDTO(profileDTO);
        Profile savedProfile = this.profileService.create(profile);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProfile.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(ProfileDTO.fromEntity(savedProfile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable UUID id, @Valid @RequestBody ProfileDTO profileDto){
            return this.profileService.update(id, ProfileDTO.fromDTO(profileDto))
                    .map(profile ->
                            ResponseEntity.ok(ProfileDTO.fromEntity(profile)))
                    .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.profileService.existsById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();

    }

    @PatchMapping
    public ResponseEntity<ProfileDTO> patch( @Valid @RequestBody ProfileDTO profileDto){
        return this.profileService.partialUpdate(ProfileDTO.fromDTO(profileDto))
                .map(profile ->
                        ResponseEntity.ok(ProfileDTO.fromEntity(profile)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    //http://localhost:8080/api/v1/profiles/paged-default?pageNumber=1&pageSize=10&sortField=bio&sortDirection=asc
    @GetMapping("/paged-default")
    public ResponseEntity<Page<ProfileDTO>> findAllPagedDefault(@RequestParam int pageNumber, @RequestParam int pageSize,
                                                          @RequestParam String sortField, @RequestParam String sortDirection, Sort sort){

        Pageable pageable = null;
        List<String> possibleParameters = Arrays.asList("asc", "desc");

        if( sortDirection == null || sortDirection.isEmpty() || !possibleParameters.contains(sortDirection))
            throw new IllegalArgumentException("Sorting cannot be null or empty outside domain [asc , desc]");

        if( sortDirection.equalsIgnoreCase("asc"))
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).ascending());
        else if( sortDirection.equalsIgnoreCase("desc"))
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).ascending());

        Page<ProfileDTO> profiles = this.profileService.fetchAll(pageable)
                .map(ProfileDTO::fromEntity);

        return ResponseEntity.ok(profiles);

    }


}
