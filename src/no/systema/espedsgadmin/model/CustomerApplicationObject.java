/**
 * 
 */
package no.systema.espedsgadmin.model;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mar 31, 2014
 * 
 */
public class CustomerApplicationObject {
	
	private String name = null; 
	public void setName(String value) {  this.name = value; }
	public String getName() { return this.name;}
	
	private String version = null; 
	public void setVersion(String value) {  this.version = value; }
	public String getVersion() { return this.version;}
	
	private String url = null; 
	public void setUrl(String value) {  this.url = value; }
	public String getUrl() { return this.url;}
	
	
	private List<String> applicationList = new ArrayList<String>(); 
	public void addToApplicationList( String value) {  this.applicationList.add(value); }
	public List<String> getApplicationList() { return this.applicationList;}
	
	
}
