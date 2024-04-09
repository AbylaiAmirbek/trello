package kz.bitlab.sprint.trello.repository;

import kz.bitlab.sprint.trello.model.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<TaskCategories, Long> {
}
