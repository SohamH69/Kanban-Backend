package todoKanban.kanban.controller;

import todoKanban.kanban.entity.Task;
import todoKanban.kanban.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTaskByUserId(userId));
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createTask(@PathVariable Long userId, @RequestBody Task task){
        try{
            Task createdTask = taskService.createTask(userId, task);
            return ResponseEntity.ok(createdTask);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId){
        try{
            taskService.deleteTask(taskId);
            return ResponseEntity.ok("Task deleted successfully!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{taskId}/title")
    public ResponseEntity<?> updateTaskTitle(@PathVariable Long taskId, @RequestBody Map<String, String> payload) {
        try {
            String newTitle = payload.get("title");
            Task updatedTask = taskService.updateTaskTitle(taskId, newTitle);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{taskId}/lock")
    public ResponseEntity<?> toggleTaskLock(@PathVariable Long taskId){
        try{
            Task updatedTask = taskService.toggleTaskLock(taskId);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{taskId}/color")
    public ResponseEntity<?> cycleTaskColor(@PathVariable Long taskId, @RequestBody Map<String, String> payload){
        try{
            String newColor = payload.get("color");
            Task updatedColor = taskService.cycleTaskColor(taskId, newColor);
            return ResponseEntity.ok(updatedColor);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
