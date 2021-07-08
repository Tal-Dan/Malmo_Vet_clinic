package Controllers;

import java.util.Observable;
import java.util.Observer;

public class SearchClientController implements Observer {


	// data members
	private Screens.SearchClientView page;
	private Malmo.Model model;
		
	// constructor
	public SearchClientController(Screens.SearchClientView page)
	{
		this.page = page;
		model = Malmo.Model.GetInstance();
	}
	
	
	
	@Override
	public void update(Observable o, Object arg1)
	{
		Object[] obj = (Object[])arg1;
		Object[] result = model.isClientIdExist((String)(obj[0]));
		page.checkClientId((boolean)result[0]);
	}
	

}
