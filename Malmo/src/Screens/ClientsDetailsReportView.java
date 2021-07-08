package Screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controllers.ClientsDetailsController;
import Controllers.InventoryController;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ClientsDetailsReportView extends JFrame{
	
	private JPanel contentPane;
	private ObservableView observableView = new ObservableView();
	private Controllers.ClientsDetailsController controller = new ClientsDetailsController(this);
	private JTable clientsDetailsTable;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientsDetailsReportView frame = new ClientsDetailsReportView();
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
	public ClientsDetailsReportView() {
		setTitle("Clients Details");
		observableView.addObserver(controller);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		clientsDetailsTable = new JTable();
		clientsDetailsTable.setRowSelectionAllowed(false);
		clientsDetailsTable.setFillsViewportHeight(true);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(clientsDetailsTable, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(clientsDetailsTable, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		observableView.notifyObs(null);
		
	}
	
	
	public void fillData(Object[][] data)
	{
		String[] colNames = {"Id", "First Name", "Last Name", "Phone Number", "Address"};
		DefaultTableModel dtm = new DefaultTableModel(data, colNames);
		clientsDetailsTable.setModel(dtm);
		clientsDetailsTable.setEnabled(false);
	}
}
