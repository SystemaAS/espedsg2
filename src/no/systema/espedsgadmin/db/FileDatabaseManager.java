/**
 * 
 */
package no.systema.espedsgadmin.db;
import java.util.*;

import org.apache.log4j.Logger;

import no.systema.main.context.TdsServletContext;
import no.systema.main.util.io.TextFileReaderService;
import no.systema.espedsgadmin.controller.CustomerApplicationController;
import no.systema.espedsgadmin.model.CustomerApplicationObject;
import no.systema.espedsgadmin.model.TomcatAspApplicationObject;


/**
 * 
 * @author oscardelatorre
 * @date Apr. 1, 2014
 * 
 */
public class FileDatabaseManager {
	private static final Logger logger = Logger.getLogger(CustomerApplicationController.class.getName());
	
	static final String RESOURCE_FILES_PATH_DEFAULT = "/WEB-INF/resources/files/customersDb/";
	static final String UTF_8 = "UTF-8";
	
	private String sourceFileCustomers =  "customers.db";
	private String sourceFileApps = "applicationModules.db";
	private String sourceFileCustApps = "custApps.db";
	Map<String, String> customerMap = new HashMap<String, String>();
	Map<String, String> appMap = new HashMap<String, String>();
	//Tomcat ports
	private String sourceFileTomcatAspCustomers = "tomcatAspCustomers.db";
	
	
	/**
	 * 
	 */
	public FileDatabaseManager(){
			
		//init base tables
		this.setCustomerMap();
		this.setApplicationMap();
		
	}
	
	/**
	 * Use this constructor when testing
	 * 
	 * @param customersDbPath
	 * @param appsDbPath
	 * @param custAppsDbPath
	 */
	public FileDatabaseManager(String customersDbPath, String appsDbPath, String custAppsDbPath){
		
		this.sourceFileCustomers = customersDbPath;
		this.sourceFileApps = appsDbPath;
		this.sourceFileCustApps = custAppsDbPath;
		//init base tables
		this.setCustomerMap();
		this.setApplicationMap();
		
	}
	/**
	 * Gets raw customer data set
	 * 
	 */
	private void setCustomerMap(){
		
		logger.info("CUSTOMERS FILE: " + RESOURCE_FILES_PATH_DEFAULT + sourceFileCustomers);
		TextFileReaderService textFileReaderServiceCustomers = new TextFileReaderService();
		List<String> payloadCustomers = textFileReaderServiceCustomers.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(RESOURCE_FILES_PATH_DEFAULT + this.sourceFileCustomers), this.UTF_8);
		for(String record : payloadCustomers){
			if(!record.contains("META")){
				String[] fields = record.split(";");
				//System.out.println ("CUST: " + record);
				if(fields!=null && fields.length>1){
					this.customerMap.put(fields[0], fields[1]);
				}
			}	
		}
		
	}
	
	/**
	 * Gets raw application data set
	 * 
	 */
	private void setApplicationMap(){
		TextFileReaderService textFileReaderServiceApps = new TextFileReaderService();
		List<String> payloadApps = textFileReaderServiceApps.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(RESOURCE_FILES_PATH_DEFAULT + this.sourceFileApps), this.UTF_8);
		
		for(String record : payloadApps){
			//System.out.println ("APPS: " + record);
			if(!record.contains("META")){
				String[] fields = record.split(";");
				//System.out.println ("CUST: " + record);
				if(fields!=null && fields.length>1){
					this.appMap.put(fields[0], fields[1]);
				}
			}	
		}
		
	}

	/**
	 * 
	 * Gets a list of CustomerApplication-relational-table objects (model layer) from file-db
	 * 
	 * @return
	 *
	 */
	public List<CustomerApplicationObject> getCustAppMap(){
		List<CustomerApplicationObject> dbCarrierObjectList = new ArrayList<CustomerApplicationObject>();
		CustomerApplicationObject custAppObject = null;
		TextFileReaderService textFileReaderServiceCustApps = new TextFileReaderService();
		List<String> payloadCustApps = textFileReaderServiceCustApps.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(RESOURCE_FILES_PATH_DEFAULT + this.sourceFileCustApps), this.UTF_8);
		
		for(String record : payloadCustApps){
			if(!record.contains("META")){
				String[] id = record.split("@");
				//System.out.println ("CUST: " + id[0]);
				String[] fields = { };
				if(id!=null && id.length>1){
					fields = id[1].split(";");
				}	
				
				List<String> list = null;
				if(fields!=null && fields.length>0){
					list = Arrays.asList(fields);
				}else{
					list = new ArrayList();
				}
				//populate carrier object
				custAppObject = new CustomerApplicationObject();
				String customerName = (String)this.customerMap.get(id[0]);
				if(customerName!=null){
					if(customerName.contains("@")){
						logger.info("CUSTOMER RAW:" + customerName);
						String[] customerRecord = customerName.split("@");
						custAppObject.setName(customerRecord[0]);
						if(customerRecord.length>1){
							custAppObject.setUrl(customerRecord[1]);
							custAppObject.setVersion(customerRecord[2]);
						}
					}else{
						custAppObject.setName(customerName);
					}
				}
				//logger.info("CUSTOMER:" + custAppObject.getName());
				//logger.info("CUSTOMER url:" + custAppObject.getUrl());
				
				for (String app : list){
					String appName = (String)this.appMap.get(app);
					//logger.info("APP.NAME:" + appName);
					custAppObject.addToApplicationList(appName);
				}
				dbCarrierObjectList.add(custAppObject);
			}
		}
		return dbCarrierObjectList;
	}
	
	/**
	 * Get list of tomcat ports per ASP customer
	 * @return
	 */
	public List<TomcatAspApplicationObject> getTomcatAspCustomersPortList(){
		List<TomcatAspApplicationObject> dbCarrierObjectList = new ArrayList<TomcatAspApplicationObject>();
		TomcatAspApplicationObject tomcatObject = null;
		TextFileReaderService textFileReaderServiceCustApps = new TextFileReaderService();
		List<String> payload = textFileReaderServiceCustApps.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(RESOURCE_FILES_PATH_DEFAULT + this.sourceFileTomcatAspCustomers), this.UTF_8);
		
		for(String record : payload){
			if(!record.contains("META")){
				String[] fields = record.split(";");
				//System.out.println ("CUST: " + id[0]);
				List<String> list = null;
				if(fields!=null && fields.length>0){
					list = Arrays.asList(fields);
				}else{
					list = new ArrayList();
				}
				if(fields!=null && fields.length>1){
					//populate carrier object
					tomcatObject = new TomcatAspApplicationObject();
					tomcatObject.setAspCustomerName(fields[0]);
					tomcatObject.setConnectorPort(fields[1]);
					tomcatObject.setShutdownPort(fields[2]);
					tomcatObject.setSslPort(fields[3]);
					tomcatObject.setAjpPort(fields[4]);
					tomcatObject.setSystemaHttpPort(fields[5]);
					logger.info("ASP:" + tomcatObject.getAspCustomerName());
					//add to list
					dbCarrierObjectList.add(tomcatObject);
				}
			}
		}
		return dbCarrierObjectList;
	}
	
	
}
