<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTransportDispChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="85%" class="popupFloatingWithRoundCorners3D" cellspacing="0" border="0" cellpadding="0">
		<tr>
		<td valign="top">
		<form action="transportdisp_workflow_childwindow_uploadFile.do?action=doSave" name="uploadFileForm" id="uploadFileForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="wstur" id="wstur" value='${model.wstur}'>
			<%-- =====================================================  --%>
          	<%-- Here we have the search [Customer] popup window --%>
          	<%-- =====================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				
				<table id="containerdatatableTable" cellspacing="2" align="left">
					<tr height="2"><td></td></tr>
					<tr>
						<td colspan="3" class="text14Bold">&nbsp;
							<img style="vertical-align:bottom;" src="resources/images/upload.png" border="0" width="24" height="24" alt="upload">
							&nbsp;File Upload&nbsp;							
						</td>
					</tr>
					<tr>
					<tr height="5"><td></td></tr>
					<tr>
					<td>
						<table>
						<tr>	
							<td class="text11">&nbsp;File:</td>
								
            					<td class="text11">
            						&nbsp;<input type="file" name="file" id="file" />
        						</td>
								 
							<td class="text11">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='Save'>
		           		</tr>
		           		</table>
					</td>
					</tr>
					<%-- Validation errors --%>
					<spring:hasBindErrors name="record"> 
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
					
					<tr height="5"><td></td></tr>
       			</table>
       			
		</form>	
		</td>
		</tr>
	</table> 
