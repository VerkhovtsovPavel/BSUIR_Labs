package all;


public class Dock {
	private boolean isWorkInUnloadMode;
	private Port port;

	public Dock(Port port){
		this.port = port;
		this.isWorkInUnloadMode = true;
	}
	
	public void unloadShips() {
		this.isWorkInUnloadMode = true;	
	}
	
	public void loadShips() {
		this.isWorkInUnloadMode = false;	
	}
	
	//TODO Add checks
	private void processShip(){
		Ship currentShip = null;
		if(isWorkInUnloadMode){
			currentShip = port.getShipOnUnload();
			port.load+=currentShip.getLoad();
		} else{
			currentShip = port.getShipOnLoad();
			port.load-= currentShip.getCapacity();
		}
	}
}
