	//============================================================
	//General functions for TRANSPORT DISP. Child Search windows
	//============================================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	
  	jq(function() {
  		//Triggers on drag-and-drop to upload
		jq('#file').change(function(){
			jq('#uploadFileForm').submit();
			
		});
  	});
  	
  	//Customer
	jq(function() {
		jq('#customerList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var kundNr = record[0].replace("kundnr_", "");
		  var customerName = record[1].replace("navn_", "");
		  var aktkod = record[2].replace("aktkod_", "");
		  
		  //alert(kundNr + " type:" + jq('#ctype').val() + "-->customerName:" + customerName);
		  //addressing a parent field from this child window
		  if(jq('#ctype').val()=='s'){
			  //shipper/consignor 	
			  opener.jq('#hekns').val(kundNr);
			  opener.jq('#hekns').focus();
		  }else if(jq('#ctype').val()=='a'){
			  //agent  
			  opener.jq('#trknfa').val(kundNr);
			  opener.jq('#trknfa').focus();
		  }else if(jq('#ctype').val()=='c'){
			  //consignee
			  opener.jq('#heknk').val(kundNr);
			  opener.jq('#heknk').focus();
		  }else if(jq('#ctype').val()=='il'){
			  //invoice line (on invoice jsp)
			  if(aktkod == 'I' && opener.jq('#fask').val() == 'X'){
				  //Not valid. Do nothing!
			  }else{
				  opener.jq('#fakunr').val(kundNr);
			  }
		  }
		  
		  //close child window
		  window.close();
	  });
	});
	
	
  	//Select Avd
	jq(function() {
		jq('#avdList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var avdNr = record[0].replace("avdnr_", "");
		  var status = record[1].replace("status_", "");
		  
		  //alert(avdNr);
		  //addressing a parent field from this child window
		  if(opener.jq('#wssavd').length){ //only way to check if field exists.
			  //DEBUG--> alert(status);
			  if(status=='a'){ //create new
				  opener.jq('#tuavd').val(avdNr);
			  }else{
				  opener.jq('#wssavd').val(avdNr);
			  }
		  }
		  if (opener.jq('#avd').length){
			  opener.jq('#avd').val(avdNr);
		  }
		  //close child window
		  window.close();
	  });
	});
	//Select BilNr.
	jq(function() {
		jq('#bilnrList').on('click', 'td', function(){
		  var id = this.id;
		  /*
		  bilnr_${record.unbiln}
		  @heng_${record.untilh}
		  @country_${record.unland}
		  @trucktype_${record.untrme}
		  @trucknr_${record.untran}
		  @truckdriver_${record.vmnavn}
		  @unretu_${record.unretu}
		  @unretunavn_${record.unretunavn}
		  @dt_${counter.count}
		   */	
		  var record = id.split('@');
		  var bilnr = record[0].replace("bilnr_", "");
		  var henger = record[1].replace("heng_", "");
		  var countryCode = record[2].replace("country_", "");
		  var truckType = record[3].replace("trucktype_", "");
		  var truckNr = record[4].replace("trucknr_", "");
		  var truckDriver = record[5].replace("truckdriver_", "");
		  var driver1 = record[6].replace("unretu_", "");
		  var driver1Name = record[7].replace("unretunavn_", "");
		  //alert(bilnr + " " + henger + " " + countryCode);
		  //addressing a parent field from this child window
		  if(opener.jq('#tubiln').val()==''){ opener.jq('#tubiln').val(bilnr); }
		  if(opener.jq('#tulk').val()==''){ opener.jq('#tulk').val(countryCode); }
		  if(opener.jq('#tuheng').val()==''){ opener.jq('#tuheng').val(henger); }
		  if(opener.jq('#tulkh').val()==''){ opener.jq('#tulkh').val(countryCode); }
		  //child fields
		  if(opener.jq('#tubilk').val()==''){ opener.jq('#tubilk').val(truckType); }
		  if(opener.jq('#tuknt2').val()==''){
			  opener.jq('#tuknt2').val(truckNr);
			  opener.jq('#tunat').val(truckDriver);
		  }
		  //driver1
		  if(opener.jq('#tusja1').val()==''){ opener.jq('#tusja1').val(driver1); }
		  if(opener.jq('#tusjn1').val()==''){ opener.jq('#tusjn1').val(driver1Name); }
		  //close child window
		  window.close();
	  });
	});
	//Select BilCode.
	jq(function() {
		jq('#bilcodeList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var bilcode = record[0].replace("bilcode_", "");
		  //alert(bilcode);
		  //addressing a parent field from this child window
		  opener.jq('#tubilk').val(bilcode);
		  //close child window
		  window.close();
	  });
	});
	//Select Transp.Carrier
	jq(function() {
		jq('#transpCarrierList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var carriernr = record[0].replace("carriernr_", "");
		  var carrierdesc = record[1].replace("carrierdesc_", "");
		  //alert(carrier);
		  //tur parent window (when applicable)
		  opener.jq('#tuknt2').val(carriernr);
		  opener.jq('#tunat').val(carrierdesc);
		  //budget parent window (when applicable)
		  opener.jq('#butnr').val(carriernr);
		  opener.jq('#traNavn').val(carrierdesc);
		  //order parent window (when applicable)
		  opener.jq('#dftran').val(carriernr);
		  
		  //close child window
		  window.close();
	  });
	});
	//Select Driver
	jq(function() {
		jq('#driverList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var drvnr = record[0].replace("drvnr_", "");
		  var drvname = record[1].replace("drvname_", "");
		  var drvId = record[2].replace("driverId_", "");
		  //alert(drvname);
		  //addressing a parent field from this child window
		  if(drvId=='1'){
			  opener.jq('#tusja1').val(drvnr);opener.jq('#tusjn1').val(drvname);
		  }else if (drvId=='2'){
			  opener.jq('#tusja2').val(drvnr);opener.jq('#tusjn2').val(drvname);
		  }
		  //close child window
		  window.close();
	  });
	});
	
	//Select Postal Code From
	jq(function() {
		jq('#postNrFromList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var postalCode = record[0].replace("postalcode_","");
			  var countryCode = record[1].replace("country_","");
			  var city = record[2].replace("city_","");
			  var caller="init";
			  var viapnr="init";
			  if(record.length>3){
				 caller = record[3].replace("caller_","");
			  }
			  if(record.length>4){
				 viapnr = record[4].replace("viapnr_","");
			  }
			  
			  //addressing a parent field from this child window
			  if(opener.jq('#tustef').length){ //only way to check if field exists. (Trip)
				  opener.jq('#tusonf').val(countryCode);
				  opener.jq('#tustef').val(postalCode);
				  opener.jq('#tusdf').val(city);
			  }
			  if(opener.jq('#hesdf').length && caller=='hesdf'){ //only way to check if field exists. (Order)
				  opener.jq('#helka').val(countryCode);
				  opener.jq('#hesdf').val(postalCode);
				  opener.jq('#hesdf').removeClass("text11RedBold");
				  opener.jq('#OWNwppns1').val(city);
				  
			  }else if(opener.jq('#hesdff').length && caller=='hesdff'){ //since there are several postnr callers in the same JSP
				  opener.jq('#helks').val(countryCode);
				  opener.jq('#hesdff').val(postalCode);
				  opener.jq('#hesdff').removeClass("text11RedBold");
				  opener.jq('#OWNwppns3').val(city);
				  if(viapnr != 'init' &&  viapnr != ''){
					  opener.jq('#hesdff').val(viapnr); 
				  }
			  }
			  
			  //close child window
			  window.close();
	  });
	});
	
	//Select Postal Code To
	jq(function() {
		jq('#postNrToList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var postalCode = record[0].replace("postalcode_","");
			  var countryCode = record[1].replace("country_","");
			  var city = record[2].replace("city_","");
			  var caller="init";
			  var viapnr="init";
			  if(record.length>3){
				 caller = record[3].replace("caller_","");
			  }
			  if(record.length>4){
				 viapnr = record[4].replace("viapnr_","");
			  }
			  //addressing a parent field from this child window
			  if(opener.jq('#tustet').length){ //only way to check if field exists. (Trip)
				  opener.jq('#tusont').val(countryCode);
				  opener.jq('#tustet').val(postalCode);
				  opener.jq('#tusdt').val(city);
			  }
			  if(opener.jq('#hesdt').length && caller=='hesdt'){ //only way to check if field exists.(Order)
				  opener.jq('#hetri').val(countryCode);
				  opener.jq('#hesdt').val(postalCode);
				  opener.jq('#hesdt').removeClass("text11RedBold");
				  opener.jq('#OWNwppns2').val(city);
				  
			  }else if(opener.jq('#hesdvt').length && caller=='hesdvt'){ //only way to check if field exists.(Order)
				  opener.jq('#helkk').val(countryCode);
				  opener.jq('#hesdvt').val(postalCode);
				  opener.jq('#hesdvt').removeClass("text11RedBold");
				  opener.jq('#OWNwppns4').val(city);
				  if(viapnr != 'init' &&  viapnr != ''){
					  opener.jq('#hesdvt').val(viapnr); 
				  }
			  }
			  //close child window
			  window.close();
	  });
	});
	
	//Select Load/Unload place
	jq(function() {
		jq('#loadUnloadPlacesList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var loadPlaceCode = record[0].replace("code_", "");
		  var loadPlaceName = record[1].replace("loadplacename_", "");
		  var caller = record[2].replace("caller_", "");
		  if(opener.jq('#hesdl').length && caller=='hesdl'){ //only way to check if field exists.(Order)
			  opener.jq('#hesdl').val(loadPlaceName);
		  }else if(opener.jq('#hesdla').length && caller=='hesdla'){ //only way to check if field exists.(Order)
			  opener.jq('#hesdla').val(loadPlaceName);
		  }
		  //close child window
		  window.close();
	  });
	});
	
	//Select Dangerous code.
	jq(function() {
		jq('#dangerousGoodsList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  if(jq('#callerLineCounter').val()!=''){
			  var unnr = record[0].replace("unnr_", "");
			  var embg = record[1].replace("embg_", "");
			  var indx = record[2].replace("indx_", "");
			  var fakt = record[3].replace("fakt_", "");
			  var callerLineCounterStr = jq('#callerLineCounter').val();
			  var callerLineCounter = 0;
			  if(callerLineCounterStr!=""){ callerLineCounter = parseInt(callerLineCounterStr);}
			  //alert(callerLineCounter);
			  //addressing a parent field from this child window
			  opener.jq('#ffunnr_' + callerLineCounter).val(unnr);opener.jq('#ffembg_' + callerLineCounter).val(embg);
			  opener.jq('#ffindx_' + callerLineCounter).val(indx);
			  //ADR calculation
			  fakt = parseInt(fakt);
			  var units = 0;
			  if(opener.jq('#ffante_' + callerLineCounter).val()!=''){ units = parseInt(opener.jq('#ffante_' + callerLineCounter).val()); }
			  var adr = fakt * units;
			  //alert(adr);
			  if(adr>0){ opener.jq('#ffpoen_' + callerLineCounter).val(adr); }
			  
			  //cosmetics
			  opener.jq('#ffunnr_' + callerLineCounter).removeClass("isa_warning");opener.jq('#ffembg_' + callerLineCounter).removeClass("isa_warning");
			  opener.jq('#ffindx_' + callerLineCounter).removeClass("isa_warning");
			  opener.jq('#ffunnr_' + callerLineCounter).removeClass("isa_error");opener.jq('#ffembg_' + callerLineCounter).removeClass("isa_error");
			  opener.jq('#ffindx_' + callerLineCounter).removeClass("isa_error");
		  }else{
			  var unnr = record[0].replace("unnr", "");
			  var embg = record[1].replace("embg", "");
			  var indx = record[2].replace("indx", "");
			  var fakt = record[3].replace("fakt", "");
			  //addressing a parent field from this child window
			  opener.jq('#ffunnr').val(unnr);opener.jq('#ffembg').val(embg);
			  opener.jq('#ffindx').val(indx);opener.jq('#ownAdrFaktNewLine').val(fakt);
			  
			  //cosmetics
			  opener.jq('#ffunnr' + callerLineCounter).removeClass("isa_warning");opener.jq('#ffembg' + callerLineCounter).removeClass("isa_warning");
			  opener.jq('#ffindx' + callerLineCounter).removeClass("isa_warning");
			  opener.jq('#ffunnr' + callerLineCounter).removeClass("isa_error");opener.jq('#ffembg' + callerLineCounter).removeClass("isa_error");
			  opener.jq('#ffindx' + callerLineCounter).removeClass("isa_error");
		  }
		  //close child window
		  window.close();
		  
	  });
	});
	
	//Select packing codes
	jq(function() {
		jq('#packingCodesList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  if(jq('#callerLineCounter').val()!=''){
			  var fvpakn = record[0].replace("kode_", "");
			  var text = record[1].replace("text_", "");
			  var fvlen = record[2].replace("len_", "");
			  var fvbrd = record[3].replace("brd_", "");
			  var fvhoy = record[4].replace("hoy_", "");
			  var fvlm = record[5].replace("lm_", "");
			  var fvlm2 = record[6].replace("lm2_", "");
			  
			  var callerLineCounterStr = jq('#callerLineCounter').val();
			  var callerLineCounter = 0;
			  if(callerLineCounterStr!=""){ callerLineCounter = parseInt(callerLineCounterStr);}
			  //alert(callerLineCounter);
			  //addressing a parent field from this child window
			  opener.jq('#fvpakn_' + callerLineCounter).val(fvpakn);
			  if(opener.jq('#fvvt_' + callerLineCounter).val()==''){ opener.jq('#fvvt_' + callerLineCounter).val(text); }
			  if(opener.jq('#fvlen_' + callerLineCounter).val()==''){ opener.jq('#fvlen_' + callerLineCounter).val(fvlen); }
			  if(opener.jq('#fvbrd_' + callerLineCounter).val()==''){ opener.jq('#fvbrd_' + callerLineCounter).val(fvbrd); }
			  if(opener.jq('#fvhoy_' + callerLineCounter).val()==''){ opener.jq('#fvhoy_' + callerLineCounter).val(fvhoy); }
			  if(opener.jq('#fvlm_' + callerLineCounter).val()==''){ opener.jq('#fvlm_' + callerLineCounter).val(fvlm); }
			  if(opener.jq('#fvlm2_' + callerLineCounter).val()==''){ opener.jq('#fvlm2_' + callerLineCounter).val(fvlm2); }
			  //cosmetics
			  //opener.jq('#ffunnr_' + callerLineCounter).removeClass("isa_warning");opener.jq('#ffembg_' + callerLineCounter).removeClass("isa_warning");
			  //opener.jq('#ffindx_' + callerLineCounter).removeClass("isa_warning");
			  //opener.jq('#ffunnr_' + callerLineCounter).removeClass("isa_error");opener.jq('#ffembg_' + callerLineCounter).removeClass("isa_error");
			  //opener.jq('#ffindx_' + callerLineCounter).removeClass("isa_error");
		  }else{
			  var fvpakn = record[0].replace("kode", "");
			  var text = record[1].replace("text", "");
			  var fvlen = record[2].replace("len", "");
			  var fvbrd = record[3].replace("brd", "");
			  var fvhoy = record[4].replace("hoy", "");
			  var fvlm = record[5].replace("lm", "");
			  var fvlm2 = record[6].replace("lm2", "");
			  //addressing a parent field from this child window
			  opener.jq('#fvpakn').val(fvpakn);
			  if(opener.jq('#fvvt').val()==''){ opener.jq('#fvvt').val(text); }
			  if(opener.jq('#fvlen').val()==''){ opener.jq('#fvlen').val(fvlen); }
			  if(opener.jq('#fvbrd').val()==''){ opener.jq('#fvbrd').val(fvbrd); }
			  if(opener.jq('#fvhoy').val()==''){ opener.jq('#fvhoy').val(fvhoy); }
			  if(opener.jq('#fvlm').val()==''){ opener.jq('#fvlm').val(fvlm); }
			  if(opener.jq('#fvlm2').val()==''){ opener.jq('#fvlm2').val(fvlm2); }
			  //cosmetics
			  //opener.jq('#ffunnr' + callerLineCounter).removeClass("isa_warning");opener.jq('#ffembg' + callerLineCounter).removeClass("isa_warning");
			  //opener.jq('#ffindx' + callerLineCounter).removeClass("isa_warning");
			  //opener.jq('#ffunnr' + callerLineCounter).removeClass("isa_error");opener.jq('#ffembg' + callerLineCounter).removeClass("isa_error");
			  //opener.jq('#ffindx' + callerLineCounter).removeClass("isa_error");
		  }
		  //close child window
		  window.close();
		  
	  });
	});

	//Select frie sokeveier codes
	jq(function() {
		jq('#frisokveiCodesList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
	  	  var kode = record[0].replace("kode", "");
		  var text = record[1].replace("text", "");
		  //alert(callerLineCounter);
		  //addressing a parent field from this child window
		  opener.jq('#fskode').val(kode);
		  opener.jq('#fskode').focus();
		  //close child window
		  window.close();
		  
	  });
	});
	
	jq(function() {
		jq('#frisokveiCodesGiltighetsList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
	  	  var kode = record[0].replace("kode", "");
		  var text = record[1].replace("text", "");
		  //alert(callerLineCounter);
		  //addressing a parent field from this child window
		  opener.jq('#fssok').val(kode);
		  opener.jq('#fssok').focus();
		  //close child window
		  window.close();
		  
	  });
	});
	
	//Select frie sokeveier codes
	jq(function() {
		jq('#frisokveiDocCodesList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
	  	  var kode = record[0].replace("kode", "");
		  var text = record[1].replace("text", "");
		  //alert(callerLineCounter);
		  //addressing a parent field from this child window
		  opener.jq('#fsdokk').val(kode);
		  opener.jq('#fsdokk').focus();
		  //close child window
		  window.close();
		  
	  });
	});
	//Select tollsted codes
	jq(function() {
		jq('#tollstedCodesList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
	  	  var dftoll = record[0].replace("kode", "");
		  var text = record[1].replace("text", "");
		  //alert(callerLineCounter);
		  //addressing a parent field from this child window
		  opener.jq('#dftoll').val(dftoll);
		  //cosmetics
		  //opener.jq('#ffunnr_' + callerLineCounter).removeClass("isa_warning");opener.jq('#ffembg_' + callerLineCounter).removeClass("isa_warning");
		  //opener.jq('#ffindx_' + callerLineCounter).removeClass("isa_warning");
		  //opener.jq('#ffunnr_' + callerLineCounter).removeClass("isa_error");opener.jq('#ffembg_' + callerLineCounter).removeClass("isa_error");
		  //opener.jq('#ffindx_' + callerLineCounter).removeClass("isa_error");
		  
		  //close child window
		  window.close();
		  
	  });
	});
	
	
	
	//Supplier
	jq(function() {
		jq('#supplierList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var id = record[0].replace("id_", "");
		  var name = record[1].replace("navn_", "");
		  //addressing a parent field from this child window
		  //faktura parent window
		  opener.jq('#falevn').val(id);
		  opener.jq('#falevn').focus();
		  //budget parent window
		  opener.jq('#bulnr').val(id);
		  opener.jq('#levNavn').val(name);
		  opener.jq('#bulnr').focus();
		  
		  //close child window
		  window.close();
	  });
	});
	
	//Gebyrkoder
	jq(function() {
		jq('#gebyrcodeList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var id = record[0].replace("code_", "");
		  //var name = record[1].replace("navn_", "");
		  //addressing a parent field from this child window
		  opener.jq('#favk').val(id);
		  opener.jq('#favk').focus();
		  //budget window
		  opener.jq('#buvk').val(id);
		  opener.jq('#buvk').focus();
		  
		  //close child window
		  window.close();
	  });
	});
	
	//CountryCodes
	jq(function() {
		jq('#countryCodeList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var code = record[0].replace("kod", "");
		  var callerType = record[2].replace("ctype", "");
		  
		  if(callerType=='tulk'){
			  opener.jq('#tulk').val(code);
			  opener.jq('#tulk').focus();
			  
		  }else if(callerType=='tulkh'){
			  opener.jq('#tulkh').val(code);
			  opener.jq('#tulkh').focus();
			  
		  }else if(callerType=='tulkc1'){
			  opener.jq('#tulkc1').val(code);
			  opener.jq('#tulkc1').focus();
			  
		  }else if(callerType=='tulkc2'){
			  opener.jq('#tulkc2').val(code);
			  opener.jq('#tulkc2').focus();
			  
		  }else if(callerType=='tusonf'){
			  opener.jq('#tusonf').val(code);
			  opener.jq('#tusonf').focus();
			  
		  }else if(callerType=='tusont'){
			  opener.jq('#tusont').val(code);
			  opener.jq('#tusont').focus();
		  }
		  
		  //close child window
		  window.close();
	  });
	});
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterAvd () {
    		jq('#avdList').DataTable().search(
      		jq('#avdList_filter').val()
    		).draw();
    } 
    function filterBilNr () {
		jq('#bilnrList').DataTable().search(
			jq('#bilnrList_filter').val()
		).draw();
    } 
    function filterBilCode () {
		jq('#bilcodeList').DataTable().search(
			jq('#bilcodeList_filter').val()
		).draw();
    } 
    function filterTranspCarrier () {
		jq('#transpCarrierList').DataTable().search(
			jq('#transpCarrierList_filter').val()
		).draw();
    }
    function filterDriver () {
		jq('#driverList').DataTable().search(
			jq('#driverList_filter').val()
		).draw();
    }
    function filterPostNrFrom () {
        jq('#postNrFromList').DataTable().search(
    		jq('#postNrFromList_filter').val()
        ).draw();
    }
    function filterPostNrTo () {
        jq('#postNrToList').DataTable().search(
    		jq('#postNrToList_filter').val()
        ).draw();
    }
    function filterCustomerList (){
        jq('#customerList').DataTable().search(
    		jq('#customerList_filter').val()
        ).draw();
    }
    function filterLoadUnloadPlacesList (){
        jq('#loadUnloadPlacesList').DataTable().search(
    		jq('#loadUnloadPlacesList_filter').val()
        ).draw();
    }
    function filterDangerousGoodsList (){
        jq('#dangerousGoodsList').DataTable().search(
    		jq('#dangerousGoodsList_filter').val()
        ).draw();
    }
    function filterPackingCodesList (){
        jq('#packingCodesList').DataTable().search(
    		jq('#packingCodesList_filter').val()
        ).draw();
    }
    function filterFrisokveiCodesList (){
        jq('#frisokveiCodesList').DataTable().search(
    		jq('#frisokveiCodesList_filter').val()
        ).draw();
    }
    function filterFrisokveiCodesGiltighetsList (){
        jq('#frisokveiCodesGiltighetsList').DataTable().search(
    		jq('#frisokveiCodesGiltighetsList_filter').val()
        ).draw();
    }
    
    function filterFrisokveiDocCodesList (){
        jq('#frisokveiDocCodesList').DataTable().search(
    		jq('#frisokveiDocCodesList_filter').val()
        ).draw();
    }
    function filterTollstedCodesList (){
        jq('#tollstedCodesList').DataTable().search(
    		jq('#tollstedCodesList_filter').val()
        ).draw();
    }
    function filterSupplierList (){
        jq('#supplierList').DataTable().search(
    		jq('#supplierList_filter').val()
        ).draw();
    }
    function filterGebyrCodeList (){
        jq('#gebyrcodeList').DataTable().search(
    		jq('#gebyrcodeList_filter').val()
        ).draw();
    }
    
    function filterCountryCodeList (){
        jq('#countryCodeList').DataTable().search(
    		jq('#countryCodeList_filter').val()
        ).draw();
    }
    
    
    
    //Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [Avd]
  	  //-----------------------
    	  jq('#avdList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 50, 75, 100]
    	  });
      //event on input field for search
      jq('input.avdList_filter').on( 'keyup click', function () {
      		filterAvd();
      });
      //-----------------------
      //table [BilNr]
  	  //-----------------------
    	  jq('#bilnrList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 50, 75, 100]
    	  });
      //event on input field for search
      jq('input.bilnrList_filter').on( 'keyup click', function () {
      		filterBilNr();
      });
      //-----------------------
      //table [BilCode]
  	  //-----------------------
    	  jq('#bilcodeList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 50, 75, 100]
    	  });
      //event on input field for search
      jq('input.bilcodeList_filter').on( 'keyup click', function () {
      		filterBilCode();
      });
      //-----------------------
      //table [Carrier]
  	  //-----------------------
    	  jq('#transpCarrierList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 50, 75, 100]
    	  });
      //event on input field for search
      jq('input.transpCarrierList_filter').on( 'keyup click', function () {
      		filterTranspCarrier();
      });
      //-----------------------
      //table [Driver]
  	  //-----------------------
    	  jq('#driverList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 50, 75, 100]
    	  });
      //event on input field for search
      jq('input.driverList_filter').on( 'keyup click', function () {
      		filterDriver();
      });
      //--------------------------
      //table [PostNr From]
	  //--------------------------
	  jq('#postNrFromList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.postNrFromList_filter').on( 'keyup click', function () {
		  filterPostNrFrom();
	  });
	  
	  //-----------------------
	  //tables [PostNr To]
	  //-----------------------
	  jq('#postNrToList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.postNrToList_filter').on( 'keyup click', function () {
		  filterPostNrFrom();
	  });
	  
	  //-----------------------
	  //tables [Customer No.]
	  //-----------------------
	  jq('#customerList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.customerList_filter').on( 'keyup click', function () {
		  filterCustomerList();
	  });

	  //------------------------------
	  //tables [Load/Unload places]
	  //----------------------------
	  jq('#loadUnloadPlacesList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.loadUnloadPlacesList_filter').on( 'keyup click', function () {
		  filterLoadUnloadPlacesList();
	  });
	  
	  //------------------------------
	  //tables [dangerous goods]
	  //----------------------------
	  jq('#dangerousGoodsList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.dangerousGoodsList_filter').on( 'keyup click', function () {
		  filterDangerousGoodsList();
	  });
	  
	  //------------------------------
	  //tables [packing codes]
	  //----------------------------
	  jq('#packingCodesList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.packingCodesList_filter').on( 'keyup click', function () {
		  filterPackingCodesList();
	  });
	  
	  //------------------------------
	  //tables [frie søkeveier codes]
	  //----------------------------
	  jq('#frisokveiCodesList').dataTable( {
		  "dom": '<"top"f>rt<"bottom"><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.frisokveiCodesList_filter').on( 'keyup click', function () {
		  filterFrisokveiCodesList();
	  });
	  //---------------------------------
	  //tables [frie søkeveier doc.codes]
	  //---------------------------------
	  jq('#frisokveiDocCodesList').dataTable( {
		  "dom": '<"top"f>rt<"bottom"><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.frisokveiDocCodesList_filter').on( 'keyup click', function () {
		  filterFrisokveiDocCodesList();
	  });
	  
	  //--------------------------------------
	  //tables [frie søkeveier giltihetslist]
	  //--------------------------------------
	  jq('#frisokveiCodesGiltighetsList').dataTable( {
		  "dom": '<"top"f>rt<"bottom"><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.frisokveiCodesGiltighetsList_filter').on( 'keyup click', function () {
		  filterFrisokveiCodesGiltighetsList();
	  });
	  
	  //------------------------------
	  //tables [tollsted codes]
	  //----------------------------
	  jq('#tollstedCodesList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.tollstedCodesList_filter').on( 'keyup click', function () {
		  filterTollstedCodesList();
	  });
	  
	  //-----------------------
	  //tables [Supplier No.]
	  //-----------------------
	  jq('#supplierList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.supplierList_filter').on( 'keyup click', function () {
		  filterSupplierList();
	  });
	  
	  //-----------------------
	  //tables [Gebyr codes]
	  //-----------------------
	  jq('#gebyrcodeList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.gebyrcodeList_filter').on( 'keyup click', function () {
		  filterGebyrCodeList();
	  });
      
	//-----------------------
	  //tables [Country codes]
	  //-----------------------
	  jq('#countryCodeList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.countryCodeList_filter').on( 'keyup click', function () {
		  filterCountryCodeList();
	  });
    });
  	