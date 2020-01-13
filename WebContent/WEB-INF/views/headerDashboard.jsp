<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %> <!-- generally you will include this in a header.jsp -->
<html>
	<head>
		<link href="resources/${user.cssEspedsg}?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
		<%-- for dialog popup --%>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		<%-- for zxcvbn password strength --%>
		<link rel="stylesheet" href="resources/passtrength.css">
		
		<c:choose>
			<c:when test="${ fn:contains(user.cssEspedsg, 'Toten') }"> 
				<link rel="SHORTCUT ICON" type="image/ico" href="resources/images/toten_ico.ico"></link>
			</c:when>
			<c:otherwise>
				<link rel="SHORTCUT ICON" type="image/png" href="resources/images/systema_logo.png"></link>
			</c:otherwise>
		</c:choose>
		<%-- for dialog popup --%>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		
		
		<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>${user.custName} eSpedsg</title>
		<style type = "text/css">
			.ui-dialog{font-size:10pt;}
		</style>
		
	</head>

	<body>
	<%-- include som javascript functions --%>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script> 		
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js""></script>
	<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="resources/js/dashboard.js?ver=${user.versionEspedsg}"></script>
	<script type="text/javascript" src="resources/js/systemaWebGlobal.js?ver=${user.versionEspedsg}"></script>
	<%-- for zxcvbn password strength --%>
	<script src="resources/js/jquery.passtrength.js"></script>
	
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- width="1200"  -->
		<%--Banner --%>
		<tr class="text" height="10">
			<td><input type="hidden" name="language" id="language" value="${user.usrLang}"></td>
		</tr>
		<tr >
    		<td height="60" align="center" colspan="5"> 
    		
    		 <!-- width="1150"  -->
			 <table width="100%" height="100" class="dashboardBanner" border="0" cellspacing="0" cellpadding="0" align="center"
			 			<c:choose> 
	    			 		<c:when test="${ not empty user.banner && fn:contains(user.banner, '/')}">
	    			 			style="background:url('${user.banner}');background-size:100%;" 
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
										<img src="${user.logo}" border="0" >
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
		<tr height="1"><td></td></tr>
		<%-- Dashboard menu --%>
		<tr >
			<td class="tabThinBorderLightGreenLogoutE2" height="23" align="center" colspan="2"> 
    			 <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" >
				 	<tr >
			    		<td class="text14" width="50%" align="left" >&nbsp;&nbsp;</td>
	      				<td class="text14" width="50%" align="right" valign="middle">
	      					<c:if test="${ empty user.usrLang || user.usrLang == 'NO'}">
			               		<img src="resources/images/countryFlags/Flag_NO.gif" height="12" border="0" alt="country" onClick="showPop('versionInfo');">
			               	</c:if>
			               	<c:if test="${ user.usrLang == 'DA'}">
			               		<img src="resources/images/countryFlags/Flag_DK.gif" height="12" border="0" alt="country" onClick="showPop('versionInfo');">
			               	</c:if>
			               	<c:if test="${ user.usrLang == 'SV'}">
			               		<img src="resources/images/countryFlags/Flag_SE.gif" height="12" border="0" alt="country" onClick="showPop('versionInfo');">
			               	</c:if>
			               	<c:if test="${ user.usrLang == 'EN'}">
			               		<img src="resources/images/countryFlags/Flag_UK.gif" height="12" border="0" alt="country" onClick="showPop('versionInfo');">
			               	</c:if>
		      				<div class="text12" style="position: relative;display: inline;" align="left">
								<span style="position:absolute; left:-150px; top:3px;" id="versionInfo" class="popupWithInputText"  >
					           		<div class="text12" align="left">
					           			<b>${user.versionEspedsg}</b>
					           			<p>
					           				&nbsp;<a id="alinkLog4jLogger" ><font class="text14LightGreen" style="cursor:pointer;">log4j</font></a><br/>
					           			</p>
					           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
					           		</div>
					           	</span>
				           	</div>
		      				
		      				
		      				
		      				
		      				&nbsp;
		      				<font id="changePwdButton" class="headerMenuGreen">
		    					<img src="resources/images/bulletGreen.png" width="8px" height="8px" border="0">
		    					<font class="text14" onMouseOver="style='color:lemonchiffon;'" onMouseOut="style='color:black;'" ><spring:message code="dashboard.menu.change.password"/></font>
		    				</font>
		    				<font color="#FCFFF0"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
		    				<a title="Tomcat metrics" tabindex=0 id="metricsIdLink" >
		    					<img id="imgMetrics" style="vertical-align:bottom;cursor:pointer;" src="resources/images/metrics.png" width="14px" height="14px" border="0" >
		    				</a>
		    				<font color="#FCFFF0"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
	      					<font class="headerMenuGreenNoPointer">
	      						<img title="${user.logo}" src="resources/images/appUser.gif" border="0" onClick="showPop('specialInformationAdmin');">&nbsp;
      							<font class="text14">${user.user}&nbsp;</font>${user.usrLang}&nbsp;
      								<c:if test="${not empty user.multiUser}">
      									<img title="${user.logo}" src="resources/images/sort_down.png" width="10px" height="10px" border="0" onClick="showPop('multiUserList');">&nbsp;
      									<div class="text14" style="position: relative; display: inline;" align="left">
											<span style="position:absolute; left:-150px; top:5px; width:250px" id="multiUserList" class="popupWithInputText"  >
												<p class="text14"><b>Multi user - Switch</b></p>
												<font class="text14BlueGreen">
													<ul>
													<c:forEach var="record" items="${user.multiUser}" varStatus="counter" >
														<form id="formMU_${counter.count}" onClick="setBlockUI(this);" action="logonDashboard.do" method="POST">
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
      								</c:if>
      							</font>
      							<font color="#FCFFF0"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
				    			
				    			<a href="logout.do">
				    				<font class="headerMenuGreen">
				    					<img src="resources/images/lock.gif" border="0">
				    					<font class="text14" onMouseOver="style='color:lemonchiffon;'" onMouseOut="style='color:black;'"><spring:message code="logout.logout"/></font>
				    				</font>
			    				</a>
			    				<div class="text11" style="position: relative;display: inline;" align="left">
								<span style="position:absolute; left:-150px; top:3px; width:150;" id="specialInformationAdmin" class="popupWithInputText"  >
									<font class="text11">
										<p>Firmakode&nbsp;<b>${user.companyCode}</b></p>
					           			<p>${activeUrlRPG}</p>
					           			<p>&nbsp;<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('specialInformationAdmin');">Close</button></p>
					           			
				           			</font>
								</span>
								</div> 
			    			</td>
			    			
							
			        </tr>
			     </table> 
			</td>
	    </tr>
	 	
		
	    
		<%-- ------------------------- --%>
		<%-- DIALOG render log4j.log   --%>
		<%-- ------------------------- --%>
		<tr>
		<td>
			<div id="dialogLogger" title="Dialog" style="display:none">
				<form>
			 	<table>
			 		<tr>
						<td colspan="3" class="text14" align="left" >Password</td>
  						</tr>
					<tr >
						<td>
							<input type="password" class="inputText" id="pwd" name="pwd" size="15" maxlength="15" value=''>
						</td>
					</tr>
					
					<tr height="5"><td></td></tr>
  					<tr >
						<td>
							<input type="text" class="inputText" id="logLevel" name="logLevel" size="8" maxlength="8" value=''>
						</td>
					</tr>
  					<tr height="10"><td></td></tr>
					<tr>
						<td colspan="3" class="text14MediumBlue" align="left">
							<label id="loggerStatus"></label>
						</td>
					</tr>
				</table>
				</form>
			</div>
		</td>
		</tr>
		
	    