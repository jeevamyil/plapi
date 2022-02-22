package parkingLot;

import java.time.LocalDateTime;

public class ParkingHistory {
	
	private String VehicleNumber;
	private LocalDateTime EntryTime;
	private LocalDateTime ExitTime;
	private VehicleType type;
	private String lotnSpot;
	private String Duration;
	private int Cost;
	
	public ParkingHistory(String vehicleNumber, LocalDateTime entryTime, LocalDateTime exitTime, VehicleType type,
			String lotnSpot, String duration, int cost) {
		super();
		VehicleNumber = vehicleNumber;
		EntryTime = entryTime;
		ExitTime = exitTime;
		this.type = type;
		this.lotnSpot = lotnSpot;
		Duration = duration;
		Cost = cost;
	}

	public String getVehicleNumber() {
		return VehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		VehicleNumber = vehicleNumber;
	}

	public LocalDateTime getEntryTime() {
		return EntryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		EntryTime = entryTime;
	}

	public LocalDateTime getExitTime() {
		return ExitTime;
	}

	public void setExitTime(LocalDateTime exitTime) {
		ExitTime = exitTime;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public String getLotnSpot() {
		return lotnSpot;
	}

	public void setLotnSpot(String lotnSpot) {
		this.lotnSpot = lotnSpot;
	}

	public String getDuration() {
		return Duration;
	}

	public void setDuration(String duration) {
		Duration = duration;
	}

	public int getCost() {
		return Cost;
	}

	public void setCost(int cost) {
		Cost = cost;
	}

	@Override
	public String toString() {
		return "ParkingHistory [VehicleNumber=" + VehicleNumber + ", EntryTime=" + EntryTime + ", ExitTime=" + ExitTime
				+ ", type=" + type + ", lotnSpot=" + lotnSpot + ", Duration=" + Duration + ", Cost=" + Cost + "]";
	}
	
	
	
}
