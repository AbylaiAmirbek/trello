package kz.bitlab.sprint.trello.repository;

import kz.bitlab.sprint.trello.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByOrderByIdDesc();
}
