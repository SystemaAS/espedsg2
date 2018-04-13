/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.budget;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Oct 12, 2015
 *
 */
public class JsonTransportDispWorkflowSpecificBudgetContainer {
	
	private String totalNumberOfItemLines = null;
	public void setTotalNumberOfItemLines(String value) {  this.totalNumberOfItemLines = value; }
	public String getTotalNumberOfItemLines() { return this.totalNumberOfItemLines;}
	
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String tur = null;
	public void setTur(String value) {  this.tur = value; }
	public String getTur() { return this.tur;}
	
	private String type = null;
	public void setType(String value) {  this.type = value; }
	public String getType() { return this.type;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonTransportDispWorkflowSpecificBudgetRecord> budgetLines;
	public void setBudgetLines(Collection<JsonTransportDispWorkflowSpecificBudgetRecord> value){ this.budgetLines = value; }
	public Collection<JsonTransportDispWorkflowSpecificBudgetRecord> getBudgetLines(){ return budgetLines; }
	
	private Collection<JsonTransportDispWorkflowSpecificBudgetRecord> budgetLineUpdate;
	public void setBudgetLineUpdate(Collection<JsonTransportDispWorkflowSpecificBudgetRecord> value){ this.budgetLineUpdate = value; }
	public Collection<JsonTransportDispWorkflowSpecificBudgetRecord> getBudgetLineUpdate(){ return budgetLineUpdate; }
	
	
}
