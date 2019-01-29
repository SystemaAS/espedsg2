  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  //BlockUI behaviour
  function setBlockUI(element){
	  jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  
  jq(function() {
	  jq('#updCancelButton').click(function() {
		  window.location = 'transportdisp_workflow.do?action=doFind&wssst=' + jq('#wssst').val() + '&wssavd=' + jq('#tuavd').val() ;
	  });
	  jq("#printImg").click(function() {
		  presentPrintDialog();
	  });
	  jq("#imgGodslistePdf").click(function() {
		  renderGodsListaWithTuproJS();
	  });
	  jq("#alinkGodslistePdf").click(function() {
		  renderGodsListaWithTuproJS();
	  });
	  jq("#imgLastlistePdf").click(function() {
		  renderLastListaWithTuproJS();
	  });
	  jq("#alinkLastlistePdf").click(function() {
		  renderLastListaWithTuproJS();
	  });
	  //ferjeoverfarter
	  jq('#imgUpdateFerjeoverfarter').click(function() {
		if(jq('#tuproJS').text()!=''){
			window.open('transportdisp_workflow_ferjeoverfarter.do?feavd='+ jq('#tuavd').val() + '&fetur=' + jq('#tuproJS').text(), 'ferjeoverfarterWin','top=120px,left=100px,height=900px,width=1250px,scrollbars=no,status=no,location=no');
		}
	  });
	});
  
  	function renderGodsListaWithTuproJS(){
  		if(jq('#tuproJS').text()!=''){
  			window.open('transportdisp_workflow_renderGodsOrLastlist.do?user=' + jq('#applicationUser').val() + '&tupro=' + jq('#tuproJS').text() + '&type=G', '_blank');
  		}
  	}
  	function renderLastListaWithTuproJS(){
  		if(jq('#tuproJS').text()!=''){
  			window.open('transportdisp_workflow_renderGodsOrLastlist.do?user=' + jq('#applicationUser').val() + '&tupro=' + jq('#tuproJS').text() + '&type=L', '_blank');
  		} 
  	}
  	
  
  //FORM SUBMIT
  jq(function() {
	  jq('#submit').click(function(e) { 
		  if(validForm()){
			  jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
			  jq('#transportdispForm').attr('onsubmit','return true;');
			  jq("#transportdispForm").submit();
		  }else{
			  jq('#transportdispForm').attr('onsubmit','return false;');
			  e.preventDefault();
		  }
		  
	  }); 
  });
  function validForm(){
	  var retval = true;
	  if(jq("#tunat").val() == "?"){
		  retval = false;
	  }
	  return retval;
  }
  //END FORM SUBMIT
  
  
  jq(function() {
	  jq('#alinkOrderListId').click(function() { 
		  jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  });
  //End BlockUI
  
  jq(function() {
	  jq('#tuopdtIdLink').click(function() {
	  	jq('#tuopdtIdLink').attr('target','_blank');
	  	window.open('transportdisp_workflow_childwindow_opptype.do?action=doInit&ctype=tur', "opptypeWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#tuopdtIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tuopdtIdLink').click();
			}
	  });
	  
	  jq('#tutrmaIdLink').click(function() {
		  	jq('#tutrmaIdLink').attr('target','_blank');
		  	window.open('transportdisp_workflow_childwindow_transporttypes.do?action=doInit', "transtypesWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#tutrmaIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tutrmaIdLink').click();
			}
	  });
  });
  //-----------------------------------
  //START - Drag from Trips to Order 
  //----------------------------------
  //this drag function is used when the order is the SOURCE of a drag and not the target
  function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
  }
  //END Drag
  
  
  //------------------------------------
  //START -  Drop from Orders to trip
  //----------------------------------  
  //this drag function is used when the order is the TARGET of a drag and not the source
	function highlightDropArea(ev) {
		var data = ev.dataTransfer.getData("text");
		jq("#"+ev.target.id).addClass('isa_blue');
	}
	//this drag function is used when the order is the TARGET of a drag and not the source
  function noHighlightDropArea(ev) {
  	//jq("#"+ev.target.id).css("color", "red");
  	jq("#"+ev.target.id).removeClass('isa_blue');
	}
  //this drag function is used when the order is the TARGET of a drag and not the source
  function allowDrop(ev) {
  	ev.preventDefault();
	}
  //this drag function is used when the order is the TARGET of a drag and not the source
	function drop(ev) {
	    ev.preventDefault();
	    jq("#"+ev.target.id).removeClass('isa_blue');
	    
	    var data = ev.dataTransfer.getData("text");
	    //DEBUG alert(data);
	    
	    var record = data.split("@");
	    var opd = record[1].replace("opd_","");
	    var existentTripNr = record[2].replace("tripnr_","");
	    
	    //meaning the trip is valid (valid opd and no trip previously attached)
	    if(opd.indexOf("_") == -1 && existentTripNr == ""){ 
		    //DEBUG alert("event.target.id:" + event.target.id);
		    //get target ids
		    var recordTarget;var avd; var trip;
		    if( (event.target.id.indexOf("onlist") > -1) || (event.target.id.indexOf("onlistA") > -1) ){ //meaning droping in the trip on list
		    	recordTarget = event.target.id.split("_");
		    	avd = recordTarget[0].replace("dtuavd","");
		    	trip = recordTarget[1].replace("dtupro","");
		    }else{ //dropping in the form area
		    	avd = jq("#tuavd").val();
		    	trip = jq("#tupro").val();
		    }
		    //DEBUG alert(trip + "XX" + avd + "XX" + opd);
		    if(trip!='' && avd!='' && opd!=''){
		    	setTripOnOrder(trip, avd, opd);
		    }else{
		    	alert("Ordre/tur/avd mangler?");
		    }
	    }else{
	    	alert("Ordre har tur:" + existentTripNr + " allerede");
	    }
	    
	    /*
	    var record = data.split("@");
	    var opd = record[1].replace("opd_","");
	    var existentTripNr = record[2].replace("tripnr_","");
	    
	    //meaning the trip is valid (valid opd and no trip previously attached)
	    if(opd.indexOf("_") == -1 && existentTripNr == ""){ 
		    //DEBUG alert(event.target.id);
		    //get target ids
		    var recordTarget;var avd; var trip;
		    if( (event.target.id.indexOf("onlist") > -1) || (event.target.id.indexOf("onlistA") > -1) ){ //meaning droping in the trip on list
		    	recordTarget = event.target.id.split("_");
		    	avd = recordTarget[0].replace("dtuavd","");
		    	trip = recordTarget[1].replace("dtupro","");
		    }else{ //dropping in the form area
		    	avd = jq("#tuavd").val();
		    	trip = jq("#tupro").val();
		    }
		    //DEBUG alert(trip + "XX" + avd + "XX" + opd);
		    if(trip!='' && avd!='' && opd!=''){
		    	setTripOnOrder(trip, avd, opd);
		    }else{
		    	alert("Ordre/tur/avd mangler?");
		    }
	    }else{
	    	alert("Ordre har tur:" + existentTripNr + " allerede");
	    }
	    //N/A
	    //ev.target.appendChild(document.getElementById(data));
	    */
	}
	
	//Connect trip with order
	//if = OK then go to trip planning (GUI)
	function setTripOnOrder(trip, avd, opd){
		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
		var requestString = "&wmode=A&wstur=" + trip + "&wsavd=" + avd + "&wsopd=" + opd;
		jq.ajax({
		  	  type: 'GET',
		  	  url: 'addTripToOrder_TransportDisp.do',
		  	  data: { applicationUser : jq('#applicationUser').val(),
					  requestString : requestString },
		  	  dataType: 'json',
		  	  cache: false,
		  	  contentType: 'application/json',
		  	  success: function(data) {
		  		var len = data.length;
		  		if(len==1){
			  		//update = OK
		  			//Bring request NOT to reload after drop
		  			//reloadParentTrip(trip,avd,opd);
		  			alert("Ordre " + opd + " har blitt lagt");
		  		}else{
		  			//update != OK
		  			alert("Error on order update [addTripToOrder_TransportDisp.do]...?");
		  		}
		  	  },
		  	  error: function() {
		  		  alert('Error loading ...');
			  }
		});
		jq.unblockUI();
	}
	//---------------------------------
	//END - Drop from Orders to Trip
	//---------------------------------
	
	//Reload the order after being coupled with the trip 
    //NOTE: this function is call from: 
    //(1) from this same file in the above ajax: setTripOnOrder(trip,avd,opd)
    function reloadParentTrip(trip, avd, opd) {
    	window.location = "transportdisp_mainorderlist.do?action=doFind&wssavd=" + avd + "&wstur=" + trip;
    	
    	//in case we want to refresh the order list (unlikely)
    	//window.location = "transportdisp_mainorder.do?hepro=" + trip + "&heavd=" + avd + "&heopd=" + opd;
    	
    }
  
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
	  	jq('#budgetButton').click(function() {
	  		window.open('transportdisp_workflow_budget.do?avd='+ jq('#tuavd').val() + '&opd=' + "&tur=" + jq('#tupro').val(), 'budgetWin','top=120px,left=100px,height=800px,width=1600px,scrollbars=no,status=no,location=no');
	  	});
  });
  //---------------------
  // UPLOAD FILE - TRIP
  //---------------------
	  function uploadFile(element) {
		  //alert(element.id);
		  var id = element.id;
		  var counter = id.replace("file_","");
		  triggerAjax(counter);
		  
	  }
	  function triggerAjax(counter){
		  	  //disable the default form submission
			  //event.preventDefault();
			  //grab all form data
		  	  jq("#file_" + counter).removeClass( "isa_blue" );
			  var form = new FormData(document.getElementById('uploadFileForm_' + counter));
			  jq.ajax({
			  	  type: 'POST',
			  	  url: 'uploadFileFromTrip.do',
			  	  data: form,
			  	  dataType: 'text',
			  	  cache: false,
			  	  processData: false,
			  	  contentType: false,
		  		  success: function(data) {
				  	  var len = data.length;
			  		  if(len>0){
			  			jq("#fileNameNew_" + counter).val("");
					  	jq("#file_" + counter).val("");
					  	//Check for errors or successfully processed
					  	var exists = data.indexOf("ERROR");
					  	if(exists>0){
					  		//ERROR on back-end
					  		jq("#file_" + counter).addClass( "isa_error" );
					  		jq("#file_" + counter).removeClass( "isa_success" );
					  		//response to end user 
						  	alert(data);
					  	}else{
					  		//OK
					  		jq("#file_" + counter).addClass( "isa_success" );
					  		jq("#file_" + counter).removeClass( "isa_error" );
					  		//emulate an update in order to refresh the specific "tur" (which is only fetch via ajax. The only POST is when "Saved")
					  		//only if the record has been previously selected for update.
					  		if(jq("#tuproJS").text()!=''){
					  			console.log("submit...");
					  			jq("#submit").click();
					  		}
					  	}
					  	
			  		  }
			  	  }, 
			  	  error: function() {
			  		  alert('Error loading ...');
			  		  jq("#fileNameNew_" + counter).val("");
			  		  jq("#file_" + counter).val("");
			  		  //cosmetics
			  		  jq("#file_" + counter).addClass( "isa_error" );
			  		  jq("#file_" + counter).removeClass( "isa_success" );
				  }
			  });
	  }
	  
	  //drag enter/leave
	  function myFileUploadDragEnter(event, element){
		  var id = element.id;
		  var counter = id.replace("file_","");
		  jq("#file_" + counter).addClass( "isa_blue" );
	  }
	  function myFileUploadDragLeave(event, element){
		  var id = element.id;
		  var counter = id.replace("file_","");
		  jq("#file_" + counter).removeClass( "isa_blue" );
	  }
	  
	  
	  /*
	  jq(function() {
		  //Triggers drag-and-drop
		  jq('#file').hover(function(){
			  jq("#file").removeClass( "isa_success" );
			  jq("#file").removeClass( "isa_error" );
		  });   
	  });
	  */
  //END UPLOAD FILE
  
  
  jq(function() {
	  jq("#tudt").focus(function(){
		  jq(jq("#tudt")).css({ "font-style": "normal", "color": "#000080"});  
	  });
	  jq("#tutm").focus(function(){
		  jq(jq("#tutm")).css({ "font-style": "normal", "color": "#000080"});  
	  });
	  jq("#tudtt").focus(function(){
		  jq(jq("#tudtt")).css({ "font-style": "normal", "color": "#000080"});  
	  });
	  jq("#tutmt").focus(function(){
		  jq(jq("#tutmt")).css({ "font-style": "normal", "color": "#000080"});  
	  });
	  
	  //ALL GREY FIELDS (READ-ONLY) must block the std. behavior of the input field (lightyellow) when focus
	  jq('#tusg').focus(function(){ jq(jq("#tusg")).css({ "background-color": "lightgrey"}); });
	  jq('#tunat').focus(function(){ jq(jq("#tunat")).css({ "background-color": "lightgrey"}); });
	  jq('#tusdf').focus(function(){ jq(jq("#tusdf")).css({ "background-color": "lightgrey"}); });
	  jq('#tusdt').focus(function(){ jq(jq("#tusdt")).css({ "background-color": "lightgrey"}); });
	  
  });
  
  jq(function() {
	  jq("#tudt").datepicker({ 
		  dateFormat: 'yymmdd',
		  firstDay: 1 //monday
		  /*showOn: "button",
	      buttonImage: "resources/images/calendar.gif",
	      buttonImageOnly: true,
	      buttonText: "Select date"	  
		  */
		  //dateFormat: 'ddmmy', 
	  });
	  jq("#wtudt").datepicker({ 
		  dateFormat: 'yymmdd',
		  firstDay: 1 //monday
	  });
	  jq("#wtudt2").datepicker({ 
		  dateFormat: 'yymmdd',
		  firstDay: 1 //monday
	  });
	  jq("#tudtt").datepicker({ 
		  dateFormat: 'yymmdd',
		  firstDay: 1 //monday
	  });
	  jq("#wtudtt").datepicker({ 
		  dateFormat: 'yymmdd',
		  firstDay: 1 //monday
	  });
	  jq("#wtudtt2").datepicker({ 
		  dateFormat: 'yymmdd',
		  firstDay: 1 //monday
	  });
  });

  jq(function() {
 	  jq("#wssavd").blur(function(){ 
 		  var avd = jq("#wssavd").val();
 		  if(avd!=""){
 			 jq("#tuavd").val(avd);
 		  }
	  });
 	 
 	  jq('#objAvdGroupsList').click(function() {
		  if(jq('#divAvdGroupsList').css('display') == 'none'){
			  jq('#divAvdGroupsList').css('display','block');
		  }else{
			  jq('#divAvdGroupsList').css('display','none');
		  }
	  });
  });
  function doPickAvdGroup(element){
	  jq('#divAvdGroupsList').css('display','none');
	  var rawId = element.id;
	  var id = rawId.replace("id_","");
	  jq('#wssavd').val(id);
  }

  //Validation on Driver
  jq(function() {
 	  jq("#tusjn1").focus(function(){ 
 		  var id = jq("#tusja1").val();
 		  if(id!=''){
 			 jq("#tusjn1").attr('readonly', true);
 		  }else{
 			 jq("#tusjn1").attr('readonly', false);
 		  }
	  });
 	  jq("#tusjn2").focus(function(){ 
		  var id = jq("#tusja2").val();
		  if(id!=''){
			 jq("#tusjn2").attr('readonly', true);
		  }else{
			 jq("#tusjn2").attr('readonly', false);
		  }
	  });
  });
  
  
  
  var DRIVER1 = "1";
  var DRIVER2 = "2";
  //Driver 1 (get name)
  jq(function() {
    jq('#tusja1').blur(function() {
    		var id = jq('#tusja1').val();
    		if(id!=null && id!=""){
    			getDriversName(DRIVER1, id);
    		}
	});
  });
  jq(function() {
    jq('#tusja2').blur(function() {
    		var id = jq('#tusja2').val();
    		if(id!=null && id!=""){
    			getDriversName(DRIVER2, id);
    		}
	});
  });
  function getDriversName(target, id){
	  jq.getJSON('searchDriver_TransportDisp.do', {
		  applicationUser : jq('#applicationUser').val(),
		driverId : id,
		ajax : 'true'
	  }, function(data) {
		//alert("Hello");
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			if(target==DRIVER1){
				jq('#tusjn1').val(data[i].drnavn);
			}else if(target==DRIVER2){
				jq('#tusjn2').val(data[i].drnavn);
			}
			
		}
	});
  }
  
  jq(function() {
    jq('#tuknt2').blur(function() {
    		var id = jq('#tuknt2').val();
    		if(id!=null && id!=""){
    			jq.getJSON('searchTranspCarrier_TransportDisp.do', {
    				applicationUser : jq('#applicationUser').val(),
    				id : id,
    				ajax : 'true'
    			}, function(data) {
    				//alert("Hello");
    				var len = data.length;
    				if (len > 0){
	    				for ( var i = 0; i < len; i++) {
	    					jq('#tunat').val(data[i].navn);
	    					jq("#tunat").removeClass( "isa_error" );
	    				}
    				}else{
    					jq('#tunat').val("?");
    					jq("#tunat").addClass( "isa_error" );
    				}
    			});
    		}
	});
  });

  
  //POSTAL CODES/CITY
  var CITY1 = "1";
  var CITY2 = "2";
  //Postnr
  jq(function() {
	    jq('#tustef').blur(function() {
	    		var id = jq('#tustef').val();
	    		if(id!=null && id!=""){
	    			var countryCode = jq('#tusonf').val();
	    			if(countryCode!=''){
	    				getCity(CITY1,id,countryCode);
	    			}
	    		}
		});
	    jq('#tustefIdLink').click(function() {
	    	jq('#tustefIdLink').attr('target','_blank');
	    	window.open('transportdisp_workflow_childwindow_postalcodes.do?action=doInit&direction=fra&st2lk='+ jq('#tusonf').val() + '&st2kod=' + jq('#tustef').val()  , "postalcodeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tustefIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tustefIdLink').click();
			}
	    });	
  });
  jq(function() {
	    jq('#tustet').blur(function() {
	    		var id = jq('#tustet').val();
	    		if(id!=null && id!=""){
	    			var countryCode = jq('#tusont').val();
	    			if(countryCode!=''){
	    				getCity(CITY2,id,countryCode);
	    			}
	    		}
		});
	    jq('#tustetIdLink').click(function() {
	    	jq('#tustetIdLink').attr('target','_blank');
	    	window.open('transportdisp_workflow_childwindow_postalcodes.do?action=doInit&direction=til&st2lk='+ jq('#tusont').val() + '&st2kod=' + jq('#tustet').val()  , "postalcodeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tustetIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tustetIdLink').click();
			}
	    });
	    
  });

  function getCity(target, id, countryCode){
	  jq.getJSON('searchPostNumber_TransportDisp.do', {
		  applicationUser : jq('#applicationUser').val(),
		  id : id,
		  countryCode : countryCode,
		  ajax : 'true'
	  }, function(data) {
		//alert("Hello");
		var len = data.length;
		if(len>0){
			for ( var i = 0; i < len; i++) {
				if(target==CITY1){
					jq('#tusdf').val(data[i].st2nvn);
				}else if(target==CITY2){
					jq('#tusdt').val(data[i].st2nvn);
				}
				
			}
		}else{
			if(target==CITY1){
				jq('#tusdf').val("");
			}else if(target==CITY2){
				jq('#tusdt').val("");
			}
		}
	});
  }
  
  //----------------------------------------------------
  //Bilnr (cascade all children fields, if applicable)
  //Priskalkulation inkluderas här
  //----------------------------------------------------
  jq(function() {
	    jq('#tubiln').blur(function() {
	    	setBilnrAndPriskalk();
	    });
	    jq('#tusonf').change(function() {
	    	setPriskalk();
	    });
	    jq('#tusont').change(function() {
	    	setPriskalk();
	    });
  });
  function setBilnrAndPriskalk(){
	  	var id = jq('#tubiln').val();
		if(id!=null && id!=""){
			jq.getJSON('searchBilnr_TransportDisp.do', {
				applicationUser : jq('#applicationUser').val(),
				id : id,
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#tubiln').val(data[i].unbiln); //always fill-in in case there is a dummy record too ask for a narrower search
					
					if(jq('#tulk').val()==''){jq('#tulk').val(data[i].unland);}
					if(jq('#tuheng').val()==''){jq('#tuheng').val(data[i].untilh);}
					if(jq('#tulkh').val()=='' && jq('#tuheng').val()!=''){
						jq('#tulkh').val(data[i].unland);//json returns only this country code (tulk)
					} 
					if(jq('#tubilk').val()==''){jq('#tubilk').val(data[i].untrme);}
					if(jq('#tuknt2').val()==''){
						jq('#tuknt2').val(data[i].untran);
						jq('#tunat').val(data[i].vmnavn);
				}
					if(jq('#tusja1').val()==''){jq('#tusja1').val(data[i].unretu);}
					if(jq('#tusjn1').val()==''){jq('#tusjn1').val(data[i].unretunavn);}
					//Lorry capacity matrix: SET LABELS
					jq('#tukvkt').text(data[i].unvekt);
					jq('#tutara').text(data[i].untara);
					jq('#tukam3').text(data[i].unm3);
					jq('#tukalM').text(data[i].unlm);
					if(data[i].unvekt!='' && data[i].untara!=''){
						var totalWeight = Number(data[i].unvekt) + Number(data[i].untara);
						jq('#wstov1').text(totalWeight);
					}
					//transp.måte
					if(jq('#tutrma').val()==''){
						jq('#tutrma').val(data[i].untrma);
					}
					if(jq('#tutbel').val()==''){
						//INNLAND priser
						if( (jq('#filand').val() == jq('#tusonf').val()) && (jq('#filand').val() == jq('#tusont').val()) ){
							jq('#tutbel').val(data[i].unkmpi);
							jq('#tutval').val(data[i].unvali);
						//EXPORT priser	
						}else if( (jq('#filand').val() == jq('#tusonf').val()) && (jq('#filand').val() != jq('#tusont').val()) ){
							jq('#tutbel').val(data[i].unkmpe);
							jq('#tutval').val(data[i].unvale);
						//IMPORT priser	
						}else if( (jq('#filand').val() != jq('#tusonf').val()) && (jq('#filand').val() == jq('#tusont').val()) ){
							jq('#tutbel').val(data[i].unkmp);
							jq('#tutval').val(data[i].unval);
						}
					}
					//Lorry capacity matrix: SET HIDDEN FIELDS
					/*jq('#own_tukvkt').val(data[i].unvekt);
					jq('#own_tutara').val(data[i].untara);
					jq('#own_tukam3').val(data[i].unm3);
					jq('#own_tukalM').val(data[i].unlm);
					*/
				}
			});
		}
	  
  }
  function setPriskalk(){
	  	var id = jq('#tubiln').val();
		if(id!=null && id!=""){
			jq.getJSON('searchBilnr_TransportDisp.do', {
				applicationUser : jq('#applicationUser').val(),
				id : id,
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {

					//if(jq('#tutbel').val()==''){
						//INNLAND priser
						if( (jq('#filand').val() == jq('#tusonf').val()) && (jq('#filand').val() == jq('#tusont').val()) ){
							jq('#tutbel').val(data[i].unkmpi);
							jq('#tutval').val(data[i].unvali);
						//EXPORT priser	
						}else if( (jq('#filand').val() == jq('#tusonf').val()) && (jq('#filand').val() != jq('#tusont').val()) ){
							jq('#tutbel').val(data[i].unkmpe);
							jq('#tutval').val(data[i].unvale);
						//IMPORT priser	
						}else if( (jq('#filand').val() != jq('#tusonf').val()) && (jq('#filand').val() == jq('#tusont').val()) ){
							jq('#tutbel').val(data[i].unkmp);
							jq('#tutval').val(data[i].unval);
						}
					//}
					
				}
			});
		}
	  
}
  
  
  
	    
  jq(function() {
    
    jq('#tubilnIdLink').click(function() {
    	jq('#tubilnIdLink').attr('target','_blank');
    	window.open('transportdisp_workflow_childwindow_bilnr.do?action=doInit&unbiln=' + jq('#tubiln').val(), "bilnrWin", "top=300px,left=350px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    //tulk
  	jq('#tulkIdLink').click(function() {
    	jq('#tulkIdLink').attr('target','_blank');
    	window.open('transportdisp_workflow_childwindow_country.do?action=doInit&code=' + jq('#tulk').val() + "&ctype=tulk", "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#tulkIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#tulkIdLink').click();
		}
    });
    //tulkh
    jq('#tulkhIdLink').click(function() {
    	jq('#tulkhIdLink').attr('target','_blank');
    	window.open('transportdisp_workflow_childwindow_country.do?action=doInit&code=' + jq('#tulkh').val() + "&ctype=tulkh", "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#tulkhIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#tulkhIdLink').click();
		}
    });

    //tulkc1
  	jq('#tulkc1IdLink').click(function() {
    	jq('#tulkc1IdLink').attr('target','_blank');
    	window.open('transportdisp_workflow_childwindow_country.do?action=doInit&code=' + jq('#tulkc1').val() + "&ctype=tulkc1", "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#tulkc1IdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#tulkc1IdLink').click();
		}
    });
    
    //tulkc2
  	jq('#tulkc2IdLink').click(function() {
    	jq('#tulkc2IdLink').attr('target','_blank');
    	window.open('transportdisp_workflow_childwindow_country.do?action=doInit&code=' + jq('#tulkc2').val() + "&ctype=tulkc2", "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#tulkc2IdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#tulkc2IdLink').click();
		}
    });
    //tulkc3
  	jq('#tulkc3IdLink').click(function() {
    	jq('#tulkc3IdLink').attr('target','_blank');
    	window.open('transportdisp_workflow_childwindow_country.do?action=doInit&code=' + jq('#tulkc3').val() + "&ctype=tulkc3", "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#tulkc3IdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#tulkc3IdLink').click();
		}
    });
    //tusonf
  	jq('#tusonfIdLink').click(function() {
    	jq('#tusonfIdLink').attr('target','_blank');
    	window.open('transportdisp_workflow_childwindow_country.do?action=doInit&code=' + jq('#tusonf').val() + "&ctype=tusonf", "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#tusonfIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#tusonfIdLink').click();
		}
    });
  //tusont
  	jq('#tusontIdLink').click(function() {
    	jq('#tusontIdLink').attr('target','_blank');
    	window.open('transportdisp_workflow_childwindow_country.do?action=doInit&code=' + jq('#tusont').val() + "&ctype=tusont", "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#tusontIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#tusontIdLink').click();
		}
    });
  });
  
  //----------------------------
  //On-Change & Keypress events
  //----------------------------
  function setFocusTo_tuheng(){
	 jq("#tuheng").focus();
  }
  jq(function() { 
	jq("#tulkImg").keypress(function(e) {
		if ( event.which == 13 ) {
			jq("#tulkImg").click();	
		}
	});
	jq('#truckLicCountry01').change(function() {
    		jq('#tulk').val(jq('#truckLicCountry01').val());	
    	});
	
    jq('#truckLicCountry01').keypress(function(e){
		if(e.which == 13) {
			e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			jq( "#truckLicCountry01ButtonClose" ).click();	
		}			
	});
    
    jq('#containerCountry01').change(function() {
		jq('#tulkc1').val(jq('#containerCountry01').val());	
	});
	jq('#containerCountry01').keypress(function(e){
		if(e.which == 13) {
			e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			jq( "#containerCountry01ButtonClose" ).click();	
		}			
	});
	//--------------------
	//Oppdragstype window
	//--------------------
	jq('#oppdragType').change(function() {
		jq('#tuopdt').val(jq('#oppdragType').val());	
	});
	jq('#oppdragType').keypress(function(e){
		if(e.which == 13) {
			e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			jq( "#oppdragTypeButtonClose" ).click();	
		}			
	});
	
  });

  //Theses 3 functions mimic the event as if it was a close-popup. 
  function setFocusTo_tubilk(){
	  jq("#tubilk").focus();
  }
  function setFocusTo_tucon2(){
	  jq("#tucon2").focus();
  }
  function setFocusTo_tuknt2(){
	  jq("#tuknt2").focus();
  }
  jq(function() {
	jq("#tulkhImg").keypress(function(e) {
		if ( event.which == 13 ) {
			jq("#tulkhImg").click();	
		}
	});  
    jq('#truckLicCountry02').change(function() {
    		jq('#tulkh').val(jq('#truckLicCountry02').val());	
    });
    jq('#truckLicCountry02').keypress(function(e){
		if(e.which == 13) {
			e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			jq( "#truckLicCountry02ButtonClose" ).click();	
		}			
	});

    //Container 1
    jq("#tulkc1Img").keypress(function(e) {
    		if ( event.which == 13 ) {
			jq("#tulkc1Img").click();	
		}
	});
    jq('#containerCountry01').change(function() {
		jq('#tulkc1').val(jq('#containerCountry01').val());	
	});
	jq('#containerCountry01').keypress(function(e){
		if(e.which == 13) {
			e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			jq( "#containerCountry01ButtonClose" ).click();	
		}			
	});
	
	//Container 2
	jq("#tulkc2Img").keypress(function(e) {
		if ( event.which == 13 ) {
			jq("#tulkc2Img").click();	
		}
	});
    jq('#containerCountry02').change(function() {
		jq('#tulkc2').val(jq('#containerCountry02').val());	
	});
	jq('#containerCountry02').keypress(function(e){
		if(e.which == 13) {
			e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			jq( "#containerCountry02ButtonClose" ).click();	
		}			
	});
	
    
  });
  
  //Dates
  function setFocusTo_tustef(){
	jq("#tustef").focus();
  }
  jq(function() {
	jq("#tusonfImg").keypress(function(e) {
		if ( event.which == 13 ) {
			jq("#tusonfImg").click();	
		}
	});  
    jq('#etdCountry').change(function() {
    		jq('#tusonf').val(jq('#etdCountry').val());	
    });
    jq('#etdCountry').keypress(function(e){
		if(e.which == 13) {
			e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			jq( "#etdCountryButtonClose" ).click();	
		}			
	});
  });
  
  
  function setFocusTo_tustet(){
	jq("#tustet").focus();
  }
  jq(function() {
	jq("#tusontImg").keypress(function(e) {
		if ( event.which == 13 ) {
			jq("#tusontImg").click();	
		}
	});  	
    jq('#etaCountry').change(function() {
    		jq('#tusont').val(jq('#etaCountry').val());	
    });
    jq('#etaCountry').keypress(function(e){
		if(e.which == 13) {
			e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			jq( "#etaCountryButtonClose" ).click();	
		}			
	});
  });
  
  jq(function() { 
    jq('#wssst').change(function() {
    	if(jq('#wssst').val()=='Z' && jq('#wtudt').val()=='' ){
    		//set a mandatory field when 'Z' has been chosen
	    	var now = new Date();
	    	now.setDate(now.getDate() - 10); // add -7 days to your date variable 
	    	jq("#wtudt").val(jq.datepicker.formatDate('yymmdd', now));
    	}
    });
    jq("#tudt").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#tudt").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#tudt").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
			  }else if (length==4){
				  var userDay = str.substring(0,2);
				  var userMonth = str.substring(2,4);
				  jq("#tudt").val(g_getCurrentYearStr() + userMonth + userDay);
			  }
		  }
	  });
    jq("#tudtt").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#tudtt").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#tudtt").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
			  }else if (length==4){
				  var userDay = str.substring(0,2);
				  var userMonth = str.substring(2,4);
				  jq("#tudtt").val(g_getCurrentYearStr() + userMonth + userDay);
			  }
		  }
	  });
    
    jq("#wtudt").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#wtudt").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#wtudt").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
			  }else if (length==4){
				  var userDay = str.substring(0,2);
				  var userMonth = str.substring(2,4);
				  jq("#wtudt").val(g_getCurrentYearStr() + userMonth + userDay);
			  }
		  }
	  });
      jq("#wtudt2").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#wtudt2").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#wtudt2").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
			  }else if (length==4){
				  var userDay = str.substring(0,2);
				  var userMonth = str.substring(2,4);
				  jq("#wtudt2").val(g_getCurrentYearStr() + userMonth + userDay);
			  }
		  }
	  });
      
      
      jq("#wtudtt").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#wtudtt").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#wtudtt").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
			  }else if (length==4){
				  var userDay = str.substring(0,2);
				  var userMonth = str.substring(2,4);
				  jq("#wtudtt").val(g_getCurrentYearStr() + userMonth + userDay);
			  }
		  }
	  });
      jq("#wtudtt2").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#wtudtt2").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#wtudtt2").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
			  }else if (length==4){
				  var userDay = str.substring(0,2);
				  var userMonth = str.substring(2,4);
				  jq("#wtudtt2").val(g_getCurrentYearStr() + userMonth + userDay);
			  }
		  }
	  });
      //priskonstruktion
      jq("#tuant1").blur(function(){
    	  prisCalculateSum();
      });
      jq("#tuant2").blur(function(){
    	  prisCalculateSum();
      });
  		
  });
  
  function prisCalculateSum(){
	  var tuant1 = jq("#tuant1").val();
	  var tuant2 = jq("#tuant2").val();
	  if(tuant1 != '' && tuant2 != ''){
		  var x = tuant1;
		  var y = tuant2.replace(",", ".");
		  //multiply
		  jq("#tutbel").val(x*y);
		  if(jq("#tutval").val()==''){
			  jq("#tutval").val("NOK");
		  }
		  if(jq("#tutako").val()==''){
			  jq("#tutako").val("A");
		  }
	  }
  }
  
  //----------------------
  //Select Specific Trip
  //----------------------
  jq(function() {
	  
	  jq('#workflowTrips').on('click', 'td', function(){
		  var avdString = "avd_"; //substring from the td=id when ajax is needed
		  //Only when wanting an Ajax call. There are other cells with html POST that must be exluded from this call ...
		  if(this.id.indexOf(avdString) !== -1){
			  var ARCHIVE_DOCS_RECORD_SEPARATOR = "&nbsp;&nbsp;";
			  var id = this.id;
			  var record = id.split('@');
			  var avd = record[0];
			  var trip = record[1];
			  var status = record[2];
			  avd = avd.replace("avd_","");
			  trip = trip.replace("tripnr_","");
			  status = status.replace("status_","");
			  //DEBUG --> 
			  //alert("avd:" + avd + "trip:" + trip + "appUser:" + jq('#applicationUser').val());
			  jq.ajax({
			  	  type: 'GET',
			  	  url: 'getTripHeading_TransportDisp.do',
			  	  data: { applicationUser : jq('#applicationUser').val(), 
			  		  	  avdNr : avd, 
			  		  	  tripNr : trip },
			  	  dataType: 'json',
			  	  cache: false,
			  	  contentType: 'application/json',
			  	  success: function(data) {
			  		var len = data.length;
			  		for ( var i = 0; i < len; i++) {
			  			jq('#centuryYearTurccTuraar').val("");jq('#centuryYearTurccTuraar').val(data[i].turacc + data[i].turaar);
			  			jq('#turmnd').val("");jq('#turmnd').val(data[i].turmnd);
			  			//avd och tripNo
			  			jq('#tuavd').val(""); jq('#tuavd').val(data[i].tuavd);
			  			jq("#tuavd").attr('readonly', true);jq("#tuavd").attr('class', 'inputTextReadOnly');
			  			jq('#tupro').val(""); jq('#tupro').val(data[i].tupro);
			  			jq('#tusg').val(""); jq('#tusg').val(data[i].tusg);
			  			jq('#turund').val(""); jq('#turund').val(data[i].turund);
			  			jq('#turundJS').text(""); jq('#turundJS').text(data[i].turund);
			  			
			  			//Trip nr is required for some GUI aspects
			  			if(data[i].tupro!=""){
			  				if(status=='close'){ //only the active trips are allowed to have this option (tur planning)
			  					jq('.clazzAvdCreateNew').css('visibility', 'collapse');
			  					jq('.clazzOrderTripTab').css('visibility', 'visible');
			  					jq('.ordersTripOpen').attr('href', 'transportdisp_mainorderlist.do?action=doFind&wssavd='+ data[i].tuavd + '&wstur=' + data[i].tupro);
			  					jq('#tuproTab').text(data[i].tupro);jq('#tuproTab').addClass('text14MediumBlue');
			  					//Info fields on EDIT
			  					//jq('#tuavdJS').text(""); jq('#tuavdJS').text(data[i].tuavd);
					  			jq('#tuproJS').text(""); jq('#tuproJS').text(data[i].tupro);
					  			jq('#tuproJS').addClass('text14MediumBlue');
					  			
			  				}
			  				jq('.submitSaveClazz').val("Lagre");//change value for the submit button according to an "Update" TODO=localize!
			  				//init uploaded/archived elements' attributes
			  				jq('#uploadedDocs').attr('title', data[i].tupro);
			  				jq('#resultUploadedDocs').text("");
			  				
			  				//START populate Message Note-text area
			  				jq('#messageNote').text("");
			  				var frtxtlen = data[i].freetextlist.length;
			  				for ( var j = 0; j < frtxtlen; j++) {
			  					//OK=alert(data[i].freetextlist[j].frttxt);
			  					jq('#messageNote').append(data[i].freetextlist[j].frttxt);
			  					jq('#messageNote').append("\n");
			  				}//END Message Note
			  				
			  				//-----------------------------
			  				//START populate ArchDocs list
			  				//-----------------------------
			  				var doctriplen = data[i].getdoctrip.length;
			  				jq('#resultUploadedDocs').text("");
			  				
			  				var table = jq('<table></table>').addClass('foo');
			  				var row = jq('<tr></tr>').addClass('tableHeaderField');
			  				var td_1 = jq('<th align="left"></th>').addClass('text14').text('Dok.type');
			  				row.append(td_1);
			  				var td_2 = jq('<th align="left"></th>').addClass('text14').text('Dok.navn');
			  				row.append(td_2);
			  				var td_3 = jq('<th align="left"></th>').addClass('text14').text('Dato/kl');
			  				row.append(td_3);
			  				//TABLE APPEND row
			  				table.append(row);
			  				
			  				//fill in table
			  				for ( var j = 0; j < doctriplen; j++) {
			  					var documentText = data[i].getdoctrip[j].doctxt;
			  					if(documentText==''){
			  						documentText = data[i].getdoctrip[j].doclnk;
			  					}
			  					var row = jq('<tr></tr>').addClass('tableRow');
				  				var td_1 = jq('<td ></td>').addClass('tableCellFirst');td_1.css('white-space','nowrap')
				  				var td_2 = jq('<td></td>').addClass('tableCell');td_2.css('white-space','nowrap')
				  				var td_3 = jq('<td></td>').addClass('tableCell');td_3.css('white-space','nowrap');
				  				
			  					if(data[i].getdoctrip[j].doclnk.indexOf(".pdf")>0 ||data[i].getdoctrip[j].doclnk.indexOf(".PDF")>0){
						  			imgSrc="resources/images/pdf.png";
			  					}else{
			  						imgSrc="resources/images/jpg.png";
			  					}
			  					
			  					//ROW APPEND TD_1 
			  					td_1.text(data[i].getdoctrip[j].doctyp);
			  					row.append(td_1);
			  					
			  					//ROW APPEND TD_2 
			  					jq('<img/>',{
			  						src: imgSrc,
			  						width: '14px',
			  						height: '14px'
		  		  			  	}).appendTo(td_2);
			  					
			  					jq('<a>',{
					  			    text: documentText,
					  			    target: '_blank',
					  			    href: 'transportdisp_workflow_renderArchivedDocs.do?doclnk='+data[i].getdoctrip[j].doclnk,
					  			    click: function(){ BlahFunc( options.rowId );return false;}
			  					}).appendTo(td_2);
			  					row.append(td_2);
			  					
			  					//ROW APPEND TD_3 
			  					td_3.text(data[i].getdoctrip[j].docdat + " " + data[i].getdoctrip[j].doctim);
			  					row.append(td_3);
			  					
			  					//
			  					table.append(row);
						  	}
			  				//append TABLE to DIV
			  				jq('#resultUploadedDocs').append(table);
			  				//--------------
			  				//END ArchDocs
			  				//--------------
			  				
			  				//----------------------------------
			  				//START populate Shipping-tour list
			  				//----------------------------------
			  				var shippingTripListLen = data[i].shippingTripList.length;
			  				jq('#resultShippingList').text("");
			  				
			  				var table = jq('<table></table>').addClass('foo');
			  				var row = jq('<tr></tr>').addClass('tableHeaderField');
			  				var td_1 = jq('<th align="left"></th>').addClass('text14').text('Dato');
			  				row.append(td_1);
			  				var td_2 = jq('<th align="left"></th>').addClass('text14').text('Kl.');
			  				row.append(td_2);
			  				var td_3 = jq('<th align="left"></th>').addClass('text14').text('Fra');
			  				row.append(td_3);
			  				var td_4 = jq('<th align="left"></th>').addClass('text14').text('Til');
			  				row.append(td_4);
			  				var td_5 = jq('<th align="left"></th>').addClass('text14').text('Lengd');
			  				row.append(td_5);
			  				var td_6 = jq('<th align="left"></th>').addClass('text14').text('Selskap');
			  				row.append(td_6);
			  				var td_7 = jq('<th align="left"></th>').addClass('text14').text('Kostpris');
			  				row.append(td_7);
			  				var td_8 = jq('<th align="left"></th>').addClass('text14').text('Valuta');
			  				row.append(td_8);
			  				var td_9 = jq('<th align="left"></th>').addClass('text14').text('Bilnr.');
			  				row.append(td_9);
			  				
			  				//TABLE APPEND row
			  				table.append(row);
			  				
			  				//fill in table
			  				for ( var j = 0; j < shippingTripListLen; j++) {
			  					
			  					var row = jq('<tr></tr>').addClass('tableRow');
				  				var td_1 = jq('<td ></td>').addClass('tableCellFirst');td_1.css('white-space','nowrap')
				  				var td_2 = jq('<td></td>').addClass('tableCell');td_2.css('white-space','nowrap')
				  				var td_3 = jq('<td></td>').addClass('tableCell');td_3.css('white-space','nowrap');
				  				var td_4 = jq('<td></td>').addClass('tableCell');td_4.css('white-space','nowrap');
				  				var td_5 = jq('<td></td>').addClass('tableCell');td_5.css('white-space','nowrap');
				  				var td_6 = jq('<td></td>').addClass('tableCell');td_6.css('white-space','nowrap');
				  				var td_7 = jq('<td></td>').addClass('tableCell');td_7.css('white-space','nowrap');
				  				var td_8 = jq('<td></td>').addClass('tableCell');td_8.css('white-space','nowrap');
				  				var td_9 = jq('<td></td>').addClass('tableCell');td_9.css('white-space','nowrap');
				  				
			  					//ROW APPEND TD_1 
			  					td_1.text(data[i].shippingTripList[j].fedat2);
			  					row.append(td_1);
			  					//ROW APPEND TD_2 
			  					td_2.text(data[i].shippingTripList[j].fetime);
			  					row.append(td_2);
			  					//ROW APPEND TD_3 
			  					td_3.text(data[i].shippingTripList[j].fefrom);
			  					row.append(td_3);
			  					//ROW APPEND TD_4 
			  					td_4.text(data[i].shippingTripList[j].feto);
			  					row.append(td_4);
			  					//ROW APPEND TD_5 
			  					td_5.text(data[i].shippingTripList[j].feleng);
			  					row.append(td_5);
			  					//ROW APPEND TD_6 
			  					td_6.text(data[i].shippingTripList[j].levNavn);
			  					row.append(td_6);
			  					//ROW APPEND TD_6 
			  					td_7.text(data[i].shippingTripList[j].fepri1);
			  					row.append(td_7);
			  					//ROW APPEND TD_8 
			  					td_8.text(data[i].shippingTripList[j].fecurr);
			  					row.append(td_8);
			  					//ROW APPEND TD_9 
			  					td_9.text(data[i].shippingTripList[j].febiln);
			  					row.append(td_9);

			  					//
			  					table.append(row);
						  	}
			  				//append TABLE to DIV
			  				jq('#resultShippingList').append(table);
			  				//-----------------------
			  				//END Shipping-tour list
			  				//-----------------------
			  				
			  				
			  			}
			  			
			  			//Truck Lic
			  			jq('#tubiln').val(""); jq('#tubiln').val(data[i].tubiln);
			  			jq('#tulk').val(""); jq('#tulk').val(data[i].tulk);
			  			jq('#tuheng').val(""); jq('#tuheng').val(data[i].tuheng);
			  			jq('#tulkh').val(""); jq('#tulkh').val(data[i].tulkh);
			  			//Container
			  			jq('#tucon1').val(""); jq('#tucon1').val(data[i].tucon1);
			  			jq('#tulkc1').val(""); jq('#tulkc1').val(data[i].tulkc1);
			  			jq('#tucon2').val(""); jq('#tucon2').val(data[i].tucon2);
			  			jq('#tulkc2').val(""); jq('#tulkc2').val(data[i].tulkc2);
			  			
			  			//Truck No.
			  			jq('#tuknt2').val(""); jq('#tuknt2').val(data[i].tuknt2);
			  			jq('#tunat').val(""); jq('#tunat').val(data[i].tunat); jq('#tunat').removeClass("isa_error"); 
			  			//Truck type
			  			jq('#tubilk').val(""); jq('#tubilk').val(data[i].tubilk);
			  			//Order type
			  			jq('#tuopdt').val(""); jq('#tuopdt').val(data[i].tuopdt);
			  			//Drivers
			  			jq('#tusja1').val(""); jq('#tusja1').val(data[i].tusja1);
			  			jq('#tusjn1').val(""); jq('#tusjn1').val(data[i].tusjn1);
			  			jq('#tusja2').val(""); jq('#tusja2').val(data[i].tusja2);
			  			jq('#tusjn2').val(""); jq('#tusjn2').val(data[i].tusjn2);
			  			//Incoterms
			  			jq('#tutrma').val(""); jq('#tutrma').val(data[i].tutrma);
			  			//Dates
			  			//ETD
			  			jq('#tudt').val(""); 
			  			if(data[i].tudt!=""){
			  				var tudt = jq.datepicker.parseDate('yymmdd', data[i].tudt);
			  				jq("#tudt").val(jq.datepicker.formatDate('yymmdd', tudt));
			  				jq(jq("#tudt")).css({ "font-style": "normal", "color": "#000080"});
			  			}
			  			jq('#tutm').val(""); 
			  			if(data[i].tutm!=""){
			  				jq("#tutm").val(data[i].tutm);
			  			}else{
			  				jq("#tutm").val('0000');
			  			}
			  			jq(jq("#tutm")).css({ "font-style": "normal", "color": "#000080"});
			  			
			  			//ETA
			  			jq('#tudtt').val(""); 
			  			if(data[i].tudtt!=""){
			  				var tudtt = jq.datepicker.parseDate('yymmdd', data[i].tudtt);
			  				jq("#tudtt").val(jq.datepicker.formatDate('yymmdd', tudtt));
			  				jq(jq("#tudtt")).css({ "font-style": "normal", "color": "#000080"});
			  			}
			  			jq('#tutmt').val(""); 
			  			if(data[i].tutmt!=""){
			  				jq("#tutmt").val(data[i].tutmt);
			  			}else{
			  				jq("#tutmt").val('0000');
			  			}
			  			jq(jq("#tutmt")).css({ "font-style": "normal", "color": "#000080"});
			  			//END dates
			  			
			  			jq('#tusonf').val(""); jq('#tusonf').val(data[i].tusonf);
			  			jq('#tustef').val(""); jq('#tustef').val(data[i].tustef);
			  			jq('#tusdf').val(""); jq('#tusdf').val(data[i].tusdf);
			  			jq('#tusont').val(""); jq('#tusont').val(data[i].tusont);
			  			jq('#tustet').val(""); jq('#tustet').val(data[i].tustet);
			  			jq('#tusdt').val(""); jq('#tusdt').val(data[i].tusdt);
			  			//Agreed/Price & Price construction
			  			jq('#tutval').val(""); jq('#tutval').val(data[i].tutval);
			  			jq('#tutbel').val(""); jq('#tutbel').val(data[i].tutbel);
			  			jq('#tutako').val(""); jq('#tutako').val(data[i].tutako);
			  			jq('#tuant1').val(""); jq('#tuant1').val(data[i].tuant1);
			  			jq('#tuenh1').val(""); jq('#tuenh1').val(data[i].tuenh1);
			  			jq('#tuant2').val(""); jq('#tuant2').val(data[i].tuant2);
			  			jq('#tuenh2').val(""); jq('#tuenh2').val(data[i].tuenh2);
			  			
			  			//new fields
			  			jq('#tutm3').text(data[i].tutm3); 
			  			jq('#tutlm').text(data[i].tutlm); 
			  			jq('#tukvkt').text(data[i].tukvkt);
			  			jq('#tutvkt').text(data[i].tutvkt);
			  			jq('#tutara').text(data[i].tutara);
			  			jq('#tukam3').text(data[i].tukam3);
			  			jq('#tukalM').text(data[i].tukalM);
			  			//Henger høyd
			  			jq('#tuhoyh').text(data[i].tuhoyh);
			  			jq('#tuhoyb').text(data[i].tuhoyb);
			  			//Estim.Transpo.kost
			  			jq('#berbud').val(data[i].berbud);
			  			//Simulerad 
			  			jq('#simlm').text(data[i].simlm);
			  			jq('#simm3').text(data[i].simm3);
			  			
			  			//Totals
			  			jq('#tuao').val(""); jq('#tuao').val(data[i].tuao);
			  			jq('#tuts').val(""); jq('#tuts').val(data[i].tuts);
			  			//Total weight
			  			var capacity1 = data[i].tukvkt;if(capacity1==''){capacity1="0";}
			  			var capacity2 = data[i].tutvkt;if(capacity2==''){capacity2="0";}
			  			var tara = data[i].tutara;if(tara==''){tara="0";}
			  			jq('#wstov1').text(Number(capacity1) + Number(tara));
			  			jq('#wstov2').text(Number(capacity2) + Number(tara));
			  			
			  			//Totals
			  			jq('#totiaa').text(data[i].totiaa);
			  			jq('#totioa').text(data[i].totioa);
			  			jq('#totisa').text(data[i].totisa);
			  			jq('#totiag').text(data[i].totiag);
			  			jq('#totiog').text(data[i].totiog);
			  			jq('#totisg').text(data[i].totisg);
			  			jq('#totkaa').text(data[i].totkaa);
			  			jq('#totkoa').text(data[i].totkoa);
			  			jq('#totksa').text(data[i].totksa);
			  			jq('#totkao').text(data[i].totkao);
			  			jq('#totkoo').text(data[i].totkoo);
			  			jq('#totkso').text(data[i].totkso);
			  			jq('#totopn').text(data[i].totopn);
			  			jq('#totovf').text(data[i].totovf);
			  			jq('#totsum').text(data[i].totsum);
			  			//buttons
				  		if(jq('#divSmsEmailButtons').css('display') == 'none'){
				  			jq('#divSmsEmailButtons').css('display','inline');
				  		}
			  			//focus
			  			jq('#centuryYearTurccTuraar').focus();
			  		}
			  	  },
			  	  error: function() {
			  	    alert('Error loading ... workflowTrips onClick');
			  	  }
			  });
		  }  
	  });
	  
  });
  
  jq(function () {
	    var limit = function (event) {
	        var linha = jq(this).attr("limit").split(",")[0];
	        var coluna = jq(this).attr("limit").split(",")[1];
	
	        var array = jq(this)
	            .val()
	            .split("\n");
	
	        jq.each(array, function (i, value) {
	            array[i] = value.slice(0, linha);
	        });
	
	        if (array.length >= coluna) {
	            array = array.slice(0, coluna);
	        }
	
	        jq(this).val(array.join("\n"));
	
	    };

	    jq("textarea[limit]")
	        .keydown(limit)
	        .keyup(limit);

  });
  
  
  
  //----------------------------------------
  //Iterate through check-boxes to update
  //----------------------------------------
  function getValidCheckis(record) {
	  var FIELD_SEPARATOR = "@";
	  var requestString = "";
	  
	  //Check current orders
	  jq( ".clazz_checkis_currenttrips" ).each(function( i ) {
		  var id = this.id;
		  if (jq(this).is(":checked")) {
			  //DEBUG alert("checked:" + id);
			  var rawString = id.split(FIELD_SEPARATOR);
			  if(requestString==""){ requestString = rawString[1]; }
			  else{ requestString += FIELD_SEPARATOR + rawString[1]; }
		  }
	  });
	  //DEBUG alert(requestString);
	  //Now update all trips with checked check boxes if any
	  if(requestString!="" && requestString!=null){
		  jq.ajax({
		  	  type: 'GET',
		  	  url: 'updateTripListCloseOpenTrip_TransportDisp.do',
		  	  data: { applicationUser : jq('#applicationUser').val(), 
		  		  	  requestString : requestString }, 
		  	  dataType: 'json',
		  	  cache: false,
		  	  contentType: 'application/json',
		  	  success: function(data) {
		  		var len = data.length;
		  		//for ( var i = 0; i < len; i++) {
		  			//we send the redirect after all updates in order to refresh...
		  			window.location = "transportdisp_workflow.do?action=doFind&user=" + jq('#applicationUser').val();
		  		//}
		  	  },
		  	  error: function() {
			  	    alert('Error loading ...');
		  	  }
		  });
	  }
  }
  
  
  
  
  
  //-------------------
  //Datatables jquery
  //-------------------
  //private function
  function filterGlobal () {
    jq('#workflowTrips').DataTable().search(
    		jq('#workflowTrips_filter').val()
    ).draw();
  }

  jq(document).ready(function() {
    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	jq('#workflowTrips').dataTable( {
		"searchHighlight": true,
		"dom": '<"transpDispWorkflowFilter"f>t<"bottom"lirp><"clear">',
		"scrollY":  "700px",
		"scrollCollapse":  	true,
		"autoWidth": false, //for optimization purposes when initializing the table
		"lengthMenu": [ 50, 75, 100],
		"fnDrawCallback": function( oSettings ) {
			  jq('.dataTables_filter input').addClass("inputText12LightYellow");
	      }
	});
		//css styling
		jq('.dataTables_filter input').addClass("inputText12LightYellow");

	
    //event on input field for search
    jq('input.workflowTrips_filter').on( 'keyup click', function () {
    		filterGlobal();
    } );
    
    //focus
    jq('#centuryYearTurccTuraar').focus();
    
    
  } );
  
  
//-----------------------------
  //START Model dialog: "SMS"
  //---------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogSMS").dialog({
		  autoOpen: false,
		  maxWidth:600,
          maxHeight: 600,
          width: 380,
          height: 320,
		  modal: true,
		  dialogClass: 'main-dialog-class'
	  });
  });

  jq(function() {
	  jq("#smsButton").click(function() {
		  if(jq('#tuproJS').text() != ''){
		  	presentSmsDialog();
	  	  }
	  });
	  jq("#emailButton").click(function() {
		  if(jq('#tuproJS').text() != ''){
			  presentEmailDialog();
		  }
	  });
	  
  });
  
  /*
  ---------------------
  /PRESENT SMS DIALOG
  ---------------------
   */
  function presentSmsDialog(){
	//setters (add more if needed)
	  jq('#dialogSMS').dialog( "option", "title", "Send SMS" );
	  //deal with buttons for this modal window
	  jq('#dialogSMS').dialog({
		 buttons: [ 
            {
			 id: "dialogSaveTU",	
			 text: "Send",
			 click: function(){
				 		if(jq("#smsnr").val() != ''){
				 			sendSMS();
				 		}
		 			}
		 	 },
  			{
		 	 id: "dialogCancelTU",
		 	 text: "Lukk", 
			 click: function(){
				 		//back to initial state of form elements on modal dialog
				 		//jq("#dialogSaveTU").button("option", "disabled", true);
				 		jq("#smsnr").val("");
				 		jq("#smsStatus").text("");
				 		jq("#smsStatus").removeClass( "isa_error" );
				 		jq("#smsStatus").removeClass( "isa_success" );
		  				jq( this ).dialog( "close" ); 
			 		} 
 	 		 } ] 
	  });
	  //init values
	  //jq("#dialogSaveTU").button("option", "disabled", true);
	  //open now
	  jq('#dialogSMS').dialog('open');
  }
  
  //new line
  function sendSMS() {
	  
	  jq.ajax({
	  	  type: 'GET',
	  	  url: 'sendSMSFromTur_TransportDisp.do',
	  	  data: { applicationUser : jq('#applicationUser').val(),
	  		  	  tur : jq("#tupro").val(),
	  		  	  smsnr : jq("#smsnr").val(),
		  		  smslang : jq("#smslang").val()},
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
	  		
	  		for ( var i = 0; i < len; i++) {
	  			if(data[i].errMsg != ''){
	  				jq("#smsStatus").removeClass( "isa_success" );
	  				jq("#smsStatus").addClass( "isa_error" );
	  				jq("#smsStatus").text("SMS error: " + jq("#smsnr").val() + " " + data[i].errMsg);
	  			}else{
	  				jq("#smsStatus").removeClass( "isa_error" );
	  				jq("#smsStatus").addClass( "isa_success" );
	  				jq("#smsStatus").text("SMS er sendt til: " + jq("#smsnr").val() + " (loggført i Hendelsesloggen)");
	  			}
	  		}
	  	  },
	  	  error: function() {
	  	    alert('Error loading on Ajax callback (?) sendSMSFromTur...check js');
	  	  }
	  });
  }	

  
//-----------------------------
  //START Model dialog: "Email"
  //---------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogEmail").dialog({
		  autoOpen: false,
		  maxWidth:700,
          maxHeight: 500,
          width: 550,
          height: 400,
		  modal: true,
		  dialogClass: 'main-dialog-class'
	  });
  });
  
  function presentEmailDialog(){
	//setters (add more if needed)
	  jq('#dialogEmail').dialog( "option", "title", "Send Mail" );
	  //deal with buttons for this modal window
	  jq('#dialogEmail').dialog({
		 buttons: [ 
            {
			 id: "dialogSaveTU",	
			 text: "Send",
			 click: function(){
				 		if(jq("#email").val() != ''){
				 			sendEmail();
				 		}
		 			}
		 	 },
  			{
		 	 id: "dialogCancelTU",
		 	 text: "Lukk", 
			 click: function(){
				 		//back to initial state of form elements on modal dialog
				 		//jq("#dialogSaveTU").button("option", "disabled", true);
				 		jq("#email").val("");
				 		jq("#emailSubject").val("");
				 		jq("#emailText").val("");
				 		jq("#emailStatus").text("");
				 		//
				 		jq("#emailStatus").removeClass( "isa_error" );
				 		jq("#emailStatus").removeClass( "isa_success" );
		  				jq( this ).dialog( "close" ); 
			 		} 
 	 		 } ] 
	  });
	  //init values
	  //jq("#dialogSaveTU").button("option", "disabled", true);
	  //open now
	  jq('#dialogEmail').dialog('open');
  }
  
  //new line
  function sendEmail() {
	  
	  jq.ajax({
	  	  type: 'GET',
	  	  url: 'sendEmailFromTur_TransportDisp.do',
	  	  data: { applicationUser : jq('#applicationUser').val(),
	  		  	  tur : jq("#tuproJS").text(),
	  		  	  email : jq("#email").val(),
		  		  text : jq("#emailText").val(),
		  		  emailLang : jq("#emailLang").val() },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
	  		
	  		for ( var i = 0; i < len; i++) {
	  			if(data[i].errMsg != ''){
	  				jq("#emailStatus").removeClass( "isa_success" );
	  				jq("#emailStatus").addClass( "isa_error" );
	  				jq("#emailStatus").text("Mail error: " + jq("#email").val() + " " + data[i].errMsg);
	  			}else{
	  				jq("#emailStatus").removeClass( "isa_error" );
	  				jq("#emailStatus").addClass( "isa_success" );
	  				jq("#emailStatus").text("Mail er sendt til: " + jq("#email").val()  + " (loggført i Hendelsesloggen)");
	  			}
	  		}
	  	  },
	  	  error: function() {
	  	    alert('Error loading on Ajax callback (?) sendMail...check js');
	  	  }
	  });
  }	
  
//-----------------------------------
  //START Model dialog Copy Trip
  //-----------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			modal: true
		});
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".copyLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("copyLink","");
		  //setters (add more if needed)
		  jq('#dialog'+counterIndex).dialog( "option", "title", "Kopi Tur " + jq('#originalTrip'+counterIndex).val() );
		  
		  //deal with buttons for this modal window
		  jq('#dialog'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSave"+counterIndex,	
				 text: "Fortsett",
				 click: function(){
					 		jq('#copyForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancel"+counterIndex,
			 	 text: "Avbryt", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSave"+counterIndex).button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
					 		  
				 		} 
	 	 		 } ] 
			  
		  });
		  //init values
		  //jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  
		  //open now
		  jq('#dialog'+counterIndex).dialog('open');
		 
	  });
  });
  
  //Events for the html elements (some kind of "implicit validation")
  jq(function() {
	  jq(".newAvd").blur(function() {
		  if(jq("#dialog"+counterIndex).find('.newAvd').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
  });
  //---------------------------------
  //END Model dialog Copy Trip
  //---------------------------------

  
  /*
  ---------------------
  //PRINT DIALOG ON FORM
  ---------------------
   */
  //INIT Dialog
  jq(function() { 
	  jq("#dialogPrint").dialog({
		  autoOpen: false,
		  maxWidth:600,
	      maxHeight: 600,
	      width: 350,
	      height: 350,
		  modal: true,
		  dialogClass: 'print-dialog-class'
			  
	  });
  });
  
  function presentPrintDialog(){
	  //set default. Can't be static in html ...MUST be dynamic HERE!!
	  //jq('input:radio[name="smsType"]').filter('[value="grabber"]').attr('checked', true);
	  
	  //setters (add more if needed)
	  jq('#dialogPrint').dialog( "option", "title", "Skriv ut - Turnr. " + jq("#tuproJS").text() );
	  //deal with buttons for this modal window
	  jq('#dialogPrint').dialog({
		 buttons: [ 
            {
			 id: "dialogSaveTU",	
			 text: "Direkte til printer",
			 click: function(){
				 		if(jq("#godslistType").is(':checked') || jq("#lastlistType").is(':checked') || jq("#fbType").is(':checked') || 
				 			jq("#cmrType").is(':checked') || jq("#ffType").is(':checked')){
				 			doPrintDocuments();
				 		}
		 			}
		 	 },
  			{
		 	 id: "dialogCancelTU",
		 	 text: "Lukk", 
			 click: function(){
				 		//back to initial state of form elements on modal dialog
				 		
				 		jq('#godslistType').prop('checked', false);
				 		jq('#lastlistType').prop('checked', false);
				 		jq('#fbType').prop('checked', false);
				 		jq('#cmrType').prop('checked', false);
				 		jq('#ffType').prop('checked', false);
				 		jq("#printStatus").removeClass( "isa_error" );
				 		jq("#printStatus").removeClass( "isa_success" );
				 		jq("#printStatus").text("");
				 		
			 			//
		  				jq( this ).dialog( "close" ); 
		  				
			 		} 
 	 		 } ] 
	  });
	  //init values
	  //jq("#dialogSaveTU").button("option", "disabled", true);
	  //open now
	  jq('#dialogPrint').dialog('open');
  }
  
  //PRINT documents 
  function doPrintDocuments() {
	   //populate tur
	    jq("#tur").val(jq("#tuproJS").text());
	    var form = new FormData(document.getElementById('printForm'));
	  	//add values to form since we do not combine form data and other data in the same ajax call.
	  	//all fields in the form MUST exists in the DTO or DAO in the rest-Controller
	  	form.append("applicationUser", jq('#applicationUser').val());
	  	form.append("sign", jq('#tusg').val());
	  	form.append("avd", jq('#tuavd').val());
	  	form.append("opd", "");
	  	//serialize form
	  	var payload = jq('printForm').serialize();
	  	
	  	jq.ajax({
	        type        : 'POST',
	        url         : 'printDocumentsTrip_TransportDisp.do?' + payload,
	        data        : form,
	        dataType    : 'text',
	        cache: false,
	  	  	processData: false,
	        contentType : false,
	        success     : function(data){
	        		console.log("A");
	        		var len = data.length;
	        		if(len > 0){
	        			jq("#printStatus").removeClass( "isa_error" );
     	  				jq("#printStatus").addClass( "isa_success" );
     	  				jq("#printStatus").text("Print = OK (loggført i Hendelsesloggen)");
	        		}else{
	        			jq("#printStatus").removeClass( "isa_success" );
     	  				jq("#printStatus").addClass( "isa_error" );
     	  				jq("#printStatus").text("Print error...  ");
	        		}
             },
             error: function() {
		  		  //alert('Error loading ...');
            	 alert('Error loading on Ajax callback (?) doPrintDocuments... check js');
			  }
           });
	}
  
  
//-----------------------------------
  //PRINT DIALOG ON Tur list
  //-----------------------------------
  //Initialize <div> here
  
  jq(function() { 
	  
	  jq( ".clazz_dialogPrint" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			  maxWidth:600,
		      maxHeight: 600,
		      width: 350,
		      height: 350,
			  modal: true,
			  dialogClass: 'print-dialog-class'
		});
	  });
	  
  });
 
  //Present dialog box onClick 
  jq(function() {
	  jq(".printLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("printLink","");
		   jq("#dialogPrint"+counterIndex).dialog( "option", "title", "Skriv ut - Tur " + jq('#tur'+counterIndex).val() );
		  //deal with buttons for this modal window
		  jq("#dialogPrint"+counterIndex).dialog({
		  
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Direkte til printer",
				 click: function(){
					 		if(jq("#godslistType"+counterIndex).is(':checked') || jq("#lastlistType"+counterIndex).is(':checked') || jq("#fbType"+counterIndex).is(':checked') ||
					 				jq("#cmrType"+counterIndex).is(':checked') || jq("#ffType"+counterIndex).is(':checked')){
					 			//print directly to system printer (AS400-printer)
					 			doPrintDocumentsFromList(counterIndex);
					 		}
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU"+counterIndex,
			 	 text: "Lukk", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#fbType"+counterIndex).prop('checked', false);
					 		jq("#cmrType"+counterIndex).prop('checked', false);
					 		jq("#ffType"+counterIndex).prop('checked', false);
				 			jq("#godslistType"+counterIndex).prop('checked', false);
					 		jq("#lastlistType"+counterIndex).prop('checked', false);
					 		jq("#printStatus"+counterIndex).removeClass( "isa_error" );
					 		jq("#printStatus"+counterIndex).removeClass( "isa_success" );
					 		jq("#printStatus"+counterIndex).text("");
				 			//
			  				jq( this ).dialog( "close" );
					 		  
				 		} 
	 	 		 } ] 
			  
		  });
		  //init values
		  //jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  
		  //open now
		  jq("#dialogPrint"+counterIndex).dialog('open');
		 
	  });
  });
  
  //PRINT documents 
  function doPrintDocumentsFromList(counterIndex) {
	  	var form = new FormData(document.getElementById('printFormOnList'+counterIndex));
	  	//add values to form since we do not combine form data and other data in the same ajax call.
	  	//all fields in the form MUST exists in the DTO or DAO in the rest-Controller
	  	form.append("applicationUser", jq('#applicationUser').val());
	  	//
	  	form.append("sign", jq('#sign'+counterIndex).val());
	  	//adjust to the only id's the rest-controller knows about (avd/opd)
	  	form.append("avd", jq('#avd'+counterIndex).val());
	  	form.append("tur", jq('#tur'+counterIndex).val());
	  	
	  	//DEBUG
	  	//console.log(jq('#fbType'+counterIndex).val());
	  	//console.log(jq('#cmrType'+counterIndex).val());
	  	//console.log(jq('#godslistType'+counterIndex).val());
	  	//console.log(jq('#lastlistType'+counterIndex).val());
	  	
	  	
	  	if(jq("#fbType"+counterIndex).is(':checked')){
	  		form.append("fbTypeOnList", jq('#fbType'+counterIndex).val());
	  	}
	  	if(jq("#cmrType"+counterIndex).is(':checked')){
	  		form.append("cmrTypeOnList", jq('#cmrType'+counterIndex).val());
	  	}
	  	if(jq("#ffType"+counterIndex).is(':checked')){
	  		form.append("ffTypeOnList", jq('#ffType'+counterIndex).val());
	  	}
	  	if(jq("#godslistType"+counterIndex).is(':checked')){
	  		form.append("godslistTypeOnList", jq('#godslistType'+counterIndex).val());
	  	}
	  	if(jq("#lastlistType"+counterIndex).is(':checked')){
	  		form.append("lastlistTypeOnList", jq('#lastlistType'+counterIndex).val());
	  	}
	  	//
	  	var payload = jq('printFormOnList'+counterIndex).serialize();
	  	
	    jq.ajax({
	        type        : 'POST',
	        url         : 'printDocumentsTrip_TransportDisp.do?' + payload,
	        data        : form,
	        dataType    : 'text',
	        cache: false,
	  	  	processData: false,
	        contentType : false,
	        success     : function(data){
	        		console.log("Z");
	        		var len = data.length;
	        		if(len > 0){
	        			jq("#printStatus"+counterIndex).removeClass( "isa_error" );
     	  				jq("#printStatus"+counterIndex).addClass( "isa_success" );
     	  				jq("#printStatus"+counterIndex).text("Print = OK (loggført i Hendelsesloggen)");
	        		}else{
	        			jq("#printStatus"+counterIndex).removeClass( "isa_success" );
     	  				jq("#printStatus"+counterIndex).addClass( "isa_error" );
     	  				jq("#printStatus"+counterIndex).text("Print error...  ");
	        		}
             },
             error: function() {
		  		  //alert('Error loading ...');
            	 alert('Error loading on Ajax callback (?) doPrintDocuments(counterIndex)... check js');
			  }
             
	    });
	}
  
  //Render PDF doc 
  jq(function() {
	  
	  //Godslista
	  jq(".clazz_alinkGodslistePdf").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("alinkGodslistePdf","");
		  renderGodsLista(counterIndex);
	  });
	  jq(".clazz_imgGodslistePdf").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("imgGodslistePdf","");
		  renderGodsLista(counterIndex);
	  });
	 //Lastelista
	  jq(".clazz_alinkLastlistePdf").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("alinkLastlistePdf","");
		  renderLastLista(counterIndex);
	  });
	  jq(".clazz_imgLastlistePdf").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("imgLastlistePdf","");
		  renderLastLista(counterIndex);
	  });
	  
	  
  });
  function renderGodsLista(counterIndex){
	 if(jq('#tur'+counterIndex).val()!=''){ 
		 window.open('transportdisp_workflow_renderGodsOrLastlist.do?user=' + jq('#applicationUser').val() + '&tupro=' + jq('#tur'+counterIndex).val() + '&type=G', '_blank');
	 }
  }
  function renderLastLista(counterIndex){
	 if(jq('#tur'+counterIndex).val()!=''){ 
		 window.open('transportdisp_workflow_renderGodsOrLastlist.do?user=' + jq('#applicationUser').val() + '&tupro=' + jq('#tur'+counterIndex).val() + '&type=L', '_blank');
	 }
  }
  //----------------------------
  //END Model dialog Print docs
  //----------------------------