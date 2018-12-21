  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  function getDataFromBrreg(element){
		var orgnr = jq('#syrg').val();
		var knavn = jq('#knavn').val();
		var adr1 = jq('#adr1').val();
		var adr3 = jq('#adr3').val();
		var postnr = jq('#postnr').val();
		var syland = jq('#syland').val();
		var spraak = jq('#spraak').val();
		
		if (orgnr != ""&& (knavn == '' && adr1 == '' && adr3 == ''&& postnr == '' && syland == '' && spraak == '')) {
			jq.getJSON('getSpecificRecord_enhet_brreg.do', {
				applicationUser : jq('#applicationUser').val(),
				orgnr : orgnr,
				ajax : 'true'
			}, function(data) {
	
				jq("#knavn").val(data.navn);
				jq("#knavn").change();
				jq("#spraak").val("N");
				jq("#spraak").change();

				if (data.postadresse !== undefined ) {  //Enhet
					jq("#adr1").val(data.postadresse.adresse);
					jq("#adr1").change();
					jq("#adr3").val(data.postadresse.poststed);
					jq("#adr3").change();
					jq("#postnr").val(data.postadresse.postnummer);
					jq("#postnr").change();
					jq("#syland").val(data.postadresse.landkode);
					jq("#syland").change();
				} else {
					if (data.beliggenhetsadresse !== undefined ) {  //Underenhet
						jq("#adr1").val(data.beliggenhetsadresse.adresse);
						jq("#adr1").change();
						jq("#adr3").val(data.beliggenhetsadresse.poststed);
						jq("#adr3").change();
						jq("#postnr").val(data.beliggenhetsadresse.postnummer);
						jq("#postnr").change();
						jq("#syland").val(data.beliggenhetsadresse.landkode);
						jq("#syland").change();
					}
				}

			});
			
			
		}	

		getDataFromBrregAsText(element);
		
  }
  
  
 function getDataFromBrregAsText(record){
	jq.ajax({
		type : 'GET',
		url : 'getSpecificRecord_enhet_brreg.do',
		data : {
			applicationUser : jq('#applicationUser').val(),
			orgnr : jq('#syrg').val()
		},
		dataType : 'text',
		cache : false,
		contentType : 'application/json',
		success : function(data) {
			var rows = 1;
			var cols = 46;
			jq("#ehp").text("Ikke nedlasted.");  //default, in missing in  brreg
			if (data.length > 0) {
				var len = data.length;
				var rows = len/cols;
				var someExtra = 2;
				rows = rows + someExtra;
				jq("#ehp").text(data.toString());
			}
			jq('#ehp').attr('rows',rows);	
			jq('#ehp').attr('cols', cols);	
			
		},
		error : function() {
			alert('Error loading ...');
		}
	});
	
 }
		
		
jq(function() {

    jq('#sylandIdLink').click(function() {
    	jq('#sylandIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=syland', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    
    jq('#valkodIdLink').click(function() {
    	jq('#valkodIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=valkod', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	    
    
    jq('#syopdtIdLink').click(function() {
    	jq('#syopdtIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=syopdt', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    
    jq('#sylikvIdLink').click(function() {
    	jq('#sylikvIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sylikv', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });

    jq('#fmotIdLink').click(function() {
    	jq('#fmotIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=fmot', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });   
    
    jq('#syfr03IdLink').click(function() {
    	jq('#syfr03IdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=syfr03', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });      
    
    
}); 
  