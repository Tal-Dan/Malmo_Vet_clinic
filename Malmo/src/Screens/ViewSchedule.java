package Screens;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.ScheduleController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewSchedule extends JFrame {

	private JPanel contentPane;
	private JTextField vetIdText;
	private JTextPane resultText;
	private ObservableView observableview = new ObservableView();
	private Controllers.ScheduleController controller = new ScheduleController(this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSchedule frame = new ViewSchedule(null);
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
	public ViewSchedule(String vetId) {
		observableview.addObserver(controller);
		setTitle("Malmo - Schedule");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		vetIdText = new JTextField();
		vetIdText.setColumns(10);
		
		JLabel vetIdLbl = new JLabel("Vet Id:");
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				observableview.notifyObs(new Object[] {vetIdText.getText()});
			}
		});
		
		resultText = new JTextPane();
		resultText.setEditable(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(vetIdLbl)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(vetIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchBtn))
						.addComponent(resultText, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(vetIdLbl)
						.addComponent(vetIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchBtn))
					.addGap(27)
					.addComponent(resultText, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		// If the page was opened by a Veterinarian, Show his Schedule
		if(vetId != null)
		{
			vetIdText.setText(vetId);
			vetIdText.setEnabled(false);
			searchBtn.doClick();
			searchBtn.setEnabled(false);
		}
	}
	
	public void fillResults(String result)
	{
		resultText.setText(result);
	}
}
