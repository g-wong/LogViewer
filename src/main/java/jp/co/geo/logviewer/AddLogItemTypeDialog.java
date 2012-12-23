package jp.co.geo.logviewer;

import java.util.ArrayList;

import jp.co.geo.logviewer.model.LogItemType;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddLogItemTypeDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text textNumber;
	private Text textTypeDescription;
	
	Combo comboTypeSelect;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddLogItemTypeDialog(Shell parent, int style) {
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
		shell.setSize(441, 213);
		shell.setText("追加ウィンドウ");
		shell.setLayout(new GridLayout(3, false));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("#");
		
		textNumber = new Text(shell, SWT.BORDER);
		textNumber.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblType = new Label(shell, SWT.NONE);
		lblType.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblType.setText("タイプ");
		
		comboTypeSelect = new Combo(shell, SWT.NONE);
		comboTypeSelect.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		// Setting List
		setComboList();
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblTypeDescription = new Label(shell, SWT.NONE);
		lblTypeDescription.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTypeDescription.setText("列名");
		
		textTypeDescription = new Text(shell, SWT.BORDER);
		textTypeDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button btnAdd = new Button(shell, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(checkInput() == false) {
					return;
				}
				
				shell.dispose();
			}
		});
		GridData gd_btnAdd = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnAdd.widthHint = 75;
		btnAdd.setLayoutData(gd_btnAdd);
		btnAdd.setText("追加");
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		GridData gd_btnCancel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnCancel.widthHint = 75;
		btnCancel.setLayoutData(gd_btnCancel);
		btnCancel.setText("キャンセル");

	}
	
	private void setComboList() {
		ArrayList<LogItemType> list = LogItemType.getLogItemTypeList();
		
		for(int i = 0; i < list.size(); i++) {
			comboTypeSelect.add(list.get(i).toString());
		}
	}
	
	private boolean checkInput() {
		Integer input;

		MessageBox msg = new MessageBox(shell, 
				SWT.OK | 
				SWT.ICON_WARNING);
		msg.setText("Message");
		
		// 数値のチェック
		try {
			input = Integer.decode(textNumber.getText());
		} catch(NumberFormatException e) {
			msg.setMessage(textNumber.getText() + " は整数値ではありません");
			msg.open();
			return false;
		}
		if(input < 0) {
			msg.setMessage(textNumber.getText() + " は負の値です。0より大きい値を入力してください");
			msg.open();
			return false;
		}
		ArrayList<Integer> numberList = LogFormatComposite.numberList;
		for(int i = 0; i < numberList.size(); i++) {
			if(numberList.get(i).equals(input)) {
				msg.setMessage(textNumber.getText() + " は既に定義されています");
				msg.open();
				return false;
			}
		}
		
		// タイプのチェック
		String selectType = comboTypeSelect.getText();
		if(LogItemType.getLogItemType(selectType) == null){
			msg.setMessage("入力したタイプは無効です");
			msg.open();
			return false;
		}
		
		
		return true;
	}
}
