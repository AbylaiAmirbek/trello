package kz.bitlab.sprint.trello.repository;

import jakarta.transaction.Transactional;
import kz.bitlab.sprint.trello.model.Folders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FolderRepository extends JpaRepository<Folders, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Tasks t WHERE t.folder.id = :folderId")
    void deleteTasksByFolderId(Long folderId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Folders f WHERE f.id = :folderId")
    void deleteFolderById(Long folderId);
}
