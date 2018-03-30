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

		jq("#svew_knso").prop("readonly", false);
		jq("#svew_knso").removeClass("inputTextReadOnly");
		jq("#svew_knso").addClass("inputTextMediumBlueMandatoryField");
		
		//init of int and floats
		jq('#svew_ankv').val("0");	
		jq('#svew_brut').val("0");	
		jq('#svew_fabl').val("0");	
		jq('#svew_kota').val("0");	
		jq('#svew_kot2').val("0");	
		jq('#svew_kot3').val("0");	
		jq('#svew_kot4').val("0");	
		jq('#svew_kot5').val("0");	
		jq('#svew_neto').val("0");	
		jq('#svew_stva').val("0");	
		jq('#svew_stva2').val("0");	
		jq('#svew_vano').val("0");	
		jq('#svew_suar').val("0");	
		jq('#svew_suok').val("0");	
		jq('#svew_sukr').val("0");
		
		//for update
		jq('#updateId').val("");
		
	});

	jq("#formRecord").submit(function() {
		if (jq('#svew_ankv').val() =='' ) {
			jq('#svew_ankv').val("0");
		}
		if (jq('#svew_brut').val() =='' ) {
			jq('#svew_brut').val("0");
		} else if (jq('#svew_brut').val() !='') {  //float
			jq("#svew_brut").val(jq("#svew_brut").val().replace(',', '.'));
		}
		if (jq('#svew_fabl').val() =='' ) {
			jq('#svew_fabl').val("0");
		} else if (jq('#svew_fabl').val() !='') {  //float
			jq("#svew_fabl").val(jq("#svew_fabl").val().replace(',', '.'));
			
		}
		if (jq('#svew_kota').val() =='' ) {
			jq('#svew_kota').val("0");
		}
		if (jq('#svew_kot2').val() =='' ) {
			jq('#svew_kot2').val("0");
		}
		if (jq('#svew_kot3').val() =='' ) {
			jq('#svew_kot3').val("0");
		}
		if (jq('#svew_kot4').val() =='' ) {
			jq('#svew_kot4').val("0");
		}
		if (jq('#svew_kot5').val() =='' ) {
			jq('#svew_kot5').val("0");
		}
		if (jq('#svew_neto').val() =='' ) {
			jq('#svew_neto').val("0");
		} else if (jq('#svew_neto').val() !='') {  //float
			jq("#svew_neto").val(jq("#svew_neto").val().replace(',', '.'));
		}		
		if (jq('#svew_stva').val() =='' ) {
			jq('#svew_stva').val("0");
		}
		if (jq('#svew_stva').val() =='' ) {
			jq('#svew_stva').val("0");
		}
		if (jq('#svew_stva2').val() =='' ) {
			jq('#svew_stva2').val("0");
		}
		if (jq('#svew_suar').val() =='' ) {
			jq('#svew_suar').val("0");
		}
		if (jq('#svew_sukr').val() =='' ) {
			jq('#svew_sukr').val("0");
		}
		if (jq('#svew_suok').val() =='' ) {
			jq('#svew_suok').val("0");
		}	
		if (jq('#svew_vano').val() =='' ) {
			jq('#svew_vano').val("0");
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
  
    
    jq('#svew_ulkdIdLink').click(function() {
    	jq('#svew_ulkdIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_ulkd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	   
   
    jq('#svew_vataIdLink').click(function() {
    	jq('#svew_vataIdLink').attr('target','_blank');
	    window.open('tdsexport_edit_items_childwindow_tulltaxa.do?action=doInit&vkod=' + jq('#svew_vata').val() + '&caller=svew_vata', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	   
 
    jq('#svew_eup1IdLink').click(function() {
    	jq('#svew_eup1IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_eup1', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	
    
    jq('#svew_eup2IdLink').click(function() {
    	jq('#svew_eup2IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_eup2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
  
    jq('#svew_koslIdLink').click(function() {
    	jq('#svew_koslIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_kosl', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    
    jq('#svew_kos2IdLink').click(function() {
    	jq('#svew_kos2IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_kos2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_kos3IdLink').click(function() {
    	jq('#svew_kos3IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_kos3', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_kos4IdLink').click(function() {
    	jq('#svew_kos4IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_kos4', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_kos5IdLink').click(function() {
    	jq('#svew_kos5IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_kos5', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_bit1IdLink').click(function() {
    	jq('#svew_bit1IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_bit1', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_bit2IdLink').click(function() {
    	jq('#svew_bit2IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_bit2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_bit3IdLink').click(function() {
    	jq('#svew_bit3IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_bit3', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_bit4IdLink').click(function() {
    	jq('#svew_bit4IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_bit4', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_bit5IdLink').click(function() {
    	jq('#svew_bit5IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_bit5', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_bit6IdLink').click(function() {
    	jq('#svew_bit6IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_bit6', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_bit7IdLink').click(function() {
    	jq('#svew_bit7IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_bit7', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_bit8IdLink').click(function() {
    	jq('#svew_bit8IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_bit8', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_bit9IdLink').click(function() {
    	jq('#svew_bit9IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_bit9', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_tit1IdLink').click(function() {
    	jq('#svew_tit1IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_tit1', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_tit2IdLink').click(function() {
    	jq('#svew_tit2IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_tit2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_tit3IdLink').click(function() {
    	jq('#svew_tit3IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_tit3', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_tit4IdLink').click(function() {
    	jq('#svew_tit4IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_tit4', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_tit5IdLink').click(function() {
    	jq('#svew_tit5IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_tit5', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_tit6IdLink').click(function() {
    	jq('#svew_tit6IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_tit6', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_tit7IdLink').click(function() {
    	jq('#svew_tit7IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_tit7', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_tit8IdLink').click(function() {
    	jq('#svew_tit8IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_tit8', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_tit9IdLink').click(function() {
    	jq('#svew_tit9IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_tit9', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    jq('#svew_laglIdLink').click(function() {
    	jq('#svew_laglIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=svew_lagl', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    

    
	jq('#svew_fabl').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#svew_brut').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#svew_neto').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#svew_kota').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#svew_kota').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#svew_kot2').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#svew_kot3').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#svew_kot4').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#svew_kot5').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#svew_ankv').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#svew_stva').calculator({ showOn: 'button',  
		buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
	jq('#svew_stva2').calculator({ showOn: 'button',  
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
	var svew_knnr = record[0];
	var svew_knso = record[1];
	
	jq("#svew_knso").prop("readonly", true);
	jq("#svew_knso").removeClass("inputTextMediumBlueMandatoryField");
	jq("#svew_knso").addClass("inputTextReadOnly");
	
	
	jq.ajax({
	  type: 'GET',
	  url: 'getSpecificRecord_svew.do',
	  data: { applicationUser : jq('#applicationUser').val(), 
		  svew_knnr : svew_knnr,
		  svew_knso  : svew_knso },
	  dataType: 'json',
	  cache: false,
	  contentType: 'application/json',
	  success: function(data) {
	  	var len = data.length;
		for ( var i = 0; i < len; i++) {
			jq('#svew_knso').val("");jq('#svew_knso').val(data[i].svew_knso);
			jq('#svew_vasl').val("");jq('#svew_vasl').val(data[i].svew_vasl);
			jq('#svew_vas2').val("");jq('#svew_vas2').val(data[i].svew_vas2);
			jq('#svew_vas3').val("");jq('#svew_vas3').val(data[i].svew_vas3);
			jq('#svew_vas4').val("");jq('#svew_vas4').val(data[i].svew_vas4);
			jq('#svew_vas5').val("");jq('#svew_vas5').val(data[i].svew_vas5);
			jq('#svew_vano').val("");jq('#svew_vano').val(data[i].svew_vano);
			jq('#svew_vata').val("");jq('#svew_vata').val(data[i].svew_vata);
			jq('#svew_lagi').val("");jq('#svew_lagi').val(data[i].svew_lagi);
			jq('#svew_ulkd').val("");jq('#svew_ulkd').val(data[i].svew_ulkd);
			jq('#svew_vati').val("");jq('#svew_vati').val(data[i].svew_vati);
			jq('#svew_vat4').val("");jq('#svew_vat4').val(data[i].svew_vat4);
			jq('#svew_vat5').val("");jq('#svew_vat5').val(data[i].svew_vat5);
			jq('#svew_komr').val("");jq('#svew_komr').val(data[i].svew_komr);
			jq('#svew_fnkd').val("");jq('#svew_fnkd').val(data[i].svew_fnkd);
			jq('#svew_brut').val(data[i].svew_brut);jq('#svew_brut').val(jq('#svew_brut').val().replace('.',','))
			jq('#svew_eup1').val("");jq('#svew_eup1').val(data[i].svew_eup1);
			jq('#svew_neto').val(data[i].svew_neto);jq('#svew_neto').val(jq('#svew_neto').val().replace('.',','))			
			jq('#svew_eup2').val("");jq('#svew_eup2').val(data[i].svew_eup2);
			jq('#svew_betk').val("");jq('#svew_betk').val(data[i].svew_betk);
			jq('#svew_atin').val("");jq('#svew_atin').val(data[i].svew_atin);
			jq('#svew_kono').val("");jq('#svew_kono').val(data[i].svew_kono);
			jq('#svew_ankv').val("");jq('#svew_ankv').val(data[i].svew_ankv);
			jq('#svew_stva').val("");jq('#svew_stva').val(data[i].svew_stva);
			jq('#svew_stva2').val("");jq('#svew_stva2').val(data[i].svew_stva2);
			jq('#svew_fabl').val(data[i].svew_fabl);jq('#svew_fabl').val(jq('#svew_fabl').val().replace('.',','))
			jq('#svew_lagt').val("");jq('#svew_lagt').val(data[i].svew_lagt);
			jq('#svew_lagl').val("");jq('#svew_lagl').val(data[i].svew_lagl);
			jq('#svew_call').val("");jq('#svew_call').val(data[i].svew_call);
			jq('#svew_bit1').val("");jq('#svew_bit1').val(data[i].svew_bit1);
			jq('#svew_bii1').val("");jq('#svew_bii1').val(data[i].svew_bii1);
			jq('#svew_bit2').val("");jq('#svew_bit2').val(data[i].svew_bit2);
			jq('#svew_bii2').val("");jq('#svew_bii2').val(data[i].svew_bii2);
			jq('#svew_bit3').val("");jq('#svew_bit3').val(data[i].svew_bit3);
			jq('#svew_bii3').val("");jq('#svew_bii3').val(data[i].svew_bii3);
			jq('#svew_bit4').val("");jq('#svew_bit4').val(data[i].svew_bit4);
			jq('#svew_bii4').val("");jq('#svew_bii4').val(data[i].svew_bii4);
			jq('#svew_bit5').val("");jq('#svew_bit5').val(data[i].svew_bit5);
			jq('#svew_bii5').val("");jq('#svew_bii5').val(data[i].svew_bii5);
			jq('#svew_bit6').val("");jq('#svew_bit6').val(data[i].svew_bit6);
			jq('#svew_bii6').val("");jq('#svew_bii6').val(data[i].svew_bii6);
			jq('#svew_bit7').val("");jq('#svew_bit7').val(data[i].svew_bit7);
			jq('#svew_bii7').val("");jq('#svew_bii7').val(data[i].svew_bii7);
			jq('#svew_bit8').val("");jq('#svew_bit8').val(data[i].svew_bit8);
			jq('#svew_bii8').val("");jq('#svew_bii8').val(data[i].svew_bii8);
			jq('#svew_bit9').val("");jq('#svew_bit9').val(data[i].svew_bit9);
			jq('#svew_bii9').val("");jq('#svew_bii9').val(data[i].svew_bii9);
			jq('#svew_suko').val("");jq('#svew_suko').val(data[i].svew_suko);
			jq('#svew_sutx').val("");jq('#svew_sutx').val(data[i].svew_sutx);
			jq('#svew_sut2').val("");jq('#svew_sut2').val(data[i].svew_sut2);
			jq('#svew_sut3').val("");jq('#svew_sut3').val(data[i].svew_sut3);
			jq('#svew_sut4').val("");jq('#svew_sut4').val(data[i].svew_sut4);
			jq('#svew_sut5').val("");jq('#svew_sut5').val(data[i].svew_sut5);
			jq('#svew_suk6').val("");jq('#svew_suk6').val(data[i].svew_suk6);
			jq('#svew_sut6').val("");jq('#svew_sut6').val(data[i].svew_sut6);
			jq('#svew_sut7').val("");jq('#svew_sut7').val(data[i].svew_sut7);
			jq('#svew_sut8').val("");jq('#svew_sut8').val(data[i].svew_sut8);
			jq('#svew_sut9').val("");jq('#svew_sut9').val(data[i].svew_sut9);
			jq('#svew_suta').val("");jq('#svew_suta').val(data[i].svew_suta);
			jq('#svew_sukb').val("");jq('#svew_sukb').val(data[i].svew_sukb);
			jq('#svew_sutb').val("");jq('#svew_sutb').val(data[i].svew_sutb);
			jq('#svew_sutc').val("");jq('#svew_sutc').val(data[i].svew_sutc);
			jq('#svew_sutd').val("");jq('#svew_sutd').val(data[i].svew_sutd);
			jq('#svew_sute').val("");jq('#svew_sute').val(data[i].svew_sute);
			jq('#svew_sutf').val("");jq('#svew_sutf').val(data[i].svew_sutf);
			jq('#svew_tik1').val("");jq('#svew_tik1').val(data[i].svew_tik1);
			jq('#svew_tik2').val("");jq('#svew_tik2').val(data[i].svew_tik2);
			jq('#svew_tik3').val("");jq('#svew_tik3').val(data[i].svew_tik3);
			jq('#svew_tik4').val("");jq('#svew_tik4').val(data[i].svew_tik4);
			jq('#svew_tik5').val("");jq('#svew_tik5').val(data[i].svew_tik5);
			jq('#svew_tik6').val("");jq('#svew_tik6').val(data[i].svew_tik6);
			jq('#svew_tik7').val("");jq('#svew_tik7').val(data[i].svew_tik7);
			jq('#svew_tik8').val("");jq('#svew_tik8').val(data[i].svew_tik8);
			jq('#svew_tik9').val("");jq('#svew_tik9').val(data[i].svew_tik9);
			jq('#svew_tit1').val("");jq('#svew_tit1').val(data[i].svew_tit1);
			jq('#svew_tit2').val("");jq('#svew_tit2').val(data[i].svew_tit2);
			jq('#svew_tit3').val("");jq('#svew_tit3').val(data[i].svew_tit3);
			jq('#svew_tit4').val("");jq('#svew_tit4').val(data[i].svew_tit4);
			jq('#svew_tit5').val("");jq('#svew_tit5').val(data[i].svew_tit5);
			jq('#svew_tit6').val("");jq('#svew_tit6').val(data[i].svew_tit6);
			jq('#svew_tit7').val("");jq('#svew_tit7').val(data[i].svew_tit7);
			jq('#svew_tit8').val("");jq('#svew_tit8').val(data[i].svew_tit8);
			jq('#svew_tit9').val("");jq('#svew_tit9').val(data[i].svew_tit9);
			jq('#svew_tix1').val("");jq('#svew_tix1').val(data[i].svew_tix1);
			jq('#svew_tix2').val("");jq('#svew_tix2').val(data[i].svew_tix2);
			jq('#svew_tix3').val("");jq('#svew_tix3').val(data[i].svew_tix3);
			jq('#svew_tix4').val("");jq('#svew_tix4').val(data[i].svew_tix4);
			jq('#svew_tix5').val("");jq('#svew_tix5').val(data[i].svew_tix5);
			jq('#svew_tix6').val("");jq('#svew_tix6').val(data[i].svew_tix6);
			jq('#svew_tix7').val("");jq('#svew_tix7').val(data[i].svew_tix7);
			jq('#svew_tix8').val("");jq('#svew_tix8').val(data[i].svew_tix8);
			jq('#svew_tix9').val("");jq('#svew_tix9').val(data[i].svew_tix9);
			jq('#svew_godm').val("");jq('#svew_godm').val(data[i].svew_godm);
			jq('#svew_god2').val("");jq('#svew_god2').val(data[i].svew_god2);
			jq('#svew_god3').val("");jq('#svew_god3').val(data[i].svew_god3);
			jq('#svew_god4').val("");jq('#svew_god4').val(data[i].svew_god4);
			jq('#svew_god5').val("");jq('#svew_god5').val(data[i].svew_god5);
			jq('#svew_kota').val("");jq('#svew_kota').val(data[i].svew_kota);
			jq('#svew_kot2').val("");jq('#svew_kot2').val(data[i].svew_kot2);
			jq('#svew_kot3').val("");jq('#svew_kot3').val(data[i].svew_kot3);
			jq('#svew_kot4').val("");jq('#svew_kot4').val(data[i].svew_kot4);
			jq('#svew_kot5').val("");jq('#svew_kot5').val(data[i].svew_kot5);
			jq('#svew_kosl').val("");jq('#svew_kosl').val(data[i].svew_kosl);
			jq('#svew_kos2').val("");jq('#svew_kos2').val(data[i].svew_kos2);
			jq('#svew_kos3').val("");jq('#svew_kos3').val(data[i].svew_kos3);
			jq('#svew_kos4').val("");jq('#svew_kos4').val(data[i].svew_kos4);
			jq('#svew_kos5').val("");jq('#svew_kos5').val(data[i].svew_kos5);
			jq('#svew_co01').val("");jq('#svew_co01').val(data[i].svew_co01);
			jq('#svew_co02').val("");jq('#svew_co02').val(data[i].svew_co02);
			jq('#svew_co03').val("");jq('#svew_co03').val(data[i].svew_co03);
			jq('#svew_co04').val("");jq('#svew_co04').val(data[i].svew_co04);
			jq('#svew_co05').val("");jq('#svew_co05').val(data[i].svew_co05);
			jq('#svew_co06').val("");jq('#svew_co06').val(data[i].svew_co06);
			jq('#svew_co07').val("");jq('#svew_co07').val(data[i].svew_co07);
			jq('#svew_co08').val("");jq('#svew_co08').val(data[i].svew_co08);
			jq('#svew_co09').val("");jq('#svew_co09').val(data[i].svew_co09);
			jq('#svew_co10').val("");jq('#svew_co10').val(data[i].svew_co10);
			jq('#svew_co11').val("");jq('#svew_co11').val(data[i].svew_co11);
			jq('#svew_co12').val("");jq('#svew_co12').val(data[i].svew_co12);
			jq('#svew_co13').val("");jq('#svew_co13').val(data[i].svew_co13);
			jq('#svew_co14').val("");jq('#svew_co14').val(data[i].svew_co14);
			jq('#svew_co15').val("");jq('#svew_co15').val(data[i].svew_co15);
			jq('#svew_co16').val("");jq('#svew_co16').val(data[i].svew_co16);
			jq('#svew_co17').val("");jq('#svew_co17').val(data[i].svew_co17);
			jq('#svew_co18').val("");jq('#svew_co18').val(data[i].svew_co18);
			jq('#svew_co19').val("");jq('#svew_co19').val(data[i].svew_co19);
			jq('#svew_co20').val("");jq('#svew_co20').val(data[i].svew_co20);
			jq('#svew_suok').val("");jq('#svew_suok').val(data[i].svew_suok);
			jq('#svew_sukr').val("");jq('#svew_sukr').val(data[i].svew_sukr);
			jq('#svew_suar').val("");jq('#svew_suar').val(data[i].svew_suar);
			
			
			//for a future update
			jq('#updateId').val("");jq('#updateId').val(data[i].svew_knso);
			
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
