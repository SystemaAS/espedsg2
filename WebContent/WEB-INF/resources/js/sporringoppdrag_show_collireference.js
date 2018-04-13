	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var map = {};
  	
  	jq(function() {
  	  jq("#datumTODO").datepicker({ 
  		  //dateFormat: 'yymmdd', 
  		  dateFormat: 'ddmmy', 
  		  defaultDate: "-6m"	  
  	  });
  	  
    });
    
    
