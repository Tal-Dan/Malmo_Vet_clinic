package Controllers;

import java.util.Observable;
import java.util.Observer;

public class MedicalHistoryController implements Observer {
	private Screens.MedicalHistoryReport page;
	private Malmo.Model model;
	
	public MedicalHistoryController(Screens.MedicalHistoryReport page) {
		this.page = page;
		model = Malmo.Model.GetInstance();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Object[] obj = (Object[])arg1;

		
		page.fillData(model.getAllOwnerReports((String)obj[0]));

	
	}

}
