package parkingLot;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/showlots")
public class PrintLotsServlet extends HttpServlet{
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setStatus(200);
		//response.addHeader("Access-Control-Allow-Origin", "*");
		ObjectMapper Obj = new ObjectMapper();
		out.println(Obj.writeValueAsString(ParkingService.printLots()));
		//out.print(ParkingService.printLots());
	}

}
