package Screens;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import Controllers.UpdateClientDetailsController;
import javax.swing.JButton;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateClientDetailsView extends JFrame
{
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldPhone;
	private JTextField textFieldAdress;
	private ObservableView observableView = new ObservableView();
	private Controllers.UpdateClientDetailsController controller = new UpdateClientDetailsController(this);
	private JComboBox<String> selectIdComboBox;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClientDetailsView frame = new UpdateClientDetailsView();
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
	public UpdateClientDetailsView() {
		observableView.addObserver(controller);
		setTitle("Update Client Details");
		setBounds(100, 100, 450, 418);
		
		JLabel lblNewLabel = new JLabel("Select Id:");
		lblNewLabel.setBounds(15, 19, 67, 20);
		
		// create comoBox and ask the controller to get client's id
		selectIdComboBox = new JComboBox<String>();
		selectIdComboBox.setBounds(109, 16, 83, 26);
		selectIdComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// when selecting one id from selectIdComboBox options
				
			}
		});
		
		// Call the controller to set selectIdComboBox
		observableView.notifyObs(new Object[] {"SET_COMBO_BOX"});
		
		JButton btnSaveUpdate = new JButton("Save & Update");
		btnSaveUpdate.setBounds(262, 304, 137, 29);
		
		JLabel lblNewLabel_1 = new JLabel("Enter new values:");
		lblNewLabel_1.setBounds(36, 92, 163, 22);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(36, 130, 76, 20);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(36, 172, 76, 20);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(36, 208, 56, 20);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(36, 244, 70, 20);
		getContentPane().setLayout(null);
		getContentPane().add(lblNewLabel);
		getContentPane().add(selectIdComboBox);
		getContentPane().add(lblPhone);
		getContentPane().add(btnSaveUpdate);
		getContentPane().add(lblLastName);
		getContentPane().add(lblFirstName);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(lblAddress);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(126, 127, 146, 26);
		getContentPane().add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(126, 168, 146, 26);
		getContentPane().add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(126, 206, 146, 26);
		getContentPane().add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		textFieldAdress = new JTextField();
		textFieldAdress.setBounds(126, 241, 146, 26);
		getContentPane().add(textFieldAdress);
		textFieldAdress.setColumns(10);
	}
	
	
	// this function get all client's id and add to the selectIdComboBox
	public void setClientsIdComboBox(Object[] allID)
	{
		for (Object id : allID)
		{
			selectIdComboBox.addItem((String)id);
		}
		selectIdComboBox.setVisible(true);
	}
}
