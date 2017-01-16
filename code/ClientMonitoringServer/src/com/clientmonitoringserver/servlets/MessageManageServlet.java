package com.clientmonitoringserver.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clientmonitoringserver.model.Message;
import com.clientmonitoringserver.model.Message.MessageType;
import com.clientmonitoringserver.services.MessageService;
import com.clientmonitoringserver.services.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class MessageManageServlet
 */
@WebServlet("/MessageManageServlet")
public class MessageManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageManageServlet() {
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
		
		Message message=new Message();
		message.setMessage(request.getParameter("sms"));
		message.setMobile(request.getParameter("mobile"));
		message.setMessageType(MessageType.RECIEVED);
		new UserService().generateCommand(message);
	//	new MessageService().addMessage(message);
		response.sendRedirect("sendSMS.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("ACTION").trim();
		if (action.equalsIgnoreCase("GET_MESSAGE_TOSEND")) {
			List<Message> messages=new MessageService().getMessageToSend();
			String jsonData=new Gson().toJson(messages);
			
			response.getWriter().write(jsonData);

		} else if (action.equalsIgnoreCase("RECEIVED_MESSAGE")) {
			
			String jsonData=request.getParameter("jsondata").trim();
			Message message=new Gson().fromJson(jsonData, Message.class);
			new UserService().generateCommand(message);
			new MessageService().addMessage(message);
			

		} else if (action.equalsIgnoreCase("MESSAGE_SEND_ACK")) {
			String jsonData=request.getParameter("messages");
			Type type = new TypeToken<List<Message>>() {}.getType();
			List<Message> messages=new Gson().fromJson(jsonData, type);
			for(Message message:messages){
				new MessageService().deleteMessage(message);
			}
			

		}
	}

}
