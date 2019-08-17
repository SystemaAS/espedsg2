	var jsonDataForSimpleGraph;
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
		  			for ( var j = 0; j < data[i].length; j++) {
		  				//(1) isolate the simple graph (graph 1 data) json (only first list)
			  			if(i == 0){
			  				jsonDataForSimpleGraph = data[i];
			  			}
			  			
			  			if(data[i][j].totalSum>0){
			  				console.log(data[i][j].totalSum);
			  				if(i == 0){
			  					$("#totalSum").text(data[i][j].totalSum);
			  				}else if (i == 1){
			  					$("#totalSuccessSum").text(data[i][j].totalSum);
			  				}
			  			}
		  			}
		  		}
		  		console.log("fetch DATA OK....");
		  		//build graphs
	  			buildGraphs(jsonDataForSimpleGraph, data);
	  			
		  	  },
		  	  error: function() {
		  		  console.log('ERROR!!! Error loading ...');
			  }
		});
	}
	

	//GRAPH plot
	function buildGraphs(jsonDataForSimpleGraph, data){
		var TIME_MASK = '%Y-%m-%d %H:%M';
		var josnSimpleData = jsonDataForSimpleGraph;
		var jsonConsolidatedData = data;
		
		
	    //CONSOLIDATED GRAPH
	    for (var i = 0; i < jsonConsolidatedData.length; i++) {
	    	jsonConsolidatedData[i] = MG.convert.date(jsonConsolidatedData[i], 'date', TIME_MASK);
	    }
		MG.data_graphic({
	        title: "Activity graph - detailed",
	        //description: "This line chart contains multiple lines.",
	        data: jsonConsolidatedData,
	        //linked: true,
	        //area: true,
	        width: 600,
	        height: 200,
	        //right: 40,
	        x_accessor: 'date',
	        y_accessor: 'logins',
	        target: '#consolidatedGraph',
	        legend: ['Login attempts','Successful attempts'],
	        legend_target: '.legend'
	    });
	    
		
		
		//SIMPLE GRAPH
		//console.log(jsonDataForSimpleGraph);
		//jsonData = [{"date": "2019-07-09 09:00", "logins": 10, "hours": 15},{"date": "2015-07-09 10:00", "logins": 21, "hours": 15}];
		//this causes an ERROR ? ---> maybe since we have 2 graphs and we already handle date conversion??
		//josnSimpleData = MG.convert.date(josnSimpleData, 'date', TIME_MASK );
	    
		//plot
	    MG.data_graphic({
	        title: "Activity graph",
	        //description: "This is an example.",
	        data: josnSimpleData,
	        //linked: true,
	        //chart_type: 'point',
	        //interpolate: d3.curveLinear,
	        width: 600,
	        height: 200,
	        //right: 10,
	        x_accessor: 'date',
	        y_accessor: 'logins',
	        
	        point_size: 6,
	        active_point_on_lines: true,
	        active_point_accessor: 'logins',
	        active_point_size: 4,

	        target: '#mainGraph',
	        legend: ['Login attempts'],
	        legend_target: '.legend'
	        //color_type:'category',
	        //mouseover: function(d, i) { console.log(d,i); },
	        //y_rug: true
	    });
		
		
	    
	}
	
	/*
	function buildGraphTEST(data){
		var jsonData = data;
		
		//var jsonData = [{"date": "2019-07-09 09:00:50", "logins": 10, "hours": 15},{"date": "2015-07-09 10:00:40", "logins": 21, "hours": 15}];
		//var jsonData = [[{"date":"2019-08-16 07:59:23","logins":4,"hour":7,"totalSum":0},{"date":"2019-08-16 08:52:09","logins":13,"hour":8,"totalSum":17}],[{"date":"2019-08-13 15:00:56","logins":10,"hour":15,"totalSum":0},{"date":"2019-08-13 18:00:56","logins":5,"hour":15,"totalSum":0}]]
		//convert string dates into date formats  
		for (var i = 0; i < jsonData.length; i++) {
			jsonData[i] = MG.convert.date(jsonData[i], 'date', '%Y-%m-%d %H:%M:%S');
	    }
		
		
		MG.data_graphic({
	        title: "Activity graph",
	        //description: "This line chart contains multiple lines.",
	        data: jsonData,
	        linked: true,
	        //width: 600,
	        //height: 200,
	        //right: 40,
	        x_accessor: 'date',
	        y_accessor: 'logins',
	        target: '#consolidatedGraph',
	        legend: ['Login attempts','Successful attempts'],
	        legend_target: '.legend'
	    });
	    
		
	}*/


