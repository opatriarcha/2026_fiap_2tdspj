package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.ProfileRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Profile;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional( propagation = Propagation.REQUIRED)
public class ProfileServiceImp implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Profile create(Profile profile){
        return this.profileRepository.save( profile );
    }

    @Override
    public Optional<Profile>  update(UUID id, Profile profile){
        if( this.profileRepository.existsById( id ) ){
            Optional<Profile> aProfile = this.profileRepository.findById(id);
            profile.setId(id);
            this.profileRepository.save(profile);
            return Optional.of(profile);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Profile> partialUpdate(Profile profile){
        Optional<Profile> fromDatabase = this.profileRepository.findById(profile.getId());
        if(fromDatabase.isEmpty())
            return fromDatabase;

        if( profile.getUser() != null && !profile.getUser().equals(fromDatabase.get().getUser()))
            fromDatabase.get().setUser(profile.getUser());

        if(profile.getImagePath() != null && !profile.getImagePath().equals(fromDatabase.get().getImagePath()))
            fromDatabase.get().setImagePath(profile.getImagePath());

        if(profile.getBio() != null && !profile.getBio().equals(fromDatabase.get().getBio()))
            fromDatabase.get().setBio(profile.getBio());

        return Optional.of(this.profileRepository.save( fromDatabase.get()));
    }

    @Override
    public void delete(Profile profile){
        this.profileRepository.delete(profile);
    }

    @Override
    public void delete(UUID id){
        this.profileRepository.deleteById(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<Profile> fetchAll(){
        return this.profileRepository.findAll();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public Optional<Profile> fetchById(final UUID id){
        return this.profileRepository.findById(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public Profile findByUser(final User user){
        return this.profileRepository.findByUser( user );
    }

    @Override
    public Page<Profile> fetchAll(Pageable pageable) {
        return this.profileRepository.findAll(pageable);
    }

    @Override
    public boolean existsById(UUID id){
        return this.profileRepository.existsById(id);
    }


}
