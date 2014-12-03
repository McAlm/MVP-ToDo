package de.commsult.examples.mvp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Main {

    public static void main(String[] args) {
        Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setSize(400,  500);
        
        final ToDoView content = new TodoViewImpl(shell, SWT.NONE);
        shell.addListener(SWT.Resize, new Listener() {

            @Override
            public void handleEvent(Event arg0) {
                Rectangle rect = shell.getClientArea();
                ((Composite)content).setSize(rect.width, rect.height);
            }
        });

        ToDoPresenter toDoPresenter = new ToDoPresenter(new ToDoModelImpl(), content);
        toDoPresenter.init();

        shell.open();
        while (!shell.isDisposed()) {
            shell.redraw();
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}