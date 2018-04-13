/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow;

import java.util.Collection;


/**
 * @author oscardelatorre
 * @date Aug 20, 2015
 *
 */
public class JsonTransportDispDangerousGoodsContainer {
	
	private String callerLineCounter = null;
	public void setCallerLineCounter(String value) {  this.callerLineCounter = value; }
	public String getCallerLineCounter() { return this.callerLineCounter;}
	
	private String singleRecordFlag = null;
	public void setSingleRecordFlag(String value) {  this.singleRecordFlag = value; }
	public String getSingleRecordFlag() { return this.singleRecordFlag;}
	
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String unnr = null;
	public void setUnnr(String value) {  this.unnr = value; }
	public String getUnnr() { return this.unnr;}
	
	private String embg = null;
	public void setEmbg(String value) {  this.embg = value; }
	public String getEmbg() { return this.embg;}
	
	private String indx = null;
	public void setIndx(String value) {  this.indx = value; }
	public String getIndx() { return this.indx;}
	
	private String getVal = null;
	public void setGetVal(String value) {  this.getVal = value; }
	public String getGetVal() { return this.getVal;}
	
	private String fullInfo = null;
	public void setFullInfo(String value) {  this.fullInfo = value; }
	public String getFullInfo() { return this.fullInfo;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTransportDispDangerousGoodsRecord> unNumbers;
	public void setUnNumbers(Collection<JsonTransportDispDangerousGoodsRecord> value){ this.unNumbers = value; }
	public Collection<JsonTransportDispDangerousGoodsRecord> getUnNumbers(){ return unNumbers; }
	
	
	
}
