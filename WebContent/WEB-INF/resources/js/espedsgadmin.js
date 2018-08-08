	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
  	function setBlockUI(element){
  	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    }
  	
  	
  	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGlobal () {
    		jq('#mainList').DataTable().search(
      		jq('#mainList_filter').val()
    		).draw();
    } 
  	jq(document).ready(function() {
  	    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
        jq('#mainList').dataTable( {
      	  "dom": '<"custMatrixFilter"f>t<"bottom"i><"clear">', //look at custMatrixFilter on JSP SCRIPT-tag
      	  "scrollY": "800px",
      	  "scrollCollapse":  true,
      	  "lengthMenu": [100]
      	  });
        //css styling
        jq('.dataTables_filter input').addClass("inputText");
        
  		//event on input field for search
  	    jq('input.mainList_filter').on( 'keyup click', function () {
  	    	filterGlobal();
  	    } );
    });
  	
  	