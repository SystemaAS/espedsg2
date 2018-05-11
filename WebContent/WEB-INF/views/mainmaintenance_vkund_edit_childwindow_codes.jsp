<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headervKundChildWindows.jsp" />
<!-- =====================end header ====================================-->

<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
<SCRIPT type="text/javascript"
	src="resources/js/mainmaintenance_vkund_edit_childwindow_codes.js?ver=${user.versionEspedsg}"></SCRIPT>

<table width="100%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom">
	<tr>
		<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp; 
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search"> ${model.labelSearch}
		</td>
	</tr>
	<tr>
		<td valign="top">
			<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
				<tr height="10">
					<td></td>
				</tr>
				<tr class="text14">
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height: 30em;">
						<table id="codeList" class="display compact cell-border">	
							<thead>
								<tr style="background-color: #EEEEEE">
									<th class="text14">&nbsp;${model.labelCode}&nbsp;</th>
									<th class="text14">&nbsp;${model.labelDesc}&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="record" items="${model.codeList}" varStatus="counter">
									<tr class="text14">
										<td nowrap style="cursor: pointer;" class="text14MediumBlue" id="code${record.code}@caller${model.caller}">&nbsp;
											<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;${record.code}
										</td>
										<td class="text14">&nbsp;${record.description}</td>
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
