  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  /* avoid this if you can ... Bring likes the default ...
  jq('#transportdispForm').on("keyup keypress", function(e) {
	  var code = e.keyCode || e.which; 
	  //alert(code);
	  if (code  == 13) {               
	    e.preventDefault();
	    return false;
	  }
  });
  */
  jq(function() {
	  jq("#selectedDate").datepicker({ 
		  dateFormat: 'yymmdd',
			  onSelect: function () {
		        this.focus();
		      }
	  });
	  
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
  
  
  //------------------------------------------
  //START - Drag and drop from Trips to Order
  //------------------------------------------
  	//this drag function implemented on the callers .js
	function drag(ev) {
	    ev.dataTransfer.setData("text", ev.target.id);
	}
	
	
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
	
	function allowDrop(ev) {
	    ev.preventDefault();
	}
	function drop(ev) {
		ev.preventDefault();
	    var data = ev.dataTransfer.getData("text");
	    //alert(data);
	    var record = data.split("@");
	    var avd = record[0].replace("avd_","");
	    var trip = record[1].replace("tripnr_","");
	    var opd = jq("#wsopd").val();
	    //alert(trip + "XX" + avd + "XX" + opd);
	    if(trip!='' && avd!='' && opd!=''){
	    	//alert("AAA:" +  trip +"XX" +  avd + "XX" + opd);
	    	setTripOnOrder(trip, avd, opd);
	    }
	    //N/A
	    //ev.target.appendChild(document.getElementById(data));
	}
	//Connect trip with order
  	//if = OK then go to order (GUI)
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
		  			reloadCallerParentOrder(trip,avd,opd);
		  		}else{
		  			//update != OK
		  			alert("Error on order update [addTripToOrder_TransportDisp.do]...?");
		  		}
		  	  },
		  	  error: function() {
		  		  alert('Error loading ...');
			  }
  		});
  	}
  //------------------------------------------
  //END - Drag and drop from Trips to Order
  //------------------------------------------
  	
  //Reload the order after being coupled with the trip 
  //NOTE: this function is call from: 
  //(1) the child window transport_workflow_childwindow from js-file: transport_workflow_childwindow_trips.js
  //(2) from this same file in the above ajax: setTripOnOrder(trip,avd,opd)
  function reloadCallerParentOrder(trip, avd, opd) {
	  window.location = "transportdisp_mainorder.do?hepro=" + trip + "&heavd=" + avd + "&heopd=" + opd;
  }

  
  
  jq(function() {
  	jq('#budgetButton').click(function() {
  		window.open('transportdisp_workflow_budget.do?avd='+ jq('#heavd').val() + '&opd=' + jq('#heopd').val() + "&tur=" + jq('#tripNr').val(), 'budgetWin','top=120px,left=100px,height=800px,width=1600px,scrollbars=no,status=no,location=no');
  	});
  	jq('#planleggingButton').click(function() {
  		window.open('transportdisp_workflow_getTrip_cw.do?tuavd='+ jq('#heavd').val() + '&opd=' + jq('#heopd').val(), 'planleggingWin','top=120px,left=100px,height=800px,width=1400px,scrollbars=no,status=no,location=no');
  	});
  	
  	jq('#frisokveiButton').click(function() {
  		window.open('transportdisp_workflow_frisokvei.do?avd='+ jq('#heavd').val() + '&opd=' + jq('#heopd').val() + "&tur=" + jq('#tripNr').val(), 'frisokveiWin','top=120px,left=100px,height=600px,width=900px,scrollbars=no,status=no,location=no');
  	});
  });
  //-------------------------------------------------------------
  //START Detect Form changes on input fields (including selects)
  //this function will detect if any changes in the input fields
  //have taken place
  //-------------------------------------------------------------
  jq(function() {
	  jq(':input').each(function() { 
		    jq(this).data('initialValue', jq(this).val()); 
	  }); 
	  jq("#fraktbrevRenderPdfLink").click(function(){ 
		  	/* NOT WORKING - the user must save instead. Usual behavior when changing fields
			var msg = 'Du må lagre dine endringer!'; 
		  	var isDirty = false; 
		  	
		    jq(':input').each(function () { 
		        if(jq(this).data('initialValue') != jq(this).val()){ 
		            isDirty = true; 
		        } 
		    });
		    
		    if(isDirty == true){ 
		    	jq("#fraktbrevRenderPdfLink").removeAttr('href');
		    		//jquery ALERT;
					jq('<div></div>').dialog({
  			        modal: true,
  			        title: "Dialog",
  			        open: function() {
  			          var markup = msg;
  			          jq(this).html(markup);
  			        },
  			        buttons: {
  			          Ok: function() {
  			            jq( this ).dialog( "close" );
  			          }
  			        }
					});  //end confirm dialog
		    }else{
		    	jq("#fraktbrevRenderPdfLink").attr('href', 'transportdisp_mainorderlist_renderFraktbrev.do?user=' + jq("#applicationUser").val() +'&wsavd=' + jq("#wsavd").val() + '&wsopd=' + jq("#wsopd").val() + '&wstoll=' + jq("#dftoll").val());
		    	
		    }*/
		  	jq("#fraktbrevRenderPdfLink").attr('href', 'transportdisp_mainorderlist_renderFraktbrev.do?user=' + jq("#applicationUser").val() +'&wsavd=' + jq("#wsavd").val() + '&wsopd=' + jq("#wsopd").val() + '&wstoll=' + jq("#dftoll").val());		  
		  
	  });
  });
  //END Detect Form changes
  
  
  jq(function() {
	  jq("#wsetdd").datepicker({ 
		  onSelect: function(date) {
		  	jq("#wsetdk").focus();
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
	  jq("#wsetdd").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#wsetdd").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#wsetdd").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
			  }else if (length==4){
				  var userDay = str.substring(0,2);
				  var userMonth = str.substring(2,4);
				  jq("#wsetdd").val(g_getCurrentYearStr() + userMonth + userDay);
			  }
		  }
	  });
	  jq("#wsetdk").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#wsetdk").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#wsetdk").val(str + '00');  
			  }else if (length==1){
				  jq("#wsetdk").val('0' + str + '00');
			  }
		  }
	  });
	  
	  jq("#wsetad").datepicker({ 
		  onSelect: function(date) {
		  	jq("#wsetak").focus();
	      },
		  dateFormat: 'yymmdd',
		  firstDay: 1 //monday
	  });
	  jq("#wsetad").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#wsetad").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#wsetad").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
			  }else if (length==4){
				  var userDay = str.substring(0,2);
				  var userMonth = str.substring(2,4);
				  jq("#wsetad").val(g_getCurrentYearStr() + userMonth + userDay);
			  }
		  }
		  
	  });
	  jq("#wsetak").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#wsetak").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#wsetak").val(str + '00');  
			  }else if (length==1){
				  jq("#wsetak").val('0' + str + '00');
			  }
		  }
		  
	  });
	  //Bookingdato / time
	  jq("#hebodt").datepicker({
		  onSelect: function(date) {
		  	jq("#wsbotm").focus();
	      },
		  dateFormat: 'yymmdd',
		  firstDay: 1 //monday
	  });
	  jq("#hebodt").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#hebodt").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#hebodt").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
			  }else if (length==4){
				  var userDay = str.substring(0,2);
				  var userMonth = str.substring(2,4);
				  jq("#hebodt").val(g_getCurrentYearStr() + userMonth + userDay);
			  }
		  }
		  
	  });
	  jq("#wsbotm").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#wsbotm").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#wsbotm").val(str + '00');  
			  }else if (length==1){
				  jq("#wsbotm").val('0' + str + '00');
			  }
		  }
		  
	  });
	  
	  
	  //ALL GREY FIELDS (READ-ONLY) must block the std. behavior of the input field (lightyellow) when focus
	  jq('#hesg').focus(function(){ jq(jq("#hesg")).css({ "background-color": "lightgrey"}); });
	  jq('#henaa').focus(function(){ jq(jq("#henaa")).css({ "background-color": "lightgrey"}); });
	  jq('#whenas').focus(function(){ jq(jq("#whenas")).css({ "background-color": "lightgrey"}); });
	  jq('#whenak').focus(function(){ jq(jq("#whenak")).css({ "background-color": "lightgrey"}); });
	  jq('#henasf').focus(function(){ jq(jq("#henasf")).css({ "background-color": "lightgrey"}); });
	  jq('#henakf').focus(function(){ jq(jq("#henakf")).css({ "background-color": "lightgrey"}); });
	  //dates
	  jq('#wsatdd').focus(function(){ jq(jq("#wsatdd")).css({ "background-color": "lightgrey"}); });
	  jq('#wsatdk').focus(function(){ jq(jq("#wsatdk")).css({ "background-color": "lightgrey"}); });
	  jq('#wsatad').focus(function(){ jq(jq("#wsatad")).css({ "background-color": "lightgrey"}); });
	  jq('#wsatak').focus(function(){ jq(jq("#wsatak")).css({ "background-color": "lightgrey"}); });
	  //places
	  jq('#OWNwppns1').focus(function(){ jq(jq("#OWNwppns1")).css({ "background-color": "lightgrey"}); });
	  jq('#OWNwppns2').focus(function(){ jq(jq("#OWNwppns2")).css({ "background-color": "lightgrey"}); });
	  jq('#OWNwppns3').focus(function(){ jq(jq("#OWNwppns3")).css({ "background-color": "lightgrey"}); });
	  jq('#OWNwppns4').focus(function(){ jq(jq("#OWNwppns4")).css({ "background-color": "lightgrey"}); });
	  
  });
  
  
  //-----------------------
  // UPLOAD FILE - ORDER
  //---------------------
  function myFileUploadDragEnter(e){
	  jq("#file").addClass( "isa_blue" );
  }
  function myFileUploadDragLeave(e){
	  jq("#file").removeClass( "isa_blue" );
  }
  
  jq(function() {
	  //Triggers drag-and-drop
	  jq('#file').hover(function(){
		  jq("#file").removeClass( "isa_success" );
		  jq("#file").removeClass( "isa_error" );
	  });   
	  
	  //Triggers drag-and-drop
	  jq('#file').change(function(){
		  //Init by removing the class used in dragEnter
		  jq("#file").removeClass( "isa_blue" );
		  
		  if(jq("#wstype").val() == 'ZP'){
			 showTimestampPopup();  
		  }else{
			 jq("#userDate").val("");
			 jq("#userTime").val("");
			 uploadFile();  
		  }
		 
	  });
  });
  function uploadFile(){
	//grab all form data  
	  var form = new FormData(document.getElementById('uploadFileForm'));
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  
	  jq.ajax({
	  	  type: 'POST',
	  	  url: 'uploadFileFromOrder.do',
	  	  data: form,  
	  	  dataType: 'text',
	  	  cache: false,
	  	  processData: false,
	  	  contentType: false,
  		  success: function(data) {
		  	  var len = data.length;
	  		  if(len>0){
	  			jq("#file").val("");
			  	//Check for errors or successfully processed
			  	var exists = data.indexOf("ERROR");
			  	if(exists>0){
			  		//ERROR on back-end
			  		jq("#file").addClass( "isa_error" );
			  		jq("#file").removeClass( "isa_success" );
			  	}else{
			  		//OK
			  		jq("#file").addClass( "isa_success" );
			  		jq("#file").removeClass( "isa_error" );
			  	}
			  	//response to end user 
			  	alert(data);
			  	if(data.indexOf('[OK') == 0) {
				  	var trip = '';
				  	var avd = jq("#wsavd").val();
				  	var opd = jq("#wsopd").val();
				  	//reload
				  	reloadCallerParentOrder('',avd,opd);
			  	}
			  	//unblock
			  	jq.unblockUI();
			  	
			  	
	  		  }
	  	  }, 
	  	  error: function() {
	  		  jq.unblockUI();
	  		  alert('Error loading ...');
	  		  jq("#file").val("");
	  		  //cosmetics
	  		  jq("#file").addClass( "isa_error" );
	  		  jq("#file").removeClass( "isa_success" );
		  }
	  });
	    
	  
  }
  //END UPLOAD ORDERS
  
  
  
  //-----------------------------------------------------------------------------
  //START Model dialog timestamp for POD documents (on file upload)
  //---------------------------------------------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogTimestamp").dialog({
		  autoOpen: false,
		  maxWidth:500,
          maxHeight: 400,
          width: 400,
          height: 300,
		  modal: true
	  });
  });
  function showTimestampPopup(){
	  //setters (add more if needed)
	  jq('#dialogTimestamp').dialog( "option", "title", "Dato og klokkeslett" );
	  
	  //deal with buttons for this modal window
	  jq('#dialogTimestamp').dialog({
		 buttons: [ 
            {
			 id: "dialogSaveTU",	
			 text: "Fortsett",
			 click: function(){
            			uploadFile();
            			jq( this ).dialog( "close" ); 
			 		}
		 	 },
 	 		{
		 	 id: "dialogCancelTU",
		 	 text: "Avbryt", 
			 click: function(){
				 		//back to initial state of form elements on modal dialog
				 		jq("#dialogSaveTU").button("option", "disabled", true);
				 		jq("#selectedDate").val("");
				 		jq("#selectedTime").val("");
				 		jq("#userDate").val("");
				 		jq("#userTime").val("");
				 		jq( this ).dialog( "close" ); 
			 		} 
 	 		 } ] 
	  });
	  //init values
	  jq("#dialogSaveTU").button("option", "disabled", true);
	  //open now
	  jq("#selectedDate").focus();
	  jq('#dialogTimestamp').dialog('open');
	  
  }
  //Some validation
  jq(function() {
	  jq("#selectedDate").blur( function(){
		 if(jq("#selectedDate").val()!='' && jq("#selectedTime").val()!=''){
			 if(!validateTimestamp()){
				 jq("#dialogSaveTU").button("option", "disabled", true); 
			 }
		 }else{
			 jq("#dialogSaveTU").button("option", "disabled", true);
		 }
	  });
	  
	  jq("#selectedTime").blur(function(){
		 if(jq("#selectedDate").val()!='' && jq("#selectedTime").val()!=''){
			 if(!validateTimestamp()){
				 jq("#dialogSaveTU").button("option", "disabled", true); 
			 }
		 }else{
			 jq("#dialogSaveTU").button("option", "disabled", true);
		 }
	  });
  });
  function validateTimestamp(){
	 var retval = false; 
	 //check time logical format
	 var timeRegex = /([01]\d|2[0-3])([0-5]\d)/;
	 var dateRegex = /(19|20)\d\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/;
	 var matchDate = dateRegex.test(jq("#selectedDate").val());
     var matchTime = timeRegex.test(jq("#selectedTime").val());
     //console.log(match ? 'matches' : 'does not match');
     if(matchDate){
    	 if(matchTime){
	    	 jq("#dialogSaveTU").button("option", "disabled", false);
	    	 //set on hidden fields in upload form
	    	 jq("#userDate").val(jq("#selectedDate").val());
	    	 jq("#userTime").val(jq("#selectedTime").val());
	    	 retval = true;
	     }	
     }

     return retval;
  }
  //----------------------------------------------------------------
  //END Model dialog timestamp for POD documents (on file upload)
  //----------------------------------------------------------------
  
  
  
  //Overlay on tab (to mark visually a delay...)
  jq(function() {
	  jq('#alinkTripListId').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
	  jq('#alinkParentTripId').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
	  jq('#alinkOrderListId').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  });
  
  jq(function() {
	  //Frankatur window
	  jq('#frankatur').change(function() {
			jq('#hefr').val(jq('#frankatur').val());	
	  });
	  jq('#frankatur').keypress(function(e){
		if(e.which == 13) {
			e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			jq( "#frankaturButtonClose" ).click();	
		}			
	  });
	  //Oppdragstype window
	  jq('#oppdragType').change(function() {
			jq('#heot').val(jq('#oppdragType').val());	
	  });
	  jq('#oppdragType').keypress(function(e){
		if(e.which == 13) {
			e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			jq( "#oppdragTypeButtonClose" ).click();	
		}			
	  });
	  
	  
	  
  });
  
  
  //==============================================================================
  //START - Postal codes On-Blur (required to be an exact number and nothing else)
  //==============================================================================
  var CITY_OWNwppns1 = 1;
  var CITY_OWNwppns2 = 2;
  var CITY_OWNwppns3 = 3;
  var CITY_OWNwppns4 = 4;
  jq(function() {
	  	jq('#hesdf').focus(function() {
	  	  if(jq('#hesdf').val()=='' && jq('#heads3').val()!=''){
	  		  var sellersPostalCodeRaw = jq('#heads3').val();
	  		  var postalCode = sellersPostalCodeRaw.substr(0,sellersPostalCodeRaw.indexOf(' '));
	  		  jq('#hesdf').val(postalCode);
	  	  }
	  	});
	    jq('#hesdf').blur(function() {
	    	var id = jq('#hesdf').val();
	    	if(id!=null && id!=""){
	    		var countryCode = jq('#helka').val();
	    		getCity(CITY_OWNwppns1,id,countryCode);
	    	}else{
	    		jq('#OWNwppns1').val("");
	    	}
		});
	    jq('#hesdfIdLink').click(function() {
	    	jq('#hesdfIdLink').attr('target','_blank');
	    	window.open('transportdisp_workflow_childwindow_postalcodes.do?action=doInit&direction=fra&st2lk=' + jq('#helka').val() + '&st2kod=' + jq('#hesdf').val() + '&caller=hesdf', "postalcodeWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#hesdfIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#hesdfIdLink').click();
			}
	    });
  });
  
  jq(function() {
	  	jq('#hesdt').focus(function() {
	  		if(jq('#hesdt').val()=='' && jq('#headk3').val()!=''){
	  			var buyersPostalCodeRaw = jq('#headk3').val();
	  			var postalCode = buyersPostalCodeRaw.substr(0,buyersPostalCodeRaw.indexOf(' '));
	  			jq('#hesdt').val(postalCode);
	  		}
	  	});
	    jq('#hesdt').blur(function() {
    		var id = jq('#hesdt').val();
    		if(id!=null && id!=""){
    			var countryCode = jq('#hetri').val();
    			getCity(CITY_OWNwppns2,id,countryCode);
    		}else{
    			jq('#OWNwppns2').val("");
    		}
		});
	    jq('#hesdtIdLink').click(function() {
	    	jq('#hesdtIdLink').attr('target','_blank');
	    	window.open('transportdisp_workflow_childwindow_postalcodes.do?action=doInit&direction=til&st2lk=' + jq('#hetri').val() + '&st2kod=' + jq('#hesdt').val() + '&caller=hesdt', "postalcodeWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#hesdtIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#hesdtIdLink').click();
			}
	    });
	    
  });
  jq(function() {
	    jq('#hesdff').blur(function() {
	    		var id = jq('#hesdff').val();
	    		if(id!=null && id!=""){
	    			var countryCode = jq('#helks').val();
	    			getCity(CITY_OWNwppns3,id,countryCode);
	    		}else{
	    			jq('#OWNwppns3').val("");
	    		}
		});
	    jq('#hesdffIdLink').click(function() {
	    	jq('#hesdffIdLink').attr('target','_blank');
	    	window.open('transportdisp_workflow_childwindow_postalcodes.do?action=doInit&direction=fra&st2lk=' + jq('#helks').val() + '&st2kod=' + jq('#hesdff').val() + '&caller=hesdff', "postalcodeWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#hesdffIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#hesdffIdLink').click();
			}
	    });
  });
  
  
  /*
  jq("#hesdff").blur(function() {
	  if(mandatoryViaFromFieldsForDupDialog()){
		  presentDupDialog();
  	  }else{
  		renderViaAlert();
  	  }
  });
  jq("#hesdvt").blur(function() {
	  if(mandatoryViaToFieldsForDupDialog()){
		  presentDupDialog();
  	  }else{
  		renderViaAlert();
  	  }
  });
  */
  
  
  jq(function() {
	    jq('#hesdvt').blur(function() {
	    		var id = jq('#hesdvt').val();
	    		if(id!=null && id!=""){
	    			var countryCode = jq('#helkk').val();
	    			getCity(CITY_OWNwppns4,id,countryCode);
	    			
	    		}else{
	    			jq('#OWNwppns4').val("");
	    		}
		});
	    jq('#hesdvtIdLink').click(function() {
	    	jq('#hesdvtIdLink').attr('target','_blank');
	    	window.open('transportdisp_workflow_childwindow_postalcodes.do?action=doInit&direction=til&st2lk=' + jq('#helkk').val() + '&st2kod=' + jq('#hesdvt').val() + '&caller=hesdvt', "postalcodeWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#hesdvtIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#hesdvtIdLink').click();
			}
	    });
  });
  
  //Sender/Receiver Postnr/Poststed. To help the user get the city as long as the postal code has been entered
  jq(function() {
	  jq('#heads3').blur(function() {
		  var id = jq('#heads3').val();
		  var countryCode = jq('#helka').val();
		  jq.getJSON('searchPostNumber_TransportDisp.do', {
			  applicationUser : jq('#applicationUser').val(),
			  id : id,
			  countryCode : countryCode,
			  ajax : 'true'
		  }, function(data) {
			 var len = data.length;
			 if(len==1){ //must be a single-valid value
				for ( var i = 0; i < len; i++) {
					jq('#heads3').val(data[i].st2kod + " " + data[i].st2nvn);
				}
			 }
		});
	  });
	  jq('#headk3').blur(function() {
		  var id = jq('#headk3').val();
		  var countryCode = jq('#hetri').val();
		  jq.getJSON('searchPostNumber_TransportDisp.do', {
			  applicationUser : jq('#applicationUser').val(),
			  id : id,
			  countryCode : countryCode,
			  ajax : 'true'
		  }, function(data) {
			 var len = data.length;
			 if(len==1){ //must be a single-valid value
				for ( var i = 0; i < len; i++) {
					jq('#headk3').val(data[i].st2kod + " " + data[i].st2nvn);
				}
			 }
		});
	  });
	  
  });
  
  
  //------------------------------------------------------------
  //Tollsted codes onBlur / child window (is triggered from jsp)
  //------------------------------------------------------------
  jq(function() {
	  jq('#dftoll').blur(function() {
		  var codeId = jq("#dftoll").val();
		  jq.ajax({
		  	  type: 'GET',
		  	  url: 'searchTollstedCodes_TransportDisp.do',
		  	  data: { applicationUser : jq('#applicationUser').val(),
			  		  kode : codeId },
		  	  dataType: 'json',
		  	  cache: false,
		  	  contentType: 'application/json',
		  	  success: function(data) {
		  		var len = data.length;
		  		if(len==1){
			  		for ( var i = 0; i < len; i++) {
			  			jq('#dftoll').removeClass( "isa_error" );
			  		}
		  		}else{
		  			jq('#dftoll').addClass( "isa_error" );
		  		}
		  	  }
		  }); 
		  
	  });	  
	  
	  
	  jq('#dftollIdLink').click(function() {
	  	jq('#dftollIdLink').attr('target','_blank');
	  	window.open('transportdisp_workflow_childwindow_tollstedcodes.do?action=doInit&kode=' + jq('#dftoll').val(), "tollstedCodesWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#dftollIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#dftollIdLink').click();
			}
	  });
  });
  
  
  
  
  //Ajax on postal codes
  function getCity(target, id, countryCode){
	  jq.getJSON('searchPostNumber_TransportDisp.do', {
		  applicationUser : jq('#applicationUser').val(),
		  id : id,
		  countryCode : countryCode,
		  async: false,
		  ajax : 'true'
	  }, function(data) {
		var len = data.length;
		if(len==1){ //must be a single-valid value
			for ( var i = 0; i < len; i++) {
				if(target==CITY_OWNwppns1){
					jq('#OWNwppns1').val(data[i].st2nvn);
					jq('#helka').val(data[i].st2lk);
					jq('#hesdf').attr("class","inputTextMediumBlue11MandatoryField");
					
				}else if(target==CITY_OWNwppns2){
					jq('#OWNwppns2').val(data[i].st2nvn);
					jq('#hetri').val(data[i].st2lk);
					jq('#hesdt').attr("class","inputTextMediumBlue11MandatoryField");
					
				}else if(target==CITY_OWNwppns3){
					jq('#OWNwppns3').val(data[i].st2nvn);
					jq('#helks').val(data[i].st2lk);
					jq('#hesdff').attr("class","inputTextMediumBlue11");
					//Via-fields
					if(jq('#ffavd').val() == ''){
						jq('#ffavd').val(data[i].avd);
					}
					if(data[i].oprkod != ''){
						jq('#hesdff').val(data[i].st2kod);
					}
					//end Via-fields
				}else if(target==CITY_OWNwppns4){
					jq('#OWNwppns4').val(data[i].st2nvn);
					jq('#helkk').val(data[i].st2lk);
					jq('#hesdvt').attr("class","inputTextMediumBlue11");
					//Via-fields
					if(jq('#vfavd').val() == ''){
						jq('#vfavd').val(data[i].avd);
					}
					if(data[i].oprkod != ''){ //change e.g. user input=H1,H2,etc to the correct postnr
						jq('#hesdvt').val(data[i].st2kod);
					}
					//end Via-fields
				}
			}
		}else{
			//invalid postal code
			if(target==CITY_OWNwppns1){
				jq('#hesdf').addClass("text11RedBold");
				jq('#OWNwppns1').val("?");
			}else if(target==CITY_OWNwppns2){
				jq('#hesdt').addClass("text11RedBold");
				jq('#OWNwppns2').val("?");
			}else if(target==CITY_OWNwppns3){
				jq('#hesdff').addClass("text11RedBold");
				jq('#OWNwppns3').val("?");
			}else if(target==CITY_OWNwppns4){
				jq('#hesdvt').addClass("text11RedBold");
				jq('#OWNwppns4').val("?");
			}
		}
	});
  }
  //=================
  //END Postal codes
  //=================
  
  //Message chunker
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

  //------------
  //SUM fields
  //------------
  function sumAntal(element) {
	  //element.id;
	  var sum = 0;
	  jq( ".clazzAntMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvant_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#hent').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
  }
  
  function sumVareslag(element){
	  var id = element.id;
	  var counter = id.replace("fvvt_","");
	  var targetStr = "";
	  //This feature is required only IF and only IF there is just a single line in the matrix. 
	  //Otherwise, the end-user must enter the total vareslag him/herself
	  if(counter=='1'){
		  var fvpakn = jq('#fvpakn_' + counter).val();
		  var fvvt = jq('#fvvt_' + counter).val();
		  var fvant = jq('#fvant_' + counter).val();
		  if(fvvt!='' && fvant!=''){
			  targetStr = fvpakn + " " + fvvt;
		  }
		  if(!jq('#hestl4').prop('checked')){
			  if(jq('#hevs1').val()==''){
				  jq('#hevs1').val(targetStr);
			  }
		  }
	  }
  }
	  
  function sumWeight(element) {
	  //element.id;
	  var sum = 0;
	  jq( ".clazzWeightMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvvkt_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#hevkt').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
  }

  function sumVolume(element) {
	  //element.id;
	  var MAX_VALUE = 9999.99;
	  var sum = 0;
	  jq( ".clazzVolMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvvol_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  var dblValue = Number(value);
			  if(dblValue > MAX_VALUE){
				  jq('#fvvol_' + counter).addClass( "isa_error" );
			  }else{
				  sum += Number(value);
				  jq('#fvvol_' + counter).removeClass( "isa_error" );
			  }
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#hem3').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
  }
  function calculateVolume(element) {
	  var id = element.id;
	  if(jq(element).val()==''){
		  var counter; var antal; var length; var width; var height; var result;
		  if(id.indexOf("_") > -1){
			  counter = id.replace("fvvol_","");
			  //now get all parameters
			  antal = jq('#fvant_'+counter).val();
			  length = jq('#fvlen_'+counter).val();
			  width= jq('#fvbrd_'+counter).val();
			  height= jq('#fvhoy_'+counter).val();
			  
		  }else{
			  antal = jq('#fvant').val();
			  length = jq('#fvlen').val();
			  width= jq('#fvbrd').val();
			  height= jq('#fvhoy').val();
		  }	
		  result = Number(antal)*Number(length)*Number(width)*Number(height);
		  //Now to the math
		  if(result>0){
			  result = result * 0.000001;
			  jq(element).val(result.toLocaleString('de-DE', { useGrouping: false }));
		  }
	  }
  }
  function sumLm() {
	  var MAX_VALUE = 99.99;
	  var sum = 0;
	  jq( ".clazzLmMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvlm_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  var dblValue = Number(value);
			  if(dblValue > MAX_VALUE){
				  jq('#fvlm_' + counter).addClass( "isa_error" );
			  }else{
				  sum += Number(value);
				  jq('#fvlm_' + counter).removeClass( "isa_error" );
				  //AUTO FILL Lmla (if applicable)
				  if(jq('#fvlm2_' + counter).val()==''){
					  jq('#fvlm2_' + counter).val(jq('#fvlm_' + counter).val());
				  }
			  }
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#helm').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  
	  
  }
  function sumLmla() {
	  var MAX_VALUE = 99.99;
	  var sum = 0;
	  jq( ".clazzLmlaMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvlm2_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  var dblValue = Number(value);
			  if(dblValue > MAX_VALUE){
				  jq('#fvlm2_' + counter).addClass( "isa_error" );
			  }else{
				  sum += Number(value);
				  jq('#fvlm2_' + counter).removeClass( "isa_error" );
			  }
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#helmla').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  
  }
  
  //ADR
  function private_sumAdr() {
	  //element.id;
	  var MAX_VALUE = 99.99;
	  var sum = 0;
	  var sum = 0;
	  jq( ".clazzAdrMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#ffpoen_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }else{
			  sum += Number(0);
		  }
	  });
	  //this ADR is THE ONLY FIELD not required to block the sum with the Protect checkbox (hestl4)
	  jq('#hepoen').val(sum.toLocaleString('de-DE', { useGrouping: false }));
  }
  
  //------------------
  // CHECK functions
  //------------------
  function checkVolumeNewLine() {
	  var MAX_VALUE = 9999.99;
	  var sum = 0;
	  var value = jq('#fvvol').val();
	  if(value!=''){
		  value = value.replace(",",".");
		  var dblValue = Number(value);
		  if(dblValue > MAX_VALUE){
			  jq('#fvvol').addClass( "isa_error" );
		  }else{
			  sum += Number(value);
			  jq('#fvvol').removeClass( "isa_error" );
		  }
	  }
  }
  function checkLmNewLine() {
	  jq('#fvlm').removeClass( "isa_error" );
	  
	  var MAX_VALUE = 99.99;
	  var sum = 0;
	  var value = jq('#fvlm').val();
	  if(value!=''){
		  value = value.replace(",",".");
		  var dblValue = Number(value);
		  if(dblValue > MAX_VALUE){
			  jq('#fvlm').addClass( "isa_error" );
		  }else{
			  sum += Number(value);
			  jq('#fvlm').removeClass( "isa_error" );
			  //AUTO FILL cousin field
			  if(jq('#fvlm2').val() == ""){
				  jq('#fvlm2').val(jq('#fvlm').val());
			  }
		  }
	  }
  }
  function checkLm2NewLine() {
	  var MAX_VALUE = 99.99;
	  var sum = 0;
	  var value = jq('#fvlm2').val();
	  if(value!=''){
		  value = value.replace(",",".");
		  var dblValue = Number(value);
		  if(dblValue > MAX_VALUE){
			  jq('#fvlm2').addClass( "isa_error" );
		  }else{
			  sum += Number(value);
			  jq('#fvlm2').removeClass( "isa_error" );
		  }
	  }else{
		  //AUTO FILL cousin field
		  if(jq('#fvlm2').val() == ""){
			  jq('#fvlm2').val(jq('#fvlm').val());
		  }
	  }
  }
  function checkHem3() {
	  var MAX_VALUE = 9999.999;
	  var sum = 0;
	  var value = jq('#hem3').val();
	  if(value!=''){
		  value = value.replace(",",".");
		  var dblValue = Number(value);
		  if(dblValue > MAX_VALUE){
			  jq('#hem3').addClass( "isa_error" );
		  }else{
			  sum += Number(value);
			  jq('#hem3').removeClass( "isa_error" );
		  }
	  }
  }
  function checkHelm() {
	  var MAX_VALUE = 99.99;
	  var sum = 0;
	  var value = jq('#helm').val();
	  if(value!=''){
		  value = value.replace(",",".");
		  var dblValue = Number(value);
		  if(dblValue > MAX_VALUE){
			  jq('#helm').addClass( "isa_error" );
		  }else{
			  sum += Number(value);
			  jq('#helm').removeClass( "isa_error" );
		  }
	  }
  }
  function checkHelmla() {
	  var MAX_VALUE = 99.99;
	  var sum = 0;
	  var value = jq('#helmla').val();
	  if(value!=''){
		  value = value.replace(",",".");
		  var dblValue = Number(value);
		  if(dblValue > MAX_VALUE){
			  jq('#helmla').addClass( "isa_error" );
		  }else{
			  sum += Number(value);
			  jq('#helmla').removeClass( "isa_error" );
		  }
	  }
  }
  
  
  
  //-----------------------
  //INIT CUSTOMER Object
  //-----------------------
  var map = {};
  //init the customer object in javascript (will be put into a map)
  var customer = new Object();
  //fields
  customer.kundnr = "";customer.knavn = "";customer.adr1 = "";
  customer.adr2 = "";customer.adr3 = ""; customer.land = ""; customer.auxnavn=""; customer.auxtlf=""; customer.auxmail="";
  //--------------------------------------------------------------------------------------
  //Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
  //--------------------------------------------------------------------------------------
  jq(function() {  
	  	//SHIPPER/CONSIGNOR
	    jq('#hekns').blur(function() {
	    	getConsignor();	
		});
	    function getConsignor(){
	    	var hekns = jq('#hekns').val();
    		if(hekns!=null && hekns!=""){
	    		jq.getJSON('searchCustomer_TransportDisp.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : "",
				customerNumber : jq('#hekns').val(),
				ajax : 'true'
	    		}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						customer = new Object();
						customer.kundnr = data[i].kundnr;
						customer.knavn = data[i].navn;
						customer.auxnavn = data[i].auxnavn;
						customer.adr1 = data[i].adr1;
						customer.adr2 = data[i].adr2;
						customer.adr3 = data[i].adresse;
						customer.land = data[i].land;
						customer.auxtlf = data[i].auxtlf;
						customer.auxmail = data[i].auxmail;
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						//always show seller
						var seller = customer.knavn;
						jq('#whenas').val(seller);
		    			//now check ids (name and address in order to overdrive (when applicable)
						var name = jq('#henas').val().trim();
		    			//var address = jq('#heads1').val().trim();
		    			//only if name is empty
		    			if(name==''){
							jq('#hekns').val(customer.kundnr);
							jq('#whenas').val(seller);
							if(customer.auxnavn!=''){
								jq('#henas').val(customer.auxnavn);
							}else{
								//fallback 
								jq('#henas').val(jq('#whenas').val());
							}
							jq('#heads1').val(customer.adr1);
							jq('#heads2').val(customer.adr2);
							jq('#heads3').val(customer.adr3 + " " +  customer.land);
							jq('#wsscont').val("");
							jq('#wsstlf').val(customer.auxtlf);
							jq('#wssmail').val(customer.auxmail);
							//Form field on "Fra"
							jq('#helka').val(customer.land);
		    			}	
					}else{
						//init fields
						jq('#hekns').val("");
						jq('#whenas').val("");
						jq('#henas').val("");
						jq('#heads1').val("");
						jq('#heads2').val("");
						jq('#heads3').val("");
					}
	    		});
    		}
	    }
	    
	    
	    //CONSIGNEE
	    jq('#heknk').blur(function() {
	    	getConsignee();
		});
	    function getConsignee(){
	    	var heknk = jq('#heknk').val();
    		if(heknk!=null && heknk!=""){
				jq.getJSON('searchCustomer_TransportDisp.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : "",
				customerNumber : jq('#heknk').val(),
				ajax : 'true'
				}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						customer = new Object();
						customer.kundnr = data[i].kundnr;
						customer.knavn = data[i].navn;
						customer.adr1 = data[i].adr1;
						customer.adr2 = data[i].adr2;
						customer.adr3 = data[i].adresse;
						customer.land = data[i].land;
						customer.auxnavn = data[i].auxnavn;
						customer.auxtlf = data[i].auxtlf;
						customer.auxmail = data[i].auxmail;
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						var buyer = customer.knavn;
						jq('#whenak').val(buyer);
						
						var name = jq('#henak').val().trim();
	    				//var address = jq('#headk1').val().trim();
	    				//only if name is empty
	    				if(name==''){
							jq('#heknk').val(customer.kundnr);
							jq('#whenak').val(buyer);
							if(customer.auxnavn!=''){
								jq('#henak').val(customer.auxnavn);
							}else{
								//fallback
								jq('#henak').val(jq('#whenak').val());
							}
							jq('#headk1').val(customer.adr1);
							jq('#headk2').val(customer.adr2);
							jq('#headk3').val(customer.adr3 + " " + customer.land);
							jq('#wskcont').val("");
							jq('#wsktlf').val(customer.auxtlf);
							jq('#wskmail').val(customer.auxmail);
							//Form field on "Til"
							jq('#hetri').val(customer.land);
	    				}
					}else{
						//init fields
						jq('#heknk').val("");
						jq('#whenak').val("");
						jq('#henak').val("");
						jq('#headk1').val("");
						jq('#headk2').val("");
						jq('#headk3').val("");
					}
				});
    		}
	    }
	    //---------------------------------------------
	    //OPPDGIV - PRINCIPAL - Get cascade other id's
	    //---------------------------------------------
	    jq('#trknfa').blur(function() {
    		getPrincipalName();
	    });
	    //Invoice parties
	    function setInvoiceParties() {
	    	var SELLER = "S"; var BUYER = "K";
			var id = jq('#trknfa').val();
			if(id!=null && id!=""){
				if(SELLER==jq('#trkdak').val()){
					//(A) Seller Invoice party
					//if(jq('#heknsf').val()==''){
						jq('#heknsf').val(jq('#varFakknr').val());
	    				jq('#heknkf').val("");
	    				jq('#henakf').val("");
	    				getInvoicePartySeller();
					//}
					//(B) Sender-Consignor
					if(jq('#hekns').val()==''){
	    				jq('#hekns').val(id);
	    				jq('#hekns').blur(); //trigger the Consignor event
					}
				}else if(BUYER==jq('#trkdak').val()){
					//(A) Buyer Invoice party
					//if(jq('#heknkf').val()==''){
	    				jq('#heknkf').val(jq('#varFakknr').val());
	    				jq('#heknsf').val("");
	    				jq('#henasf').val("");
	    				getInvoicePartyBuyer();
					//}
					//(B) Receiver-Consignee
					if(jq('#heknk').val()==''){
	    				jq('#heknk').val(id);
	    				jq('#heknk').blur(); //trigger the Consignee event
					}
				}
			}
	    	
	    };
	    //OPPDGIV. code
	    jq(function() { 
		    jq('#trkdak').change(function() {
		    	setInvoiceParties();
		    	jq('#trknfa').focus();
		    	if(jq('#trkdak').val()=='S'){
		    		//if(jq('#hefr').val() == ''){
		    			jq('#hefr').val('S');
		    		//}
		    	} else if(jq('#trkdak').val()=='K'){
		    		//if(jq('#hefr').val() == ''){
		    			jq('#hefr').val('M');
		    		//}
		    	}
		    });
	    });
	    //Fakturapart Seller
	    jq('#heknsf').blur(function() {
	    	getInvoicePartySeller();
		});
	    //Fakturapart Buyer
	    jq('#heknkf').blur(function() {
	    	getInvoicePartyBuyer();
	    });
	    //-------------------
	    //getPrincipalName()
	    //-------------------
	    function getPrincipalName() {
	    	var id = jq('#trknfa').val();
    		if(id!=null && id!=""){
    			jq.getJSON('searchCustomer_TransportDisp.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : "",
				customerNumber : id,
				ajax : 'true'
	    		}, function(data) {
	    			//alert("Hello");
	    			jq('#henaa').val("");
	    			var len = data.length;
	    			for ( var i = 0; i < len; i++) {
	    				jq('#henaa').val(data[i].navn);
	    				jq('#varFakknr').val(data[i].fakknr);
	    				//----------------------------------------------------------------------------------------------
	    				//INVOICE Parties fragment
	    				//HAS TO BE HERE. 
	    				//Can not move this fragment outside this ajax call. Otherwise there will not be a sync call...
	    				//-----------------------------------------------------------------------------------------------
	    				setInvoiceParties();
	    			}
				});
    		}
	    }
	    //--------------------------
	    //getInvoicePartySeller()
	    //--------------------------
	    function getInvoicePartySeller() {
	    	var id = jq('#heknsf').val();
    		if(id!=null && id!=""){
	    		jq.getJSON('searchCustomer_TransportDisp.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : "",
				customerNumber : id,
				ajax : 'true'
	    		}, function(data) {
	    			jq('#henasf').val("");
	    			var len = data.length;
	    			for ( var i = 0; i < len; i++) {
	    				if(data[i].aktkod != 'I'){
	    					jq('#henasf').val(data[i].navn);
	    					//jq('#heknsf').addClass( "inputTextMediumBlueUPPERCASE" );
	    					jq('#heknsf').removeClass ("isa_warning");
	    					jq('#henasf').removeClass ("isa_warning");
	    				}else{
	    					jq('#heknsf').addClass( "isa_warning" );
	    					jq('#henasf').addClass( "isa_warning" );
	    					//jq('#heknsf').removeClass ("inputTextMediumBlueUPPERCASE");
	    					jq('#henasf').val("adr.kunde?" + data[i].navn);
	    				}
	    			}
	    		});
    		}else{
    			jq('#henasf').val("");
    		}
	    }
	    //--------------------------
	    //getInvoicePartyBuyer()
	    //--------------------------
	    function getInvoicePartyBuyer() {
    		var id = jq('#heknkf').val();
    		if(id!=null && id!=""){
	    		jq.getJSON('searchCustomer_TransportDisp.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : "",
				customerNumber : id,
				ajax : 'true'
	    		}, function(data) {
	    			jq('#henakf').val("");
	    			var len = data.length;
	    			for ( var i = 0; i < len; i++) {
	    				jq('#henakf').val(data[i].navn);
	    				
	    				if(data[i].aktkod != 'I'){
	    					jq('#henakf').val(data[i].navn);
	    					jq('#heknkf').removeClass ("isa_warning");
	    					jq('#henakf').removeClass ("isa_warning");
	    				}else{
	    					jq('#heknkf').addClass( "isa_warning" );
	    					jq('#henakf').addClass( "isa_warning" );
	    					jq('#henakf').val("adr.kunde?" + data[i].navn);
	    				}
	    				
	    			}
	    		});
    		}else{
    			jq('#henakf').val("");
    		}
		}
	    
	});
  

  //--------------------------
  //Add Item line (in Order)
  //--------------------------
  function addItemLine() {
	  //[1] Validate new line first
	  //if(private_validateNewItemLine()){
	  
		  //[2] At this point we are ready to prepare and execute the add line implementation
		  var newLineNr = Number(jq('#upperCurrentItemlineNr').val()) + 1;
		  //Build the request string here (new line)
		  //user=JOVO&avd=75&opd=11&fbn=1&lin=2&mode=A&fvant=11&fvvkt=15
		  var requestString = "user=" + jq('#applicationUser').val() + "&avd=" + jq('#heavd').val() + 
		  					 "&opd=" + jq('#heopd').val() + "&fbn=1" + "&lin=" +  newLineNr + "&mode=A" +
		  					 "&fmmrk1=" + jq('#fmmrk1').val() + "&fvant=" +  jq('#fvant').val() + "&fvpakn=" +  jq('#fvpakn').val() +	
		  					 "&fvvt=" + jq('#fvvt').val() + "&fvvkt=" +  jq('#fvvkt').val() + "&fvlen=" +  jq('#fvlen').val() +
		  					 "&fvbrd=" + jq('#fvbrd').val() + "&fvhoy=" +  jq('#fvhoy').val() + "&fvvol=" +  jq('#fvvol').val() +
		  					 "&fvlm=" + jq('#fvlm').val() + "&fvlm2=" +  jq('#fvlm2').val() + "&ffunnr=" +  jq('#ffunnr').val() +
		  					 "&ffembg=" + jq('#ffembg').val() + jq('#ffindx').val() + "&ffantk=" +  jq('#ffantk').val() + "&ffante=" +  jq('#ffante').val() +
		  					 "&ffenh=" + jq('#ffenh').val();
		  
		  
		  var ant = jq('#fvant').val();
		  var weight = jq('#fvvkt').val();
		  var description = jq('#fvvt').val();
		  //mandatory fields
		  if ( ant!='' && weight!='' && description !='' ){
			  //-------------------------
			  //[1] update totals on GUI
			  //-------------------------
			  updateOrderLineTotalsBeforeAdd();
			  //Prepare the total records in order to update the order record with these
			  var orderLinesTotalString = "@hent_" + jq('#hent').val() + "@hevkt_" + jq('#hevkt').val() + "@hem3_" + jq('#hem3').val() + "@helm_" + jq('#helm').val() + 
			  							  "@helmla_" + jq('#helmla').val() + "@hepoen_" + jq('#hepoen').val();
										  //append the protect checkbox value (if applicable)
										  if(jq('#hestl4').prop('checked')){ orderLinesTotalString += "@hestl4_" + jq('#hestl4').val();}
										  else{ orderLinesTotalString += "@hestl4_"; }
			  
			  //-----------------------------
			  //[2] now to the add line impl.
			  //-----------------------------
			  jq.ajax({
			  	  type: 'GET',
			  	  url: 'addNewOrderDetailLine_TransportDisp.do',
			  	  data: { applicationUser : jq('#applicationUser').val(), 
			  		  	  requestString : requestString }, 
			  	  dataType: 'json',
			  	  cache: false,
			  	  contentType: 'application/json',
			  	  success: function(data) {
			  		var len = data.length;
			  		for ( var i = 0; i < len; i++) {
			  			//we send the redirect after a successfull creation in order to refresh...
			  			//success code = 1
			  			if(data[i].fvlinr=='1'){
			  				//jq('#submit').click(); //doUpdate
			  				//doFind on redirect...with extra order line total fields in order to update them there...
			  				//Note: the reason for updating the totals in doFind is because no session object is possible to hand in an ajax call.
			  				//For the above reason we then use the doFind method in order to get the whole record and update its total fields once there.
			  				//There is no other way to do the update without breaking the Ajax design in Spring and good healthy session handling in the web infrastructure
				  			window.location = "transportdisp_mainorder.do?hepro=" + jq('#tripNr').val() + "&heavd=" + jq('#heavd').val() + 
				  								"&heopd=" + jq('#heopd').val() + "&oltotals=" + orderLinesTotalString;
				  			
			  			}else{
			  				alert("[ERROR] when creating the order line...?");
			  			}
			  		}
			  	  },
			  	  error: function() {
				  	    alert('Error loading ...');
			    
			  	  }
			  });
			  
		  }else{
			  alert("[ERROR] missing mandatory fields for new line...");
		  }
	  //}
	  
  }
  
  //----------------------------------
  //Add Item line (in existent Order)
  //----------------------------------
  function validateItemLineExtensionLmLma(element) { 
	  var id = element.id;
	  var record = id.split('_');
	  var lineNr = record[1];
	  if(record[0].startsWith('fvlm')){
		  if(jq('#fvlm_' + lineNr).val()==''){
			  validateItemLine(lineNr);  
		  }
	  }
	  if(record[0].startsWith('fvlm2')){
		  if(jq('#fvlm2_' + lineNr).val()==''){
			  validateItemLine(lineNr);  
		  }
	  }
	  
  }
  function validateItemLine(lineNr) {
	  var retval = true;
	  var i = Number(lineNr);
	  //Build the request string here (new line)
	  //user=JOVO&avd=75&opd=19&fmmrk1=&fvant=1&fvpakn=&fvvt=TEST&fvvkt=1000&fvvol=&fvlm=&fvlm2=&fvlen=&fvbrd=&fvhoy=&ffunnr=1234&ffemb=&ffantk=1&ffante=1&ffenh=KGM
	  var requestString = "user=" + jq('#applicationUser').val() + "&avd=" + jq('#heavd').val() + "&opdtyp=" + jq('#heot').val() +
			 "&opd=" + jq('#heopd').val() + "&fmmrk1=" + jq('#fmmrk1_' + i).val() + "&fvant=" +  jq('#fvant_' + i).val() + "&fvpakn=" +  jq('#fvpakn_' + i).val() +	
			 "&fvvt=" + jq('#fvvt_' + i).val() + "&fvvkt=" +  jq('#fvvkt_' + i).val() + "&fvlen=" +  jq('#fvlen_' + i).val() +
			 "&fvbrd=" + jq('#fvbrd_' + i).val() + "&fvhoy=" +  jq('#fvhoy_' + i).val() + "&fvvol=" +  jq('#fvvol_' + i).val() +
			 "&fvlm=" + jq('#fvlm_' + i).val() + "&fvlm2=" +  jq('#fvlm2_' + i).val() + "&ffunnr=" +  jq('#ffunnr_' + i).val() +
			 "&ffembg=" + jq('#ffembg_' + i).val() + "&ffindx=" + jq('#ffindx_' + i).val() + "&ffantk=" +  jq('#ffantk_' + i).val() + "&ffante=" +  jq('#ffante_' + i).val() +
			 "&ffenh=" + jq('#ffenh_' + i).val();
	  //alert(requestString);
	  //mandatory fields
	  var ant = jq('#fvant_' + i).val(); var weight = jq('#fvvkt_' + i).val(); var description = jq('#fvvt_' + i).val();
	  if ( ant!='' && weight!='' && description !='' ){
		  //Only for existent orders.New orders are handled in the reflection mechanism at the controller
		  var lineNrParam = lineNr;
		  if(jq('#fvlinr_' + i).val()==''){ lineNrParam = null; }
		  //if(jq('#heopd').val()!=''){
			  jq.ajax({
			  	  type: 'GET',
			  	  url: 'validateCurrentOrderDetailLine_TransportDisp.do',
			  	  data: { applicationUser : jq('#applicationUser').val(), 
			  		  	  requestString : requestString,
			  		  	  lineNr : lineNrParam },
			  	  dataType: 'json',
			  	  cache: false,
			  	  contentType: 'application/json',
			  	  success: function(data) {
			  		var len = data.length;
			  		for ( var j = 0; j < len; j++) {
			  			//we send the redirect after a successfull creation in order to refresh...
			  			//success code = 1
			  			if(data[j].errMsg!=null && data[j].errMsg!=''){
			  				//alert(data[i].errMsg);
			  				var errorPrefix = "[ERROR] FATAL on line:" + lineNr;
				  			jq('#orderLineErrMsgPlaceHolder').text(errorPrefix + " -->" + data[j].errMsg);
				  			jq('#fvvkt_' + j).focus(); //always to weight column otherwise we lose control
				  			retval = false;
			  			}else{
			  				jq('#orderLineErrMsgPlaceHolder').text("");
			  				//here we take care of the parameters that will complete some values
			  				if(jq('#fvlm_' + i).val()==''){ jq('#fvlm_' + i).val(data[j].fvlm); }
			  				if(jq('#fvlm2_' + i).val()==''){ jq('#fvlm2_' + i).val(data[j].fvlm2); }
			  				//trigger local function to keep in sync the math
			  				sumLm();sumLmla();
			  			}	
			  		}
			  		
			  	  },
			  	  error: function() {
				  	    alert('Error loading ...');
				  	    retval = false;
			  	  }
			  });
		  //}
	  }else{
		  var fvlinrExists = jq('#fvlinr_' + i).val(); 
		  if(fvlinrExists!='' || (ant!='' || weight!='' || description !='')  ){
			  alert("[Linje ERROR] Antall, Vareslag, Vekt er obligatoriske");
			  if(ant==''){
				  jq('#fvant_' + i).focus();  
			  }else if (description==''){
				  jq('#fvvt_' + i).focus();
			  }else if (weight==''){
				  jq('#fvvkt_' + i).focus();
			  }
			  retval = false;
		  }
	  }
	  return retval;
	  
  }
  
  //----------------------------------
  //NEW Item line
  //----------------------------------
  function validateNewItemLine() {
	  var retval = false;
	  //Build the request string here (new line)
	  //user=JOVO&avd=75&opd=19&fmmrk1=&fvant=1&fvpakn=&fvvt=TEST&fvvkt=1000&fvvol=&fvlm=&fvlm2=&fvlen=&fvbrd=&fvhoy=&ffunnr=1234&ffemb=&ffantk=1&ffante=1&ffenh=KGM
	  var requestString = "user=" + jq('#applicationUser').val() + "&avd=" + jq('#heavd').val() + "&opdtyp=" + jq('#heot').val() +
			 "&opd=" + jq('#heopd').val() + "&fmmrk1=" + jq('#fmmrk1').val() + "&fvant=" +  jq('#fvant').val() + "&fvpakn=" +  jq('#fvpakn').val() +	
			 "&fvvt=" + jq('#fvvt').val() + "&fvvkt=" +  jq('#fvvkt').val() + "&fvlen=" +  jq('#fvlen').val() +
			 "&fvbrd=" + jq('#fvbrd').val() + "&fvhoy=" +  jq('#fvhoy').val() + "&fvvol=" +  jq('#fvvol').val() +
			 "&fvlm=" + jq('#fvlm').val() + "&fvlm2=" +  jq('#fvlm2').val() + "&ffunnr=" +  jq('#ffunnr').val() +
			 "&ffembg=" + jq('#ffembg').val() + "&ffindx=" + jq('#ffindx').val() + "&ffantk=" +  jq('#ffantk').val() + "&ffante=" +  jq('#ffante').val() +
			 "&ffenh=" + jq('#ffenh').val();
	  //alert(requestString);
	  //mandatory fields
	  var ant = jq('#fvant').val(); var weight = jq('#fvvkt').val(); var description = jq('#fvvt').val();
	  if ( ant!='' && weight!='' && description !='' ){
		  jq.ajax({
		  	  type: 'GET',
		  	  url: 'validateCurrentOrderDetailLine_TransportDisp.do',
		  	  data: { applicationUser : jq('#applicationUser').val(), 
		  		  	  requestString : requestString,
		  		  	  lineNr : null },
		  	  dataType: 'json',
		  	  cache: false,
		  	  contentType: 'application/json',
		  	  async: false, //only way to make synchronous calls. Otherwise will all ajax dependent functions will execute asynchronously
		  	  success: function(data) {
		  		var len = data.length;
		  		for ( var j = 0; j < len; j++) {
		  			//we send the redirect after a successfull creation in order to refresh...
		  			//success code = 1
		  			if(data[j].errMsg!=null && data[j].errMsg!=''){
		  				//alert(data[i].errMsg);
		  				var errorPrefix = "[ERROR] FATAL on new line:";
			  			jq('#orderLineErrMsgPlaceHolder').text(errorPrefix + " -->" + data[j].errMsg);
			  			jq('#fvvkt').focus(); //always to weight column otherwise we lose control
		  			}else{
		  				jq('#orderLineErrMsgPlaceHolder').text("");
		  				//here we take care of the parameters that will complete some values
		  				if(jq('#fvlm').val()==''){ jq('#fvlm').val(data[j].fvlm); }
		  				if(jq('#fvlm2').val()==''){ jq('#fvlm2').val(data[j].fvlm2); }
		  				//trigger local function to keep in sync the math
		  				retval = true;
		  						  				
		  			}	
		  		}
		  	  },
		  	  error: function() {
			  	  alert('Error loading ...');
		  	  }
		  });
	  }else{
		  alert("[ERROR] missing mandatory fields for new line...");
		  if(ant==''){
			  jq('#fvant').focus();  
		  }else if (weight==''){
			  jq('#fvvkt').focus();
		  }else if (description==''){
			  jq('#fvvt').focus();
		  }
	  }
	  return retval;
  }
  
  //-------------------------------------------------------
  //Dangerous goods child window (is triggered from jsp)
  //-------------------------------------------------------
  function searchDangerousGoods(element) {
	  var id = element.id;
	  var record = id.split('_');
	  var i = record[1]; 
	  //alert(jq('#ffunnr_' + counter).val());
	  jq(id).attr('target','_blank');
  	  window.open('transportdisp_workflow_childwindow_dangerousgoods.do?action=doFind&unnr=' + jq("#ffunnr_" + i).val() + 
  			  '&embg=' + jq("#ffembg_" + i).val() + '&indx=' + jq("#ffindx_" + i).val() + '&callerLineCounter=' + i, 
  			  "dangerousgoodsWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  }
  
  function searchDangerousGoodsNewLine(element) {
	  jq(element.id).attr('target','_blank');
  	  window.open('transportdisp_workflow_childwindow_dangerousgoods.do?action=doFind&unnr=' + jq("#ffunnr").val() + 
  			  '&embg=' + jq("#ffembg").val() + '&indx=' + jq("#ffindx").val() + '&callerLineCounter=', 
  			  "dangerousgoodsWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  }
  //--------------------------------------------------------------
  //Dangerous goods validation in order to demand the indx or not
  //--------------------------------------------------------------
  function validateDangerousGoodsUnnr(lineNr) {
	  var counter = Number(lineNr);
	  var keyUnnr =jq("#ffunnr_" + counter).val();
	  if(keyUnnr!=""){
		  if(jq("#ffembg_" + counter).val()=="?" ){
			  jq("#ffembg_" + counter).val("");
		  }
		  if(jq("#ffindx_" + counter).val()=="?" ){
			  jq("#ffindx_" + counter).val("");
		  }
		  
		  jq.ajax({
		  	  type: 'GET',
		  	  url: 'searchDangerousGoods_TransportDisp.do',
		  	  data: { applicationUser : jq('#applicationUser').val(),
			  		  unnr : jq("#ffunnr_" + counter).val(),
		  		  	  embg : jq("#ffembg_" + counter).val() ,
		  		  	  indx : jq("#ffindx_" + counter).val()  },
		  	  dataType: 'json',
		  	  cache: false,
		  	  contentType: 'application/json',
		  	  success: function(data) {
		  		var len = data.length;
		  		for ( var i = 0; i < len; i++) {
		  			if(len>1){
		  				if(jq("#ffembg_" + counter).val()==''){ 
		  					//jq("#ffembg_" + counter).val("?");
		  					jq("#ffembg_" + counter).addClass( "isa_warning" );
	  					}
		  				//jq("#ffindx_" + counter).val("?");
		  				jq("#ffindx_" + counter).addClass( "isa_warning" );
		  				jq("#ffunnr_" + counter).removeClass( "isa_error" );
		  				jq("#ffpoen_" + counter).val("");
		  				
		  			}else if (len==1){
		  				jq("#ffunnr_" + counter).val(data[i].adunnr);
		  				jq("#ffembg_" + counter).val(data[i].adembg);
		  				jq("#ffindx_" + counter).val(data[i].adindx);
		  				//[1] ADR->Update line and line ADR
		  				if(jq("#ffante_" + counter).val()!='' && jq("#ffante_" + counter).val()!='?'){
		  					//var unit = parseInt(jq("#ffante_" + counter).val()); //OBSOLETE -->ffante as Integer
		  					var unitStr = jq("#ffante_" + counter).val();
		  					unitStr = unitStr.replace(",",".");
		  					var unit = Number(unitStr);
		  					var fakt = parseInt(data[i].adfakt);
		  					if(jq("#ffantk_" + counter).val()!='' && jq("#ffantk_" + counter).val()!='?' && jq("#ffenh_" + counter).val()!=''){
		  						jq("#ffpoen_" + counter).val(unit * fakt);
			  					//cosmetics
			  					jq("#ffantk_" + counter).removeClass( "isa_warning" );
			  					jq("#ffante_" + counter).removeClass( "isa_warning" );
		  					}else{
		  						jq("#ffpoen_" + counter).val("");
		  						if(jq("#ffantk_" + counter).val()==''){
		  							//jq("#ffantk_" + counter).val("?");
		  							jq("#ffantk_" + counter).addClass( "isa_warning" );
		  						}
		  					}
		  					
		  				}else{
		  					jq("#ffpoen_" + counter).val("");
		  					//jq("#ffante_" + counter).val("?");
	  						jq("#ffante_" + counter).addClass( "isa_warning" );
		  				}
		  				//[2] ADR->Update always total ADR to keep it in sync
	  					private_sumAdr();
	  					
		  				//cosmetics
		  				jq("#ffunnr_" + counter).removeClass( "isa_error" );
		  				jq("#ffembg_" + counter).removeClass( "isa_error isa_warning" );
		  				jq("#ffindx_" + counter).removeClass( "isa_error isa_warning" );
		  			}
		  		}
		  		//if invalid number acknowledge this...
		  		if(len<=0){
		  			//cosmetics
	  				jq("#ffunnr_" + counter).addClass( "isa_error" );
	  				if(jq("#ffembg_" + counter).val()!='') { jq("#ffembg_" + counter).addClass( "isa_error" ); }
	  				if(jq("#ffindx_" + counter).val()!='') { jq("#ffindx_" + counter).addClass( "isa_error" ); }
	  				jq("#ffpoen_" + counter).val("");
	  			}
		  	  },
		  	  error: function() {
			  	    alert('Error loading on Ajax callback (?)...check js');
		  	  }
		  });
		  
	  }else{
		  jq("#ffunnr_" + counter).val("");jq("#ffembg_" + counter).val("");jq("#ffindx_" + counter).val("");
		  jq("#ffantk_" + counter).val("");jq("#ffante_" + counter).val("");jq("#ffenh_" + counter).val("");
		  jq("#ffpoen_" + counter).val("");
		  //cosmetics
		  jq("#ffunnr_" + counter).removeClass( "isa_error" );
		  jq("#ffembg_" + counter).removeClass( "isa_error isa_warning" );jq("#ffindx_" + counter).removeClass( "isa_error isa_warning" );
		  jq("#ffantk_" + counter).removeClass( "isa_error isa_warning" );jq("#ffante_" + counter).removeClass( "isa_error isa_warning" );
		  jq("#ffenh_" + counter).removeClass( "isa_error isa_warning" );
	  }
  }
  
//--------------------------------------------------------------
  //Dangerous goods validation in order to demand the indx or not
  //--------------------------------------------------------------
  function validateDangerousGoodsUnnrNewLine() {
	  var keyUnnr =jq("#ffunnr").val();
	  if(keyUnnr!=""){
		  if(jq("#ffembg").val()=="?" ){
			  jq("#ffembg").val("");
		  }
		  if(jq("#ffindx").val()=="?" ){
			  jq("#ffindx").val("");
		  }
		  jq.ajax({
		  	  type: 'GET',
		  	  url: 'searchDangerousGoods_TransportDisp.do',
		  	  data: { applicationUser : jq('#applicationUser').val(),
			  		  unnr : jq("#ffunnr").val(),
		  		  	  embg : jq("#ffembg").val() ,
		  		  	  indx : jq("#ffindx").val()  },
		  	  dataType: 'json',
		  	  cache: false,
		  	  contentType: 'application/json',
		  	  success: function(data) {
		  		var len = data.length;
		  		for ( var i = 0; i < len; i++) {
		  			if(len>1){
		  				if(jq("#ffembg").val()==''){ 
		  					//jq("#ffembg").val("?");
		  					jq("#ffembg").addClass( "isa_warning" );
	  					}
		  				//jq("#ffindx").val("?");
		  				jq("#ffindx").addClass( "isa_warning" );
		  				jq("#ffunnr").removeClass( "isa_error" );
		  				
		  			}else if (len==1){
		  				jq("#ffunnr").val(data[i].adunnr);
		  				jq("#ffembg").val(data[i].adembg);
		  				jq("#ffindx").val(data[i].adindx);
		  				//[1] ADR->get the ADR factor
		  				if(jq("#ffante").val()!='' && jq("#ffante").val()!='?'){
		  					if(jq("#ffantk").val()!='' && jq("#ffantk").val()!='?' && jq("#ffenh").val()!=''){
		  						jq("#ownAdrFaktNewLine").val(data[i].adfakt);
			  					//TODO Tentative ?
		  						//var hepoen = Number(jq("#hepoen").val());
			  					//hepoen = hepoen + (unit * fakt);
			  					//Update total ADR. Note: notice that this is the only update in ADR-Total. When NEW LINE...
			  					//jq("hepoen").val(hepoen);
			  					
			  					//cosmetics
			  					jq("#ffantk").removeClass( "isa_warning" );
			  					jq("#ffante").removeClass( "isa_warning" );
		  					}else{
		  						jq("#ownAdrFaktNewLine").val("");
		  						if(jq("#ffantk").val()==''){
		  							//jq("#ffantk").val("?");
		  							jq("#ffantk").addClass( "isa_warning" );
		  						}
		  					}
		  					
		  				}else{
		  					jq("#ownAdrFaktNewLine").val("");
		  					//jq("#ffante").val("?");
	  						jq("#ffante").addClass( "isa_warning" );
		  				}
		  				//cosmetics
		  				jq("#ffunnr").removeClass( "isa_error" );
		  				jq("#ffembg").removeClass( "isa_error isa_warning" );
		  				jq("#ffindx").removeClass( "isa_error isa_warning" );
		  			}
		  		}
		  		//if invalid number acknowledge this...
		  		if(len<=0){
		  			//cosmetics
	  				jq("#ffunnr").addClass( "isa_error" );
	  				if(jq("#ffembg").val()!='') { jq("#ffembg").addClass( "isa_error" ); }
	  				if(jq("#ffindx").val()!='') { jq("#ffindx").addClass( "isa_error" ); }
	  				jq("#ownAdrFaktNewLine").val("");
	  			}
		  	  },
		  	  error: function() {
			  	    alert('Error loading on Ajax callback (?)...check js');
		  	  }
		  });
		  
	  }else{
		  jq("#ffunnr").val("");jq("#ffembg").val("");jq("#ffindx").val("");
		  jq("#ffantk").val("");jq("#ffante").val("");jq("#ffenh").val("");
		  //cosmetics
		  jq("#ffunnr").removeClass( "isa_error" );
		  jq("#ffembg").removeClass( "isa_error isa_warning" );jq("#ffindx").removeClass( "isa_error isa_warning" );
		  jq("#ffantk").removeClass( "isa_error isa_warning" );jq("#ffante").removeClass( "isa_error isa_warning" );
		  jq("#ffenh").removeClass( "isa_error isa_warning" );
		  
	  }
  }
  
  
  //-------------------------------------------------------
  //Packing codes onBlur / child window (is triggered from jsp)
  //-------------------------------------------------------
  function searchPackingCodesOnBlur(element) {
	  var id = element.id;
	  var record = id.split('_');
	  var counter = record[1];
	  var codeId = jq("#fvpakn_" + counter).val();
	  jq.ajax({
	  	  type: 'GET',
	  	  url: 'searchPackingCodes_TransportDisp.do',
	  	  data: { applicationUser : jq('#applicationUser').val(),
		  		  kode : codeId },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
	  		for ( var i = 0; i < len; i++) {
	  			if(jq("#fvvt_" + counter).val() == ''){
	  				jq("#fvvt_" + counter).val(data[i].entext);
	  			}
	  			if(jq("#fvlen_" + counter).val() == ''){
	  				jq("#fvlen_" + counter).val(data[i].enlen);
	  			}
	  			if(jq("#fvbrd_" + counter).val() == ''){
	  				jq("#fvbrd_" + counter).val(data[i].enbrd);
	  			}
	  			if(jq("#fvhoy_" + counter).val() == ''){
	  				jq("#fvhoy_" + counter).val(data[i].enhoy);
	  			}
	  			if(jq("#fvlm_" + counter).val() == ''){
	  				jq("#fvlm_" + counter).val(data[i].enlm);
	  			}
	  			if(jq("#fvlm2_" + counter).val() == ''){
	  				jq("#fvlm2_" + counter).val(data[i].enlm2);
	  			}
	  		}
	  	  }
	  });
  }	
  //new line
  function searchPackingCodesNewLineOnBlur(element) {
	  var codeId = jq("#fvpakn").val();
	  
	  jq.ajax({
	  	  type: 'GET',
	  	  url: 'searchPackingCodes_TransportDisp.do',
	  	  data: { applicationUser : jq('#applicationUser').val(),
		  		  kode : codeId },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
	  		for ( var i = 0; i < len; i++) {
	  			
	  			if(jq("#fvvt").val() == ''){
	  				jq("#fvvt").val(data[i].entext);
	  			}
	  			if(jq("#fvlen").val() == ''){
	  				jq("#fvlen").val(data[i].enlen);
	  			}
	  			if(jq("#fvbrd").val() == ''){
	  				jq("#fvbrd").val(data[i].enbrd);
	  			}
	  			if(jq("#fvhoy").val() == ''){
	  				jq("#fvhoy").val(data[i].enhoy);
	  			}
	  			if(jq("#fvlm").val() == ''){
	  				jq("#fvlm").val(data[i].enlm);
	  			}
	  			if(jq("#fvlm2").val() == ''){
	  				jq("#fvlm2").val(data[i].enlm2);
	  			}
	  			
	  		}
	  	  }
	  });
  }	
  function searchPackingCodes(element) {
	  var id = element.id;
	  var record = id.split('_');
	  var i = record[1]; 
	  //alert(jq('#fvpakn_' + counter).val());
	  jq(id).attr('target','_blank');
  	  window.open('transportdisp_workflow_childwindow_packingcodes.do?action=doFind&kode=' + jq("#fvpakn_" + i).val() + '&callerLineCounter=' + i, 
  			  "packingCodesWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  }
  
  function searchPackingCodesNewLine(element) {
	  jq(element.id).attr('target','_blank');
  	  window.open('transportdisp_workflow_childwindow_packingcodes.do?action=doFind&kode=' + jq("#fvpakn").val() + '&callerLineCounter=', 
			  "packingCodesWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  }

  
  //-----------------------
  // FORM SUBMIT
  //-----------------------
  jq(function () {
	  //FORM submit 
	  jq( "#submit" ).click(function( event ) {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
		  
		  //[1] sum all totals (in case)
		  private_updateOrderLineTotalsBeforeSubmit();
		  
		  //[2] execute som events
		  if(jq( "#hesdf" ).val()=='' && jq( "#helka" ).val()!=''){
			  jq( "#hesdf" ).focus();
			  jq( "#hesdf" ).blur();
		  }
		  if(jq( "#hesdt" ).val()=='' && jq( "#hetri" ).val()!=''){
			  jq( "#hesdt" ).focus();
			  jq( "#hesdt" ).blur();
		  }
		 
		  
	  });
	  //FORM submit (NEW ORDER) 
	  jq( "#submitnew" ).click(function( event ) {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
		  
		  //[1] sum all totals (in case)
		  private_updateOrderLineTotalsBeforeSubmit();
		  
		  //[2] execute som events
		  if(jq( "#hesdf" ).val()=='' && jq( "#helka" ).val()!=''){
			  jq( "#hesdf" ).focus();
			  jq( "#hesdf" ).blur();
		  }
		  if(jq( "#hesdt" ).val()=='' && jq( "#hetri" ).val()!=''){
			  jq( "#hesdt" ).focus();
			  jq( "#hesdt" ).blur();
		  }
	  });
	  
  });
  
  //------------------
  //DELETE order line
  //------------------
  function deleteOrderLine(element){
	  var id = element.id;
	  var record = id.split('_');
	  var counter = record[1]; 
	  var r = confirm("Are you sure you want to remove this order line?");
	  if (r == true){
		  updateOrderLineTotalsBeforeDelete(counter);
		  var params = "heavd=" + jq('#heavd').val() + "&heopd=" + jq('#heopd').val() + 
						"&lin=" + jq('#fvlinr_' + counter).val() + "&hent=" + jq('#hent').val() + "&hevkt=" + jq('#hevkt').val() + 
						"&hem3=" + jq('#hem3').val() + "&helm=" + jq('#helm').val() + "&helmla=" + jq('#helmla').val() + "&hepoen=" + jq('#hepoen').val();
		  				//append the protect checkbox value (if applicable)
						if(jq('#hestl4').prop('checked')){ params += "&hestl4=" + jq('#hestl4').val();}
						else{ params += "&hestl4="; }
						//shoot now to the controller!
						window.location = "transportdisp_mainorder_delete_order_line.do?" + params;
	  }else{
		  //nothing
	  }
  }
  //--------------------------------
  // UPDATE before DELETE ORDER LINE
  //--------------------------------
  function updateOrderLineTotalsBeforeDelete(counter_delete) { 
      //Antall
	  var sum = 0;
	  jq( ".clazzAntMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvant_' + counter).val();
		  if(value!='' && counter!=counter_delete){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#hent').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //Weight
	  var sum = 0;
	  jq( ".clazzWeightMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvvkt_' + counter).val();
		  if(value!='' && counter!=counter_delete){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#hevkt').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //M3
	  var sum = 0;
	  jq( ".clazzVolMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvvol_' + counter).val();
		  if(value!='' && counter!=counter_delete){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#hem3').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //LM
	  var sum = 0;
	  jq( ".clazzLmMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvlm_' + counter).val();
		  if(value!='' && counter!=counter_delete){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#helm').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //LM-la
	  var sum = 0;
	  jq( ".clazzLmlaMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvlm2_' + counter).val();
		  if(value!='' && counter!=counter_delete){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#helmla').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //ADR
	  var sum = 0;
	  jq( ".clazzAdrMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#ffpoen_' + counter).val();
		  if(value!='' && counter!=counter_delete){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  //this ADR-field in NOT REQUIRED to be blocked by Protected checkbox: hestl4
	  jq("#hepoen").attr("readonly", false); 
	  jq('#hepoen').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  
  }
  
  
  //-----------------------------
  //UPDATE before ADD ORDER LINE
  //-----------------------------
  function updateOrderLineTotalsBeforeAdd() { 
      //Antall
	  var sum = 0;
	  jq( ".clazzAntMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvant_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  var fvant = jq('#fvant').val();
		  if(fvant!=''){ fvant = fvant.replace(",","."); sum += Number(fvant); }
		  jq('#hent').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //Weight
	  var sum = 0;
	  jq( ".clazzWeightMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvvkt_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  var fvvkt = jq('#fvvkt').val();
		  if(fvvkt!=''){ fvvkt = fvvkt.replace(",","."); sum += Number(fvvkt); }
		  jq('#hevkt').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //M3
	  var sum = 0;
	  jq( ".clazzVolMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvvol_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  var fvvol = jq('#fvvol').val();
		  if(fvvol!=''){ fvvol = fvvol.replace(",","."); sum += Number(fvvol); }
		  jq('#hem3').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //LM
	  var sum = 0;
	  jq( ".clazzLmMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvlm_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  var fvlm = jq('#fvlm').val();
		  if(fvlm!=''){ fvlm = fvlm.replace(",","."); sum += Number(fvlm); }
		  jq('#helm').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //LM-la
	  var sum = 0;
	  jq( ".clazzLmlaMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvlm2_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  var fvlm2 = jq('#fvlm2').val();
		  if(fvlm2!=''){ fvlm2 = fvlm2.replace(",","."); sum += Number(fvlm2); }
		  jq('#helmla').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //ADR
	  var sum = 0;
	  jq( ".clazzAdrMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#ffpoen_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  //Update the ADR-sum with
	  /* OLD With ffante as Integer
	  var unitStr = jq('#ffante').val();
	  var faktStr = jq('#ownAdrFaktNewLine').val();
	  if(unitStr!='' && faktStr!=''){
		  unit = parseInt(unitStr);
		  fakt = parseInt(faktStr);
		  sum += (unit*fakt);
	  }*/
	  var unitStr = jq('#ffante').val();
	  var faktStr = jq('#ownAdrFaktNewLine').val();
	  if(unitStr!='' && faktStr!=''){
		  unitStr = unitStr.replace(",",".");
		  unit = Number(unitStr);
		  fakt = parseInt(faktStr);
		  sum += (unit*fakt);
	  }
	  
	  //ADR field NOT REQUIRED to be blocked by checkbox: hestl4
	  jq("#hepoen").attr("readonly", false); 
	  jq('#hepoen').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  
  }
  
  //UPDATE before SUBMIT Vareslag - TOT
  //Usually if the field hasn't been populated earlier by other jQuery functions on Vareslag
  function private_isSingleOrderLine() {
	  //element.id;
	  var sum = 0;
	  var isTrue = true;
	  jq( ".clazzVareslagAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var fvant = jq('#fvant_' + counter).val();
		  var fvvt = jq('#fvvt_' + counter).val();
		  var fvvkt = jq('#fvvkt_' + counter).val();
		  
		  if(counter>1 && (fvant!='' && fvvt!='' && fvvkt!='')){
			 isTrue = false;  
		  }
		  
	  });
	  
	  return isTrue;
	  
  }
  
  //-----------------------------
  //UPDATE before SUBMIT (Save)
  //-----------------------------
  function private_updateOrderLineTotalsBeforeSubmit() { 
      //Antall
	  var sum = 0;
	  jq( ".clazzAntMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvant_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){	
		  jq('#hent').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  
	  //check Vareslag
	  if(private_isSingleOrderLine()){
		  if(jq("#hevs1").val()==''){
			  var hevs1 = jq("#fvpakn_1").val()  + " " +  jq("#fvvt_1").val();
			  jq("#hevs1").val(hevs1);
		  }
	  }
	  
	  //Weight
	  var sum = 0;
	  jq( ".clazzWeightMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvvkt_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#hevkt').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //M3
	  var sum = 0;
	  jq( ".clazzVolMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvvol_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#hem3').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //LM
	  var sum = 0;
	  jq( ".clazzLmMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvlm_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#helm').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //LM-la
	  var sum = 0;
	  jq( ".clazzLmlaMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#fvlm2_' + counter).val();
		  if(value!=''){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  if(!jq('#hestl4').prop('checked')){
		  jq('#helmla').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  }
	  //ADR
	  var sum = 0;
	  jq( ".clazzAdrMathAware" ).each(function( i ) {
		  var id = this.id;
		  var counter = i + 1;
		  var value = jq('#ffpoen_' + counter).val();
		  if(value!='' && counter!=counter_delete){
			  value = value.replace(",",".");
			  sum += Number(value);
		  }
	  });
	  //this ADR-field in NOT REQUIRED to be blocked by Protected checkbox: hestl4
	  jq("#hepoen").attr("readonly", false); 
	  jq('#hepoen').val(sum.toLocaleString('de-DE', { useGrouping: false }));
	  
  }
 
  
  
//-----------------------------
  //START Model dialog: "DUP"
  //---------------------------
  //Initialize <div> here
  jq(function() { 
	  //events before the dialog is created/opened
	  jQuery("#dialogDup").on("dialogopen", function (event, ui) {
		  if(jq("#helks").val() == '' || jq("#hesdff").val() == '' ){
			  jq("#ffavd").attr("readonly", true); 
			  jq("#ffoty").attr("disabled", true); 
			  jq("#fffrank").attr("disabled", true); 
			  jq("#ffftxt").attr("readonly", true); 
			  jq("#ffmodul").attr("readonly", true); 
			  jq("#ffpkod").attr("readonly", true); 
			  jq("#ffbel").attr("readonly", true); 
			  jq("#ffbelk").attr("readonly", true); 
			  jq("#ffbnr").attr("readonly", true); 
			  jq("#fftran").attr("readonly", true);
			  jq("#ffkomm").attr("readonly", true);
		  }else{
			  jq("#ffavd").attr("readonly", false);
			  jq("#ffoty").attr("disabled", false); 
			  jq("#fffrank").attr("disabled", false); 
			  jq("#ffftxt").attr("readonly", false); 
			  jq("#ffmodul").attr("readonly", false); 
			  jq("#ffpkod").attr("readonly", false); 
			  jq("#ffbel").attr("readonly", false); 
			  jq("#ffbelk").attr("readonly", false); 
			  jq("#ffbnr").attr("readonly", false); 
			  jq("#fftran").attr("readonly", false);
			  jq("#ffkomm").attr("readonly", false);
		  }
		  if(jq("#helkk").val() == '' || jq("#hesdvt").val() == '' ){
			  jq("#vfavd").attr("readonly", true); 
			  jq("#vfoty").attr("disabled", true); 
			  jq("#vffrank").attr("disabled", true); 
			  jq("#vfftxt").attr("readonly", true); 
			  jq("#vfmodul").attr("readonly", true); 
			  jq("#vfpkod").attr("readonly", true); 
			  jq("#vfbel").attr("readonly", true); 
			  jq("#vfbelk").attr("readonly", true); 
			  jq("#vfbnr").attr("readonly", true); 
			  jq("#vftran").attr("readonly", true);
			  jq("#vfkomm").attr("readonly", true);
		  }else{
			  jq("#vfavd").attr("readonly", false);
			  jq("#vfoty").attr("disabled", false); 
			  jq("#vffrank").attr("disabled", false); 
			  jq("#vfftxt").attr("readonly", false); 
			  jq("#vfmodul").attr("readonly", false); 
			  jq("#vfpkod").attr("readonly", false); 
			  jq("#vfbel").attr("readonly", false); 
			  jq("#vfbelk").attr("readonly", false); 
			  jq("#vfbnr").attr("readonly", false); 
			  jq("#vftran").attr("readonly", false);
			  jq("#vfkomm").attr("readonly", false);
		  }
	  });
	  jq("#dialogDup").dialog({
		  autoOpen: false,
		  maxWidth:550,
          maxHeight: 650,
          width: 520,
          height: 640,
		  modal: true,
		  dialogClass: 'main-dialog-class',
		  //the form must be appended otherwise the default jQuey dialog behavior (leave the dialog outside the form) will take place...=(
		  appendTo: "#transportdispForm"
		  
	  });
	  jq("#dialogDupReadOnly").dialog({
		  autoOpen: false,
		  maxWidth:550,
          maxHeight: 650,
          width: 520,
          height: 640,
		  modal: true,
		  dialogClass: 'main-dialog-class',
		  //the form must be appended otherwise the default jQuey dialog behavior (leave the dialog outside the form) will take place...=(
		  //appendTo: "#transportdispForm"
		  
	  });
  });
  
  //----------------------------
  //Present dialog box onClick 
  //----------------------------
  jq(function() {
	  //Via 1
	  jq("#viaFromDialogImg").click(function() {
		  if(mandatoryViaFromFieldsForDupDialog()){
			  presentDupDialog();
	  	  }else{
	  		renderViaAlert();
	  	  }
	  });
	  //Read only dialog
	  jq("#viaFromDialogImgReadOnly").click(function() {
		  presentDupDialogReadOnly();
	  });
	 
	  //Via 2
	  jq("#viaFrom2DialogImg").click(function() {
		  if(mandatoryViaToFieldsForDupDialog()){
			  presentDupDialog();
	  	  }else{
	  		  renderViaAlert();
	  	  }
	  });
	  //Read-only dialog
	  jq("#viaFrom2DialogImgReadOnly").click(function() {
		  presentDupDialogReadOnly();
	  });
  });
  function mandatoryViaFromFieldsForDupDialog(){
	  var status = false;
	  if(jq("#helks").val() != '' && jq("#hesdff").val() != ''){
		//check if the hesdvt was a valid number (returning a korrekt string ...
		  if(jq("#OWNwppns3").val() != '?' ){
			  status = true;
		  }
	  }
	  return  status;
  }
  function mandatoryViaToFieldsForDupDialog(){
	  var status = false;
	  if(jq("#helkk").val() != '' && jq("#hesdvt").val() != ''){
		  //check if the hesdvt was a valid number (returning a korrekt string ...
		  if(jq("#OWNwppns4").val() != '?' ){
			  status = true;
		  }
	  }
	  return status;
  }
  function renderViaAlert(){
	  alert("Du må fylle ut Via-felten først...");
  }
  

  //---------------------
  //PRESENT DUP DIALOG
  //---------------------
  function presentDupDialog(){
	//setters (add more if needed)
	  jq('#dialogDup').dialog( "option", "title", "DUP / Rekvisisjon" );
	  //deal with buttons for this modal window
	  jq('#dialogDup').dialog({
		 buttons: [ 
            {
			 id: "dialogSaveTU",	
			 text: "Fortsett",
			 click: function(){
				 		if(!isValidViaFromAvd()){
				 			alert("Forfrakt: Via-avd er obligatorisk");
				 		}else if(!isValidViaToAvd()){
				 			alert("Viderefrakt: Via-avd er obligatorisk");
				 		}else{
				 			jq( this ).dialog( "close" );
				 		}
				 		
			 		}
		 	 } ]
	  });
	  //open now
	  jq('#dialogDup').dialog('open');
  }
  //check for DUP-dialog if there is any mandatory requirement
  function isValidViaFromAvd(){
	  var retval = true;
	  if(jq("#ffavd").val() == ''){
		  if(jq("#helks").val() != '' && jq("#hesdff").val() != '' ){
			  retval = false;
		  }
	  }
	  return retval;
  }
  function isValidViaToAvd(){
	  var retval = true;
	  if(jq("#vfavd").val() == ''){
		  if(jq("#helkk").val() != '' && jq("#hesdvt").val() != '' ){
			  retval = false;
		  }
	  }
	  return retval;
  }
 
  //DUP read-only dialog
  function presentDupDialogReadOnly(){
	//setters (add more if needed)
	  jq('#dialogDupReadOnly').dialog( "option", "title", "DUP / Rekvisisjon" );
	  //deal with buttons for this modal window
	  jq('#dialogDupReadOnly').dialog({
		 buttons: [ 
            {
			 id: "dialogSaveTU",	
			 text: "Lukk",
			 click: function(){
				 		jq( this ).dialog( "close" );
		 			}
		 	 } ]
	  });
	  //open now
	  jq('#dialogDupReadOnly').dialog('open');
  }
  
  //-------------------------------------------
  //END Model dialog: "DUP"
  //-------------------------------------------

  
  
  
  //-----------------------------
  //START Model dialog: "SMS"
  //---------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogSMS").dialog({
		  autoOpen: false,
		  maxWidth:400,
          maxHeight: 250,
          width: 360,
          height: 230,
		  modal: true,
		  dialogClass: 'main-dialog-class'
	  });
  });

  jq(function() {
	  jq("#smsButton").click(function() {
		  presentSmsDialog();
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
	  	  url: 'sendSMS_TransportDisp.do',
	  	  data: { applicationUser : jq('#applicationUser').val(),
	  		  	  avd : jq("#heavd").val(),
	  		  	  opd : jq("#heopd").val(),
		  		  smsnr : jq("#smsnr").val() },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
	  		
	  		for ( var i = 0; i < len; i++) {
	  			if(data[i].errMsg != ''){
	  				jq("#smsStatus").removeClass( "isa_success" );
	  				jq("#smsStatus").addClass( "isa_error" );
	  				jq("#smsStatus").text("SMS error: " + data[i].smsnr + " " + data[i].errMsg);
	  			}else{
	  				jq("#smsStatus").removeClass( "isa_error" );
	  				jq("#smsStatus").addClass( "isa_success" );
	  				jq("#smsStatus").text("SMS er sendt ti" + data[i].smsnr + " (loggført i Hendelsesloggen)");
	  			}
	  		}
	  	  },
	  	  error: function() {
	  	    alert('Error loading on Ajax callback (?) sendSMS...check js');
	  	  }
	  });
  }	

