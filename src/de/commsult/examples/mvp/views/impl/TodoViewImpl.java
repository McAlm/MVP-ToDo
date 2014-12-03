package de.commsult.examples.mvp.views.impl;

import de.commsult.examples.mvp.core.Todo;
import de.commsult.examples.mvp.presenter.TodoActionHandler;
import de.commsult.examples.mvp.views.TodoView;

import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TodoViewImpl extends Composite implements TodoView {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    private Table table;
    private TodoActionHandler actionHandler;

    /**
     * Create the composite.
     * @param parent
     * @param style
     */
    public TodoViewImpl(Composite parent, int style) {
        super(parent, style);
        setLayout(new GridLayout(1, false));

        Group grpTodo = new Group(this, SWT.NONE);
        GridLayout gl_grpTodo = new GridLayout(1, false);
        gl_grpTodo.verticalSpacing = 0;
        gl_grpTodo.horizontalSpacing = 0;
        grpTodo.setLayout(gl_grpTodo);
        grpTodo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        grpTodo.setText("Todo");

        table = new Table(grpTodo, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        String[] titles = { "Title", "Details", "Until" };
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
        }

        Composite composite = new Composite(grpTodo, SWT.NONE);
        FillLayout fl_composite = new FillLayout(SWT.HORIZONTAL);
        fl_composite.marginHeight = 5;
        fl_composite.spacing = 5;
        composite.setLayout(fl_composite);
        composite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

        Button btnDelete = new Button(composite, SWT.NONE);
        btnDelete.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selectionIndex = table.getSelectionIndex();
                if (selectionIndex >= 0) {
                    TodoViewImpl.this.actionHandler.delete((Todo)table.getItem(selectionIndex).getData());
                }
            }
        });
        btnDelete.setText("Delete");

        Button btnEdit = new Button(composite, SWT.NONE);
        btnEdit.setText("Edit");
        btnEdit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selectionIndex = table.getSelectionIndex();
                if (selectionIndex >= 0) {
                    TodoViewImpl.this.actionHandler.edit((Todo)table.getItem(selectionIndex).getData());
                }
            }
        });

        Button btnNew = new Button(composite, SWT.NONE);
        btnNew.setText("New");
        btnNew.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                TodoViewImpl.this.actionHandler.create();
            }
        });

    }

    @Override
    public void setTodos(List<Todo> toDos) {

        table.removeAll();

        for (Todo toDo : toDos) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, toDo.getName());
            item.setText(1, toDo.getDetails());
            item.setText(2, dateFormatter.format(toDo.getUntil()));
            item.setData(toDo);
        }

        for (int i = 0, n = table.getColumnCount(); i < n; i++) {
            table.getColumn(i).setWidth(110);
        }

        table.select(0);
    }

    @Override
    public void setActionHandler(TodoActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    @Override
    public void showMessage(String message) {
        MessageBox messageBox = new MessageBox(getShell());
        messageBox.setText("Achtung");
        messageBox.setMessage(message);
        messageBox.open();
    }

}
