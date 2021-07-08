package Screens;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;


import Controllers.SearchItemController;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class SearchItemView extends JFrame {
	
	
	private ObservableView observableView = new ObservableView();
	private Controllers.SearchItemController controller = new SearchItemController(this);
	private JTextField txtItemId;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchItemView frame = new SearchItemView();
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
	public SearchItemView()
	{
		setTitle("Search Item In Inventory");
		observableView.addObserver(controller);
		
		JPanel contentPane = new JPanel();		
		getContentPane().add(contentPane, BorderLayout.CENTER);
		setBounds(100, 100, 450, 300);

		txtItemId = new JTextField();
		txtItemId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Item Id:");
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				observableView.notifyObs(new Object[] {txtItemId.getText()});
			}
		});
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(txtItemId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(190, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(181, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(168))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(245, Short.MAX_VALUE)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(182))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtItemId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(85)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	
	public void checkItemId(boolean isValidId)
	{
		if (!isValidId)
		{
			JOptionPane.showMessageDialog(null, "Item id was not found!\nPlease try again","Message",0);
		}
		else
		{
			ItemSearchResultsView itemResultsView = new ItemSearchResultsView(txtItemId.getText());
			itemResultsView.setVisible(true);
		}
	}
}
