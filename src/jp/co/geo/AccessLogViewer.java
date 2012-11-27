package jp.co.geo;

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
		shell.setSize(573, 478);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(4, false));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("\u30D5\u30A1\u30A4\u30EB");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmOpen = new MenuItem(menu_1, SWT.NONE);
		mntmOpen.setText("\u958B\u304F");
		
		MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText("\u8868\u793A");
		
		Menu menu_3 = new Menu(menuItem);
		menuItem.setMenu(menu_3);
		
		MenuItem menuItem_1 = new MenuItem(menu_3, SWT.NONE);
		menuItem_1.setText("\u30D8\u30C3\u30C0");
		
		MenuItem mntmNewRadiobutton = new MenuItem(menu_3, SWT.RADIO);
		mntmNewRadiobutton.setText("New RadioButton");
		
		MenuItem mntmNewRadiobutton_1 = new MenuItem(menu_3, SWT.RADIO);
		mntmNewRadiobutton_1.setText("New RadioButton");
		
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
		tblclmnAccessTime.setWidth(138);
		tblclmnAccessTime.setText("\u65E5\u6642");
		
		TableColumn tblclmnRequest = new TableColumn(table, SWT.NONE);
		tblclmnRequest.setWidth(218);
		tblclmnRequest.setText("\u30EA\u30AF\u30A8\u30B9\u30C8URL");
		
		TableColumn tblclmnHttpStatusCode = new TableColumn(table, SWT.NONE);
		tblclmnHttpStatusCode.setWidth(87);
		tblclmnHttpStatusCode.setText("\u30B9\u30C6\u30FC\u30BF\u30B9\u30B3\u30FC\u30C9");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(new String[] {});
		tableItem.setText("2012/11/27 23:44:54\r\n");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u51E6\u7406\u6642\u9593(\u30DF\u30EA\u79D2)");

	}
}
