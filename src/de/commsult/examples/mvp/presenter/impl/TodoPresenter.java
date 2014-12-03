package de.commsult.examples.mvp.presenter.impl;

import de.commsult.examples.mvp.core.Todo;
import de.commsult.examples.mvp.model.TodoModel;
import de.commsult.examples.mvp.presenter.TodoActionHandler;
import de.commsult.examples.mvp.presenter.TodoEditPresenter;
import de.commsult.examples.mvp.views.TodoView;

public class TodoPresenter implements TodoActionHandler {

    private TodoModel model;
    private TodoView view;
    private TodoEditPresenter editPresenter;

    public TodoPresenter(TodoModel accountModel, TodoView accountView, TodoEditPresenter editPresenter) {
        this.model = accountModel;
        this.view = accountView;
        this.editPresenter = editPresenter;
    }

    public void init() {
        this.view.setActionHandler(this);
        this.view.setTodos(this.model.getTodos());
    }

    @Override
    public void delete(Todo td) {
        if (!this.model.deleteTodo(td)) {
            this.view.showMessage("Could not delete Todo " + td.getName());
        } else {
            this.view.setTodos(this.model.getTodos());
        }
    }

    @Override
    public void edit(Todo todo) {
        handleTodo(todo);
    }

    @Override
    public void create() {
        handleTodo(new Todo());
    }

    private void handleTodo(Todo todo) {
        this.editPresenter.open(todo);
        this.view.setTodos(this.model.getTodos());
    }

}
