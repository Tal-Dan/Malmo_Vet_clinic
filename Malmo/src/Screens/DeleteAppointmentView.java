package Screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controllers.DeleteAppointmentController;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteAppointmentView extends JFrame {

	private JPanel contentPane;
	private JTable appointmentTable;
	private JTextField vetIdText;
	private JButton deleteBtn;
	private JButton SearchBtn;
	private ObservableView observableview = new ObservableView();
	private Controllers.DeleteAppointmentController controller = new DeleteAppointmentController(this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteAppointmentView frame = new DeleteAppointmentView();
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
	public DeleteAppointmentView() {
		observableview.addObserver(controller);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] selectedAppointments = appointmentsToDelete();
				if(selectedAppointments == null)
				{
					reportMessage(-2);
				}
				else
				{
					observableview.notifyObs(new Object[] {"Delete", vetIdText.getText(), selectedAppointments});
				}
			}
		});
		deleteBtn.setEnabled(false);
		
		vetIdText = new JTextField();
		vetIdText.setColumns(10);
		
		JLabel vetIdLbl = new JLabel("Vet Id:");
		
		SearchBtn = new JButton("Search");
		SearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) appointmentTable.getModel();
				model.setRowCount(0);
				observableview.notifyObs(new Object[] {"Search", vetIdText.getText()});
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(15)
							.addComponent(deleteBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(vetIdLbl)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(vetIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(SearchBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(vetIdLbl)
						.addComponent(vetIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(SearchBtn))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(deleteBtn))
		);
		
		appointmentTable = new JTable();
		scrollPane.setViewportView(appointmentTable);
		appointmentTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Delete", "Appoinments"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		appointmentTable.getColumnModel().getColumn(0).setResizable(false);
		appointmentTable.getColumnModel().getColumn(1).setResizable(false);
		appointmentTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void fillTable(Object[][] data)
	{
		appointmentTable.setModel(new DefaultTableModel(data, new String[] {"Delete", "Appoinments"})
		{
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					true, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		});
			appointmentTable.getColumnModel().getColumn(0).setResizable(false);
			appointmentTable.getColumnModel().getColumn(1).setResizable(false);
			appointmentTable.getColumnModel().getColumn(0).setPreferredWidth(1);
			deleteBtn.setEnabled(true);
			
			
			vetIdText.getDocument().addDocumentListener(new DocumentListener() {
				  public void changedUpdate(DocumentEvent e) {
					  	  deleteBtn.setEnabled(false);
					  }
					  public void removeUpdate(DocumentEvent e) {
						  deleteBtn.setEnabled(false);
					  }
					  public void insertUpdate(DocumentEvent e) {
						  deleteBtn.setEnabled(false);
					  }
			});
	}
	
	//// return code -1 - no appointments found
	//// return code -2 - no appointments selected
	//// return code -3 - unexpected error
	//// return code  0 - appointments deleted
	public void reportMessage(int returnCode)
	{
		switch(returnCode)
		{
		case 0: 
			JOptionPane.showMessageDialog(null, "Appointments Deleted","Message",1);
			SearchBtn.doClick();
			break;
		case -1:
			JOptionPane.showMessageDialog(null, "No Appointments Found","Message",0);
			deleteBtn.setEnabled(false);
			break;
		case -2:
			JOptionPane.showMessageDialog(null, "No Appointments Selected","Message",0);
			break;
		case -3:
			JOptionPane.showMessageDialog(null, "Unexpected Error","Message",0);
			deleteBtn.setEnabled(false);
			break;
		}
	}
	
	public String[][] appointmentsToDelete()
	{
		String[][] data = null;
		int selectionCount = 0;
		String[] stringSplit;
		int index = 0;
		
		for(int i = 0; i < appointmentTable.getRowCount(); i++)
		{
			if((boolean)appointmentTable.getValueAt(i, 0) == true)
			{
				selectionCount++;
			}
		}
		if(selectionCount != 0)
		{
			data = new String[selectionCount][2];
			for(int i = 0; i < appointmentTable.getRowCount(); i++)
			{
				if((boolean)appointmentTable.getValueAt(i, 0) == true)
				{
					stringSplit =  ((String)appointmentTable.getValueAt(i, 1)).split(",");
					data[index][0] = stringSplit[0];
					data[index][1] = stringSplit[1];
					index++;
				}
			}
		}
		
		return data;
	}
}
