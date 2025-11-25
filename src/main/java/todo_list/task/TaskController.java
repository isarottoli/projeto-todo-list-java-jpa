package todo_list.task;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        return taskRepository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody TaskModel taskModel){
        taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskModel> update(@RequestBody TaskModel taskModel, @PathVariable UUID id)
    {
        TaskModel updateTask = taskRepository.findById(id).map(task -> {
                    task.setDescription(taskModel.getDescription());
                    task.setTitle(taskModel.getTitle());

            return task;
                }
        ).orElse(null);

        if (updateTask == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(taskRepository.save(updateTask));
    }
}
