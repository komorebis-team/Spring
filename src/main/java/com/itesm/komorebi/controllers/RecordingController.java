package com.itesm.komorebi.controllers;

import com.itesm.komorebi.models.Recording;
import com.itesm.komorebi.services.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/recording")
public class RecordingController {
    @Autowired
    RecordingService recordingService;

    @GetMapping("/all")
    public List<Recording> getAllRecordings() {
        return recordingService.findAll();
    }


    @PostMapping("/save")
    public Recording saveRecording(@RequestBody Recording R){
        System.out.println(R.getVideo_id());
        System.out.println(R.getTimestamp());
        System.out.println(R.getAgent());
        System.out.println(R.getSuccesfulOutcome());
        System.out.println(R.getCategory());
        System.out.println(R.getDuration());
        System.out.println(R.getTags());
        System.out.println(R.getNotes());
        System.out.println(R.getCustomer());
        System.out.println(R.getExternal_id());
        return recordingService.saveRecording(R);
    }
}

