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
			Oppdragstype
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
			<%-- =====================================================  --%>
          	<%-- Here we have the search [Tollsted codes form] popup window --%>
          	<%-- =====================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table style="width:100%;" id="containerdatatableTable" cellspacing="0" align="left" >
					<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" style="height:30em;width:90%;">
					<%-- this is the datatables grid (content)--%>
					<table id="oppTypeList" class="display compact cell-border" >
						<thead>
						<tr class="tableHeaderField" height="20">
							<th class="text14" title="todo">&nbsp;Kode&nbsp;</th>
		                    <th class="text14" title="todo">&nbsp;Navn&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.oppdragstypeList}" varStatus="counter">    
			               	<tr class="text14">
			               		<td nowrap style="cursor:pointer;" class="textMediumBlue" 
				               		id="code_${record.opdTyp}@ctype_${model.callerType}" >
				               		&nbsp;<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				               		&nbsp;&nbsp;${record.opdTyp}
			               		</td>	
		               	   		<td class="text14">&nbsp;${record.beskr}</td>
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
