package exam1.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam1.command.JoinCommand;

@SuppressWarnings("serial")
@WebServlet
public class FrontController extends HttpServlet {
	private HashMap<String, Command> map;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
		map = new HashMap<String, Command>();
		map.put("/join.do", new JoinCommand());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String uri = req.getRequestURI();	// http://localhost:8080/exam1/join.do
		String contextPath = req.getContextPath();
		String commandPath = uri.substring(contextPath.length());
		
		System.out.println(uri + " : " + contextPath + " : " + commandPath);
		
		Command command = map.get(commandPath);	//필요한 command객체를 찾아옴
		String viewPage = command.execute(req, resp); 	//명령실행 후 보여줄 결과페이지
		
		if(viewPage !=null) {
			if(viewPage.startsWith("redirect:")) {
				String url = viewPage.substring(9);
				System.out.println("url >> " + url);
				resp.sendRedirect(url);
			}else {
				req.getRequestDispatcher(viewPage).forward(req, resp);
			}
		}
	}
}
