	//===========================================
	//General functions for Dashboard - AJAX
	//===========================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
    var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
	
    function setBlockUI(element){
  	  jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    }
    
    function setBlockUI(){
    	  jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
     }
  	
    jq(function() { 
    	jq("#metricsIdLink").click(function() {
    		jq('#metricsIdLink').attr('target','_blank');
	    	window.open('renderTomcatMetrics.do', "TomcatMetricsWin", "top=300px,left=350px,height=700px,width=1000px,scrollbars=no,status=no,location=no");
    		//setBlockUI();
	    });
    	
    	jq("td#dashItem_VismaInt").bind("click",function() {
    		jq("#dashForm_VismaInt").submit();
    		setBlockUI();
	    });
    	jq("td#dashItem_roadmap").bind("click",function() {
    		window.location = "aespedsg_roadmap.do";
    		setBlockUI();
	    });
    	jq("td#dashItem_custMatrix").bind("click",function() {
    		window.location = "espedsgadmin.do";
    		setBlockUI();
    		
	    });
    	//Vedlikehold - Administrator
    	jq("td#dashItem_Vedlikehold").bind("click",function() {
    		jq("#dashForm_Vedlikehold").submit();
    		setBlockUI();
	    });
    	//Vedlikehold - User (only kundevedlikehold)
    	jq("td#dashItem_Vedlikehold_Restricted_kundevedlikehold").bind("click",function() {
    		jq("#dashForm_Vedlikehold_Restricted_kundevedlikehold").submit();
    		setBlockUI();
	    });
    	
    	
    	
    	
    	/*
    	jq("td#dashItem_Sporroppdrag").bind("click",function() {
    		window.location = "sporringoppdraggate.do?lang=" + jq("#usrLang").val();
    		setBlockUI();
    		
	    });*/
    	jq("td#dashItem_Sporroppdrag").bind("click",function() {
    		jq("#dashForm_Sporroppdrag").submit();
    		setBlockUI();
	    });
    	
    	jq("td#dashItem_Testsuites").bind("click",function() {
    		window.location = "aespedsgtestersuite.do?lang=" + jq("#usrLang").val();
    		setBlockUI();
    		
	    });
    	jq("td#dashItem_Tpmmonitor").bind("click",function() {
    		window.location = "aespedsgtpmmonitor.do?lang=" + jq("#usrLang").val();
    		setBlockUI();
    		
	    });
    	
    	
    	jq("td#dashItem_AltinnRunnersuites").bind("click",function() {
    		window.location = "altinnrunnersuite.do?lang=" + jq("#usrLang").val();
    		setBlockUI();
    		
	    });
    	
    	
    	/*
    	jq("td#dashItem_Transpdisp").bind("click",function() {
    		window.location = "transportdisp_mainorderlist.do?lang=" + jq("#usrLang").val() + "&action=doFind";
    		setBlockUI();
    		
	    });*/
    	jq("td#dashItem_Transpdisp").bind("click",function() {
    		jq("#dashForm_Transpdisp").submit();
    		setBlockUI();
	    });
    	
    	
    	jq("td#dashItem_Kvalitet").bind("click",function() {
    		jq("#dashForm_Kvalitet").submit();
    		setBlockUI();
	    });
    	jq("td#dashItem_Ufortollede").bind("click",function() {
    		jq("#dashForm_Ufortollede").submit();
    		setBlockUI();
	    });
    	jq("td#dashItem_Tds").bind("click",function() {
    		jq("#dashForm_Tds").submit();
    		setBlockUI();
	    });
    	jq("td#dashItem_Skat").bind("click",function() {
    		jq("#dashForm_Skat").submit();
    		setBlockUI();
    		
	    });
    	jq("td#dashItem_Tvinn").bind("click",function() {
    		jq("#dashForm_Tvinn").submit();
    		setBlockUI();
	    });
    	jq("td#dashItem_Tvinnavgg").bind("click",function() {
    		jq("#dashForm_Tvinnavgg").submit();
    		setBlockUI();
	    });
    	jq("td#dashItem_Priskalk").bind("click",function() {
    		jq("#dashForm_Priskalk").submit();
    		setBlockUI();
	    });
    	jq("td#dashItem_Efaktura").bind("click",function() {
    		jq("#dashForm_Efaktura").submit();
    		setBlockUI();
	    });
    	jq("td#dashItem_Tror").bind("click",function() {
    		jq("#dashForm_Tror").submit();
    		setBlockUI();
	    });
    	jq("td#dashItem_Ebooking").bind("click",function() {
    		jq("#dashForm_Ebooking").submit();
    		setBlockUI();
	    });
    	jq("td#dashItem_StatsTrafikk").bind("click",function() {
    		//to be developed TODO --->FM/JOVO
    		//jq("#dashForm_StatsTrafikk").submit();
    		//jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	    });
    	jq("td#dashItem_StatsFortolling").bind("click",function() {
    		jq("#dashForm_StatsFortolling").submit();
    		setBlockUI();
	    });
    	
    	jq("td#dashItem_GodsReg").bind("click",function() {
    		jq("#dashForm_GodsReg").submit();
    		setBlockUI();
	    });
  
    	jq("td#dashItem_Kostf").bind("click",function() {
    		jq("#dashForm_Kostf").submit();
    		setBlockUI();
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
			  modal: true,
			  dialogClass: 'main-dialog-class'
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
						 		jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
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
	  
	  //-------------------------------------
	  //START Model dialog: "Change Password"
	  //-------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogChangePwd").dialog({
			  autoOpen: false,
			  maxWidth:400,
	          maxHeight: 300,
	          width: 380,
	          height: 250,
			  modal: true,
			  dialogClass: 'main-dialog-class'
		  });
	  });

	  jq(function() {
		  //call model dialog
		  jq("#changePwdButton").click(function() {
			  presentChangePwdDialog();
		  });
	  });
	  
	  //init jquery plugin on password strength meter
	  function initPasswordStrength(){
		  //Config the password strength meter with the following options.
		  jq('#passwordNew').passtrength({
			  minChars: 5,
			  passwordToggle:true,
			  eyeImg :"resources/images/eye.svg", // toggle icon
			  tooltip:true,
			  textWeak:"Weak",
			  textMedium:"Medium",
			  textStrong:"Strong",
			  textVeryStrong:"Very Strong",
		  });
		 
	  }

	  //------------------------------
	  //PRESENT CHANGE PASSWORD DIALOG
	  //------------------------------
	  function presentChangePwdDialog(){
		  let PASS_MINIMUM_LENGTH = 5;
		  //init password strength plugin
		  initPasswordStrength();
		  //password strength plugin
		  jq('#passwordNew').passtrength();
		  
		  //setters (add more if needed)
		  jq('#dialogChangePwd').dialog( "option", "title", "Endre Passord" );
		  //deal with buttons for this modal window
		  jq('#dialogChangePwd').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Endre",
					 click: function(){
						  
						  if(jq('#passwordNew').val()!='' && jq('#passwordConfirm').val()!='' ){
							  if(jq('#passwordNew').val() .length >= PASS_MINIMUM_LENGTH && jq('#passwordConfirm').val().length >= PASS_MINIMUM_LENGTH){
								  jq('#password').removeClass('isa_error');
								  if(jq('#passwordNew').val() == jq('#passwordConfirm').val()){
									  jq("#validationLabelMessage").text("");
									  //cosmetics
									  jq('#passwordNew').removeClass('isa_error');
					 				  jq('#passwordConfirm').removeClass('isa_error');
									  //Submit
					 				  setBlockUI();
					 				  jq("#loginFormChgPwd").submit();
					 				  
								  }else{
									  jq("#validationLabelMessage").text("Passordene er forskjellige");
									  jq('#passwordNew').addClass('isa_error');
					 				  jq('#passwordConfirm').addClass('isa_error');
								  }
							  }else{
								  jq('#password').addClass('isa_error');
								  jq("#validationLabelMessage").text("Passordene er for korte");
							  }
						  }else{
			 				  jq("#validationLabelMessage").text("Alla felt må fylles ut");
			 				  jq('#passwordNew').addClass('isa_error');
			 				  jq('#passwordConfirm').addClass('isa_error');
			 			  }
					 }	
			 	 },
	  			{
			 	 id: "dialogCancelTU",
			 	 text: "Lukk", 
				 click: function(){
					 		//init password strength plugin
					  		initPasswordStrength();
					  		
					 		//back to initial state of form elements on modal dialog
					 		jq("#validationLabelMessage").text("");
					 		jq("#passwordNew").val("");
					 		jq("#passwordConfirm").text("");
					 		jq('#passwordNew').removeClass('isa_error');
					 		jq('#passwordConfirm').removeClass('isa_error');
					 		jq.unblockUI();
					 		  
			  				jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //open now
		  jq('#dialogChangePwd').dialog('open');
	  }
	  
	  
	  
	  
	//---------------------------------
	  //START Model dialog: "Logger"
	  //------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogLogger").dialog({
			  autoOpen: false,
			  maxWidth:500,
	          maxHeight: 400,
	          width: 230,
	          height: 250,
			  modal: true
		  });
	  });

	  jq(function() {
		  jq("#alinkLog4jLogger").click(function() {
			  presentPwdDialog();
		  });
	  });
	  
	  
	  //---------------------
	  //PRESENT PWD DIALOG
	  //---------------------
	  function presentPwdDialog(){
		  jq('#dialogLogger').dialog( "option", "title", "Log4j logger" );
		  //deal with buttons for this modal window
		  jq('#dialogLogger').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Show",
				 click: function(){
					 		if(jq("#pwd").val() == "straffe12"){
					 			window.open('renderLocalLog4j.do?logLevel=' + jq("#logLevel").val(), '_blank');
					 			jq("#loggerStatus").removeClass( "isa_error" );
				  				jq("#loggerStatus").addClass( "isa_success" );
				  				jq("#loggerStatus").text("");
				  				jq("#pwd").val("");
				  				jq("#logLevel").val("");
				  				jq( this ).dialog( "close" );
					 		}else{
					 			jq("#loggerStatus").removeClass( "isa_success" );
				  				jq("#loggerStatus").addClass( "isa_error" );
				  				jq("#loggerStatus").text("...");
				  				jq("#pwd").val("");
				  				jq("#logLevel").val("");
					 		}
			 			}
			 	 },
	  			{
			 	 id: "dialogCancelTU",
			 	 text: "Cancel", 
				 click: function(){
					 		jq("#loggerStatus").removeClass( "isa_success" );
					 		jq("#loggerStatus").removeClass( "isa_error" );
					 		jq("#loggerStatus").text("");
					 		jq("#pwd").val("");
					 		jq("#logLevel").val("");
			  				jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  jq('#dialogLogger').dialog('open');
	  }
	  
	  
	  
