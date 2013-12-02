package localdomain.localhost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

import localdomain.localhost.domain.CapitalBean;


/**
 * Servlet implementation class CapitalServlet
 */
@WebServlet("/CapitalServlet")
/*
 * Copyright 2010-2013, the original author or authors
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


public class CapitalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  @EJB
	   private CapitalServiceBean bean;
	 
	   protected void processRequest(HttpServletRequest request, 
	                                 HttpServletResponse response)
	                  throws ServletException, IOException {
		   
		 
		   String nationToSave=request.getParameter("nation");
		   String capitalToSave=(request.getParameter("capital")!=null?request.getParameter("capital"):null);
	     //  PrintWriter out = response.getWriter();
//	       CapitalBean g_en = new CapitalBean("Rome", "Italy");
//	       CapitalBean g_es = new CapitalBean("Madrid", "Spain");
	       
	       if(nationToSave!=null&&capitalToSave!=null){
	    	   
		       CapitalBean capitalbean= new CapitalBean(capitalToSave,nationToSave);
		       bean.create(capitalbean);
	    	   
	       }
	       
	       if(request.getParameter("nationToRetrieve")!=null&&request.getParameter("nationToRetrieve").trim()!=""){
	          String nationToRetrieve=request.getParameter("nationToRetrieve");
	          
	          System.out.println(nationToRetrieve);
	       
	         String capital= bean.findByNation(nationToRetrieve).toString();
	          
	          request.setAttribute("capitalRetrieved", capital );
	          
	          
	       
	       }
	       //bean.create(g_en, g_es);
	     //  out.println("Created and persisted " + g_en + ", and " + g_es);
	 
//	       CapitalBean g = bean.findByNation("Italy");
//	       out.println("Query returned: " + g);
//	       
	     
//	    	   
//	    		  try {
//	    	 
//	    			URL url = new URL("http://localhost:8080/RESTfulExample/json/product/get");
//	    			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	    			conn.setRequestMethod("GET");
//	    			conn.setRequestProperty("Accept", "application/json");
//	    	 
//	    			if (conn.getResponseCode() != 200) {
//	    				throw new RuntimeException("Failed : HTTP error code : "
//	    						+ conn.getResponseCode());
//	    			}
//	    	 
//	    			BufferedReader br = new BufferedReader(new InputStreamReader(
//	    				(conn.getInputStream())));
//	    	 
//	    			String output;
//	    			System.out.println("Output from Server .... \n");
//	    			while ((output = br.readLine()) != null) {
//	    				System.out.println(output);
//	    			}
//	    	 
//	    			conn.disconnect();
//	    	 
//	    		  } catch (MalformedURLException e) {
//	    	 
//	    			e.printStackTrace();
//	    	 
//	    		  } catch (IOException e) {
//	    	 
//	    			e.printStackTrace();
//	    	 
//	    		  }
	       
	       
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

