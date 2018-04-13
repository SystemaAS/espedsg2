/**
 * 
 */
package no.systema.sporringoppdrag.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Feb 4, 2015
 * 
 * 
 */
public final class SporringOppdragUrlDataStore {
	
	//----------------------------
	//[1] FETCH OPPDRAG LIST
	//----------------------------
	static public String SPORRING_OPPDRAG_BASE_TOPICLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJSYSO01.pgm";
	//http://http://gw.systema.no/sycgip/TJSYSO01.pgm?user=OSCAR&wsdtf=20141213
	
	//----------------------------
	//[2] FETCH A SPECIFIC OPPDRAG
	//----------------------------
	static public String SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJSYSO02.pgm";
	//http://gw.systema.no/sycgip/TJSYSO02.pgm?user=OSCAR&heavd=0123&heopd=0055556
	static public String SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_FREETEXT_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE11R.pgm";
	//http://gw.systema.no/sycgip/TJGE11R.pgm?user=JOVO&avd=1&opd=53208
	static public String SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_ARCHIVED_DOCUMENTS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE12R.pgm";
	//http://gw.systema.no/sycgip/TJGE12R.pgm?user=JOVO&avd=1&opd=53208
	static public String SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_INVOICES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE13R.pgm";
	//http://gw.systema.no/sycgip/TJGE11R.pgm?user=JOVO&avd=1&opd=53208
	static public String SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_FRIE_SOKE_VEIER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE14R.pgm";
	//http://gw.systema.no/sycgip/TJGE14R.pgm?user=JOVO&avd=1&opd=52904
	static public String SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_HENDELSESLOG_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE15R.pgm";
	//http://gw.systema.no/sycgip/TJGE15R.pgm?user=JOVO&avd=1&opd=53892 
	
	//-------------------------------------
	//[3] FETCH A SPECIFIC SINGLE CHILDREN
	//-------------------------------------
	//SINGLE INVOICE
	static public String SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_SINGLE_CHILD_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJESARKF3.pgm";
	//http://gw.systema.no/sycgip/TJESARKF3.pgm?user=JOVO&avd=1&opd=155449&faknr=283990 	
	//SINGLE KOLLI-ID
	static public String SPORRING_OPPDRAG_BASE_FETCH_SPECIFIC_TOPIC_SINGLE_CHILD_COLLIID_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJSYOPCL.pgm";
	//http://gw.systema.no/sycgip/TJSYOPCL.pgm?user=JOVO&avd=1&opd=52904 
}
