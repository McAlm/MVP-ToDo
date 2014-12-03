package de.commsult.examples.mvp.model;

import de.commsult.examples.mvp.core.Todo;

import java.util.List;

public interface TodoModel {

    List<Todo> getTodos();

    boolean deleteTodo(Todo td);

    void setTodo(Todo todo);

}
