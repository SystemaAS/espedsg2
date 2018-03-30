  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
 
  //-------------------
  //Datatables jquery
  //-------------------
  //private function
  /*
  function filterGlobal () {
    jq('#childList').dataTable().search(
    	jq('#childList_filter').val()
    ).draw();
  }
  
  jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#childList').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
    	  "scrollY": "350px",
    	  "scrollCollapse":  true,
    	  "columnDefs": [{ "type": "num", "targets": 1 }],
    	  "order": [[ 1, "asc" ]],
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.childList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
 */
  