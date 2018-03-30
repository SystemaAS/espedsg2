	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//----------
  	//functions
  	//----------
	jq(function() {
		jq('#mainList').on('click', 'td', function(){
			  
			  var id = this.id;
			  var record = id.split('@');
			 
			  var kosfsi = record[0].replace("kosfsi", "");
			  var kosfnv = record[1].replace("kosfnv", "");
			  var syuser = record[2].replace("syuser", "");
			  var callerType = record[3].replace("ctype", "");
			  
			  if(callerType == 'dkth_sysg'){
				  opener.jq('#dkth_sysg').val(kosfsi);
				  opener.jq('#dkth_namn').val(kosfnv);
				  opener.jq('#dkth_usid').val(syuser);
				  //
				  opener.jq('#dkth_sysg').focus();
				  
			  }else if(callerType == 'svth_sysg'){
				  opener.jq('#svth_sysg').val(kosfsi);
				  opener.jq('#svth_namn').val(kosfnv);
				  opener.jq('#svth_usid').val(syuser);
				  //
				  opener.jq('#svth_sysg').focus();
				  
			  }/*TODO if more ... else if(callerType == 's0010'){
				  opener.jq('#s0010').val(inid);
				  opener.jq('#s0010').focus();
				*/  
			  
			  //close child window
			  window.close();
		  });
	});
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#mainList').DataTable().search(
      		jq('#mainList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#mainList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	