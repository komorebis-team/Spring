package com.itesm.komorebi.repositories;
import com.itesm.komorebi.models.Recording;
import com.itesm.komorebi.models.RecordingKey;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@EnableScan
public interface RecordingRepository extends CrudRepository<Recording, RecordingKey> {
    List<Recording> findAll();
    Recording save(Recording recording);
    Optional<Recording> findById(RecordingKey recordingKey);
    void deleteById(RecordingKey recording);

    //Attribute name = "timestamp"
    List<Recording> findAllByTimestamp(String timestamp);
    boolean existsByTimestamp(String timestamp);

    //Attribute name = "agentId"
    List<Recording> findAllByAgentId(String agentId);
    boolean existsByAgentId(String agentId);

    //Atrribute name = "category"
    List<Recording> findAllByCategory(String category);
    boolean existsByCategory(String category);

    //Atrribute name = "tags"
    List<Recording> findAllByTags(Set<String> tags);
    boolean existsByTags(Set<String> tags);
}
