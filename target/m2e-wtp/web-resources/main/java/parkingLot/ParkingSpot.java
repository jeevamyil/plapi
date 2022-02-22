package parkingLot;

import java.util.ArrayList;

public class ParkingSpot {

	private LotFlag flag;
	private int size;
	private ArrayList<Vehicle> vehicle = new ArrayList<Vehicle>();

	public ParkingSpot(LotFlag flag) {
		super();
		this.flag = flag;
		this.size = 4;
	}

	public LotFlag isFlag() {
		return flag;
	}

	public void setFlag(LotFlag flag) {
		this.flag = flag;
	}

	public ArrayList<Vehicle> getVehicle() {
		return vehicle;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean removeVehicle(Vehicle vehicle) {
		if (this.vehicle.contains(vehicle)) {
			this.setSize(this.getSize() + vehicle.getSize());
			if (this.getSize() == 4) {
				this.setFlag(LotFlag.O);
			} else if (this.getSize() > 0) {
				this.setFlag(LotFlag.P);
			}
			return this.vehicle.remove(vehicle);
		}
		return false;
	}

	public boolean setVehicle(Vehicle vehicle) {

		if (this.size >= vehicle.getSize()) {
			this.vehicle.add(vehicle);
			this.size -= vehicle.getSize();
			if (size > 0) {
				this.setFlag(LotFlag.P);
				return true;
			} else {
				this.setFlag(LotFlag.X);
				return true;
			}

		}

		return false;

	}

	@Override
	public String toString() {
		return "ParkingSpot [flag=" + flag + ", size=" + size + ", vehicle=" + vehicle + "]";
	}

}
