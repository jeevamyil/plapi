package parkingLot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class ParkingService {
	private static int spotSize;
	private static HashMap<LotName, ArrayList<ParkingSpot>> lots = new HashMap<>();
	public static HashMap<String, ArrayList<ParkingHistory>> history = new HashMap<>();
	public static HashMap<Vehicle, String> lotidx = new HashMap<>();

	public static int createlots(LotName[] lotsarray, int ss) {
		spotSize = ss;
		for (LotName ln : lotsarray) {
			lots.put(ln, ParkingLot.getLot(spotSize));
		}
		return lots.size();
	}

	public HashMap<String,String> parkVehicle(String vn, VehicleType vt, LocalDateTime inTim, LotName name) {
		Vehicle v = new Vehicle(vn, vt, inTim);
		HashMap<String,String> result = new HashMap<String, String>();
		boolean flag = this.finder(vn);
		if (flag) {
			System.out.println("Failure: Vehicle already present in the Lot.");
			result.put("0", "Vehicle already present in the Lot.");
			return result;
		}

		for (int i = 0; i < spotSize; i++) {
			if (!((lots.get(name).get(i)).isFlag() == LotFlag.X) && (lots.get(name).get(i)).setVehicle(v)) {
				String idx = String.valueOf(i + 1);
				if (v.getVtype() == VehicleType.BIKE) {
					if (lots.get(name).get(i).getSize() == 2) {
						idx = idx + 1;
					} else {
						idx = idx + 2;
					}
				} else if (v.getVtype() == VehicleType.CYCLE) {
					int pos = lots.get(name).get(i).getSize();
					switch (pos) {
					case 3:
						idx = idx + 1 + 1;
						break;
					case 2:
						idx = idx + 1 + 2;
						break;
					case 1:
						idx = idx + 2 + 1;
						break;
					default:
						idx = idx + 2 + 2;
						break;
					}

				}
				lotidx.put(v, idx);
				System.out.println("Success: Vehicle parked at " + name + idx + ".");
				result.put("1","The Vehicle parked at " + name + idx + ".");
				return result;
			}
		}
		System.out.println("Failure: Lot " + "'" + name + "'" + " is full");
		result.put("0","The lot " +  name + " is full. " );
		return result;
	}

	public HashMap<String,String> retreiveVehicle(String vn, LocalDateTime outTim) {
		int i = 0;
		HashMap<String,String> result = new HashMap<String, String>();
		for (LotName nam : lots.keySet()) {
			for (ParkingSpot ps : lots.get(nam)) {
				if (!(ps.isFlag() == LotFlag.O)) {
					for (Vehicle vs : ps.getVehicle()) {
						if (vs.getVnumber().equals(vn)) {
							String vt = vs.getVtype().name();
							String vnum = vn;
							String lotname = nam.name();
							String idx = String.valueOf(i);
							// i++;
							LocalDateTime inTim = vs.getEnteringDate();
							if (outTim.compareTo(inTim) <= 0) {
								System.out.println(outTim + " is an Invalid Out-Time");
								result.put("0",outTim + " is an Invalid Out-Time");
								return result;
								
							}
							Duration duration = Duration.between(inTim, outTim);
							long hours = duration.toHours();
							long minutes = duration.toMinutes() - hours * 60;
							int rate = vs.getRate();
							int bill = 0;
							bill = (int) ((hours * rate) + ((double) minutes / 60) * rate);
							boolean flag = ps.removeVehicle(vs);
							if (flag && lotidx.containsKey(vs)) {
								idx = lotidx.get(vs);
								lotidx.remove(vs);
							}
							String ltn = lotname + idx;
							addHistory(vn, inTim, outTim, vt, ltn, hours + " " + minutes, bill);

							System.out.println("Success: Vehicle(" + vt.toUpperCase().charAt(0)
									+ vt.toLowerCase().substring(1) + "," + vnum + ")" + " is available in " + lotname
									+ idx + ".Total duration: " + (hours > 0 ? hours + " hours " : " ")
									+ (minutes > 0 ? minutes + " minutes." : "") + "Amount to be paid:Rs: " + bill);
							String str= "The parking duration of " +(hours > 0 ? hours + " hours " : " ")
									+ (minutes > 0 ? minutes + " minutes." : "") + " Costs Rs: " + bill;
							result.put("1", str);
							return result;
						}
					}
				}
				i++;
			}
		}
		System.out.println("Failure: Vehicle(" + vn + ") is not available.");
		result.put("0", "Currently, Vehicle " + vn + " is not checked in");
		return result;

	}

	public boolean finder(String vn) {
		int i = 0;
		for (LotName name : lots.keySet()) {
			for (ParkingSpot ps : lots.get(name)) {
				if (!(ps.isFlag() == LotFlag.O)) {
					for (Vehicle v : ps.getVehicle()) {
						if (v.getVnumber().equals(vn)) {

							return true;

						}
					}
				}
			}
		}
		return false;
	}

	public HashMap<String,ArrayList<String>> findVehicle(String vn) {
		HashMap<String,ArrayList<String>> result =  new HashMap<String, ArrayList<String>>();
		int i = 0;
		for (LotName name : lots.keySet()) {
			for (ParkingSpot ps : lots.get(name)) {
				if (!(ps.isFlag() == LotFlag.O)) {
					for (Vehicle v : ps.getVehicle()) {
						if (v.getVnumber().equals(vn)) {
							String time = v.getEnteringDate().toString();
							String vt = v.getVtype().name();
							String vnum = vn;
							String idx = String.valueOf(i);
							i++;
							String lotname = name.name();

							if (lotidx.containsKey(v)) {
								idx = lotidx.get(v);
							}
							String res = "1Vehicle " + vnum + " is parked at " + lotname + idx + " lot at " + time+". ";
							result.put(res,printHistory(vn));
							return result;

						}
					}
				}
			}
		}

		String res = "2Currently, Vehicle " + vn + " is not checked in";
		result.put(res,printHistory(vn));
		return result;

	}

	public static HashMap<String, ArrayList<Integer>> printLots() {
		ArrayList<LotName> temp = new ArrayList<>();
		for (LotName name : lots.keySet()) {
				if (temp.indexOf(name) == -1) {
					temp.add(name);
				}
			
		}
		HashMap<String, ArrayList<Integer>> lotmap = new HashMap<String, ArrayList<Integer>>();

		if (temp.size() > 0) {

			for (LotName name : temp) {
				ArrayList<Integer> tmp = new ArrayList<>();
				tmp.add(spotSize);
				int count = 0;
				for (int j = 0; j < spotSize; j++) {
					if ((lots.get(name).get(j)).isFlag() == LotFlag.O) {
						count += 1;
					} else if ((lots.get(name).get(j)).isFlag() == LotFlag.P) {
						count += 1;
					}
				}
				tmp.add(spotSize - count);
				tmp.add(count);

				lotmap.put(name.name(), tmp);
			}
			// return res;
		}
		return lotmap;

	}

	private static void addHistory(String vn, LocalDateTime inTim, LocalDateTime outTim, String vt, String lotn,
			String duration, int bill) {
		ParkingHistory ph = new ParkingHistory(vn, inTim, outTim, VehicleType.valueOf(vt), lotn, duration, bill);
		if (history.get(vn) == null) {
			ArrayList<ParkingHistory> phl = new ArrayList<ParkingHistory>();
			phl.add(ph);
			history.put(vn, phl);
		} else {
			ArrayList<ParkingHistory> temp = history.get(vn);
			if (temp.size() < 10) {
				temp.add(ph);
			} else {
				temp.remove(0);
				temp.add(ph);
			}
		}

	}

	private static ArrayList<String> printHistory(String vn) {
		ArrayList<ParkingHistory> temp = history.get(vn);
		ArrayList<String>result =new ArrayList<>();
		if (temp == null) {
			System.out.println("There is no parking history");
			return result;
		} else {
			String hist = "";
			for (ParkingHistory ph : temp) {
				LocalDateTime ent = ph.getEntryTime();
				LocalDateTime ext = ph.getExitTime();
				String intime = ent.format(DateTimeFormatter.ofPattern("dd-MM-yyyy h:mm a"));
				String outtime = ext.format(DateTimeFormatter.ofPattern("dd-MM-yyyy h:mm a"));
				String type = ph.getType().name();
				String lotn = ph.getLotnSpot();
				String[] duration = ph.getDuration().split(" ");
				String hours = duration[0];
				String minutes = duration[1];
				int cost = ph.getCost();

				result.add(intime + "+" + type.toUpperCase().charAt(0) + type.substring(1).toLowerCase() + "+" + outtime
						+ "+" + lotn + "+" + (Integer.parseInt(hours) > 0 ? hours + " hours" : "") + " "
						+ (Integer.parseInt(minutes) > 0 ? minutes + " minutes" : "") + "+ " + cost);
			}
			return result;
		}

	}
}
