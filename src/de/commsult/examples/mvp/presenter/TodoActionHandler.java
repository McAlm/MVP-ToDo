package de.commsult.examples.mvp.presenter;

import de.commsult.examples.mvp.core.Todo;

public interface TodoActionHandler {

    void delete(Todo todo);

    void edit(Todo todo);

    void create();

}
