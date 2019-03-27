<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancecundf_vkund.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.main.maintenance.label"/></font>
					<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tab" align="center">
					<font class="tabLink">&nbsp;<spring:message code="systema.main.maintenance.customerreg"/></font>&nbsp;
					<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tabDisabled" align="center">
					<a id="alinkMainMaintVkundNew" onClick="setBlockUI(this);" href="mainmaintenancecundf_vkund_edit.do?action=doCreate">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.main.maintenance.customer.new"/></font>&nbsp;						
						<img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">
					</a>
				</td>
				<td width="55%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%" class="text14">
					<form action="mainmaintenancecundf_vkund.do?id=${model.dbTable}" name="formRecord" id="formRecord" method="POST" >
						<input type="hidden" name="firma" id="firma" value="${user.companyCode}">
						<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.customernr"/>:&nbsp;
						<input type="text" class="inputTextMediumBlue" name="searchKundnr" id="searchKundnr" size="10" maxlength="8" value='${model.kundnr}'>					
						&nbsp;
						<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.name"/>:&nbsp;
						<input type="text" class="inputTextMediumBlue" name="searchKnavn" id="searchKnavn" size="32" maxlength="30" value='${model.knavn}'>	
						&nbsp;				
						<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.orgnr"/>:&nbsp;
						<input type="text" class="inputTextMediumBlue" name="searchSyrg" id="searchSyrg" size="16" maxlength="14" value='${model.syrg}'>					
						&nbsp;				
						<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.landcode"/>:&nbsp;
						<input type="text" class="inputTextMediumBlue" name="searchSyland" id="searchSyland" size="4" maxlength="2" value='${model.syland}'>					
						&nbsp;				
						<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.postnr"/>:&nbsp;
						<input type="text" class="inputTextMediumBlue" name="searchPostnr" id="searchPostnr" size="6" maxlength="4" value='${model.postnr}'>					
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submitSearch" id="submitSearch" value='<spring:message code="systema.main.maintenance.search"/>'/>
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
						<table id="mainList" class="display compact cell-border nowrap" > 
							<thead>
								<tr>
								    <th width="2%" class="tableHeaderFieldFirst" align="center" ><spring:message code="systema.edit"/></th>                                                            
									<th width="2%" class="tableHeaderField" align="center" ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.customernr"/></th>
									<th class="tableHeaderField" align="center" ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.name"/></th>
				                    <th class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.orgnr"/></th>
				                    <th class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.address"/></th>
				                    <th class="tableHeaderField" align="center" ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.postnr.plain"/></th>
				                    <th class="tableHeaderField" align="center" ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.adr3"/></th>
				                    <th class="tableHeaderField" align="center" ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.landcode"/></th>
				                    <th class="tableHeaderField" align="center" ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.customs"/></th>
				                    <th class="tableHeaderField" align="center" ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.firm"/></th>
<!--  
				                    <th class="tableHeaderField" align="center" ><spring:message code="systema.delete"/></th>
-->				                    
				                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">
									<input type="hidden" name="action" id="action" value="doUpdate">
					               	<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="mainmaintenancecundf_vkund_edit.do?action=doUpdate&kundnr=${record.kundnr}&firma=${record.firma}&knavn=${record.knavn}">
	               						<img src="resources/images/update.gif" border="0" alt="edit">
				               		</a>
				               </td>
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">${record.kundnr}</td>
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">${record.knavn}</td>
				               <td width="20%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.syrg}&nbsp;</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.adr1}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.postnr}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.adr3}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.syland}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.syfr02}&nbsp;${record.sykont}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.firma}&nbsp;</td>
<!--  
		                       <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne og all tilhørende informasjon?')" tabindex=-1 href="mainmaintenancecundf_vkund_edit.do?action=doDelete&kundnr=${record.kundnr}&firma=${record.firma}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
-->
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

