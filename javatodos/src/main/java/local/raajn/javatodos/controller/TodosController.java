package local.raajn.javatodos.controller;

import local.raajn.javatodos.models.Todo;
import local.raajn.javatodos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping("/todos")
public class TodosController
{
    @Autowired
    private TodoService todoService;

    @PutMapping(value = "/todoid/{todoid}", consumes={"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateTodo(@RequestBody
                                                Todo updateTodo, @PathVariable long todoid)
    {
        Todo updatedTodo = todoService.update(updateTodo, todoid);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }
}