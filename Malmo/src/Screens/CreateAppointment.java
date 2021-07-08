package Screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import Controllers.AppointmentController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Dictionary;
import java.util.Hashtable;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Dimension;

public class CreateAppointment extends JFrame {
	private Controllers.AppointmentController controller = new AppointmentController(this);
	private ObservableView observableview = new ObservableView();
	private JPanel contentPane;
	private JTable appointmentTable;
	private boolean[][] enabledTableCells;
	private JButton scheduleAppointmentBtn;
	private JButton appointmentSearch;
	private int userSelectedRow = -1;
	private int userSelectedCol = -1;
	private JTextField vetIdText;
	private JTextField petIdText;
	private LocalTime startTime = LocalTime.parse("10:00");
	private LocalTime endTime = LocalTime.parse("18:00");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAppointment frame = new CreateAppointment();
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
	public CreateAppointment() {
		observableview.addObserver(controller);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 630, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Malmo - Create Appointment");
		
		JLabel lblNewLabel = new JLabel("Vet ID:");
		
		appointmentSearch = new JButton("Search");
		appointmentSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				observableview.notifyObs(new Object[] {"Search",vetIdText.getText()});
			}
		});
		
		JLabel lblPetId = new JLabel("Pet ID:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		scheduleAppointmentBtn = new JButton("Schedule Appointment");
		scheduleAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				observableview.notifyObs(new Object[] {"Schedule", appointmentTable.getColumnName(userSelectedCol), startTime.plusHours(userSelectedRow).toString(), vetIdText.getText(), petIdText.getText()});
			}
		});
		
		vetIdText = new JTextField();
		vetIdText.setColumns(10);
		
		petIdText = new JTextField();
		petIdText.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
						.addComponent(scheduleAppointmentBtn)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPetId, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(vetIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(appointmentSearch))
								.addComponent(petIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(119)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(vetIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel))
						.addComponent(appointmentSearch))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(petIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPetId))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scheduleAppointmentBtn)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		appointmentTable = new JTable();
		appointmentTable.setRowSelectionAllowed(false);
		scrollPane.setViewportView(appointmentTable);
		String[] colNames = new String[] {"Times", LocalDate.now().toString(), LocalDate.now().plusDays(1).toString(), LocalDate.now().plusDays(2).toString(), LocalDate.now().plusDays(3).toString(), LocalDate.now().plusDays(4).toString(), LocalDate.now().plusDays(5).toString(), LocalDate.now().plusDays(6).toString()};
		appointmentTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"10:00", null, null, null, null, null, null, null},
				{"11:00", null, null, null, null, null, null, null},
				{"12:00", null, null, null, null, null, null, null},
				{"13:00", null, null, null, null, null, null, null},
				{"14:00", null, null, null, null, null, null, null},
				{"15:00", null, null, null, null, null, null, null},
				{"16:00", null, null, null, null, null, null, null},
				{"17:00", null, null, null, null, null, null, null},
			},
			colNames
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return enabledTableCells[row][column];
			}
		});
		appointmentTable.getColumnModel().getColumn(0).setResizable(false);
		appointmentTable.getColumnModel().getColumn(1).setResizable(false);
		appointmentTable.getColumnModel().getColumn(2).setResizable(false);
		appointmentTable.getColumnModel().getColumn(3).setResizable(false);
		appointmentTable.getColumnModel().getColumn(4).setResizable(false);
		appointmentTable.getColumnModel().getColumn(5).setResizable(false);
		appointmentTable.getColumnModel().getColumn(6).setResizable(false);
		appointmentTable.getColumnModel().getColumn(7).setResizable(false);
		contentPane.setLayout(gl_contentPane);
	
		enabledTableCells = new boolean[appointmentTable.getRowCount()][appointmentTable.getColumnCount()];
		clearTable();
		scheduleAppointmentBtn.setEnabled(false);

		appointmentTable.addMouseListener(new MouseAdapter(){
			@Override
            public void mouseClicked(MouseEvent e){
				if(enabledTableCells[appointmentTable.rowAtPoint(e.getPoint())][appointmentTable.columnAtPoint(e.getPoint())])
				{
					if(userSelectedCol == -1)
					{
						userSelectedCol = appointmentTable.columnAtPoint(e.getPoint());
						userSelectedRow = appointmentTable.rowAtPoint(e.getPoint());
					}
					else
					{
						if(userSelectedCol == appointmentTable.columnAtPoint(e.getPoint()) && userSelectedRow == appointmentTable.rowAtPoint(e.getPoint()))
						{
							userSelectedCol = -1;
							userSelectedRow = -1;
						}
						else
						{
							appointmentTable.setValueAt(false, userSelectedRow, userSelectedCol);
							userSelectedCol = appointmentTable.columnAtPoint(e.getPoint());
							userSelectedRow = appointmentTable.rowAtPoint(e.getPoint());
						}
					}   
				}
            }
        });
		
		vetIdText.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  	  scheduleAppointmentBtn.setEnabled(false);
				  }
				  public void removeUpdate(DocumentEvent e) {
					  scheduleAppointmentBtn.setEnabled(false);
				  }
				  public void insertUpdate(DocumentEvent e) {
					  scheduleAppointmentBtn.setEnabled(false);
				  }
		});
	}
	
	public void fillTable(Object[][] data)
	{
		Dictionary<String,Integer> dates = new Hashtable<String,Integer>();
		Dictionary<String,Integer> hours = new Hashtable<String,Integer>();
		LocalTime tempStartTime = startTime;
		clearTable();
		scheduleAppointmentBtn.setEnabled(false);
		if (data == null)
		{
			scheduleResult(-2);
		}
		else if(data.length != 0)
		{
			for(int i = 0; i < 7; i++)
			{
				dates.put(LocalDate.now().plusDays(i).toString(), (i + 1));
			}
			int c = 0;
			while(!tempStartTime.equals(endTime))
			{
				hours.put(tempStartTime.toString(), c);
				c++;
				tempStartTime = tempStartTime.plusHours(1);
			}
			
			for (int i = 0; i < data.length; i++)
			{
				appointmentTable.setValueAt(true,hours.get(data[i][1].toString()),dates.get(data[i][0].toString()));
				enabledTableCells[hours.get(data[i][1].toString())][dates.get(data[i][0].toString())] = false;
			}
			scheduleAppointmentBtn.setEnabled(true);
		}
	}
	
	public void clearTable()
	{
		userSelectedCol = -1;
		userSelectedRow = -1;
		for (int i = 0; i < enabledTableCells.length; i++)
		{
			for (int j = 0; j < enabledTableCells[i].length; j++)
			{
				if (j == 0)
				{
					enabledTableCells[i][j] = false;
				}
				else
				{
					enabledTableCells[i][j] = true;
				}
			}
		}
		for(int i = 0; i < appointmentTable.getRowCount(); i++)
		{
			for(int j = 1; j < appointmentTable.getColumnCount(); j++)
			{
				appointmentTable.setValueAt(false, i, j);
			}
		}
	}
	
	//// return code  1 - Success
	//// return code  0 - No date selected
	//// return code -1 - No animal id
	//// return code -2 - No vet id
	//// return code -3 - Unexpected Error
	public void scheduleResult(int returnCode)
	{
		switch(returnCode)
		{
		case 1:
			JOptionPane.showMessageDialog(null, "Appointment Made","Message",1);
			appointmentSearch.doClick();
			break;
		case 0:
			JOptionPane.showMessageDialog(null, "No date was selected","Message",0);
			break;
		case -1:
			JOptionPane.showMessageDialog(null, "The Animal Id is incorrect","Message",0);
			break;
		case -2:
			JOptionPane.showMessageDialog(null, "The Vet Id is incorrect","Message",0);
			break;
		case -3:
			JOptionPane.showMessageDialog(null, "Unexpected Error","Message",0);
			break;
		}
	}
}
