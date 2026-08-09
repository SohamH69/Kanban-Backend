package todoKanban.kanban.service;

import todoKanban.kanban.entity.Task;
import todoKanban.kanban.entity.User;
import todoKanban.kanban.repository.TaskRepository;
import todoKanban.kanban.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getTaskByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task createTask(Long userId, Task task) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        task.setUser(user);
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Task not found with ID: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }

    public Task updateTaskTitle(Long taskId, String newTitle) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));

        // Prevent updates if task is locked
        if (task.getLocked()) {
            throw new RuntimeException("Cannot edit title because task is locked.");
        }

        task.setTitle(newTitle);
        return taskRepository.save(task);
    }

    public Task toggleTaskLock(Long taskId){
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        //Toggle boolean value
        task.setLocked(!task.getLocked());
        return taskRepository.save(task);
    }

    public Task cycleTaskColor(Long taskId, String newColor){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        //Change task color
        task.setColor(newColor);
        return taskRepository.save(task);
    }
}
