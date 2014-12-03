package de.commsult.examples.mvp;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ToDoModelImpl implements ToDoModel {

    @Override
    public List<ToDo> getToDos() {
        ToDo td1 = new ToDo("td1", "desc1", new Date());
        return Arrays.asList(new ToDo[] { td1 });
    }

}
