public class Tester 
{
    public static void main(String[] args)
    {
        FuelStation station1 = new FuelStation("Petrol",60,100);
        FuelStation station2 = new FuelStation("Diesel",60,120);
        FuelStation station3 = new FuelStation("Petrol",65,60);
        
        Vehicle v1 = new Vehicle("Petrol", 120.0, station1);
        Vehicle v2 = new Vehicle("Petrol", 55.0, station3);
        Vehicle v3 = new Vehicle("Diesel", 110.0, station3);
        
        v1.askForFuel();
        v2.askForFuel();
        v3.askForFuel();
        
        v3.setFuelStationToGo(station2);
        v3.askForFuel();
    }
}

