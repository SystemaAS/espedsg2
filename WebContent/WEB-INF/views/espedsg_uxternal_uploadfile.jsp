<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerEspedsgUxternal.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/espedsg_uxternal_uploadfile.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
		.ui-dialog{font-size:10pt;}
		.ui-datepicker { font-size:9pt;}
	</style>
	
	
	<%-- Errors in general --%>
	<c:choose>
	<c:when test="${not empty model.errorMessage}">
		<table width="85%" height="80%" class="popupFloatingWithRoundCorners3D" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td width="5%">&nbsp;</td>
			<td valign="top">
	           	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
			 		<tr>
			 			<td >
			 				<ul class="isa_error text24" >
                                 <li>ERROR - Server return code: ${model.errorMessage}</li>                                    
                             </ul>
			 			</td>
					</tr>
				</table>
			</td>
		</tr>
		</table>
	</c:when>
	<c:otherwise>
		<div id="dialogDraggable" title="File Upload">
		<table width="85%" height="80%" class="popupFloatingWithRoundCorners3D" cellspacing="0" border="0" cellpadding="0">
			<tr>
			<td valign="top">
			<form action="TODOtvinnsadmanifest_childwindow_uploadFile.do?action=doSave" name="uploadFileForm" id="uploadFileForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="user" id="user" value='${model.user}'>
				<input type="hidden" name="wsavd" id="wsavd" value='${model.wsavd}'>
				<input type="hidden" name="wsopd" id="wsopd" value='${model.wsopd}'>
					<table id="containerdatatableTable" cellspacing="2" align="left">
						<tr height="2"><td></td></tr>
						<tr>
							<td class="text14Bold">&nbsp;
								<img style="vertical-align:bottom;" src="resources/images/upload.png" border="0" width="24" height="24" alt="upload">
								&nbsp;File Upload						
							</td>
						</tr>
						<tr height="5"><td></td></tr>
						<tr>
						<td>
							<table>
							<tr>
								<td colspan="2" class="text14">
									&nbsp;Avd&nbsp;<b>${model.wsavd}</b>&nbsp;&nbsp;Oppdrag&nbsp;<b>${model.wsopd}</b>						
								</td>
							</tr>
							<tr>	
								<td class="text14">&nbsp;Arkiv typen:</td>
								<td class="text14">&nbsp;
									<select name="wstype" id="wstype">
										<option value="ZH">ZH-Handelsfaktura</option>
									 	<option value="ZO">ZO-Oppdragsvedlegg</option>
									</select>	
								</td>
							</tr>
							<tr>	
								<td class="text14">&nbsp;Fil:</td>
								<td class="text14">
	           						&nbsp;<input ondragenter="myFileUploadDragEnter(event,this)" ondragleave="myFileUploadDragLeave(event,this)" class="tableBorderWithRoundCornersLightYellow3D" style="width:200px;height:100px;display:block;" type="file" name="file" id="file" />
	       						</td>
			           		</tr>
			           	 	<%--
			           		<tr>	
								<td class="text14">&nbsp;</td>
								<td valign="bottom" >&nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Save'>
			           		</tr>
			           		 --%>
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
					</table>
	       		</form>	
				</td>
				</tr>
			</table> 
		</div>
		</c:otherwise>
	</c:choose>	

