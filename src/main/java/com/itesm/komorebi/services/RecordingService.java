package com.itesm.komorebi.services;

import com.itesm.komorebi.dto.RecordingSearch;
import com.itesm.komorebi.models.Note;
import com.itesm.komorebi.models.Recording;
import com.itesm.komorebi.models.RecordingKey;
import com.itesm.komorebi.repositories.RecordingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Recording> findByTimestamp(String timestamp){return  recordingRepository.findAllByTimestamp(timestamp);}
    public List<Recording> findAllByAgentId(String agentId){return recordingRepository.findAllByAgentId(agentId);}
    public List<Recording> customSearch(RecordingSearch recordingSearch){return findAll();}
    //Update
    public Optional<Recording> update(Recording recording){
        if (existByAgentId(recording.getAgentId()) && existByTimestamp(recording.getTimestamp())){
            return Optional.ofNullable(recordingRepository.save(recording));
        }
        return Optional.empty();
    }
    public Optional<Recording> insertNotes(Note note, String agentId, String timestamp){
        RecordingKey recordingKey = new RecordingKey();
        recordingKey.setAgentId(agentId);
        recordingKey.setTimestamp(timestamp);
        Optional<Recording> optionalRecording = recordingRepository.findById(recordingKey);
        if (optionalRecording.isEmpty()){
            return Optional.empty();
        }
        List<Note> notes = optionalRecording.get().getNotes();
        if (notes == null){
            notes = new ArrayList<Note>();
        }
        notes.add(note);
        optionalRecording.get().setNotes(notes);
        return update(optionalRecording.get());
    }
    //Delete
    public void deleteById(RecordingKey recordingKey){
        recordingRepository.deleteById(recordingKey);
    }
}
