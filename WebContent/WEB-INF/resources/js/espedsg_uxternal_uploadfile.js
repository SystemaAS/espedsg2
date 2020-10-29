	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict()
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	function setBlockUI(element){
  	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    }
  	
  	
  	
  	jq(document).ready(function() {
  		showDialogDraggable();
  	});
  	
    //draggable window
    function showDialogDraggable(){
  	  //jq( "#dialogDraggableMatrix" ).removeClass("popup");
  	  jq( "#dialogDraggable" ).dialog({
  		  modal: true,
  		  minHeight: 350,
  		  minWidth:500,
  		  position: { my: "center top", at: "center top", of: window }
  	  }); 
  	  jq( "#dialogDraggable" ).focus();
    }
  	
  	
  	