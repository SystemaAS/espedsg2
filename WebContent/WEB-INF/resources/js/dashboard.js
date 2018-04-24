	//===========================================
	//General functions for Dashboard - AJAX
	//===========================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
    var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
    function setBlockUI(element){
  	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    }
  	
    jq(function() { 
    	jq("td#dashItem_roadmap").bind("click",function() {
    		window.location = "aespedsg_roadmap.do";
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	jq("td#dashItem_custMatrix").bind("click",function() {
    		window.location = "espedsgadmin.do";
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    		
	    });
    	jq("td#dashItem_Vedlikehold").bind("click",function() {
    		window.location = "mainmaintenancegate.do?lang=" + jq("#usrLang").val();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    		
	    });
    	jq("td#dashItem_Sporroppdrag").bind("click",function() {
    		window.location = "sporringoppdraggate.do?lang=" + jq("#usrLang").val();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    		
	    });
    	jq("td#dashItem_Testsuites").bind("click",function() {
    		window.location = "aespedsgtestersuite.do?lang=" + jq("#usrLang").val();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    		
	    });
    	jq("td#dashItem_Transpdisp").bind("click",function() {
    		window.location = "transportdisp_mainorderlist.do?lang=" + jq("#usrLang").val() + "&action=doFind";
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    		
	    });
    	jq("td#dashItem_Kvalitet").bind("click",function() {
    		jq("#dashForm_Kvalitet").submit();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	jq("td#dashItem_Ufortollede").bind("click",function() {
    		jq("#dashForm_Ufortollede").submit();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	jq("td#dashItem_Tds").bind("click",function() {
    		jq("#dashForm_Tds").submit();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	jq("td#dashItem_Skat").bind("click",function() {
    		jq("#dashForm_Skat").submit();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    		
	    });
    	jq("td#dashItem_Tvinn").bind("click",function() {
    		jq("#dashForm_Tvinn").submit();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	jq("td#dashItem_Tvinnavgg").bind("click",function() {
    		jq("#dashForm_Tvinnavgg").submit();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	jq("td#dashItem_Priskalk").bind("click",function() {
    		jq("#dashForm_Priskalk").submit();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	jq("td#dashItem_Efaktura").bind("click",function() {
    		jq("#dashForm_Efaktura").submit();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	jq("td#dashItem_Tror").bind("click",function() {
    		jq("#dashForm_Tror").submit();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	jq("td#dashItem_Ebooking").bind("click",function() {
    		jq("#dashForm_Ebooking").submit();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	jq("td#dashItem_StatsTrafikk").bind("click",function() {
    		//to be developed TODO --->FM/JOVO
    		//jq("#dashForm_StatsTrafikk").submit();
    		//jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	jq("td#dashItem_StatsFortolling").bind("click",function() {
    		jq("#dashForm_StatsFortolling").submit();
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	
    	
    });
  	
	//call this with onClick() from an html-element
	function doPostMultiUser(element){
		var id = element.id;
		//already in the jsp: --> jq('#formMU_'+ id).append('<input type="hidden" name="user" value="oscar">');
		jq('#formMU_'+ id).append('<input type="hidden" name="password" value="mltid">');
		//
		jq('#formMU_'+ id).submit();
		
	/*	
	 * //call this with onClick() in <a>link (in case a user pass. is sent to another WAR file)
	   jq.post("logonDashboard.do", { user: "jovo", password: "mltid" },
		   function(data) {
	         window.location = "logonDashboard.do";
	       });	   

	   
	   not working --> jq.redirect('logonDashboard.do', {'user': 'oscar', 'pwd': 'mltid'});
	   */
	}
  
	  jq(function() { 
		  jq("#dialogRunKundedatakontroll").dialog({
			  autoOpen: false,
			  maxWidth:400,
	          maxHeight: 220,
	          width: 400,
	          height: 220,
			  modal: true
		  });
	  });
	

	  jq(function() {
		  jq("#dialogRunKundedatakontrollLink").click(function() {
			  //setters (add more if needed)
			  jq('#dialogRunKundedatakontroll').dialog( "option", "title", "Kør Kundedatakontroll" );
			  //deal with buttons for this modal window
			  jq('#dialogRunKundedatakontroll').dialog({
				 buttons: [ 
		            {
					 id: "dialogSaveTU",	
					 text: "Fortsett",
					 click: function(){
						 		jq('#runKundedatakontrollForm').submit();
						 		jq( this ).dialog( "close" );
						 		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
					 		}
				 	 },
		 	 		{
				 	 id: "dialogCancelTU",
				 	 text: "Avbryt", 
					 click: function(){
						 		jq( this ).dialog( "close" );
					 		} 
		 	 		 } ] 
			  });
			  jq('#dialogRunKundedatakontroll').dialog('open');
		  });
	  });

