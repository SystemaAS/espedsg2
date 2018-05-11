<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenanceChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenance_childwindow_edi.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Søk Utveksling
			</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<tr>
					<td>
						<table>
						<form name="ediForm" id="customerForm" action="mainmaintenance_childwindow_edi.do?action=doFind" method="post">
						<input type="hidden" name="ctype" id="ctype" value="${model.ctype}">
						
						<tr>
							<td class="text14">&nbsp;UtvekslingsId</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="id" id="id" size="25" maxlength="35" value="${model.id}"></td>
							<td class="text14">&nbsp;</td>
							<td class="text14">&nbsp;Navn</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="navn" id="navn" size="30" maxlength="50" value="${model.navn}"></td>
							
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.main.maintenance.search"/>'></td>
		           		</tr>
		           		</form>
		           		</table>
					</td>
					</tr>
					 
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="mainList" class="display compact cell-border" width="100%" >
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text14" >&nbsp;UtvekslingsId.&nbsp;</th>
		                    <th class="text14" >&nbsp;Navn&nbsp;</th>
		                    <th class="text14" >&nbsp;Network&nbsp;</th>
		                    <th class="text14" >&nbsp;Internal / External&nbsp;</th>
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.list}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text14">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text14">
			                   </c:otherwise>
			               </c:choose>
			               <td style="cursor:pointer;" class="text14MediumBlue" id="inid${record.inid}@inna${record.inna}@ctype${model.ctype}" >
			               		<img title="select" style="vertical-align:top;" src="resources/images/bebullet.gif" border="0" alt="edit">&nbsp;${record.inid}
			               	</td>
		               	   <td class="text14">&nbsp;${record.inna}</td>
		               	   <td class="text14">&nbsp;${record.innetw}</td>
		               	   <td class="text14">&nbsp;${record.inex}</td>
		               	    
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
        			</table>
		
		</td>
		</tr>
	</table> 
