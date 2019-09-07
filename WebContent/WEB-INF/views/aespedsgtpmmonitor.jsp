<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTpmmonitor.jsp" />
<!-- =====================end header ==========================-->
	<SCRIPT type="text/javascript" src="resources/js/aespedsgtpmmonitor.js?ver=${user.versionEspedsg}"></SCRIPT>
 	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
 	<table width="100%" class="text14" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text14" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;Tomcat Performance Metrics</font>&nbsp;
						<img style="vertical-align: top;" src="resources/images/testsuites.png" height="16px" width="16px" border="0" alt="general list">
						&nbsp;
					</td>
					<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
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
					
				<td width="100%">
				<table id="containerdatatableTable" width="90%" cellspacing="1" border="0" align="center">
			    	    <tr>
						<td class="text14">
						<table width="100%" id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
								<th width="2%" class="tableHeaderFieldFirst" align="center" >Id</th>
								<th class="tableHeaderField" align="left" >&nbsp;App.name</th>
								<th class="tableHeaderField" align="left" >&nbsp;Module</th>
								<th width="2%" class="tableHeaderField" align="center" >Action</th>
							</tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.moduleList}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				              
				               <td width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;" align="center" >
				               		<%-- <font class="text12">&nbsp;${record.id}&nbsp;</font>  --%>
				               		<img src="resources/images/leaf.png" height="18px" width="18px" border="0" alt="test module">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="left" >
				               		<a target="_blank"  href="${record.url}" >
					               		&nbsp;<font class="text16SkyBlue">&nbsp;${record.desc}</font>
					               	</a>
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="left" >
				               		<a target="_blank"  href="${record.url}" >
					               		&nbsp;<font class="text16SkyBlue">&nbsp;${record.module}</font>
					               	</a>
				               </td>
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;" align="center" >
				               		<img src="resources/images/tpmmonitor.png" height="20px" width="20px" border="0" alt="test module">
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

