package ru.itmo.ib1.repository;

import ru.itmo.ib1.repository.domain.NoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<NoteEntity, Long> { }
