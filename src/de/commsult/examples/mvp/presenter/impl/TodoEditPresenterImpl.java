package de.commsult.examples.mvp.presenter.impl;

import de.commsult.examples.mvp.core.Todo;
import de.commsult.examples.mvp.model.TodoModel;
import de.commsult.examples.mvp.presenter.TodoEditPresenter;
import de.commsult.examples.mvp.views.TodoEditView;

import org.eclipse.jface.window.Window;

public class TodoEditPresenterImpl implements TodoEditPresenter {

    private TodoModel model;
    private TodoEditView view;

    public TodoEditPresenterImpl(TodoModel model, TodoEditView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void open(Todo todo) {
        if (Window.OK == view.open(todo)) {
            todo = view.getTodo();
            model.setTodo(todo);
        }
    }
}
