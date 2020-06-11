public class Vehicle 
{
    private String fuelType;
    private double quantRequired;
    private FuelStation stationToGo;
    
    Vehicle(String type, double quantRequired, FuelStation station)
    {
        this.fuelType = type;
        this.quantRequired = quantRequired;
        this.stationToGo = station;
    }
    
    public String getFuelType()
    {
        return this.fuelType;
    }
    
    public void setFuelType(String type)
    {
        this.fuelType = type;
    }
    
    public double getQuantReq()
    {
        return this.quantRequired;
    }
    
    public void setQuantReq(double quant)
    {
        this.quantRequired = quant;
    }
    
    public FuelStation getFuelStationToGo()
    {
        return this.stationToGo;
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
