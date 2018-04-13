/**
 * 
 */
package no.systema.sporringoppdrag.util;

/**
 * 
 * All type of system constants for SPORRING OPPDRAG in general
 * 
 * @author oscardelatorre
 * @date Feb 4, 2015
 * 
 *
 */
public final class SporringOppdragConstants {
	
	
	//session constants
	public static final String ACTIVE_URL_RPG_SPORRING_OPPDRAG = "activeUrlRPG_SporringOppdrag";
	public static final String ACTIVE_URL_RPG_UPDATE_SPORRING_OPPDRAG = "activeUrlRPGUpdate_SporringOppdrag";
	public static final String ACTIVE_URL_RPG_FETCH_ITEM_SPORRING_OPPDRAG = "activeUrlRPGFetchItem_SporringOppdrag"; //Ajax
	public static final String ACTIVE_URL_RPG_INITVALUE = "=)";
	
	//actions
	public static final String EDIT_ACTION_ON_TOPIC = "editActionOnTopic";
	public static final String EDIT_ACTION_ON_TOPIC_ITEM = "editActionOnTopicItem";
	
	public static final String ACTION_FETCH = "doFetch";
	public static final String ACTION_UPDATE = "doUpdate";
	public static final String ACTION_CREATE = "doCreate";
	public static final String ACTION_DELETE = "doDelete";
	public static final String ACTION_SEND = "doSend";
	
	//update modes
	public static final String MODE_UPDATE = "U";
	public static final String MODE_ADD = "A";
	public static final String MODE_DELETE = "D";
	public static final String MODE_SEND = "S";
	
	
	//url
	public static final String URL_CHAR_DELIMETER_FOR_URL_WITH_HTML_REQUEST_GET = "?";
	public static final String URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST = "&"; //Used for GET and POST
	//base path for resource files (for drop-downs or other convenient files
	public static final String RESOURCE_FILES_PATH = "/WEB-INF/resources/files/";
	public static final String RESOURCE_MODEL_KEY_CURRENCY_LIST = "currencyList";
	public static final String RESOURCE_MODEL_KEY_COUNTRY_LIST = "countryList";
	public static final String RESOURCE_MODEL_KEY_LANGUAGE_LIST = "languageList";
	public static final String RESOURCE_MODEL_KEY_BERAKNINGSENHET_LIST = "berakningsEnhetList";
	
	
	public static final String RESOURCE_MODEL_KEY_HOURS_LIST = "hoursList";
	public static final String RESOURCE_MODEL_KEY_MINUTES_LIST = "minutesList";
	public static final String RESOURCE_MODEL_KEY_UOM_LIST = "uomList";
	
	//other lists
	public static final String RESOURCE_MODEL_KEY_CHILD_DOCUMENT_LIST = "docList";
	public static final String RESOURCE_MODEL_KEY_CHILD_FREETEXT_LIST = "freeTextList";
	public static final String RESOURCE_MODEL_KEY_CHILD_INVOICE_LIST = "invoiceList";
	public static final String RESOURCE_MODEL_KEY_CHILD_FRIE_SOKE_VEIER_LIST = "friesokeVeierList";
	public static final String RESOURCE_MODEL_KEY_CHILD_HENDELSESLOG_LIST = "hendelseslogList";
	
	
	
	
	//external URLs (if applicable)
	public static final String URL_EXTERNAL_LANGUAGECODES_TARIC_CODE = "isoLanguageCodesURL";
	/*public static final String URL_EXTERNAL_LANDCODES_TARIC_CODE = "taricLandCodesURL";
	public static final String URL_EXTERNAL_FRAGA_TULLID_TARIC_CODE = "taricFragaTullidURL";
	*/

	//domain objects for model-view passing values
	public static final String DOMAIN_MODEL = "model";
	public static final String DOMAIN_RECORD = "record";
	public static final String DOMAIN_CONTAINER = "container";
	
	public static final String DOMAIN_RECORD_TOPIC_SPORRING_OPPDRAG = "recordTopicSporringOppdrag";
	
	public static final String DOMAIN_LIST = "list";
	public static final String DOMAIN_RECORD_ITEM_CONTAINER_TOPIC = "recordItemContainerTopic";
	public static final String ITEM_LIST = "itemList";
	public static final String SESSION_LIST = "sessionList";
	public static final String SESSION_SEARCH_FILTER = "searchFilter";
	public static final String SESSION_CHILDWINDOW_FLAG = "cw";
	public static final String SESSION_CHILDWINDOW_FLAG_INIT = "cwInit";
	

	//aspects in view (sucha as errors, logs, other
	public static final String ASPECT_ERROR_MESSAGE = "errorMessage";
	public static final String ASPECT_ERROR_META_INFO = "errorInfo";
	

	   
}
