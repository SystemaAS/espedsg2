<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%> 
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTransportDispChildWindows.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow_childwindow_trips.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
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

<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align:bottom;" src="resources/images/list.gif" border="0" alt="general list">
				<font class="tabLink"><spring:message code="systema.transportdisp.workflow.trip.tab"/>&nbsp;&nbsp;<font class="inputTextMediumBlue" style="background-color:lightgrey;">Ordre ${model.opd}</font></font>
			</td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
	</tr>

	<tr>
	<td>
	<%-- search filter component --%>
		<table width="100%" class="tabThinBorderWhiteWithSideBorders" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="15"><td></td></tr>
 	        <tr>
        		<td>
        		<form name="searchForm" id="searchForm" action="transportdisp_workflow_childwindow.do?action=doFind" method="post" >
        		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
        		<input type="hidden" name="opd" id="opd" value='${model.opd}'>
        		
	 	        <table style="width:99%;">
	 	        <tr>	
	                <td valign="bottom" class="text12" align="left" >&nbsp;&nbsp;
                		<img onMouseOver="showPop('dpts_info');" onMouseOut="hidePop('dpts_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                		<span title="wssavd"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.department"/></span>
		 				<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_avd.do?action=doInit','avdWin','top=150px,left=300px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 					<img id="imgAvdSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 				</a>
	                </td>
	                <div class="text11" style="position: relative;" align="left">
						<span style="position:absolute; left:50px; top:10px;" id="dpts_info" class="popupWithInputText"  >
 							<font class="text11">
			           			<b>Dept</b>
			           			<div>
			           			<p>Special search codes</p>
			           			<ul>
			           				<li>Blank=default, else dept.number</li>
			           				<li><b>ALL</b>=All departments</li>
			           			    <li><b>IMP</b>=Import</li>
			           			    <li><b>EXP</b>=Export</li>
			           			    <li><b>DOM</b>=Domestic</li>
			           			    <li><b>IN</b>=Inbound domestic</li>
			           			    <li><b>OUT</b>=Outbound domestic</li>
			           			</ul>	
			           			</div>
		           			</font>
						</span>
					</div>
					<td valign="bottom" class="text12" align="left" >&nbsp;&nbsp;&nbsp;<span title="wsstur"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.trip"/></span></td>
	                <td valign="bottom" class="text12" align="left" >&nbsp;&nbsp;&nbsp;<span title="wtusg"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.sign"/></span></td>
	                <td valign="bottom" class="text12" align="left" >&nbsp;&nbsp;&nbsp;<span title="wtubiln"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.trucknr"/></span></td>
	                <td valign="bottom" class="text12" align="left" >&nbsp;&nbsp;&nbsp;<span title="wtustef"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.from"/></span></td>
	                <td valign="bottom" class="text12" align="left" >
	                		&nbsp;&nbsp;&nbsp;<span title="wtudt/wtudt2"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.date"/></span>
	                		<img src="resources/images/calendar.gif" height="12px" width="12px" border="0" alt="date">
                	</td>
	                <td valign="bottom" class="text12" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.to"/></td>
	                <td valign="bottom" class="text12" align="left" >
	                		&nbsp;&nbsp;&nbsp;<span title="wtudtt/wtudtt2"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.date"/></span>
	                		<img src="resources/images/calendar.gif" height="12px" width="12px" border="0" alt="date">
	                </td>
	                <td valign="bottom" class="text12" align="left" >&nbsp;&nbsp;&nbsp;<span title="wssst"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.status"/></span></td>
	                <td>&nbsp;</td>
				</tr>
				<tr>			       
	                <c:choose>
						<c:when test="${not empty searchFilter.wssavd}">	
			                <td align="left" >&nbsp;<input type="text" class="inputTextMediumBlueUPPERCASE" name="wssavd" id="wssavd" size="5" maxlength="4" value='${searchFilter.wssavd}'>&nbsp;</td>
		                </c:when>
		                <c:otherwise>
		                		<td align="left" >&nbsp;<input type="text" class="inputTextMediumBlueUPPERCASE" name="wssavd" id="wssavd" size="5" maxlength="4" value='${model.record.tuavd}'>&nbsp;</td>
		                </c:otherwise>
	                </c:choose>
					<td align="left" >&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueUPPERCASE" name="wsstur" id="wsstur" size="9" maxlength="8" value='${searchFilter.wsstur}'>&nbsp;</td>
					<td align="left" >&nbsp;<input type="text" class="inputTextMediumBlueUPPERCASE" name="wtusg" id="wtusg" size="5" maxlength="5" value='${searchFilter.wtusg}'>&nbsp;</td>
					<td align="left" >&nbsp;<input type="text" class="inputTextMediumBlueUPPERCASE" name="wtubiln" id="wtubiln" size="10" maxlength="10" value='${searchFilter.wtubiln}'>&nbsp;</td>
					<td align="left" >&nbsp;<input type="text" class="inputTextMediumBlueUPPERCASE" name="wtustef" id="wtustef" size="6" maxlength="5" value='${searchFilter.wtustef}'>&nbsp;</td>
					<td align="left" >&nbsp;
						<input type="text" class="inputTextMediumBlue" name="wtudt" id="wtudt" size="9" maxlength="8" value='${searchFilter.wtudt}'>
						-<input type="text" class="inputTextMediumBlue" name="wtudt2" id="wtudt2" size="9" maxlength="8" value='${searchFilter.wtudt2}'>
						
					</td>
					<td align="left" >&nbsp;<input type="text" class="inputTextMediumBlueUPPERCASE" name="wtustet" id="wtustet" size="6" maxlength="5" value='${searchFilter.wtustet}'>&nbsp;</td>
					<td align="left" >&nbsp;
						<input type="text" class="inputTextMediumBlue" name="wtudtt" id="wtudtt" size="9" maxlength="8" value='${searchFilter.wtudtt}'>
						-<input type="text" class="inputTextMediumBlue" name="wtudtt2" id="wtudtt2" size="9" maxlength="8" value='${searchFilter.wtudtt2}'>
						
					</td>
					<td class="text11" align="left" >
						<select name="wssst" id="wssst">
								<option value="" <c:if test="${searchFilter.wssst == ''}"> selected </c:if> >Åpne</option>
								<option value="A" <c:if test="${searchFilter.wssst == 'A'}"> selected </c:if> >A-Stengde</option>
			            		<option value="B" <c:if test="${searchFilter.wssst == 'B'}"> selected </c:if> >B-Underveis</option>
			            		<option value="C" <c:if test="${searchFilter.wssst == 'C'}"> selected </c:if> >C-Ferdige</option>
			            		<option value="Z" <c:if test="${searchFilter.wssst == 'Z'}"> selected </c:if> >Alle</option>
			            		
						</select>
					</td>
					<td valign="bottom" align="left" >&nbsp;
						<input class="inputFormSubmit" type="submit" name="submit" id="submitSearch" name="submitSearch" value='<spring:message code="systema.transportdisp.search"/>'>
	                </td>
				</tr>
				</table>
				</form>
				</td>
			</tr>
			<tr height="5"><td></td></tr>
			<%-- -------------------------- --%>
			<%-- Validation errors on model --%>
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
			<tr height="5"><td></td></tr>
			
	            
		</table>		
	</td>
	</tr>
	
		<tr>
		<td>
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<table style="width:100%;" id="wrapperTable" class="tabThinBorderWhite" cellspacing="1">
			<tr height="10"><td></td></tr> 
			<%-- -------------------- --%>
			<%-- Datatables component --%>
			<%-- -------------------- --%>
			<%-- list component --%>
			<c:if test="${not empty list}">
				<c:if test="${not empty model.containerTripList.maxWarning}">
					<tr>	
						<td class="listMaxLimitWarning">
						<img style="vertical-align:bottom;" src="resources/images/redFlag.png" width="16" height="16" border="0" alt="Warning">
						${model.containerTripList.maxWarning}</td>
					</tr>
					<tr height="2"><td></td></tr>
				</c:if>
				<tr>
					<td>
					<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
						 the cosmetic frame will not follow the whole datatable grid including the search field... --%>
					<table id="containerdatatableTable" width="100%" cellspacing="1" align="left">
					<tr>
					<td>
					<%-- this is the datatables grid (content) --%>
					<table id="workflowTrips" class="display compact cell-border" width="100%" >
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text12">Pluk.&nbsp;</th>
						    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.department"/>&nbsp;</th>
						    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.trip"/>&nbsp;</th>
						    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.sign"/>&nbsp;</th>  
		                    <th class="text12"><span title="Trip">
		                    		<img style="vertical-align: bottom;" src="resources/images/lorry_green.png" width="15px" height="15px" border="0" alt="lorry no.">
		                    		<spring:message code="systema.transportdisp.workflow.trip.list.search.label.trucknr"/>
		                    		</span>
                    		</th>
                    		<th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.ordertype"/>&nbsp;</th>
						    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.pda.status"/>&nbsp;</th>
						    <th class="text12" nowrap><span title="postal code">
			                    	<img style="vertical-align: bottom;" src="resources/images/addressIcon.png" width="11px" height="11px" border="0" alt="address">
			                    	<spring:message code="systema.transportdisp.workflow.trip.list.search.label.from"/>
			                    	</span>
			                </th>
		                    <th class="text12">
		                    		<spring:message code="systema.transportdisp.workflow.trip.list.search.label.date"/>&nbsp;
	                    	</th>
   		                    <th class="text12">
	                    		<img style="vertical-align:bottom;" src="resources/images/clock2.png" width="12" height="12" border="0" alt="time">&nbsp;
   		            		</th> 
		                    <th class="text12" nowrap><span title="postal code">
	                    		<img style="vertical-align: bottom;" src="resources/images/addressIcon.png" width="11px" height="11px" border="0" alt="address">
	                    		<spring:message code="systema.transportdisp.workflow.trip.list.search.label.to"/>
	                    		</span>
		                    </th>
		                    <th class="text12">
		                    		<spring:message code="systema.transportdisp.workflow.trip.list.search.label.date"/>&nbsp;
                    		</th>
   		                    <th class="text12">
	                    		<img style="vertical-align:bottom;" src="resources/images/clock2.png" width="12" height="12" border="0" alt="time">&nbsp;
  		            		</th> 
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.roundTrip"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.weight"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.m3"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.lm"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.fg"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.res"/>&nbsp;</th>
		                    <th class="text12">&nbsp;Status&nbsp;</th>
		                    <!-- 
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.glist"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.gp"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.llist"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.upl"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.upl"/>&nbsp;2</th>
		                     -->
		                </tr> 
		                </thead>
		                <tbody>
		                 <c:forEach items="${list}" var="record" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text11 tableRow" >
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text11 tableRow" >
			                   </c:otherwise>
			               </c:choose>
			               
			               <td align="center" class="text11 tableCellGray">
			               		<c:choose>
				               <c:when test="${record.turclose=='close'}">  
			        		       <a id="alinkTripListId_tupro${record.tupro}_avd${record.tuavd}_opd${model.opd}" onClick="setTripOnOrder(this);" style="display:block;" >
		        		       			<img title="Add trip" src="resources/images/addOrder.png" width="14px" height="15px" border="0" alt="add trip">
			            	       </a>
				               </c:when>
				               <c:otherwise>
				               	   &nbsp;
				               </c:otherwise>
				               </c:choose>
			               </td>
			               
			               <td align="center" class="text11 tableCellGray">${record.tuavd}</td>
			               <td align="center" style="width: 100px;" class="text11MediumBlue tableCellGray"><font class="text11MediumBlue">${record.tupro}</font></td>	
			               <td class="text11 tableCellGray">&nbsp;${record.tusg}</td>
			               <td class="text11 tableCellGray">&nbsp;${record.tubiln}</td>
			               <td class="text11 tableCellGray">&nbsp;${record.tuopdt}</td>
			               <td align="center" class="text11 tableCellGray" >
			               	<c:if test="${not empty record.pdaStat}">
				               <c:choose>
					               <c:when test="${record.pdaStat=='inWork'}">
					               		<img title="inProcess" src="resources/images/engines.png" width="18px" height="18px" border="0" alt="PDA">
					               </c:when>
					               <c:otherwise>
					               		<c:choose>
						               		<c:when test="${record.pdaStat=='finished'}">
						               			<img title="finished" src="resources/images/checkmarkOK.png" width="14px" height="14px" border="0" alt="PDA">
						               		</c:when>
						               		<c:otherwise>
						               			<img title="assigned" src="resources/images/warning.png" width="18px" height="18px" border="0" alt="PDA">
						               		</c:otherwise>
					               		</c:choose>
					               </c:otherwise>
					           </c:choose> 
				           </c:if>   
			               </td>
	            		   <td class="text11 tableCellGray">&nbsp;${record.tustef}</td>
	            		   <td class="text11 tableCellGray">
	            		   	<c:if test="${not empty record.tudt && fn:startsWith(record.tudt, '20')}">
	            		   		<fmt:parseDate value="${record.tudt}" var="dateEtdDate" pattern="yyyyMMdd" />
	            		   		&nbsp;<fmt:formatDate pattern="yyyyMMdd" value="${dateEtdDate}"/>
	            		   	</c:if>
	            		   </td>
	            		   <td class="text11 tableCellGray">&nbsp;${record.tutm}</td>
	            		   <td class="text11 tableCellGray">&nbsp;${record.tustet}</td>
	            		   <td class="text11 tableCellGray">
            		   		<c:if test="${not empty record.tudtt && fn:startsWith(record.tudtt, '20')}">
	            		   		<fmt:parseDate value="${record.tudtt}" var="dateEtaDate" pattern="yyyyMMdd" />
	            		   		&nbsp;<fmt:formatDate pattern="yyyyMMdd" value="${dateEtaDate}"/>
	            		   	</c:if>
	            		   </td>
	            		   <td class="text11 tableCellGray">&nbsp;${record.tutmt}</td>
	            		   <td align="center" class="text11 tableCellGray">&nbsp;${record.turund}</td>
	            		   <td align="right" class="text11 tableCellGray">&nbsp;${record.tutvkt}&nbsp;</td>
	            		   <td align="right" class="text11 tableCellGray">&nbsp;${record.tutm3}&nbsp;</td>
	            		   <td align="right" class="text11 tableCellGray">&nbsp;${record.tutlm2}&nbsp;</td>
	            		   <td align="right" class="text11 tableCellGray">&nbsp;${record.tupoen}&nbsp;</td>
	            		   <td align="right" class="text11 tableCellGray">&nbsp;${record.tures}&nbsp;</td>
	            		   <td align="center" class="text11 tableCellGray">
            		   		<c:choose>	
	            		   		<c:when test="${record.turclose=='close'}">
	            		   			<%--meaning this is open and could be closed --%>
	            		   			<img title="Åpen" style="vertical-align:bottom;" src="resources/images/bulletGreen.gif" border="0" alt="close">
			   					</c:when>
			   					<c:otherwise>
			   						<%--meaning this is closed and could be opened --%>
			   						<img title="Ikke åpen" style="vertical-align:bottom;" src="resources/images/bulletRed.gif" border="0" alt="open">
			   					</c:otherwise>
		   					</c:choose>
	            		   	</td>
	            		   
			            </tr> 
		            	</c:forEach>
		            </tbody>
		            </table>
		            </td>
            		</tr>
	            		
            		</table> <%--containerdatatableTable END --%>
            		</td>
	            </tr>
	           </c:if> 
	        
	        <tr height="2"><td>&nbsp;</td></tr>
	        
			</table> <%--wrapperTable END --%>
         </td>
         </tr>
         
         <%-- Validation errors --%>
		<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
		<tr>
			<td>
	           	<table class="text12" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
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
         
         
         <tr height="5"><td>&nbsp;</td></tr>
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

