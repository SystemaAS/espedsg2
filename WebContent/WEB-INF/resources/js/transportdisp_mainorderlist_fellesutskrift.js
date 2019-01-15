	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
  	function setBlockUI(element){
		  jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  }
  	
  	jq(function() { 
  		jq("#wsdt1").datepicker({ 
  			dateFormat: 'ddmmy' 
  			//defaultDate: "-6m"	  
  		});
  		jq("#wsdt2").datepicker({ 
  			dateFormat: 'ddmmy' 
  			//defaultDate: "-7d"	  
  		});
  	});
  	
  //----------------------------------------
    //START Model dialog "Create new order"
    //--------------------------------------
    //Initialize <div> here
  	/*
    jq(function() { 
  	  jq("#dialogCreateNewOrder").dialog({
  		  autoOpen: false,
  		  maxWidth:400,
            maxHeight: 220,
            width: 300,
            height: 250,
  		  modal: true
  	  });
    });
    
    //Present dialog box onClick (href in parent JSP)
    jq(function() {
  	  jq("#createNewOrderTabIdLink").click(function() {
  		  
  		  //setters (add more if needed)
  		  jq('#dialogCreateNewOrder').dialog( "option", "title", "Lag ny Oppdrag" );
  		  
  		  //deal with buttons for this modal window
  		  jq('#dialogCreateNewOrder').dialog({
  			 buttons: [ 
  	            {
  				 id: "dialogSaveTU",	
  				 text: "Fortsett",
  				 click: function(){
  					 		jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  			 				jq('#createNewOrderForm').submit();
  				 		}
  			 	 },
  	 	 		{
  			 	 id: "dialogCancelTU",
  			 	 text: "Avbryt", 
  				 click: function(){
  					 		//back to initial state of form elements on modal dialog
  					 		//jq("#dialogSaveSU").button("option", "disabled", true);
  					 		
  					 		jq( this ).dialog( "close" );
  					 		jq("#opd").focus();
  				 		} 
  	 	 		 } ] 
  		  });
  		  //init values
  		  //jq("#dialogSaveTU").button("option", "disabled", false);
  		  //open now
  		  jq('#dialogCreateNewOrder').dialog('open');
  	  });
    });
    */
    //-----------------------------
    //END Create new order - Dialog
    //-----------------------------

  	
  	
  