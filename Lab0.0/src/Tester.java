public class Tester
{
	public static void main(String[] args)
	{
		FuelStation FS1 = new FuelStation("Petrol",62.9,100);
		FuelStation FS2 = new FuelStation("Diesel",51.2,150);
		FuelStation FS3 = new FuelStation("Petrol",63.2,50);
		
		Vehicle V1 = new Vehicle("Diesel",75.0,FS1);
		Vehicle V2 = new Vehicle("Petrol",25.0,FS2);
		Vehicle V3 = new Vehicle("Petrol",40.0,FS3);
		
		V1.askForFuel();
		V2.askForFuel();
		V3.askForFuel();

        V1.setFuelStationToGo(FS2);
        V3.setFuelStationToGo(FS1);
        V2.setFuelStationToGo(FS3);
        
        V1.askForFuel();
        V3.askForFuel();
        V2.askForFuel();
	}
}