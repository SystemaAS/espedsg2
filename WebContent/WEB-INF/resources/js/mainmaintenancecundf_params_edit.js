//this variable is a global jQuery var instead of using "$" all the time. Very handy
var jq = jQuery.noConflict();
var counterIndex = 0;
var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

function setBlockUI(element) {
	jq.blockUI({
		message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT
	});
}

jq(function() {
	//Clean values for createing new record
	jq('#newRecordButton').click(function() {
		jq('#syrecn').val("")
		jq('#sypaid').val("");
		jq('#sypaidDesc').val("");
		jq('#sysort').val("");
		jq('#syvrdn').val("");
		jq('#syvrda').val("");
		
		//for update
		jq('#updateId').val("");
		
	});

	jq("#formRecord").submit(function() {
		if (jq('#syvrdn').val() !='') {  //float
			jq("#syvrdn").val(jq("#syvrdn").val().replace(',', '.'));
		}
		
		jq.blockUI({
			message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT
		});
		
	});

    jq('#sypaidIdLink').click(function() {
    	jq('#sypaidIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sypaid', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
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
	var sykunr = record[0];
	var syrecn = record[1];
	    	
	jq.ajax({
	  type: 'GET',
	  url: 'getSpecificRecord_syparf.do',
	  data: { applicationUser : jq('#applicationUser').val(), 
		  sykunr : sykunr,
		  syrecn  : syrecn },
	  dataType: 'json',
	  cache: false,
	  contentType: 'application/json',
	  success: function(data) {
	  	var len = data.length;
		for ( var i = 0; i < len; i++) {
			jq('#sykunr').val("");jq('#sykunr').val(data[i].sykunr);
			jq('#syrecn').val("");jq('#syrecn').val(data[i].syrecn);
			jq('#sypaid').val("");jq('#sypaid').val(data[i].sypaid);
			jq('#sypaidDesc').val("");jq('#sypaidDesc').val(data[i].sypaidDesc);
			jq('#sysort').val("");jq('#sysort').val(data[i].sysort);
			jq('#syvrda').val("");jq('#syvrda').val(data[i].syvrda);
			jq('#syvrdn').val(data[i].syvrdn);jq('#syvrdn').val(jq('#syvrdn').val().replace('.',','))			
			
			//for a future update
			jq('#updateId').val("");jq('#updateId').val(data[i].sypaid);
			
		}
	  }, 
	  error: function (jqXHR, exception) {
		    alert('Error loading ...look in console log.');
		    console.log(jqXHR);
	  }	
	});
		
}


//-------------------
//Datatables jquery
//-------------------
//private function
function filterGlobal() {
	jq('#mainList').dataTable().search(jq('#mainList_filter').val()).draw();
}

jq(document).ready(function() {
	var lang = jq('#language').val();
	jq('#mainList').dataTable({
		"dom" : '<"top">t<"bottom"flip><"clear">',
		"scrollY" : "200px",
		"scrollCollapse" : false,
		"columnDefs" : [ {
			"type" : "num",
			"targets" : 0
		} ],
		"lengthMenu" : [ 75, 100 ],
		"language": {
			"url": getLanguage(lang)
        }
	});

});
