  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
 
  jq(function() {

	    jq('#arklagIdLink').click(function() {
	    	jq('#arklagIdLink').attr('target','_blank');
	    	window.open('mainmaintenance_arkiv_edit_childwindow_codes.do?caller=arklag', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });

	    jq('#arkvedIdLink').click(function() {
	    	jq('#arkvedIdLink').attr('target','_blank');
	    	window.open('mainmaintenance_arkiv_edit_childwindow_codes.do?caller=arkved', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });

	    jq('#arsbanIdLink').click(function() {
	    	jq('#arsbanIdLink').attr('target','_blank');
	    	window.open('mainmaintenance_arkiv_edit_childwindow_codes.do?caller=arsban', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });	    
	    
  });
  
  