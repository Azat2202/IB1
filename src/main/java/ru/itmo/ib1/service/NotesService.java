package ru.itmo.ib1.service;

import ru.itmo.ib1.controller.domain.NoteDTO;
import ru.itmo.ib1.repository.DataRepository;
import ru.itmo.ib1.repository.domain.NoteEntity;
import ru.itmo.ib1.service.domain.NoteModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final DataRepository repository;
    private final ModelMapper mapper;

    public List<NoteModel> getAllNotes() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(entity -> mapper.map(entity, NoteModel.class))
                .collect(Collectors.toList());
    }

    public NoteModel createNote(NoteDTO dto) {
        NoteEntity entity = mapper.map(dto, NoteEntity.class);
        NoteEntity saved = repository.save(entity);
        return mapper.map(saved, NoteModel.class);
    }
}
