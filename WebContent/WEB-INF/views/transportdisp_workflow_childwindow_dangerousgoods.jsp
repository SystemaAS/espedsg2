<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTransportDispChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr height="5"><td colspan="2"></td></tr>
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Farlig Gods
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
		<form action="transportdisp_workflow_childwindow_dangerousgoods.do?action=doFind" name="searchDangerousGoodsForm" id="searchDangerousGoodsForm" method="post">
			<input type="hidden" name="callerLineCounter" id="callerLineCounter" value='${model.container.callerLineCounter}'>
			
			<%-- =====================================================  --%>
          	<%-- Here we have the search [Dangerous goods form] popup window --%>
          	<%-- =====================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<tr height="5"><td></td></tr>
					<tr>
					<td>
						<table>
						<tr>
							<td class="text11">&nbsp;UnNr</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="unnr" id="unnr" size="10" maxlength="10" value="${model.container.unnr}"></td>
						
							<%--
							<td class="text11">&nbsp;&nbsp;&nbsp;Emb.g</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="embg" id="embg" size="10" maxlength="8" value="${model.container.embg}"></td>

							<td class="text11">&nbsp;&nbsp;&nbsp;Indx</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="indx" id="indx" size="5" maxlength="1" value="${model.container.indx}"></td>
							 --%>
							 
							<td class="text11">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.transportdisp.search"/>'>
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					<%-- Validation errors --%>
					<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
					<tr>
						<td colspan="20">
			            	<table align="left" border="0" cellspacing="0" cellpadding="0">
			            	<tr>
							<td class="textError">					
					            <ul>
					            <c:forEach var="error" items="${errors.allErrors}">
					                <li >
					                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
					                </li>
					            </c:forEach>
					            </ul>
							</td>
							</tr>
							</table>
						</td>
					</tr>
					</spring:hasBindErrors>

					<tr><td><hr size="1" width="100%"/></td></tr>								           		
	           		<tr height="15"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="dangerousGoodsList" class="display compact cell-border" width="100%" >
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text11">&nbsp;Op&nbsp;</th>   
		                    <th class="text11" title="adunnr">&nbsp;UnNr&nbsp;</th>
		                    <th class="text11" title="adembg">&nbsp;Emb.g&nbsp;</th>
		                    <th class="text11" title="adindx">&nbsp;Indx.&nbsp;</th>
		                    <th class="text11" title="adklas">&nbsp;FareK&nbsp;</th>
		                    <th class="text11" title="adsedd">&nbsp;Fareseddel&nbsp;</th>
		                    <th class="text11" title="adtres">&nbsp;Tunnerllrest&nbsp;</th>
		                    <th class="text11" title="adfakt">&nbsp;Fakt&nbsp;</th>
		                    <th class="text11" title="adtextK">&nbsp;Beskrivelse&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.dangerousGoodsList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text11">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text11">
			                   </c:otherwise>
			               </c:choose>
			               
		               	   <td class="text11">&nbsp;</td>
		               	   <c:choose>
		               	   <c:when test="${not empty model.container.callerLineCounter}">
			               	   <td nowrap style="cursor:pointer;" class="text11MediumBlue" 
				               		id="unnr_${record.adunnr}@embg_${record.adembg}@indx_${record.adindx}@fakt_${record.adfakt}@dt_${counter.count}" >
				               		&nbsp;<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				               		&nbsp;&nbsp;${record.adunnr}
			               	   </td>
		               	   </c:when>
		               	   <c:otherwise>
		               	   	   <%-- Explicit New Line --%>	
			               	   <td nowrap style="cursor:pointer;" class="text11MediumBlue" 
				               		id="unnr${record.adunnr}@embg${record.adembg}@indx${record.adindx}@fakt${record.adfakt}@dt_${counter.count}" >
				               		&nbsp;<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				               		&nbsp;&nbsp;${record.adunnr}
			               	   </td>
		               	   </c:otherwise>
		               	   </c:choose>
		               	   
		               	   <td class="text11">&nbsp;${record.adembg}</td>
		               	   <td class="text11">&nbsp;${record.adindx}</td>
		               	   <td class="text11">&nbsp;${record.adklas}</td>
			               <td class="text11">&nbsp;${record.adsedd}</td>
			               <td class="text11">&nbsp;${record.adtres}</td>
			               <td class="text11">&nbsp;${record.adfakt}</td>
			               <td class="text11">&nbsp;${record.adtextK}</td>
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
        			</table>
				
		</form>	
		</td>
		</tr>
	</table> 
