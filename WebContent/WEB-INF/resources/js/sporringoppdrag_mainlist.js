	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var map = {};
  	
  	//----------------------------------------------------------------------
  	//Integration towards Transp.Disp mainlist
  	//In case this window has been open from Transp.Disp (as a childwindow)
  	//----------------------------------------------------------------------
  	jq(function() {
		jq('#oppdragMainList').on('click', 'td', function(){
		  var id = this.id;
		  //check if child window mode
		  if(id.indexOf("cw") != -1){
			  id = id.replace("cw","");
			  var record = id.split('_');
			  var avd = record[0];
			  var opd = record[1];
			  //opener=TransportDisponering main order list JSP
			  if(opener.jq('#searchForm').length){ //only way to check if field exists.
				  opener.jq('#avd').val(avd); opener.jq('#opd').val(opd);
				  opener.jq('#opd').focus();
				  //emulate a search now
				  opener.jq('#submit').click();
				  
				  //close child window
				  window.close();
			  }
		  }
	  });
	});
  	
  	
  	
  	
  	
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
    	  jq("#wsdtfs").datepicker({ 
    		  dateFormat: 'yymmdd' 
    	  });
    	  jq("#wsdtfst").datepicker({ 
    		  dateFormat: 'yymmdd' 
    	  });
  	});
  	
  	
    //-------------------
    //Datatables jquery
    //-------------------
    //private function
    function filterGlobal () {
      jq('#oppdragMainList').DataTable().search(
      		jq('#oppdragMainList_filter').val()
      ).draw();
    }

    jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
    	  jq('#oppdragMainList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 25, 50, 75, 100, 500, 1000 ]
    	  } );
      //event on input field for search
      jq('input.oppdragMainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      } );
      
      
    } );
    
    
    
