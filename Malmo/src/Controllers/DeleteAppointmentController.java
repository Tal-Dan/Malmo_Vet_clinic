package Controllers;

import java.util.Observable;
import java.util.Observer;

public class DeleteAppointmentController  implements Observer{
	private Screens.DeleteAppointmentView page;
	private Malmo.Model model;
	
	public DeleteAppointmentController(Screens.DeleteAppointmentView page)
	{
		this.page = page;
		model = Malmo.Model.GetInstance();
	}
	

	@Override
	public void update(Observable o, Object arg1)
	{
		Object[] obj = (Object[])arg1;
		if((String)obj[0] == "Search")
		{
			Object[][] result = model.searchSchedule((String)obj[1]);
			if(result != null)
			{
				Object[][] data = new Object[result.length][2];
				for(int i = 0; i < result.length; i++)
				{
					data[i][0] = false;
					data[i][1] = result[i][0] + "," + result[i][1];
				}
				
				page.fillTable(data);
			}
			else
			{
				page.reportMessage(-1);
			}
		}
		else if ((String)obj[0] == "Delete")
		{
			String[][] appointments = (String[][])obj[2];
			for(int i = 0; i < appointments.length; i++)
			{
				if(!model.deleteAppointment((String)obj[1],appointments[i][0],appointments[i][1]))
				{
					page.reportMessage(-3);
					return;
				}
			}
			
			page.reportMessage(0);
		}
	}
}
