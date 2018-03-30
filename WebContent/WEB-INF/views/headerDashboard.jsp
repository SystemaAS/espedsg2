<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %> <!-- generally you will include this in a header.jsp -->
<html>
	<head>
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js""></script>
		<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
		<script type="text/javascript" src="resources/js/dashboard.js?ver=${user.versionEspedsg}"></script>
		<%-- include som javascript functions --%>
 		<script type="text/javascript" src="resources/js/systemaWebGlobal.js?ver=${user.versionEspedsg}"></script>
 	
		<link href="resources/${user.cssEspedsg}?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
		<%-- for dialog popup --%>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
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
		<title>${user.custName} eSpedsg</title>
	</head>

	<body>
	
 
	<table class="dashboardFrameMain" width="1200" border="0" cellspacing="0" cellpadding="0">
		<%--Banner --%>
		<tr class="text" height="10"><td></td></tr>
		<tr >
    		<td height="60" align="center" colspan="2"> 
    		
			 <table width="1150" height="100" class="dashboardBanner" border="0" cellspacing="0" cellpadding="0" align="center"
			 			<c:choose> 
	    			 		<c:when test="${ not empty user.banner && fn:contains(user.banner, '/')}">
	    			 			style="background-image:url('${user.banner}');background-repeat:no-repeat;" 
	    			 		</c:when>  
	    			 		<c:otherwise>
	    			 			<%-- %>style="background-image:url('resources/images/${user.banner}');background-repeat:no-repeat;" --%> 
	    			 		</c:otherwise>
    			 		</c:choose>
    			 		>
    			 
    			 		<tr height="5"><td></td></tr>
				 	<tr>
				 		<td style="min-width: 300px; max-width: 300px;" class="text22Bold" align=left valign="middle" >
				 			<c:if test="${not empty user.logo}">
				 				<c:choose>
					 				<c:when test="${fn:contains(user.logo, '/')}">
										<img src="${user.logo}" border="0" width="30px" height="20px">
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${fn:contains(user.logo, 'systema')}">
												<img src="resources/images/${user.logo}" border="0" width=80px height=50px>
											</c:when>
											<c:otherwise>
												<c:if test="${fn:contains(user.logo, 'logo')}">
													<img src="resources/images/${user.logo}" border="0" >
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
   			 				</c:if>
						</td>
						
						
						<td class="text32Bold" width="40%" align="center" valign="middle" 
								<c:choose>
			 					<c:when test="${ not empty user.banner }">
				 					style="font-style:italic; color:#555555;" <%-- gray --%>
				 				</c:when>
				 				<c:otherwise>
				 					style="font-style:italic; color:#778899;" <%-- special metal gray --%>
				 				</c:otherwise>
				 				</c:choose>
						>
				 			eSped<font style="color:#003300;">sg</font>
				 		</td>
		    			<td width="30%" align="right" valign="middle">
						<c:if test="${not empty user.systemaLogo && (user.systemaLogo=='Y')}">
		    				<img src="resources/images/systema_logo.png" border="0" width=75px height=45px>
							&nbsp; 
						</c:if>		
	    				</td>
			      		
			        </tr>
    			 		<tr height="5"><td></td></tr>
		     </table> 
 		</td>

		</tr>
		<tr height="2"><td></td></tr>
		<%-- Dashboard menu --%>
		<tr >
			<td height="23" align="center" colspan="2"> 
    			 <table class="dashboardFrameMain" width="1150" border="0" cellspacing="0" cellpadding="0" align="center" >
				 	<tr >
			    		<td class="text11" width="50%" align="left" >&nbsp;&nbsp;</td>
	      				<td class="text11" width="50%" align="right">
	      					<font class="headerMenuGreenNoPointer">
	      						<img title="${user.logo}" src="resources/images/appUser.gif" border="0" onClick="showPop('specialInformationAdmin');">&nbsp;
      							<font class="text11User">${user.user}&nbsp;</font>${user.usrLang}&nbsp;
      								<c:if test="${not empty user.multiUser}">
      									<img title="${user.logo}" src="resources/images/sort_down.png" width="10px" height="10px" border="0" onClick="showPop('multiUserList');">&nbsp;
      								</c:if>
      							</font>
      							<font color="#FCFFF0"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
				    			<%--
				    			<font color="#FCFFF0"; style="font-weight: bold;" onClick="showPop('userInfo');">&nbsp;|&nbsp;</font>
				    			<span style="position:absolute; left:750px; top:180px; width:150px; height:20px;" id="userInfo" class="popupWithInputText"  >
						           		<div class="text11" align="left">
						           			in order to see TDS Behörighet pw 
						           			${user.user}@${user.pwAS400}
						           			&nbsp;&nbsp;&nbsp;&nbsp;<button name="userInfoButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('userInfo');">Close</button> 
						           		</div>
						        </span>
						         --%>
				    			<a href="logout.do">
				    				<font class="headerMenuGreen">
				    					<img src="resources/images/lock.gif" border="0">&nbsp;
				    					<font class="text11User" ><spring:message code="logout.logout"/>&nbsp;</font>
				    				</font>
			    				</a>
			    			</td>
			    			<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute; left:1000px; top:30px; width:250px" id="specialInformationAdmin" class="popupWithInputText"  >
									<font class="text11">
										<p>Firmakode&nbsp;<b>${user.companyCode}</b></p>
					           			<p>${activeUrlRPG}</p>
					           			<p>&nbsp;<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('specialInformationAdmin');">Close</button></p>
					           			
				           			</font>
								</span>
							</div> 
							<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute; left:900px; top:25px; width:280px" id="multiUserList" class="popupWithInputText"  >
									<p class="text12"><b>Multi user - Switch</b></p>
									<font class="text11BlueGreen">
										<ul>
										<c:forEach var="record" items="${user.multiUser}" varStatus="counter" >
											<form id="formMU_${counter.count}" action="logonDashboard.do" method="POST">
											<input type="hidden" name="user" id="user" value='${record.multiID}'> 
											<li>
                       	 						<span id="${counter.count}" onClick="doPostMultiUser(this);"><b>${record.multiID}</b>&nbsp;-&nbsp;${record.multiTxt}</span>
                       	 					</li>
                       	 					</form>
										</c:forEach>
										
										<%--
										<c:forEach var="record" items="${user.multiUser}" >
                       	 					<li><a href="logonDashboard.do?user=${record.multiID}&password=mltid">${record.multiTxt}</a></li>
										</c:forEach>
										--%>
										</ul>
					           			<p>&nbsp;<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('multiUserList');">Close</button></p>
					           			
				           			</font>
								</span>
							</div> 	
			        </tr>
			     </table> 
			</td>
	    </tr>
	 	
		
	    
		