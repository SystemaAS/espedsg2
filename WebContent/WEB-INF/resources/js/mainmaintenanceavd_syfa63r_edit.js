  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  //called from form-submit, otherwise the select-disabled won't send the value
  function enableDisabledFields() {
	    document.getElementById('honet').disabled= "";
  }
  
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#honet').val("");
			jq('#honet').attr('disabled', false);
			jq("#honet").removeClass("inputTextReadOnly");
			jq("#honet").addClass("inputTextMediumBlueMandatoryField");
			//
			jq('#hostfr').val("");
			jq('#hoht1').val("");
			jq('#hoht2').val("");
			jq('#hoht3').val("");
			jq('#hoht4').val("");
			jq('#hoht5').val("");
			jq('#hoht6').val("");
			jq('#hoht7').val("");
			//
			jq('#hobt1').val("");
			jq('#hobt2').val("");
			jq('#hobt3').val("");
			
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
  	var record = rawId.split('_');
	var koaavd = record[0];
	var hoavd = record[1];
	var honet = record[2];
	
	if(honet =='N'){
		if(hoavd!=null && hoavd!='' && hoavd!='null'){
			honet = '+'; //space in url-post
		}
	}
		
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_syfa63r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  koaavd : koaavd,
  		  	  hoavd : hoavd, 
  		  	  honet : honet },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			if(data[i].hoavd!=null && data[i].hoavd != '' && data[i].hoavd != 'null'){
	  			//editable fields
  				//Special treatment for honet when empty since we must map to space-Html representation: meaning = '+'
  				var sHonet = data[i].honet;
  				if (sHonet!='T' && sHonet!='E'){sHonet = 'N';}
  				
	  			jq('#honet').val("");jq('#honet').val(sHonet);
	  			jq('#honet').attr('disabled', true);
	  			jq("#honet").removeClass("inputTextMediumBlueMandatoryField");
				jq("#honet").addClass("inputTextReadOnly");
	  			//
	  			jq('#hostfr').val("");jq('#hostfr').val(data[i].hostfr);
	  			jq('#hoavd').val("");jq('#hoavd').val(data[i].hoavd);
	  			
	  			jq('#hoht1').val("");jq('#hoht1').val(data[i].hoht1);
	  			jq('#hoht2').val("");jq('#hoht2').val(data[i].hoht2);
	  			jq('#hoht3').val("");jq('#hoht3').val(data[i].hoht3);
	  			jq('#hoht4').val("");jq('#hoht4').val(data[i].hoht4);
	  			jq('#hoht5').val("");jq('#hoht5').val(data[i].hoht5);
	  			jq('#hoht6').val("");jq('#hoht6').val(data[i].hoht6);
	  			jq('#hoht7').val("");jq('#hoht7').val(data[i].hoht7);
	  			jq('#hobt1').val("");jq('#hobt1').val(data[i].hobt1);
	  			jq('#hobt2').val("");jq('#hobt2').val(data[i].hobt2);
	  			jq('#hobt3').val("");jq('#hobt3').val(data[i].hobt3);
	  			//for a future update
	  			jq('#updateId').val("");jq('#updateId').val(data[i].koaavd);
	  			
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
    	  "scrollY": "200px",
    	  "scrollCollapse":  false,
    	  //"columnDefs": [{ "type": "num", "targets": 1 }],
    	  //"order": [[ 1, "asc" ]],
    	  "lengthMenu": [ 75, 100],
    	  "language": { "url": getLanguage(lang) }
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
 
  