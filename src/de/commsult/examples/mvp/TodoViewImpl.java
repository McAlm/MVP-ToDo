package de.commsult.examples.mvp;

import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TodoViewImpl extends Composite implements ToDoView {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    private Table table;

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
        String[] titles = {"Title", "Details", "Until"};
        for (int i=0; i<titles.length; i++) {
            TableColumn column = new TableColumn (table, SWT.NONE);
            column.setText (titles [i]);
        }
        
        Composite composite = new Composite(grpTodo, SWT.NONE);
        FillLayout fl_composite = new FillLayout(SWT.HORIZONTAL);
        fl_composite.marginHeight = 5;
        fl_composite.spacing = 5;
        composite.setLayout(fl_composite);
        composite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        
        Button btnDelete = new Button(composite, SWT.NONE);
        btnDelete.setText("Delete");
        
        Button btnEdit = new Button(composite, SWT.NONE);
        btnEdit.setText("Edit");
        
        Button btnNew = new Button(composite, SWT.NONE);
        btnNew.setText("New");

    }

    @Override
    public void setToDos(List<ToDo> toDos) {
        for (ToDo toDo : toDos) {
            TableItem  item = new TableItem(table, SWT.NONE);
            item.setText(0, toDo.getName());
            item.setText(1,toDo.getDetails());
            item.setText(2, dateFormatter.format(toDo.getUntil()));
            item.setData(toDo);
        }
        
        for (int i = 0, n = table.getColumnCount(); i < n; i++) {
            table.getColumn(i).setWidth(110);
          }
    }

}
