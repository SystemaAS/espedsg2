/**
 * 
 */
package no.systema.transportdisp.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Mar 31, 2015
 * 
 * 
 */
public final class TransportDispUrlDataStore {
	
	//----------------------------
	//[1] FETCH WORKFLOW LIST
	//----------------------------
	static public String TRANSPORT_DISP_BASE_WORKFLOW_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR01.pgm";
	//http://gw.systema.no/sycgip/TJETUR01.pgm?user=JOVO
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_AVD_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE16R.pgm";
	//http://gw.systema.no/sycgip/TJGE16R.pgm?user=OSCAR&ie=A 
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_BILNR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQBILN.pgm";
	//http://gw.systema.no/sycgip/TJINQBILN.pgm?user=JOVO&sokbnr=SC 
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_BILCODE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQBILK.pgm";	
	//http://gw.systema.no/sycgip/TJINQBILK.pgm?user=JOVO&soknvn=A
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_CARRIER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQTNR.pgm";	
	//http://gw.systema.no/sycgip/TJINQTNR.pgm?user=JOVO&soknvn=A 
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_DRIVER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQDRIV.pgm";	
	//http://gw.systema.no/sycgip/TJINQDRIV.pgm?user=JOVO&soksjn=A
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_POSTAL_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQSTED.pgm";
	//(FRA)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=FRALK&VARKOD=FRA&SOKLK=NO&WSKUNPA=A (A, P eller blank) 
	//(TIL)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=TILLK&VARKOD=TIL&SOKLK=NO& 
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_CUSTOMER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQKUND.pgm";
	//http://gw.systema.no/sycgip/TJINQKUND.pgm?user=JOVO&sokknr=1 
	//flera parametrar är: soknvn, kunpnsted, wsvarnv, maxv
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_CUSTOMER_DELIVERY_ADDRESS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQVADR.pgm";
	//http://gw.systema.no/sycgip/TJINQVADR.pgm?user=JOVO&wkundnr=7031&wvadrnr=1 
	//http://gw.systema.no/sycgip/TJINQVADR.pgm?user=JOVO&wkundnr=7031&wvadrna=A (all addresses)
	//...if not empty this will override the customer address (fetched with TJINQKUND.pgm...)
	
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_LOAD_UNLOAD_PLACES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQSDL.pgm";
	//http://gw.systema.no/sycgip/TJINQSDL.pgm?user=JOVO(return all)
	//http://gw.systema.no/sycgip/TJINQSDL.pgm?user=JOVO&soknvn=T... etc
	
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_UPLOAD_FILE_VALIDATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR07A.pgm";	
	//http://gw.systema.no/sycgip/TJETUR07A.pgm?user=OSCAR&wsdokn=tarzan.jpg
	//{ "user": "OSCAR", "wsdokn": "tarzan.jpg","valids": "Y", "tmpdir": "/pdf/tmp/", "errMsg": "", "chksuffix": [] } 
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_UPLOAD_FILE_AFTER_VALIDATION_APPROVAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR07B.pgm";	
	//http://gw.systema.no/sycgip/TJETUR07B.pgm?user=JOVO&wstur=75000002&wsdokn=/pdf/tmp/ukkulele.jpg&wsalias=trumpet.jpg 
	
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_DANGEROUS_GOODS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQUNNR.pgm";
	//Note: gir alle poster (max 100)  fra og med unnr 1950 ved &fullinfo  ulik J returneres kun unnr /emb.gruppe/ index og kort tekst (max 50 lang) 
	//[1]http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1950=&embg=&indx=&getval=&fullinfo=J
	//Note: Ved &getval=J sender en gjerne inn også embg + index (om dette er ulikt blank).. 
	//[2]http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1950=&embg=&indx=G&getval=J&fullinfo=J
	//Note: Exakt match
	//[3]http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1950=&embg=&indx=G&getval=J&fullinfo=J
	//[3.1]
	//J=Krever FULL match (båd unnr / embg / indx) :
	//http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1202=&embg=III&indx=&matchOnly=J
	//[3.2]
	//E=Krever match på unnr / embg:
	//http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1202=&embg=III&indx=&matchOnly=E
	//[3.3]
	//U=Krever kun match på  unnr :
	//http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1202=&embg=&indx=&matchOnly=U
	
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_PACKING_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQFORP.pgm";
	//http://gw.systema.no/sycgip/TJINQFORP.pgm?user=JOVO&kode=ABCD&Getval=J&fullInfo=J
	//http://gw.systema.no/sycgip/TJINQFORP.pgm?user=JOVO&kode=A (sök alla fom A)
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_TOLLSTED_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQTSTE.pgm";
	//http://gw.systema.no/sycgip/TJINQTSTE.pgm?user=JOVO&kode=0503&tekst=&Getval=J&fullInfo=J
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_GEBYR_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQGEB.pgm";
	//http://gw.systema.no/sycgip/TJINQGEB.pgm?user=JOVO&kode=&tekst=&fullinfo=J
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_SUPPLIER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQLEV.pgm";
	//http://gw.systema.no/sycgip/TJINQLEV.pgm?user=JOVO&kode=5000&tekst=&Getval=N&fullInfo=J
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_FRISOKVEI_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE30R.pgm";
	//http://gw.systema.no/sycgip/tjge30r.pgm?user=JOVO
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_FRISOKVEI_DOCCODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE32R.pgm";
	//http://gw.systema.no/sycgip/tjge32r.pgm?user=JOVO&kftyp=FSDOKK
	
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_SMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/tjop11hs.pgm";
	//http://gw.systema.no/sycgip/tjop11hs.pgm?user=JOVO&avd=75&opd=108&type=&smsnr=48052470
	
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_SMS_FROM_TUR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/tjfa55s.pgm";
	//http://gw.systema.no/sycgip/tjfa55s.pgm?user=JOVO&tur=80000060&smsnr=48052470&sprak=EN
		
	//---------------------------------------------------
	//[1] GENERAL CODES - for country (AS400 from TVINN) 
	//---------------------------------------------------
	static public String TRANSPORT_DISP_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG005R.pgm"; 
	//http://gw.systema.no/sycgip/TNOG005R.pgm?user=OSCAR&typ=2 //country list
	
	//---------------------------------------------------
	//[1.1] GENERAL FUNCTIONS eg.(signature, other...) 
	//---------------------------------------------------
	static public String TRANSPORT_DISP_GENERAL_SIGN_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE24R.pgm"; 
	//http://gw.systema.no/sycgip/TJGE24R.pgm?user=JOVO	
	
	static public String TRANSPORT_DISP_GENERAL_OPPDRAGSTYPE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQOTY.pgm";
	//http://gw.systema.no/sycgip/TJINQOTY.pgm?user=JOVO&opdtyp=&beskr=&getval=&fullinfo=J
	//Note: getval=J:perfect match, fullinfo=J:all fields are returned
	
	static public String TRANSPORT_DISP_GENERAL_FRANKATUR_INCOTERMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQFRA.pgm";
	//http://gw.systema.no/sycgip/TJINQFRA.pgm?user=JOVO&franka=&beskr=&getval=&fullinfo=J
	
	static public String TRANSPORT_DISP_GENERAL_TRACK_AND_TRACE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE002.pgm";
	//http://gw.systema.no/sycgip/TJGE002.pgm?user=JOVO&avd=75&opd=19
	
	
	
	//--------------------------------------------
	//[1.2] FETCH Specific TRIP from WORKFLOW LIST
	//--------------------------------------------
	static public String TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR02.pgm";
	//http://gw.systema.no/sycgip/TJETUR02.pgm?user=JOVO&tuavd=75&tupro=75000002 (via Ajax)
	static public String TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_ARCHIVED_DOCS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR02DO.pgm";
	//http://gw.systema.no/sycgip/TJETUR02DO.pgm?user=JOVO&wstur=75000015 (via Ajax)
	static public String TRANSPORT_DISP_BASE_ClOSE_SPECIFIC_TRIP_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR03ST.pgm";
	//Close:-->http://gw.systema.no/sycgip/TJETUR03ST.pgm?user=OSCAR&tuavd=75&tupro=75000015&tust=A 
	//Open:-->http://gw.systema.no/sycgip/TJETUR03ST.pgm?user=OSCAR&tuavd=75&tupro=75000015 
	static public String TRANSPORT_DISP_BASE_PRINT_SPECIFIC_TRIP_GODSLISTA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR06GL.pgm";
	//http://gw.systema.no/sycgip/TJETUR06GL.pgm?user=OSCAR&wstur=75000015 
	static public String TRANSPORT_DISP_BASE_PRINT_SPECIFIC_TRIP_LASTLISTA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR06LL.pgm";	
	//http://gw.systema.no/sycgip/TJETUR06LL.pgm?user=OSCAR&wstur=75000015 
	
	
	//----------------------------------------------------------
	//[2] UPDATE/CREATE/DELETE Specific TRIP from WORKFLOW LIST
	// mode=A (add)
	// mode=U (update)
	// mode=D (delete)
	//----------------------------------------------------------
	static public String TRANSPORT_DISP_BASE_UPDATE_SPECIFIC_TRIP_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR03.pgm";
	//http://gw.systema.no/sycgip/TJETUR03.pgm?user=JOVO&tuavd=1&tupro=1&mode=U + alla parametrar &TU***=xxx 
	static public String TRANSPORT_DISP_BASE_UPDATE_SPECIFIC_TRIP_MESSAGE_NOTE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR10.pgm";
	//http://gw.systema.no/sycgip/TJETUR10.pgm?user=JOVO&wsavd=75&wstur=75000015&frttxt01=Tarzan i djungelskogen&frttxt02=Jane i bastkjol&frttxt03=aiaiaiaia ! 
	//--->frttxt01 - frttxt12  alla max 70 långa 
	static public String TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_MESSAGE_NOTE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR08.pgm";
	//http://gw.systema.no/sycgip/TJETUR08.pgm?user=JOVO&wsavd=75&wstur=75000002

	//------------------------------------------------------
	//[3] FETCH LISTs of ORDERS-ON-TRIP and ORDERS-NOT-TRIP
	//------------------------------------------------------
	static public String TRANSPORT_DISP_BASE_WORKFLOW_LIST_ORDERS_ON_TRIP_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR05LT.pgm";
	//http://gw.systema.no/sycgip/TJETUR05LT.pgm?user=JOVO&wstur=75000001
	
	static public String TRANSPORT_DISP_BASE_WORKFLOW_LIST_ORDERS_NOT_ON_TRIP_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR05LL.pgm";
	//http://gw.systema.no/sycgip/TJETUR05LL.pgm?user=JOVO&wssavd=75
	//Note: Det finns fler parametrar: wssdf=Fra sted; wssdt=Til sted 
	//wssavd= ALL   IN     OUT        IMP           EXP            DOM     
	//d.v.s antingen en konkret avdelning med siffra, eller bokstævskoder
	
	static public String TRANSPORT_DISP_BASE_PRINT_SPECIFIC_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPDFBR.pgm";
	//renders PDF...
	
	
	//-----------------------------------------
	//[4] ADD or REMOVE ORDER(S) from a Trip
	//-----------------------------------------
	static public String TRANSPORT_DISP_BASE_WORKFLOW_ADD_DELETE_ORDER_FROM_TRIP_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR05AD.pgm";
	//http://gw.systema.no/sycgip/TJETUR05AD.pgm?user=OSCAR?wmode=A?wstur=75000001?wsavd=75?wsopd=1 
	//http://gw.systema.no/sycgip/TJETUR05AD.pgm?user=OSCAR?wmode=D?wstur=75000001?wsavd=75?wsopd=1 
	
	//-----------------------------------------
	//[5] UPDATE position in uppdraget
	//-----------------------------------------
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_POSITION_ON_UPPDRAGET_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR05PO.pgm";
	//ADD----->http://gw.systema.no/sycgip/TJETUR05PO.pgm?user=OSCAR?wsavd=75?wsopd=6?wspos=3 
	//REMOVE-->http://gw.systema.no/sycgip/TJETUR05PO.pgm?user=OSCAR?wsavd=75?wsopd=6?
		
	
	//----------------------------------------------------------
	//[2] FETCH/UPDATE/CREATE/DELETE Specific ORDER from ORDER LIST
	// It might or might not have a trip cross reference
	// mode=A (add)
	// mode=U (update)
	// mode=D (delete)
	//----------------------------------------------------------
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD5L.pgm";
	//http://gw.systema.no/sycgip/TJEOPD5L.pgm?user=JOVO&heavd=1&heopd=52904
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_OPPDTYPE_TIME_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD5P.pgm";
	//http://gw.systema.no/sycgip/TJEOPD5P.pgm?user=JOVO&avd=75
	
	//[2.1] Message Note management (Consignee, Carrier, Internal)
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_MESSAGE_NOTE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE11R.pgm";
	//http://gw.systema.no/sycgip/TJGE11R.pgm?user=OSCAR&avd=75&opd=11&kod=R (R=Receiver, G=Carrier, b=Blank)
	//[2.2] Fraktbrev section (order lines)
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_LIST_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE21R.pgm"; 	
	//http://gw.systema.no/sycgip/TJGE21R.pgm?user=JOVO&avd=75&opd=11&fbn=1
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_LINE_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE22R.pgm"; 	
	//http://gw.systema.no/sycgip/TJGE22R.pgm?user=JOVO&avd=75&opd=11&fbn=1&lin=1
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23R.pgm";
	//http://gw.systema.no/sycgip/TJGE23R.pgm?user=JOVO&avd=75&opd=11&fbn=1&lin=2&mode=A&fvant=11&fvvkt=15
	static public String TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23RV.pgm";
	//http://gw.systema.no/sycgip/TJGE23RV.pgm?user=JOVO&avd=75&opd=19&fmmrk1=&fvant=1&fvpakn=&fvvt=TEST&fvvkt=&fvvol=&fvlm=&fvlm2=&fvlen=&fvbrd=&fvhoy=&ffunnr=1234&ffemb=&ffantk=1&ffante=1&ffenh=KGM
	static public String TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_2_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23RV2.pgm";
	//http://gw.systema.no/sycgip/TJGE23RV2.pgm?user=JOVO&avd=75&opdtyp=OX&fmmrk1=&fvant=2&fvpakn=&fvvt=TEST&fvvkt=1000&fvlen=220&fvbrd=220&fvhoy=120&fvvol=&fvlm=&fvlm2=&ffunr=1234&ffemb=&ffantk=1&ffante=1&ffenh=KGM
	
	//[2.2.1] Uploaded documents on order...(fetch)
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_UPLOADED_DOCS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE12R.pgm";
	//http://gw.systema.no/sycgip/TJGE12R.pgm?User=JOVO&AVD=75&OPD=68
	
	//[2.3] Order header save and validation (including all lines)
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD6L.pgm";
	//http://gw.systema.no/sycgip/TJEOPD6L.pgm?user=JOVO&avd=1&opd=999999&mode=U&hest=A&heur=H&hedtop=20150513...........................................o.s.v
	static public String TRANSPORT_DISP_BASE_WORKFLOW_PERMANENTLY_DELETE_MAIN_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD6LD.pgm";
	//http://gw.systema.no/sycgip/TJEOPD6LD.pgm?user=JOVO&avd=75&opd=72
	static public String TRANSPORT_DISP_BASE_WORKFLOW_COPY_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD6LC.pgm";
	//http://gw.systema.no/sycgip/TJEOPD6LC.pgm?user=JOVO&avd=75&opd=70&newavd=75&newhesg=JOV
	static public String TRANSPORT_DISP_BASE_WORKFLOW_MOVE_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD6LM.pgm";
	//http://gw.systema.no/sycgip/TJEOPD6LM.pgm?user=JOVO&avd=75&opd=70&newavd=1
	
	//NOTE: nästan alla fält i HEADF + [WSKCONT WSKTLF WSKMAIL WSSCONT WSSTLF WSSMAIL]
	static public String TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_MAIN_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD6LV.pgm";
	//http://gw.systema.no/sycgip/TJEOPD6LV.pgm?user=JOVO&AVD=80&OPD=1384&HEOT=OX&HENT=1234567&HELM=27,55
	
	//----------------------------------------------------------------
	//[2] FETCH/UPDATE/CREATE/DELETE Specific Invoice - Invoice lines
	// It might or might not have a trip cross reference
	// mode=A (add)
	// mode=U (update)
	// mode=D (delete)
	//----------------------------------------------------------
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE25R.pgm";
	//Lista -->http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=type=A
	//Linje -->http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=55&type=A
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE26R.pgm";
	//Update -->http://gw.systema.no/sycgip/TJGE26R.pgm?user=JOVO&avd=80&opd=201523&lin=55&mode=(A)(U)(D)
	
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_STATUS_READYMARK_MAIN_ORDER_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE26RF.pgm";
	//http://gw.systema.no/sycgip/tjge26rf.pgm?user=JOVO&avd=75&opd=19&Mode=
	//Mode=G = Get  =  kun sjekk av status 
	//http://gw.systema.no/sycgip/tjge26rf.pgm?user=JOVO&avd=75&opd=19&Mode=G
	
	//----------------------------------------------------------------
	//[2] FETCH/UPDATE/CREATE/DELETE Specific Budget - Budget lines
	// It might or might not have a trip cross reference
	// mode=A (add)
	// mode=U (update)
	// mode=D (delete)
	//----------------------------------------------------------
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_BUDGET_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE27RG.pgm";
	//http://gw.systema.no/sycgip/TJGE27RG.pgm?user=JOVO&avd=75&opd=&tur=75000038&bnr=1319143&type=A
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_BUDGET_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE27RU.pgm";
	//http://gw.systema.no/sycgip/tjge27ru.pgm?user=JOVO&bnr=1318923&mode=U&bupCc=20&bupAr=15&bupMn
	
	
	//----------------------------------------------------------------
	//[3] FETCH/UPDATE/CREATE/DELETE Frie søkeveier
	// It might or might not have a trip cross reference
	// mode=A (add)
	// mode=U (update)
	// mode=D (delete)
	// mode=I (inquiry)
	//----------------------------------------------------------
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_FRISOKVEI_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE28R.pgm";
	//http://gw.systema.no/sycgip/TJGE28R.pgm?user=JOVO&avd=75&opd=155651
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_FRISOKVEI_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE29R.pgm";
	//http://gw.systema.no/sycgip/tjge29R.pgm?user=JOVO&avd=75&opd=155651&mode=A&fskode=IFB&fssok=test&fsdokk=...	
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_FRISOKVEI_VALID_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE28RG.pgm";
	//http://gw.systema.no/sycgip/tjge28RG.pgm?user=JOVO&Avd=75&opd=108&kode=AVD
	
	
	
	//----------------------------------------------------------------------------------------------------------
	// Post Update service call (in order to use some last-resource pgm)
	// Usually used when we are required to do something in an update situation BEFORE the FETCH event happens
	// The return value(s) could be anything. A string, a field description, json-fields. Then a specific implementation
	// will be required ...
	//----------------------------------------------------------------------------------------------------------
	static public String TRANSPORT_DISP_BASE_WORKFLOW_POST_UPDATE_Z = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPDZ.pgm";
	
	
}
