package jp.co.geo.logviewer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class OpenFileDialog extends Dialog {

	protected Shell parent;
	protected Object result;
	protected Shell shlOpenFiles;
	private Text textFile;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public OpenFileDialog(Shell parent, int style) {
		super(parent, style);
		this.parent = parent;
		setText("Open File Dialog");
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
		
		textFile = new Text(shlOpenFiles, SWT.BORDER);
		textFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Button btnNewButton = new Button(shlOpenFiles, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(parent, SWT.OPEN);
				fileDialog.setText("Open");
				fileDialog.setFilterPath("C:/");
				String selected = fileDialog.open();
				if (selected == null) {
					selected = "";
				}
				System.out.println("selected : " + selected);
				textFile.setText(selected);
			}
		});
		btnNewButton.setText("...");
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
			@Override
			public void widgetSelected(SelectionEvent e) {
				File file = new File(textFile.getText());
				ArrayList<StringBuffer> bufferList = openFile(file);
				
				result = bufferList;
				shlOpenFiles.dispose();
			}
		});
		btnOpen.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnOpen.setText("Open");
		
		Button btnCancel = new Button(shlOpenFiles, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = new ArrayList<StringBuffer>();
				shlOpenFiles.dispose();
			}	
		});
		btnCancel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnCancel.setText("Cancel");
	}

	/**
	 * 
	 * @param file
	 * @param buffer
	 * @return
	 */
	public ArrayList<StringBuffer> openFile(File file){
		return openFile(file, new ArrayList<StringBuffer>());
	}
	
	public ArrayList<StringBuffer> openFile(File file, ArrayList<StringBuffer> bufferList) {
		if(file.exists() == false) {
			return bufferList;
		}
		try {
			FileReader fr = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				bufferList.add(new StringBuffer(line));
			}
			return bufferList;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result = null;
		return null;
	}

}
