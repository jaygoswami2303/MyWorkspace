/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *
 * @author Dhruv Shah
 */
public class Vehicle 
{
    String fuelType;
    double quantRequired;
    FuelStation stationToGo;
    
  
    public void setFuelStationToGo(FuelStation station)
    {
        this.stationToGo = station;
    }
    
    public void askForFuel()
    {
        this.stationToGo.sellFuel(this.quantRequired, this.fuelType);
    }
}


 