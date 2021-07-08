package Controllers;

import java.util.Observable;
import java.util.Observer;

public class MedicalController implements Observer {
	private Screens.CreateMedicalReport page;
	private Malmo.Model model;
	
	public MedicalController(Screens.CreateMedicalReport page) {
		this.page = page;
		model = Malmo.Model.GetInstance();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Object[] obj = (Object[])arg1;
		String result =(String) model.CheckMedicalReportForm((String)obj[0],(String)obj[1],(String)obj[2]);
		
		//Display Action result for the user
		page.userMessage(result);
	
	
	}
}
