package jp.co.geo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.jface.viewers.TableViewer;

public class LogViewer {
	private Logger logger = Logger.getLogger(LogViewer.class.getName());

	protected Shell shell;
	private Display display;
	private ArrayList<CLabel> labelList = new ArrayList<CLabel>();
	private CLabel label1;
	private CLabel label2;
	private ArrayList<StyledText> styledTextList = new ArrayList<StyledText>();
	private StyledText styledText1;
	private StyledText styledText2;
	
	private ArrayList<StringBuffer> bufferList = new ArrayList<StringBuffer>();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LogViewer window = new LogViewer();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
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
		shell.setSize(487, 499);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(2, false));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("File");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
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
				ArrayList<String> openFile = (ArrayList<String>) openFileDialog.open();
				if(openFile == null) return;
				for(int i = 0; i < openFile.size(); i++){
					if(labelList.isEmpty()) return;
					// ファイル名をラベルに設定する
					labelList.get(i).setText(openFile.get(i));
					
					// ファイルダイアログで読み込んだファイルを表示する
					StringBuffer buffer = new StringBuffer();
					buffer = openFile(new File(openFile.get(i)), buffer);
					bufferList.add(buffer);
				}
				Analyzer analyzer = new Analyzer(display, bufferList, styledTextList);
				analyzer.run();
				
				return;
				
			}
		});
		mntmOpen.setText("Open");
		
		MenuItem mntmNewSubmenu_1 = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu_1.setText("Log Level");
		
		Menu menu_2 = new Menu(mntmNewSubmenu_1);
		mntmNewSubmenu_1.setMenu(menu_2);
		
		MenuItem mntmError = new MenuItem(menu_2, SWT.CHECK);
		mntmError.setText("Error");
		
		MenuItem mntmWarn = new MenuItem(menu_2, SWT.CHECK);
		mntmWarn.setText("Warn");
		
		MenuItem mntmDebug = new MenuItem(menu_2, SWT.CHECK);
		mntmDebug.setText("Debug");
		
		MenuItem mntmTrace = new MenuItem(menu_2, SWT.CHECK);
		mntmTrace.setText("Trace");
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		new Label(shell, SWT.NONE);
		
		label1 = new CLabel(shell, SWT.NONE);
		label1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		label1.setText("FilePath");
		
		label2 = new CLabel(shell, SWT.NONE);
		label2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		label2.setText("FilePath");
		
		labelList.add(label1);
		labelList.add(label2);
		
		styledText1 = new StyledText(shell, SWT.BORDER);
		styledText1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		styledText2 = new StyledText(shell, SWT.BORDER);
		styledText2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		
		styledTextList.add(styledText1);
		styledTextList.add(styledText2);

	}
	
	/**
	 * ファイルを開く
	 * @param file
	 * @param buffer
	 * @return
	 */
	public StringBuffer openFile(File file, StringBuffer buffer) {
		if(file.exists() == false) {
			buffer = new StringBuffer("");
			return buffer;
		}
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				buffer.append(line);
				buffer.append("\n");
			}
			logger.finest("Buffer : \n" + buffer);
			System.out.println("Buffer : \n" + buffer);
			
			return buffer;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
