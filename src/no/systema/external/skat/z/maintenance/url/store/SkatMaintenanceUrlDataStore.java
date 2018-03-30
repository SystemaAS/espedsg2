/**
 * 
 */
package no.systema.external.skat.z.maintenance.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 * 
 */
public final class SkatMaintenanceUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	//DKT057R
	//ALL --> http://gw.systema.no:8080/syjservicesst/syjsDKT057R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicesst/syjsDKT057R.do?user=OSCAR&kvakod=USD
	static public String MAINTENANCE_BASE_DKT057R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT057R.do";
	//DKG210R
	//Specific code --> http://gw.systema.no:8080/syjservicesst/syjsDKG210R.do?user=OSCAR&dkkd_typ=001&dkkd_kd=01
	static public String MAINTENANCE_BASE_DKG210R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKG210R.do";
	//DKTARDR
	//Specific code --> http://gw.systema.no:8080/syjservicesst/syjsDKTARDR.do?user=OSCAR&dktard01=10...
	static public String MAINTENANCE_BASE_DKTARDR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKTARDR.do";
	//DKT055R
	//Specific code --> http://gw.systema.no:8080/syjservicesst/syjsDKT055R.do?user=OSCAR
	static public String MAINTENANCE_BASE_DKT055R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT055R.do";
	//DKT056R
	//Specific code --> http://gw.systema.no:8080/syjservicesst/syjsDKT056R.do?user=OSCAR
	static public String MAINTENANCE_BASE_DKT056R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT056R.do";

	//DKX001R
	//Specific code --> http://gw.systema.no:8080/syjservicesst/syjsDKXKODFR.do?user=OSCAR&dkkd_typ=001&dkkd_kd=01
	static public String MAINTENANCE_BASE_DKX001R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKX001R.do";
		
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//DKT057R_U
	//ALL --> http://gw.systema.no:8080/syjservicesst/syjsDKT057R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_DKT057R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT057R_U.do";
	//DKG210R
	//Specific code --> TODO http://gw.systema.no:8080/syjservicesst/syjsDKG210R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_DKG210R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKG210R_U.do";
	//DKTARDR
	//Specific code --> TODO http://gw.systema.no:8080/syjservicesst/syjsDKTARDR_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_DKTARDR_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKTARDR_U.do";
	//DKT055R
	//Specific code --> TODO http://gw.systema.no:8080/syjservicesst/syjsDKT055R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_DKT055R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT055R_U.do";
	//DKT055R
	//Specific code --> TODO http://gw.systema.no:8080/syjservicesst/syjsDKT056R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_DKT056R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT056R_U.do";
	//DKX001R
	//Specific code --> TODO http://gw.systema.no:8080/syjservicesst/syjsDKX001R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_DKX001R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKX001R_U.do";
	
	//Specific code --> http://gw.systema.no:8080/sycgip/TDKG201R.do?user=OSCAR
	static public String MAINTENANCE_BASE_DKG210R_RUN_DOWNLOAD_TOLTARIF_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKG201R.pgm";
		
}
