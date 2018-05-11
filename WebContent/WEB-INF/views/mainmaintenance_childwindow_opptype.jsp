<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenanceChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenance_childwindow_opptype.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Søk Oppdragstype
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
						<form name="form" id="form" action="mainmaintenance_childwindow_opptype.do?action=doFind" method="post">
						<input type="hidden" name="ctype" id="ctype" value="${model.ctype}">
						
						<tr>
							<td class="text14">&nbsp;Kode</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="id" id="id" size="10" maxlength="10" value="${model.id}"></td>
							<td class="text14">&nbsp;</td>
							<%-- 
							<td class="text14">&nbsp;Tekst</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="text" id="text" size="30" maxlength="50" value="${model.text}"></td>
							--%>
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
							<th class="text14" >&nbsp;Kode.&nbsp;</th>
							<th class="text14" >&nbsp;Tekst&nbsp;</th>
							<th class="text14" >&nbsp;Fr.brev&nbsp;</th>
		                    <th class="text14" >&nbsp;Forp.SAD&nbsp;</th>
		                    <th class="text14" >&nbsp;Kj.kvitt.&nbsp;</th>
		                    <th class="text14" >&nbsp;Toll.Imp&nbsp;</th>
		                    <th class="text14" >&nbsp;Fr.brev Imp&nbsp;</th>
		                    <th class="text14" >&nbsp;Toll.Eksp.&nbsp;</th>
		                    <th class="text14" >&nbsp;B.of land.&nbsp;</th>
		                    <th class="text14" >&nbsp;CMR&nbsp;</th>
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.list}" varStatus="counter">    
			               <tr class="text14">
				               <td style="cursor:pointer;" class="text14MediumBlue" id="id${record.ko2kod}@ctype${model.ctype}" >
				               		<img title="select" style="vertical-align:top;" src="resources/images/bebullet.gif" border="0" alt="edit">&nbsp;${record.ko2kod}
				               	</td>
			               	   <td class="text14">&nbsp;${record.ko2ntx}</td>
			               	   
			               	   <td class="text14">&nbsp;${record.ko2fb}</td>
			               	   <td class="text14">&nbsp;${record.ko2fps}</td>
			               	   <td class="text14">&nbsp;${record.ko2kk}</td>
			               	   <td class="text14">&nbsp;${record.ko2ti}</td>
			               	   <td class="text14">&nbsp;${record.ko2fi}</td>
			               	   <td class="text14">&nbsp;${record.ko2te}</td>
			               	   <td class="text14">&nbsp;${record.ko2bi}</td>
			               	   <td class="text14">&nbsp;${record.ko2cm}</td>
			               	   
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
