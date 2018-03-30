/**
 * 
 */
package no.systema.external.tds.z.maintenance.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date May 03, 2017
 * 
 * 
 */
public final class MaintenanceUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	//DKT057R
	//ALL --> http://gw.systema.no:8080/syjservicesst/syjsDKT057R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicesst/syjsDKT057R.do?user=OSCAR&kvakod=USD
	//static public String MAINTENANCE_BASE_DKT057R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT057R.do";
	//DKG210R
	//Specific code --> http://gw.systema.no:8080/syjservicesst/syjsDKG210R.do?user=OSCAR&dkkd_typ=001&dkkd_kd=01
	//static public String MAINTENANCE_BASE_DKG210R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKG210R.do";
	//DKTARDR
	//Specific code --> http://gw.systema.no:8080/syjservicesst/syjsDKTARDR.do?user=OSCAR&dktard01=10...
	//static public String MAINTENANCE_BASE_DKTARDR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKTARDR.do";
	//DKT055R
	//Specific code --> http://gw.systema.no:8080/syjservices/syjsSVT055R.do?user=OSCAR
	static public String MAINTENANCE_BASE_SVT055R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVT055R.do";
	//SVT056R
	//Specific code --> http://gw.systema.no:8080/syjservices/syjsSVT056R.do?user=OSCAR
	static public String MAINTENANCE_BASE_SVT056R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVT056R.do";
	//SVT057R
	//Specific code --> http://gw.systema.no:8080/syjservices/syjsSVT057R.do?user=OSCAR
	static public String MAINTENANCE_BASE_SVT057R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVT057R.do";
	//SVT058R
	//Specific code --> http://gw.systema.no:8080/syjservices/syjsSVT057R.do?user=OSCAR
	static public String MAINTENANCE_BASE_SVT058R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVT058R.do";
	
	//SVX001R
	//Specific code --> http://gw.systema.no:8080/syjservices/syjsSVX001R.do?user=OSCAR&tkunik=106&tkkode=SE
	static public String MAINTENANCE_BASE_SVX001R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVX001R.do";
	//DKX030R
	//ALL --> http://gw.systema.no:8080/syjservicesst/syjsDKX030R.do?user=OSCAR
	static public String MAINTENANCE_BASE_SVX030R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVX030R.do";
		
			
		
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//DKT057R_U
	//ALL --> http://gw.systema.no:8080/syjservicesst/syjsDKT057R_U.do?user=OSCAR&mode=U&<record>attributes...
	//static public String MAINTENANCE_BASE_DKT057R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT057R_U.do";
	//DKG210R
	//Specific code --> TODO http://gw.systema.no:8080/syjservicesst/syjsDKG210R_U.do?user=OSCAR&mode=U&<record>attributes...
	//static public String MAINTENANCE_BASE_DKG210R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKG210R_U.do";
	//DKTARDR
	//Specific code --> TODO http://gw.systema.no:8080/syjservicesst/syjsDKTARDR_U.do?user=OSCAR&mode=U&<record>attributes...
	//static public String MAINTENANCE_BASE_DKTARDR_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKTARDR_U.do";
	//DKT055R
	//Specific code --> TODO http://gw.systema.no:8080/syjservices/syjsDKT055R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_SVT055R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVT055R_U.do";
	//SVT056R
	//Specific code --> TODO http://gw.systema.no:8080/syjservices/syjsSVT056R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_SVT056R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVT056R_U.do";
	//SVT057R
	//Specific code --> TODO http://gw.systema.no:8080/syjservices/syjsSVT057R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_SVT057R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVT057R_U.do";
	//SVT058R
	//Specific code --> TODO http://gw.systema.no:8080/syjservices/syjsSVT058R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_SVT058R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVT058R_U.do";
	//SVX001R
	//Specific code --> TODO http://gw.systema.no:8080/syjservices/syjsSVX001R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_SVX001R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVX001R_U.do";
	//SVX030R
	//ALL --> http://gw.systema.no:8080/syjservicesst/syjsSVX030R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_SVX030R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVX030R_U.do";
									
}
