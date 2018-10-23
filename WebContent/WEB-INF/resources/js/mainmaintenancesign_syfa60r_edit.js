  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  jq(function() {
	  jq("#formRecord").submit(function() {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT}); 
	  });
	  
  });
  
//called from form-submit, otherwise the select-disabled won't send the value
  function enableDisabledFields() {
	    document.getElementById('kosfsi').disabled= "";
  }
  
  
  jq(function() {
	  //custom validity
	  jq('#kosfnv').blur(function() {
	  		if(jq('#kosfnv').val()!=''){
	    		refreshCustomValidity(jq('#kosfnv')[0]);
	  		}
	  });
	  jq('#kosfsi').blur(function() {
	  		if(jq('#kosfsi').val()!=''){
	    		refreshCustomValidity(jq('#kosfsi')[0]);
	  		}
	  });
	  
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#kosfsi').val("");
			jq('#kosfsi').attr('disabled', false);
			jq('#kosfsi').attr('readOnly', false);
			jq("#kosfsi").removeClass("inputTextReadOnly");
			jq("#kosfsi").addClass("inputTextMediumBlueMandatoryField");
			//
			jq('#kosfnv').val("");
			jq('#syuser').val("");
			
			//for update
			jq('#updateId').val("");
		});
  }); 
  
  //-----------------------
  //GET specific db-record
  //-----------------------
  function getRecord(record){
	
	var rawId = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	rawId = rawId.replace("recordUpdate_", "");
  	//var record = rawId.split('_');
  	var kosfsi = rawId;
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_syfa60r.do',
  	  data: { applicationUser : applicationUserParam, 
  		  	  kosfsi : kosfsi },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {

  			if(data[i].kosfsi!=null && data[i].kosfsi != ''){
  				
  				jq('#kosfsi').val("");jq('#kosfsi').val(data[i].kosfsi);
  				
	  			//editable fields
  				//Special treatment for honet when empty since we must map to space-Html representation: meaning = '+'
  				
	  			jq('#kosfsi').val("");jq('#kosfsi').val(data[i].kosfsi);
	  			jq('#kosfsi').attr('disabled', true);
	  			jq('#kosfsi').attr('readOnly', true);
				jq("#kosfsi").removeClass("inputTextMediumBlueMandatoryField");
				jq("#kosfsi").addClass("inputTextReadOnly");
				//rest of the fields
				jq('#kosfnv').val("");jq('#kosfnv').val(data[i].kosfnv);
  				jq('#syuser').val("");jq('#syuser').val(data[i].syuser);
  				
	  			//for a future update
	  			jq('#updateId').val("");jq('#updateId').val(data[i].kosfsi);

  			}else{
  				//N/A   DEBUG->alert("hoavd = NULL ?");
  			}
  			
  		}
  	  }, 
  	  error: function() {
  		  alert('Error loading ...');
  	  }
	});
	
		
  }
  
  //-------------------
  //Datatables jquery
  //-------------------
  //private function
  function filterGlobal () {
    jq('#mainList').dataTable().search(
    	jq('#mainList_filter').val()
    ).draw();
  }
  
  jq(document).ready(function() {
	  var lang = jq('#language').val();
	  //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	  jq('#mainList').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
    	  "scrollY": "350px",
    	  "scrollCollapse":  true,
    	  "columnDefs": [{ "type": "num", "targets": 1 }],
    	  "order": [[ 1, "asc" ]],
    	  "lengthMenu": [ 75, 100],
    	  "language": { "url": getLanguage(lang) }
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  