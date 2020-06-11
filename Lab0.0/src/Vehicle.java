public class Vehicle
{
	String fuelType;
    double quantRequired;
    FuelStation stationToGo;
    
    Vehicle(String ft,  double quantity, FuelStation FS)
    {
    	this.fuelType=ft;
    	this.quantRequired=quantity;
    	this.stationToGo=FS;
    }
    public void setFuelStationToGo(FuelStation station)
    {
        this.stationToGo = station;
    }
    
    public void askForFuel()
    {
        this.stationToGo.sellFuel(this.quantRequired, this.fuelType);
    }
}
