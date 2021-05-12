package sliit.paf.com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OrderAPI") 
public class OrderAPI extends HttpServlet 
{ 
		OrderManagement orderManagement = new OrderManagement();
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
				String output = orderManagement.insertOrder(
					request.getParameter("orderCode"), 
					request.getParameter("customerID"), 
					request.getParameter("customerEmail"), 
					request.getParameter("customerName"),
					request.getParameter("orderTotalAmount"), 
					request.getParameter("cardNo"), 
					request.getParameter("cvvNo")
				);
					
				response.getWriter().write(output);
			}
		
		private static Map getMap(HttpServletRequest request) {
			
			Map<String, String> map = new HashMap<String, String>(); 
			try { 
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
				String queryString = scanner.hasNext() ? 
				scanner.useDelimiter("\\A").next() : ""; 
				scanner.close(); 
				String[] params = queryString.split("&"); 
				for (String param : params)  {
					String[] p = param.split("=");
					map.put(p[0], p[1]); 
				} 
			} catch (Exception e)  { 
			}
			return map;
		}
		
		protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{ 
			Map paras = getMap(request); 
			
			String output = orderManagement.updateOrder(paras.get("hidItemIDSave").toString(), 
			paras.get("orderCode").toString(), 
			paras.get("customerID").toString(), 
			paras.get("customerEmail").toString(), 
			paras.get("customerName").toString(), 
			paras.get("orderTotalAmount").toString(), 
			paras.get("cardNo").toString(), 
			paras.get("cvvNo").toString()); 
			response.getWriter().write(output); 
		}

		protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{ 
			Map paras = getMap(request); 
			
			String output = orderManagement.deleteOrder(paras.get("orderID").toString()); 
			response.getWriter().write(output); 
		}
}
