package com.itesm.komorebi.repositories;
import com.itesm.komorebi.models.Recording;
import com.itesm.komorebi.models.RecordingKey;import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableScan
public interface RecordingRepository extends CrudRepository<Recording, RecordingKey> {
    List<Recording> findAll();
    Recording save(Recording recording);
    Optional<Recording> findById(RecordingKey recordingKey);
    void delete(Recording recording);

    //Attribute name = "timestamp"
    Recording findByTimestamp(String timestamp);
    boolean existsByTimestamp(String timestamp);

    //Attribute name = "agentId"
    Recording findAllByAgentId(String agentId);
    boolean existsByAgentId(String agentId);
}
