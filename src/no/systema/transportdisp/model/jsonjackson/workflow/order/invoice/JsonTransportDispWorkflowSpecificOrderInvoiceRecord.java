/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.invoice;

import java.util.*;
import java.lang.reflect.Field;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author oscardelatorre
 * @date Aug 14, 2015
 * 
 *
 */
public class JsonTransportDispWorkflowSpecificOrderInvoiceRecord extends JsonAbstractGrandFatherRecord implements Comparable<JsonTransportDispWorkflowSpecificOrderInvoiceRecord>{
	
	private String intMVAx = null;
	public void setIntMVAx(String value) {  this.intMVAx = value; }
	public String getIntMVAx() {return this.intMVAx;}
	
	private String kosMVAx = null;
	public void setKosMVAx(String value) {  this.kosMVAx = value; }
	public String getKosMVAx() {return this.kosMVAx;}
	
	
	@Override
	/**
	 * must be implemented. In this case we just put a default
	 */
	public int compareTo(JsonTransportDispWorkflowSpecificOrderInvoiceRecord record) {
		return 1;
	}
	/**
	 * 
	 * Comparator implementation to Sort record object based on other fields than default compareTo
	 * We must encapsulate this inner classes in order to accomplish this behavior within its outer class
	 */
	public static class OrderByCodeAndInvoiceNr implements Comparator<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> {
        @Override
        public int compare(JsonTransportDispWorkflowSpecificOrderInvoiceRecord o1, JsonTransportDispWorkflowSpecificOrderInvoiceRecord o2) {
        	int sComp = o1.fask.compareTo(o2.fask);

            if (sComp != 0) {
               return sComp;
            } else {
               return o1.fafakt.compareTo(o2.fafakt);
            }
            //return o1.fask.compareTo(o2.fask);
        }
    }
	
	private String heavd = null;
	public void setHeavd(String value) {  this.heavd = value; }
	public String getHeavd() {return this.heavd;}
	
	private String heopd = null;
	public void setHeopd(String value) {  this.heopd = value; }
	public String getHeopd() {return this.heopd;}
	
	
	private String fali = null;
	public void setFali(String value) {  this.fali = value; }
	public String getFali() {return this.fali;}
	
	private String fask = null;
	public void setFask(String value) {  this.fask = value; }
	public String getFask() {return this.fask;}
	
	private String favk = null;
	public void setFavk(String value) {  this.favk = this.toUpperCase(value); }
	public String getFavk() {return this.favk;}
	
	private String stdVt = null;
	public void setStdVt(String value) {  this.stdVt = value; }
	public String getStdVt() {return this.stdVt;}
	
	private String faVT = null;
	public void setFaVT(String value) {  this.faVT = value; }
	public String getFaVT() {return this.faVT;}
	
	private String faval = null;
	public void setFaval(String value) {  this.faval = value; }
	public String getFaval() {return this.faval;}
	
	private String fabelv = null;
	public void setFabelv(String value) {  this.fabelv = value; }
	public String getFabelv() {return this.fabelv;}
	
	private String fabeln = null;
	public void setFabeln(String value) {  this.fabeln = value; }
	public String getFabeln() {return this.fabeln;}
	
	private String fakdm = null;
	public void setFakdm(String value) {  this.fakdm = value; }
	public String getFakdm() {return this.fakdm;}
	
	private String fakda = null;
	public void setFakda(String value) {  this.fakda = value; }
	public String getFakda() {return this.fakda;}
	
	private String fakdul = null;
	public void setFakdul(String value) {  this.fakdul = value; }
	public String getFakdul() {return this.fakdul;}
	
	private String fa01b = null;
	public void setFa01b(String value) {  this.fa01b = value; }
	public String getFa01b() {return this.fa01b;}
	
	private String fakduk = null;
	public void setFakduk(String value) {  this.fakduk = value; }
	public String getFakduk() {return this.fakduk;}
	
	private String facu33 = null;
	public void setFacu33(String value) {  this.facu33 = value; }
	public String getFacu33() {return this.facu33;}
	
	private String fabelu = null;
	public void setFabelu(String value) {  this.fabelu = value; }
	public String getFabelu() {return this.fabelu;}
	
	private String fakunr = null;
	public void setFakunr(String value) {  this.fakunr = value; }
	public String getFakunr() {return this.fakunr;}

		
	private String fafakt = null;
	public void setFafakt(String value) {  this.fafakt = value; }
	public String getFafakt() {return this.fafakt;}

	private String fadato = null;
	public void setFadato(String value) {  this.fadato = value; }
	public String getFadato() {return this.fadato;}
	
	private String fadaff = null;
	public void setFadaff(String value) {  this.fadaff = value; }
	public String getFadaff() {return this.fadaff;}
	
	private String faavdr = null;
	public void setFaavdr(String value) {  this.faavdr = value; }
	public String getFaavdr() {return this.faavdr;}
	
	private String faopko = null;
	public void setFaopko(String value) {  this.faopko = value; }
	public String getFaopko() {return this.faopko;}
	
	private String fajour = null;
	public void setFajour(String value) {  this.fajour = value; }
	public String getFajour() {return this.fajour;}
	
	private String peri = null;
	public void setPeri(String value) {  this.peri = value; }
	public String getPeri() {return this.peri;}
	
	private String faggrp = null;
	public void setFaggrp(String value) {  this.faggrp = value; }
	public String getFaggrp() {return this.faggrp;}
	
	private String faaccn = null;
	public void setFaaccn(String value) {  this.faaccn = value; }
	public String getFaaccn() {return this.faaccn;}
	
	private String fabr = null;
	public void setFabr(String value) {  this.fabr = value; }
	public String getFabr() {return this.fabr;}
	
	private String fabaer = null;
	public void setFabaer(String value) {  this.fabaer = value; }
	public String getFabaer() {return this.fabaer;}
	
	
	private String faexra = null;
	public void setFaexra(String value) {  this.faexra = value; }
	public String getFaexra() {return this.faexra;}
	
	private String fakurs = null;
	public void setFakurs(String value) {  this.fakurs = value; }
	public String getFakurs() {return this.fakurs;}
	
	private String fafrbn = null;
	public void setFafrbn(String value) {  this.fafrbn = value; }
	public String getFafrbn() {return this.fafrbn;}
	
	private String fant = null;
	public void setFant(String value) {  this.fant = value; }
	public String getFant() {return this.fant;}
	
	private String falevn = null;
	public void setFalevn(String value) {  this.falevn = value; }
	public String getFalevn() {return this.falevn;}
	
	private String lnavn = null;
	public void setLnavn(String value) {  this.lnavn = value; }
	public String getLnavn() {return this.lnavn;}
	
	private String famade = null;
	public void setFamade(String value) {  this.famade = value; }
	public String getFamade() {return this.famade;}
	
	private String faagrn = null;
	public void setFaagrn(String value) {  this.faagrn = value; }
	public String getFaagrn() {return this.faagrn;}
	
	private String fatax = null;
	public void setFatax(String value) {  this.fatax = value; }
	public String getFatax() {return this.fatax;}
	
	private String fataxa = null;
	public void setFataxa(String value) {  this.fataxa = value; }
	public String getFataxa() {return this.fataxa;}
	
	private String facuri = null;
	public void setFacuri(String value) {  this.facuri = value; }
	public String getFacuri() {return this.facuri;}
	
		
	private String fadocnA = null;
	public void setFadocnA(String value) {  this.fadocnA = value; }
	public String getFadocnA() {return this.fadocnA;}
	
	private String fadocnB = null;
	public void setFadocnB(String value) {  this.fadocnB = value; }
	public String getFadocnB() {return this.fadocnB;}
	
	private String facd11 = null;
	public void setFacd11(String value) {  this.facd11 = value; }
	public String getFacd11() {return this.facd11;}
	
	private String facd12 = null;
	public void setFacd12(String value) {  this.facd12 = value; }
	public String getFacd12() {return this.facd12;}
	
	private String facd13 = null;
	public void setFacd13(String value) {  this.facd13 = value; }
	public String getFacd13() {return this.facd13;}
	
	private String facd21 = null;
	public void setFacd21(String value) {  this.facd21 = value; }
	public String getFacd21() {return this.facd21;}
	
	private String facd22 = null;
	public void setFacd22(String value) {  this.facd22 = value; }
	public String getFacd22() {return this.facd22;}
	
	private String facd23 = null;
	public void setFacd23(String value) {  this.facd23 = value; }
	public String getFacd23() {return this.facd23;}
	
	private String facd31 = null;
	public void setFacd31(String value) {  this.facd31 = value; }
	public String getFacd31() {return this.facd31;}
	
	private String fa01 = null;
	public void setFa01(String value) {  this.fa01 = value; }
	public String getFa01() {return this.fa01;}
	
	private String faduknb = null;
	public void setFaduknb(String value) {  this.faduknb = value; }
	public String getFaduknb() {return this.faduknb;}
	
	private String knavn = null;
	public void setKnavn(String value) {  this.knavn = value; }
	public String getKnavn() {return this.knavn;}
	
	private String wantall = null;
	public void setWantall(String value) {  this.wantall = value; }
	public String getWantall() {return this.wantall;}
	
	private String wkurs = null;
	public void setWkurs(String value) {  this.wkurs = value; }
	public String getWkurs() {return this.wkurs;}
	
	private String wtnr = null;
	public void setWtnr(String value) {  this.wtnr = value; }
	public String getWtnr() {return this.wtnr;}
	
	private String wkomp = null;
	public void setWkomp(String value) {  this.wkomp = value; }
	public String getWkomp() {return this.wkomp;}
	
	
	public String toUpperCase(String value) {
		String retval = value;
		if(value!=null){
			retval = value.toUpperCase();
		}
		return retval;
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
