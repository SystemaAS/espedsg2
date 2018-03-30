/**
 * 
 */
package no.systema.espedsgadmin.model;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Nov 24, 2016
 * 
 */
public class TomcatAspApplicationObject {
	
	private String aspCustomerName = null; 
	public void setAspCustomerName(String value) {  this.aspCustomerName = value; }
	public String getAspCustomerName() { return this.aspCustomerName;}
	
	private String shutdownPort = null; 
	public void setShutdownPort(String value) {  this.shutdownPort = value; }
	public String getShutdownPort() { return this.shutdownPort;}
	
	private String connectorPort = null; 
	public void setConnectorPort(String value) {  this.connectorPort = value; }
	public String getConnectorPort() { return this.connectorPort;}
	
	private String sslPort = null; 
	public void setSslPort(String value) {  this.sslPort = value; }
	public String getSslPort() { return this.sslPort;}
	
	private String ajpPort = null; 
	public void setAjpPort(String value) {  this.ajpPort = value; }
	public String getAjpPort() { return this.ajpPort;}
	
	private String systemaHttpPort = null; 
	public void setSystemaHttpPort(String value) {  this.systemaHttpPort = value; }
	public String getSystemaHttpPort() { return this.systemaHttpPort;}
	
	private List<String> portList = new ArrayList<String>(); 
	public void addToPortList( String value) {  this.portList.add(value); }
	public List<String> getPortList() { return this.portList;}
	
	
}
