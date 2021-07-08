package Controllers;

import java.util.Observable;
import java.util.Observer;


public class InventoryController implements Observer{
	private Screens.InverntoryReport page;
	private Malmo.Model model;
	
	public InventoryController(Screens.InverntoryReport page) {
		this.page = page;
		model = Malmo.Model.GetInstance();
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		page.fillData(model.getInventory());
	}
}
