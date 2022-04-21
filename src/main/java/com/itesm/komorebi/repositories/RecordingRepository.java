package com.itesm.komorebi.repositories;
import com.itesm.komorebi.models.Recording;
import com.itesm.komorebi.models.RecordingKey;import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableScan
public interface RecordingRepository extends CrudRepository<Recording, RecordingKey> {
    List<Recording> findAll();
    Recording save( Recording R);
}
