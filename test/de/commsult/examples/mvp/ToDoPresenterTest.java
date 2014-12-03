package de.commsult.examples.mvp;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class ToDoPresenterTest {

    private ToDoPresenter presenter;
    private ToDoModel todoModel;
    private ToDoView todoView;

    @Before
    public void setUp() throws Exception {
        todoModel = EasyMock.createMock(ToDoModel.class);
        todoView = EasyMock.createMock(ToDoView.class);

        this.presenter = new ToDoPresenter(todoModel, todoView);
    }

    @Test
    public void testTodosAreSetInitially() {
        List<ToDo> toDos = new ArrayList<ToDo>();
        expect(todoModel.getToDos()).andReturn(toDos);
        todoView.setToDos(toDos);

        replay(todoModel, todoView);

        this.presenter.init();

        verify(todoModel, todoView);

    }

}
