package de.commsult.examples.mvp;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

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
    public void test() {
        fail("Not yet implemented");
    }

}
