package parkingLot;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/init")
public class DemoServlet extends HttpServlet {
	int size = 0;
	LotName[] temp = LotName.values();
	int parkingSpots = VConst.SPOT_SIZE;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		size = ParkingService.createlots(temp, parkingSpots);
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		String str = String.valueOf(temp.length) + "," + temp[0].name() + "," + temp[temp.length - 1] + ","
				+ parkingSpots + "," + VConst.CAR_RATE + "," + VConst.BIKE_RATE + "," + VConst.CYCLE_RATE + "";
		System.out.println(str);
		ObjectMapper Obj = new ObjectMapper();
		out.println(Obj.writeValueAsString(str));
		// out.println(str);
		response.setStatus(200);
		// ObjectMapper Obj = new ObjectMapper(ParkingService.);
		// out.println(Obj.writeValueAsString(createlotso()));
	}

}
