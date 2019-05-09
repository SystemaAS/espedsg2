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
			<td colspan="3" class="text16Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Hend.logg
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table style="width:100%;" id="containerdatatableTable" cellspacing="0" align="left" >
					<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" style="height:35em;width:90%;">
					<%-- this is the datatables grid (content)--%>
					<table id="trackAndTraceList" class="display compact cell-border" >
						<thead>
						<tr class="tableHeaderField" height="20">
							<th class="text14" >&nbsp;F.brev.&nbsp;</th>
		                    <th class="text14" >&nbsp;Dato&nbsp;</th>
		                    <th class="text14" >&nbsp;Tid&nbsp;</th>
		                    <th class="text14" >&nbsp;Event&nbsp;</th>
		                    <th class="text14" >&nbsp;Tekst&nbsp;</th>
		                    <th class="text14" >&nbsp;Bruker&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.trackAndTraceList}" varStatus="counter">    
			               	<tr class="tableRow">
			               		<td class="tableCellFirst">&nbsp;${record.frBrev}</td>
				               	<td class="tableCell" >&nbsp;${record.date}</td>
				               	<td class="tableCell" >&nbsp;${record.time}</td>
				               	<td class="tableCell" >&nbsp;${record.event}</td>
				               	<td class="tableCell" >&nbsp;${record.textLoc}</td>
				               	<td class="tableCell" >&nbsp;${record.user}</td>
		           			</tr>
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
       			</table>
				
			
		</td>
		</tr>
		
	<tr height="5px"><td class="text" align="left"></td></tr>
						
	<%-- --------------------------- --%>
	<%-- Validation errors FRONT END --%>
	<%-- --------------------------- --%>
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
	<tr height="5"><td></td></tr>
	<tr>
		<td colspan="10">
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">					
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
	
	<%-- -------------------------- --%>
	<%-- Validation errors BACK END --%>
	<%-- -------------------------- --%>
	<c:if test="${not empty model.errorMessage}">
		<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">					
	            <ul>
	            	<li >${model.errorMessage}</li>
	            </ul>
			</td>
			</tr>
			</table>
		</td>
		</tr>		
	</c:if>
			
	<%-- ------------------------------------------------- --%>
    	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
    	<%-- ------------------------------------------------- --%>
    	<tr>
		<td class="text14" align="left" >
			<form name="createNewLineForm" id="createNewLineForm" method="post" action="transportdisp_workflow_childwindow_trackandtrace.do">
				<input type="hidden" name="action" id="action" value='doInit'>
				<input type="hidden" name="avd" id="avd" value='${model.avd}'>
				<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" id="submit" value='<spring:message code="systema.transportdisp.search.remove.filter"/>'>
			</form>
		</td>
	</tr>
	<tr height="5"><td class="text14" align="left" ></td></tr>
    	<tr>
			<td >
			<form action="transportdisp_workflow_childwindow_trackandtrace_edit.do" name="transportdispUpdateTracktForm" id="transportdispUpdateTracktForm" method="post">
		 	<%--Required key parameters from the Topic parent --%>
		 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
		 	<input type="hidden" name="action" id="action" value='doUpdate'/>
			<input type="hidden" name="ttavd" id="ttavd" value='${model.avd}'>
			<input type="hidden" name="ttopd" id="ttopd" value='${model.opd}'>
			<input type="hidden" name="updateId" id="updateId" value="${model.updateId}">
			
		 	<%-- Record CREATE --%>
				<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
					
		 		<tr height="15">
		 			<td class="text14White" align="left" >
		 				<b>&nbsp;&nbsp;Varelinje&nbsp;</b>
							<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;<font id="editLineNr"></font>
	 				</td>
 				</tr>
				</table>
			<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
		 		<tr height="12"><td class="text" align="left"></td></tr>
		 		<tr>
			 		<td>
				 		<table  class="tableBorderWithRoundCornersGray" width="95%" border="0" cellspacing="0" cellpadding="0">
				 			<tr height="5"><td class="text" align="left"></td></tr>
				 			<tr >
				 				
				            	<td class="text14" align="left"><span title="ttfbnr">&nbsp;Fraktbrevnr.</span></td>
					            <td class="text14" align="left">
					            	<img onMouseOver="showPop('ttacti_info');" onMouseOut="hidePop('ttacti_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			            			<span title="ttacti"><font class="text14RedBold" >*</font>Kode</span>
									<div class="text14" style="position: relative;" align="left">
										<span style="position:absolute; left:30px; top:-500px; width:250px" id="ttacti_info" class="popupWithInputText"  >
											<font class="text14">
						           			<b>Kode</b>
						           			<ul>
						           				<c:forEach var="record" items="${model.codeList}" varStatus="counter">
						           					<li><font class="text10"><b>${record.kfkod}</b>&nbsp;${record.kftxt}</font></li>
						           				</c:forEach>
						           			</ul>
					           			</font>
										</span>
									</div>	
					            </td>
			            		<td width="5%" class="text14" align="left"><span title="ttdate/tttime"><font class="text14RedBold" >*</font>Hendelsestidspunkt</span></td>
			            		<td class="text14" align="left"><span title="ttmanu">&nbsp;&nbsp;Status</span></td>
			            		<td class="text14" align="left">
			            			<img onMouseOver="showPop('ttedev_info');" onMouseOut="hidePop('ttedev_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			            			<span title="ttedev">Event code</span>
									<div class="text14" style="position: relative;" align="left">
										<span style="position:absolute; left:30px; top:2px; width:250px" id="ttedev_info" class="popupWithInputText"  >
											<font class="text14">
						           			<b>Event code</b>
						           			<ul>
						           				todo
						           			</ul>
					           			</font>
										</span>
									</div>	
		            			</td>
			            		<td class="text14" align="left">
			            			<img onMouseOver="showPop('ttedre_info');" onMouseOut="hidePop('ttedre_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			            			<span title="ttedre">Reason code</span>
									<div class="text14" style="position: relative;" align="left">
										<span style="position:absolute; left:25px; top:2px; width:250px" id="ttedre_info" class="popupWithInputText"  >
											<font class="text14">
						           			<b>Reason code</b>
						           			<ul>
						           				todo
						           			</ul>
					           			</font>
										</span>
									</div>		
			            		</td>
					        </tr>
					        <tr>
				        		<td class="text14" align="left" >&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="ttfbnr" id="ttfbnr" size="4" maxlength="3" value="${model.record.ttfbnr}"></td>
					            <td class="text14" align="left" >
					            	<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="ttacti" id="ttacti" >
					 				  <option value="">-velg-</option>
					 				  <c:forEach var="record" items="${model.codeList}" >
					 				  		<option title="${record.kftxt}" value="${record.kfkod}"<c:if test="${model.record.ttacti == record.kfkod && (not empty model.record.ttacti)}"> selected </c:if> >${record.kfkod}</option>
									  </c:forEach>
									</select>							            	
					            </td>
					            <td nowrap width="5%" class="text14" align="left"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField"  name="ttdate" id="ttdate" size="10" maxlength="8" value="${model.record.ttdate}">
					 			&nbsp;Kl:<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField"  name="tttime" id="tttime" size="7" maxlength="6" value="${model.record.tttime}"></td>	
					            	
					            <td class="text14" align="left" >
				            		  <select class="inputTextMediumBlue" name="ttmanu" id="ttmanu" >
					 				  <option value="X" <c:if test="${model.record.ttmanu == 'X'}"> selected </c:if> >X</option>
					 				  <option value="J" <c:if test="${model.record.ttmanu == 'J'}"> selected </c:if> >Manuelt</option>
									  <option value="S"<c:if test="${model.record.ttmanu == 'S'}"> selected </c:if> >Send</option>
									  <option value="F"<c:if test="${model.record.ttmanu == 'F'}"> selected </c:if> >Ferdig</option>
								  </select>
					            </td>	
					            <td class="text14" align="left" >
					            <input type="text" class="inputTextMediumBlue"  name="ttedev" id="ttedev" size="4" maxlength="3" value='${model.record.ttedev}'>
					            	<%--
					            	<select class="inputTextMediumBlue" name="ttedev" id="ttedev" >
					 				  <option value="">-velg-</option>
									  <c:forEach var="record" items="${model.eventCodeList}" >
					 				  		<option title="${record.kftxt}" value="${record.kfkod}"<c:if test="${'*LA' == record.kfkod}"> selected </c:if> >${record.kfkod}</option>
									  </c:forEach> 
								</select>
								 --%>
					            </td>	
					            <td class="text14" align="left" >
					            <input type="text" class="inputTextMediumBlue"  name="ttedre" id="ttedre" size="5" maxlength="5" value='${model.record.ttedre}'>
					            <%-- 
					            	<select class="inputTextMediumBlue" name="ttedre" id="ttedre" >
					 				  <option value="">-velg-</option>
									  <c:forEach var="record" items="${model.genericList}" >
					 				  		<option value="${record.kfkod}"<c:if test="${Xmodel.record.ttedre == record.kfkod}"> selected </c:if> >${record.kfkod}</option>
									  </c:forEach>
								</select>
								--%>
					            </td>							            
					        </tr>
					        <tr height="5"><td class="text" align="left"></td></tr>
					        <tr >
				 				<td class="text14" align="left"><span title="tttexl"><font class="text14RedBold" >*</font>NO tekst</span></td>
				 				<td colspan="10" class="text14" align="left"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField"  name="tttexl" id="tttexl" size="75" maxlength="71" value='${model.record.tttexl}'></td>
					        </tr>
					        <tr >
				 				<td class="text14" align="left"><span title="tttext">&nbsp;EN tekst</span></td>
				 				<td colspan="10" class="text14" align="left"><input type="text" class="inputTextMediumBlue"  name="tttext" id="tttext" size="75" maxlength="71" value='${model.record.tttext}'></td>
					        </tr>
					        <tr height="5"><td class="text" align="left"></td></tr>
					        <tr >
				 				<td class="text14" align="left"><span title="ttdepo">&nbsp;Depot/term</span></td>
				 				<td colspan="10" class="text14" align="left"><input type="text" class="inputTextMediumBlue"  name="ttdepo" id="ttdepo" size="12" maxlength="10" value='${model.record.ttdepo}'></td>
					        </tr>
					        <tr >
				 				<td class="text14" align="left"><span title="ttname">&nbsp;Name</span></td>
				 				<td colspan="10" class="text14" align="left"><input type="text" class="inputTextMediumBlue"  name="ttname" id="ttname" size="12" maxlength="10" value='${model.record.ttname}'></td>
					        </tr>
					        <tr height="10"><td class="text" align="left"></td></tr>
					              
					         <tr >
					         	<%--
				 				<td class="text14" align="left"><span title="ttdatl/tttiml">&nbsp;Loggføringstid</span></td>
				 				<td colspan="2" class="text14" align="left"><input readonly type="text" class="inputTextReadOnly"  name="ttdatl" id="ttdatl" size="10" maxlength="8" value='${Xmodel.record.ttdatl}'>
					 				&nbsp;Kl:<input readonly type="text" class="inputTextReadOnly"  name="tttiml" id="tttiml" size="7" maxlength="6" value='${Xmodel.record.tttiml}'>
					 			</td>
					 			 --%>
					 			<td class="text14" align="left"><span title="ttuser">&nbsp;Av bruker ID</span></td>
				 				<td class="text14" align="left">
				 					<c:choose>
				 					<c:when test="${not empty model.record.ttuser}">
				 						<input readonly type="text" class="inputTextReadOnly"  name="ttuser" id="ttuser" size="8" maxlength="10" value='${model.record.ttuser}'>
				 					</c:when>
				 					<c:otherwise>
				 						<input readonly type="text" class="inputTextReadOnly"  name="ttuser" id="ttuser" size="8" maxlength="10" value='${user.user}'>
				 					</c:otherwise>
				 				</c:choose>
				 				</td>
					        </tr>
					       
					        <tr height="8"><td class="text" align="left"></td></tr>

				        </table>
			        </td>
		        </tr>
			    <tr height="10"><td colspan="2" ></td></tr>
			    <tr>	
				    <td align="left" colspan="5">
				    	<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.transportdisp.createnew"/>'>
					</td>
		        </tr>
      	        </table>
         	</form>
        </td>
    </tr>

	<tr height="10"><td ></td></tr>
		
		
	</table> 
