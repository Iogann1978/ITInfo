package ru.home.itinfo.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tag;
}
