package jp.co.geo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;

public class AccessLogViewer {

	protected Shell shell;
	private Table table;
	
	/**
	 * HTTPステータスコードの表示メニュー
	 */
	private Menu mntmHttpStatusCode;
	
	/**
	 * HTTPステータスコードの一覧
	 * ファイルからログを読み込んだ際に重複なく一覧に追加する
	 */
	private ArrayList<String> httpStatusCodeList = new ArrayList<String>();
	
	private ArrayList<Object> logList = new ArrayList<Object>();

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
		shell.setText("Apache Access Log");
		shell.setLayout(new GridLayout(5, false));
		
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
		
		MenuItem mntmHttp = new MenuItem(menu_3, SWT.CASCADE);
		mntmHttp.setText("HTTP\u30B9\u30C6\u30FC\u30BF\u30B9\u30B3\u30FC\u30C9");
		
		mntmHttpStatusCode = new Menu(mntmHttp);
		mntmHttp.setMenu(mntmHttpStatusCode);
		
		
		MenuItem menuItem_1 = new MenuItem(menu_3, SWT.NONE);
		menuItem_1.setText("\u30D8\u30C3\u30C0");
		
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
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				redraw();
			}
		});
		btnNewButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnNewButton.setText("\u518D\u63CF\u753B");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
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
		
		// 読み込んだファイルデータから表を作成する
		for (int i = 0; i < dataList.size(); i++) {
			LogModel logModel = new LogModel();
			Object[] data = logModel.analyze(dataList.get(i));
			logList.add(data);
			TableItem item = new TableItem(table, SWT.NULL);
			String httpStatusCode = logModel.getHttpStatusCode();
			if (httpStatusCode.contains("50")
					|| httpStatusCode.contains("40")) {
				Color red = new Color(Display.getDefault(), 0xFF, 0x00, 0x00);
				item.setForeground(red);
			}
			item.setText(0, (String)data[3]);
			item.setText(1, (String)data[4]);
			item.setText(2, (String) data[5]);
			item.setText(3, (String) data[6]);

			if (httpStatusCodeList.contains(logModel.getHttpStatusCode()) == false) {
				httpStatusCodeList.add(logModel.getHttpStatusCode());
			}
		}
		
		// HTTPステータスコードを昇順に並べる
		Collections.sort(httpStatusCodeList, new Comparator<String>(){
				public int compare(String str1, String str2){
					Integer val1 = new Integer(str1);
					Integer val2 = new Integer(str2);
					return val1.compareTo(val2);
				}
			});
		
		// HTTPステータスコードを表示メニューに追加する
		for (String httpStatusCode : httpStatusCodeList) {
			MenuItem mntmNewItem = new MenuItem(mntmHttpStatusCode, SWT.CHECK);
			mntmNewItem.setText(httpStatusCode);
			mntmNewItem.setSelection(true);
		}
	}
	
	private void redraw() {
		table.removeAll();
		MenuItem menuItems[] = mntmHttpStatusCode.getItems();
		for (int i = 0; i < logList.size(); i++) {
			Object[] data = (Object[]) logList.get(i);
			String httpStatusCode = (String) data[5];
			
			if (selected(menuItems, httpStatusCode)) {
				TableItem item = new TableItem(table, SWT.NULL);
				if (httpStatusCode.contains("50")
						|| httpStatusCode.contains("40")) {
					Color red = new Color(Display.getDefault(), 0xFF, 0x00, 0x00);
					item.setForeground(red);
				}
				item.setText(0, (String)data[3]);
				item.setText(1, (String)data[4]);
				item.setText(2, (String) data[5]);
				item.setText(3, (String) data[6]);
			}
		}
	}

	/**
	 * チェックボックスにチェックがあるか
	 * @param items
	 * @param target
	 * @return
	 */
	private boolean selected(MenuItem[] items, String target) {
		for (MenuItem item : items) {
			if (item.getText().equals(target)) {
				return item.getSelection();
			}
		}
		
		return false;
	}
}
