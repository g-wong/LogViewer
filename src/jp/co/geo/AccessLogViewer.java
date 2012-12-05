package jp.co.geo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import jp.co.geo.model.LogModel;
import jp.co.geo.model.Logs;
import jp.co.geo.table.TableSortListener;

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

import com.ibm.icu.util.Calendar;

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
	
	/**
	 * ファイルから読み込んだログの全データを格納するリスト
	 */
	private Logs logList = new Logs();
	
	
	private DateTime fromDateTime;
	
	private DateTime afterDateTime;

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
		
		fromDateTime = new DateTime(shell, SWT.BORDER);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setText("\u3000\u3000\u7D42\u4E86\u65E5");
		
		afterDateTime = new DateTime(shell, SWT.BORDER);
		
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
		tblclmnAccessTime.addSelectionListener(new TableSortListener(table));
		tblclmnAccessTime.setWidth(162);
		tblclmnAccessTime.setText("\u65E5\u6642");
		
		TableColumn tblclmnRequest = new TableColumn(table, SWT.NONE);
		tblclmnRequest.addSelectionListener(new TableSortListener(table));
		tblclmnRequest.setWidth(218);
		tblclmnRequest.setText("\u30EA\u30AF\u30A8\u30B9\u30C8URL");
		
		TableColumn tblclmnHttpStatusCode = new TableColumn(table, SWT.NONE);
		tblclmnHttpStatusCode.addSelectionListener(new TableSortListener(table));
		tblclmnHttpStatusCode.setWidth(87);
		tblclmnHttpStatusCode.setText("\u30B9\u30C6\u30FC\u30BF\u30B9\u30B3\u30FC\u30C9");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.addSelectionListener(new TableSortListener(table));
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u51E6\u7406\u6642\u9593(\u30DF\u30EA\u79D2)");

	}
	
	/**
	 * ログデータから表を作成する
	 * @param dataList
	 */
	private void setData(ArrayList<StringBuffer> dataList){
		if (dataList == null) return;
		
		// 読み込んだファイルデータから表を作成する
		for (int i = 0; i < dataList.size(); i++) {
			LogModel log = new LogModel();
			Object[] data = log.analyze(dataList.get(i));
			logList.appendLog(log);
			TableItem item = new TableItem(table, SWT.NULL);
			// HTTPステータスコードを見て 50x や 40x ならその行を赤色にする
			String httpStatusCode = log.getHttpStatusCode();
			if (httpStatusCode.contains("50")
					|| httpStatusCode.contains("40")) {
				Color red = new Color(Display.getDefault(), 0xFF, 0x00, 0x00);
				item.setForeground(red);
			}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			item.setText(0, df.format(log.getDate()));  // 日時
			item.setText(1, log.getURL());  // URL
			item.setText(2, log.getHttpStatusCode()); // HTTPステータスコード
			item.setText(3, log.getProcessingTime()); // レスポンスサイズ

			if (httpStatusCodeList.contains(log.getHttpStatusCode()) == false) {
				httpStatusCodeList.add(log.getHttpStatusCode());
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
		
		setDateTime();
	}
	
	/**
	 * 再描画を行う
	 */
	@SuppressWarnings("deprecation")
	private void redraw() {
		table.removeAll();
		Iterator<LogModel> it = logList.iterator();
		while( it.hasNext() ) {
			LogModel log = it.next();
			String httpStatusCode = log.getHttpStatusCode();
			
			if (isDisplay(log)) {
				TableItem item = new TableItem(table, SWT.NULL);
				if (httpStatusCode.contains("50")
						|| httpStatusCode.contains("40")) {
					Color red = new Color(Display.getDefault(), 0xFF, 0x00, 0x00);
					item.setForeground(red);
				}
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				item.setText(0, df.format(log.getDate()));  // 日時
				item.setText(1, log.getURL());
				item.setText(2, log.getHttpStatusCode());
				item.setText(3, log.getProcessingTime());
			}
		}
	}

	/**
	 * チェックボックスでチェックが行われているかを確認する
	 * @param items チェックボックス
	 * @param target 検索対象文字列
	 * @return
	 */
	private boolean selectedCheckBox(MenuItem[] items, String target) {
		for (MenuItem item : items) {
			if (item.getText().equals(target)) {
				return item.getSelection();
			}
		}
		
		return false;
	}
	
	private void setDateTime() {
		Calendar cal = Calendar.getInstance();
		Date date = logList.getMinDate();
		cal.setTime(date);
		fromDateTime.setDate(
				cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE));
		date = logList.getMaxDate();
		cal.setTime(date);
		afterDateTime.setDate(
				cal.get(Calendar.YEAR), 
				cal.get(Calendar.MONTH), 
				cal.get(Calendar.DATE));
	}
	
	/**
	 * ログが表示すべきものかどうか判定
	 * @return
	 */
	private boolean isDisplay(LogModel log) {
		//HTTPステータスコードで判定
		MenuItem menuItems[] = mntmHttpStatusCode.getItems();
		String httpStatusCode = log.getHttpStatusCode();
		if (selectedCheckBox(menuItems, httpStatusCode) == false) {
			return false;
		}
		
		//日付で判定
		Calendar from = Calendar.getInstance(Locale.JAPAN);
		Calendar after = Calendar.getInstance(Locale.JAPAN);
		Calendar logCal = Calendar.getInstance(Locale.JAPAN);
		int fromYear = fromDateTime.getYear();
		int fromMonth = fromDateTime.getMonth();
		int fromDay = fromDateTime.getDay();
		from.set(fromYear, fromMonth, fromDay);
		int afterYear = afterDateTime.getYear();
		int afterMonth = afterDateTime.getMonth();
		int afterDay = afterDateTime.getDay() + 1;
		after.set(afterYear, afterMonth, afterDay);
		
		Date date = log.getDate();
		logCal.setTime(date);
		
		if ( from.after(logCal) || after.before(logCal)){
			return false;
		}
		
		return true;
	}
}
