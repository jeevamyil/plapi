package parkingLot;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/find")
public class FindServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String input = request.getParameter("input");
		String flag = InputHandler.InputSplitter(input);
		PrintWriter out = response.getWriter();
		if(!flag.isEmpty()) {
			response.setStatus(200);
			
			out.print(flag);
		}
	}
}
