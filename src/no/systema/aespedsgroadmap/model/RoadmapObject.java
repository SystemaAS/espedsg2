/**
 * 
 */
package no.systema.aespedsgroadmap.model;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Dec 21, 2015
 * 
 */
public class RoadmapObject {
	
	private String id = null; 
	public void setId(String value) {  this.id = value; }
	public String getId() { return this.id;}
	
	private String subject = null; 
	public void setSubject(String value) {  this.subject = value; }
	public String getSubject() { return this.subject;}
	
	private String text = null; 
	public void setText(String value) {  this.text = value; }
	public String getText() { return this.text;}
	
	private String status = null; 
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}
	
	private String description = null; 
	public void setDescription(String value) {  this.description = value; }
	public String getDescription() { return this.description;}
	
	
}
