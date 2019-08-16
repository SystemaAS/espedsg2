<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
    <head>
        
        <script src="resources/tomcatmetrics/d3.min.js"></script>
        <script src="resources/tomcatmetrics/d3.js"></script>
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js""></script>
            
        <script src="resources/tomcatmetrics/metricsgraphics.js"></script>
        <script src="resources/tomcatmetrics/metricsgraphics.min.js"></script>
        
        
        
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="resources/tomcatmetrics/metricsgraphics.css">
    </head>
    <body>
		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
        
        <h2>Tomcat login attempts - espedsg2</h2>
		<div>
			<button name="metricsButton" id="metricsButton" class="buttonGrayWithGreenFrame" type="button" >Refresh</button>
		</div>
		<div>
			<p>Totals</p>
			Login attempts:&nbsp;<b><label style="color:blue;" name="totalSum" id="totalSum"></label></b><br>
			Successful attempts:&nbsp;<b><label style="color:mediumseagreen;" name="totalSuccessSum" id="totalSuccessSum"></label></b>
		</div>
		
        <div name="mainGraph" id="mainGraph"></div>
        <div height="10"></div>
        <div name="consolidatedGraph" id="consolidatedGraph"></div>
        
        
        <script src="resources/tomcatmetrics/app.js?ver=${user.versionEspedsg}"></script>
        
    </body>
</html>