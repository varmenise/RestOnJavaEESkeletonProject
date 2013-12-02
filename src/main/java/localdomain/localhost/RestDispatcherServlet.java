/*
 * Copyright 2010-2013, CloudBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package localdomain.localhost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Rest responding to the url RestDispatcherServlet, that will call the right
 * rest service according to the jsp input parameters.
 */
@WebServlet("/RestDispatcherServlet")
public class RestDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CapitalServiceBean bean;

	protected void processRequest(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException {

		String action=request.getParameter("action");
		String nationToSave=request.getParameter("nation");
		String capitalToSave=(request.getParameter("capital")!=null?request.getParameter("capital"):null);
		if(action!=null&&action.equalsIgnoreCase("Save")){

			if(nationToSave!=null&&capitalToSave!=null){

				try {
					String serverName=request.getServerName();
					String contextPath=request.getContextPath();
					int serverPort=request.getServerPort();
					URL url = new URL("http://"+serverName+":"+serverPort+contextPath+"/rest/Services/saveCapital/"+capitalToSave+"/"+nationToSave);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");

					if (conn.getResponseCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
								+ conn.getResponseCode());
					}

					BufferedReader br = new BufferedReader(new InputStreamReader(
							(conn.getInputStream())));
					String output;
					System.out.println("Output from Server .... \n");

					while ((output = br.readLine()) != null) {
						System.out.println(output);
					}

					conn.disconnect();
				}catch (MalformedURLException e) {
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		if(action!=null&&action.equalsIgnoreCase("retrieve")){
			if(request.getParameter("nationToRetrieve")!=null&&request.getParameter("nationToRetrieve").trim()!=""){
				String nationToRetrieve=request.getParameter("nationToRetrieve");

				try {
					String serverName=request.getServerName();
					String contextPath=request.getContextPath();
					System.out.println(contextPath);
					int serverPort=request.getServerPort();

					URL url = new URL("http://"+serverName+":"+serverPort+contextPath+"/rest/Services/findByNation/"+nationToRetrieve);

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");

					if (conn.getResponseCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
								+ conn.getResponseCode());
					}

					BufferedReader br = new BufferedReader(new InputStreamReader(
							(conn.getInputStream())));

					String output;
					String outputString ="";
					System.out.println("Output from Server .... \n");

					while ((output = br.readLine()) != null) {
						System.out.println(output);
						outputString+=output;
					}

					request.setAttribute("capitalRetrieved", outputString );

					conn.disconnect();

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}


			}
		}


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/welcome.jsp");

		dispatcher.forward(request, response);

	}


	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException {
		processRequest(request, response);
	}
}


