package gui.customelements;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class NonEditableTable extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NonEditableTable() {
		// TODO Auto-generated constructor stub
	}

	public NonEditableTable(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		// TODO Auto-generated constructor stub
	}

	public NonEditableTable(Vector<?> columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public NonEditableTable(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public NonEditableTable(Vector<?> data, Vector<?> columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	public NonEditableTable(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}

}
