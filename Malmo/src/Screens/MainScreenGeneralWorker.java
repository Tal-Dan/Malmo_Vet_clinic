package Screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;

public class MainScreenGeneralWorker extends JFrame {

	private JPanel contentPane;
	private final JTextPane textPane = new JTextPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreenGeneralWorker frame = new MainScreenGeneralWorker();
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
	public MainScreenGeneralWorker() {
		setTitle("Malmo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel reportsTab = new JPanel();
		tabbedPane.addTab("Reports", null, reportsTab, null);
		
		JButton medicalReportBtn = new JButton("Get Medical History Report");
		medicalReportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MedicalHistoryReport newWindow = new MedicalHistoryReport();
				newWindow.setVisible(true);
			}
		});
		
		JButton inventoryReportBtn = new JButton("Get Inventory Report");
		inventoryReportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InverntoryReport newWindow = new InverntoryReport();
				newWindow.setVisible(true);
			}
		});
		
		JButton clientsDetailsReportBtn = new JButton("Get Clients Details Report");
		clientsDetailsReportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientsDetailsReportView newWindow = new ClientsDetailsReportView();
				newWindow.setVisible(true);
				
			}
		});
		GroupLayout gl_reportsTab = new GroupLayout(reportsTab);
		gl_reportsTab.setHorizontalGroup(
			gl_reportsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_reportsTab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_reportsTab.createParallelGroup(Alignment.LEADING)
						.addComponent(medicalReportBtn, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addComponent(inventoryReportBtn, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addComponent(clientsDetailsReportBtn, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		gl_reportsTab.setVerticalGroup(
			gl_reportsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_reportsTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(medicalReportBtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(inventoryReportBtn)
					.addGap(18)
					.addComponent(clientsDetailsReportBtn)
					.addContainerGap(63, Short.MAX_VALUE))
		);
		reportsTab.setLayout(gl_reportsTab);
		
		JPanel scheduleTab = new JPanel();
		tabbedPane.addTab("Schedule", null, scheduleTab, null);
		
		JButton veterinarianScheduleReportBtn = new JButton("Get Veterinarian Schedule Report");
		veterinarianScheduleReportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewSchedule newWindow = new ViewSchedule(null);
				newWindow.setVisible(true);
			}
		});
		
		JButton createNewAppointmentBtn = new JButton("Create New Appointment");
		createNewAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAppointment newWindow = new CreateAppointment();
				newWindow.setVisible(true);
			}
		});
		
		JButton deleteAnAppointmentBtn = new JButton("Delete An Appointment");
		deleteAnAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteAppointmentView newWindow = new DeleteAppointmentView();
				newWindow.setVisible(true);
			}
		});
		GroupLayout gl_scheduleTab = new GroupLayout(scheduleTab);
		gl_scheduleTab.setHorizontalGroup(
			gl_scheduleTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_scheduleTab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_scheduleTab.createParallelGroup(Alignment.LEADING)
						.addComponent(deleteAnAppointmentBtn, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_scheduleTab.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(createNewAppointmentBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(veterinarianScheduleReportBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(131, Short.MAX_VALUE))
		);
		gl_scheduleTab.setVerticalGroup(
			gl_scheduleTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_scheduleTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(veterinarianScheduleReportBtn)
					.addGap(18)
					.addComponent(createNewAppointmentBtn)
					.addGap(18)
					.addComponent(deleteAnAppointmentBtn)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		scheduleTab.setLayout(gl_scheduleTab);
		
		JPanel searchTab = new JPanel();
		tabbedPane.addTab("Search", null, searchTab, null);
		
		JButton searchAnEquipmentBtn = new JButton("Search Item In Inventory");
		searchAnEquipmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				SearchItemView newWindow = new SearchItemView();
				newWindow.setVisible(true);
			}
		});
		
		JButton searchClientBtn = new JButton("Search Client");
		searchClientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				SearchClientView newWindow = new SearchClientView();
				newWindow.setVisible(true);
			}
		});
		GroupLayout gl_searchTab = new GroupLayout(searchTab);
		gl_searchTab.setHorizontalGroup(
			gl_searchTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchTab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_searchTab.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(searchClientBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(searchAnEquipmentBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
					.addContainerGap(179, Short.MAX_VALUE))
		);
		gl_searchTab.setVerticalGroup(
			gl_searchTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(searchAnEquipmentBtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(searchClientBtn)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		searchTab.setLayout(gl_searchTab);
		
		JPanel addTab = new JPanel();
		tabbedPane.addTab("Add", null, addTab, null);
		
		JButton addNewClientBtn = new JButton("Add New Client");
		addNewClientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_addTab = new GroupLayout(addTab);
		gl_addTab.setHorizontalGroup(
			gl_addTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(addNewClientBtn, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(148, Short.MAX_VALUE))
		);
		gl_addTab.setVerticalGroup(
			gl_addTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(addNewClientBtn)
					.addContainerGap(155, Short.MAX_VALUE))
		);
		addTab.setLayout(gl_addTab);
		
		JPanel updateTab = new JPanel();
		tabbedPane.addTab("Update", null, updateTab, null);
		
		JButton updateClientDetailsBtn = new JButton("Update Client Details");
		updateClientDetailsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				UpdateClientDetailsView newWindow = new UpdateClientDetailsView();
				newWindow.setVisible(true);
			}
		});
		GroupLayout gl_updateTab = new GroupLayout(updateTab);
		gl_updateTab.setHorizontalGroup(
			gl_updateTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_updateTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(updateClientDetailsBtn, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
		);
		gl_updateTab.setVerticalGroup(
			gl_updateTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_updateTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(updateClientDetailsBtn)
					.addContainerGap(155, Short.MAX_VALUE))
		);
		updateTab.setLayout(gl_updateTab);
	}
}
