package edu.neu.cs5200.jpaxslt.orm.dao;


import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import edu.neu.cs5200.jpaxslt.orm.models.*;

public class SiteDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAXSLT");
	EntityManager em = factory.createEntityManager();
	
	public Site findSite(int siteId)
	{
		return em.find(Site.class, siteId);
	}
	
	public List<Site> findAllSites()
	{
		Query query = em.createQuery("select site from Site site");
		return (List<Site>) query.getResultList();
		
	}
	
	public void exportSiteDatabaseToXmlFile(SiteList siteList, String xmlFileName) {
		File xmlFile = new File(xmlFileName);
		try {
			JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//marshaller.marshal(sites, System.out);
			marshaller.marshal(siteList, xmlFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void convertXmlFileToOutputFile(
			String inputXmlFileName,
			String outputFileName,
			String xsltFileName)
	{
		File inputXmlFile = new File(inputXmlFileName);
		File outputXmlFile = new File(outputFileName);
		File xsltFile = new File(xsltFileName);
		
		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt    = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SiteDao dao = new SiteDao();
		
		List<Site> sites = dao.findAllSites();
		
		SiteList allSites = new SiteList();
		allSites.setSites(sites);

		
		dao.exportSiteDatabaseToXmlFile(allSites, "xml/sites.xml");
		
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xslt");
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
		
	}

}
