package edu.neu.cs5200.jpaasgn.orm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.neu.cs5200.jpaasgn.orm.models.Site;

@Path("/site")
public class SiteDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAJWS");
	EntityManager em = factory.createEntityManager();
	
	
	//findSite
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("id") int siteId)
	{
		return em.find(Site.class, siteId);
	}
	
	//findAllSites
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> findAllSites()
	{
		Query query = em.createQuery("select site from Site site");
		return (List<Site>) query.getResultList();
		
	}
	
	//updateSite
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> updateSite(@PathParam("id") int siteId, Site site) 
	{
		em.getTransaction().begin();
		site.setId(siteId);
		em.merge(site);
		em.getTransaction().commit();
		return findAllSites();
	}
	
	//removeSite
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> removeSite(@PathParam("id") int siteId)
	{
		Site site = em.find(Site.class, siteId);
		em.getTransaction().begin();
		em.remove(site);
		em.getTransaction().commit();
		return findAllSites(); 
	}
	
	//createSite
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site) 
	{
		em.getTransaction().begin();
		em.persist(site);
		em.getTransaction().commit();
		return findAllSites();
	}
	
	public static void main (String[] args){
		SiteDao dao = new SiteDao();
		
/*		Site site = new Site(0,"Site7",20, 40);
		dao.createSite(site);*/
		
		
/*		List<Site> sites = dao.createSite(site);
		
		for(Site sitee: sites)
		{
			System.out.println(sitee.getName());
		}*/
		
/*		Site site = dao.findSite(3);
		System.out.println(site.getName());*/
		
/*		List<Site> sites = dao.findAllSites();
		for(Site site: sites)
		{
			System.out.println(site.getName());
		}*/
		
/*		Site site4 = dao.findSite(4);
		System.out.println(site4.getName());
		
		List<Site> sites = dao.findAllSites();
		for(Site site: sites)
		{
			System.out.println(site.getName());
		}*/
		
/*		Site site4 = dao.findSite(6);
		
		site4.setLatitude(48);
		dao.updateSite(6, site4);*/
		
/*		dao.removeSite(3);
		
		List<Site> sites = dao.findAllSites();
		for(Site site: sites)
		{
			System.out.println(site.getName());
		}*/

	}


}
