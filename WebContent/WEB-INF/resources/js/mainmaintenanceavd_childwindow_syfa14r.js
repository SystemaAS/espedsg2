	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//----------
  	//functions
  	//----------
	jq(function() {
		jq('#mainList').on('click', 'td', function(){
			  
			  var id = this.id;
			  var record = id.split('@');
			 
			  var avd = record[0].replace("avd", "");
			  var callerType = record[1].replace("ctype", "");
			  
			  if(callerType == 'siavd'){
				  opener.jq('#siavd').val(avd);
				  
			  }else if(callerType == 'seavd'){
				  opener.jq('#seavd').val(avd);
				  
			  }else if(callerType == 'thavd'){
				  opener.jq('#thavd').val(avd);
				  
			  }else if(callerType == 'tiavd'){
				  opener.jq('#tiavd').val(avd);
				  
			  }else if(callerType == 'dkea_syav'){
				  opener.jq('#dkea_syav').val(avd);
				  
			  }else if(callerType == 'dkia_syav'){
				  opener.jq('#dkia_syav').val(avd);
				  
			  }    
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
    		  "columnDefs": [{ "type": "num", "targets": 0 }],
        	  "order": [[ 0, "asc" ]],
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	