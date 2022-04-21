package com.itesm.komorebi.services;

import com.itesm.komorebi.models.Personnel;import com.itesm.komorebi.repositories.PersonnelRepository;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;import java.util.UUID;

@Service
public class PersonnelService {

    @Autowired
    PersonnelRepository supervisorRepository;

    public List<Personnel> listAll(){
        return supervisorRepository.findAll();
    }

    public Personnel findById(UUID id){
        return supervisorRepository.findById(id);
    }

}
