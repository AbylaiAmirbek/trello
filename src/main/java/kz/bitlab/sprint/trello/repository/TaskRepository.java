package kz.bitlab.sprint.trello.repository;

import kz.bitlab.sprint.trello.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
}
