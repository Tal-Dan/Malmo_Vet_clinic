package Controllers;

import java.util.Observable;
import java.util.Observer;

public class UpdateClientDetailsController implements Observer{

	// data members
	private Screens.UpdateClientDetailsView page;
	private Malmo.Model model;
			
	// constructor
	public UpdateClientDetailsController(Screens.UpdateClientDetailsView page)
	{
		this.page = page;
		model = Malmo.Model.GetInstance();
	}


	@Override
	public void update(Observable o, Object arg1)
	{
		if (((Object[])arg1)[0] == "SET_COMBO_BOX")
		{
			Object[] allId = model.retrieveAllClientsId();
			page.setClientsIdComboBox(allId);
		}
		
	}
		
}
