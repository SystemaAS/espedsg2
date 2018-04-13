/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order;

import java.util.*;
import java.lang.reflect.Field;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeParametersRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingRecord;


/**
 * @author oscardelatorre
 * @date Maj 8, 2015
 * 
 */
public class JsonTransportDispWorkflowSpecificOrderRecord extends JsonAbstractGrandFatherRecord{

	private String totalNumberOfLines = null;
	public void setTotalNumberOfLines(String value) {  this.totalNumberOfLines = value; }
	public String getTotalNumberOfLines() { return this.totalNumberOfLines;}
	
	private String itemsType = null;
	public void setItemsType(String value) {  this.itemsType = value; }
	public String getItemsType() {return this.itemsType;}
	
	private JsonTransportDispOppdragTypeParametersRecord oppdragTypeParameters = null;
	public void setOppdragTypeParameters(JsonTransportDispOppdragTypeParametersRecord value) {  this.oppdragTypeParameters = value; }
	public JsonTransportDispOppdragTypeParametersRecord getOppdragTypeParameters() {return this.oppdragTypeParameters;}
	
	
	private boolean isValidPostalCodeHesdf = false;
	public void setIsValidPostalCodeHesdf(boolean value) {  this.isValidPostalCodeHesdf = value; }
	public boolean getIsValidPostalCodeHesdf() {return this.isValidPostalCodeHesdf;}

	private boolean isValidPostalCodeHesdt = false;
	public void setIsValidPostalCodeHesdt(boolean value) {  this.isValidPostalCodeHesdt = value; }
	public boolean getIsValidPostalCodeHesdt() {return this.isValidPostalCodeHesdt;}

	private boolean isValidPostalCodeHesdff = false;
	public void setIsValidPostalCodeHesdff(boolean value) {  this.isValidPostalCodeHesdff = value; }
	public boolean getIsValidPostalCodeHesdff() {return this.isValidPostalCodeHesdff;}

	private boolean isValidPostalCodeHesdvt = false;
	public void setIsValidPostalCodeHesdvt(boolean value) {  this.isValidPostalCodeHesdvt = value; }
	public boolean getIsValidPostalCodeHesdvt() {return this.isValidPostalCodeHesdvt;}

	
	private String orderLineToDelete = null;
	public void setOrderLineToDelete(String value) {  this.orderLineToDelete = value; }
	public String getOrderLineToDelete() {return this.orderLineToDelete;}
	
	private String messageNoteConsignee = null;
	public void setMessageNoteConsignee(String value) {  this.messageNoteConsignee = this.toUpperCase(value); }
	public String getMessageNoteConsignee() {return this.messageNoteConsignee;}
	//Original value is used in UPDATE in order to delete all lines first before creating the new ones
	private String messageNoteConsigneeOriginal = null;
	public void setMessageNoteConsigneeOriginal(String value) {  this.messageNoteConsigneeOriginal = value; }
	public String getMessageNoteConsigneeOriginal() {return this.messageNoteConsigneeOriginal;}

	private String messageNoteCarrier = null;
	public void setMessageNoteCarrier(String value) {  this.messageNoteCarrier = this.toUpperCase(value); }
	public String getMessageNoteCarrier() {return this.messageNoteCarrier;}
	//Original value is used in UPDATE in order to delete all lines first before creating the new ones
	private String messageNoteCarrierOriginal = null;
	public void setMessageNoteCarrierOriginal(String value) {  this.messageNoteCarrierOriginal = value; }
	public String getMessageNoteCarrierOriginal() {return this.messageNoteCarrierOriginal;}

	private String messageNoteInternal = null;
	public void setMessageNoteInternal(String value) {  this.messageNoteInternal = this.toUpperCase(value); }
	public String getMessageNoteInternal() {return this.messageNoteInternal;}
	//Original value is used in UPDATE in order to delete all lines first before creating the new ones
	private String messageNoteInternalOriginal = null;
	public void setMessageNoteInternalOriginal(String value) {  this.messageNoteInternalOriginal = value; }
	public String getMessageNoteInternalOriginal() {return this.messageNoteInternalOriginal;}
	
	private String wsatad = null;
	public void setWsatad(String value) {  this.wsatad = value; }
	public String getWsatad() {return this.wsatad;}
	
	private String wsatak = null;
	public void setWsatak(String value) {  this.wsatak = value; }
	public String getWsatak() {return this.wsatak;}
	
	private String wsatdd = null;
	public void setWsatdd(String value) {  this.wsatdd = value; }
	public String getWsatdd() {return this.wsatdd;}
	
	private String wsatdk = null;
	public void setWsatdk(String value) {  this.wsatdk = value; }
	public String getWsatdk() {return this.wsatdk;}
	
	private String wsscont = null;
	public void setWsscont(String value) {  this.wsscont = this.toUpperCase(value); }
	public String getWsscont() {return this.wsscont;}
	
	private String wsstlf = null;
	public void setWsstlf(String value) {  this.wsstlf = this.toUpperCase(value); }
	public String getWsstlf() {return this.wsstlf;}
	
	private String wssmail = null;
	public void setWssmail(String value) {  this.wssmail = this.toUpperCase(value); }
	public String getWssmail() {return this.wssmail;}
	
	private String wskcont = null;
	public void setWskcont(String value) {  this.wskcont = this.toUpperCase(value); }
	public String getWskcont() {return this.wskcont;}
	
	private String wsktlf = null;
	public void setWsktlf(String value) {  this.wsktlf = this.toUpperCase(value); }
	public String getWsktlf() {return this.wsktlf;}
	
	private String wskmail = null;
	public void setWskmail(String value) {  this.wskmail = this.toUpperCase(value); }
	public String getWskmail() {return this.wskmail;}
	
	private String hest = null;
	public void setHest(String value) {  this.hest = value; }
	public String getHest() {return this.hest;}
	
	private String heur = null;
	public void setHeur(String value) {  this.heur = value; }
	public String getHeur() {return this.heur;}
	
	private String heavd = null;
	public void setHeavd(String value) {  this.heavd = value; }
	public String getHeavd() {return this.heavd;}
	
	private String heopd = null;
	public void setHeopd(String value) {  this.heopd = value; }
	public String getHeopd() {return this.heopd;}
	
	private String hesg = null;
	public void setHesg(String value) {  this.hesg = value; }
	public String getHesg() {return this.hesg;}
	
	private String hedtr = null;
	public void setHedtr(String value) {  this.hedtr = value; }
	public String getHedtr() {return this.hedtr;}

	private String hebodt = null;
	public void setHebodt(String value) {  this.hebodt = value; }
	public String getHebodt() {return this.hebodt;}

	private String wsbotm = null;
	public void setWsbotm(String value) {  this.wsbotm = value; }
	public String getWsbotm() {return this.wsbotm;}
	
	private String hegnn = null;
	public void setHegnn(String value) {  this.hegnn = value; }
	public String getHegnn() {return this.hegnn;}
	
	private String hepro = null;
	public void setHepro(String value) {  this.hepro = value; }
	public String getHepro() {return this.hepro;}
	
	private String hegn = null;
	public void setHegn(String value) {  this.hegn = value; }
	public String getHegn() {return this.hegn;}
	
	private String heplan = null;
	public void setHeplan(String value) {  this.heplan = value; }
	public String getHeplan() {return this.heplan;}
	
	private String trrund = null;
	public void setTrrund(String value) {  this.trrund = value; }
	public String getTrrund() {return this.trrund;}
	
	private String hepos1 = null;
	public void setHepos1(String value) {  this.hepos1 = value; }
	public String getHepos1() {return this.hepos1;}
	
	private String hepos2 = null;
	public void setHepos2(String value) {  this.hepos2 = value; }
	public String getHepos2() {return this.hepos2;}
	
	private String trknfa = null;
	public void setTrknfa(String value) {  this.trknfa = value; }
	public String getTrknfa() {return this.trknfa;}
	
	private String henao = null;
	public void setHenao(String value) {  this.henao = value; }
	public String getHenao() {return this.henao;}
	
	private String hekna = null;
	public void setHekna(String value) {  this.hekna = value; }
	public String getHekna() {return this.hekna;}
	
	private String henaa = null;
	public void setHenaa(String value) {  this.henaa = value; }
	public String getHenaa() {return this.henaa;}
	
	private String herfa = null;
	public void setHerfa(String value) {  this.herfa = this.toUpperCase(value); }
	public String getHerfa() {return this.herfa;}
	
	private String hesaml = null;
	public void setHesaml(String value) {  this.hesaml = value; }
	public String getHesaml() {return this.hesaml;}
	
	private String hehawb = null;
	public void setHehawb(String value) {  this.hehawb = value; }
	public String getHehawb() {return this.hehawb;}
	
	private String hetrcn = null;
	public void setHetrcn(String value) {  this.hetrcn = value; }
	public String getHetrcn() {return this.hetrcn;}
	
	private String hesdla = null;
	public void setHesdla(String value) {  this.hesdla = value; }
	public String getHesdla() {return this.hesdla;}
	
	private String hesdl = null;
	public void setHesdl(String value) {  this.hesdl = value; }
	public String getHesdl() {return this.hesdl;}
	
	private String hevalt = null;
	public void setHevalt(String value) {  this.hevalt = value; }
	public String getHevalt() {return this.hevalt;}
	
	private String hevalp = null;
	public void setHevalp(String value) {  this.hevalp = value; }
	public String getHevalp() {return this.hevalp;}
	
	private String heknt = null;
	public void setHeknt(String value) {  this.heknt = value; }
	public String getHeknt() {return this.heknt;}
	
	private String helka = null;
	public void setHelka(String value) {  this.helka = value; }
	public String getHelka() {return this.helka;}
	
	private String hekns = null;
	public void setHekns(String value) {  this.hekns = value; }
	public String getHekns() {return this.hekns;}
	
	private String henas = null;
	public void setHenas(String value) {  this.henas = this.toUpperCase(value); }
	public String getHenas() {return this.henas;}
	
	private String heads1 = null;
	public void setHeads1(String value) {  this.heads1 = this.toUpperCase(value); }
	public String getHeads1() {return this.heads1;}
	
	private String heads2 = null;
	public void setHeads2(String value) {  this.heads2 = this.toUpperCase(value); }
	public String getHeads2() {return this.heads2;}
	
	private String hepns = null;
	public void setHepns(String value) {  this.hepns = value; }
	public String getHepns() {return this.hepns;}
	
	private String heads3 = null;
	public void setHeads3(String value) {  this.heads3 = this.toUpperCase(value); }
	public String getHeads3() {return this.heads3;}
	
	private String helks = null;
	public void setHelks(String value) {  this.helks = value; }
	public String getHelks() {return this.helks;}
	
	private String heknh = null;
	public void setHeknh(String value) {  this.heknh = value; }
	public String getHeknh() {return this.heknh;}
	
	private String henah = null;
	public void setHenah(String value) {  this.henah = value; }
	public String getHenah() {return this.henah;}
	
	private String headh1 = null;
	public void setHeadh1(String value) {  this.headh1 = value; }
	public String getHeadh1() {return this.headh1;}
	
	private String headh2 = null;
	public void setHeadh2(String value) {  this.headh2 = value; }
	public String getHeadh2() {return this.headh2;}
	
	private String hepnh = null;
	public void setHepnh(String value) {  this.hepnh = value; }
	public String getHepnh() {return this.hepnh;}
	
	private String headh3 = null;
	public void setHeadh3(String value) {  this.headh3 = value; }
	public String getHeadh3() {return this.headh3;}
	
	private String helkh = null;
	public void setHelkh(String value) {  this.helkh = value; }
	public String getHelkh() {return this.helkh;}
	
	private String hehbre = null;
	public void setHehbre(String value) {  this.hehbre = value; }
	public String getHehbre() {return this.hehbre;}
	
	private String hehlen = null;
	public void setHehlen(String value) {  this.hehlen = value; }
	public String getHehlen() {return this.hehlen;}
	
	private String hehblk = null;
	public void setHehblk(String value) {  this.hehblk = value; }
	public String getHehblk() {return this.hehblk;}
	
	private String hekdfs = null;
	public void setHekdfs(String value) {  this.hekdfs = value; }
	public String getHekdfs() {return this.hekdfs;}
	
	private String hevals = null;
	public void setHevals(String value) {  this.hevals = value; }
	public String getHevals() {return this.hevals;}
	
	private String heknsf = null;
	public void setHeknsf(String value) {  this.heknsf = value; }
	public String getHeknsf() {return this.heknsf;}
	
	private String heans = null;
	public void setHeans(String value) {  this.heans = value; }
	public String getHeans() {return this.heans;}
	
	private String henasf = null;
	public void setHenasf(String value) {  this.henasf = value; }
	public String getHenasf() {return this.henasf;}
	
	private String heknk = null;
	public void setHeknk(String value) {  this.heknk = value; }
	public String getHeknk() {return this.heknk;}
	
	
	private String henak = null;
	public void setHenak(String value) {  this.henak = this.toUpperCase(value); }
	public String getHenak() {return this.henak;}
	
	private String headk1 = null;
	public void setHeadk1(String value) {  this.headk1 = this.toUpperCase(value); }
	public String getHeadk1() {return this.headk1;}
	
	private String headk2 = null;
	public void setHeadk2(String value) {  this.headk2 = this.toUpperCase(value); }
	public String getHeadk2() {return this.headk2;}
	
	private String hepnk = null;
	public void setHepnk(String value) {  this.hepnk = value; }
	public String getHepnk() {return this.hepnk;}
	
	private String headk3 = null;
	public void setHeadk3(String value) {  this.headk3 = this.toUpperCase(value); }
	public String getHeadk3() {return this.headk3;}

	private String helkk = null;
	public void setHelkk(String value) {  this.helkk = value; }
	public String getHelkk() {return this.helkk;}

	private String heknl = null;
	public void setHeknl(String value) {  this.heknl = value; }
	public String getHeknl() {return this.heknl;}

	private String henal = null;
	public void setHenal(String value) {  this.henal = value; }
	public String getHenal() {return this.henal;}

	private String headl1 = null;
	public void setHeadl1(String value) {  this.headl1 = value; }
	public String getHeadl1() {return this.headl1;}

	private String headl2 = null;
	public void setHeadl2(String value) {  this.headl2 = value; }
	public String getHeadl2() {return this.headl2;}

	private String headl3 = null;
	public void setHeadl3(String value) {  this.headl3 = value; }
	public String getHeadl3() {return this.headl3;}

	private String hepnl = null;
	public void setHepnl(String value) {  this.hepnl = value; }
	public String getHepnl() {return this.hepnl;}

	private String helkl = null;
	public void setHelkl(String value) {  this.helkl = value; }
	public String getHelkl() {return this.helkl;}

	private String helbre = null;
	public void setHelbre(String value) {  this.helbre = value; }
	public String getHelbre() {return this.helbre;}

	private String hellen = null;
	public void setHellen(String value) {  this.hellen = value; }
	public String getHellen() {return this.hellen;}

	private String helblk = null;
	public void setHelblk(String value) {  this.helblk = value; }
	public String getHelblk() {return this.helblk;}

	private String hekdfk = null;
	public void setHekdfk(String value) {  this.hekdfk = value; }
	public String getHekdfk() {return this.hekdfk;}

	private String hevalk = null;
	public void setHevalk(String value) {  this.hevalk = value; }
	public String getHevalk() {return this.hevalk;}

	private String heknkf = null;
	public void setHeknkf(String value) {  this.heknkf = value; }
	public String getHeknkf() {return this.heknkf;}

	private String heank = null;
	public void setHeank(String value) {  this.heank = value; }
	public String getHeank() {return this.heank;}

	private String henakf = null;
	public void setHenakf(String value) {  this.henakf = value; }
	public String getHenakf() {return this.henakf;}

	//Avtalekunder (Selger /nr och navn)
	private String avtknrs = null;
	public void setAvtknrs(String value) {  this.avtknrs = value; }
	public String getAvtknrs() {return this.avtknrs;}
	private String avtknavs = null;
	public void setAvtknavs(String value) {  this.avtknavs = value; }
	public String getAvtknavs() {return this.avtknavs;}

	//Avtalekunder (Kjøper /nr och navn)
	private String avtknrk = null;
	public void setAvtknrk(String value) {  this.avtknrk = value; }
	public String getAvtknrk() {return this.avtknrk;}
	private String avtknavk = null;
	public void setAvtknavk(String value) {  this.avtknavk = value; }
	public String getAvtknavk() {return this.avtknavk;}

	
	private String heot = null;
	public void setHeot(String value) {  this.heot = value; }
	public String getHeot() {return this.heot;}

	private String wppns1 = null;
	public void setWppns1(String value){ this.wppns1 = value;}
	public String getWppns1(){ return this.wppns1; }
	
	private String wppns2 = null;
	public void setWppns2(String value){ this.wppns2 = value;}
	public String getWppns2(){ return this.wppns2; }
	
	private String wppns3 = null;
	public void setWppns3(String value){ this.wppns3 = value;}
	public String getWppns3(){ return this.wppns3; }
	
	private String wppns4 = null;
	public void setWppns4(String value){ this.wppns4 = value;}
	public String getWppns4(){ return this.wppns4; }
	
	private String hekdpl = null;
	public void setHekdpl(String value) {  this.hekdpl = this.toUpperCase(value); }
	public String getHekdpl() {return this.hekdpl;}

	private String hefr = null;
	public void setHefr(String value) {  this.hefr = value; }
	public String getHefr() {return this.hefr;}

	
	private String hekf = null;
	public void setHekf(String value) {  this.hekf = value; }
	public String getHekf() {return this.hekf;}

	private String hekft1 = null;
	public void setHekft1(String value) {  this.hekft1 = value; }
	public String getHekft1() {return this.hekft1;}

	private String heft1 = null;
	public void setHeft1(String value) {  this.heft1 = value; }
	public String getHeft1() {return this.heft1;}

	private String hedtcp = null;
	public void setHedtcp(String value) {  this.hedtcp = value; }
	public String getHedtcp() {return this.hedtcp;}

	private String hedtap = null;
	public void setHedtap(String value) {  this.hedtap = value; }
	public String getHedtap() {return this.hedtap;}

	private String hedtmp = null;
	public void setHedtmp(String value) {  this.hedtmp = value; }
	public String gethedtmp() {return this.hedtmp;}

	private String hekdkk = null;
	public void setHekdkk(String value) {  this.hekdkk = value; }
	public String getHekdkk() {return this.hekdkk;}

	private String hefngk = null;
	public void setHefngk(String value) {  this.hefngk = value; }
	public String getHefngk() {return this.hefngk;}

	private String hefngs = null;
	public void setHefngs(String value) {  this.hefngs = value; }
	public String getHefngs() {return this.hefngs;}

	private String hekdtm = null;
	public void setHekdtm(String value) {  this.hekdtm = value; }
	public String getHekdtm() {return this.hekdtm;}

	private String hetrm = null;
	public void setHetrm(String value) {  this.hetrm = value; }
	public String getHetrm() {return this.hetrm;}

	private String hetri = null;
	public void setHetri(String value) {  this.hetri = value; }
	public String getHetri() {return this.hetri;}

	private String hetrc = null;
	public void setHetrc(String value) {  this.hetrc = value; }
	public String getHetrc() {return this.hetrc;}

	private String hefbk = null;
	public void setHefbk(String value) {  this.hefbk = value; }
	public String getHefbk() {return this.hefbk;}

	private String hetle = null;
	public void setHetle(String value) {  this.hetle = value; }
	public String getHetle() {return this.hetle;}

	private String hetll = null;
	public void setHetll(String value) {  this.hetll = value; }
	public String getHetll() {return this.hetll;}

	private String hetlku = null;
	public void setHetlku(String value) {  this.hetlku = value; }
	public String getHetlku() {return this.hetlku;}

	private String hentvs = null;
	public void setHentvs(String value) {  this.hentvs = value; }
	public String getHentvs() {return this.hentvs;}

	private String xkda = null;
	public void setXkda(String value) {  this.xkda = value; }
	public String getXkda() {return this.xkda;}

	
	private String hevn = null;
	public void setHevn(String value) {  this.hevn = value; }
	public String getHevn() {return this.hevn;}

	private String hentf = null;
	public void setHentf(String value) {  this.hentf = value; }
	public String getHentf() {return this.hentf;}

	private String hebelt = null;
	public void setHebelt(String value) {  this.hebelt = value; }
	public String getHebelt() {return this.hebelt;}

	private String hebelm = null;
	public void setHebelm(String value) {  this.hebelm = value; }
	public String getHebelm() {return this.hebelm;}

	private String heklmo = null;
	public void setHeklmo(String value) {  this.heklmo = value; }
	public String getHeklmo() {return this.heklmo;}

	private String ttstat = null;
	public void setTtstat(String value) {  this.ttstat = value; }
	public String getTtstat() {return this.ttstat;}

	private String hepk1 = null;
	public void setHepk1(String value) {  this.hepk1 = this.toUpperCase(value); }
	public String getHepk1() {return this.hepk1;}

	private String hepk2 = null;
	public void setHepk2(String value) {  this.hepk2 = this.toUpperCase(value); }
	public String getHepk2() {return this.hepk2;}

	private String hepk3 = null;
	public void setHepk3(String value) {  this.hepk3 = this.toUpperCase(value); }
	public String getHepk3() {return this.hepk3;}

	private String hepk4 = null;
	public void setHepk4(String value) {  this.hepk4 = this.toUpperCase(value); }
	public String getHepk4() {return this.hepk4;}

	private String hepk5 = null;
	public void setHepk5(String value) {  this.hepk5 = this.toUpperCase(value); }
	public String getHepk5() {return this.hepk5;}

	private String hepk6 = null;
	public void setHepk6(String value) {  this.hepk6 = this.toUpperCase(value); }
	public String getHepk6() {return this.hepk6;}

	private String hepk7 = null;
	public void setHepk7(String value) {  this.hepk7 = this.toUpperCase(value); }
	public String getHepk7() {return this.hepk7;}

	private String hepk8 = null;
	public void setHepk8(String value) {  this.hepk8 = value; }
	public String getHepk8() {return this.hepk8;}

	private String hepk9 = null;
	public void setHepk9(String value) {  this.hepk9 = value; }
	public String getHepk9() {return this.hepk9;}

	private String w2toll = null;
	public void setW2toll(String value) {  this.w2toll = value; }
	public String getW2toll() {return this.w2toll;}

	private String dftoll = null;
	public void setDftoll(String value) {  this.dftoll = value; }
	public String getDftoll() {return this.dftoll;}

	private String dftran = null;
	public void setDftran(String value) {  this.dftran = value; }
	public String getDftran() {return this.dftran;}

	private String w2kdme = null; //märkelapp (1 ch)
	public void setW2kdme(String value) {  this.w2kdme = value; }
	public String getW2kdme() {return this.w2kdme;}

	private String dfkdme = null; //märkelapp (1 ch)
	public void setDfkdme(String value) {  this.dfkdme = this.toUpperCase(value); }
	public String getDfkdme() {return this.dfkdme;}

	
	private String w2kde = null;// 1 ch
	public void setW2kde(String value) {  this.w2kde = value; }
	public String getW2kde() {return this.w2kde;}

	private String hekdl = null;
	public void setHekdl(String value) {  this.hekdl = value; }
	public String getHekdl() {return this.hekdl;}

	private String helb = null;
	public void setHelb(String value) {  this.helb = value; }
	public String getHelb() {return this.helb;}

	private String helkfr = null;
	public void setHelkfr(String value) {  this.helkfr = value; }
	public String getHelkfr() {return this.helkfr;}

	private String hesdfr = null;
	public void setHesdfr(String value) {  this.hesdfr = value; }
	public String getHesdfr() {return this.hesdfr;}

	private String hestfr = null;
	public void setHestfr(String value) {  this.hestfr = value; }
	public String getHestfr() {return this.hestfr;}

	private String herfk = null;
	public void setHerfk(String value) {  this.herfk = this.toUpperCase(value); }
	public String getHerfk() {return this.herfk;}

	private String trslag = null;
	public void setTrslag(String value) {  this.trslag = value; }
	public String getTrslag() {return this.trslag;}

	private String travd0 = null;
	public void setTravd0(String value) {  this.travd0 = value; }
	public String getTravd0() {return this.travd0;}

	private String tropd0 = null;
	public void setTropd0(String value) {  this.tropd0 = value; }
	public String getTropd0() {return this.tropd0;}

	private String travd1 = null;
	public void setTravd1(String value) {  this.travd1 = value; }
	public String getTravd1() {return this.travd1;}

	private String tropd1 = null;
	public void setTropd1(String value) {  this.tropd1 = value; }
	public String getTropd1() {return this.tropd1;}

	private String travd2 = null;
	public void setTravd2(String value) {  this.travd2 = value; }
	public String getTravd2() {return this.travd2;}

	private String tropd2 = null;
	public void setTropd2(String value) {  this.tropd2 = value; }
	public String getTropd2() {return this.tropd2;}

	private String trsffd = null;
	public void setTrsffd(String value) {  this.trsffd = value; }
	public String getTrsffd() {return this.trsffd;}

	private String trsffk = null;
	public void setTrsffk(String value) {  this.trsffk = value; }
	public String getTrsffk() {return this.trsffk;}
	/*
	private String trsdfd = null;
	public void setTrsdfd(String value) {  this.trsdfd = value; }
	public String getTrsdfd() {return this.trsdfd;}
	
	private String trsdtd = null;
	public void setTrsdtd(String value) {  this.trsdtd = value; }
	public String getTrsdtd() {return this.trsdtd;}
	*/
	
	private String wsetdd = null;
	public void setWsetdd(String value) {  this.wsetdd = value; }
	public String getWsetdd() {return this.wsetdd;}

	private String wsetdk = null;
	public void setWsetdk(String value) {  this.wsetdk = value; }
	public String getWsetdk() {return this.wsetdk;}

	private String wsetad = null;
	public void setWsetad(String value) {  this.wsetad = value; }
	public String getWsetad() {return this.wsetad;}

	private String wsetak = null;
	public void setWsetak(String value) {  this.wsetak = value; }
	public String getWsetak() {return this.wsetak;}

	private String trsdfk = null;
	public void setTrsdfk(String value) {  this.trsdfk = value; }
	public String getTrsdfk() {return this.trsdfk;}
	
	private String trsdtk = null;
	public void setTrsdtk(String value) {  this.trsdtk = value; }
	public String getTrsdtk() {return this.trsdtk;}

	private String trsvtd = null;
	public void setTrsvtd(String value) {  this.trsvtd = value; }
	public String getTrsvtd() {return this.trsvtd;}

	private String trsvtk = null;
	public void setTrsvtk(String value) {  this.trsvtk = value; }
	public String getTrsvtk() {return this.trsvtk;}

	private String trkdak = null;
	public void setTrkdak(String value) {  this.trkdak = value; }
	public String getTrkdak() {return this.trkdak;}

	private String trkdff = null;
	public void setTrkdff(String value) {  this.trkdff = value; }
	public String getTrkdff() {return this.trkdff;}

	private String trverv = null;
	public void setTrverv(String value) {  this.trverv = value; }
	public String getTrverv() {return this.trverv;}

	private String trverb = null;
	public void setTrverb(String value) {  this.trverb = value; }
	public String getTrverb() {return this.trverb;}

	private String trettv = null;
	public void setTrettv(String value) {  this.trettv = value; }
	public String getTrettv() {return this.trettv;}

	private String trettb = null;
	public void setTrettb(String value) {  this.trettb = value; }
	public String getTrettb() {return this.trettb;}

	private String trfrak = null;
	public void setTrfrak(String value) {  this.trfrak = value; }
	public String getTrfrak() {return this.trfrak;}

	private String trfrav = null;
	public void setTrfrav(String value) {  this.trfrav = value; }
	public String getTrfrav() {return this.trfrav;}

	private String trfrab = null;
	public void setTrfrab(String value) {  this.trfrab = value; }
	public String getTrfrab() {return this.trfrab;}

	private String trmva = null;
	public void setTrmva(String value) {  this.trmva = this.toUpperCase(value); }
	public String getTrmva() {return this.trmva;}

	private String trfa11 = null;
	public void setTrfa11(String value) {  this.trfa11 = value; }
	public String getTrfa11() {return this.trfa11;}

	private String trfa12 = null;
	public void setTrfa12(String value) {  this.trfa12 = value; }
	public String getTrfa12() {return this.trfa12;}

	private String trflp1 = null;
	public void setTrflp1(String value) {  this.trflp1 = value; }
	public String getTrflp1() {return this.trflp1;}

	private String trfa21 = null;
	public void setTrfa21(String value) {  this.trfa21 = value; }
	public String getTrfa21() {return this.trfa21;}

	private String trfa22 = null;
	public void setTrfa22(String value) {  this.trfa22 = value; }
	public String getTrfa22() {return this.trfa22;}

	private String trflp2 = null;
	public void setTrflp2(String value) {  this.trflp2 = value; }
	public String getTrflp2() {return this.trflp2;}

	private String trtsta = null;
	public void setTrtsta(String value) {  this.trtsta = value; }
	public String getTrtsta() {return this.trtsta;}

	private String trstaf = null;
	public void setTrstaf(String value) {  this.trstaf = value; }
	public String getTrstaf() {return this.trstaf;}

	private String trstae = null;
	public void setTrstae(String value) {  this.trstae = value; }
	public String getTrstae() {return this.trstae;}

	private String trsta1 = null;
	public void setTrsta1(String value) {  this.trsta1 = value; }
	public String getTrsta1() {return this.trsta1;}

	private String trsta2 = null;
	public void setTrsta2(String value) {  this.trsta2 = value; }
	public String getTrsta2() {return this.trsta2;}

	private String trsta3 = null;
	public void setTrsta3(String value) {  this.trsta3 = value; }
	public String getTrsta3() {return this.trsta3;}

	private String trsta4 = null;
	public void setTrsta4(String value) {  this.trsta4 = value; }
	public String getTrtsta4() {return this.trsta4;}

	private String trkm = null;
	public void setTrkm(String value) {  this.trkm = value; }
	public String getTrkm() {return this.trkm;}

	private String he01 = null;
	public void setHe01(String value) {  this.he01 = value; }
	public String getHe01() {return this.he01;}

	private String hestd = null;
	public void setHestd(String value) {  this.hestd = value; }
	public String getHestd() {return this.hestd;}

	private String hxsndn = null;
	public void setHxsndn(String value) {  this.hxsndn = value; }
	public String getHxsndn() {return this.hxsndn;}

	private String hxpall = null;
	public void setHxpall(String value) {  this.hxpall = value; }
	public String getHxpall() {return this.hxpall;}

	private String hxblgk = null;
	public void setHxblgk(String value) {  this.hxblgk = value; }
	public String getHxblgk() {return this.hxblgk;}

	private String hxlkod = null;
	public void setHxlkod(String value) {  this.hxlkod = value; }
	public String getHxlkod() {return this.hxlkod;}

	private String hxllst = null;
	public void setHxllst(String value) {  this.hxllst = value; }
	public String getHxllst() {return this.hxllst;}

	private String hxedik = null;
	public void setHxedik(String value) {  this.hxedik = value; }
	public String getHxedik() {return this.hxedik;}

	private String hehakn = null;
	public void setHehakn(String value) {  this.hehakn = value; }
	public String getHehakn() {return this.hehakn;}

	private String hehaan = null;
	public void setHehaan(String value) {  this.hehaan = value; }
	public String getHehaan() {return this.hehaan;}

	private String helakn = null;
	public void setHelakn(String value) {  this.helakn = value; }
	public String getHelakn() {return this.helakn;}

	private String helaan = null;
	public void setHelaan(String value) {  this.helaan = value; }
	public String getHelaan() {return this.helaan;}

	private String helmla = null;
	public void setHelmla(String value) {  this.helmla = value; }
	public String getHelmla() {return this.helmla;}

	private String hepoen = null;
	public void setHepoen(String value) {  this.hepoen = value; }
	public String getHepoen() {return this.hepoen;}

	private String hestl1 = null;
	public void setHestl1(String value) {  this.hestl1 = value; }
	public String getHestl1() {return this.hestl1;}

	private String hestl2 = null;
	public void setHestl2(String value) {  this.hestl2 = value; }
	public String getHestl2() {return this.hestl2;}

	private String hestl3 = null;
	public void setHestl3(String value) {  this.hestl3 = value; }
	public String getHestl3() {return this.hestl3;}

	private String hestl4 = null;
	public void setHestl4(String value) {  this.hestl4 = value; }
	public String getHestl4() {return this.hestl4;}

	private String hestn1 = null;
	public void setHestn1(String value) { this.hestn1 = this.toUpperCase(value); }
	public String getHestn1() {return this.hestn1;}

	private String hestn2 = null;
	public void setHestn2(String value) { this.hestn2 = this.toUpperCase(value); }
	public String getHestn2() {return this.hestn2;}

	private String hestn3 = null;
	public void setHestn3(String value) { this.hestn3 = this.toUpperCase(value); }
	public String getHestn3() {return this.hestn3;}

	private String hestn4 = null;
	public void setHestn4(String value) { this.hestn4 = this.toUpperCase(value); }
	public String getHestn4() {return this.hestn4;}

	private String hestn5 = null;
	public void setHestn5(String value) { this.hestn5 = this.toUpperCase(value); }
	public String getHestn5() {return this.hestn5;}

	private String hestn6 = null;
	public void setHestn6(String value) { this.hestn6 = this.toUpperCase(value); }
	public String getHestn6() {return this.hestn6;}
	

	private String hestn7 = null;
	public void setHestn7(String value) {  this.hestn7 = value; }
	public String getHestn7() {return this.hestn7;}

	private String hestn8 = null;
	public void setHestn8(String value) {  this.hestn8 = value; }
	public String getHestn8() {return this.hestn8;}

	private String hestn9 = null;
	public void setHestn9(String value) {  this.hestn9 = value; }
	public String getHestn9() {return this.hestn9;}

	private String herfed = null;
	public void setHerfed(String value) {  this.herfed = value; }
	public String getHerfed() {return this.herfed;}

	private String helib = null;
	public void setHelib(String value) {  this.helib = value; }
	public String getHelib() {return this.helib;}

	private String hefile = null;
	public void setHefile(String value) {  this.hefile = value; }
	public String getHefile() {return this.hefile;}

	private String hembr = null;
	public void setHembr(String value) {  this.hembr = value; }
	public String getHembr() {return this.hembr;}

	private String hetype = null;
	public void setHetype(String value) {  this.hetype = value; }
	public String getHetype() {return this.hetype;}

	private String helvl = null;
	public void setHelvl(String value) {  this.helvl = value; }
	public String getHelvl() {return this.helvl;}

	private String hesnn = null;
	public void setHesnn(String value) {  this.hesnn = value; }
	public String getHesnn() {return this.hesnn;}

	private String heunik = null;
	public void setHeunik(String value) {  this.heunik = value; }
	public String getHeunik() {return this.heunik;}

	private String hereff = null;
	public void setHereff(String value) {  this.hereff = value; }
	public String getHereff() {return this.hereff;}

	
	private String hesdff = null;
	public void setHesdff(String value) {  this.hesdff = value; }
	public String getHesdff() {return this.hesdff;}

	private String hesdf = null;
	public void setHesdf(String value) {  this.hesdf = value; }
	public String getHesdf() {return this.hesdf;}

	private String hesdt = null;
	public void setHesdt(String value) {  this.hesdt = value; }
	public String getHesdt() {return this.hesdt;}
	
	private String hesdvt = null;
	public void setHesdvt(String value) {  this.hesdvt = value; }
	public String getHesdvt() {return this.hesdvt;}

	private String hent = null;
	public void setHent(String value) {  this.hent = value; }
	public String getHent() {return this.hent;}
	
	private String hedtmo = null;
	public void setHedtmo(String value) {  this.hedtmo = value; }
	public String getHedtmo() {return this.hedtmo;}
	
	private String hevkt = null;
	public void setHevkt(String value) {  this.hevkt = value; }
	public String getHevkt() {return this.hevkt;}
	
	private String hefbv = null;
	public void setHefbv(String value) {  this.hefbv = value; }
	public String getHefbv() {return this.hefbv;}
	
	private String hem3 = null;
	public void setHem3(String value) {  this.hem3 = value; }
	public String getHem3() {return this.hem3;}
	
	private String helm = null;
	public void setHelm(String value) {  this.helm = value; }
	public String getHelm() {return this.helm;}
	
	private String hevs1 = null;
	public void setHevs1(String value) {  this.hevs1 = value; }
	public String getHevs1() {return this.hevs1;}
	
	private String hevs2 = null;
	public void setHevs2(String value) {  this.hevs2 = value; }
	public String getHevs2() {return this.hevs2;}
	
	private String hegm1 = null;
	public void setHegm1(String value) {  this.hegm1 = value; }
	public String getHegm1() {return this.hegm1;}
	
	private String hegm2 = null;
	public void setHegm2(String value) {  this.hegm2 = value; }
	public String getHegm2() {return this.hegm2;}
	
	private String hesgm = null;
	public void setHesgm(String value) {  this.hesgm = value; }
	public String getHesgm() {return this.hesgm;}
	
	private String whenas = null;
	public void setWhenas(String value) {  this.whenas = value; }
	public String getWhenas() {return this.whenas;}
	
	private String whenak = null;
	public void setWhenak(String value) {  this.whenak = value; }
	public String getWhenak() {return this.whenak;}
	
	//START Via-fält
	private String ffavd = null;
	public void setFfavd(String value) {  this.ffavd = value; }
	public String getFfavd() {return this.ffavd;}
	
	private String ffoty = null;
	public void setFfoty(String value) {  this.ffoty = value; }
	public String getFfoty() {return this.ffoty;}
	
	private String fffrank = null;
	public void setFffrank(String value) {  this.fffrank = value; }
	public String getFffrank() {return this.fffrank;}
	
	private String ffftxt = null;
	public void setFfftxt(String value) {  this.ffftxt = value; }
	public String getFfftxt() {return this.ffftxt;}
	
	private String ffmodul = null;
	public void setFfmodul(String value) {  this.ffmodul = value; }
	public String getFfmodul() {return this.ffmodul;}
	
	private String ffpkod = null;
	public void setFfpkod(String value) {  this.ffpkod = value; }
	public String getFfpkod() {return this.ffpkod;}
	
	private String ffbnr = null;
	public void setFfbnr(String value) {  this.ffbnr = value; }
	public String getFfbnr() {return this.ffbnr;}
	
	private String fftran = null;
	public void setFftran(String value) {  this.fftran = value; }
	public String getFftran() {return this.fftran;}
	
	private String ffkomm = null;
	public void setFfkomm(String value) {  this.ffkomm = value; }
	public String getFfkomm() {return this.ffkomm;}
	
	private String ffbel = null;
	public void setFfbel(String value) {  this.ffbel = value; }
	public String getFfbel() {return this.ffbel;}
	
	private String ffbelk = null;
	public void setFfbelk(String value) {  this.ffbelk = value; }
	public String getFfbelk() {return this.ffbelk;}
	
	private String vfavd = null;
	public void setVfavd(String value) {  this.vfavd = value; }
	public String getVfavd() {return this.vfavd;}
	
	private String vfoty = null;
	public void setVfoty(String value) {  this.vfoty = value; }
	public String getVfoty() {return this.vfoty;}
	
	private String vffrank = null;
	public void setVffrank(String value) {  this.vffrank = value; }
	public String getVffrank() {return this.vffrank;}
	
	private String vfftxt = null;
	public void setVfftxt(String value) {  this.vfftxt = value; }
	public String getVfftxt() {return this.vfftxt;}
	
	private String vfmodul = null;
	public void setVfmodul(String value) {  this.vfmodul = value; }
	public String getVfmodul() {return this.vfmodul;}
	
	private String vfpkod = null;
	public void setVfpkod(String value) {  this.vfpkod = value; }
	public String getVfpkod() {return this.vfpkod;}
	
	private String vfbnr = null;
	public void setVfbnr(String value) {  this.vfbnr = value; }
	public String getVfbnr() {return this.vfbnr;}
	
	private String vftran = null;
	public void setVftran(String value) {  this.vftran = value; }
	public String getVftran() {return this.vftran;}
	
	private String vfkomm = null;
	public void setVfkomm(String value) {  this.vfkomm = value; }
	public String getVfkomm() {return this.vfkomm;}
	
	private String vfbel = null;
	public void setVfbel(String value) {  this.vfbel = value; }
	public String getVfbel() {return this.vfbel;}
	
	private String vfbelk = null;
	public void setVfbelk(String value) {  this.vfbelk = value; }
	public String getVfbelk() {return this.vfbelk;}
	//END Via-fält
	
	
	private List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> fraktbrevList = null;
	public void setFraktbrevList(List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> value) {  this.fraktbrevList = value; }
	public List<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> getFraktbrevList() {return this.fraktbrevList;}
	
	private String singleLine = "N";
	public String getSingleLine() {
		if(this.fraktbrevList!=null){
			int i = 0;
			for(JsonTransportDispWorkflowSpecificOrderFraktbrevRecord lineRecord : this.fraktbrevList){
				if(lineRecord.getFvlinr()!=null && !"".equals(lineRecord.getFvlinr())){
					i++;
				}
			}
			if(i==1){
				this.singleLine = "Y";
			}
		}
		return this.singleLine;
	}
	//nothing just a dummy method to avoid exceptions in java reflections implementations
	public void setSingleLine(String value) { }
		
	
	private Collection<JsonTransportDispWorkflowSpecificOrderArchivedDocsRecord> archivedDocsRecord = null;
	public void setArchivedDocsRecord(Collection<JsonTransportDispWorkflowSpecificOrderArchivedDocsRecord> value) {  this.archivedDocsRecord = value; }
	public Collection<JsonTransportDispWorkflowSpecificOrderArchivedDocsRecord> getArchivedDocsRecord() {return this.archivedDocsRecord;}
	
	
	private Collection<JsonTransportDispWorkflowSpecificOrderLoggingRecord> loggingRecord = null;
	public void setLoggingRecord(Collection<JsonTransportDispWorkflowSpecificOrderLoggingRecord> value) {  this.loggingRecord = value; }
	public Collection<JsonTransportDispWorkflowSpecificOrderLoggingRecord> getLoggingRecord() {return this.loggingRecord;}
	
	
	
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
