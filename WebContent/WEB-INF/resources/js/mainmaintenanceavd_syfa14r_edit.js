  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
 
  jq(function() {
	  //----------------
	  //CUSTOMER search
	  //----------------
	  //Kundnr
	  jq('#koaknrIdLink').click(function() {
		  jq('#koaknrIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&firma=' + jq('#koafir').val() + "&knr=" + jq('#koaknr').val() + '&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#koaknrIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#koaknrIdLink').click();
  		  }
  	  });
  });
  
  