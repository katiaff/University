package gui.customelements;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
			int arg5) {
		super.getTableCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5);
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
		return this;
	}

}
