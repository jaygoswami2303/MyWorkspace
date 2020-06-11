
public class FuelStation
{
	String fuelType;
    double fuelPrice;
    double quantityAvailable;
    
    FuelStation(String ft,double fp,double quantity)
    {
    	this.fuelType=ft;
    	this.fuelPrice=fp;
    	this.quantityAvailable=quantity;
    }
    
   public void sellFuel(double quantityDemanded, String type)
    {
        if(type!=fuelType)
        	System.out.println("Fuel type is not available at this station.");
        else if(quantityDemanded>quantityAvailable)
        	System.out.println("Fuel quantity is not available at this station.");
        else
        {
        	quantityAvailable=quantityAvailable-quantityDemanded;
        	transactionDetails(quantityDemanded);
        }
    }
    
    private void transactionDetails(double quantityDemanded)
    {
        double bill;
        bill=quantityDemanded*fuelPrice;
        System.out.print("The total bill for the fuel is ");
        System.out.println(bill);
    }
}
