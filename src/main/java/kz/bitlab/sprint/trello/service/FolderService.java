package kz.bitlab.sprint.trello.service;

import kz.bitlab.sprint.trello.model.Folders;
import kz.bitlab.sprint.trello.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {
    private final FolderRepository folderRepository;

    public List<Folders> getFolders(){
        return folderRepository.findAll();
    }
    public Folders addFolder(Folders folder){
        return folderRepository.save(folder);
    }

    public Folders saveFolder(Folders folder){
        return folderRepository.save(folder);
    }

    public Folders getFolder(Long id){
        return folderRepository.findById(id).orElse(null);
    }

    public void deleteFolderById(Long id){
        folderRepository.deleteTasksByFolderId(id);
        folderRepository.deleteFolderById(id);
    }

}
