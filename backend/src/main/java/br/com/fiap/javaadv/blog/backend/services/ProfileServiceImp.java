package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.ProfileRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Profile;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.User;
import lombok.RequiredArgsConstructor;
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
    public Profile update(Profile profile){
        return this.profileRepository.save( profile );
    }

    @Override
    public Profile partialUpdate(Profile profile){
        return this.profileRepository.save( profile );
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







}
