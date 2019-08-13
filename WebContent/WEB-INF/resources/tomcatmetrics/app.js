	
	//-----------------
	//functions section
	//-----------------
	$(document).ready(function() {
		init();
	});

	//button click
	$('#metricsButton').click(function() { 
		init();
	});
	
	
	//get rest services
	function init(){
		$.ajax({
		  	  type: 'GET',
		  	  url: 'getTomcatMetricsData.do',
		  	  data: { user : $('#applicationUser').val() },
		  	  dataType: 'json',
		  	  cache: false,
		  	  contentType: 'application/json',
		  	  success: function(data) {
		  		var len = data.length;
		  		for ( var i = 0; i < len; i++) {
		  			if(data[i].totalSum>0){
		  				console.log(data[i].totalSum);
		  				$("#totalSum").text(data[i].totalSum);
		  			}
		  		}
		  		console.log("fetch DATA OK....");
		  		//build graph
	  			buildGraph(data);
		  	  },
		  	  error: function() {
		  		  console.log('ERROR!!! Error loading ...');
			  }
		});
	}
	
	
	function buildGraph(data){
		var jsonData = data;
		
		//var jsonData = [{"date": "2019-07-09 09:00:50", "logins": 10, "hours": 15},{"date": "2015-07-09 10:00:40", "logins": 21, "hours": 15}];
		//convert string dates into date formats                
		jsonData = MG.convert.date(jsonData, 'date', "%Y-%m-%d %H:%M:%S" );
	    //plot
	    MG.data_graphic({
	        title: "Activity graph",
	        //description: "This is an example.",
	        data: jsonData,
	        //chart_type: 'point',
	        //interpolate: d3.curveLinear,
	        width: 750,
	        height: 300,
	        //right: 10,
	        x_accessor: 'date',
	        y_accessor: 'logins',
	        
	        point_size: 6,
	        active_point_on_lines: true,
	        active_point_accessor: 'logins',
	        active_point_size: 4,

	        target: '#mainGraph',
	        color_type:'category',
	        //mouseover: function(d, i) { console.log(d,i); },
	        //y_rug: true
	    });
		
	}

