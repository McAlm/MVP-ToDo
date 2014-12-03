package de.commsult.examples.mvp.views.impl;

import de.commsult.examples.mvp.core.Todo;
import de.commsult.examples.mvp.views.TodoEditView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TodoEditViewImpl extends Dialog implements TodoEditView {
    private DataBindingContext m_bindingContext;

    private Text textName;
    private Text textDetail;
    private Todo todo;
    private DateTime dateTimeUntil;
    private Label lblDetails;

    /**
     * Create the dialog.
     * @param parentShell
     */
    public TodoEditViewImpl(Shell parentShell) {
        super(parentShell);
    }

    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)container.getLayout();
        gridLayout.numColumns = 2;

        Label lblName = new Label(container, SWT.NONE);
        lblName.setText("Name:");

        textName = new Text(container, SWT.BORDER);
        textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        lblDetails = new Label(container, SWT.NONE);
        lblDetails.setText("Details:");

        textDetail = new Text(container, SWT.BORDER);
        textDetail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        Label lblUntil = new Label(container, SWT.NONE);
        lblUntil.setText("Until:");

        dateTimeUntil = new DateTime(container, SWT.BORDER);

        this.textName.setText(todo.getName());
        this.textDetail.setText(todo.getDetails());
        Calendar cal = new GregorianCalendar();
        cal.setTime(todo.getUntil());
        this.dateTimeUntil.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        return container;
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        m_bindingContext = initDataBindings();
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(450, 300);
    }

    @Override
    public int open(Todo todo) {
        this.todo = todo;
        return super.open();

    }

    @Override
    public Todo getTodo() {
        return this.todo;
    }

    protected DataBindingContext initDataBindings() {
        DataBindingContext bindingContext = new DataBindingContext();

        //
        IObservableValue observeTextTextNameObserveWidget = WidgetProperties.text(SWT.Modify).observe(textName);
        IObservableValue nameTodoObserveValue = PojoProperties.value("name").observe(todo);
        bindingContext.bindValue(observeTextTextNameObserveWidget, nameTodoObserveValue, null, null);
        //
        IObservableValue observeTextLblDetailsObserveWidget = WidgetProperties.text(SWT.Modify).observe(textDetail);
        IObservableValue detailsTodoObserveValue = PojoProperties.value("details").observe(todo);
        bindingContext.bindValue(observeTextLblDetailsObserveWidget, detailsTodoObserveValue, null, null);

        IObservableValue observableValueUntilWidget = WidgetProperties.selection().observe(dateTimeUntil);
        IObservableValue observableValueUntilModel = PojoProperties.value("until").observe(todo);
        bindingContext.bindValue(observableValueUntilWidget, observableValueUntilModel, null, null);
        //
        return bindingContext;
    }
}
