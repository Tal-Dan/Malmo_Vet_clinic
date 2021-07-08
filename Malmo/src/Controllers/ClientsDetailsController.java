package Controllers;

import java.util.Observable;
import java.util.Observer;

public class ClientsDetailsController implements Observer{

	// data members
	private Screens.ClientsDetailsReportView page;
	private Malmo.Model model;
	
	// constructor
	public ClientsDetailsController(Screens.ClientsDetailsReportView page)
	{
		this.page = page;
		model = Malmo.Model.GetInstance();
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		page.fillData(model.getClientsDetails());
	}
	
	
}
