package jp.co.geo.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.*;

public class TableSortListener implements SelectionListener {
        private Table table;
        
        public TableSortListener(Table table) {
                this.table = table;
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
		public void widgetSelected(SelectionEvent e) {
                final int colIndex;
                
                colIndex = getColumnIndex(e);
                
                if (colIndex == -1) {
                	// NOOP
                	return ;
                }
                                
                TableItem[] items = table.getItems();
                
                Object array[] = getItemsText();
                
                Arrays.sort(array, new Comparator(){
                        public int compare(Object o1, Object o2) {
                                String s1 = ((String[])o1)[colIndex];
                                String s2 = ((String[])o2)[colIndex];
                                return s1.compareTo(s2);
                        }
                        public boolean equals(Object arg0) {
                                return false;
                        }
                });
                for (int i = 0; i < items.length; i++){
                        items[i].dispose();
                        items[i] = new TableItem(table, SWT.NONE);
                        String [] strs = (String[])array[i];
                        items[i].setText(strs);
                }
                table.redraw();

        }

        public void widgetDefaultSelected(SelectionEvent e) {
        }
        
        private int getColumnIndex(SelectionEvent e) {
        	TableColumn columns[] = table.getColumns();
            
            for (int i = 0; i < columns.length; i++){
            	if ( e.getSource() == columns[i]) {
            		return i;
            	}
            }
            return -1;
        }
        
        private Object[] getItemsText() {
        	TableItem items[] = table.getItems();
        	ArrayList<String[]> array = new ArrayList<String[]>();
            for (TableItem item : items) {
            	String[] text = new String[table.getColumnCount()];
            	for (int i = 0; i < text.length; i++) {
            		text[i] = item.getText(i);
            	}
            	
            	array.add(text);
            }
            
            return array.toArray();
        }
}