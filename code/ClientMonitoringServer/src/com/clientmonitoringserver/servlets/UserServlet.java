package com.clientmonitoringserver.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clientmonitoringserver.model.Command;
import com.clientmonitoringserver.model.Host;
import com.clientmonitoringserver.model.Leader;
import com.clientmonitoringserver.model.Message;
import com.clientmonitoringserver.model.User;
import com.clientmonitoringserver.services.CommandService;
import com.clientmonitoringserver.services.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("ACTION");
		if (action.equalsIgnoreCase("REGISTER")) {
			String jsonData = request.getParameter("jsonData");
System.out.println(jsonData);
			Host host = new Gson().fromJson(jsonData, Host.class);
			User user = new UserService().addHost(host).getUser();
			System.out.println(new Gson().toJson(user));
			response.getWriter().println(new Gson().toJson(user));

		} else if (action.equalsIgnoreCase("GET_LEADERS")) {

			List<Leader> leaders = new UserService().getLeaders();
			System.out.println(new Gson().toJson(leaders));
			response.getWriter().write(new Gson().toJson(leaders));

		} else if (action.equalsIgnoreCase("GET_MESSAGES")) {
			String mobile = request.getParameter("mobile");
			List<Message> messages = new UserService().getMessages(mobile);
			// Type type = new TypeToken<List<Message>>() {}.getType();
			response.getWriter().write(new Gson().toJson(messages));

		}
		else if (action.equalsIgnoreCase("GET_COMMANDS")) {
			String mobile = request.getParameter("mobile");
			List<Command> commands = new CommandService().getCommands(mobile);
			// Type type = new TypeToken<List<Message>>() {}.getType();
			response.getWriter().write(new Gson().toJson(commands));
			 new CommandService().deleteCommands(mobile);

		}

	}

}
