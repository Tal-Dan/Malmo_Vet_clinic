package Controllers;

import java.util.Observer;
import java.util.Observable;

public class AppointmentController implements Observer{
	private Screens.CreateAppointment page;
	private Malmo.Model model;
	
	public AppointmentController(Screens.CreateAppointment page)
	{
		this.page = page;
		model = Malmo.Model.GetInstance();
	}
	

	//// return code  1 - Success
	//// return code  0 - No date selected
	//// return code -1 - No animal id
	//// return code -2 - No vet id
	//// return code -3 - Unexpected Error
	@Override
	public void update(Observable o, Object arg1)
	{
		Object[] obj = (Object[])arg1;
		if((String)obj[0] == "Search")
		{
			Object[][] result = model.searchSchedule((String)obj[1]);
			page.fillTable(result);
		}
		else if((String)obj[0] == "Schedule")
		{
			if(((String)obj[1]).equals(""))
			{
				page.scheduleResult(0);
			}
			else if (!model.isPetInDatabase((String)obj[4]))
			{
				page.scheduleResult(-1);
			}
			else if(!model.isVetInDatabase((String)obj[3]))
			{
				page.scheduleResult(-2);
			}
			else
			{
				if(model.createAppointment((String)obj[3], (String)obj[4], (String)obj[1], (String)obj[2]))
				{
					page.scheduleResult(1);
				}
				else
				{
					page.scheduleResult(-3);
				}
			}
		}
	}
}
