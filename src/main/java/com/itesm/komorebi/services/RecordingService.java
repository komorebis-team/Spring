package com.itesm.komorebi.services;

import com.itesm.komorebi.models.Recording;
import com.itesm.komorebi.models.RecordingKey;
import com.itesm.komorebi.repositories.RecordingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordingService {

    @Autowired
    RecordingRepository recordingRepository;

    private boolean existByTimestamp(String timestamp){return recordingRepository.existsByTimestamp(timestamp);}
    private boolean existByAgentId(String agentId){return recordingRepository.existsByAgentId(agentId);}
    //Create
    public Optional<Recording> insert(Recording recording){
        if (existByAgentId(recording.getAgentId()) && existByTimestamp(recording.getTimestamp())){
            return Optional.empty();
        }
        return Optional.ofNullable(recordingRepository.save(recording));
    }
    //Retrieve
    public List<Recording> findAll() {return recordingRepository.findAll();}
    public Optional<Recording> findById(RecordingKey recordingKey){return recordingRepository.findById(recordingKey);}
    public Recording findByTimestamp(String timestamp){return  recordingRepository.findByTimestamp(timestamp);}
    //Update
    public Optional<Recording> update(Recording recording){
        if (existByAgentId(recording.getAgentId()) && existByTimestamp(recording.getTimestamp())){
            return Optional.ofNullable(recordingRepository.save(recording));
        }
        return Optional.empty();
    }
    //Delete
    public void delete(Recording recording){
        recordingRepository.delete(recording);
    }
}
