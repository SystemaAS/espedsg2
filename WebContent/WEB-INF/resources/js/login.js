  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  let lang = getLanguage();
	  if (lang == undefined) {
		lang = 'en-GB';
	  }

	  let UIMessage = blockUIMessage[lang];
	  if (UIMessage == undefined) {
		  UIMessage = BLOCKUI_OVERLAY_MESSAGE_DEFAULT;
	  }

	  jq.blockUI({ css: { fontSize: '22px' }, message: UIMessage});
  }

  //https://www.w3schools.com/tags/ref_language_codes.asp - https://www.w3schools.com/tags/ref_country_codes.asp
  var blockUIMessage = {
			   'en-GB' : 'Please wait...',
			   'da-DK' : 'Vent venligst...',
			   'sv-SE' : 'Vänligen vänta...',
			   'no-NO' : 'Vennligst vent...'
  }
  
  function getLanguage() {
	  let language = navigator.languages && navigator.languages[0] ||
	  navigator.language ||
	  navigator.userLanguage;
	  
	  return language;
  }
  
  jq(function() {
	  jq("#user").focus();
  });
 
  
  jq(document).ready(function() {
	  console.log("Hi"); 
	  console.log("language",getLanguage());
  });
  

  //----------------
  //FORM SUBMISSION
  //----------------
  function checkRecaptcha() {
	  var response = grecaptcha.getResponse();
	  if(response.length == 0) { 
	    //reCaptcha not verified
	    console.log("no pass"); 
	  }
	  else { 
	    //reCaptch verified
	    console.log("pass");
	    //only when verified reCaptcha
	    submitLoginForm();
	  }
	}
  
  //html form can not have the action attribute in the html ... Has to be here to let reCaptcha work first
  function submitLoginForm() {
	  let PASS_MINIMUM_LENGTH = 5;
	  //this is to prevent most of the bots - attacks
	  if(jq('#password').val() != '' && jq('#password').val().length >= PASS_MINIMUM_LENGTH){
		  jq('#password').removeClass('isa_error');
		  
		  if(jq('#alwaysEmptyAndInvisible').val() === ''){
			  let host = jq('#host').val();
			  //only SaaS customers should use the 2FA-solution (Google Authenticator or Duo or other).

			  //removed since Roger moved to new machine on 14.Jun.2022	
			  if(host.indexOf("systema.no") != -1 || host.indexOf("localhost") != -1){
					//check saas flag (disabled or enabled 2FA)
					if(jq('#saas_2fa').val() != ''){	
					  	jq('#loginForm').attr('action', 'loginconfirm.do');
					}else{
						//bypass the 2FA in a panic situation. Blank in properties file
						jq('#loginForm').attr('action', 'logonDashboard.do');
					}
			  }else{
				  //this is the normal none-2FA solution. Usually all external customers
				  jq('#loginForm').attr('action', 'logonDashboard.do');
			  }
			  
			  jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
			  jq('#loginForm').submit();
		  }
	  }else{
		  jq('#password').addClass('isa_error');
	  }
	};
	//--------------------
	//END-FORM SUBMISSION
	//--------------------	
	
	
	//------------------------------------------------
	//FORM SUBMISSION - CONFIRM 2FactorAuthentication
	//------------------------------------------------
	function submitLoginConfirmForm(){
		jq.ajax({
		  type: 'GET',
		  url: 'getAuthenticator2FAResponse.do',
		  data: { code : jq('#code').val() },
		  dataType: 'json',
		  cache: false,
		  contentType: 'application/json',
		  success: function(data) {
		  	var len = data.length;
		  	if(len>0){
				for ( var i = 0; i < len; i++) {
					if(data[i].user=='OK'){
						jq('#code').removeClass('isa_error');
						jq('#code').addClass('isa_success');
						redirectFormAfter2FAConfirm();
					}else{
						jq('#code').val('');jq('#code').focus();
						jq('#code').addClass('isa_error');
					}					
				}
		  	}else{
		  		jq('#code').val('');jq('#code').focus();
		  		jq('#code').addClass('isa_error');
		  	}
		  }, 
		  error: function() {
	  		  alert('Error loading ...');
	  		  jq('#code').addClass('isa_error');
	  		  jq('#code').val('');jq('#code').focus();
		  }
		});
	}
	function redirectFormAfter2FAConfirm(){
		jq('#loginConfirmForm').attr('action', 'logonDashboard.do');
		jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
		jq('#loginConfirmForm').submit();
	}
	//----------------------------------------------------
	//END FORM SUBMISSION - CONFIRM 2FactorAuthentication
	//----------------------------------------------------
	
	jq(function() {
		  jq("#qrcode").click(function() {
			  window.open('renderLocalQRcode.do?fn=QRcode.png', "qrcode", "top=300px,left=500px,height=400px,width=500px,scrollbars=no,status=no,location=no");
		  });
	  });
	
	
	
	jq(document).ready(function(){
		jq('#code').focus();
		
	    jq('#code').keypress(function(e){
	      if(e.keyCode==13)
	      jq('#btnConfirm').click();
	    });
	});
	
	
	