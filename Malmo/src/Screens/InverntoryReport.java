package Screens;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controllers.InventoryController;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;

public class InverntoryReport extends JFrame {

	private JPanel contentPane;
	private ObservableView observableView = new ObservableView();
	private Controllers.InventoryController controller = new InventoryController(this);
	private JTable inventoryTable;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InverntoryReport frame = new InverntoryReport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InverntoryReport() {
		this.setTitle("Inventory Report");
		observableView.addObserver(controller);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		inventoryTable = new JTable();
		inventoryTable.setRowSelectionAllowed(false);
		inventoryTable.setFillsViewportHeight(true);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(inventoryTable, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(inventoryTable, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		observableView.notifyObs(null);
	}
	
	public void fillData(Object[][] data)
	{
		String[] colNames = {"Id", "Name", "Amount"};
		DefaultTableModel dtm = new DefaultTableModel(data, colNames);
		inventoryTable.setModel(dtm);
		inventoryTable.setEnabled(false);
	}
}
