  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
 
  jq(function() {
	  /*jq("#todo").datepicker({ 
		  dateFormat: 'yymmdd' 	  
	  });*/
	  
	  //Avd.
	  jq('#avdIdLink').click(function() {
		  jq('#avdIdLink').attr('target','_blank');
		  window.open('mainmaintenanceavd_childwindow_syfa14r.do?action=doFind&ssialist=1' + '&ctype=dkia_syav', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#avdIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#avdIdLink').click();
  		  }
  	  });
  	  
  });
  
  