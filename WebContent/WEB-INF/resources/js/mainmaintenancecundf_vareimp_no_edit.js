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
		jq("#varenr").prop("readonly", false);
		jq("#varenr").removeClass("inputTextReadOnly");
		jq("#varenr").addClass("inputTextMediumBlueMandatoryField");
		
		//for update
		jq('#updateId').val("");
		
	});

	jq("#formRecord").submit(function() {
		jq.blockUI({
			message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT
		});
	});

    jq('#w2vfIdLink').click(function() {
    	jq('#w2vfIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=w2vf', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	

    jq('#w2lkIdLink').click(function() {
    	jq('#w2lkIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=w2lk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	  
    
    jq('#w2vntiIdLink').click(function() {
    	jq('#w2lkIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=w2vnti', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	 
    
    jq('#w2tnIdLink').click(function() {
    	jq('#w2lkIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=w2tn', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	  
    
    jq('#w2preIdLink').click(function() {
    	jq('#w2lkIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=w2pre', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	 	
   
    jq('#enhetIdLink').click(function() {
    	jq('#enhetIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=enhet', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	 
    
    jq('#avgkodeIdLink').click(function() {
    	jq('#avgkodeIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=avgkode', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	
    
    jq('#kommref1IdLink').click(function() {
    	jq('#kommref1IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=kommref', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	
    
    jq('#kommref2IdLink').click(function() {
    	jq('#kommref2IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=kommref', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	 
    
    jq('#w2valIdLink').click(function() {
    	jq('#w2valIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=w2val', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
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
	var levenr = record[0];
	var varenr = record[1];
	
	jq("#varenr").prop("readonly", true);
	jq("#varenr").removeClass("inputTextMediumBlueMandatoryField");
	jq("#varenr").addClass("inputTextReadOnly");
	
	
	jq.ajax({
	  type: 'GET',
	  url: 'getSpecificRecord_sadvare.do',
	  data: { applicationUser : jq('#applicationUser').val(), 
		  levenr : levenr,
		  varenr  : varenr },
	  dataType: 'json',
	  cache: false,
	  contentType: 'application/json',
	  success: function(data) {
	  	var len = data.length;
		for ( var i = 0; i < len; i++) {
			jq('#varenr').val("");jq('#varenr').val(data[i].varenr);
			jq('#varebe').val("");jq('#varebe').val(data[i].varebe);
			jq('#levenr').val("");jq('#levenr').val(data[i].levenr);
			jq('#w2vf').val("");jq('#w2vf').val(data[i].w2vf);
			jq('#w2lk').val("");jq('#w2lk').val(data[i].w2lk);
			jq('#w2vnti').val("");jq('#w2vnti').val(data[i].w2vnti);
			jq('#w2tn').val("");jq('#w2tn').val(data[i].w2tn);
			jq('#w2pre').val("");jq('#w2pre').val(data[i].w2pre);
			jq('#w2pva').val("");jq('#w2pva').val(data[i].w2pva);
			jq('#w2as').val("");jq('#w2as').val(data[i].w2as);
			jq('#w2mfr').val("");jq('#w2mfr').val(data[i].w2mfr);
			jq('#w2belt').val("");jq('#w2belt').val(data[i].w2belt);
			jq('#w2vktb').val("");jq('#w2vktb').val(data[i].w2vktb);
			jq('#w2ntm').val("");jq('#w2ntm').val(data[i].w2ntm);
			jq('#w2beln').val("");jq('#w2beln').val(data[i].w2beln);
			jq('#w2pros').val("");jq('#w2pros').val(data[i].w2pros);
			jq('#w2val').val("");jq('#w2val').val(data[i].w2val);
			jq('#w2vktn').val("");jq('#w2vktn').val(data[i].w2vktn);			
			jq('#w2akd1').val("");jq('#w2akd1').val(data[i].w2akd1);			
			jq('#w2asv1').val("");jq('#w2asv1').val(data[i].w2asv1);			
			jq('#w2asa1').val("");jq('#w2asa1').val(data[i].w2asa1);			
			jq('#w2agr1').val("");jq('#w2agr1').val(data[i].w2agr1);			
			jq('#w2abl1').val("");jq('#w2abl1').val(data[i].w2abl1);			
			jq('#w2bel').val("");jq('#w2bel').val(data[i].w2bel);			
			jq('#w2akd2').val("");jq('#w2akd2').val(data[i].w2akd2);			
			jq('#w2asv2').val("");jq('#w2asv2').val(data[i].w2asv2);			
			jq('#w2asa2').val("");jq('#w2asa2').val(data[i].w2asa2);			
			jq('#w2agr2').val("");jq('#w2agr2').val(data[i].w2agr2);			
			jq('#w2abl2').val("");jq('#w2abl2').val(data[i].w2abl2);			
			jq('#w2akd3').val("");jq('#w2akd3').val(data[i].w2akd3);			
			jq('#w2asv3').val("");jq('#w2asv3').val(data[i].w2asv3);			
			jq('#w2asa3').val("");jq('#w2asa3').val(data[i].w2asa3);			
			jq('#w2agr3').val("");jq('#w2agr3').val(data[i].w2agr3);			
			jq('#w2abl3').val("");jq('#w2abl3').val(data[i].w2abl3);			
			jq('#w2akd4').val("");jq('#w2akd4').val(data[i].w2akd4);			
			jq('#w2asv4').val("");jq('#w2asv4').val(data[i].w2asv4);			
			jq('#w2asa4').val("");jq('#w2asa4').val(data[i].w2asa4);			
			jq('#w2agr4').val("");jq('#w2agr4').val(data[i].w2agr4);			
			jq('#w2abl4').val("");jq('#w2abl4').val(data[i].w2abl4);			
			jq('#w2akd5').val("");jq('#w2akd5').val(data[i].w2akd5);			
			jq('#w2asv5').val("");jq('#w2asv5').val(data[i].w2asv5);			
			jq('#w2asa5').val("");jq('#w2asa5').val(data[i].w2asa5);			
			jq('#w2agr5').val("");jq('#w2agr5').val(data[i].w2agr5);			
			jq('#w2abl5').val("");jq('#w2abl5').val(data[i].w2abl5);			
			jq('#w2akd6').val("");jq('#w2akd6').val(data[i].w2akd6);			
			jq('#w2asv6').val("");jq('#w2asv6').val(data[i].w2asv6);			
			jq('#w2asa6').val("");jq('#w2asa6').val(data[i].w2asa6);			
			jq('#w2agr6').val("");jq('#w2agr6').val(data[i].w2agr6);			
			jq('#w2abl6').val("");jq('#w2abl6').val(data[i].w2abl6);			
			jq('#w2akd7').val("");jq('#w2akd7').val(data[i].w2akd7);			
			jq('#w2asv7').val("");jq('#w2asv7').val(data[i].w2asv7);			
			jq('#w2asa7').val("");jq('#w2asa7').val(data[i].w2asa7);			
			jq('#w2agr7').val("");jq('#w2agr7').val(data[i].w2agr7);			
			jq('#w2abl7').val("");jq('#w2abl7').val(data[i].w2abl7);			
			jq('#w2akd8').val("");jq('#w2akd8').val(data[i].w2akd8);			
			jq('#w2asv8').val("");jq('#w2asv8').val(data[i].w2asv8);			
			jq('#w2asa8').val("");jq('#w2asa8').val(data[i].w2asa8);			
			jq('#w2agr8').val("");jq('#w2agr8').val(data[i].w2agr8);			
			jq('#w2abl8').val("");jq('#w2abl8').val(data[i].w2abl8);			
			jq('#w2ft01').val("");jq('#w2ft01').val(data[i].w2ft01);			
			jq('#w2nt01').val("");jq('#w2nt01').val(data[i].w2nt01);			
			jq('#w2eh01').val("");jq('#w2eh01').val(data[i].w2eh01);			
			jq('#w2vt01').val("");jq('#w2vt01').val(data[i].w2vt01);			
			jq('#w2ft02').val("");jq('#w2ft02').val(data[i].w2ft02);			
			jq('#w2nt02').val("");jq('#w2nt02').val(data[i].w2nt02);			
			jq('#w2eh02').val("");jq('#w2eh02').val(data[i].w2eh02);			
			jq('#w2vt02').val("");jq('#w2vt02').val(data[i].w2vt02);			
			jq('#w2ft03').val("");jq('#w2ft03').val(data[i].w2ft03);			
			jq('#w2nt03').val("");jq('#w2nt03').val(data[i].w2nt03);			
			jq('#w2eh03').val("");jq('#w2eh03').val(data[i].w2eh03);			
			jq('#w2vt03').val("");jq('#w2vt03').val(data[i].w2vt03);			
			jq('#w2ft04').val("");jq('#w2ft04').val(data[i].w2ft04);			
			jq('#w2nt04').val("");jq('#w2nt04').val(data[i].w2nt04);			
			jq('#w2eh04').val("");jq('#w2eh04').val(data[i].w2eh04);			
			jq('#w2vt04').val("");jq('#w2vt04').val(data[i].w2vt04);			
			jq('#w2ft05').val("");jq('#w2ft05').val(data[i].w2ft05);			
			jq('#w2nt05').val("");jq('#w2nt05').val(data[i].w2nt05);			
			jq('#w2eh05').val("");jq('#w2eh05').val(data[i].w2eh05);			
			jq('#w2vt05').val("");jq('#w2vt05').val(data[i].w2vt05);			
			jq('#w2ft06').val("");jq('#w2ft06').val(data[i].w2ft06);			
			jq('#w2nt06').val("");jq('#w2nt06').val(data[i].w2nt06);			
			jq('#w2eh06').val("");jq('#w2eh06').val(data[i].w2eh06);			
			jq('#w2ft07').val("");jq('#w2ft07').val(data[i].w2ft07);			
			jq('#w2nt07').val("");jq('#w2nt07').val(data[i].w2nt07);			
			jq('#w2eh07').val("");jq('#w2eh07').val(data[i].w2eh07);			
			jq('#w2top1').val("");jq('#w2top1').val(data[i].w2top1);			
			jq('#w2cre1').val("");jq('#w2cre1').val(data[i].w2cre1);			
			jq('#w2top2').val("");jq('#w2top2').val(data[i].w2top2);			
			jq('#w2cre2').val("");jq('#w2cre2').val(data[i].w2cre2);			
			jq('#w2top3').val("");jq('#w2top3').val(data[i].w2top3);			
			jq('#w2cre3').val("");jq('#w2cre3').val(data[i].w2cre3);					
			jq('#w2top4').val("");jq('#w2top4').val(data[i].w2top4);					
			jq('#w2cre4').val("");jq('#w2cre4').val(data[i].w2cre4);					
			jq('#w2top5').val("");jq('#w2top5').val(data[i].w2top5);					
			jq('#w2cre5').val("");jq('#w2cre5').val(data[i].w2cre5);					
			jq('#w2top6').val("");jq('#w2top6').val(data[i].w2top6);					
			jq('#w2cre6').val("");jq('#w2cre6').val(data[i].w2cre6);					
			jq('#w2top7').val("");jq('#w2top7').val(data[i].w2top7);					
			jq('#w2cre7').val("");jq('#w2cre7').val(data[i].w2cre7);					
			jq('#w2top8').val("");jq('#w2top8').val(data[i].w2top8);					
			jq('#w2cre8').val("");jq('#w2cre8').val(data[i].w2cre8);					
			jq('#w2top9').val("");jq('#w2top9').val(data[i].w2top9);					
			jq('#w2cre9').val("");jq('#w2cre9').val(data[i].w2cre9);					
			jq('#w2top10').val("");jq('#w2top10').val(data[i].w2top10);					
			jq('#w2cre10').val("");jq('#w2cre10').val(data[i].w2cre10);					
	
			//for a future update
			jq('#updateId').val("");jq('#updateId').val(data[i].varenr);
			
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
