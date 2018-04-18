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
			Frie søkeveier koder
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
		<form action="transportdisp_workflow_childwindow_frisokveicodes.do?action=doFind" name="searchFrisokveiCodesForm" id="searchFrisokveiCodesForm" method="post">
			
			<%-- =============================================================  --%>
          	<%-- Here we have the search [Frisokvei codes form] popup window --%>
          	<%-- ==============================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<tr height="15"><td></td></tr>
					
					<tr class="text14" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="frisokveiCodesList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" height="20">
							<th class="text14" title="kfsoko">&nbsp;Kode&nbsp;</th>
		                    <th class="text14" title="kfsotx">&nbsp;Tekst&nbsp;</th>
		                 
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.frisokveiCodesList}" varStatus="counter">    
			               <tr class="text14">
				               <td style="cursor:pointer;" class="textMediumBlue" 
					               		id="kode${record.kfsoko}@text${record.kfsotx}@dt_${counter.count}" >
					               		&nbsp;<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					               		&nbsp;&nbsp;${record.kfsoko}
			               	   </td>
			               	   <td class="text14">&nbsp;${record.kfsotx}</td>
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
