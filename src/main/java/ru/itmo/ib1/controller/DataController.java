package ru.itmo.ib1.controller;

import ru.itmo.ib1.controller.domain.NoteDTO;
import ru.itmo.ib1.service.NotesService;
import ru.itmo.ib1.service.domain.NoteModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class DataController {

    private final NotesService service;

    @GetMapping
    public List<NoteModel> getAllNotes() {
        return service.getAllNotes();
    }

    @PostMapping
    public NoteModel createNote(@RequestBody NoteDTO dto) {
        return service.createNote(dto);
    }
}
