package de.commsult.examples.mvp.model.impl;

import de.commsult.examples.mvp.core.Todo;
import de.commsult.examples.mvp.model.TodoModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TodoModelImpl implements TodoModel {

    private List<Todo> todos;

    public TodoModelImpl() {

        Todo td1 = new Todo("td1", "desc1", new Date());
        Todo td2 = new Todo("td2", "desc2", new Date());
        Todo td3 = new Todo("td3", "desc3", new Date());
        Todo td4 = new Todo("td4", "desc4", new Date());
        Todo td5 = new Todo("td5", "desc5", new Date());

        todos = new ArrayList(Arrays.asList(new Todo[] { td1, td2, td3, td4, td5 }));

    }

    @Override
    public List<Todo> getTodos() {
        return this.todos;
    }

    @Override
    public boolean deleteTodo(Todo todo) {
        return this.todos.remove(todo);
    }

    @Override
    public void setTodo(Todo todo) {
        int index = this.todos.indexOf(todo);
        if (index == -1) {
            this.todos.add(todo);
        } else {
            this.todos.set(index, todo);
        }
    }

}
