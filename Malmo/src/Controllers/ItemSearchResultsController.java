package Controllers;

import java.util.Observable;
import java.util.Observer;

public class ItemSearchResultsController implements Observer{
	
	// data members
	private Screens.ItemSearchResultsView page;
	private Malmo.Model model;
			
	// constructor
	public ItemSearchResultsController(Screens.ItemSearchResultsView page)
	{
		this.page = page;
		model = Malmo.Model.GetInstance();
	}
		
	@Override
	public void update(Observable o, Object arg1)
	{
		String itemId = page.getItemId();
		page.fillData(model.getItemInformation(itemId));
	}

}
