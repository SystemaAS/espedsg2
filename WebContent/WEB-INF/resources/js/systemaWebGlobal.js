var jq = jQuery.noConflict();
  	
	function setBlockUI(element){
		setBlockUI();
	}

	function setBlockUI(){
		let lang = jq('#language').val();
		if (lang == undefined) {
			lang = 'EN';
		}
		jq.blockUI({ css: { fontSize: '22px' }, message: blockUIMessageMap[lang]});
     }	

	function unBlockUI(){
		jq.unblockUI();	
	}
	
	var langMap = {
			   'EN' : 'English',
			   'DA' : 'Danish',
			   'SV' : 'Swedish',
			   'NO' : 'Norwegian-Bokmal'
	}

	var blockUIMessageMap = {
			   'EN' : 'Please wait...',
			   'DA' : 'Vent venligst...',
			   'SV' : 'Vänligen vänta...',
			   'NO' : 'Vennligst vent...'
	}	
	
	//for setting user lang to datatables
	function getLanguage(lang) {
	    return '/espedsg2/resources/localization/'+langMap[lang]+'.json';
	}
	
	//for mouse-over for css popup
    function showPop(id){
    	if(id!=''){
    		document.getElementById(id).style.visibility = "visible";
    	}
    }
    //for mouse-out for css popup
    function hidePop(id){
    	if(id!=''){
    		document.getElementById(id).style.visibility = "hidden";
    	}
    }
    

    //Date with decimal period(46)
    function dateKey(evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode!=46){
            return false;
        }else{
            return true;
        }
    }
    
    //numeric values
    function numberKey(evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)){
            return false;
        }else{
            return true;
        }
    }
    //amounts with decimal comma(44), period(46), minus(45)
    function amountKey(evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode!=44 && charCode!=45){
            return false;
        }else{
            return true;
        }
    }
    //blocks the carriage return key
    function lockCarriageReturnKey(evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        if (charCode == 13){
            return false;
        }else{
            return true;
        }
    }
    //refreshes all html 5 CustomValidity functions from jQuery
    function refreshCustomValidity(element){
  	  element.setCustomValidity('');
    }
  
