/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.frisokvei;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Jun 09, 2015
 *
 */
public class JsonTransportDispWorkflowSpecificOrderFrisokveiContainer {
	
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
	
	private String kode = null;
	public void setKode(String value) {  this.kode = value; }
	public String getKode() { return this.kode;}
	
	private String sok = null;
	public void setSok(String value) {  this.sok = value; }
	public String getSok() { return this.sok;}
	
	private String dokk = null;
	public void setDokk(String value) {  this.dokk = value; }
	public String getDokk() { return this.dokk;}
	
	private String dtop = null;
	public void setDtop(String value) {  this.dtop = value; }
	public String getDtop() { return this.dtop;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispWorkflowSpecificOrderFrisokveiRecord> awblinelist;
	public void setAwblinelist(Collection<JsonTransportDispWorkflowSpecificOrderFrisokveiRecord> value){ this.awblinelist = value; }
	public Collection<JsonTransportDispWorkflowSpecificOrderFrisokveiRecord> getAwblinelist(){ return awblinelist; }
	
	private Collection<JsonTransportDispWorkflowSpecificOrderFrisokveiRecord> freesearchUpdate;
	public void setFreesearchUpdate(Collection<JsonTransportDispWorkflowSpecificOrderFrisokveiRecord> value){ this.freesearchUpdate = value; }
	public Collection<JsonTransportDispWorkflowSpecificOrderFrisokveiRecord> getFreesearchUpdate(){ return freesearchUpdate; }
	

	/**
	 * Used for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
}
