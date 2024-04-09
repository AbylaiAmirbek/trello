package kz.bitlab.sprint.trello.service;

import kz.bitlab.sprint.trello.model.Tasks;
import kz.bitlab.sprint.trello.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Tasks> getTasks(){
        return taskRepository.findAll();
    }

    public Tasks getTask(Long id){
        return taskRepository.findById(id).orElse(null);
    }

    public Tasks addTask(Tasks task){
        return taskRepository.save(task);
    }

    public Tasks saveTask(Tasks task){
        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

}
