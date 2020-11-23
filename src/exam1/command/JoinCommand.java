package exam1.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam1.action.Command;
import exam1.dao.MemberDao;

public class JoinCommand implements Command {
	private MemberDao memberDao = MemberDao.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase("get")) {
			System.out.println("JoinCommand GET");
			
			int nextNum = memberDao.nextCustNo();
			request.setAttribute("nextNum", nextNum);
			
			System.out.println("nextNum >> " + nextNum);
			
			return "index.jsp?pp=join";
		}else {
			
		}
		return null;
	}

}
