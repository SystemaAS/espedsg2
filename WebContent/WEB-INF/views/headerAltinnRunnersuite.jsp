<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %> <!-- generally you will include this in a header.jsp -->

<html>
	<head>
		<link href="resources/${user.cssEspedsgTestersuite}?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
		<link href="resources/jquery.calculator.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<%-- datatables grid CSS --%>
		<link type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css" rel="stylesheet">
		
		<c:choose>
			<c:when test="${ fn:contains(user.cssEspedsg, 'Toten') }"> 
				<link rel="SHORTCUT ICON" type="image/ico" href="resources/images/toten_ico.ico"></link>
			</c:when>
			<c:otherwise>
				<link rel="SHORTCUT ICON" type="image/png" href="resources/images/systema_logo.png"></link>
			</c:otherwise>
		</c:choose>
		<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>eSpedsg - <spring:message code="systema.main.maintenance.label"/></title>
	</head>
	<body>
	<%-- include som javascript functions --%>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.0/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="resources/js/systemaWebGlobal.js?ver=${user.versionEspedsg}"></script>

	<%--datatables grid --%>
	<script type="text/javascript" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
	<input type="hidden" name="language" id=language value="${user.usrLang}">
	
    <table class="noBg" width="100%" border="0" cellspacing="0" cellpadding="0">
		<%--Banner --%>
	 	<tr>
	 		 <%-- class="grayTitanBg" --%>
    		<td height="60" class="headerTdsBannerAreaBg" width="100%" align="left" colspan="3"> 
    			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
    			 	<tr>
			        	<td>&nbsp;</td>
			        	<td>&nbsp;</td>
				 		<td>&nbsp;</td>
			        </tr>
				 	<tr>
				 		<td class="text12white" width="10%" align=left valign="bottom" >&nbsp;</td>
				 		<td class="text32Bold" width="80%" align="middle" valign="middle" style="color:#778899;" >
				 			eSped<font style="color:#003300;">sg</font> - <spring:message code="systema.altinnrunnersuite.banner"/>
				 			
				 		</td>
				 		 
			    		<td class="text12" width="10%" align="center" valign="middle" ><img src="resources/images/systema_logo.png" border="0" width=80px height=50px ></td>
			      		<%-- <td class="text12white" width="10%" align=right valign="bottom" >&nbsp;</td>--%>
			        </tr>
			        <tr>
			        	<td>&nbsp;</td>
			        	<td>&nbsp;</td>
				 		<td class="text14" width="10%" align=right valign="bottom" >&nbsp;</td>
			        </tr>
			        <tr class="text" height="1"><td></td></tr>
			     </table> 
			</td>
		</tr>
		<%-- Header menu --%>
		<tr >
			<td height="22" class="tabThinBorderLightGray" width="100%" align="left" colspan="3"> 
    			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
				 	<tr >
			    		<td class="text12" width="70%" align="left" >&nbsp;&nbsp;
			    			<%-- --------------------- --%>
			    			<%-- MAINTENANCE MENU --%>
			    			<%-- --------------------- --%>
			    			<a id="alinkTopicListMenuExp" tabindex=-1 href="altinnrunnersuite.do">
			    				&nbsp;<font 
			    				<c:choose>           
		                   			<c:when test="${user.activeMenu=='ALTINN_RUNNER_SUITES'}">
		                       			class="headerMenuOrange"
		                   			</c:when>
		                   			<c:otherwise>   
		                       			class="headerMenuWhite"
		                   			</c:otherwise>
		               			</c:choose>
			    				
			    				>&nbsp;<spring:message code="systema.altinnrunnersuite.label"/>&nbsp;</font>
			    			</a>
			    			&nbsp;<font color="#FF6600"; style="font-weight: bold;">|</font>

		    			 	<%-- ------------------- --%>
			    			<%-- Maintenance  MENU    --%>
			    			<%-- -------------------- --%>
			    			<a tabindex=-1 href="logoutRunnersuites.do">
			    				&nbsp;<font class="headerMenuMaintenance">
		                   		&nbsp;&nbsp;<spring:message code="systema.altinnrunnersuite.returnTo.label"/>&nbsp;&nbsp;</font>
			    			</a>
	      				</td>		      				
	      				<td class="text12" width="50%" align="right" valign="middle">
	      					
	      					<img src="resources/images/countryFlags/Flag_UK.gif" height="12" border="0" alt="country">
			               	
		      				&nbsp;
		      				<font class="headerMenuWhite">
			    				<img src="resources/images/appUser.gif" border="0" > 
						        <font style="color:#000000" >${user.user}&nbsp;</font><font style="color:#FF6600" >${user.usrLang}</font>
			    			</font>
			    				
		    				<font color="#FF6600"; style="font-weight: bold;">&nbsp;&nbsp;|&nbsp;&nbsp;</font>
			    			<a tabindex=-1 href="logoutTestersuites.do">
			    				<font class="headerMenuWhite"><img src="resources/images/home.gif" border="0">&nbsp;
			    					<font style="color:#000000;" ><spring:message code="dashboard.menu.button"/>&nbsp;</font>
			    				</font>
			    			</a>
			    			<font color="#FF6600"; style="font-weight: bold;">&nbsp;&nbsp;|&nbsp;</font>
			    			<font class="text12" style="cursor:pointer;" onClick="showPop('versionInfo');">${user.versionSpring}&nbsp;</font>
		    				    <div class="text11" style="position: relative;display: inline;" align="left">
								<span style="position:absolute; left:-150px; top:3px; width:150;" id="versionInfo" class="popupWithInputText"  >
									<div class="text11" align="left">
					           			&nbsp;<b>${user.versionEspedsg}</b>
					           			<br/><br/>
					           			&nbsp;<a href="renderLocalLogsg.do" target="_blank"><font class="text12Orange">logsg</font></a>
					           			<br/><br/><br/>
					           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
					           		</div>
								</span>
								</div> 
			    		</td>
			        </tr>
			     </table> 
			</td>
	    </tr>
	    <tr>
		    <td height="4" class="tabThinBorderOrange" width="100%" align="left" colspan="3"> 
	   			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
	   			 </table>
			</td>
	    </tr>
	   
	    <tr class="text" height="2"><td></td></tr>
		
		
		<%-- ------------------------------------
		Content after banner och header menu
		------------------------------------- --%>
		<tr>
    		<td width="100%" align="left" colspan="3"> 
    		     
     