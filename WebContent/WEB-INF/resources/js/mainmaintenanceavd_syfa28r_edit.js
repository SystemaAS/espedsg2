  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  jq(function() {
	  //----------------
	  //OPP.TYPE search
	  //----------------
	  jq('#kowxxx2IdLink').click(function() {
		  jq('#kowxxx2IdLink').attr('target','_blank');
		  window.open("mainmaintenance_childwindow_opptype.do?action=doFind&ko2kod=" + jq('#kowxxx2').val() + '&ctype=kowxxx2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#kowxxx2IdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#kowxxx2IdLink').click();
		  }
	  });
  });
  
//--------------------------------
  //START Model dialog "Dupliser"
  //------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			modal: true
		});
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".copyLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("copyLink","");
		  //setters (add more if needed)
		  //jq('#dialog'+counterIndex).dialog( "option", "width", 400);
		  jq('#dialog'+counterIndex).dialog( "option", "title", "Kopier " + "Avd: " + jq('#originalAvd'+counterIndex).val() + " Lnr: " +  jq('#originalLnr'+counterIndex).val());
		  
		  //deal with buttons for this modal window
		  jq('#dialog'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSave"+counterIndex,	
				 text: "Fortsett",
				 click: function(){
	            	 		if(jq("#dialog"+counterIndex).find('.fromAvd').val()!='' && jq("#dialog"+counterIndex).find('.toAvd').val()!=''){
	            	 			jq('#copyForm'+counterIndex).submit();
	            	 		}	
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancel"+counterIndex,
			 	 text: "Avbryt", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSave"+counterIndex).button("option", "disabled", true);
					 		jq("#fromAvd"+counterIndex).val("");
					 		jq("#toAvd"+counterIndex).val("");
							jq( this ).dialog( "close" ); 
					 		  
				 		} 
	 	 		 } ] 
			  
		  });
		  //init values
		  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  
		  //open now
		  jq('#dialog'+counterIndex).dialog('open');
		 
	  });
  });
  //Events for the drop downs (some kind of "implicit validation" since all input values are mandatory)
  jq(function() {
	  jq(".fromAvd").blur(function() {
		  if(jq("#dialog"+counterIndex).find('.fromAvd').val()!='' && jq("#dialog"+counterIndex).find('.toAvd').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
	  jq(".toAvd").blur(function() {
		  if(jq("#dialog"+counterIndex).find('.fromAvd').val()!='' && jq("#dialog"+counterIndex).find('.toAvd').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
  });
  //----------------------------
  //END Model dialog "Dupliser"
  //----------------------------
	
 
  //-------------------
  //Datatables jquery
  //-------------------
  //private function
  function filterGlobal () {
    jq('#childList').dataTable().search(
    	jq('#childList_filter').val()
    ).draw();
  }
  
  jq(document).ready(function() {
	  var lang = jq('#language').val();
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#childList').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
    	  "scrollY": "350px",
    	  "scrollCollapse":  true,
    	  "columnDefs": [{ "type": "num", "targets": 1 }],
    	  "order": [[ 1, "asc" ]],
    	  "lengthMenu": [ 75, 100],
    	  "language": { "url": getLanguage(lang) }
  	  });
      
      //event on input field for search
      jq('input.childList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
 
  