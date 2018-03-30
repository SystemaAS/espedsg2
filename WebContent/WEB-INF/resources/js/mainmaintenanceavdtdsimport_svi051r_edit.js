  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
 
  jq(function() {
	  /*jq("#todo").datepicker({ 
		  dateFormat: 'yymmdd' 	  
	  });*/
	  
	  //Avd.
	  jq('#avdIdLink').click(function() {
		  jq('#avdIdLink').attr('target','_blank');
		  window.open('mainmaintenanceavd_childwindow_syfa14r.do?action=doFind&ssealist=1' + '&ctype=dkea_syav', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#avdIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#avdIdLink').click();
  		  }
  	  });
	  
	  //START Customer Validity init
	  jq('#svea_syav').focus(function() {
    	if(jq('#svea_syav').val()!=''){
    		refreshCustomValidity(jq('#svea_syav')[0]);
  		}
 	  });
	  jq('#svea_syop').focus(function() {
    	if(jq('#svea_syop').val()!=''){
    		refreshCustomValidity(jq('#svea_syop')[0]);
  		}
 	  });
	  jq('#svea_omeo').focus(function() {
    	if(jq('#svea_omeo').val()!=''){
    		refreshCustomValidity(jq('#svea_omeo')[0]);
  		}
 	  });
	  //END Customer Validity init
  	  
  });
  
  