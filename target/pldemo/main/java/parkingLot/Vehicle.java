package parkingLot;

import java.time.LocalDateTime;

public class Vehicle {

	private String Vnumber;
	private VehicleType vtype;
	private LocalDateTime enteringDate;
	private int Size;
	private int Rate;

	public Vehicle(String vnumber, VehicleType vtype, LocalDateTime enteringDate) {
		super();
		Vnumber = vnumber;
		this.vtype = vtype;
		this.enteringDate = enteringDate;
		switch (vtype) {
		case CAR:
			this.Size = VConst.CAR_SIZE;
			this.Rate = VConst.CAR_RATE;
			break;
		case BIKE:
			this.Size = VConst.BIKE_SIZE;
			this.Rate = VConst.BIKE_RATE;
			break;
		case CYCLE:
			this.Size = VConst.CYCLE_SIZE;
			this.Rate = VConst.CYCLE_RATE;
			break;
		default:
			break;
		}

	}

	public int getRate() {
		return Rate;
	}

	public void setRate(int rate) {
		Rate = rate;
	}

	public String getVnumber() {
		return Vnumber;
	}

	public void setVnumber(String vnumber) {
		Vnumber = vnumber;
	}

	public VehicleType getVtype() {
		return vtype;
	}

	public void setVtype(VehicleType vtype) {
		this.vtype = vtype;
	}

	public LocalDateTime getEnteringDate() {
		return enteringDate;
	}

	public void setEnteringDate(LocalDateTime enteringDate) {
		this.enteringDate = enteringDate;
	}

	public int getSize() {
		return Size;
	}

	public void setSize(int size) {
		Size = size;
	}

	@Override
	public String toString() {
		return "Vehicle [Vnumber=" + Vnumber + ", vtype=" + vtype + ", enteringDate=" + enteringDate + ", Size=" + Size
				+ "]";
	}

}
