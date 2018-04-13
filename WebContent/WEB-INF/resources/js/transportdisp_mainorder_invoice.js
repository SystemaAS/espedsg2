	//this variable is a global jQuery var instead of using "$" all the time. Very handy
	var jq = jQuery.noConflict();
	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
	
	jq.ajaxSetup({ cache:false }); //to avoid F5 after every new version!

	jq(document).ready(function() {
		jq('#updCancelButton').hide();
	});
	
	jq(function() {
	  jq('#updCancelButton').click(function() {
		  window.location = 'transportdisp_mainorder_invoice.do?heavd='+ jq('#avd').val() + '&heopd=' + jq('#opd').val() + 
		  													"&hepro=" + jq('#hepro').val() + "&itemsType=O";
	  });
	});
	
	//Links on child windows
	jq(function() {
		  //supplier child window search
		  jq('#falevnIdLink').click(function() {
			jq('#falevnIdLink').attr('target','_blank');  
			window.open('transportdisp_workflow_childwindow_supplier.do?action=doInit&kode=' + jq('#falevn').val(),"supplierWin","top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no");
		  });
		  
		  //gebyr koder child window search
		  jq('#favkIdLink').click(function() {
			jq('#favkIdLink').attr('target','_blank');  
			window.open('transportdisp_workflow_childwindow_gebyrcode.do?action=doInit&kode=' + jq('#favk').val(),"gebyrCodesWin","top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no");
		  });
	});
	
	
	
	//Ferdigmelde (readyMark). 
	jq(function() {
	  	jq('#readyMarkButton').click(function() {
	  		jq.ajax({
			  	  type: 'GET',
			  	  url: 'updateReadyMarkInvoice.do',
			  	  data: { applicationUser : jq('#applicationUser').val(), 
			  		  	  avd : jq('#avd').val(),
			  		  	  opd : jq('#opd').val() }, 
			  	  dataType: 'json',
			  	  cache: false,
			  	  contentType: 'application/json',
			  	  success: function(data) {
			  		var len = data.length;
		  			for ( var i = 0; i < len; i++) { 
		  				jq('#readyMarkInvoice').text("");
		  				if(data[i].status!=''){
		  					jq('#readyMarkInvoice').removeClass( "isa_error" );
		  					jq('#readyMarkInvoice').text("[Status: " + data[i].status + "]");
		  				}else{ 
		  					if(data[i].errMsg!=''){
		  						jq('#readyMarkInvoice').addClass( "isa_error" );
		  						jq('#readyMarkInvoice').text("[ERROR: " + data[i].errMsg + "]");
	  						}
		  				}
					}
		  			  //Start dialog
		  			  /*	
				  	  jq('<div></div>').dialog({
				          modal: true,
				          title: "Dialog - Info.",
				          open: function() {
				  	  		  var markup = "Operasjonen var vellykket";
				  	          jq(this).html(markup);
				  	          //make Cancel the default button
				  	          //jq(this).siblings('.ui-dialog-buttonpane').find('button:eq(1)').focus();
				  	     },
				          buttons: {
				  	        Ok: function() {
				          		jq( this ).dialog( "close" );
				  	        }
				          }
				          
				  	  });  //end dialog
				  	  */
			  	  },
			  	  error: function() {
			  	    alert('Error loading ...');
			  	  }
			  	}); 
	  		});
	});

	
	jq(function() {
		jq("#linType").change(function() { 
			window.location = "transportdisp_mainorder_invoice.do?action=doFind&hepro="+ jq('#hepro').val() +
			"&heavd=" + jq('#avd').val() + "&heopd=" + jq('#opd').val() + "&itemsType=" + jq("#linType").val(); 
		});
		//----------------------
		//Validate supplier id
		//----------------------
		jq("#falevn").blur(function() { 
				jq.ajax({
			  	  type: 'GET',
			  	  url: 'validateSupplierInvoiceDetailLine_TransportDisp.do',
			  	  data: { applicationUser : jq('#applicationUser').val(), 
			  		  	  id : jq('#falevn').val() }, 
			  	  dataType: 'json',
			  	  cache: false,
			  	  contentType: 'application/json',
			  	  success: function(data) {
			  		var len = data.length;
			  		if(len==1){
			  			jq("#falevn").removeClass( "isa_error" );
			  			jq("#submit").removeAttr("disabled");
						for ( var i = 0; i < len; i++) { 
							
							if(data[i].aktkod != 'I'){
		    					jq('#lnavn').val(data[i].navn);
		    					jq('#falevn').removeClass ("isa_error");
		    					jq('#lnavn').removeClass ("isa_error");
		    					jq("#submit").removeAttr("disabled");
		    				}else{
		    					jq('#lnavn').val("Inaktiv Lev." + data[i].navn);
		    					jq('#falevn').addClass( "isa_error" );
		    					jq('#lnavn').addClass( "isa_error" );
		    					jq("#submit").attr("disabled", true);
		    				}
							
						}
			  		}else{
			  			if(jq("#falevn").val()!=''){
			  				jq("#lnavn").val("");
			  				jq("#falevn").addClass( "isa_error" );
			  				jq("#submit").attr("disabled", true);
			  			}else{
			  				jq("#falevn").removeClass( "isa_error" );
			  				jq("#submit").removeAttr("disabled");
			  			}
			  		}
			  		
			  	  },
			  	  error: function() {
			  	    alert('Error loading ...');
			  	    jq("#falevn").addClass( "isa_error" );
			  	    jq("#submit").attr("disabled", true);
			  	  }
			  	}); 
		});
		//----------------------
		//Validate customer id
		//----------------------
		jq("#fakunr").blur(function() { 
			if(validateCustomer()){
		  	  //everything ok
		  	}else{
	  			if(jq("#fakunr").val()!=''){
	  				jq("#fakunr").addClass( "isa_error" );
	  				jq("#submit").attr("disabled", true);
	  				
	  			}else{
	  				if(jq("#fask").val()=='X'){
	  					jq("#fakunr").addClass( "isa_error" );
		  				jq("#submit").attr("disabled", true);
	  				}else{
	  					jq("#fakunr").removeClass( "isa_error" );
	  					jq("#submit").removeAttr("disabled");
	  				}
	  			}
	  		}
		});
			
		//Customer id - rule
		jq("#fask").change(function() { 
			if(jq("#fask").val()=='X' && jq("#fakunr").val()==''){
				jq("#fakunr").addClass( "isa_error" );
				jq("#submit").attr("disabled", true);
			}else{
				jq("#fakunr").removeClass( "isa_error" );
				jq("#submit").removeAttr("disabled");
			}
		});
		
		function validateCustomer(){
			jq.ajax({
			  	  type: 'GET',
			  	  url: 'validateCustomerInvoiceDetailLine_TransportDisp.do',
			  	  data: { applicationUser : jq('#applicationUser').val(), 
			  		  	  id : jq('#fakunr').val() }, 
			  	  dataType: 'json',
			  	  cache: false,
			  	  contentType: 'application/json',
			  	  success: function(data) {
			  		var len = data.length;
			  		if(len==1){
			  			jq("#fakunr").removeClass( "isa_error" );
			  			jq("#submit").removeAttr("disabled");
						for ( var i = 0; i < len; i++) { 
							if(data[i].aktkod == 'I'){
								if(jq("#fask").val() == 'X'){
									jq("#fakunr").addClass( "isa_error" );
									jq("#submit").attr("disabled", true);
									return false;
								}
							}	
						}
						return true;
			  		}else{
			  			return false;
			  		}
			  		
			  	  },
			  	  error: function() {
			  	    return false;
			  	  }
		  	});
		}
    });
	
	//Overlay for end-user when too much waiting...
	jq(function() {
		jq('#alinkOrderListId').click(function() { 
			jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
		});
		jq('#alinkTripListId').click(function() { 
			jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
		}); 
		jq('#alinkParentTripId').click(function() { 
			jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
		});
		jq('#alinkOrderId').click(function() { 
			jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
		});
	});

	/**
	 * Get the specific item detail data
	 * @param record
	 * @return
	 */
	function getInvoiceItemData(record) {
		var FIELD_SEPARATOR = "_";
	  	var htmlValue = record.id;
	  	var applicationUser = jq('#applicationUser').val();
	  	//alert(htmlValue);
	  	var field = htmlValue.split(FIELD_SEPARATOR);
	  	var lineId = field[1];
	  	var requestString = "user=" + jq('#applicationUser').val() + "&avd=" + jq('#avd').val() + "&opd=" + jq('#opd').val() + "&lin=" + lineId + "&type=A";
	  	//alert(requestString);
	  	//http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getOrderInvoiceDetailLine_TransportDisp.do',
	  	  data: { applicationUser : applicationUser, 
	  		  	  requestString : requestString }, 
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//alert(data[i].fask);
				jq('#editLineNr').text("");jq('#editLineNr').text(data[i].fali);
				jq('#updCancelButton').show(); //in order to be able to cancel (implicit reload)
				
				jq('#fali').val("");jq('#fali').val(data[i].fali);
				jq('#fask').val("");jq('#fask').val(data[i].fask);
				jq('#favk').val("");jq('#favk').val(data[i].favk);
				jq('#fabelv').val("");jq('#fabelv').val(data[i].fabelv);
				jq('#faval').val("");jq('#faval').val(data[i].faval);
				jq('#fakdm').val("");jq('#fakdm').val(data[i].fakdm);
				//Fritext
				jq('#faVT').val("");jq('#faVT').val(data[i].faVT);
				/* tentative
				var freeText = data[i].faVT;
				if(freeText!=""){
					jq('#faVT').val("");jq('#faVT').val(data[i].faVT);
					jq('#freeText').val("");jq('#freeText').val(data[i].faVT); //only for presentation purposes

				}else{
					jq('#stdVt').val("");jq('#stdVt').val(data[i].stdVt);
					jq('#freeText').val("");jq('#freeText').val(data[i].stdVt); //only for presentation purposes
				}*/
				jq('#falevn').val("");jq('#falevn').val(data[i].falevn);
				jq('#fadocnB').val("");jq('#fadocnB').val(data[i].fadocnB);
				jq('#faavdr').val("");jq('#faavdr').val(data[i].faavdr);
				jq('#fakduk').val("");jq('#fakduk').val(data[i].fakduk);
				jq('#facu33').val("");jq('#facu33').val(data[i].facu33);
				jq('#fabelu').val("");jq('#fabelu').val(data[i].fabelu);
				
				jq('#fakunr').val("");jq('#fakunr').val(data[i].fakunr);
				jq('#facd11').val("");jq('#facd11').val(data[i].facd11);
				
				
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
  	}
  	
	//---------------------------------------
	//DELETE Invoice line
	//This is done in order to present a jquery
	//Alert modal pop-up
	//----------------------------------------
	function doPermanentlyDeleteInvoiceLine(element){
	  //start
	  var record = element.id.split('@');
	  var heavd = record[0];
	  var heopd = record[1];
	  var fali = record[2];
	  heavd= heavd.replace("heavd_","");
	  heopd= heopd.replace("heopd_","");
	  fali= fali.replace("fali_","");
	  
	  //Start dialog
	  jq('<div></div>').dialog({
        modal: true,
        title: "Dialog - Slett linje: " + fali,
        buttons: {
	        Fortsett: function() {
        		jq( this ).dialog( "close" );
	            //do delete
	            jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	            window.location = "transportdisp_mainorder_invoice_edit.do?action=doDelete" + "&heavd=" + heavd + "&heopd=" + heopd + "&fali=" + fali;
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
	