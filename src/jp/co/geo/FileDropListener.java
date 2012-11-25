package jp.co.geo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.widgets.Text;

public class FileDropListener extends DropTargetAdapter
{
	private Text target;
	
	public FileDropListener(Text target)
	{
		super();
		this.target = target;
	}
	
	public void dragEnter(DropTargetEvent e)
	{
		e.detail = DND.DROP_COPY;
	}
	
	public void drop(DropTargetEvent e)
	{
		String files[] = (String[]) e.data;
		for( String f : files){
			File file = new File(f);
			System.out.println("ファイル名 :" + f + " を読み込みました。");
			
			setFileToTarget(file, target);
		}
	}
	
	private void setFileToTarget(File file, Text target)
	{
		// File read
		try {
			FileReader in = new FileReader(file);
			BufferedReader br = new BufferedReader(in);
			String line;
			String buffer = "";
			while ((line = br.readLine()) != null) {
				buffer += line;
				buffer += "\n";
				
			    target.setText(buffer);
			}
			br.close();
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println("ドロップしたファイル（" + file + "）が読み込めませんでした。");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
