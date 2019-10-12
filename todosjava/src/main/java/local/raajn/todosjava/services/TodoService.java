package local.raajn.todosjava.services;



import local.raajn.todosjava.models.Todo;

import java.util.List;

public interface TodoService
{
  Todo updateTodo(Todo todo, long todoid);

  List<Todo> findAllTodos();
}
