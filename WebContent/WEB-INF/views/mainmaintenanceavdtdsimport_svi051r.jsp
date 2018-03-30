<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavdtdsimport_svi051r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<font class="tabDisabledLink">&nbsp;Underhåll</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="13%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdgate.do?id=${model.dbTable}">
							<font class="tabDisabledLink">&nbsp;Avdelningar</font>&nbsp;						
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="18%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdTdsSvi051" onClick="setBlockUI(this);" href="mainmaintenanceavdtdsimport_svi051r.do?id=${model.id}">
                      			<font class="tabLink">&nbsp;<spring:message code="systema.tds.import.label"/></font>&nbsp;
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdtdsimport_svi051r_edit.do?id=${model.id}">
							<font class="tabDisabledLink">&nbsp;Skapa ny avd.</font>&nbsp;						
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
							    <th width="2%" class="tableHeaderFieldFirst" align="center" >Uppd.</th>                                                            
								<th width="2%" class="tableHeaderField" align="center" >Avd.</th>
								<th class="tableHeaderField" align="center" >Ärendenr.</th>
								<th class="tableHeaderField" align="center" >Eori</th>
								<th width="2%" class="tableHeaderField" align="center" >Typ</th>
								<th width="2%" class="tableHeaderField" align="center" >Test</th>
								<th nowrap class="tableHeaderField" align="center" >Ta bort</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">
					               	<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="mainmaintenanceavdtdsimport_svi051r_edit.do?avd=${record.svia_syav}&opd=${record.svia_syop}&updateId=${record.svia_syav}&eori=${record.svia_omeo}&id=${model.id}">
	               						<img src="resources/images/update.gif" border="0" alt="edit">
				               		</a>
				               </td>
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">${record.svia_syav}</td>
				               <td width="20%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.svia_syop}&nbsp;</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.svia_omeo}&nbsp;</td>
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.svia_omty}&nbsp;</td>
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.svia_0035}&nbsp;</td>
				               
				               <td nowrap align="center" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="mainmaintenanceavdtdsimport_svi051r_edit.do?action=doDelete&svia_syav=${record.svia_syav}&svia_syop=${record.svia_syop}">
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

