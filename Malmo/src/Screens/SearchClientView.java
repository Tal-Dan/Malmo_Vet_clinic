package Screens;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controllers.SearchClientController;
import Controllers.SearchItemController;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchClientView extends JFrame{
	
	
	private ObservableView observableView = new ObservableView();
	private Controllers.SearchClientController controller = new SearchClientController(this);
	private JTextField textFieldClientId;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchClientView frame = new SearchClientView();
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
	public SearchClientView()
	{
		observableView.addObserver(controller);
		setTitle("Search Client");
		
		JPanel contentPannel = new JPanel();
		getContentPane().add(contentPannel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Client Id:");
		
		textFieldClientId = new JTextField();
		textFieldClientId.setColumns(10);
		
		JButton btnSearchClient = new JButton("Search");
		btnSearchClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				observableView.notifyObs(new Object[] {textFieldClientId.getText()});
			}
		});
		
		GroupLayout gl_contentPannel = new GroupLayout(contentPannel);
		gl_contentPannel.setHorizontalGroup(
			gl_contentPannel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPannel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textFieldClientId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(186, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPannel.createSequentialGroup()
					.addContainerGap(201, Short.MAX_VALUE)
					.addComponent(btnSearchClient)
					.addGap(112))
		);
		gl_contentPannel.setVerticalGroup(
			gl_contentPannel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPannel.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPannel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldClientId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
					.addComponent(btnSearchClient)
					.addContainerGap())
		);
		contentPannel.setLayout(gl_contentPannel);
		setBounds(100, 100, 450, 300);

	}
	
	
	// this function open client search result new window in case the client id exist
	// else output message box
	public void checkClientId(boolean isClientIdExist)
	{
		if (!isClientIdExist)
		{
			JOptionPane.showMessageDialog(null, "No such client with given id!\nPlease try again","Message",0);
		}
		else
		{
			ClientSearchResultsView clientResultsView = new ClientSearchResultsView(textFieldClientId.getText());
			clientResultsView.setVisible(true);
		}
	}
	
	
}
