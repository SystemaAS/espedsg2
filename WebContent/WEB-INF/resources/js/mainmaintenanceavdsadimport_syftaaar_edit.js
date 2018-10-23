  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
 
  jq(function() {
	  
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
	  jq('#siavdIdLink').click(function() {
		  jq('#siavdIdLink').attr('target','_blank');
		  window.open('mainmaintenanceavd_childwindow_syfa14r.do?action=doFind&sialist=1' + '&ctype=siavd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#siavdIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#siavdIdLink').click();
  		  }
  	  });
	  
	//----------------
	  //CUSTOMER search
	  //----------------
	  //Sender
	  jq('#sinasIdLink').click(function() {
		  jq('#sinasIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&sonavn=' + jq('#sinas').val() + "&knr=" + jq('#sikns').val() + '&ctype=sinas', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#sinasIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#sinasIdLink').click();
  		  }
  	  });
	  //Receiver
	  jq('#sinakIdLink').click(function() {
		  jq('#sinakIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&sonavn=' + jq('#sinak').val() + "&knr=" + jq('#siknk').val() + '&ctype=sinak', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#sinakIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#sinakIdLink').click();
  		  }
  	  });
	  
  	  
  });
  
  