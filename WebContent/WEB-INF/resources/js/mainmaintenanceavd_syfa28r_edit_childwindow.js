  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
 
  //SENDER
  jq('#sinasIdLink').click(function() {
  	jq('#sinasIdLink').attr('target','_blank');
  	window.open('mainmaintenanceavd_syfa28r_edit_childwindow.do?action=doFind&sonavn=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  });
  