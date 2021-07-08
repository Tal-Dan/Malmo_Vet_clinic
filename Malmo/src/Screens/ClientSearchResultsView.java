package Screens;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controllers.ClientSearchResultController;
import Controllers.SearchClientController;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ClientSearchResultsView extends JFrame{
	
	private ObservableView observableView = new ObservableView();
	private Controllers.ClientSearchResultController controller = new ClientSearchResultController(this); 		
	private JTable clientResultsTable;
	private JPanel contentPanel;
	private String clientId;
	
	public String getClientId() {return this.clientId; }
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientSearchResultsView frame = new ClientSearchResultsView("");
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

	// constructor with parameter - the client id
	public ClientSearchResultsView(String clientId)
	{
		this.clientId = clientId;
		observableView.addObserver(controller);
		setTitle("Search Results");
		
		contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		
		clientResultsTable = new JTable();
		clientResultsTable.setRowSelectionAllowed(false);
		clientResultsTable.setFillsViewportHeight(true);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPanel);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(clientResultsTable, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(14)
					.addComponent(clientResultsTable, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPane);
		observableView.notifyObs(null);
	}
	
	
	public void fillData(Object[][] data)
	{
		String[] colNames = {"Id", "First Name", "Last Name", "Phone Number", "Address"};
		DefaultTableModel dtm = new DefaultTableModel(data, colNames);
		clientResultsTable.setModel(dtm);
		clientResultsTable.setEnabled(false);
	}

}
