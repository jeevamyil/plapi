package parkingLot;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.format.DateTimeFormatter;

public class InputHandler {

	public static String InputSplitter(String inp) throws JsonProcessingException {
		
		String [] temparray = inp.split(",");
		ObjectMapper Obj = new ObjectMapper();
		
		
		int tasknumber = Integer.parseInt(temparray[0]);
		if (tasknumber == 1) {
			if (temparray.length == 5) {
				String vnumber = temparray[1];
				String vtype = temparray[2];
				String etime = temparray[3];
				String lotName = temparray[4];
				//DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mm a");
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
				LocalDateTime inTim = LocalDateTime.parse(etime, format);
				System.out.println(inTim);
				ParkingService ps = new ParkingService();
				try {
					return Obj.writeValueAsString(ps.parkVehicle(vnumber, VehicleType.valueOf(vtype.toUpperCase()), inTim,
							LotName.valueOf(lotName.toUpperCase())));
				} catch (Exception e) {
					// e.printStackTrace();
					System.out.println("Error : " + lotName + " is not a valid parking lot Id!!");
					return "Error : " + lotName + " is not a valid parking lot Id!!";
				}

			}

		} else if (tasknumber == 2) {
			if (temparray.length == 3) {
				String vnumber = temparray[1];
				String exitTime = temparray[2];
				ParkingService ps = new ParkingService();
				//DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mm a");
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
				LocalDateTime outTim = LocalDateTime.parse(exitTime, format);
				return Obj.writeValueAsString(ps.retreiveVehicle(vnumber, outTim));
				
			}
		}

		else if (tasknumber == 3) {
			if (temparray.length == 2) {
				String vnumber = temparray[1];
				ParkingService ps = new ParkingService();
				
				return Obj.writeValueAsString(ps.findVehicle(vnumber));
			}
		}
		else {
			System.out.println("invalid input ");
		}
		return "invalid input ";
	}
}
