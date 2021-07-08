package Malmo;

import java.util.Date;

public class InventoryReport implements Report {
	// General properties 
		private String reportName;
		private String reportCreator;
		private String reportText;
		private Date creationDate;
		
		// Constructor
		public InventoryReport(String name, String creator, String text)
		{
			this.reportName = name;
			this.reportCreator = creator;
			this.reportText = text;
			this.creationDate = new Date();
		}
		
		// Get and Set Methods
		
		public String getReportName()
		{
			return this.reportName;
		}
		
		public void setReportName(String name)
		{
			this.reportName = name;
		}
		
		public String getReportCreator()
		{
			return this.reportCreator;
		}
		
		public void setReportCreator(String creator)
		{
			this.reportCreator = creator;
		}
		
		public String getReportText()
		{
			return this.reportText;
		}
		
		public void setReportText(String text)
		{
			this.reportText = text;
		}
		
		public Date GetCreationDate()
		{
			return this.creationDate;
		}
		
		public void SetCreationDate(Date date)
		{
			this.creationDate = date;
		}
		
		
		// Methods
		public void showReport() 
		{
			
		}
		public void deleteReport() 
		{
			
		}
		public void createReport() 
		{
		
		}
}
