public class FuelStation 
{
    private String fuelType;
    private double fuelPrice;
    private double quantityAvailable;
    
    FuelStation(String type, double price, double quant)
    {
        this.fuelType = type;
        this.fuelPrice = price;
        this.quantityAvailable = quant;
    }
    
    public String getFuelType()
    {
        return this.fuelType;
    }
    
    public void setFuelType(String type)
    {
        this.fuelType = type;
    }

    public double getPrice()
    {
        return this.fuelPrice;
    }
    
    public void setPrice(double price)
    {
        this.fuelPrice = price;
    }
    
    public double getFuelQuantAvailable()
    {
        return this.quantityAvailable;
    }
    
    public void addFuel(double quant)
    {
        this.quantityAvailable += quant;
    }
    
    public void sellFuel(double quantityDemanded, String type)
    {
        if(!type.equals(fuelType))
        {
            System.out.println("Sorry, this fuel station does not have the type of fuel required by your vehicle");
        }
        else if(quantityDemanded>quantityAvailable)
        {
            System.out.println("Sorry, the amount of fuel you demanded is not available with us");
        }
        else
        {
            quantityAvailable = quantityAvailable - quantityDemanded;
            transactionDetails(quantityDemanded);
        }
    }
    
    private void transactionDetails(double quantityDemanded)
    {
        double bill = fuelPrice*quantityDemanded;
        System.out.println("Fuel demanded by you has been filled in your vehicle. Total amount to be paid is : "+bill);
    }
}
