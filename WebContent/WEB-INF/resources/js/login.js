  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }

  jq(function() {
	  jq("#user").focus();
  });
  
  
  jq(document).ready(function() {
	  console.log("Hi"); 
  });
  
  
