package com.itesm.komorebi.controllers;

import com.itesm.komorebi.dto.RecordingSearch;
import com.itesm.komorebi.models.Note;
import com.itesm.komorebi.models.Recording;
import com.itesm.komorebi.models.RecordingKey;
import com.itesm.komorebi.services.RecordingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@SecurityScheme(name = "komorebi_auth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")

@RestController
@RequestMapping("/v1/recording")
public class RecordingController {
    @Autowired
    RecordingService recordingService;

    @Operation(summary = "Return all the recordings", description = "Return all the recordings from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    array = @ArraySchema(schema = @Schema(implementation = Recording.class)),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "204", description = "Successful operation but there is no content",
                    content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<List<Recording>> getAllRecordings() {
        List<Recording> allRecordings = recordingService.findAll();
        if (allRecordings.isEmpty()){
            return new ResponseEntity("No content", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(allRecordings, HttpStatus.OK);
    }

    @Operation(summary = "Return a recording by ID", description = "Return the recording with using the ID indicated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = Recording.class),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "404", description = "The recording do not exist", content = @Content)
    })
    @GetMapping("/find")
    public ResponseEntity<Recording> findRecordingById(
            @Parameter(description = "The DynamoDB index to look for", required = true)@RequestBody RecordingKey recordingKey){
        Optional<Recording> findRecording = recordingService.findById(recordingKey);
        if (findRecording.isEmpty()){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(findRecording.get(), HttpStatus.OK);
    }

    @Operation(summary = "Return a list of recording by timestamp", description = "Return a list of recordings that" +
            "corresponds to the specified timestamp")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    array = @ArraySchema(schema = @Schema(implementation = Recording.class)),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "204", description = "Successful operation but there is no content",
                    content = @Content)
    })
    @GetMapping("/find/timestamp/{timestamp}")
    public ResponseEntity<List<Recording>> findRecordingByTimestamp(
            @Parameter(description = "The timestamp to look for", required = true)@PathVariable("timestamp") String timestamp)
    {
        List<Recording> recordingList = recordingService.findByTimestamp(timestamp);
        if (recordingList.isEmpty()){
            return new ResponseEntity("No content", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(recordingList, HttpStatus.OK);
    }

    @Operation(summary = "Return a list of recording by agentID", description = "Return a list of recordings that" +
            "corresponds to the specified agentID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    array = @ArraySchema(schema = @Schema(implementation = Recording.class)),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "204", description = "Successful operation but there is no content",
                    content = @Content)
    })
    @GetMapping("/find/agentId/{agentId}")
    public ResponseEntity<List<Recording>> findAllRecordingsByAgentId(
            @Parameter(description = "The agentId to look for", required = true)@PathVariable("agentId") String agentId){
        List<Recording> recordingList = recordingService.findAllByAgentId(agentId);
        if (recordingList.isEmpty()){
            return new ResponseEntity("No content", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(recordingList, HttpStatus.OK);
    }

    @Operation(summary = "Return a list of recording with the given constraints",
            description = "This method construct internally a custom query to look for all the recording that satisfy" +
                    " the give search parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    array = @ArraySchema(schema = @Schema(implementation = Recording.class)),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "204", description = "Successful operation but there is no content",
                    content = @Content)
    })
    @GetMapping("/search")
    public ResponseEntity<List<Recording>> searchRecordings(
            @Parameter(description = "The recording attributes", required = true)
            @RequestBody RecordingSearch recordingSearch ){
        List<Recording> recordingList = recordingService.customSearch(recordingSearch);
        if (recordingList.isEmpty()){
            return new ResponseEntity("No content", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(recordingList, HttpStatus.OK);
    }

    @Operation(summary = "Add a recording", description = "Receive a recording and add it to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = Recording.class),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "409", description = "The recording already exists. Use PUT to update it",
                    content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<Recording> saveRecording(
            @Parameter(description = "The recording data to be inserted", required = true)@RequestBody Recording recording)
    {
        Optional<Recording> insertedRecording = recordingService.insert(recording);
        if (insertedRecording.isEmpty()){
            return new ResponseEntity("Already exists", HttpStatus.CONFLICT);
        }
        return new ResponseEntity(insertedRecording.get(), HttpStatus.CREATED);
    }

    @Operation(summary = "Add a note", description = "Receive a note and add it to the registry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = Recording.class),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "409", description = "The recording do not exist", content = @Content)
    })
    @PutMapping("/insertNote")
    public ResponseEntity<Recording> insertNote(
            @Parameter(description = "The note to be inserted", required = true) @RequestBody Recording recording)
    {
        Optional<Recording> optionalRecording = recordingService.insertNotes(recording);
        if (optionalRecording.isEmpty()){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(optionalRecording.get(), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a recording", description = "Receive a recording and update it in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = Recording.class),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "404", description = "The recording data cannot be found", content = @Content)
    })
    @PutMapping("/save")
    public ResponseEntity<Recording> updateRecording(
            @Parameter(description = "The recoding to be updated", required = true)@RequestBody Recording recording)
    {
        Optional<Recording> updateRecording = recordingService.update(recording);
        if (updateRecording.isEmpty()){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(updateRecording.get(), HttpStatus.OK);
    }

    @Operation(summary = "Delete a recording", description = "Delete a recording with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content)
    })
    @DeleteMapping("/delete/{timestamp}/{agentId}")
    public ResponseEntity deleteRecordingByVideoId(
            @Parameter(description = "The timestampo of the recording", required = true) @PathVariable("timestamp") String timestamp,
            @Parameter(description = "The agenteId of the recording", required = true) @PathVariable("agentId") String agentId
    ){
        RecordingKey recordingKey = new RecordingKey();
        recordingKey.setAgentId(agentId);
        recordingKey.setTimestamp(timestamp);
        recordingService.deleteById(recordingKey);
        return new ResponseEntity("Changes applied", HttpStatus.OK);
    }
}

