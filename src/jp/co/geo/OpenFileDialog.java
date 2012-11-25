package jp.co.geo;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class OpenFileDialog extends Dialog {

	protected Shell parent;
	protected Object result;
	protected Shell shlOpenFiles;
	private Text textFile1;
	private Text textFile2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public OpenFileDialog(Shell parent, int style) {
		super(parent, style);
		this.parent = parent;
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlOpenFiles.open();
		shlOpenFiles.layout();
		Display display = getParent().getDisplay();
		while (!shlOpenFiles.isDisposed()) {
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
		shlOpenFiles = new Shell(getParent(), getStyle());
		shlOpenFiles.setSize(372, 160);
		shlOpenFiles.setText("Open Files");
		shlOpenFiles.setLayout(new GridLayout(5, false));
		
		CLabel lblFile1 = new CLabel(shlOpenFiles, SWT.NONE);
		lblFile1.setText("File1");
		
		textFile1 = new Text(shlOpenFiles, SWT.BORDER);
		textFile1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Button btnNewButton = new Button(shlOpenFiles, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			// File1 のファイルダイアログ
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(parent, SWT.OPEN);
				fileDialog.setText("Open");
				fileDialog.setFilterPath("C:/");
				String selected = fileDialog.open();
				System.out.println("selected : " + selected);
				textFile1.setText(selected);
			}
		});
		btnNewButton.setText("...");
		
		CLabel lblFile2 = new CLabel(shlOpenFiles, SWT.NONE);
		lblFile2.setText("File2");
		
		textFile2 = new Text(shlOpenFiles, SWT.BORDER);
		textFile2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Button btnNewButton_1 = new Button(shlOpenFiles, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			// File2 のファイルダイアログ
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(parent, SWT.OPEN);
				fileDialog.setText("Open");
				fileDialog.setFilterPath("C:/");
				String selected = fileDialog.open();
				System.out.println("selected : " + selected);
				textFile2.setText(selected);
			}
		});
		btnNewButton_1.setText("...");
		new Label(shlOpenFiles, SWT.NONE);
		new Label(shlOpenFiles, SWT.NONE);
		new Label(shlOpenFiles, SWT.NONE);
		new Label(shlOpenFiles, SWT.NONE);
		new Label(shlOpenFiles, SWT.NONE);
		new Label(shlOpenFiles, SWT.NONE);
		new Label(shlOpenFiles, SWT.NONE);
		new Label(shlOpenFiles, SWT.NONE);
		
		Button btnOpen = new Button(shlOpenFiles, SWT.NONE);
		btnOpen.addSelectionListener(new SelectionAdapter() {
			// Open を選択したときの動作
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<String> fileList = new ArrayList<String>();
				fileList.add(textFile1.getText());
				fileList.add(textFile2.getText());
				result = fileList;
				shlOpenFiles.dispose();
			}
		});
		btnOpen.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnOpen.setText("Open");
		
		Button btnCancel = new Button(shlOpenFiles, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			// Cancel ボタンが押された時の動作
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = null;
				shlOpenFiles.dispose();
			}	
		});
		btnCancel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnCancel.setText("Cancel");
	}

}
