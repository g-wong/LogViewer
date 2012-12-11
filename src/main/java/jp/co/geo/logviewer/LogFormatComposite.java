package jp.co.geo.logviewer;

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

public class LogFormatComposite extends Composite {
	private Text text;
	private Table table;
	private Text txtDdmmmyyyyhhmmssZ;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LogFormatComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("書式");
		
		text = new Text(this, SWT.BORDER);
		text.setText("{0} {1} {2} [{3}] \"{4}\" {5} {6}");
		
		Group group = new Group(this, SWT.NONE);
		group.setText("テーブルに表示する列名とタイプを指定");
		group.setLayout(new GridLayout(1, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
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
		tblclmnType.setText("タイプ");
		
		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(157);
		tblclmnName.setText("表示列名");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		String text[] = {"4", "Time", "時刻"};
		tableItem.setText(new String[] {"3", "Time", "時刻"});
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(new String[] {"4", "URL", "リクエストURL"});
		
		TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText(new String[] {"5", "CODE", "HTTPステータスコード"});
		
		TableItem tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText(new String[] {"6", "INT", "サイズ(Byte)"});
		
		Label label = new Label(group, SWT.NONE);
		label.setText("※省略すると表示されません");
		
		Group group_1 = new Group(this, SWT.NONE);
		group_1.setLayout(new GridLayout(2, false));
		GridData gd_group_1 = new GridData(SWT.FILL, SWT.TOP, false, false, 2, 1);
		gd_group_1.heightHint = 32;
		gd_group_1.widthHint = 289;
		group_1.setLayoutData(gd_group_1);
		
		Label lblNewLabel_1 = new Label(group_1, SWT.NONE);
		lblNewLabel_1.setText("時刻形式");
		
		txtDdmmmyyyyhhmmssZ = new Text(group_1, SWT.BORDER);
		txtDdmmmyyyyhhmmssZ.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		txtDdmmmyyyyhhmmssZ.setText("dd/MMM/yyyy:HH:mm:ss Z");
		
		Label label_1 = new Label(group_1, SWT.NONE);
		new Label(group_1, SWT.NONE);
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 2, 1));
		
		Button btnImport = new Button(sashForm, SWT.NONE);
		btnImport.setText("インポート");
		
		Button btnExport = new Button(sashForm, SWT.NONE);
		btnExport.setText("エクスポート");
		
		Button btnApply = new Button(sashForm, SWT.NONE);
		btnApply.setText("適用");
		sashForm.setWeights(new int[] {1, 1, 1});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
