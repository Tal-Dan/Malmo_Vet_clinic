package Screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateMedicalReport extends JFrame {

	private JPanel contentPane;
	private ObservableView observableView = new ObservableView();
	private Controllers.MedicalController controller = new Controllers.MedicalController(this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateMedicalReport frame = new CreateMedicalReport();
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
	public CreateMedicalReport() {
		observableView.addObserver(controller);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Malmo");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTextPane fillerIdText = new JTextPane();
		
		JTextPane petIdText = new JTextPane();
		
		JTextPane reportText = new JTextPane();
		
		JLabel fillerIdLbl = new JLabel("Worker ID:");
		
		JLabel petIdLbl = new JLabel("Pet ID:");
		
		JLabel reportLbl = new JLabel("Report:");
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				observableView.notifyObs(new Object[] {fillerIdText.getText(),petIdText.getText(),reportText.getText()});
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(fillerIdLbl)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(petIdLbl)
								.addComponent(reportLbl))
							.addGap(18)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(submitBtn)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(reportText, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(petIdText, Alignment.LEADING)
								.addComponent(fillerIdText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(fillerIdLbl)
						.addComponent(fillerIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(petIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(petIdLbl))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(reportText, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(reportLbl))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(submitBtn)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void userMessage(String msg) {
		
			if(msg.equals("Valid")) {
				JOptionPane.showMessageDialog(null, "Medical Report Created Successfully","Message",1);
			}
			else if(msg.equals("DB")) {
				JOptionPane.showMessageDialog(null, "Technical Error, Please Try Again ","Message",0);
			}
			else if(msg.equals("Report")) {
				JOptionPane.showMessageDialog(null, "Report Text Cannot Be Empty ","Message",0);
		
			}
			else{
				JOptionPane.showMessageDialog(null, msg+" Is Not Valid","Message",0);
			}
			
	}
}
