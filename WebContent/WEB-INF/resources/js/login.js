  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  
  jq(function() {
	  jq("#user").focus();
  });
  
  
