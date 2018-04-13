  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  jq(document).ready(function() {
		jq('#updCancelButton').hide();
  });
  
  //Global functions
  function g_getCurrentYearStr(){
	  return new Date().getFullYear().toString();
  }
  function g_getCurrentMonthStr(){
	  var currentMonth = new Date().getMonth() + 1;
	  var currentMonthStr = currentMonth.toString();
	  if (currentMonth < 10) { currentMonthStr = '0' + currentMonth; }
	  return currentMonthStr;
  }
  
  jq(function() {
	  jq("#bufedt").datepicker({ 
		  onSelect: function(date) {
		  	jq("#bufedt").focus();
	      },
		  dateFormat: 'yymmdd',
		  firstDay: 1 //monday
		  /*showOn: "button",
	      buttonImage: "resources/images/calendar.gif",
	      buttonImageOnly: true,
	      buttonText: "Select date" 
		  */
		  //dateFormat: 'ddmmy', 
	  });
	  jq("#bufedt").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#bufedt").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#bufedt").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
			  }else if (length==4){
				  var userDay = str.substring(0,2);
				  var userMonth = str.substring(2,4);
				  jq("#bufedt").val(g_getCurrentYearStr() + userMonth + userDay);
			  }
		  }
	  });
	  
  });
  
  jq(function() {
	  jq('#updCancelButton').click(function() {
  		window.open('transportdisp_workflow_budget.do?avd='+ jq('#avd').val() + '&opd=' + jq('#opd').val() + "&tur=" + jq('#hepro').val(), 'budgetWin','top=120px,left=100px,height=800px,width=1600px,scrollbars=no,status=no,location=no');
	  });
  });
  
  //Links on child windows
  jq(function() {
	  //supplier child window search
	  jq('#bulnrIdLink').click(function() {
		jq('#bulnrIdLink').attr('target','_blank');  
		window.open('transportdisp_workflow_childwindow_supplier.do?action=doInit&kode=' + jq('#bulnr').val(),"supplierWin","top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  //gebyr koder child window search
	  jq('#buvkIdLink').click(function() {
		jq('#buvkIdLink').attr('target','_blank');  
		window.open('transportdisp_workflow_childwindow_gebyrcode.do?action=doInit&kode=' + jq('#buvk').val(),"gebyrCodesWin","top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  //Transport carrier id-numbers child window search
	  jq('#butnrIdLink').click(function() {
		jq('#butnrIdLink').attr('target','_blank');  
		window.open('transportdisp_workflow_childwindow_transpcarrier.do?action=doInit&soktnr=' + jq('#butnr').val(),"transpcarrierWin","top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  
  });
  
  

  jq(function() {
  	//----------------------
	//Validate supplier id
	//----------------------
	jq("#bulnr").blur(function() { 
			jq.ajax({
		  	  type: 'GET',
		  	  url: 'validateSupplier_TransportDisp.do',
		  	  data: { applicationUser : jq('#applicationUser').val(), 
		  		  	  id : jq('#bulnr').val() }, 
		  	  dataType: 'json',
		  	  cache: false,
		  	  contentType: 'application/json',
		  	  success: function(data) {
		  		var len = data.length;
		  		if(len==1){
		  			jq("#bulnr").removeClass( "isa_error" );
		  			jq("#submit").removeAttr("disabled");
					for ( var i = 0; i < len; i++) { 
						jq('#bulnr').val(data[i].levnr);
						jq('#levNavn').val(data[i].lnavn);	
					}
		  		}else{
		  			if(jq("#bulnr").val()!=''){
		  				jq('#levNavn').val("");
		  				jq("#bulnr").addClass( "isa_error" );
		  				jq("#submit").attr("disabled", true);
		  			}else{
		  				jq("#bulnr").removeClass( "isa_error" );
		  				jq("#submit").removeAttr("disabled");
		  			}
		  		}
		  		
		  	  },
		  	  error: function() {
		  	    alert('Error loading ...');
		  	    jq("#bulnr").addClass( "isa_error" );
		  	    jq("#submit").attr("disabled", true);
		  	  }
		  	}); 
	});
	
	//-------------------------
	//Validate Carrier (transp)
	//-------------------------
	/* TODO!! - Defect on SEARCH function: JOVO must implement getval=J on (CBs): http://gw.systema.no/sycgip/TJINQTNR.pgm
	jq('#butnr').blur(function() {
		jq.ajax({
	  	  type: 'GET',
	  	  url: 'searchTranspCarrier_TransportDisp.do',
	  	  data: { applicationUser : jq('#applicationUser').val(), 
	  		  	  id : jq('#butnr').val() }, 
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
	  		if(len==1){
	  			jq("#butnr").removeClass( "isa_error" );
	  			jq("#submit").removeAttr("disabled");
				for ( var i = 0; i < len; i++) { 
					jq('#traNavn').val(data[i].navn);	
				}
	  		}else{
	  			if(jq("#butnr").val()!=''){
	  				jq('#traNavn').val("");
	  				jq("#butnr").addClass( "isa_error" );
	  				jq("#submit").attr("disabled", true);
	  			}else{
	  				jq("#butnr").removeClass( "isa_error" );
	  				jq("#submit").removeAttr("disabled");
	  			}
	  		}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	    jq("#butnr").addClass( "isa_error" );
			jq("#submit").attr("disabled", true);
	  	  }
	  	}); 
		
	});
	*/
  });
  
  
  //-------------------
  //Fetch specific line
  //-------------------
  function getBudgetItemData(record) {
		var FIELD_SEPARATOR = "_";
	  	var htmlValue = record.id;
	  	var applicationUser = jq('#applicationUser').val();
	  	//alert(htmlValue);
	  	var field = htmlValue.split(FIELD_SEPARATOR);
	  	var lineId = field[1];
	  	var requestString = "user=" + jq('#applicationUser').val() + "&avd=" + jq('#avd').val() + "&opd=" + jq('#opd').val() + 
	  						"&tur=" + jq('#hepro').val() + "&bnr=" + lineId + "&type=A";
	  	//alert(requestString);
	  	//http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getBudgetDetailLine_TransportDisp.do',
	  	  data: { applicationUser : applicationUser, 
	  		  	  requestString : requestString }, 
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//alert(data[i].fask);
				jq('#isModeUpdate').val("");jq('#isModeUpdate').val("true");
				jq('#updCancelButton').show(); //in order to be able to cancel (implicit reload)
				//rekvnr
				jq('#bubnr').val("");jq('#bubnr').val(data[i].bubnr);
				//jq("#bubnr").prop('readonly', true);
				
				jq('#buvk').val("");jq('#buvk').val(data[i].buvk);
				jq('#bukdm').val("");jq('#bukdm').val(data[i].bukdm);
				jq('#bubl').val("");jq('#bubl').val(data[i].bubl);
				jq('#buval').val("");jq('#buval').val(data[i].buval);
					
				jq('#bulnr').val("");
				if(data[i].bulnr!='0'){
					jq('#bulnr').val(data[i].bulnr);
				}
				
				jq('#levNavn').val("");jq('#levNavn').val(data[i].levNavn);
				jq('#butype').val("");jq('#butype').val(data[i].butype);
				jq('#bublst').val("");jq('#bublst').val(data[i].bublst);
				jq('#busg').val("");jq('#busg').val(data[i].busg);
				
				jq('#buoavd').val("");
				if(data[i].buoavd!='0'){
					jq('#buoavd').val(data[i].buoavd);
				}
				
				jq('#buopd').val("");
				if(data[i].buopd!='0'){
					jq('#buopd').val(data[i].buopd);
				}
				
				jq('#butunr').val("");
				if(data[i].butunr!='0'){
					jq('#butunr').val(data[i].butunr);
  		  	  	}
				
				jq('#butnr').val("");
				if(data[i].butnr!='0'){
					jq('#butnr').val(data[i].butnr);
				}
				jq('#traNavn').val("");jq('#traNavn').val(data[i].traNavn);
				
				jq('#butxt').val("");jq('#butxt').val(data[i].butxt);
				jq('#bubiln').val("");jq('#bubiln').val(data[i].bubiln);
				
				jq('#bufedt').val("");
				if(data[i].bufedt!='0'){
					jq('#bufedt').val(data[i].bufedt);
				}
				jq('#bupMn').val("");jq('#bupMn').val(data[i].bupMn);
				jq('#bupAr').val("");jq('#bupAr').val(data[i].bupCc + data[i].bupAr);
				jq('#bubilk').val("");jq('#bubilk').val(data[i].bubilk);
				
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
	  	
	}

  	//---------------------------------------
	//DELETE Invoice line
	//This is done in order to present a jquery
	//Alert modal pop-up
	//----------------------------------------
	function doPermanentlyDeleteInvoiceLine(element){
	  //start
		var record = element.id.split('@');
		var avd = record[0];
		var opd = record[1];
		var tur = record[2];
		var bubnr = record[3];
		avd= avd.replace("avd_","");
		opd= opd.replace("opd_","");
		tur= tur.replace("tur_","");
		bubnr= bubnr.replace("bubnr_","");
		
	  //Start dialog
	  jq('<div></div>').dialog({
      modal: true,
      title: "Dialog - Slett Rekvnr: " + bubnr,
      buttons: {
	        Fortsett: function() {
      		jq( this ).dialog( "close" );
	            //do delete
	            jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	            window.location = "transportdisp_workflow_budget_edit.do?action=doDelete" + "&bubnr=" + bubnr + "&avd=" + avd + "&opd=" + opd + "&tur=" + tur;
	        },
	        Avbryt: function() {
	            jq( this ).dialog( "close" );
	        }
      },
      open: function() {
	  		  var markup = "Er du sikker på at du vil slette denne?";
	          jq(this).html(markup);
	          //make Cancel the default button
	          jq(this).siblings('.ui-dialog-buttonpane').find('button:eq(1)').focus();
	     }
	  });  //end dialog
	}	  
  