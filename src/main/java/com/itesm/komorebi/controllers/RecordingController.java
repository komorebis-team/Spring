package com.itesm.komorebi.controllers;

import com.itesm.komorebi.models.Recording;
import com.itesm.komorebi.models.RecordingKey;
import com.itesm.komorebi.services.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/recording")
public class RecordingController {
    @Autowired
    RecordingService recordingService;

    @GetMapping("/all")
    public ResponseEntity<List<Recording>> getAllRecordings() {
        List<Recording> allRecordings = recordingService.findAll();
        if (allRecordings.isEmpty()){
            return new ResponseEntity("Not found data", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(allRecordings, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Recording> saveRecording(@RequestBody Recording recording){
        Optional<Recording> insertedRecording = recordingService.insert(recording);
        if (insertedRecording.isEmpty()){
            return new ResponseEntity("Already exists", HttpStatus.CONFLICT);
        }
        return new ResponseEntity(insertedRecording.get(), HttpStatus.CREATED);
    }

    @PutMapping("/save")
    public ResponseEntity<Recording> updateRecording(@RequestBody Recording recording){
        Optional<Recording> updateRecording = recordingService.update(recording);
        if (updateRecording.isEmpty()){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(updateRecording.get(), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<Recording> findRecordingById(@RequestBody RecordingKey recordingKey){
        Optional<Recording> findRecording = recordingService.findById(recordingKey);
        if (findRecording.isEmpty()){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(findRecording.get());
    }

    @GetMapping("/find/timestamp/{timestamp}")
    public Recording findRecordingByTimestamp(@PathVariable("timestamp") String timestamp){
        return recordingService.findByTimestamp(timestamp);
    }

    @GetMapping("/find/agentId/{agentId}")
    public List<Recording> findAllRecordingsByAgentId(@PathVariable("agentId") String agentId){
        return recordingService.findAllByAgentId(agentId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteRecordingByVideoId(@RequestBody RecordingKey recordingKey){
        recordingService.deleteById(recordingKey);
        return new ResponseEntity("Changes applied", HttpStatus.OK);
    }
}

