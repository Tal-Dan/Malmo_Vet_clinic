package Controllers;

import java.util.Observable;
import java.util.Observer;

public class ScheduleController implements Observer{
	private Screens.ViewSchedule page;
	private Malmo.Model model;
	
	public ScheduleController(Screens.ViewSchedule page)
	{
		this.page = page;
		model = Malmo.Model.GetInstance();
	}
	

	@Override
	public void update(Observable o, Object arg1)
	{
		Object[] obj = (Object[])arg1;
		Object[][] result = model.getSchedule((String)obj[0]);
		String resultStr = "No Appointments Found";
		if(result != null)
		{
			resultStr = "";
			for(int i = 0; i < result.length; i++)
			{
				resultStr += "Appointment for " + model.getPetName((String)result[i][2]) + " At " + result[i][0] + "," + result[i][1] + "\n";
			}	
		}
		
		page.fillResults(resultStr);
	}
}
