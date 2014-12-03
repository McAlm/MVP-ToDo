package de.commsult.examples.mvp.views;

import de.commsult.examples.mvp.core.Todo;
import de.commsult.examples.mvp.presenter.TodoActionHandler;

import java.util.List;

public interface TodoView {

    void setTodos(List<Todo> toDos);

    void setActionHandler(TodoActionHandler actionHandler);

    void showMessage(String anyObject);

}
