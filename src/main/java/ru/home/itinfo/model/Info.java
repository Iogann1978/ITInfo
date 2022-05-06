package ru.home.itinfo.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String title;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "PUBLISHER_ID")
    protected Publisher publisher;
    protected Rate rate;
    protected State state;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "BOOK_TAG", joinColumns = {@JoinColumn(name = "BOOK_ID")}, inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    protected Set<Tag> tags;
    protected int year;
    @OneToMany(cascade = CascadeType.ALL)
    protected List<Descript> descripts;
    @OneToOne(cascade = CascadeType.ALL)
    protected InfoFile file;
}
