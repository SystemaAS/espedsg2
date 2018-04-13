	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var map = {};
  	
  	jq(function() {
  	  jq("#wsdtf").datepicker({ 
  		  dateFormat: 'yymmdd' 
  	  });
  	  jq("#wsdtt").datepicker({ 
		  dateFormat: 'yymmdd' 
	  });
  	  jq("#wsdthawb").datepicker({ 
		  dateFormat: 'yymmdd' 
	  });
  	  jq("#wsdtot").datepicker({ 
		  dateFormat: 'yymmdd'
	  });
    });
    
    
    
