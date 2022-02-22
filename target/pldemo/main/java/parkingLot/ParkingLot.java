package parkingLot;

import java.util.ArrayList;

public class ParkingLot {

	private static ArrayList<ParkingSpot> ps = new ArrayList<>();

	private static void makeLot(int availableSpots) {
		ps = new ArrayList<>();
		for (int i = 0; i < availableSpots; i++) {
			ps.add(new ParkingSpot(LotFlag.O));
		}

	}

	public static ArrayList<ParkingSpot> getLot(int spotsize) {
		makeLot(spotsize);
		return ps;
	}

}
