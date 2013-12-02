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

import javax.ejb.*;
import javax.persistence.*;
import localdomain.localhost.domain.CapitalBean;

/**
 * This is a simple Session Bean that accesses the Entity trough the EntityManager.
 * The @PersistenceContext annotation tells the JBoss Server to inject an entity manager 
 * during deployment.
 * 
 * @author valentinaarmenise
 */

@Stateless
public class CapitalServiceBean {
	@PersistenceContext(unitName = "testjpa")
	private EntityManager em;

	
	public void create(CapitalBean... glist) {
		for(CapitalBean g : glist) {
			em.persist(g);
		}
	}

	public CapitalBean findByNation(String nation) {

		CapitalBean bean= new CapitalBean();
		Query q=em.createQuery(
				"select g from CapitalBean g where g.nation = :nation")
				.setParameter("nation", nation);
		if (q.getResultList()!=null&&q.getResultList().size()>0){
			bean=(CapitalBean) q.getResultList().get(0);
		}
		return bean;
	}
	
}