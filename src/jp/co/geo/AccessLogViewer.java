package jp.co.geo;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class AccessLogViewer {

	protected Shell shell;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AccessLogViewer window = new AccessLogViewer();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(615, 501);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(4, false));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("\u30D5\u30A1\u30A4\u30EB");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmOpen = new MenuItem(menu_1, SWT.NONE);

		mntmOpen.addSelectionListener(new SelectionAdapter() {
			
			@SuppressWarnings("unchecked")
			@Override
			/**
			 * ファイルを開く
			 */
			public void widgetSelected(SelectionEvent e) {
				System.out.println("test");
				// ファイルダイアログを開く
				OpenFileDialog openFileDialog = new OpenFileDialog(shell, SWT.PRIMARY_MODAL);
				ArrayList<StringBuffer> openFile = (ArrayList<StringBuffer>) openFileDialog.open();
				setData(openFile);
				return;
				
			}
		});
		mntmOpen.setText("\u958B\u304F");
		
		MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText("\u8868\u793A");
		
		Menu menu_3 = new Menu(menuItem);
		menuItem.setMenu(menu_3);
		
		MenuItem menuItem_1 = new MenuItem(menu_3, SWT.NONE);
		menuItem_1.setText("\u30D8\u30C3\u30C0");
		
		MenuItem menuItem_2 = new MenuItem(menu_3, SWT.NONE);
		menuItem_2.setText("\u30B9\u30C6\u30FC\u30BF\u30B9\u30B3\u30FC\u30C9");
		
		MenuItem menuItem_3 = new MenuItem(menu_3, SWT.NONE);
		menuItem_3.setText("\u51E6\u7406\u6642\u9593");
		
		MenuItem mntmTool = new MenuItem(menu, SWT.CASCADE);
		mntmTool.setText("\u30C4\u30FC\u30EB");
		
		Menu menu_2 = new Menu(mntmTool);
		mntmTool.setMenu(menu_2);
		
		MenuItem mntmGraph = new MenuItem(menu_2, SWT.NONE);
		mntmGraph.setText("\u30B0\u30E9\u30D5");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText("\u958B\u59CB\u65E5");
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setText("\u3000\u3000\u7D42\u4E86\u65E5");
		
		DateTime dateTime_1 = new DateTime(shell, SWT.BORDER);
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnAccessTime = new TableColumn(table, SWT.NONE);
		tblclmnAccessTime.setWidth(162);
		tblclmnAccessTime.setText("\u65E5\u6642");
		
		TableColumn tblclmnRequest = new TableColumn(table, SWT.NONE);
		tblclmnRequest.setWidth(218);
		tblclmnRequest.setText("\u30EA\u30AF\u30A8\u30B9\u30C8URL");
		
		TableColumn tblclmnHttpStatusCode = new TableColumn(table, SWT.NONE);
		tblclmnHttpStatusCode.setWidth(87);
		tblclmnHttpStatusCode.setText("\u30B9\u30C6\u30FC\u30BF\u30B9\u30B3\u30FC\u30C9");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u51E6\u7406\u6642\u9593(\u30DF\u30EA\u79D2)");

	}
	
	private void setData(ArrayList<StringBuffer> dataList){
		if (dataList == null) return;
		for (int i = 0; i < dataList.size(); i++) {
			Object[] data = new Analyzer().analyze(dataList.get(i));
			TableItem item = new TableItem(table, SWT.NULL);
			item.setText(0, (String)data[1]);
			item.setText(1, (String)data[2]);
			item.setText(2, (String) data[4]);
			item.setText(3, (String) data[5]);
		}
	}
	
	
}
