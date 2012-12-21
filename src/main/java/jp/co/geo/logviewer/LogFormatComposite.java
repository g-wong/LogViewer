package jp.co.geo.logviewer;

import java.util.ArrayList;

import jp.co.geo.logviewer.model.AccessLogFormat;
import jp.co.geo.logviewer.model.LogItemType;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Menu;
import swing2swt.layout.FlowLayout;
import swing2swt.layout.BorderLayout;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LogFormatComposite extends Composite {
	
	private Text textLogFormat;
	private Table table;
	private Text txtTimeFormat;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LogFormatComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("����");
		
		textLogFormat = new Text(this, SWT.BORDER);
		textLogFormat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		textLogFormat.setText("{0} {1} {2} [{3}] \"{4}\" {5} {6}");
		
		Group group = new Group(this, SWT.NONE);
		group.setText("�e�[�u���ɕ\������񖼂ƃ^�C�v���w��");
		group.setLayout(new GridLayout(2, false));
		GridData gd_group = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1);
		gd_group.heightHint = 174;
		group.setLayoutData(gd_group);
		
		table = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_table = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_table.widthHint = 275;
		table.setLayoutData(gd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNum = new TableColumn(table, SWT.NONE);
		tblclmnNum.setWidth(23);
		tblclmnNum.setText("#");
		
		TableColumn tblclmnType = new TableColumn(table, SWT.NONE);
		tblclmnType.setWidth(110);
		tblclmnType.setText("�^�C�v");
		
		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(157);
		tblclmnName.setText("�\����");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		String text[] = {"4", "Time", "����"};
		tableItem.setText(new String[] {"3", "TIME", "����"});
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(new String[] {"4", "URL", "���N�G�X�gURL"});
		
		TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText(new String[] {"5", "STATUS", "HTTP�X�e�[�^�X�R�[�h"});
		
		TableItem tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText(new String[] {"6", "SIZE", "�T�C�Y(Byte)"});
		
		Composite composite = new Composite(group, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		composite.setLayout(new GridLayout(1, false));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnNewButton.setText("�s�̒ǉ�");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnNewButton_1.setText("�s�̕ҏW");
		
		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnNewButton_2.setText("�s�̍폜");
		
		Label label = new Label(group, SWT.NONE);
		label.setText("���ȗ�����ƕ\������܂���");
		new Label(group, SWT.NONE);
		
		Group group_1 = new Group(this, SWT.NONE);
		group_1.setLayout(new GridLayout(2, false));
		GridData gd_group_1 = new GridData(SWT.FILL, SWT.TOP, false, false, 2, 1);
		gd_group_1.heightHint = 32;
		gd_group_1.widthHint = 289;
		group_1.setLayoutData(gd_group_1);
		
		Label lblNewLabel_1 = new Label(group_1, SWT.NONE);
		lblNewLabel_1.setText("�����`��");
		
		txtTimeFormat = new Text(group_1, SWT.BORDER);
		GridData gd_txtTimeFormat = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txtTimeFormat.widthHint = 202;
		txtTimeFormat.setLayoutData(gd_txtTimeFormat);
		txtTimeFormat.setText("dd/MMM/yyyy:HH:mm:ss Z");
		
		Label label_1 = new Label(group_1, SWT.NONE);
		new Label(group_1, SWT.NONE);
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 2, 1));
		
		Button btnImport = new Button(sashForm, SWT.NONE);
		btnImport.setText("�C���|�[�g");
		
		Button btnExport = new Button(sashForm, SWT.NONE);
		btnExport.setText("�G�N�X�|�[�g");
		sashForm.setWeights(new int[] {1, 1});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public String getLogFormat() {
		return textLogFormat.getText();
	}
	
	public String getTimeFormat() {
		return txtTimeFormat.getText();
	}
	
	public Object getResult() {
		String logFormat = getLogFormat();
		String timeFormat = getTimeFormat();
		TableItem[] item = table.getItems();
		ArrayList types = new ArrayList();
		for(int i = 0; i < item.length; i++) {
			int index = new Integer(item[i].getText(0)).intValue();
			String name = item[i].getText(1);
			String description = item[i].getText(2);
			LogItemType type = LogItemType.getLogItemType(name);
			if (null != type) {
				type.setDescription(description);
				type.setIndex(index);
				types.add(type);
			} else {
				System.err.println("Error : ��`����Ă��Ȃ����O�̃^�C�v���w�肳��܂��� " + name);
			}
			
		}
		AccessLogFormat format 
			= new AccessLogFormat(logFormat, timeFormat, types);
		
		return format;
	}
}
