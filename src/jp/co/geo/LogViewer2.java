package jp.co.geo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.widgets.Group;

public class LogViewer2 {

	protected Shell shell;
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
			LogViewer2 window = new LogViewer2();
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
				// ダイアログでファイルを開く
				OpenFileDialog openFileDialog = new OpenFileDialog(shell, SWT.PRIMARY_MODAL);
				ArrayList<String> openFile = (ArrayList<String>) openFileDialog.open();
				if(openFile == null) return;
				for(int i = 0; i < openFile.size(); i++){
					if(labelList.isEmpty()) return;
					// ファイル名をラベルに設定する
					labelList.get(i).setText(openFile.get(i));
					StringBuffer buffer = new StringBuffer();
					buffer = openFile(new File(openFile.get(i)), buffer);
					
				}
				analyze(bufferList, styledTextList);
				
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
	
	public StringBuffer openFile(File file, StringBuffer buffer) {
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				buffer.append(line);
			}
			System.out.println("Buffer : " + buffer);
			
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
	
	public void analyze(ArrayList<StringBuffer> bufferList, ArrayList<StyledText> styledTextList) {
		for (int i = 0; i < bufferList.size(); i++) {
			if(styledTextList.get(i) == null) break;
			styledTextList.get(i).setText("");
			styledTextList.get(i).setText(bufferList.get(i).toString());
		}
	}
}
