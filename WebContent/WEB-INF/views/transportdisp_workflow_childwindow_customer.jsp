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
			Customer Search
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
		<form action="transportdisp_workflow_childwindow_customer.do?action=doFind" name="searchCustomerForm" id="searchCustomerForm" method="post">
			<input type="hidden" name="ctype" id="ctype" value="${model.container.ctype}">
			<%-- =====================================================  --%>
          	<%-- Here we have the search [Customer] popup window --%>
          	<%-- =====================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
					<tr height="5"><td></td></tr>
					<tr>
					<td>
						<table>
						<tr>
							<td class="text11">&nbsp;Customer No.</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="sokknr" id="sokknr" size="8" maxlength="8" value="${model.container.sokknr}"></td>
						
							<td class="text11">&nbsp;&nbsp;&nbsp;Name</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="soknvn" id="soknvn" size="15" maxlength="35" value="${model.container.soknvn}"></td>
						
							<td class="text11">&nbsp;&nbsp;&nbsp;Post.Code/City/Country</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="kunpnsted" id="kunpnsted" size="15" maxlength="10" value="${model.container.kunpnsted}"></td>
						
							<td class="text11">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.transportdisp.search"/>'></td>
	           				<td width="15px" >&nbsp;</td>
	           				<td class="text11" style="color:#9F6000;"><label class="isa_warning" >&nbsp;&nbsp;Adressekunder&nbsp;&nbsp;</label></td>
	           				
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
					<table id="customerList" class="display compact cell-border" width="100%">
						<thead>
						<tr style="background-color:#EEEEEE">
						    <th class="text11">Customer No.</th>   
		                    <th class="text11">Name</th>
		                    <th class="text11">Address</th>
		                    <th class="text11">Post.Code/City/Country</th>
		                    <th class="text11">Vareadresse</th>
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.customerList}" varStatus="counter">    
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
			               			<td onMouseOver="showPop('kType_info${counter.count}');" onMouseOut="hidePop('kType_info${counter.count}');" class="text11MediumBlue" style="background-color:#FEEFB3;cursor:pointer;" id="kundnr_${record.kundnr}@navn_${record.navn}@aktkod_${record.aktkod}@counter_${counter.count}">&nbsp;${record.kundnr}
			               			<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px;" id="kType_info${counter.count}" class="popupWithInputText"  >
											<font class="text11">Adressekund</font>	
			               				</span>
			               			</div>	
			               			</td>
			               	   	</c:when>
			               		<c:otherwise>
			               			<td class="text11MediumBlue" style="cursor:pointer;" id="kundnr_${record.kundnr}@navn_${record.navn}@aktkod_${record.aktkod}@counter_${counter.count}">&nbsp;${record.kundnr}</td>
			               		</c:otherwise>
			               </c:choose>
			               <td class="text11" <c:if test="${record.aktkod == 'I'}">style="color:#9F6000;background-color:#FEEFB3;"</c:if> >&nbsp;${record.navn}</td>
			               <td class="text11" <c:if test="${record.aktkod == 'I'}">style="color:#9F6000;background-color:#FEEFB3;"</c:if>>&nbsp;${record.adr1}</td>
			               <td class="text11" <c:if test="${record.aktkod == 'I'}">style="color:#9F6000;background-color:#FEEFB3;"</c:if>>&nbsp;${record.adresse}</td>
			               <td class="text11" style="color:#4F8A10;background-color:#DFF2BF;">&nbsp;${record.vadrna}&nbsp;${record.vadrn1}&nbsp;&nbsp;${record.vadrn2}&nbsp;${record.vadrn3}</td>
			               
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
