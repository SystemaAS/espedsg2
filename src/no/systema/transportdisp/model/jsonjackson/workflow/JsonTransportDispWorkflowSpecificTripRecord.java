/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Apr 1, 2015
 * 
 *
 */
public class JsonTransportDispWorkflowSpecificTripRecord extends JsonAbstractGrandFatherRecord{
	
	private String messageNote = null;
	public void setMessageNote(String value) {  this.messageNote = value; }
	public String getMessageNote() {return this.messageNote;}
	
	private Collection<JsonTransportDispWorkflowSpecificTripMessageNoteRecord> freetextlist;
	public void setFreetextlist(Collection<JsonTransportDispWorkflowSpecificTripMessageNoteRecord> value){ this.freetextlist = value; }
	public Collection<JsonTransportDispWorkflowSpecificTripMessageNoteRecord> getFreetextlist(){ return freetextlist; }
	
	private Collection<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord> getdoctrip;
	public void setGetdoctrip(Collection<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord> value){ this.getdoctrip = value; }
	public Collection<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord> getGetdoctrip(){ return getdoctrip; }
	
	public String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() {return this.errMsg;}
	
	
	private String tust = null;
	public void setTust(String value) {  this.tust = value; }
	public String getTust() {return this.tust;}
	
	private String tuavd = null;
	public void setTuavd(String value) {  this.tuavd = value; }
	public String getTuavd() {return this.tuavd;}
	
	private String tupro = null;
	public void setTupro(String value) {  this.tupro = value; }
	public String getTupro() {return this.tupro;}
	
	private String tudt = null;
	public void setTudt(String value) {  this.tudt = value; }
	public String getTudt() {return this.tudt;}
	
	private String tukna = null;
	public void setTukna(String value) {  this.tukna = value; }
	public String getTukna() {return this.tukna;}
	
	private String tuknt = null;
	public void setTuknt(String value) {  this.tuknt = value; }
	public String getTuknt() {return this.tuknt;}
	
	private String tunat = null;
	public void setTunat(String value) {  this.tunat = value; }
	public String getTunat() {return this.tunat;}

	private String tuad1t = null;
	public void setTuad1t(String value) {  this.tuad1t = value; }
	public String getTuad1t() {return this.tuad1t;}
	
	private String tuad2t = null;
	public void setTuad2t(String value) {  this.tuad2t = value; }
	public String getTuad2t() {return this.tuad2t;}
	
	private String tuad3t = null;
	public void setTuad3t(String value) {  this.tuad3t = value; }
	public String getTuad3t() {return this.tuad3t;}
	
	private String tubiln = null;
	public void setTubiln(String value) {  this.tubiln = value; }
	public String getTubiln() {return this.tubiln;}
	
	private String tutarf = null;
	public void setTutarf(String value) {  this.tutarf = value; }
	public String getTutarf() {return this.tutarf;}
	
	private String tulk = null;
	public void setTulk(String value) {  this.tulk = value; }
	public String getTulk() {return this.tulk;}
	
	private String tuxxx1 = null;
	public void setTuxxx1(String value) {  this.tuxxx1 = value; }
	public String getTuxxx1() {return this.tuxxx1;}
	
	private String tusdf = null;
	public void setTusdf(String value) {  this.tusdf = value; }
	public String getTusdf() {return this.tusdf;}
	
	private String tusdt = null;
	public void setTusdt(String value) {  this.tusdt = value; }
	public String getTusdt() {return this.tusdt;}
	
	private String tuao = null;
	public void setTuao(String value) {  this.tuao = value; }
	public String getTuao() {return this.tuao;}
	
	private String tuts = null;
	public void setTuts(String value) {  this.tuts = value; }
	public String getTuts() {return this.tuts;}
	
	private String tutvkt = null;
	public void setTutvkt(String value) {  this.tutvkt = value; }
	public String getTutvkt() {return this.tutvkt;}
	
	private String tutlm = null;
	public void setTutlm(String value) {  this.tutlm = value; }
	public String getTutlm() {return this.tutlm;}
	
	private String tutm3 = null;
	public void setTutm3(String value) {  this.tutm3 = value; }
	public String getTutm3() {return this.tutm3;}
	
	private String tulast = null;
	public void setTulast(String value) {  this.tulast = value; }
	public String getTulast() {return this.tulast;}
	
	private String tubusj = null;
	public void setTubusj(String value) {  this.tubusj = value; }
	public String getTubusj() {return this.tubusj;}
	
	private String tuxxx2 = null;
	public void setTuxxx2(String value) {  this.tuxxx2 = value; }
	public String getTuxxx2() {return this.tuxxx2;}
	
	private String tutst1 = null;
	public void setTutst1(String value) {  this.tutst1 = value; }
	public String getTutst1() {return this.tutst1;}
	
	private String tutst2 = null;
	public void setTutst2(String value) {  this.tutst2 = value; }
	public String getTutst2() {return this.tutst2;}
	
	private String tutst3 = null;
	public void setTutst3(String value) {  this.tutst3 = value; }
	public String getTutst3() {return this.tutst3;}
	
	private String tutst4 = null;
	public void setTutst4(String value) {  this.tutst4 = value; }
	public String getTutst4() {return this.tutst4;}
	
	private String tutst5 = null;
	public void setTutst5(String value) {  this.tutst5 = value; }
	public String getTutst5() {return this.tutst5;}
	
	private String tuavre = null;
	public void setTuavre(String value) {  this.tuavre = value; }
	public String getTuavre() {return this.tuavre;}
	
	private String tuavnr = null;
	public void setTuavnr(String value) {  this.tuavnr = value; }
	public String getTuavnr() {return this.tuavnr;}
	
	private String turund = null;
	public void setTurund(String value) {  this.turund = value; }
	public String getTurund() {return this.turund;}
	
	private String turacc = null;
	public void setTuracc(String value) {  this.turacc = value; }
	public String getTuracc() {return this.turacc;}
	
	private String turaar = null;
	public void setTuraar(String value) {  this.turaar = value; }
	public String getTuraar() {return this.turaar;}
	
	private String centuryYearTurccTuraar = null;
	public void setCenturyYearTurccTuraar(String value) {  this.centuryYearTurccTuraar = value; }
	public String getCenturyYearTurccTuraar() {
		if( (this.turacc!=null && this.turaar!=null) && (!"".equals(this.turacc) && !"".equals(this.turaar!=null)) ){
			return this.turacc + this.turaar;
		}else{
			return this.centuryYearTurccTuraar;
		}
	}
	private String turmnd = null;
	public void setTurmnd(String value) {  this.turmnd = value; }
	public String getTurmnd() {
		String retval = this.turmnd;
		if(this.turmnd!=null && !"".equals(this.turmnd)){
			if(this.turmnd.length()==1){
				retval = "0" + this.turmnd;
			}
		}
		return retval;
	}
	
	private String tuopdt = null;
	public void setTuopdt(String value) {  this.tuopdt = value; }
	public String getTuopdt() {return this.tuopdt;}
	
	private String tutrma = null;
	public void setTutrma(String value) {  this.tutrma = value; }
	public String getTutrma() {return this.tutrma;}
	
	private String tuheng = null;
	public void setTuheng(String value) {  this.tuheng = value; }
	public String getTuheng() {return this.tuheng;}
	
	private String tulkh = null;
	public void setTulkh(String value) {  this.tulkh = value; }
	public String getTulkh() {return this.tulkh;}
	
	private String tucon1 = null;
	public void setTucon1(String value) {  this.tucon1 = value; }
	public String getTucon1() {return this.tucon1;}
	
	private String tulkc1 = null;
	public void setTulkc1(String value) {  this.tulkc1 = value; }
	public String getTulkc1() {return this.tulkc1;}
	
	private String tucon2 = null;
	public void setTucon2(String value) {  this.tucon2 = value; }
	public String getTucon2() {return this.tucon2;}
	
	private String tulkc2 = null;
	public void setTulkc2(String value) {  this.tulkc2 = value; }
	public String getTulkc2() {return this.tulkc2;}
	
	private String tutara = null;
	public void setTutara(String value) {  this.tutara = value; }
	public String getTutara() {return this.tutara;}
	
	private String tubilk = null;
	public void setTubilk(String value) {  this.tubilk = value; }
	public String getTubilk() {return this.tubilk;}
	
	private String tuknt2 = null;
	public void setTuknt2(String value) {  this.tuknt2 = value; }
	public String getTuknt2() {return this.tuknt2;}
	
	private String tusja1 = null;
	public void setTusja1(String value) {  this.tusja1 = value; }
	public String getTusja1() {return this.tusja1;}
	
	private String tusjn1 = null;
	public void setTusjn1(String value) {  this.tusjn1 = value; }
	public String getTusjn1() {return this.tusjn1;}
	
	private String tusja2 = null;
	public void setTusja2(String value) {  this.tusja2 = value; }
	public String getTusja2() {return this.tusja2;}
	
	private String tusjn2 = null;
	public void setTusjn2(String value) {  this.tusjn2 = value; }
	public String getTusjn2() {return this.tusjn2;}
	
	private String tustef = null;
	public void setTustef(String value) {  this.tustef = value; }
	public String getTustef() {return this.tustef;}
	
	private String tusonf = "NO";
	public void setTusonf(String value) {  this.tusonf = value; }
	public String getTusonf() {return this.tusonf;}
	
	private String tudtt = null;
	public void setTudtt(String value) {  this.tudtt = value; }
	public String getTudtt() {return this.tudtt;}
	
	private String tutm = null;
	public void setTutm(String value) {  this.tutm = value; }
	public String getTutm() {return this.tutm;}
	
	private String tutmt = null;
	public void setTutmt(String value) {  this.tutmt = value; }
	public String getTutmt() {return this.tutmt;}
	
	private String tustet = null;
	public void setTustet(String value) {  this.tustet = value; }
	public String getTustet() {return this.tustet;}
	
	private String tusont = "NO";
	public void setTusont(String value) {  this.tusont = value; }
	public String getTusont() {return this.tusont;}
	
	private String tukvkt = null;
	public void setTukvkt(String value) {  this.tukvkt = value; }
	public String getTukvkt() {return this.tukvkt;}
	
	private String tukam3 = null;
	public void setTukam3(String value) {  this.tukam3 = value; }
	public String getTukam3() {return this.tukam3;}
	
	private String tukalM = null;
	public void setTukalM(String value) {  this.tukalM = value; }
	public String getTukalM() {return this.tukalM;}
	
	private String tuto1a = null;
	public void setTuto1a(String value) {  this.tuto1a = value; }
	public String getTuto1a() {return this.tuto1a;}
	
	private String tuto1b = null;
	public void setTuto1b(String value) {  this.tuto1b = value; }
	public String getTuto1b() {return this.tuto1b;}
	
	private String tutval = "NOK";
	public void setTutval(String value) {  this.tutval = value; }
	public String getTutval() {return this.tutval;}
	
	private String tutako = null;
	public void setTutako(String value) {  this.tutako = value; }
	public String getTutako() {return this.tutako;}
	
	private String tutbel = null;
	public void setTutbel(String value) {  this.tutbel = value; }
	public String getTutbel() {return this.tutbel;}
	
	private String tutref = null;
	public void setTutref(String value) {  this.tutref = value; }
	public String getTutref() {return this.tutref;}
	
	private String tuhoyb = null;
	public void setTuhoyb(String value) {  this.tuhoyb = value; }
	public String getTuhoyb() {return this.tuhoyb;}
	
	private String tuhoyh = null;
	public void setTuhoyh(String value) {  this.tuhoyh = value; }
	public String getTuhoyh() {return this.tuhoyh;}
	
	private String tusg = null;
	public void setTusg(String value) {  this.tusg = value; }
	public String getTusg() {return this.tusg;}
	
	private String tuant1 = null;
	public void setTuant1(String value) {  this.tuant1 = value; }
	public String getTuant1() {return this.tuant1;}
	
	private String tuenh1 = null;
	public void setTuenh1(String value) {  this.tuenh1 = value; }
	public String getTuenh1() {return this.tuenh1;}
	
	private String tuant2 = null;
	public void setTuant2(String value) {  this.tuant2 = value; }
	public String getTuant2() {return this.tuant2;}
	
	private String tuenh2 = null;
	public void setTuenh2(String value) {  this.tuenh2 = value; }
	public String getTuenh2() {return this.tuenh2;}
	
	private String tukm = null;
	public void setTukm(String value) {  this.tukm = value; }
	public String getTukm() {return this.tukm;}
	
	private String tublnr = null;
	public void setTublnr(String value) {  this.tublnr = value; }
	public String getTublnr() {return this.tublnr;}
	
	private String tubkd = null;
	public void setTubkd(String value) {  this.tubkd = value; }
	public String getTubkd() {return this.tubkd;}
	
	private String tubnv = null;
	public void setTubnv(String value) {  this.tubnv = value; }
	public String getTubnv() {return this.tubnv;}
	
	private String tuetd = null;
	public void setTuetd(String value) {  this.tuetd = value; }
	public String getTuetd() {return this.tuetd;}
	
	private String tueta = null;
	public void setTueta(String value) {  this.tueta = value; }
	public String getTueta() {return this.tueta;}
	
	private String tuatd = null;
	public void setTuatd(String value) {  this.tuatd = value; }
	public String getTuatd() {return this.tuatd;}
	
	private String tuata = null;
	public void setTuata(String value) {  this.tuata = value; }
	public String getTuata() {return this.tuata;}
	
	private String tuckd1 = null;
	public void setTuckd1(String value) {  this.tuckd1 = value; }
	public String getTuckd1() {return this.tuckd1;}
	
	private String tuckd2 = null;
	public void setTuckd2(String value) {  this.tuckd2 = value; }
	public String getTuckd2() {return this.tuckd2;}
	
	private String tupoen = null;
	public void setTupoen(String value) {  this.tupoen = value; }
	public String getTupoen() {return this.tupoen;}
	
	private String tutlm2 = null;
	public void setTutlm2(String value) {  this.tutlm2 = value; }
	public String getTutlm2() {return this.tutlm2;}
	
	private String tustn1 = null;
	public void setTustn1(String value) {  this.tustn1 = value; }
	public String getTustn1() {return this.tustn1;}
	
	private String tustn2 = null;
	public void setTustn2(String value) {  this.tustn2 = value; }
	public String getTustn2() {return this.tustn2;}
	
	private String tustn3 = null;
	public void setTustn3(String value) {  this.tustn3 = value; }
	public String getTustn3() {return this.tustn3;}
	
	private String tustn4 = null;
	public void setTustn4(String value) {  this.tustn4 = value; }
	public String getTustn4() {return this.tustn4;}
	
	private String tustn5 = null;
	public void setTustn5(String value) {  this.tustn5 = value; }
	public String getTustn5() {return this.tustn5;}
	
	private String tustn6 = null;
	public void setTustn6(String value) {  this.tustn6 = value; }
	public String getTustn6() {return this.tustn6;}
	
	private String tustn7 = null;
	public void setTustn7(String value) {  this.tustn7 = value; }
	public String getTustn7() {return this.tustn7;}
	
	private String tustn8 = null;
	public void setTustn8(String value) {  this.tustn8 = value; }
	public String getTustn8() {return this.tustn8;}
	
	private String tustn9 = null;
	public void setTustn9(String value) {  this.tustn9 = value; }
	public String getTustn9() {return this.tustn9;}
	
	private String totiaa = null;
	public void setTotiaa(String value) {  this.totiaa = value; }
	public String getTotiaa() {return this.totiaa;}
	
	private String totioa = null;
	public void setTotioa(String value) {  this.totioa = value; }
	public String getTotioa() {return this.totioa;}
	
	private String totisa = null;
	public void setTotisa(String value) {  this.totisa = value; }
	public String getTotisa() {return this.totisa;}
	
	private String totiag = null;
	public void setTotiag(String value) {  this.totiag = value; }
	public String getTotiag() {return this.totiag;}
	
	private String totiog = null;
	public void setTotiog(String value) {  this.totiog = value; }
	public String getTotiog() {return this.totiog;}
	
	private String totisg = null;
	public void setTotisg(String value) {  this.totisg = value; }
	public String getTotisg() {return this.totisg;}
	
	private String totkaa = null;
	public void setTotkaa(String value) {  this.totkaa = value; }
	public String getTotkaa() {return this.totkaa;}
	
	private String totkoa = null;
	public void setTotkoa(String value) {  this.totkoa = value; }
	public String getTotkoa() {return this.totkoa;}
	
	private String totksa = null;
	public void setTotksa(String value) {  this.totksa = value; }
	public String getTotksa() {return this.totksa;}
	
	private String totkao = null;
	public void setTotkao(String value) {  this.totkao = value; }
	public String getTotkao() {return this.totkao;}
	
	private String totkoo = null;
	public void setTotkoo(String value) {  this.totkoo = value; }
	public String getTotkoo() {return this.totkoo;}
	
	private String totkso = null;
	public void setTotkso(String value) {  this.totkso = value; }
	public String getTotkso() {return this.totkso;}
	
	private String totopn = null;
	public void setTotopn(String value) {  this.totopn = value; }
	public String getTotopn() {return this.totopn;}
	
	private String totovf = null;
	public void setTotovf(String value) {  this.totovf = value; }
	public String getTotovf() {return this.totovf;}
	
	private String totsum = null;
	public void setTotsum(String value) {  this.totsum = value; }
	public String getTotsum() {return this.totsum;}
	
	private String tures = null;
	public void setTures(String value) {  this.tures = value; }
	public String getTures() {return this.tures;}
	
	private String berbud = null;
	public void setBerbud(String value) {  this.berbud = value; }
	public String getBerbud() {return this.berbud;}
	
	private String simlm = null;
	public void setSimlm(String value) {  this.simlm = value; }
	public String getSimlm() { return this.simlm; }
	
	private String simm3 = null;
	public void setSimm3(String value) {  this.simm3 = value; }
	public String getSimm3() { return this.simm3; }
	
	
	
    	
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
