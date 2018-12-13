  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
 
  
  jq(function() {
	  jq("#fedat2").datepicker({ 
		  onClose: function() {
			    /* Validate a specific element: */
			  	refreshCustomValidity(jq('#fedat2')[0]);
		   },
		  dateFormat: 'yymmdd',
		  firstDay: 1 //monday
	  });
	  //
	  jq('#fedat2').focus(function() {
	    	if(jq('#fedat2').val()!=''){
	    		refreshCustomValidity(jq('#fedat2')[0]);
	    	}
	  });
	  jq('#wsfajn').focus(function() {
	    	if(jq('#wsfajn').val()!=''){
	    		refreshCustomValidity(jq('#wsfajn')[0]);
	    	}
	  });
  });
  
  //Global functions
  function g_getCurrentYearStr(){
	  return new Date().getFullYear().toString();
  }
  function g_getCurrentMonthStr(){
	  var currentMonth = new Date().getMonth() + 1;
	  var currentMonthStr = currentMonth.toString();
	  if (currentMonth < 10) { currentMonthStr = '0' + currentMonth; }
	  return currentMonthStr;
  }
   jq(function() {
	  jq('#newRecordButton').click(function() {
		  jq("#fefirm").val('');
		  jq("#fetime").val('');
		  jq("#fefrom").val('');
		  jq("#feto").val('');
		  jq("#feleng").val('');
		  //jq("#levNavn").val(levNavn);
		  jq("#fepri1").val('');
		  jq("#fepri2").val('');
		  jq("#fecurr").val('');
		  
		  jq('#isModeUpdate').val("");
		  //jq('#submit').css("visibility", "hidden");
		  
	  });
  });
  
  //Links on child windows
  jq(function() {
	  //frisokvei koder child window search
	  jq('#fskodeIdLink').click(function() {
		jq('#fskodeIdLink').attr('target','_blank');  
		window.open('transportdisp_workflow_childwindow_frisokveicodes.do?action=doFind',"frisokveiCodesWin","top=300px,left=50px,height=600px,width=650px,scrollbars=no,status=no,location=no");
	  });
	  //frisokvei giltighetskoder
	  jq('#fsverdiIdLink').click(function() {
			jq('#fsverdiIdLink').attr('target','_blank');  
			window.open('transportdisp_workflow_childwindow_frisokveicodes_giltihetslist.do?action=doFind&avd=' + jq('#avd').val() + '&opd=' + jq('#opd').val() + '&kode=' + jq('#fskode').val(),"frisokveiCodesWin","top=300px,left=100px,height=600px,width=650px,scrollbars=no,status=no,location=no");
	  });
	  //frisokvei dok.koder child window search
	  jq('#fsdokkIdLink').click(function() {
		jq('#fsdokkIdLink').attr('target','_blank');  
		window.open('transportdisp_workflow_childwindow_frisokveidoccodes.do?action=doFind',"frisokveiDocCodesWin","top=300px,left=150px,height=600px,width=650px,scrollbars=no,status=no,location=no");
	  });
	  
	  
  });
  

  //-------------------
  //Fetch specific line
  //-------------------
  function getItemData(record) {
		var FIELD_SEPARATOR = "@";
	  	var htmlValue = record.id;
	  	var applicationUser = jq('#applicationUser').val();
	  	//alert(htmlValue);
	  	htmlValue = htmlValue.replace("recordUpdate_","");
	  	var fields = htmlValue.split(FIELD_SEPARATOR);
	  	var requestString = "user=" + jq('#applicationUser').val() + "&avd=" + jq('#avd').val() + "&opd=" + jq('#opd').val() + "&mode=I" +
	  						"&o_fskode=" + fields[0] + "&o_fssok=" + fields[1];
	  	//DEBUG--> alert(requestString);
	  	//http://user=JOVO&avd=75&opd=103&mode=I&o_fskode=IFB&o_fssok=70701550001424817 
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getFrisokveiDetailLine_TransportDisp.do',
	  	  data: { applicationUser : applicationUser, 
	  		  	  requestString : requestString }, 
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//alert(data[i].fask);
				
				jq('#isModeUpdate').val("");jq('#isModeUpdate').val("true");
				jq('#fskodeKey').val("");jq('#fskodeKey').val(data[i].kode); //hidden field
				jq('#fssokKey').val("");jq('#fssokKey').val(data[i].sok); //hidden field
				//read only field(s)
				jq('#fskode').val("");
				jq('#fskode').prop("readonly", true);
				jq('#fskode').removeClass("inputTextMediumBlueMandatoryField");
				jq('#fskode').addClass("inputTextReadOnly");
				//fields
				jq('#fskode').val("");jq('#fskode').val(data[i].kode);
				jq('#fssok').val("");jq('#fssok').val(data[i].sok);
				jq('#fsdokk').val("");jq('#fsdokk').val(data[i].dokk);
				/*
				jq('#buoavd').val("");
				if(data[i].buoavd!='0'){
					jq('#buoavd').val(data[i].buoavd);
				}
				*/
					
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
	  	
	}

  
  //populate form read-only fields
  function setDepartureData(element){
	  var record = element.id.split('@');
	  var fefirm = record[0].replace("fefirm_","");
	  var fetime = record[1].replace("fetime_","");
	  var fefrom = record[2].replace("fefrom_","");
	  var feto = record[3].replace("feto_","");
	  var feleng = record[4].replace("feleng_","");
	  var levNavn = record[5].replace("levNavn_","");
	  var fepri1 = record[6].replace("fepri1_","");
	  var fepri2 = record[7].replace("fepri2_","");
	  var fecurr = record[8].replace("fecurr_","");
	  //
	  jq("#fefirm").val(fefirm);
	  jq("#fetime").val(fetime);
	  jq("#fefrom").val(fefrom);
	  jq("#feto").val(feto);
	  jq("#feleng").val(feleng);
	  //jq("#levNavn").val(levNavn);
	  jq("#fepri1").val(fepri1);
	  jq("#fepri2").val(fepri2);
	  jq("#fecurr").val(fecurr);
	  //
	  //jq('#submit').css("visibility", "visible");
	 
	  
  }
  
  	//---------------------------------------
	//DELETE Invoice line
	//This is done in order to present a jquery
	//Alert modal pop-up
	//----------------------------------------
	function doDeleteItemLine(element){
	  //start
		var record = element.id.split('@');
		var fetur = record[0].replace("fetur_","");
		var feavd = record[1].replace("feavd_","");
		var fefrom = record[2].replace("fefrom_","");
		var feto = record[3].replace("feto_","");

	  //Start dialog
	  jq('<div></div>').dialog({
      modal: true,
      title: "Dialog - Slett avg. " + fefrom + "/" + feto,
      buttons: {
	        Fortsett: function() {
      		jq( this ).dialog( "close" );
	            //do delete
	            jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	            window.location = "transportdisp_workflow_ferjeoverfarter_edit.do?action=doDelete&feavd=" + feavd + "&fetur=" + fetur + "&fefrom=" + fefrom + "&feto=" + feto;
	        },
	        Avbryt: function() {
	            jq( this ).dialog( "close" );
	        }
      },
      open: function() {
	  		  var markup = "Er du sikker på at du vil slette denne?";
	          jq(this).html(markup);
	          //make Cancel the default button
	          jq(this).siblings('.ui-dialog-buttonpane').find('button:eq(1)').focus();
	     }
	  });  //end dialog
	}
	
	//-------------------
	  //Datatables jquery
	  //-------------------
	 jq(document).ready(function() {
		  jq('#listDepartures').dataTable( {
			  "searchHighlight": true,
			  "dom": '<"localFilter"f>t<"bottom"lirp><"clear">',
			  "scrollY": "200px",
			  "scrollCollapse": true,
			  "lengthMenu": [ 50, 75, 100],
			  "fnDrawCallback": function( oSettings ) {
				jq('.dataTables_filter input').addClass("inputText12LightYellow");
		      }
			} );
			
		    //event on input field for search
		    jq('input.listDepartures_filter').on( 'keyup click', function () {
		    	jq('#listDepartures').DataTable().search(
		        		jq('#listDepartures_filter').val()
		        ).draw();
		    });
		    
	  });
  