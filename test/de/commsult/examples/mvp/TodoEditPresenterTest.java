package de.commsult.examples.mvp;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import de.commsult.examples.mvp.core.Todo;
import de.commsult.examples.mvp.model.TodoModel;
import de.commsult.examples.mvp.presenter.TodoEditPresenter;
import de.commsult.examples.mvp.presenter.impl.TodoEditPresenterImpl;
import de.commsult.examples.mvp.views.TodoEditView;

import java.util.Date;

import org.easymock.EasyMock;
import org.eclipse.jface.window.Window;
import org.junit.Before;
import org.junit.Test;

public class TodoEditPresenterTest {

    private TodoEditPresenter presenter;
    private TodoEditView view;
    private TodoModel model;

    @Before
    public void setUp() throws Exception {
        view = EasyMock.createMock(TodoEditView.class);
        model = EasyMock.createMock(TodoModel.class);
        this.presenter = new TodoEditPresenterImpl(model, view);
    }

    @Test
    public void testOpen() {
        Todo todo = new Todo("td1", "detail1", new Date());
        expect(view.open(todo)).andReturn(Window.OK);
        expect(view.getTodo()).andReturn(todo);
        model.setTodo(todo);
        replay(model, view);
        this.presenter.open(todo);
        verify(model, view);
    }

}
