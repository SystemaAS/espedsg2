  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }

  jq(function() {
	  jq("#user").focus();
  });
  
  jq(function() {
	  //gui events
	  jq('#changePwdButton').click(function() {
		  if(jq('#user').val()!='' && jq('#password').val()!=''){
			  jq('#user').removeClass('isa_error');
			  jq('#password').removeClass('isa_error');
			  var form = new FormData(document.getElementById('loginForm'));
			  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
			  
			  jq.ajax({
			  	  type: 'POST',
			  	  url: 'logonDashboardThin.do',
			  	  data: form,  
			  	  dataType: 'text',
			  	  cache: false,
			  	  processData: false,
			  	  contentType: false,
		  		  success: function(data) {
				  	  var len = data.length;
			  		  if(len>0){
			  			  console.log(data);
			  			  jq("#changePwdArea").toggle();
			  			  jq("#submit").toggle();
			  			  //
			  			  jq("#validUser").val(data);
			  			  //
			  			  jq("#backendError").hide();
			  			  //unblock
			  			  jq.unblockUI();
			  		  }else{
			  			jq.unblockUI();
			  			jq('#user').addClass('isa_error');
						jq('#password').addClass('isa_error');
			  		  }
			  	  }, 
			  	  error: function() {
			  		  jq.unblockUI();
			  		  alert('Error loading ...');
			  		  //jq("#file").val("");
			  		  //cosmetics
			  		  //jq("#file").addClass( "isa_error" );
			  		  //jq("#file").removeClass( "isa_success" );
				  }
			  });
		  }else{
			  jq('#user').val("?");
			  jq('#user').addClass('isa_error');
			  jq('#password').val("?");
			  jq('#password').addClass('isa_error');
			  
		  }
	  });
	
	  //VALIDATION GUI on change pwd
	  jq('#passwordNew').blur(function() {
		  validateChangePwd();
	  });
	  jq('#passwordConfirm').blur(function() {
		  validateChangePwd();
	  });
	  
	  //send Form POST for change pwd
	  jq('#executeNewPwdButton').click(function() {
		  if(jq('#passwordNew').val()!='' && jq('#passwordConfirm').val()!='' && jq('#password').val()!='' && jq('#user').val()!='' ){
			  if(jq('#passwordNew').val() == jq('#passwordConfirm').val()){
				  jq("#validationLabelMessage").text("");
				  jq("#loginFormChgPwd").submit();
			  }else{
				  jq("#validationLabelMessage").text("Passordene er forskjellige");
			  }
		  }else{
			  jq("#validationLabelMessage").text("Alla felt må fylles ut");
		  }
		  
		
	  });
  });
  
  
  function clearMandatoryFieldsError(){
	  jq("#validationLabelMessage").text("");  
  }
  
  function validateChangePwd(){
	  if(jq('#passwordNew').val()!='' && jq('#passwordConfirm').val()!='' ){
		  if(jq('#password').val()!='' && jq('#user').val()!='' ){
			  
		  }else{
			  jq('#user').addClass('isa_error');
			  jq('#password').addClass('isa_error');
			  
		  }
	  }else if  (jq('#passwordNew').val()=='' && jq('#passwordConfirm').val()=='' ){
		  //nothing. It means att normal Login will take place
	  }else{
		 //TODO ?
	  }
  }
  
  
  jq(document).ready(function() {
	  console.log("Hi"); 
  });
  
  
