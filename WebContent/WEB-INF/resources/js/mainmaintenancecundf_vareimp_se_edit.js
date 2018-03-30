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
	jq('#newRecordButton').click(function() {
		jq("input[type='text']").val("");
		jq('select').find('option').prop("selected", false);

		jq("#sviw_knso").prop("readonly", false);
		jq("#sviw_knso").removeClass("inputTextReadOnly");
		jq("#sviw_knso").addClass("inputTextMediumBlueMandatoryField");
		
		//init of int and floats
		jq('#sviw_ankv').val("0");	
		jq('#sviw_brut').val("0");	
		jq('#sviw_fabl').val("0");	
		jq('#sviw_kota').val("0");	
		jq('#sviw_kot2').val("0");	
		jq('#sviw_kot3').val("0");	
		jq('#sviw_kot4').val("0");	
		jq('#sviw_kot5').val("0");	
		jq('#sviw_neto').val("0");	
		jq('#sviw_stva').val("0");	
		jq('#sviw_stva2').val("0");	
		jq('#sviw_vano').val("0");	
		jq('#sviw_suar').val("0");	
		jq('#sviw_suok').val("0");	
		jq('#sviw_sukr').val("0");
		
		//for update
		jq('#updateId').val("");
		
	});

	jq("#formRecord").submit(function() {
		if (jq('#sviw_ankv').val() =='' ) {
			jq('#sviw_ankv').val("0");
		}
		if (jq('#sviw_brut').val() =='' ) {
			jq('#sviw_brut').val("0");
		} else if (jq('#sviw_brut').val() !='') {  //float
			jq("#sviw_brut").val(jq("#sviw_brut").val().replace(',', '.'));
		}
		if (jq('#sviw_fabl').val() =='' ) {
			jq('#sviw_fabl').val("0");
		} else if (jq('#sviw_fabl').val() !='') {  //float
			jq("#sviw_fabl").val(jq("#sviw_fabl").val().replace(',', '.'));
			
		}
		if (jq('#sviw_kota').val() =='' ) {
			jq('#sviw_kota').val("0");
		}
		if (jq('#sviw_kot2').val() =='' ) {
			jq('#sviw_kot2').val("0");
		}
		if (jq('#sviw_kot3').val() =='' ) {
			jq('#sviw_kot3').val("0");
		}
		if (jq('#sviw_kot4').val() =='' ) {
			jq('#sviw_kot4').val("0");
		}
		if (jq('#sviw_kot5').val() =='' ) {
			jq('#sviw_kot5').val("0");
		}
		if (jq('#sviw_neto').val() =='' ) {
			jq('#sviw_neto').val("0");
		} else if (jq('#sviw_neto').val() !='') {  //float
			jq("#sviw_neto").val(jq("#sviw_neto").val().replace(',', '.'));
		}		
		if (jq('#sviw_stva').val() =='' ) {
			jq('#sviw_stva').val("0");
		}
		if (jq('#sviw_stva').val() =='' ) {
			jq('#sviw_stva').val("0");
		}
		if (jq('#sviw_stva2').val() =='' ) {
			jq('#sviw_stva2').val("0");
		}
		if (jq('#sviw_suar').val() =='' ) {
			jq('#sviw_suar').val("0");
		}
		if (jq('#sviw_sukr').val() =='' ) {
			jq('#sviw_sukr').val("0");
		}
		if (jq('#sviw_suok').val() =='' ) {
			jq('#sviw_suok').val("0");
		}	
		if (jq('#sviw_vano').val() =='' ) {
			jq('#sviw_vano').val("0");
		}		
		
		jq.blockUI({
			message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT
		});
		
	});

	
    jq( "#accordion" ).accordion({
      collapsible: true,
      active: false
    });
    
    jq( "#accordion2" ).accordion({
        collapsible: true,
        active: false
    }); 
    
    jq( "#accordion3" ).accordion({
        collapsible: true,
        active: false
    });  
    
    jq( "#accordion4" ).accordion({
        collapsible: true,
        active: false
    });    

    jq( "#accordion-half1" ).accordion({
        collapsible: true,
        active: false
    });     
    
    jq( "#accordion-half2" ).accordion({
        collapsible: true,
        active: false
    }); 
  
    
    jq('#sviw_ulkdIdLink').click(function() {
    	jq('#sviw_ulkdIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_ulkd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	   
   
    jq('#sviw_vataIdLink').click(function() {
    	jq('#sviw_vataIdLink').attr('target','_blank');
	    window.open('tdsimport_edit_items_childwindow_tulltaxa.do?action=doInit&vkod=' + jq('#sviw_vata').val() + '&caller=sviw_vata', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	   
 
    jq('#sviw_eup1IdLink').click(function() {
    	jq('#sviw_eup1IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_eup1', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	
    
    jq('#sviw_eup2IdLink').click(function() {
    	jq('#sviw_eup2IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_eup2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
  
    jq('#sviw_koslIdLink').click(function() {
    	jq('#sviw_koslIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_kosl', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    
    jq('#sviw_kos2IdLink').click(function() {
    	jq('#sviw_kos2IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_kos2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_kos3IdLink').click(function() {
    	jq('#sviw_kos3IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_kos3', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_kos4IdLink').click(function() {
    	jq('#sviw_kos4IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_kos4', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_kos5IdLink').click(function() {
    	jq('#sviw_kos5IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_kos5', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_bit1IdLink').click(function() {
    	jq('#sviw_bit1IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_bit1', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_bit2IdLink').click(function() {
    	jq('#sviw_bit2IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_bit2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_bit3IdLink').click(function() {
    	jq('#sviw_bit3IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_bit3', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_bit4IdLink').click(function() {
    	jq('#sviw_bit4IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_bit4', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_bit5IdLink').click(function() {
    	jq('#sviw_bit5IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_bit5', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_bit6IdLink').click(function() {
    	jq('#sviw_bit6IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_bit6', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_bit7IdLink').click(function() {
    	jq('#sviw_bit7IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_bit7', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_bit8IdLink').click(function() {
    	jq('#sviw_bit8IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_bit8', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_bit9IdLink').click(function() {
    	jq('#sviw_bit9IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_bit9', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_tit1IdLink').click(function() {
    	jq('#sviw_tit1IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_tit1', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_tit2IdLink').click(function() {
    	jq('#sviw_tit2IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_tit2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_tit3IdLink').click(function() {
    	jq('#sviw_tit3IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_tit3', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_tit4IdLink').click(function() {
    	jq('#sviw_tit4IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_tit4', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_tit5IdLink').click(function() {
    	jq('#sviw_tit5IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_tit5', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_tit6IdLink').click(function() {
    	jq('#sviw_tit6IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_tit6', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_tit7IdLink').click(function() {
    	jq('#sviw_tit7IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_tit7', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_tit8IdLink').click(function() {
    	jq('#sviw_tit8IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_tit8', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_tit9IdLink').click(function() {
    	jq('#sviw_tit9IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_tit9', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_laglIdLink').click(function() {
    	jq('#sviw_laglIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_lagl', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#sviw_fokdIdLink').click(function() {
    	jq('#sviw_fokdIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sviw_fokd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    

    
	jq('#sviw_fabl').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#sviw_brut').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#sviw_neto').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#sviw_kota').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#sviw_kota').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#sviw_kot2').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#sviw_kot3').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#sviw_kot4').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#sviw_kot5').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#sviw_ankv').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#sviw_stva').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#sviw_stva2').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	
}); 


//-----------------------
//GET specific db-record
//-----------------------

function getRecord(record){
	var rawId = record.id;
	var applicationUserParam = jq('#applicationUser').val();
	rawId = rawId.replace("recordUpdate_", "");
	var record = rawId.split('_');
	var sviw_knnr = record[0];
	var sviw_knso = record[1];
	
	jq("#sviw_knso").prop("readonly", true);
	jq("#sviw_knso").removeClass("inputTextMediumBlueMandatoryField");
	jq("#sviw_knso").addClass("inputTextReadOnly");
	
	
	jq.ajax({
	  type: 'GET',
	  url: 'getSpecificRecord_sviw.do',
	  data: { applicationUser : jq('#applicationUser').val(), 
		  sviw_knnr : sviw_knnr,
		  sviw_knso  : sviw_knso },
	  dataType: 'json',
	  cache: false,
	  contentType: 'application/json',
	  success: function(data) {
	  	var len = data.length;
		for ( var i = 0; i < len; i++) {
			jq('#sviw_knso').val("");jq('#sviw_knso').val(data[i].sviw_knso);
			jq('#sviw_vasl').val("");jq('#sviw_vasl').val(data[i].sviw_vasl);
			jq('#sviw_vas2').val("");jq('#sviw_vas2').val(data[i].sviw_vas2);
			jq('#sviw_vas3').val("");jq('#sviw_vas3').val(data[i].sviw_vas3);
			jq('#sviw_vas4').val("");jq('#sviw_vas4').val(data[i].sviw_vas4);
			jq('#sviw_vano').val("");jq('#sviw_vano').val(data[i].sviw_vano);
			jq('#sviw_vata').val("");jq('#sviw_vata').val(data[i].sviw_vata);
			jq('#sviw_lagi').val("");jq('#sviw_lagi').val(data[i].sviw_lagi);
			jq('#sviw_ulkd').val("");jq('#sviw_ulkd').val(data[i].sviw_ulkd);
			jq('#sviw_vati').val("");jq('#sviw_vati').val(data[i].sviw_vati);
			jq('#sviw_vat4').val("");jq('#sviw_vat4').val(data[i].sviw_vat4);
			jq('#sviw_vat5').val("");jq('#sviw_vat5').val(data[i].sviw_vat5);
			jq('#sviw_brut').val(data[i].sviw_brut);jq('#sviw_brut').val(jq('#sviw_brut').val().replace('.',','))
			jq('#sviw_eup1').val("");jq('#sviw_eup1').val(data[i].sviw_eup1);
			jq('#sviw_neto').val(data[i].sviw_neto);jq('#sviw_neto').val(jq('#sviw_neto').val().replace('.',','))			
			jq('#sviw_eup2').val("");jq('#sviw_eup2').val(data[i].sviw_eup2);
			jq('#sviw_atin').val("");jq('#sviw_atin').val(data[i].sviw_atin);
			jq('#sviw_kono').val("");jq('#sviw_kono').val(data[i].sviw_kono);
			jq('#sviw_ankv').val("");jq('#sviw_ankv').val(data[i].sviw_ankv);
			jq('#sviw_stva').val("");jq('#sviw_stva').val(data[i].sviw_stva);
			jq('#sviw_stva2').val("");jq('#sviw_stva2').val(data[i].sviw_stva2);
			jq('#sviw_fabl').val(data[i].sviw_fabl);jq('#sviw_fabl').val(jq('#sviw_fabl').val().replace('.',','))
			jq('#sviw_lagt').val("");jq('#sviw_lagt').val(data[i].sviw_lagt);
			jq('#sviw_lagl').val("");jq('#sviw_lagl').val(data[i].sviw_lagl);
			jq('#sviw_call').val("");jq('#sviw_call').val(data[i].sviw_call);
			jq('#sviw_bit1').val("");jq('#sviw_bit1').val(data[i].sviw_bit1);
			jq('#sviw_bii1').val("");jq('#sviw_bii1').val(data[i].sviw_bii1);
			jq('#sviw_bit2').val("");jq('#sviw_bit2').val(data[i].sviw_bit2);
			jq('#sviw_bii2').val("");jq('#sviw_bii2').val(data[i].sviw_bii2);
			jq('#sviw_bit3').val("");jq('#sviw_bit3').val(data[i].sviw_bit3);
			jq('#sviw_bii3').val("");jq('#sviw_bii3').val(data[i].sviw_bii3);
			jq('#sviw_bit4').val("");jq('#sviw_bit4').val(data[i].sviw_bit4);
			jq('#sviw_bii4').val("");jq('#sviw_bii4').val(data[i].sviw_bii4);
			jq('#sviw_bit5').val("");jq('#sviw_bit5').val(data[i].sviw_bit5);
			jq('#sviw_bii5').val("");jq('#sviw_bii5').val(data[i].sviw_bii5);
			jq('#sviw_bit6').val("");jq('#sviw_bit6').val(data[i].sviw_bit6);
			jq('#sviw_bii6').val("");jq('#sviw_bii6').val(data[i].sviw_bii6);
			jq('#sviw_bit7').val("");jq('#sviw_bit7').val(data[i].sviw_bit7);
			jq('#sviw_bii7').val("");jq('#sviw_bii7').val(data[i].sviw_bii7);
			jq('#sviw_bit8').val("");jq('#sviw_bit8').val(data[i].sviw_bit8);
			jq('#sviw_bii8').val("");jq('#sviw_bii8').val(data[i].sviw_bii8);
			jq('#sviw_bit9').val("");jq('#sviw_bit9').val(data[i].sviw_bit9);
			jq('#sviw_bii9').val("");jq('#sviw_bii9').val(data[i].sviw_bii9);
			jq('#sviw_suko').val("");jq('#sviw_suko').val(data[i].sviw_suko);
			jq('#sviw_sutx').val("");jq('#sviw_sutx').val(data[i].sviw_sutx);
			jq('#sviw_sut2').val("");jq('#sviw_sut2').val(data[i].sviw_sut2);
			jq('#sviw_sut3').val("");jq('#sviw_sut3').val(data[i].sviw_sut3);
			jq('#sviw_sut4').val("");jq('#sviw_sut4').val(data[i].sviw_sut4);
			jq('#sviw_sut5').val("");jq('#sviw_sut5').val(data[i].sviw_sut5);
			jq('#sviw_suk6').val("");jq('#sviw_suk6').val(data[i].sviw_suk6);
			jq('#sviw_sut6').val("");jq('#sviw_sut6').val(data[i].sviw_sut6);
			jq('#sviw_sut7').val("");jq('#sviw_sut7').val(data[i].sviw_sut7);
			jq('#sviw_sut8').val("");jq('#sviw_sut8').val(data[i].sviw_sut8);
			jq('#sviw_sut9').val("");jq('#sviw_sut9').val(data[i].sviw_sut9);
			jq('#sviw_suta').val("");jq('#sviw_suta').val(data[i].sviw_suta);
			jq('#sviw_sukb').val("");jq('#sviw_sukb').val(data[i].sviw_sukb);
			jq('#sviw_sutb').val("");jq('#sviw_sutb').val(data[i].sviw_sutb);
			jq('#sviw_sutc').val("");jq('#sviw_sutc').val(data[i].sviw_sutc);
			jq('#sviw_sutd').val("");jq('#sviw_sutd').val(data[i].sviw_sutd);
			jq('#sviw_sute').val("");jq('#sviw_sute').val(data[i].sviw_sute);
			jq('#sviw_sutf').val("");jq('#sviw_sutf').val(data[i].sviw_sutf);
			jq('#sviw_tik1').val("");jq('#sviw_tik1').val(data[i].sviw_tik1);
			jq('#sviw_tik2').val("");jq('#sviw_tik2').val(data[i].sviw_tik2);
			jq('#sviw_tik3').val("");jq('#sviw_tik3').val(data[i].sviw_tik3);
			jq('#sviw_tik4').val("");jq('#sviw_tik4').val(data[i].sviw_tik4);
			jq('#sviw_tik5').val("");jq('#sviw_tik5').val(data[i].sviw_tik5);
			jq('#sviw_tik6').val("");jq('#sviw_tik6').val(data[i].sviw_tik6);
			jq('#sviw_tik7').val("");jq('#sviw_tik7').val(data[i].sviw_tik7);
			jq('#sviw_tik8').val("");jq('#sviw_tik8').val(data[i].sviw_tik8);
			jq('#sviw_tik9').val("");jq('#sviw_tik9').val(data[i].sviw_tik9);
			jq('#sviw_tit1').val("");jq('#sviw_tit1').val(data[i].sviw_tit1);
			jq('#sviw_tit2').val("");jq('#sviw_tit2').val(data[i].sviw_tit2);
			jq('#sviw_tit3').val("");jq('#sviw_tit3').val(data[i].sviw_tit3);
			jq('#sviw_tit4').val("");jq('#sviw_tit4').val(data[i].sviw_tit4);
			jq('#sviw_tit5').val("");jq('#sviw_tit5').val(data[i].sviw_tit5);
			jq('#sviw_tit6').val("");jq('#sviw_tit6').val(data[i].sviw_tit6);
			jq('#sviw_tit7').val("");jq('#sviw_tit7').val(data[i].sviw_tit7);
			jq('#sviw_tit8').val("");jq('#sviw_tit8').val(data[i].sviw_tit8);
			jq('#sviw_tit9').val("");jq('#sviw_tit9').val(data[i].sviw_tit9);
			jq('#sviw_tix1').val("");jq('#sviw_tix1').val(data[i].sviw_tix1);
			jq('#sviw_tix2').val("");jq('#sviw_tix2').val(data[i].sviw_tix2);
			jq('#sviw_tix3').val("");jq('#sviw_tix3').val(data[i].sviw_tix3);
			jq('#sviw_tix4').val("");jq('#sviw_tix4').val(data[i].sviw_tix4);
			jq('#sviw_tix5').val("");jq('#sviw_tix5').val(data[i].sviw_tix5);
			jq('#sviw_tix6').val("");jq('#sviw_tix6').val(data[i].sviw_tix6);
			jq('#sviw_tix7').val("");jq('#sviw_tix7').val(data[i].sviw_tix7);
			jq('#sviw_tix8').val("");jq('#sviw_tix8').val(data[i].sviw_tix8);
			jq('#sviw_tix9').val("");jq('#sviw_tix9').val(data[i].sviw_tix9);
			jq('#sviw_godm').val("");jq('#sviw_godm').val(data[i].sviw_godm);
			jq('#sviw_god2').val("");jq('#sviw_god2').val(data[i].sviw_god2);
			jq('#sviw_god3').val("");jq('#sviw_god3').val(data[i].sviw_god3);
			jq('#sviw_god4').val("");jq('#sviw_god4').val(data[i].sviw_god4);
			jq('#sviw_god5').val("");jq('#sviw_god5').val(data[i].sviw_god5);
			jq('#sviw_kota').val("");jq('#sviw_kota').val(data[i].sviw_kota);
			jq('#sviw_kot2').val("");jq('#sviw_kot2').val(data[i].sviw_kot2);
			jq('#sviw_kot3').val("");jq('#sviw_kot3').val(data[i].sviw_kot3);
			jq('#sviw_kot4').val("");jq('#sviw_kot4').val(data[i].sviw_kot4);
			jq('#sviw_kot5').val("");jq('#sviw_kot5').val(data[i].sviw_kot5);
			jq('#sviw_kosl').val("");jq('#sviw_kosl').val(data[i].sviw_kosl);
			jq('#sviw_kos2').val("");jq('#sviw_kos2').val(data[i].sviw_kos2);
			jq('#sviw_kos3').val("");jq('#sviw_kos3').val(data[i].sviw_kos3);
			jq('#sviw_kos4').val("");jq('#sviw_kos4').val(data[i].sviw_kos4);
			jq('#sviw_kos5').val("");jq('#sviw_kos5').val(data[i].sviw_kos5);
			jq('#sviw_co01').val("");jq('#sviw_co01').val(data[i].sviw_co01);
			jq('#sviw_co02').val("");jq('#sviw_co02').val(data[i].sviw_co02);
			jq('#sviw_co03').val("");jq('#sviw_co03').val(data[i].sviw_co03);
			jq('#sviw_co04').val("");jq('#sviw_co04').val(data[i].sviw_co04);
			jq('#sviw_co05').val("");jq('#sviw_co05').val(data[i].sviw_co05);
			jq('#sviw_co06').val("");jq('#sviw_co06').val(data[i].sviw_co06);
			jq('#sviw_co07').val("");jq('#sviw_co07').val(data[i].sviw_co07);
			jq('#sviw_co08').val("");jq('#sviw_co08').val(data[i].sviw_co08);
			jq('#sviw_co09').val("");jq('#sviw_co09').val(data[i].sviw_co09);
			jq('#sviw_co10').val("");jq('#sviw_co10').val(data[i].sviw_co10);
			jq('#sviw_co11').val("");jq('#sviw_co11').val(data[i].sviw_co11);
			jq('#sviw_co12').val("");jq('#sviw_co12').val(data[i].sviw_co12);
			jq('#sviw_co13').val("");jq('#sviw_co13').val(data[i].sviw_co13);
			jq('#sviw_co14').val("");jq('#sviw_co14').val(data[i].sviw_co14);
			jq('#sviw_co15').val("");jq('#sviw_co15').val(data[i].sviw_co15);
			jq('#sviw_co16').val("");jq('#sviw_co16').val(data[i].sviw_co16);
			jq('#sviw_co17').val("");jq('#sviw_co17').val(data[i].sviw_co17);
			jq('#sviw_co18').val("");jq('#sviw_co18').val(data[i].sviw_co18);
			jq('#sviw_co19').val("");jq('#sviw_co19').val(data[i].sviw_co19);
			jq('#sviw_co20').val("");jq('#sviw_co20').val(data[i].sviw_co20);
			jq('#sviw_suok').val("");jq('#sviw_suok').val(data[i].sviw_suok);
			jq('#sviw_sukr').val("");jq('#sviw_sukr').val(data[i].sviw_sukr);
			jq('#sviw_suar').val("");jq('#sviw_suar').val(data[i].sviw_suar);
			jq('#sviw_fokd').val("");jq('#sviw_fokd').val(data[i].sviw_fokd);
					
			//for a future update
			jq('#updateId').val("");jq('#updateId').val(data[i].sviw_knso);
			
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
			"url": getLanguage('SE')
        }
	});
	
	jq('#language').val("SE");
	
	
});
