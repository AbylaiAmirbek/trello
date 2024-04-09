package kz.bitlab.sprint.trello.service;

import kz.bitlab.sprint.trello.model.TaskCategories;
import kz.bitlab.sprint.trello.model.Tasks;
import kz.bitlab.sprint.trello.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<TaskCategories> getCategories(){
        return categoryRepository.findAll();
    }

    public TaskCategories getCategory(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public TaskCategories addCategory(TaskCategories cat){
        return categoryRepository.save(cat);
    }

    public TaskCategories saveCategory(TaskCategories cat){
        return categoryRepository.save(cat);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

}
