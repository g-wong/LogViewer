package jp.co.geo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FillLayout;

public class LogView {

	protected Shell shell;
	private Text textField01;
	private Text textField02;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LogView window = new LogView();
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
		shell.setSize(635, 537);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.NONE);
		mntmFile.setText("File");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		textField01 = new Text(scrolledComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		textField01.setText("\u30ED\u30B0\u30D5\u30A1\u30A4\u30EB\u3092\u3053\u3053\u306B\u30C9\u30ED\u30C3\u30D7\u3057\u3066\u304F\u3060\u3055\u3044\u3002");
		scrolledComposite.setContent(textField01);
		scrolledComposite.setMinSize(textField01.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		ScrolledComposite scrolledComposite_1 = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_1.setExpandHorizontal(true);
		scrolledComposite_1.setExpandVertical(true);
		
		textField02 = new Text(scrolledComposite_1, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		textField02.setText("\u30ED\u30B0\u30D5\u30A1\u30A4\u30EB\u3092\u3053\u3053\u306B\u30C9\u30ED\u30C3\u30D7\u3057\u3066\u304F\u3060\u3055\u3044\u3002");
		scrolledComposite_1.setContent(textField02);
		scrolledComposite_1.setMinSize(textField02.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		// Add drop listener
		addDropListener(textField01);
		addDropListener(textField02);
	}
	
	private void addDropListener(Text text)
	{
		DropTarget target = new DropTarget(text, DND.DROP_DEFAULT | DND.DROP_COPY);
		FileTransfer transfer = FileTransfer.getInstance();
		Transfer[] types = new Transfer[]{transfer};
		target.setTransfer(types);
		target.addDropListener(new FileDropListener(text));
	}
}
