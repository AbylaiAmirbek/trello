package kz.bitlab.sprint.trello.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.config.Task;

@Entity
@Table(name = "t_comments")
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "commentary")
    private String comment;

    @ManyToOne
    private Tasks task;
}

