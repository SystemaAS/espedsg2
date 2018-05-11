<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancearkiv_arc007.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text12" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text12" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
						<font class="tabDisabledLink">&nbsp;
							<spring:message code="systema.main.maintenance.label"/>
						</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintArkivGate" onClick="setBlockUI(this);" href="mainmaintenancearkivgate.do?id=${model.dbTable}">
							<font class="tabDisabledLink">&nbsp;
								<spring:message code="systema.main.maintenance.arkiv"/>
							</font>&nbsp;						
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tab" align="center">
						<font class="tabLink">&nbsp;
							<spring:message code="systema.main.maintenance.arkiv.documents"/>					
						</font>&nbsp;
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintArkivArc007" onClick="setBlockUI(this);" href="mainmaintenancearkiv_arc007_edit.do">
							<font class="tabDisabledLink">&nbsp;
								<spring:message code="systema.new"/>	
							</font>&nbsp;						
							<img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">
						</a>
					</td>
					<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>					
				</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>

	 	    <%-- search --%>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%" class="text14">
					<form action="mainmaintenancearkiv_arc007.do?" name="formRecord" id="formRecord" method="POST" >
						<input tabindex=-1 type="checkbox" name="showUpload" id="showUpload" value="J" <c:if test="${model.showUpload == 'J'}"> checked </c:if> >
						<font class="text12"><spring:message code="systema.main.maintenance.arkiv.document.onlyscanning"/></font>&nbsp;&nbsp;						
						&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submitSearch" id="submitSearch" value='<spring:message code="systema.main.maintenance.search"/>'/>
					</form>
				</td>
			</tr>
	 	    
			<%-- list component --%>
			<tr>
				<td width="3%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="99%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text12">
						<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
							    <th width="2%" class="tableHeaderFieldFirst" align="center"><spring:message code="systema.edit"/></th>                                        
								<th width="2%" class="tableHeaderField" align="center"><spring:message code="systema.main.maintenance.arkiv.document.artype"/></th>
								<th width="10%" class="tableHeaderField" align="center"><spring:message code="systema.main.maintenance.arkiv.document.artxt"/></th>
			                    <th class="tableHeaderField" align="center"><spring:message code="systema.main.maintenance.arkiv.document.arkjn"/></th>
			                    <th class="tableHeaderField" align="center"><spring:message code="systema.main.maintenance.arkiv.document.arksnd"/></th>
			                    <th class="tableHeaderField" align="center"><spring:message code="systema.main.maintenance.arkiv.document.arklag"/></th>
			                    <th class="tableHeaderField" align="center"><spring:message code="systema.main.maintenance.arkiv.document.arkved"/></th>   
			                    <th class="tableHeaderField" align="center"><spring:message code="systema.main.maintenance.arkiv.document.arsban"/></th>
			                    <th class="tableHeaderField" align="center"><spring:message code="systema.delete"/></th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">
					               	<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="mainmaintenancearkiv_arc007_edit.do?artype=${record.artype}&updateId=${record.artype}">
	               						<img src="resources/images/update.gif" border="0" alt="edit">
				               		</a>
				               </td>
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">${record.artype}</td>
				               <td width="10%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.artxt}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
			    					<c:if test="${record.arkjn=='J'}">
		                       			<font class="text14">&nbsp;&nbsp;&nbsp;<spring:message code="systema.yes"/>&nbsp;</font>
		                       		</c:if>
			    					<c:if test="${record.arkjn=='N'}">
		                       			<font class="text14">&nbsp;&nbsp;&nbsp;<spring:message code="systema.no"/>&nbsp;</font>
		                       		</c:if>
		                       </td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
			    					<c:if test="${record.arksnd=='J'}">
		                       			<font class="text14">&nbsp;&nbsp;&nbsp;<spring:message code="systema.yes"/>&nbsp;</font>
		                       		</c:if>
			    					<c:if test="${record.arksnd=='N'}">
		                       			<font class="text14">&nbsp;&nbsp;&nbsp;<spring:message code="systema.no"/>&nbsp;</font>
		                       		</c:if>
		                       </td>				               
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.arklag}&nbsp;</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
				               		${record.arkved1}&nbsp;
				               		${record.arkved2}&nbsp;
				               		${record.arkved3}&nbsp;
				               		${record.arkved4}&nbsp;
				               		${record.arkved5}&nbsp;
				               		${record.arkved6}&nbsp;
				               		${record.arkved7}&nbsp;
				               		${record.arkved8}&nbsp;
				               		${record.arkved9}&nbsp;
				               		${record.arkved10}&nbsp;
				               		${record.arkved11}&nbsp;
				               		${record.arkved12}&nbsp;
				               		${record.arkved13}&nbsp;
				               		${record.arkved14}&nbsp;
				               		${record.arkved15}&nbsp;
				               		${record.arkved16}&nbsp;
				               		${record.arkved17}&nbsp;
				               		${record.arkved18}&nbsp;
				               		${record.arkved19}&nbsp;
				               		${record.arkved20}&nbsp;
				               		${record.arkved21}&nbsp;
				               		${record.arkved22}&nbsp;
				               		${record.arkved23}&nbsp;
				               		${record.arkved24}&nbsp;
				               		${record.arkved25}&nbsp;
				               		${record.arkved26}&nbsp;
				               		${record.arkved27}&nbsp;
				               		${record.arkved28}&nbsp;
				               		${record.arkved29}&nbsp;
				               		${record.arkved30}&nbsp;
				               </td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.arsban}&nbsp;</td>
		                       <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('<spring:message code="systema.delete.confirm"/>')" tabindex=-1 href="mainmaintenancearkiv_arc007_edit.do?action=doDelete&artype=${record.artype}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>		    
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

