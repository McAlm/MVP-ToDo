package de.commsult.examples.mvp;

public class ToDoPresenter {

    private ToDoModel model;
    private ToDoView view;

    public ToDoPresenter(ToDoModel accountModel, ToDoView accountView) {
        this.model = accountModel;
        this.view = accountView;
    }

    public void init() {
        this.view.setToDos(this.model.getToDos());
    }

}
