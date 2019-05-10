<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTransportDispChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-dialog{font-size:11pt;}
	.ui-datepicker { font-size:9pt;}
	.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
	.ui-timepicker-div dl { text-align: left; }
	.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; }
	.ui-timepicker-div dl dd { margin: 0 10px 10px 40%; }
	.ui-timepicker-div td { font-size: 90%; }
	.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
	
	.ui-timepicker-rtl{ direction: rtl; }
	.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
	.ui-timepicker-rtl dl dt{ float: right; clear: right; }
	.ui-timepicker-rtl dl dd { margin: 0 40% 10px 10px; }
	
	
	</style>
	
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
							<th class="text14" title="Fraktbrev" >&nbsp;F.b.&nbsp;</th>
		                    <th class="text14" >&nbsp;Dato&nbsp;</th>
		                    <th class="text14" >&nbsp;Tid&nbsp;</th>
		                    <th class="text14" >&nbsp;Kode&nbsp;</th>
		                    <th class="text14" >&nbsp;Tekst&nbsp;</th>
		                    <th class="text14" title="EDI Event kode" >&nbsp;Ec&nbsp;</th>
		                    <th class="text14" title="EDI Reason kode" >&nbsp;Rc&nbsp;</th>
		                    <th class="text14" title="Manuell Opprettet/Auto Opprettet">&nbsp;Oppr.&nbsp;
		                    <th class="text14" title="Status: (S)end, (F)erdig">&nbsp;St.&nbsp;
		                    <th class="text14" title="Sted i google maps...">&nbsp;Sted&nbsp;</th>
		                    <th class="text14" >&nbsp;Depo&nbsp;</th>
		                    <th class="text14" >&nbsp;Navn&nbsp;</th>
		                    <th class="text14" >&nbsp;Bruker&nbsp;</th>
		                    
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.trackAndTraceList}" varStatus="counter">    
			               	<tr class="tableRow">
			               		<td width="2%" class="tableCellFirst">&nbsp;${record.frBrev}</td>
				               	<td width="2%" class="tableCell" >&nbsp;${record.date}</td>
				               	<td width="2%" class="tableCell" >&nbsp;${record.time}</td>
				               	<td width="2%" class="tableCell" >&nbsp;${record.event}</td>
				               	<td class="tableCell" >&nbsp;${record.textLoc}</td>
				               	<td width="2%" class="tableCell" >&nbsp;${record.ediCode}</td>
				               	<td width="2%" class="tableCell" >&nbsp;${record.ediReason}</td>
				               	<td width="2%" class="tableCell"  >
				               		<c:choose>
				               		<c:when test="${record.manualCode == 'J'}">
				               			Man.
				               		</c:when>
				               		<c:otherwise>
				               			Auto
				               		</c:otherwise>
				               		</c:choose>
				               	</td>
				               	<td width="2%" class="tableCell" >
				               		<if test="${not empty record.status}">
					               		<c:choose>
					               			<c:when test="${record.status == 'S' || record.status == 'F' }">
						               			<c:if test="${record.status == 'S'}">
						               				&nbsp;Send
						               			</c:if>
						               			<c:if test="${record.status == 'F'}">
						               				&nbsp;Ferdig
						               			</c:if>
					               			</c:when>
					               			<c:otherwise>
					               				&nbsp;${record.status}
					               			</c:otherwise>
				               			</c:choose>
				               		</if>
				               		
				               	</td>
				               	<td width="2%" class="tableCell" >
				               		<%--
				               		<a target="_blank" href="https://www.google.no/maps/?q=60.279938,5.236414&t=h">
				               			test
				               		</a>
				               		 --%>
				               		<c:if test="${not empty record.locCode && not empty record.locText}">
				               			<c:choose>
				               			<c:when test="${not empty record.latitude && not empty record.longitude}">
					               			<a target="_blank" href="https://www.google.no/maps/?q=${record.latitude},${record.longitude}&t=h">
					               				${record.locCode}&nbsp;${record.locText}
					               			</a>
				               			</c:when>
				               			<c:otherwise>
				               				${record.locCode}&nbsp;${record.locText}
				               			</c:otherwise>
				               			</c:choose>
				               		</c:if>
				               	</td>
				               	<td width="2%" class="tableCell" >&nbsp;${record.depot}</td>
				               	<td width="2%" class="tableCell" >&nbsp;${record.name}</td>
				               	<td width="2%" class="tableCell" >&nbsp;${record.user}</td>
				               	
		           			</tr>
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
       			</table>
				
			
		</td>
		</tr>
		
						
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
    	<c:if test="${empty doUpdated }">
	<tr>
    <td>
    		<button name="inputShowFormButton" id="inputShowFormButton" class="buttonGrayWithGreenFrame" type="button" ><spring:message code="systema.transportdisp.createnew"/></button>
    </td>
    </tr>
    </c:if>
    
    <tr height="5px"><td class="text" align="left"></td></tr>
	
    <tr>
    <td>
    
    	<c:choose>
		<c:when test="${empty doUpdated }">
	    <div id="divInputForm" style="display:none">
	    </c:when>
	    <c:otherwise>
	    <div id="divInputForm" style="display:inline">
	    </c:otherwise>
    </c:choose>
    
	<table style="width:80" >
	    	<tr>
			<td class="text14" align="left" >
				<form name="createNewLineForm" id="createNewLineForm" >
					<input type="hidden" name="action" id="action" value='doInit'>
					<input type="hidden" name="avd" id="avd" value='${model.avd}'>
					<input type="hidden" name="opd" id="opd" value='${model.opd}'>
					<button id="clearNewLineFormValuesButton" tabindex=-1 class="inputFormSubmitStd" type="button"><spring:message code="systema.transportdisp.search.remove.filter"/></button> 
					<c:if test="${empty doUpdated }">
						<img id="inputShowFormImage" src="resources/images/sort_down.png" border="0" width="8px" height="8px" alt="edit">
					</c:if>
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
			 	<input type="hidden" name="ttuser" id="ttuser" value='${user.user}'>
				<input type="hidden" name="ttavd" id="ttavd" value='${model.avd}'>
				<input type="hidden" name="ttopd" id="ttopd" value='${model.opd}'>
				<input type="hidden" name="updateId" id="updateId" value="${model.updateId}">
				
			 	<%-- Record CREATE --%>
					<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						
			 		<tr height="15">
			 			<td class="text14White" align="left" >
			 				<b>&nbsp;&nbsp;Logglinje&nbsp;</b>
								<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;<font id="editLineNr"></font>
		 				</td>
	 				</tr>
					</table>
				<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
			 		<tr height="12"><td class="text" align="left"></td></tr>
			 		<tr>
				 		<td>
					 		<table  class="tableBorderWithRoundCornersGray" width="99%" border="0" cellspacing="0" cellpadding="0">
					 			<tr height="5"><td class="text" align="left"></td></tr>
					 			<tr >
					 				<td class="text14" align="left">&nbsp;</td>
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
				            		
						        </tr>
						        <tr>
						        		<td class="text14" align="left">&nbsp;</td>
					        		    <td class="text14" align="left" >
						            	<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="ttacti" id="ttacti" >
						 				  <option value="">-velg-</option>
						 				  <c:forEach var="record" items="${model.codeList}" >
						 				  		<option title="${record.kftxt}" value="${record.kfkod}"<c:if test="${model.record.ttacti == record.kfkod && (not empty model.record.ttacti)}"> selected </c:if> >${record.kfkod}</option>
										  </c:forEach>
										</select>							            	
						            </td>
						            <td nowrap width="5%" class="text14" align="left"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField"  name="ttdate" id="ttdate" size="10" maxlength="8" value="${model.record.ttdate}">
						 			&nbsp;Kl:<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField"  name="tttime" id="tttime" size="5" maxlength="4" value="${model.record.tttime}">
						 			</td>	
						            	
						            	
						            <td class="text14" align="left" >
						            <input type="text" class="inputTextMediumBlue"  name="ttedev" id="ttedev" size="4" maxlength="3" value='${model.record.ttedev}'>
						            
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
	</div>
	</td>
	</tr>	
		
	</table> 
