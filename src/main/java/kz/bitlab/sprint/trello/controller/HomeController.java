package kz.bitlab.sprint.trello.controller;

import kz.bitlab.sprint.trello.model.Comments;
import kz.bitlab.sprint.trello.model.Folders;
import kz.bitlab.sprint.trello.model.TaskCategories;
import kz.bitlab.sprint.trello.model.Tasks;
import kz.bitlab.sprint.trello.repository.CategoryRepository;
import kz.bitlab.sprint.trello.repository.CommentsRepository;
import kz.bitlab.sprint.trello.repository.FolderRepository;
import kz.bitlab.sprint.trello.repository.TaskRepository;
import kz.bitlab.sprint.trello.service.CategoryService;
import kz.bitlab.sprint.trello.service.FolderService;
import kz.bitlab.sprint.trello.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CommentsRepository commentsRepository;

    private final FolderService folderService;
    private final TaskService taskService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("folders", folderService.getFolders());
        return "index";
    }

    @PostMapping("/add-folder")
    public String addFolder(Folders folder){
        folderService.addFolder(folder);
        return "redirect:/";
    }
    @GetMapping("/details/{folderId}")
    public String folderDetails(@PathVariable(name = "folderId") Long id, Model model){
        Folders folder = folderService.getFolder(id);
        model.addAttribute("folderById", folder);

        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("tasks", taskService.getTasks());
        return "details";
    }

    @GetMapping("/detailsTasks/{taskId}")
    public String taskDetails(@PathVariable(name = "taskId") Long id, Model model){

        Tasks tasks = taskService.getTask(id);
        model.addAttribute("taskById", tasks);
        model.addAttribute("comments", commentsRepository.findAllByOrderByIdDesc());
        return "detailsTasks";
    }

    @GetMapping("/detailsCats/{catId}")
    public String catDetails(@PathVariable(name = "catId") Long id, Model model){

        TaskCategories categories = categoryService.getCategory(id);
        model.addAttribute("categoryById", categories);
        return "detailsCategory";
    }

    @PostMapping("/add-task")
    public String addTask(Tasks task, @RequestParam(name = "folder_id") Long folderId){
        task.setStatus(0);
        Folders folder = folderService.getFolder(folderId);
        task.setFolder(folder);
        taskService.addTask(task);
        return "redirect:/details/" + folderId;
    }
    @PostMapping("/add-comment")
    public String addComment(Comments comment, @RequestParam(name = "task_id") Long taskId){
        Tasks task = taskService.getTask(taskId);
        comment.setTask(task);
        commentsRepository.save(comment);
        return "redirect:/detailsTasks/" + taskId;
    }

    @PostMapping("/save-task")
    public String saveTask(Tasks task, @RequestParam(name = "folder_id") Long folderId){

        Folders folder = folderService.getFolder(folderId);
        task.setFolder(folder);
        taskService.saveTask(task);
        return "redirect:/details/" + folderId;
    }

    @PostMapping(value = "/save-category")
    public String saveCategory(TaskCategories categories){
        categoryService.saveCategory(categories);
        return "redirect:/categories-page";
    }

    @PostMapping("/add-category")
    public String addCategory(@RequestParam(name = "folder_id") Long folderId,
                              @RequestParam(name = "category_id") Long categoryId){
        Folders folders = folderService.getFolder(folderId);
        TaskCategories categories = categoryService.getCategory(categoryId);

        if(folders.getTaskCategories()!=null && folders.getTaskCategories().size()>0){
            if(!folders.getTaskCategories().contains(categories)) {
                folders.getTaskCategories().add(categories);
            }
        }else {
            List<TaskCategories> categoriesList = new ArrayList<>();
            categoriesList.add(categories);
            folders.setTaskCategories(categoriesList);
        }

        folderService.saveFolder(folders);
        return "redirect:/details/" + folderId;
    }

    @PostMapping("/drop-category")
    public String dropCategory(@RequestParam(name = "folder_id") Long folderId,
                              @RequestParam(name = "category_id") Long categoryId){
        Folders folders = folderService.getFolder(folderId);
        TaskCategories categories = categoryService.getCategory(categoryId);

        if(folders.getTaskCategories()!=null && folders.getTaskCategories().size()>0){

            folders.getTaskCategories().remove(categories);

        }

        folderService.saveFolder(folders);
        return "redirect:/details/" + folderId;
    }

    @PostMapping("/delete-task")
    public String deleteTask(@RequestParam(name = "id") Long id,
                             @RequestParam(name = "folder_id") Long folderId){
        taskService.deleteTask(id);
        return "redirect:/details/" + folderId;
    }
    @PostMapping("/delete-folder")
    public String deleteFolder(@RequestParam(name = "folder_id") Long id){
        folderService.deleteFolderById(id);
        return "redirect:/";
    }

    @PostMapping("/delete-category")
    public String deleteCategory(@RequestParam(name = "id") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories-page";
    }

    @GetMapping("/categories-page")
    public String categoryPage(Model model){
        model.addAttribute("cats", categoryService.getCategories());
        return "categoryPage";
    }

    @PostMapping("/add-category-db")
    public String addNewCategory(TaskCategories category){
        categoryService.addCategory(category);
        return "redirect:/categories-page";
    }

}
