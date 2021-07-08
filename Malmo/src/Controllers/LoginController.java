package Controllers;

import java.util.Observable;
import java.util.Observer;

public class LoginController implements Observer{
	private Screens.LoginPage page;
	private Malmo.Model model;
	
	public LoginController(Screens.LoginPage page) {
		this.page = page;
		model = Malmo.Model.GetInstance();
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		Object[] obj = (Object[])arg1;
		Object[] result = model.checkValidUser((String)obj[0], (String)obj[1]);
		page.checkUser((boolean)result[0], (String)result[1], (String)result[2]);
	}
}