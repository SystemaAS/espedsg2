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
			<td colspan="3" class="text16Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Hend.logg
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
			
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table style="width:100%;" id="containerdatatableTable" cellspacing="0" align="left" >
					<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" style="height:35em;width:90%;">
					<%-- this is the datatables grid (content)--%>
					<table id="trackAndTraceList" class="display compact cell-border" >
						<thead>
						<tr class="tableHeaderField" height="20">
							<th class="text14" >&nbsp;F.brev.&nbsp;</th>
		                    <th class="text14" >&nbsp;Dato&nbsp;</th>
		                    <th class="text14" >&nbsp;Tid&nbsp;</th>
		                    <th class="text14" >&nbsp;Event&nbsp;</th>
		                    <th class="text14" >&nbsp;Tekst&nbsp;</th>
		                    <th class="text14" >&nbsp;Bruker&nbsp;</th>
		                    
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.trackAndTraceList}" varStatus="counter">    
			               	<tr class="tableRow">
			               		<td class="tableCellFirst">&nbsp;${record.frBrev}</td>
				               	<td class="tableCell" >&nbsp;${record.date}</td>
				               	<td class="tableCell" >&nbsp;${record.time}</td>
				               	<td class="tableCell" >&nbsp;${record.event}</td>
				               	<td class="tableCell" >&nbsp;${record.textLoc}</td>
				               	<td class="tableCell" >&nbsp;${record.user}</td>
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
