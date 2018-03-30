<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavdskatexport_dke051r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
						<font class="tabDisabledLink">&nbsp;Vedligehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="13%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdgate.do?id=${model.dbTable}">
							<font class="tabDisabledLink">&nbsp;Afdelinger</font>&nbsp;						
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="18%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdSkatDke051" onClick="setBlockUI(this);" href="mainmaintenanceavdskatexport_dke051r.do?id=${model.id}">
                      			<font class="tabLink">&nbsp;<spring:message code="systema.skat.export.tab.description.avd"/></font>&nbsp;
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdskatexport_dke051r_edit.do?id=${model.id}">
							<font class="tabDisabledLink">&nbsp;Skabe ny afd.</font>&nbsp;						
							<img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">
						</a>
					</td>
					<td width="41%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
			<%-- list component --%>
			<tr>
				<td width="3%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="99%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text11">
						<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
							    <th width="2%" class="tableHeaderFieldFirst" align="center" >Endre</th>                                                            
								<th width="2%" class="tableHeaderField" align="center" >Afd.</th>
								<th class="tableHeaderField" align="center" >Angivelsesnr.</th>
								<th class="tableHeaderField" align="center" >Navn</th>
								<th class="tableHeaderField" align="center" >Test SKAT</th>
								<th class="tableHeaderField" align="center" >Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">
					               	<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="mainmaintenanceavdskatexport_dke051r_edit.do?avd=${record.dkea_syav}&opd=${record.dkea_syop}&updateId=${record.dkea_syav}&avdnavn=${record.dkea_14c}&id=${model.id}">
	               						<img src="resources/images/update.gif" border="0" alt="edit">
				               		</a>
				               </td>
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">${record.dkea_syav}</td>
				               <td width="20%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.dkea_syop}&nbsp;</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.dkea_14c}&nbsp;</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.dkea_0035}&nbsp;</td>
				               
				               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="mainmaintenanceavdskatexport_dke051r_edit.do?action=doDelete&dkea_syav=${record.dkea_syav}&dkea_syop=${record.dkea_syop}">
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

