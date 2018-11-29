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
  		var lang = jq('#language').val();
  	    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
        jq('#mainList').dataTable( {
      	  "dom": '<"custMatrixFilter"if>t<"bottom"lp><"clear">', //look at custMatrixFilter on JSP SCRIPT-tag
      	  "scrollY": "800px",
      	  "scrollCollapse":  true,
      	  "lengthMenu": [100],
	      //	"language": {
	  		//  "url": getLanguage(lang)
	      //  },
	  	  "fnDrawCallback": function( oSettings ) {
	  		jq('.dataTables_filter input').addClass("inputText12LightYellow");
	  		jq('.dataTables_filter input').focus();
	   	}
  	  });
        //css styling
        //jq('.dataTables_filter input').addClass("inputText12LightYellow");
        
        
  		//event on input field for search
  	    jq('input.mainList_filter').on( 'keyup click', function () {
  	    	filterGlobal();
  	    } );
    });
  	
  	