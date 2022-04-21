package com.itesm.komorebi.repositories;

import com.itesm.komorebi.models.Personnel;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class PersonnelRepository {

    public List<Personnel> findAll(){
        List<Personnel> supervisors = new ArrayList<>();
        supervisors.add(new Personnel("Diego Jiménez", 20, UUID.fromString("c93f017d-42e6-4855-84c3-66916aa4eb8f")));
        supervisors.add(new Personnel("Yusdivia Molina", 21, UUID.fromString("b4d91931-70fb-49c7-81e4-c2df8cfabbf7")));
        return supervisors;
    }

    public Personnel findById(UUID id){
        List<Personnel> supervisors = findAll();
        for (Personnel s: supervisors){
            if (s.getId().compareTo(id) == 0){
                return s;
            }
        }
        return null;
    }

}
