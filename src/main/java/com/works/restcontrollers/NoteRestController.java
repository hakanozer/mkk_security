package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/note")
public class NoteRestController {


    final NoteService nService;
    public NoteRestController(NoteService nService) {
        this.nService = nService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Note note) {
        return nService.noteSave( note );
    }

    @PostMapping("/read")
    public ResponseEntity read(@RequestBody Note note) {
        return nService.noteRead( note );
    }

}
