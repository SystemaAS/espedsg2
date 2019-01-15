/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.util.AppConstants;

/**
 * @author oscardelatorre
 * @date Jan 2019
 *
 */
public class JsonTransportDispWorkflowFellesutskriftRecord {
	
	
	private String jbk = null;
	private String of = null;
	private String vf = null;
	private String tpfb = null;
	private String iffb = null;
	private String loss = null;
	private String ffak = null;
	private String kowfet = null;
	private String cm = null;
	private String sakode = null;
	private String satype = null;
	private String wsavd = null;
	private String wssg = null;
	private String wspro = null;
	private String wsopd = null;
	private String wsgn = null;
	private String wsdt1 = null;
	private String wsdt2 = null;
	private String wssum = null;
	private String wsot1 = null;
	private String wsot2 = null;
	private String wsot3 = null;
	private String wsot4 = null;
	private String wsot5 = null;
	private String wsms1 = null;
	private String wsms2 = null;
	private String wsms3 = null;
	private String wsms4 = null;
	private String wsms5 = null;
	private String wsms6 = null;
	private String wsprt = null;
	private String wsprt2 = null;
	
	
		public String getWsprt() {
		return wsprt;
	}


	public void setWsprt(String wsprt) {
		this.wsprt = wsprt;
	}


	public String getWsprt2() {
		return wsprt2;
	}


	public void setWsprt2(String wsprt2) {
		this.wsprt2 = wsprt2;
	}


		public String getJbk() {
		return jbk;
	}


	public void setJbk(String jbk) {
		this.jbk = jbk;
	}


	public String getOf() {
		return of;
	}


	public void setOf(String of) {
		this.of = of;
	}


	public String getVf() {
		return vf;
	}


	public void setVf(String vf) {
		this.vf = vf;
	}


	public String getTpfb() {
		return tpfb;
	}


	public void setTpfb(String tpfb) {
		this.tpfb = tpfb;
	}


	public String getIffb() {
		return iffb;
	}


	public void setIffb(String iffb) {
		this.iffb = iffb;
	}


	public String getLoss() {
		return loss;
	}


	public void setLoss(String loss) {
		this.loss = loss;
	}


	public String getFfak() {
		return ffak;
	}


	public void setFfak(String ffak) {
		this.ffak = ffak;
	}


	public String getKowfet() {
		return kowfet;
	}


	public void setKowfet(String kowfet) {
		this.kowfet = kowfet;
	}


	public String getCm() {
		return cm;
	}


	public void setCm(String cm) {
		this.cm = cm;
	}


	public String getSakode() {
		return sakode;
	}


	public void setSakode(String sakode) {
		this.sakode = sakode;
	}


	public String getSatype() {
		return satype;
	}


	public void setSatype(String satype) {
		this.satype = satype;
	}


	public String getWsavd() {
		return wsavd;
	}


	public void setWsavd(String wsavd) {
		this.wsavd = wsavd;
	}


	public String getWssg() {
		return wssg;
	}


	public void setWssg(String wssg) {
		this.wssg = wssg;
	}


	public String getWspro() {
		return wspro;
	}


	public void setWspro(String wspro) {
		this.wspro = wspro;
	}


	public String getWsopd() {
		return wsopd;
	}


	public void setWsopd(String wsopd) {
		this.wsopd = wsopd;
	}


	public String getWsgn() {
		return wsgn;
	}


	public void setWsgn(String wsgn) {
		this.wsgn = wsgn;
	}


	public String getWsdt1() {
		return wsdt1;
	}


	public void setWsdt1(String wsdt1) {
		this.wsdt1 = wsdt1;
	}


	public String getWsdt2() {
		return wsdt2;
	}


	public void setWsdt2(String wsdt2) {
		this.wsdt2 = wsdt2;
	}


	public String getWssum() {
		return wssum;
	}


	public void setWssum(String wssum) {
		this.wssum = wssum;
	}


	public String getWsot1() {
		return wsot1;
	}


	public void setWsot1(String wsot1) {
		this.wsot1 = wsot1;
	}


	public String getWsot2() {
		return wsot2;
	}


	public void setWsot2(String wsot2) {
		this.wsot2 = wsot2;
	}


	public String getWsot3() {
		return wsot3;
	}


	public void setWsot3(String wsot3) {
		this.wsot3 = wsot3;
	}


	public String getWsot4() {
		return wsot4;
	}


	public void setWsot4(String wsot4) {
		this.wsot4 = wsot4;
	}


	public String getWsot5() {
		return wsot5;
	}


	public void setWsot5(String wsot5) {
		this.wsot5 = wsot5;
	}


	public String getWsms1() {
		return wsms1;
	}


	public void setWsms1(String wsms1) {
		this.wsms1 = wsms1;
	}


	public String getWsms2() {
		return wsms2;
	}


	public void setWsms2(String wsms2) {
		this.wsms2 = wsms2;
	}


	public String getWsms3() {
		return wsms3;
	}


	public void setWsms3(String wsms3) {
		this.wsms3 = wsms3;
	}


	public String getWsms4() {
		return wsms4;
	}


	public void setWsms4(String wsms4) {
		this.wsms4 = wsms4;
	}


	public String getWsms5() {
		return wsms5;
	}


	public void setWsms5(String wsms5) {
		this.wsms5 = wsms5;
	}


	public String getWsms6() {
		return wsms6;
	}


	public void setWsms6(String wsms6) {
		this.wsms6 = wsms6;
	}


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
