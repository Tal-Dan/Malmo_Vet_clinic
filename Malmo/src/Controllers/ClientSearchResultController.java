package Controllers;

import java.util.Observable;
import java.util.Observer;

public class ClientSearchResultController implements Observer{

	// data members
	private Screens.ClientSearchResultsView page;
	private Malmo.Model model;

	
	// constructor
	public ClientSearchResultController(Screens.ClientSearchResultsView page)
	{
		this.page = page;
		model = Malmo.Model.GetInstance();
	}
	
	
	@Override
	public void update(Observable o, Object arg)
	{
		String clientId = page.getClientId();
		page.fillData(model.getClientInformation(clientId));
		
	}

}
