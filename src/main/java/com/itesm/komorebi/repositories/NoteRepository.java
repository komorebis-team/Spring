package com.itesm.komorebi.repositories;

import com.itesm.komorebi.models.Note;
import org.springframework.stereotype.Repository;
import java.time.Instant;import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class NoteRepository {

    PersonnelRepository supervisorRepository = new PersonnelRepository();

    public List<Note> findAll(){
        List<Note> notes = new ArrayList<>();
        Date date = Date.from(Instant.now());
        notes.add(new Note("c93f017d-42e6-4855-84c3-66916aa4eb8f", date, "Buen video", UUID.fromString("d4240d20-a0ab-48f9-b6e4-a88a5ec3c084")));
        notes.add(new Note("b4d91931-70fb-49c7-81e4-c2df8cfabbf7", date, "No me gusta", UUID.fromString("d4240d20-a0ab-48f9-b6e4-a88a5ec3c084")));
        notes.add(new Note("c93f017d-42e6-4855-84c3-66916aa4eb8f", date, "Excelente!", UUID.fromString("e6d66165-5f50-45a2-aa4d-41b9f31b62d2")));
        return notes;
    }

    public List<Note> findByRecordingId(UUID id){
        List<Note> notes = this.findAll();
        List<Note> recordingNotes = new ArrayList<>();
        for (Note n:notes){
            if (n.getRecordingId() == id){
                recordingNotes.add(n);
            }
        }
        return recordingNotes;
    }

}
