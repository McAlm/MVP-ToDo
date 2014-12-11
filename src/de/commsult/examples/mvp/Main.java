package de.commsult.examples.mvp;

import de.commsult.examples.mvp.model.TodoModel;
import de.commsult.examples.mvp.model.impl.TodoModelImpl;
import de.commsult.examples.mvp.presenter.impl.TodoEditPresenterImpl;
import de.commsult.examples.mvp.presenter.impl.TodoPresenter;
import de.commsult.examples.mvp.views.TodoView;
import de.commsult.examples.mvp.views.impl.TodoEditViewImpl;
import de.commsult.examples.mvp.views.impl.TodoViewImpl;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Main {

    public static void main(String[] args) {
        final Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setSize(400, 500);

        final TodoView content = new TodoViewImpl(shell, SWT.NONE);
        shell.addListener(SWT.Resize, new Listener() {

            @Override
            public void handleEvent(Event arg0) {
                Rectangle rect = shell.getClientArea();
                ((Composite)content).setSize(rect.width, rect.height);
            }
        });

        TodoModel model = new TodoModelImpl();
        TodoPresenter toDoPresenter = new TodoPresenter(model, content, new TodoEditPresenterImpl(model, new TodoEditViewImpl(shell)));
        toDoPresenter.init();

        Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
            public void run() {
                shell.open();
                while (!shell.isDisposed()) {
                    if (!display.readAndDispatch())
                        display.sleep();
                }
                display.dispose();

            }
        });
    }
}