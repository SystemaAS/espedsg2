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
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Supplier Search
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
		<form action="transportdisp_workflow_childwindow_supplier.do?action=doFind" name="searchSupplierForm" id="searchSupplierForm" method="post">
			<%-- =====================================================  --%>
          	<%-- Here we have the search [Supplier] popup window --%>
          	<%-- =====================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
					<tr height="5"><td></td></tr>
					<tr>
					<td>
						<table>
						<tr>
							<td class="text11">&nbsp;Levnr.</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="kode" id="kode" size="8" maxlength="8" value="${model.container.kode}"></td>
						
							<td class="text11">&nbsp;&nbsp;&nbsp;Navn</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="tekst" id="tekst" size="15" maxlength="35" value="${model.container.tekst}"></td>
						
							<td class="text11">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.transportdisp.search"/>'>
	           				<td width="15px" >&nbsp;</td>
	           				<td class="text11" style="color:#9F6000;"><label class="isa_warning" >&nbsp;&nbsp;Inaktiv&nbsp;&nbsp;</label></td>
	           				
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					<%-- Validation errors --%>
					<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
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
										
					
					<tr><td><hr size="1" width="100%"/></td></tr>								           		
	           		<tr height="15"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:50em;">
					<%-- this is the datatables grid (content)--%>
					<table id="supplierList" class="display compact cell-border" width="100%">
						<thead>
						<tr style="background-color:#EEEEEE">
						    <th class="text11">&nbsp;Levnr.</th>   
		                    <th class="text11">&nbsp;Navn</th>
		                    <th class="text11">&nbsp;Adr1</th>
		                    <th class="text11">&nbsp;Postnr</th>
		                    <th class="text11">&nbsp;Sted</th>
		                    <th class="text11">&nbsp;Land</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.supplierList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text11" >
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text11" >
			                   </c:otherwise>
			               </c:choose>
			               <c:choose>
			               <c:when test="${record.aktkod == 'I'}">
		               			<td onMouseOver="showPop('lType_info${counter.count}');" onMouseOut="hidePop('lType_info${counter.count}');" class="text11MediumBlue" style="background-color:#FEEFB3;cursor:pointer;" id="id_${record.levnr}@navn_${record.lnavn}@counter_${counter.count}">&nbsp;${record.levnr}
			               			<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px;" id="lType_info${counter.count}" class="popupWithInputText"  >
											<font class="text11">Inaktiv</font>	
			               				</span>
			               			</div>	
		               			</td>
		               	   	</c:when>
		               		<c:otherwise>
		               			<td style="cursor:pointer;" class="text11MediumBlue" id="id_${record.levnr}@navn_${record.lnavn}@counter_${counter.count}">&nbsp;${record.levnr}</td>
			               </c:otherwise>
			               </c:choose>
			               
			               
			               <td class="text11" <c:if test="${record.aktkod == 'I'}">style="color:#9F6000;background-color:#FEEFB3;"</c:if> >&nbsp;${record.lnavn}</td>
			               <td class="text11" <c:if test="${record.aktkod == 'I'}">style="color:#9F6000;background-color:#FEEFB3;"</c:if> >&nbsp;${record.adr1}</td>
			               <td class="text11" <c:if test="${record.aktkod == 'I'}">style="color:#9F6000;background-color:#FEEFB3;"</c:if> >&nbsp;${record.postnr} </td>
			               <td class="text11" <c:if test="${record.aktkod == 'I'}">style="color:#9F6000;background-color:#FEEFB3;"</c:if> >&nbsp;${record.sted}</td>
			               <td class="text11" <c:if test="${record.aktkod == 'I'}">style="color:#9F6000;background-color:#FEEFB3;"</c:if> >&nbsp;${record.land} </td>
			               
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
        			</table>
				
		</form>	
		</td>
		</tr>
	</table> 
