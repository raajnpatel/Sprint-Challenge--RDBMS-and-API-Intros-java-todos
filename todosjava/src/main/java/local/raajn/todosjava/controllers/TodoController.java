package local.raajn.todosjava.controllers;


import local.raajn.todosjava.models.Todo;
import local.raajn.todosjava.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController
{
  @Autowired
  private TodoService todoService;

  // PUT -- http://localhost:2019/todos/todoid/{todoid}
  // updates a to-do based on given todoid
  @PutMapping(value = "/todoid/{todoid}",
              consumes = {"application/json"})
  public ResponseEntity<?> updateTodo(@RequestBody Todo todo,
                                      @PathVariable long todoid)
  {
    todoService.updateTodo(todo, todoid);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // GET -- http://localhost:2019/todos/todos
  // get all todos
  @GetMapping(value = "/todos",
              produces = {"application/json"})
  public ResponseEntity<?> getAllTodos()
  {
    return new ResponseEntity<>(todoService.findAllTodos(), HttpStatus.OK);
  }
}
