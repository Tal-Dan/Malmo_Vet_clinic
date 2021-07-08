package Screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainScreenVeterinarian extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreenVeterinarian frame = new MainScreenVeterinarian(null);
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
	public MainScreenVeterinarian(String userId) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Malmo");
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setName("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
		);
		
		JPanel reportsTab = new JPanel();
		reportsTab.setName("");
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
		
		JButton createReportBtn = new JButton("Create Medical Report");
		createReportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateMedicalReport newWindow = new CreateMedicalReport();
				newWindow.setVisible(true);
			}
		});
		GroupLayout gl_reportsTab = new GroupLayout(reportsTab);
		gl_reportsTab.setHorizontalGroup(
			gl_reportsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_reportsTab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_reportsTab.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(createReportBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(inventoryReportBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(medicalReportBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
					.addContainerGap(137, Short.MAX_VALUE))
		);
		gl_reportsTab.setVerticalGroup(
			gl_reportsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_reportsTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(medicalReportBtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(inventoryReportBtn)
					.addGap(18)
					.addComponent(createReportBtn)
					.addContainerGap(63, Short.MAX_VALUE))
		);
		reportsTab.setLayout(gl_reportsTab);
		
		JPanel scheduleTab = new JPanel();
		tabbedPane.addTab("Schedule", null, scheduleTab, null);
		
		JButton currentScheduleBtn = new JButton("Show Current Schedule");
		currentScheduleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewSchedule newWindow = new ViewSchedule(userId);
				newWindow.setVisible(true);
			}
		});
		GroupLayout gl_scheduleTab = new GroupLayout(scheduleTab);
		gl_scheduleTab.setHorizontalGroup(
			gl_scheduleTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_scheduleTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(currentScheduleBtn, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(137, Short.MAX_VALUE))
		);
		gl_scheduleTab.setVerticalGroup(
			gl_scheduleTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_scheduleTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(currentScheduleBtn)
					.addContainerGap(155, Short.MAX_VALUE))
		);
		scheduleTab.setLayout(gl_scheduleTab);
		
		JPanel searchTab = new JPanel();
		tabbedPane.addTab("Search", null, searchTab, null);
		
		JButton searchInventoryBtn = new JButton("Search Item In Inventory");
		searchInventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				SearchItemView newWindow = new SearchItemView();
				newWindow.setVisible(true);
			}
		});
		GroupLayout gl_searchTab = new GroupLayout(searchTab);
		gl_searchTab.setHorizontalGroup(
			gl_searchTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(searchInventoryBtn, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(137, Short.MAX_VALUE))
		);
		gl_searchTab.setVerticalGroup(
			gl_searchTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(searchInventoryBtn)
					.addContainerGap(155, Short.MAX_VALUE))
		);
		searchTab.setLayout(gl_searchTab);
		contentPane.setLayout(gl_contentPane);
	}
}
