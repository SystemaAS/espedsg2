  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
 
  jq(function() {
	  /*jq("#tidt").datepicker({ 
		  dateFormat: 'yymmdd' 	  
	  });*/
	  
	  
	  //Sender EDI
	  jq('#s0004IdLink').click(function() {
		  jq('#s0004IdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_edi.do?action=doFind&inid=' + jq('#s0004').val() + '&ctype=s0004', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#s0004IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#s0004IdLink').click();
  		  }
  	  });
	  
	  //Receiver EDI
	  jq('#s0010IdLink').click(function() {
		  jq('#s0010IdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_edi.do?action=doFind&inid=' + jq('#s0010').val() + '&ctype=s0010', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#s0010IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#s0010IdLink').click();
  		  }
  	  });
	  
	  //Avd.
	  jq('#tiavdIdLink').click(function() {
		  jq('#tiavdIdLink').attr('target','_blank');
		  window.open('mainmaintenanceavd_childwindow_syfa14r.do?action=doFind&snialist=1' + '&ctype=tiavd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#tiavdIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#tiavdIdLink').click();
  		  }
  	  });
	  
	  //----------------
	  //CUSTOMER search
	  //----------------
	  //Ansvarlig
	  jq('#tinaIdLink').click(function() {
		  jq('#thnasIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&sonavn=' + jq('#tina').val() + "&knr=" + jq('#tikn').val() + '&ctype=tina', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#tinaIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#tinaIdLink').click();
  		  }
  	  });
	  
	  
	  
	  //Tullkontor (code id = 106)
	  jq('#titsbIdLink').click(function() {
		  jq('#titsbIdLink').attr('target','_blank');
		  window.open('mainmaintenanceavdskatncts_childwindow_generalcodes.do?action=doFind&tkunik=106' + "&tkkode=" + jq('#titsb').val() + '&ctype=titsb', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#titsbIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#titsbIdLink').click();
  		  }
  	  });
	  
  	  
  });
  
  