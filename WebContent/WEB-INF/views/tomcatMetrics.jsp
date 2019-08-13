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
        
        <h1>Tomcat logins - espedsg2</h1>
		<div><button name="metricsButton" id="metricsButton" class="buttonGrayWithGreenFrame" type="button" >Refresh</button></div>
        <div name="mainGraph" id="mainGraph">

            
        </div>
        
        
        <script src="resources/tomcatmetrics/app.js"></script>
        
    </body>
</html>