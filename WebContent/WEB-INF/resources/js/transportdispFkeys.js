	//===========================================
	//General functions for tror landimport- AJAX
	//===========================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	 var jq = jQuery.noConflict();
  	 var counterIndex = 0;
  	 var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	 
  	 function setBlockUIKeys(){
  		 jq.blockUI({
  			css: { 
  	            fontSize:'22px'
  	        },
  			 message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT}
  		 );
  	 }
  	
  	 //KEY's shortcuts
  	 jq(document).keydown(function(evt) {
  		 var f1 = 112; 
  		 var f2 = 113; 
  		 var f3 = 114; 
  		 var f4 = 115; 
  		 var f5 = 116;
  		 var f6 = 117; 
  		 var f7 = 118; 
  		 var f8 = 119; 
  		 var f9 = 120; 
  		 var f10 = 121;
  		 var f11 = 122; 
  		 var f12 = 123;
  		 //
  		 var f13 = 124;
  		 var f24 = 135;
	  
  		 //check shiftKey (Apple machines do use this ...	
  		 if(event.shiftKey){
  			var charCode = (evt.which) ? evt.which : event.keyCode; 
  			//alert(charCode);
  			/* Edge has F24 as OS-command on browser
  			if (charCode == f12){ //shift + F12 = F24 --> in apple
  	  			 //More 
  	   			 setBlockUIKeys();
  	   			 window.location.href = "tror_mainorderlandimport_more.do?avd=" + jq('#fkeysavd').val() + "&sign=" + jq('#fkyessign').val() + "&opd=" + jq('#fkyesopd').val();	 
  			}*/
  			
  		 }else{
	  		 var charCode = (evt.which) ? evt.which : event.keyCode;
	  		 //debug ->
	  		 //alert(charCode);
	  		 if (charCode == f3){
	  			 //Orderlist
	  			setBlockUIKeys();
	  			window.location.href = "transportdisp_mainorderlist.do?action=doFind&avd=" + jq('#fkeysavd').val(); 
	  		 
	  		 }else if (charCode == f4){
	  			if( jq('#alinkInvoice').length ){ 
	  				setBlockUIKeys();
	  				window.location.href = "transportdisp_mainorder_invoice.do?action=doFind&hepro=" + jq('#fkeystur').val() + "&heavd=" + jq('#fkeysavd').val() + "&heopd=" + jq('#fkeysopd').val() + "&itemsType=O"
	  			}
	  		 }else if (charCode == f9){
		  			//Disponering turer
		  			setBlockUIKeys();
			   		window.location.href = "transportdisp_workflow_getTrip.do?user=" + jq('#applicationUser').val() + "&tuavd=" + jq('#fkeysavd').val() + "&tupro="
	  		 }else if (charCode == f10){
	  			//Disponering turer
	  			setBlockUIKeys();
		   		window.location.href = "transportdisp_mainorderlist.do?action=doFind&wssavd=" + jq('#fkeysavd').val() + "&wstur=" + jq('#fkeysturParent').val();
		   		 	
	  		 }/*else if (charCode == f24){ //Edge har detta som OS-command
  	  			 //More 
  	   			 setBlockUIKeys();
  	   			 window.location.href = "tror_mainorderlandimport_more.do?avd=" + jq('#fkeysavd').val() + "&sign=" + jq('#fkeyssign').val() + "&opd=" + jq('#fkeysopd').val();	 
  			}*/
  		 }	 
  	 });
  	