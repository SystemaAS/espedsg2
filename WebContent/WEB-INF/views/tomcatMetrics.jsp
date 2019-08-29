<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
    <head>
    
    
        <script src="resources/tomcatmetrics/d3.min.js"></script>
        <script src="resources/tomcatmetrics/d3.js"></script>
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js""></script>
        
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script>
	    
        <script src="resources/tomcatmetrics/metricsgraphics.js"></script>
        <script src="resources/tomcatmetrics/metricsgraphics.min.js"></script>
        
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="resources/tomcatmetrics/metricsgraphics.css">
        <link href="resources/${user.cssEspedsg}?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
		
        <style type = "text/css">
			.ui-dialog{font-size:11pt;}
			.ui-datepicker { font-size:10pt;}
		</style>
    </head>
    <body>
    
		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
        
        <h2>Tomcat login attempts - espedsg2</h2>
		<div>
			<button name="metricsButton" id="metricsButton" class="buttonGrayWithGreenFrame" type="button" >Refresh</button>
			<input type="text" class="inputTextMediumBlue" name="date" id="date" size="11" maxlength="10" value="">
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