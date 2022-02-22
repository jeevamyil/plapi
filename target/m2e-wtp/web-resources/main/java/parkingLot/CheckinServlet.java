package parkingLot;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/checkin")
public class CheckinServlet extends HttpServlet {
	
public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
		
		String input = request.getParameter("input");
		System.out.println(input);
		String flag = InputHandler.InputSplitter(input);
		PrintWriter out = response.getWriter();
		if(!flag.isEmpty()) {
			response.setStatus(200);
			out.print(flag);
		}
		//ObjectMapper Obj = new ObjectMapper();
		//out.println(Obj.writeValueAsString(createlotso()));
	}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
	// TODO Auto-generated method stub
	//super.doPost(request, response);
	String input = request.getParameter("input");
	System.out.println(input);
	String flag = InputHandler.InputSplitter(input);
	PrintWriter out = response.getWriter();
	if(!flag.isEmpty()) {
		response.setStatus(200);
		out.print(flag);
	}
}


	
}
