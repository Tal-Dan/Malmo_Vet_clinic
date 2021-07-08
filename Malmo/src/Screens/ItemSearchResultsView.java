package Screens;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controllers.ItemSearchResultsController;
import Controllers.SearchItemController;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class ItemSearchResultsView extends JFrame{
	
	private ObservableView observableView = new ObservableView();
	private Controllers.ItemSearchResultsController controller = new ItemSearchResultsController(this);
	private String itemId;
	private JPanel contentPannel;
	private JTable itmSearchResultTable;
	public String getItemId() {return itemId;}
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemSearchResultsView frame = new ItemSearchResultsView("");
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
	
	
	// constructor with parameter - the itemId
	public ItemSearchResultsView(String itemId)
	{
		this.itemId = itemId;
		setTitle("Search Results");
		observableView.addObserver(controller);		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPannel = new JPanel();
		contentPannel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPannel);
		
		itmSearchResultTable = new JTable();
		itmSearchResultTable.setRowSelectionAllowed(false);
		itmSearchResultTable.setFillsViewportHeight(true);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPannel);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(itmSearchResultTable, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(itmSearchResultTable, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		contentPannel.setLayout(gl_contentPane);
		observableView.notifyObs(null);
		
	}
	
	
	
	public void fillData(Object[][] data)
	{
		String[] colNames = {"Id", "Name", "Amount"};
		DefaultTableModel dtm = new DefaultTableModel(data, colNames);
		itmSearchResultTable.setModel(dtm);
		itmSearchResultTable.setEnabled(false);
//		JOptionPane.showMessageDialog(null, "Test message " + this.itemId,"Message",0);
	}

}
