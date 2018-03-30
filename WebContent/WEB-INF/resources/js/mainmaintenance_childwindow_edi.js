	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//----------
  	//functions
  	//----------
	jq(function() {
		jq('#mainList').on('click', 'td', function(){
			  
			  var id = this.id;
			  var record = id.split('@');
			 
			  var inid = record[0].replace("inid", "");
			  var inna = record[1].replace("inna", "");
			  var callerType = record[2].replace("ctype", "");
			  
			  if(callerType == 's0004'){
				  opener.jq('#s0004').val(inid);
				  opener.jq('#s0004').focus();
				  
			  }else if(callerType == 's0010'){
				  opener.jq('#s0010').val(inid);
				  opener.jq('#s0010').focus();
				  
			  //DK Felles Vedlikehold	  
			  }else if(callerType == 'dktf_0004t'){
				  opener.jq('#dktf_0004t').val(inid);
				  opener.jq('#dktf_0004t').focus();
				  
			  }else if(callerType == 'dktf_0010t'){
				  opener.jq('#dktf_0010t').val(inid);
				  opener.jq('#dktf_0010t').focus();
				  
			  }else if(callerType == 'dktf_0004p'){
				  opener.jq('#dktf_0004p').val(inid);
				  opener.jq('#dktf_0004p').focus();
				  
			  }else if(callerType == 'dktf_0010p'){
				  opener.jq('#dktf_0010p').val(inid);
				  opener.jq('#dktf_0010p').focus();
			  } //END DK Vedlikehold
			
			  //TDS Felles Underhåll	  
			  else if(callerType == 'svtf_0004'){
			  opener.jq('#svtf_0004').val(inid);
			  opener.jq('#svtf_0004').focus();
			  
			  }else if(callerType == 'svtf_0010'){
				  opener.jq('#svtf_0010').val(inid);
				  opener.jq('#svtf_0010').focus();
				  
			  }//END TDS Underhåll
		  
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
    		  //"columnDefs": [{ "type": "natural", "targets": 0 }],
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	