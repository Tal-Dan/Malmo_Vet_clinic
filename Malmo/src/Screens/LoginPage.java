package Screens;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordText;
	private JTextField userIdText;
	private ObservableView observableView = new ObservableView();
	private Controllers.LoginController controller = new Controllers.LoginController(this);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		observableView.addObserver(controller);
		this.setTitle("Malmo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel idLabel = new JLabel("User Id:");
		
		passwordText = new JPasswordField();
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				observableView.notifyObs(new Object[] {userIdText.getText(), new String(passwordText.getPassword())});
			}
		});
		
		JLabel passwordLabel = new JLabel("Password:");
		
		userIdText = new JTextField();
		userIdText.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(140)
					.addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(144, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(62, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addComponent(idLabel))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(userIdText, 0, 0, Short.MAX_VALUE)
						.addComponent(passwordText, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
					.addGap(99))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(51, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(idLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addComponent(loginBtn)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void checkUser(boolean isValidUser, String Profession, String userName)
	{
		if (!isValidUser)
		{
			JOptionPane.showMessageDialog(null, "Bad password or user name, please try again","Message",0);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Welcome " + userName,"Message",1);
			if (Profession.equals("General Worker"))
			{
				// WorkerPage
				MainScreenGeneralWorker view = new MainScreenGeneralWorker();
				dispose();
				view.setVisible(true);
			
			}
			else
			{
				// VetPage
				MainScreenVeterinarian view = new MainScreenVeterinarian(userIdText.getText());
				dispose();
				view.setVisible(true);
			}
		}
	}
}
