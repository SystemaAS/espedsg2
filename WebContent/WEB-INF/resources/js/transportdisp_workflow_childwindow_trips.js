	//============================================================
	//General functions for TRANSPORT DISP. Child windows trips 
	//============================================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
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
  	    jq('#wssst').change(function() {
  	    	if(jq('#wssst').val()=='Z' && jq('#wtudt').val()=='' ){
  	    		//set a mandatory field when 'Z' has been chosen
  		    	var now = new Date();
  		    	now.setDate(now.getDate() - 10); // add -7 days to your date variable 
  		    	jq("#wtudt").val(jq.datepicker.formatDate('yymmdd', now));
  	    	}
  	    });
  	    
  	    jq("#wtudt").blur(function(){
  			  //now check the user input alternatives
  			  var str = jq("#wtudt").val();
  			  if(str!=''){
  				  var length = str.length;
  				  if(length==2){
  					  jq("#wtudt").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
  				  }else if (length==4){
  					  var userDay = str.substring(0,2);
  					  var userMonth = str.substring(2,4);
  					  jq("#wtudt").val(g_getCurrentYearStr() + userMonth + userDay);
  				  }
  			  }
  		  });
  	      jq("#wtudt2").blur(function(){
  			  //now check the user input alternatives
  			  var str = jq("#wtudt2").val();
  			  if(str!=''){
  				  var length = str.length;
  				  if(length==2){
  					  jq("#wtudt2").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
  				  }else if (length==4){
  					  var userDay = str.substring(0,2);
  					  var userMonth = str.substring(2,4);
  					  jq("#wtudt2").val(g_getCurrentYearStr() + userMonth + userDay);
  				  }
  			  }
  		  });
  	      
  	      
  	      jq("#wtudtt").blur(function(){
  			  //now check the user input alternatives
  			  var str = jq("#wtudtt").val();
  			  if(str!=''){
  				  var length = str.length;
  				  if(length==2){
  					  jq("#wtudtt").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
  				  }else if (length==4){
  					  var userDay = str.substring(0,2);
  					  var userMonth = str.substring(2,4);
  					  jq("#wtudtt").val(g_getCurrentYearStr() + userMonth + userDay);
  				  }
  			  }
  		  });
  	      jq("#wtudtt2").blur(function(){
  			  //now check the user input alternatives
  			  var str = jq("#wtudtt2").val();
  			  if(str!=''){
  				  var length = str.length;
  				  if(length==2){
  					  jq("#wtudtt2").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
  				  }else if (length==4){
  					  var userDay = str.substring(0,2);
  					  var userMonth = str.substring(2,4);
  					  jq("#wtudtt2").val(g_getCurrentYearStr() + userMonth + userDay);
  				  }
  			  }
  		  });
  	  });
  	
  	
  	//Connect trip with order
  	//if = OK then go to order (GUI)
  	function setTripOnOrder(element){
  		var id= element.id;
  		//alert(id);
  		var record = id.split('_');
  		var trip = record[1];
  		var avd = record[2];
  		var opd = record[3];
  		trip = trip.replace("tupro","");
  		avd = avd.replace("avd","");
  		opd = opd.replace("opd","");
  		//alert(trip+"XX"+avd+"XX"+opd);
  		var requestString = "&wmode=A&wstur=" + trip + "&wsavd=" + avd + "&wsopd=" + opd;
  		jq.ajax({
		  	  type: 'GET',
		  	  url: 'addTripToOrder_TransportDisp.do',
		  	  data: { applicationUser : jq('#applicationUser').val(),
  					  requestString : requestString },
		  	  dataType: 'json',
		  	  cache: false,
		  	  contentType: 'application/json',
		  	  success: function(data) {
		  		var len = data.length;
		  		if(len==1){
			  		//update = OK
		  			opener.reloadCallerParentOrder(trip,avd,opd);
		  			window.close();
		  			
		  		}else{
		  			//update != OK
		  			alert("Error on order update [Planlegging]...?");
		  		}
		  	  },
		  	  error: function() {
		  		  alert('Error loading ...');
		  		  
			  }
  		});
  		//href="transportdisp_mainorderlist_add_remove_order.do?user=${user.user}&wmode=A&wstur=${record.tupro}&wsavd=${record.tuavd}&wsopd=${model.opd}&cw=true">
  		
  	}
  	
  	
  //-------------------
    //Datatables jquery
    //-------------------
    //private function
    function filterGlobal () {
      jq('#workflowTrips').DataTable().search(
      		jq('#workflowTrips_filter').val()
      ).draw();
    }

jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
  	jq('#workflowTrips').dataTable( {
  		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
  		  "scrollY":        	"1000px",
  		  "scrollCollapse":  	false,
  		  "autoWidth": false, //for optimization purposes when initializing the table
  		  "lengthMenu": [ 50, 75, 100]
  		  
  	});
  	
  	//event on input field for search
  	jq('input.workflowTrips_filter').on( 'keyup click', function () {
  		filterGlobal();
  	});
      
});	
  	