package ru.itmo.ib1.repository.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("NOTES")
public class NoteEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("header")
    private String header;
    @Column("note_text")
    private String text;
}
