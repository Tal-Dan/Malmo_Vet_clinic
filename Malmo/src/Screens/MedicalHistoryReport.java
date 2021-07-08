package Screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controllers.ItemSearchResultsController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MedicalHistoryReport extends JFrame {

	private ObservableView observableView = new ObservableView();
	private Controllers.MedicalHistoryController controller = new Controllers.MedicalHistoryController(this);
	private JPanel contentPane;
	private JTextField ownerIdTxt;
	private JTable resultTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicalHistoryReport frame = new MedicalHistoryReport();
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
	public MedicalHistoryReport() {
		observableView.addObserver(controller);
		setTitle("Medical Report");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		resultTable = new JTable();
		resultTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Owner Id", "Pet Id", "Date", "Report"
			}
		));
		resultTable.setVisible(false);
		
		
		ownerIdTxt = new JTextField();
		ownerIdTxt.setColumns(10);
		
		JLabel ownerIdLbl = new JLabel("Enter Owner ID:");
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ownerIdTxt.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Owner ID Cannot Be Empty","Message",0);
				}
				else {
					observableView.notifyObs(new Object[] {ownerIdTxt.getText()});
				}
				
			}
		});
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(ownerIdLbl)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ownerIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(46)
							.addComponent(resultTable, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ownerIdLbl)
						.addComponent(ownerIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchBtn))
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(resultTable, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addGap(35))
		);
		contentPane.setLayout(gl_contentPane);
	}

	
	public void fillData(Object[][] data)
	{
		resultTable.setModel(new DefaultTableModel(
				data,
				new String[] {
					"Owner Id", "Pet Id", "Date", "Report"
				}
			));
		resultTable.setVisible(true);
		resultTable.updateUI();
		System.out.print(resultTable.isVisible());
		 
		
	}
}
