package de.commsult.examples.mvp;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import de.commsult.examples.mvp.core.Todo;
import de.commsult.examples.mvp.model.TodoModel;
import de.commsult.examples.mvp.presenter.TodoEditPresenter;
import de.commsult.examples.mvp.presenter.impl.TodoPresenter;
import de.commsult.examples.mvp.views.TodoView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class TodoPresenterTest {

    private TodoPresenter presenter;
    private TodoModel model;
    private TodoView view;
    private TodoEditPresenter editPresenter;

    @Before
    public void setUp() throws Exception {
        model = EasyMock.createMock(TodoModel.class);
        view = EasyMock.createMock(TodoView.class);
        editPresenter = EasyMock.createMock(TodoEditPresenter.class);

        this.presenter = new TodoPresenter(model, view, editPresenter);
    }

    @Test
    public void testTodosAreSetInitially() {
        List<Todo> todos = new ArrayList<Todo>();
        expect(model.getTodos()).andReturn(todos);
        view.setTodos(todos);
        view.setActionHandler(this.presenter);

        replay(model, view);

        this.presenter.init();

        verify(model, view);

    }

    @Test
    public void testDeleteTodo() {
        testDelete(true);
    }

    @Test
    public void testDeleteTodoFails() {
        testDelete(false);
    }

    private void testDelete(boolean success) {
        Todo td = new Todo("td1", "det1", new Date());
        expect(model.deleteTodo(td)).andReturn(success);
        if (!success) {
            view.showMessage(EasyMock.anyObject(String.class));
        } else {
            List<Todo> toDos = new ArrayList<Todo>();
            expect(model.getTodos()).andReturn(toDos);
            view.setTodos(toDos);
        }

        replay(model, view);
        this.presenter.delete(td);
        verify(model, view);
    }

    @Test
    public void testEdit() {
        Todo td = new Todo("td1", "det1", new Date());
        this.editPresenter.open(td);

        List<Todo> toDos = new ArrayList<Todo>();
        expect(model.getTodos()).andReturn(toDos);
        view.setTodos(toDos);

        replay(editPresenter);
        this.presenter.edit(td);
        verify(editPresenter);
    }

    @Test
    public void testCreate() {
        this.editPresenter.open(EasyMock.eq(new Todo()));
        List<Todo> toDos = new ArrayList<Todo>();
        expect(model.getTodos()).andReturn(toDos);
        view.setTodos(toDos);

        replay(editPresenter);
        this.presenter.create();
        verify(editPresenter);
    }

}
