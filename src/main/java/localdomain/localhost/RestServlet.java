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

package localdomain.localhost;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import localdomain.localhost.domain.CapitalBean;

@Path("/Services")
@Stateless

/**
 * Rest services
 */
public class RestServlet {

	/**
	 * EJB annotation does not work with REST services, thus we defined a provider as workaround that
	 * will inject the object annotated by @EJB (see class EJBProvider) with the specified name
	 *  (in this case CapitalServiceBean).
	 */
	@EJB(mappedName = "java:module/CapitalServiceBean")
	private CapitalServiceBean capitalService;


	@GET
	@Produces("application/json")
	@Path("/saveCapital/{capital}/{nation}")
	public Response registration(@PathParam("capital") String capital, @PathParam("nation") String nation) {

		CapitalBean capitalbean = new CapitalBean(capital, nation);
		capitalService.create(capitalbean);
		return Response.ok("Created and persisted " + capital).build();
	}

	@GET
	@Produces("application/json")
	@Path("/findByNation/{nation}")
	public Response findByNation(@PathParam("nation") String nation) {

		CapitalBean g = capitalService.findByNation(nation);
		return Response.ok(g, MediaType.APPLICATION_JSON).build();
	}

}
