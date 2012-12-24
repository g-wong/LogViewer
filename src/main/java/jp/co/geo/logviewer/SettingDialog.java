package jp.co.geo.logviewer;

import java.util.ResourceBundle;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SettingDialog extends Dialog {

	ResourceBundle rb = ResourceBundle.getBundle("jp.co.geo.logviewer.Location");
	protected Object result;
	protected Shell shell;
	LogFormatComposite logFormatComposite;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public SettingDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(580, 435);
		shell.setText(rb.getString("setting.window.name"));
		shell.setLayout(new GridLayout(1, false));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Tree tree = new Tree(sashForm, SWT.BORDER);
		
		TreeItem treeItem = new TreeItem(tree, SWT.NONE);
		treeItem.setText(rb.getString("setting.window.tree.item.logformat"));
		treeItem.setExpanded(true);

		Composite mainComposite = new Composite(sashForm, SWT.NONE);
		mainComposite.setLayout(new GridLayout(1, false));
		mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		
		// ログのフォーマットを設定
		logFormatComposite = new LogFormatComposite(mainComposite, SWT.FILL);
		GridData gd_logFormatComposite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_logFormatComposite.heightHint = 329;
		logFormatComposite.setLayoutData(gd_logFormatComposite);
		new Label(logFormatComposite, SWT.NONE);
		new Label(logFormatComposite, SWT.NONE);
		new Label(logFormatComposite, SWT.NONE);
		new Label(logFormatComposite, SWT.NONE);
		new Label(logFormatComposite, SWT.NONE);
		new Label(logFormatComposite, SWT.NONE);
		new Label(logFormatComposite, SWT.NONE);
		sashForm.setWeights(new int[] {156, 403});
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		// OKボタン
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setResult();
				shell.dispose();
			}
		});
		btnNewButton.setLayoutData(new RowData(70, SWT.DEFAULT));
		btnNewButton.setText("OK");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_1.setLayoutData(new RowData(70, SWT.DEFAULT));
		btnNewButton_1.setText("Cancel");

	}
	
	private void setResult() {
		result = logFormatComposite.getResult();
	}
}
