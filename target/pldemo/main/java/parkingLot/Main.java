/*
 * package parkingLot;
 * 
 * import java.io.*;
 * 
 * import java.time.LocalDateTime; import java.time.format.DateTimeFormatter;
 * import java.util.ArrayList;
 * 
 * public class Main {
 * 
 * public static void main(String[] args) throws IOException { // TODO
 * Auto-generated method stub int parkingSpots = 5; LotName[] temp =
 * LotName.values(); ParkingService.createlots(temp, parkingSpots);
 * ArrayList<String[]> temparray = new ArrayList<>(); int inputSize = 0; File
 * file = new File("C:\\input.txt"); if (file.exists()) { BufferedReader br =
 * new BufferedReader(new FileReader(file)); String st; int idx = 0; while ((st
 * = br.readLine()) != null) { temparray.add(st.split(",")); idx++; } inputSize
 * = idx; br.close();
 * 
 * } else if (args.length > 0) { inputSize = args.length; for (int i = 0; i <
 * inputSize; i++) { for (int j = 0; j < args[i].split(",").length; j++) {
 * temparray.add(i,args[i].split(",") ); }
 * 
 * } }
 * 
 * for (int i = 0; i < inputSize; i++) { int tasknumber =
 * Integer.parseInt(temparray.get(i)[0]); if (tasknumber == 1) { if
 * (temparray.get(i).length == 5) { String vnumber = temparray.get(i)[1]; String
 * vtype = temparray.get(i)[2]; String etime = temparray.get(i)[3]; String
 * lotName = temparray.get(i)[4]; DateTimeFormatter format =
 * DateTimeFormatter.ofPattern("dd-MM-yyyy h:mm a"); LocalDateTime inTim =
 * LocalDateTime.parse(etime, format); System.out.println(inTim); ParkingService
 * ps = new ParkingService(); try { ps.parkVehicle(vnumber,
 * VehicleType.valueOf(vtype.toUpperCase()), inTim,
 * LotName.valueOf(lotName.toUpperCase())); } catch (Exception e) { //
 * e.printStackTrace(); System.out.println("Error : " + lotName +
 * " is not a valid parking lot Id!!"); }
 * 
 * }
 * 
 * } else if (tasknumber == 2) { if (temparray.get(i).length == 3) { String
 * vnumber = temparray.get(i)[1]; String exitTime = temparray.get(i)[2];
 * ParkingService ps = new ParkingService(); DateTimeFormatter format =
 * DateTimeFormatter.ofPattern("dd-MM-yyyy h:mm a"); LocalDateTime outTim =
 * LocalDateTime.parse(exitTime, format); ps.retreiveVehicle(vnumber, outTim); }
 * }
 * 
 * else if (tasknumber == 3) { if (temparray.get(i).length == 2) { String
 * vnumber = temparray.get(i)[1]; ParkingService ps = new ParkingService();
 * ps.findVehicle(vnumber, true); } } else {
 * System.out.println("invalid input "); } } if (inputSize > 0) {
 * ParkingService.printLots(); }
 * 
 * }
 * 
 * }
 */