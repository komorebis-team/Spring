package com.itesm.komorebi.services;

import com.itesm.komorebi.models.Recording;
import com.itesm.komorebi.repositories.RecordingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordingService {

    @Autowired
    RecordingRepository recordingRepository;

    public List<Recording> findAll() {
        return recordingRepository.findAll();
    }

    public Recording saveRecording(Recording R){return recordingRepository.save(R);}
}
