package com.works.services;

import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import com.works.utils.TinkEncDec;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class NoteService {

    final NoteRepository nRepo;
    public NoteService(NoteRepository nRepo) {
        this.nRepo = nRepo;
    }

    String key128Bit = "KaPdSgVkYp3s6v9y";

    // Note Save
    public ResponseEntity noteSave(Note note) {
        String newDetail = TinkEncDec.encrypt( key128Bit, note.getDetail(), note.getExtrakey() );
        note.setDetail( newDetail );
        note.setExtrakey("");
        nRepo.save( note );
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("note", note);
        return new ResponseEntity( hm, HttpStatus.OK );
    }


    // note read
    public ResponseEntity noteRead( Note note) {
        Map<String, Object> hm = new LinkedHashMap<>();
        Optional<Note> oNote = nRepo.findById(note.getNid());
        if (oNote.isPresent() ) {
            Note dbNote = oNote.get();
            String newDetail = TinkEncDec.decrypt( key128Bit,  dbNote.getDetail(), note.getExtrakey() );
            dbNote.setDetail( newDetail );
            if ( newDetail.equals("") ) {
                hm.put("status", false);
                hm.put("note", note);
            }else {
                hm.put("status", true);
                hm.put("note", dbNote);
            }
        }else {
            hm.put("status", false);
            hm.put("note", note);
        }
        return new ResponseEntity( hm, HttpStatus.OK );
    }


}
