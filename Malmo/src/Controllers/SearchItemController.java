package Controllers;

import java.util.Observable;
import java.util.Observer;

public class SearchItemController implements Observer{

	
	// data members
	private Screens.SearchItemView page;
	private Malmo.Model model;
		
	// constructor
	public SearchItemController(Screens.SearchItemView page)
	{
		this.page = page;
		model = Malmo.Model.GetInstance();
	}
	
	
	
	@Override
	public void update(Observable o, Object arg1) {
		Object[] obj = (Object[])arg1;
		Object[] result = model.checkValidItemId((String)(obj[0]));
		page.checkItemId((boolean)result[0]);
		}
	}

