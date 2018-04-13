<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%> 
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTransportDisp.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow.js?ver=${user.versionEspedsg}"></SCRIPT>	
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
	/* this is in order to customize a SPECIFIC ui dialog in the .js file ...dialog() */
	/*.main-dialog-class .ui-widget-header{ background-color:#DAC8BA } */
	.main-dialog-class .ui-widget-content{ background-image:none;background-color:lemonchiffon }
	</style>

<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkOrderListId" style="display:block;" id="ordersOpen" href="transportdisp_mainorderlist.do?action=doFind&avd=${searchFilter.wssavd}">
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink"><spring:message code="systema.transportdisp.workflow.trip.all.openorders.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align:bottom;" src="resources/images/list.gif" border="0" alt="general list">
				<font class="tabLink"><spring:message code="systema.transportdisp.workflow.trip.tab"/></font>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<div class="clazzOrderTripTab" <c:if test="${empty model.record.tupro}">style="visibility:collapse;"</c:if> >
				<a style="display:block;" class="ordersTripOpen" href="transportdisp_mainorderlist.do?action=doFind&wssavd=${model.record.tuavd}&wstur=${model.record.tupro}">
					<img title="Planning" style="vertical-align:bottom;" src="resources/images/math.png" width="16" height="16" border="0" alt="planning">
					<font class="tabDisabledLink"><font class="text12MediumBlue"><spring:message code="systema.transportdisp.open.orderlist.trip.label"/></font></font>
					<c:choose>
						<c:when test="${not empty model.record.tupro}">
							&nbsp;<font id="tuproTab" class="text12MediumBlue">&nbsp;${model.record.tupro}&nbsp;</font>
						</c:when>
						<c:otherwise>
							&nbsp;<font id="tuproTab" class="text11Gray">&nbsp;</font>
						</c:otherwise>
					</c:choose>
				</a>
				</div>
			</td>
			<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
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
        		<form name="searchForm" id="searchForm" action="transportdisp_workflow.do?action=doFind" method="post" >
        		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
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
						    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.department"/>&nbsp;</th>
						    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.trip"/>&nbsp;</th>
						    <th class="text12"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.edit"/></th>
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
		                    
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.antopd"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.antpod"/>&nbsp;</th>
		                    
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.weight"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.m3"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.lm"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.fg"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.res"/>&nbsp;</th>
		                    <th class="text12">&nbsp;
	                    		<spring:message code="systema.transportdisp.workflow.trip.list.search.label.closeopen"/>&nbsp;
	                    	</th>
	                    	<c:if test="${empty searchFilter.wssst || searchFilter.wssst != 'Z'}"> 
		                    	<th class="text12">
			            			<input style="cursor:pointer;" type="button" value="<spring:message code="systema.transportdisp.workflow.trip.list.search.label.closeopen"/>" name="currenttripsColumnHeaderButtonCloseOpen" id="currenttripsColumnHeaderButtonCloseOpen" onClick="getValidCheckis(this);">
			                    </th>
		                    </c:if>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.glist"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.gp"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.llist"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.upl"/>&nbsp;</th>
		                    <th class="text12">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.upl"/>&nbsp;2</th>
		                </tr> 
		                </thead>
		                <tbody>
		                 <c:forEach items="${list}" var="record" varStatus="counter">    
			               <tr class="text11 tableRow" >
			             
			               <td align="center" class="text11 tableCellGray">
			               		${record.tuavd}
			               </td>
			               <td nowrap align="left" style="width: 100px;" class="text11MediumBlue tableCellGray" id="htmlpost_${counter.count}">
			               <%--OLD before upgrade to Spring 4 OBS:remove if the above is working
			               	<td nowrap align="left" style="width: 100px;" class="text11MediumBlue tableCellGray" id="avd_${record.tuavd}@tripnr_${record.tupro}@statusA_${record.turclose}@${counter.count}"> 
			               --%>
				               <c:choose>
				               <c:when test="${record.turclose=='close'}"> 
				               		<a id="alinkTripListId_${counter.count}" onClick="setBlockUI(this);" style="display:block;" href="transportdisp_mainorderlist.do?action=doFind&wssavd=${record.tuavd}&wstur=${record.tupro}">
										<div style="line-height: 25px;line-width: 100px;" id="dtuavd${record.tuavd}_dtupro${record.tupro}_onlistA${counter.count}" ondrop="drop(event)" ondragenter="highlightDropArea(event)" ondragleave="noHighlightDropArea(event)" ondragover="allowDrop(event)" > 
										<img title="Trip planning" src="resources/images/math.png" width="14px" height="14px" border="0" alt="planning">
										<font class="text11MediumBlue">${record.tupro}</font>
										</div>
			            	       </a>
			            	     
				               </c:when>
				               <c:otherwise>
				               		&nbsp;${record.tupro}
				               </c:otherwise>
				               </c:choose>
			               </td>
			               	
			               <td style="width: 50px;" align="center" nowrap style="cursor:pointer;" class="text11 tableCellGray" id="avd_${record.tuavd}@tripnr_${record.tupro}@status_${record.turclose}@${counter.count}">
			               		<div style="line-height: 25px;line-width: 50px;" id="dtuavd${record.tuavd}_dtupro${record.tupro}_onlist${counter.count}" ondrop="drop(event)" ondragenter="highlightDropArea(event)" ondragleave="noHighlightDropArea(event)" ondragover="allowDrop(event)" >
			               		&nbsp;&nbsp;<img title="Edit trip ${record.tupro}" style="vertical-align:middle;cursor:pointer;" src="resources/images/update.gif" border="0" alt="edit">
			               		<%--<font class="text11MediumBlue"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.edit"/></font> --%>
			               		</div>
			               </td>
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
	            		   
	            		   <td align="center" class="text11 tableCellGray">&nbsp;${record.tuao}</td>
	            		   <td align="center" class="text11 tableCellGray">&nbsp;${record.podTxt}</td>
	            		   
	            		   <td align="right" class="text11 tableCellGray">&nbsp;${record.tutvkt}&nbsp;</td>
	            		   <td align="right" class="text11 tableCellGray">&nbsp;${record.tutm3}&nbsp;</td>
	            		   <td align="right" class="text11 tableCellGray">&nbsp;${record.tutlm2}&nbsp;</td>
	            		   <td align="right" class="text11 tableCellGray">&nbsp;${record.tupoen}&nbsp;</td>
	            		   <td align="right" class="text11 tableCellGray">&nbsp;${record.tures}&nbsp;</td>
	            		   <td align="center" class="text11 tableCellGray">
	            		   		<c:choose>	
		            		   		<c:when test="${record.turclose=='close'}">
					           		<a href="transportdisp_workflow_closeOpenTrip.do?user=${user.user}&tuavd=${record.tuavd}&tupro=${record.tupro}&tust=A">
	    		    					<img title="Close" style="vertical-align:bottom;" src="resources/images/close.png" width="15" hight="15" border="0" alt="close">
				   					</a>
			   					</c:when>
			   					<c:otherwise>
									<a href="transportdisp_workflow_closeOpenTrip.do?user=${user.user}&tuavd=${record.tuavd}&tupro=${record.tupro}&tust=">
	    		    					<img title="Open" style="vertical-align:bottom;" src="resources/images/open.png" width="18" hight="18" border="0" alt="open">
				   					</a>
			   					</c:otherwise>
		   					</c:choose>
	            		   	</td>
	            		   <c:if test="${empty searchFilter.wssst || searchFilter.wssst != 'Z'}"> 
	            		   		<td class="text11 tableCellGray" align="center"><input class="clazz_checkis_currenttrips" type="checkbox" id="checkis_currenttrips${counter.count}@user=${user.user}&tuavd=${record.tuavd}&tupro=${record.tupro}&tust=<c:if test="${record.turclose=='close'}">A</c:if>"></td>
	            		   </c:if>	
	            		   <td align="center" class="text11 tableCellGray">
	            		   		<a target="_new" href="transportdisp_workflow_renderGodsOrLastlist.do?user=${user.user}&tupro=${record.tupro}&type=G">
    		    					<img title="Glist" style="vertical-align:bottom;" src="resources/images/pdf.png" width="16" height="16" border="0" alt="Glist PDF">
		   						</a>
	            		   </td>
	            		   <td align="center" class="text11 tableCellGray">&nbsp;${record.tutst1}&nbsp;</td>
		               		<td align="center" class="text11 tableCellGray">
	            		   		<a target="_new" href="transportdisp_workflow_renderGodsOrLastlist.do?user=${user.user}&tupro=${record.tupro}&type=L">
    		    					<img title="Llist" style="vertical-align:bottom;" src="resources/images/pdf.png" width="16" hight="16" border="0" alt="Llist PDF">
	   							</a>
	            		   </td>
	            		   
	            		   
	            		   
	            		   
	            		   <td align="center" class="text11 tableCellGray">
	            		   		<input class="inputFormSubmit11Slim" type="button" value="Upload" name="uplButton${counter.count}" onClick="window.open('transportdisp_workflow_childwindow_uploadFile.do?action=doInit&wstur=${record.tupro}','transpDispWorklistFileUpload','top=300px,left=800px,height=210px,width=330px,scrollbars=no,status=no,location=no')">
	            		   		 <%-- OBSOLETE --> now we use the file upload with span/div hidden form and javascrip + jquery ajax
		            		   		 <form name="uploadFileForm_${counter.count}" id="uploadFileForm_${counter.count}" method="post" enctype="multipart/form-data">
		            		   		 	<input onChange="uploadFile(this);" type="file" name="file_${counter.count}" id="file_${counter.count}" />
		            		   		 	
		            		   		 	<%-- everything below this line will be hidden for the end-user but not for jquery
		            		   		 	<input type="hidden" name="applicationUserUpload_${counter.count}" id="applicationUserUpload_${counter.count}" value='${user.user}'>
										<input type="hidden" name="wstur_${counter.count}" id="wstur_${counter.count}" value='${record.tupro}'>
										 <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:250px" id="upload_phantom" class="popupWithInputText"  >
												<input type="text" class="inputText" name="fileNameNew_${counter.count}" id="fileNameNew_${counter.count}" size="20" maxlength="20" value="">
												<select name="wstype_${counter.count}" id="wstype_${counter.count}">
													<c:forEach var="record" items="${user.arkivKodTurList}" >
							                       	 	<option value="${record.arkKod}">${record.arkKod}</option>
													</c:forEach> 
												</select>
												<input class="inputFormSubmit" type="button" name="submitUpload_${counter.count}" id="submitUpload_${counter.count}" value='Save'>
											</span>
										</div>
									</form>
								--%>	 
	            		   </td>
	            		   <td align="center" class="text11 tableCellGray">
            		   			 <form name="uploadFileForm_${counter.count}" id="uploadFileForm_${counter.count}" method="post" enctype="multipart/form-data">
	            		   		 	<input ondragenter="myFileUploadDragEnter(event,this)" ondragleave="myFileUploadDragLeave(event,this)" class="tableBorderWithRoundCornersLightYellow noFileChosenTransparent" style="height:25px;display:block;" onChange="uploadFile(this);" type="file" name="file_${counter.count}" id="file_${counter.count}" />
	            		   		 	
	            		   		 	<%-- everything below this line will be hidden for the end-user but not for jquery--%>
	            		   		 	<input type="hidden" name="applicationUserUpload_${counter.count}" id="applicationUserUpload_${counter.count}" value='${user.user}'>
									<input type="hidden" name="wstur_${counter.count}" id="wstur_${counter.count}" value='${record.tupro}'>
									 <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px; width:250px" id="upload_phantom" class="popupWithInputText"  >
											<select name="wstype_${counter.count}" id="wstype_${counter.count}">
												<c:forEach var="record" items="${user.arkivKodTurList}" >
						                       	 	<option value="${record.arkKod}">${record.arkKod}</option>
												</c:forEach> 
											</select>
											<input class="inputFormSubmit" type="button" name="submitUpload_${counter.count}" id="submitUpload_${counter.count}" value='Save'>
										</span>
									</div>
								</form>
	            		   </td>
			            </tr> 
		            	</c:forEach>
		            </tbody>
		            </table>
		            </td>
	            		</tr>
	            		<tr>
	            		<td align="right" class="text12">
	            		<table >
						<tr>
							<td>	
								<a href="transportDispWorkflowTripListExcelView.do" target="_new">
			                		<img valign="bottom" id="tripListExcel" src="resources/images/excel.gif" width="14" height="14" border="0" alt="excel">
			                		<font class="text12MediumBlue">&nbsp;Eksport til Excel</font>
			 	        		</a>
			 	        		&nbsp;
		 	        		</td>
		 	        		<td class="text12" width="20px">&nbsp;</td>
	 	        		</tr>
	 	        		</table>
			 		</td>
		         	</tr>
	            		</table> <%--containerdatatableTable END --%>
	            		</td>
	            </tr>
	           </c:if> 
	            
	            
	            
            	<tr height="2"><td></td></tr>
	            
	            <%-- Validation errors --%>
				<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
				<tr>
					<td>
			           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
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
				
	            <tr>
	            		<td>
	            		<c:choose>
						<c:when test="${not empty searchFilter.wssst}">	
			                <c:set var="requestParamWssst" scope="request" value="${searchFilter.wssst}"/>
		                </c:when>
		                <c:otherwise>
		                		<c:set var="requestParamWssst" scope="request" value="${model.record.tust}"/>
		                </c:otherwise>
	                </c:choose>
	                <c:choose>
						<c:when test="${not empty searchFilter.wssavd}">	
			                <c:set var="requestParamWssavd" scope="request" value="${searchFilter.wssavd}"/>
		                </c:when>
		                <c:otherwise>
		                		<c:set var="requestParamWssavd" scope="request" value="${model.record.tuavd}"/>
		                </c:otherwise>
	                </c:choose>
	            		<input style="cursor:pointer;" type="button" value="<spring:message code="systema.transportdisp.workflow.trip.form.button.createnew.trip"/>" name="cnButton" onClick="location.href = 'transportdisp_workflow.do?action=doFind&wssst=${requestParamWssst}&wssavd=${requestParamWssavd}'">
	            		</td>
	           	</tr>
	            
	            <%-- FORM HEADER --%>
		 		<tr>
	            		<td>
		        			<table width="98%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
					 		<tr height="15">
					 			<td class="text12White">
									&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.header.workWithTrip"/>&nbsp;<img style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">
				 				</td>
			 				</tr>
		 				</table>
	            		</td>
	            </tr>
	            <%-- FORM DETAIL --%>
		 		<tr id="formcontainer" ondrop="drop(event)" ondragenter="highlightDropArea(event)" ondragleave="noHighlightDropArea(event)" ondragover="allowDrop(event)" >
	            		<td>
	            			<form name="transportdispForm" id="transportdispForm" method="post">
	            			<%-- <input type="hidden" name="tuavd" id="tuavd" value='${model.record.tuavd}'> --%>
	            			<input type="hidden" name="tupro" id="tupro" value='${model.record.tupro}'>
	            			
	            			<table width="98%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
					 		<tr height="10"><td ></td></tr>
					 		<tr>
								<td colspan="2" valign="top">
									<table style="width:48%" cellspacing="1" cellpadding="0">
							 			<tr>
								 			<td class="text14" nowrap>&nbsp;&nbsp;
									 			&nbsp;&nbsp;
									 			<font class="text14"><b><spring:message code="systema.transportdisp.workflow.trip.list.search.label.dept"/></b>&nbsp;</font>
									 			<c:choose>
							 				    <c:when test="${not empty model.record.tupro}">
													<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="tuavd" id="tuavd" size="5" value='${model.record.tuavd}'>
									 			</c:when>
									 			<c:otherwise>
									 				<c:choose>
							 				    	<c:when test="${not empty model.record.tuavd}">
										 				<input type="text" class="inputTextMediumBlue" name="tuavd" id="tuavd" size="5" maxlength="4" value='${model.record.tuavd}'>
									 				</c:when>
									 				<c:otherwise>
										 				<input type="text" class="inputTextMediumBlue" name="tuavd" id="tuavd" size="5" maxlength="4" value='${searchFilter.wssavd}'>
										 				<div style="display:inline-block;" class="clazzAvdCreateNew" >
											 				<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_avd.do?action=doInit&status=a','avdWin','top=150px,left=300px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
				 												<img id="imgAvdSearchOnCreateNew" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
				 											</a>
			 											</div>
		 											</c:otherwise>
		 											</c:choose>
		 											
									 			</c:otherwise>
									 			</c:choose>
									 			&nbsp;&nbsp;&nbsp;<span title="tusg"><b><spring:message code="systema.transportdisp.workflow.trip.list.search.label.sign"/></b></span>
									 			<c:choose>
										 			<c:when test="${not empty model.record.tupro}">
											 			<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="tusg" id="tusg" size="5" value='${model.record.tusg}'>
									 				</c:when>
									 				<c:otherwise>
									 					<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="tusg" id="tusg" size="5" value='${user.signatur}'>
									 				</c:otherwise>
								 				</c:choose>
								 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="tuproJS"><b><spring:message code="systema.transportdisp.workflow.trip.list.search.label.trip"/>:</b></span>
									 			<div style="display:inline-block;" class="clazzOrderTripTab" <c:if test="${empty model.record.tupro}">style="visibility:collapse;"</c:if> >
													<a class="ordersTripOpen" style="display:block" href="transportdisp_mainorderlist.do?action=doFind&wssavd=${model.record.tuavd}&wstur=${model.record.tupro}">
										 				<label id="tuproJS" class="text14MediumBlue" style="cursor:pointer;">${model.record.tupro}</label>
										 			</a>
									 			</div>
									 			
								 			</td>
					 						<td align="right">
					 						<button name="smsButton" id="smsButton" class="buttonGrayWithGreenFrame" type="button" >Send SMS</button>
		 									<button name="budgetButton" id="budgetButton" class="buttonGrayWithGreenFrame" type="button" >Budsjett/rekv.</button>
		 									&nbsp;
						 				    <c:choose>
							 				    <c:when test="${ not empty model.record.tupro}">
							 				    		<input class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" onclick="javascript: form.action='transportdisp_workflow_edit.do?action=doUpdate';" value='<spring:message code="systema.transportdisp.submit.save"/>'/>
							 				    </c:when>
							 				    <c:otherwise>
							 				    		<input class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" onclick="javascript: form.action='transportdisp_workflow_edit.do?action=doUpdate';" value='<spring:message code="systema.transportdisp.submit.createnew"/>'/>
							 				    </c:otherwise>	
						 				    </c:choose>
						 				    </td>
								    		</tr>
							 		</table>
								</td>
							</tr>	
					 		
					 		<tr>
					 			<td valign="top" >
					 			 <table width="98%" class="tableBorderWithRoundCornersLightGray" cellspacing="1" cellpadding="0">
							 		<tr height="10"><td >&nbsp;</td></tr>
								    	<tr>
								    		<td class="text12">
								    			&nbsp;<img onMouseOver="showPop('invperiod_info');" onMouseOut="hidePop('invperiod_info');" style="vertical-align: bottom;" src="resources/images/info3.png" width="12px" height="12px" border="0" alt="info">
								    			<span title="centuryYearTurccTuraar/turmnd">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.period"/><font class="text12RedBold" >*</font>
							    				</span>
								    			<div class="text11" style="position: relative;" align="left">
						 						<span style="position:absolute; width:200px; left:0px; top:0px;" id="invperiod_info" class="popupWithInputText"  >
						 							<font class="text11">
									           			<b>Invoice Period</b>
									           			<div>
									           			<p>Always a year and month</p>
									           			</div>
								           			</font>
												</span>
											</div>
								    			
							    			</td>
								    		<td>
								    			<select class="inputTextMediumBlue11MandatoryField" name="centuryYearTurccTuraar" id="centuryYearTurccTuraar">
							            		<option value="">-select-</option>
							 				  	<c:forEach var="record" items="${model.yearList}" >
						                       	 	<option value="${record}"<c:if test="${model.record.centuryYearTurccTuraar == record}"> selected </c:if> >${record}</option>
												</c:forEach> 
											</select>	
								    		</td>
								    		<td>
								    			<select class="inputTextMediumBlue11MandatoryField" name="turmnd" id="turmnd">
							            		<option value="">-select-</option>
							 				  	<c:forEach var="record" items="${model.monthList}" >
						                       	 	<option value="${record}"<c:if test="${model.record.turmnd == record}"> selected </c:if> >${record}</option>
												</c:forEach> 
											</select>
								    		</td>
								    	</tr>
								    	<tr height="5"><td ></td></tr>
								    	<tr>
								    		<td class="text12">
								    			<span title="tubiln/tulk/tuheng/tulkh">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/lorry_green.png" height="16px" width="16px" border="0" alt="edit">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic"/><font class="text12RedBold" >*</font>
								    			</span>
							    			</td>
								    		<td class="text11" nowrap><input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="tubiln" id="tubiln" size="10" maxlength="8" value="${model.record.tubiln}">
								    		<a tabindex=0 id="tubilnIdLink" >
								    			<img id="imgTruckLicSearch" style="cursor:pointer; vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
		 									</a>
								    		
								    		</td>
								    		<td class="text11"  nowrap>
								    			<select class="inputTextMediumBlueMandatoryField" name="tulk" id="tulk">
						 						<option value="">-select-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.tulk == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="tulkIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
											</td>

								    		<td><input type="text" class="inputTextMediumBlueUPPERCASE" name="tuheng" id="tuheng" size="10" maxlength="10" value="${model.record.tuheng}"></td>
								    		<td class="text11" nowrap>
								    			<select name="tulkh" id="tulkh">
						 						<option value="">-select-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.tulkh == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
												</select>
												<a tabindex="-1" id="tulkhIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
												
							    			</td>
							    			
								    	</tr>
									    <tr>
								    		<td class="text12">
								    			<span title="tubilk">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/lorry_green.png" height="16px" width="16px" border="0" alt="edit">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.trucktype"/><font class="text12RedBold" >*</font>
							    				</span>
							    			</td>
								    		<td><input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="tubilk" id="tubilk" size="4" maxlength="3" value="${model.record.tubilk}">
								    			<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_bilcode.do?action=doInit','bilcodeWin','top=300px,left=350px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 											<img id="imgTruckTypeSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
		 										</a>
							    			</td>
							    			
							    			<td class="text12"><span title="tuopdt"><spring:message code="systema.transportdisp.workflow.trip.form.label.ordertype"/></span></td>
							    			<td class="text11" colspan="2">
								 				<select class="inputTextMediumBlue" name="tuopdt" id="tuopdt">
								            		<option value="">-select-</option>
								 				  	<c:forEach var="record" items="${model.oppdragstypeList}" >
							                       	 	<option value="${record.opdTyp}"<c:if test="${model.record.tuopdt == record.opdTyp}"> selected </c:if> >${record.opdTyp}</option>
													</c:forEach> 
												</select>	
												<%-- info span --%>
												<img id="tuopdtImg" tabindex=-1 onClick="showPop('OppdragTypeInfo');" style="cursor:pointer; vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
												<div class="text11" style="position: relative;" align="left">
						 						<span style="position:absolute; width:280px; left:0px; top:4px;" id="OppdragTypeInfo" class="popupWithInputText"  >
						 								<select class="text11" id="oppdragType" name="oppdragType" size="5" onDblClick="hidePop('OppdragTypeInfo');">
									           				<c:forEach var="record" items="${model.oppdragstypeList}" >
									 				  			<option value="${record.opdTyp}">${record.opdTyp}=${record.beskr}</option>
															</c:forEach>
									           			</select>
									           			<table width="100%" align="left" border="0">
															<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
															<tr align="left" >
																<td class="text11">&nbsp;<button name="oppdragTypeButtonClose" id="oppdragTypeButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('OppdragTypeInfo');">&nbsp;<spring:message code="systema.transportdisp.ok"/></button> 
																</td>
															</tr>
														</table>
												</span>	
												</div>
								 			</td>
								    	</tr>
								    	<tr height="10"><td ></td></tr>
								    	<tr>
								    		<td class="text12">
								    			<span title="tucon1/tulkc1/tucon2/tulkc2">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/containerYellow.png" height="16px" width="16px" border="0" alt="edit">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.container.nr"/>
								    			</span>
							    			</td>
								    		<td class="text11" nowrap><input type="text" class="inputTextMediumBlueUPPERCASE" name="tucon1" id="tucon1" size="15" maxlength="17" value="${model.record.tucon1}"></td>
								    		<td class="text11"  nowrap>
								    			<select name="tulkc1" id="tulkc1">
						 						<option value="">-select-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.tulkc1 == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="tulkc1IdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
											
								    		</td>

								    		<td><input type="text" class="inputTextMediumBlueUPPERCASE" name="tucon2" id="tucon2" size="15" maxlength="17" value="${model.record.tucon2}"></td>
								    		<td class="text11" nowrap>
								    			<select name="tulkc2" id="tulkc2">
						 						<option value="">-select-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.tulkc2 == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="tulkc2IdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
							    			</td>
								    	</tr>
								    
								    	<tr>
								    		<td class="text12">
								    			<span title="tuknt2/tunat">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/lorry_green.png" height="16px" width="16px" border="0" alt="edit">								    				
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.truckersno"/><font class="text12RedBold" >*</font>
							    				</span>
						    				</td>
								    		<td><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="tuknt2" id="tuknt2" size="9" maxlength="8" value="${model.record.tuknt2}">
								    			<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_transpcarrier.do?action=doInit','transpcarrierWin','top=300px,left=350px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 										<img id="imgTruckersNrSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
		 									</a>
								    		</td>
								    		<td colspan="4"><input readonly tabindex=-1 type="text" class="inputTextMediumBlueUPPERCASE inputTextReadOnly" name="tunat" id="tunat" size="35" maxlength="30" value="${model.record.tunat}"></td>
								    	</tr>
							    		<tr height="5"><td ></td></tr>
								    	<tr>
								    		<td class="text12">
								    			<span title="tusja1/tusjn1">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/appUserOg.gif" height="16px" width="16px" border="0" alt="edit">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.driver1"/>
								    			</span>
								    			
								    		</td>
								    		<td><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueUPPERCASE" name="tusja1" id="tusja1" size="6" maxlength="8" value="${model.record.tusja1}">
								    			<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_driver.do?action=doInit&dv=1','driverWin','top=300px,left=350px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 										<img id="imgDriver1Search" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
		 									</a>
								    		</td>
								    		<td colspan="4"><input type="text" class="inputTextMediumBlueUPPERCASE" name="tusjn1" id="tusjn1" size="35" maxlength="30" value="${model.record.tusjn1}"></td>
								    	</tr>
								    	<tr>	
								    		<td class="text12">
								    			<span title="tusja2/tusjn2">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/appUserOg.gif" height="16px" width="16px" border="0" alt="edit">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.driver2"/>
								    			</span>
								    		</td>
								    		<td><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueUPPERCASE" name="tusja2" id="tusja2" size="6" maxlength="8" value="${model.record.tusja2}">
								    			<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_driver.do?action=doInit&dv=2','driverWin','top=300px,left=350px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 										<img id="imgDriver2Search" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
		 									</a>
								    		</td>
								    		<td colspan="4"><input type="text" class="inputTextMediumBlueUPPERCASE" name="tusjn2" id="tusjn2" size="35" maxlength="30" value="${model.record.tusjn2}"></td>
								    	</tr>
								    	<tr height="5"><td ></td></tr>
								 </table>	
				 				</td>
				 				<td valign="top">
					 			 <table width="98%" class="tableBorderWithRoundCornersLightGray" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="10"><td >&nbsp;</td></tr>
								    <tr>
								    		<td class="text12" nowrap>
							    				&nbsp;<img onMouseOver="showPop('etd_info');" onMouseOut="hidePop('etd_info');" style="vertical-align: bottom;" src="resources/images/info3.png" width="12px" height="12px" border="0" alt="info">
							    				<span title="tudt/tutm"><spring:message code="systema.transportdisp.workflow.trip.form.label.date.departure"/></span>
							    				<div class="text11" style="position: relative;" align="left">
					 						<span style="position:absolute; width:200px; left:0px; top:0px;" id="etd_info" class="popupWithInputText"  >
					 							<font class="text11">
								           			<b>Date of Departture (ETD)</b>
								           			<div>
								           			<p>Always a time-stamp and the place.</p>
								           			<p>Place:
								           			<ul>
								           				<li>Country Code</li>
								           			    <li>Postal number</li>
								           			    <li>The city will be filled automatically</li>
								           			</ul>
								           			</p>	
								           			</div>
							           			</font>
											</span>
											</div>
							    			</td>
								    		<td>
							    				<c:choose>
								    				<c:when test="${not empty model.record.tudt && !fn:contains(model.record.tudt,'yyyy')}">
								    					<input type="text" class="inputTextMediumBlue" name="tudt" id="tudt" size="9" maxlength="8" value="${model.record.tudt}">
							    					</c:when>
							    					<c:otherwise>
							    						<input onfocus="if (this.value==this.defaultValue) this.value = ''" type="text" class="inputTextMediumBlue" style="color:#CCCCCC;" name="tudt" id="tudt" size="9" maxlength="8" value="">
								    				</c:otherwise>
							    				</c:choose>
								    			<img src="resources/images/calendar.gif" height="12px" width="12px" border="0" alt="date">
								    			
								    			<c:choose>
								    			<c:when test="${not empty model.record.tutm && !fn:contains(model.record.tutm,'HH')}">
								    				&nbsp;<input type="text" class="inputTextMediumBlue" name="tutm" id="tutm" size="5" maxlength="4" value="${model.record.tutm}">
								    			</c:when>
								    			<c:otherwise>
								    				&nbsp;<input onfocus="if (this.value==this.defaultValue) this.value = ''" type="text" class="inputTextMediumBlue" style="color:#CCCCCC;" name="tutm" id="tutm" size="5" maxlength="4" value="">
								    			</c:otherwise>
								    			</c:choose>
							    			</td>
								    		<td class="text12" nowrap>&nbsp;
								    			<span title="tusonf/tustef/tusdf">
								    				<img onMouseOver="showPop('etd_info');" onMouseOut="hidePop('etd_info');" style="vertical-align: bottom;" src="resources/images/addressIcon.png" width="11px" height="11px" border="0" alt="address">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.date.departure.from"/>
								    			</span>
							    			</td>
								    		<td class="text12" nowrap>
								    			<select name="tusonf" id="tusonf">
						 						<option value="">-select-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.tusonf == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="tusonfIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
											
							    			</td>
								    		<td class="text11" nowrap><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="tustef" id="tustef" size="6" maxlength="5" value="${model.record.tustef}">
								    			<a tabindex=0 id=tustefIdLink>
								    				<img id="imgFromSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
								    			</a>
								    		</td>
								    	</tr>
								    <tr>
								    		<td>&nbsp;</td>
								    		<td colspan="6"><input tabindex=-1 readonly tabindex=-1 type="text" class="inputTextReadOnlyNormal" name="tusdf" id="tusdf" size="22" maxlength="20" value="${model.record.tusdf}"></td>
								    	</tr>
								    <tr>
								    		<td class="text12" nowrap>
							    				&nbsp;<img onMouseOver="showPop('eta_info');" onMouseOut="hidePop('eta_info');" style="vertical-align: bottom;" src="resources/images/info3.png" width="12px" height="12px" border="0" alt="info">
							    				<span title="tudtt/tutmt"><spring:message code="systema.transportdisp.workflow.trip.form.label.date.arrival"/></span>
							    				<div class="text11" style="position: relative;" align="left">
					 						<span style="position:absolute; width:200px; left:0px; top:0px;" id="eta_info" class="popupWithInputText"  >
					 							<font class="text11">
								           			<b>Date of Arrival (ETA)</b>
								           			<div>
								           			<p>Always a time-stamp and the place.</p>
								           			<p>Place:
								           			<ul>
								           				<li>Country Code</li>
								           			    <li>Postal number</li>
								           			    <li>The city will be filled automatically</li>
								           			</ul>
								           			</p>	
								           			</div>
							           			</font>
											</span>
											</div>
						    				</td>
								    		<td>
								    			<c:choose>
								    				<c:when test="${not empty model.record.tudtt && !fn:contains(model.record.tudtt,'yyyy')}">
								    					<input type="text" class="inputTextMediumBlue" name="tudtt" id="tudtt" size="9" maxlength="8" value="${model.record.tudtt}">
							    					</c:when>
							    					<c:otherwise>
							    						<input onfocus="if (this.value==this.defaultValue) this.value = ''" type="text" class="inputTextMediumBlue" style="color:#CCCCCC;" name="tudtt" id="tudtt" size="9" maxlength="8" value="">
								    				</c:otherwise>
							    				</c:choose>
								    			<img src="resources/images/calendar.gif" height="12px" width="12px" border="0" alt="date">
								    			
								    			<c:choose>
								    			<c:when test="${not empty model.record.tutmt && !fn:contains(model.record.tutmt,'HH')}">
								    				&nbsp;<input type="text" class="inputTextMediumBlue" name="tutmt" id="tutmt" size="5" maxlength="4" value="${model.record.tutmt}">
								    			</c:when>
								    			<c:otherwise>
								    				&nbsp;<input onfocus="if (this.value==this.defaultValue) this.value = ''" type="text" class="inputTextMediumBlue" style="color:#CCCCCC;" name="tutmt" id="tutmt" size="5" maxlength="4" value="">
								    			</c:otherwise>
								    			</c:choose>
								    		</td>
								    		<td class="text12" nowrap>&nbsp;
								    			<span title="tusont/tustet/tusdt">
								    				<img onMouseOver="showPop('eta_info');" onMouseOut="hidePop('eta_info');" style="vertical-align: bottom;" src="resources/images/addressIcon.png" width="11px" height="11px" border="0" alt="address">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.date.arrival.to"/>
								    			</span>
							    			</td>
								    		<td class="text11" nowrap>
								    			<select name="tusont" id="tusont">
						 						<option value="">-select-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.tusont == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="tusontIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
							    			</td>
								    		<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="tustet" id="tustet" size="6" maxlength="5" value="${model.record.tustet}">
								    			<a tabindex=0 id="tustetIdLink" >
		 											<img id="imgToSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
		 										</a>
								    		</td>
								    	</tr>
								    	<tr>
								    		<td>&nbsp;</td>
								    		<td colspan="6"><input tabindex=-1 readonly tabindex=-1 type="text" class="inputTextReadOnlyNormal" name="tusdt" id="tusdt" size="22" maxlength="20" value="${model.record.tusdt}"></td>
								    	</tr>
								    	<tr height="5"><td ></td></tr>
								    	<tr>
								    		<td class="text12" nowrap>
								    			&nbsp;<img onMouseOver="showPop('agreedPrice_info');" onMouseOut="hidePop('agreedPrice_info');" style="vertical-align: bottom;" src="resources/images/info3.png" width="12px" height="12px" border="0" alt="info">
								    			<span title="tutval/tutbel/tutako"><spring:message code="systema.transportdisp.workflow.trip.form.label.price.agreed"/></span>
								    			<div class="text11" style="position: relative;" align="left">
					 						<span style="position:absolute; width:200px; left:0px; top:0px;" id="agreedPrice_info" class="popupWithInputText"  >
					 							<font class="text11">
								           			<b>Agreed/Estim. Price</b>
								           			<div>
								           			<ul>
								           				<li>Currency</li>
								           			    <li>Amount</li>
								           			    <li>A=Agreed, E=Estimated</li>
								           			</ul>
								           			</p>	
								           			</div>
							           			</font>
											</span>
											</div>
							    			</td>
								    		<td colspan="2">
								    			<select class="text11" id="tutval" name="tutval">
							    				<option value="">-select-</option>
						           				<c:forEach var="currency" items="${model.currencyCodeList}" >
						 				  			<option value="${currency}"<c:if test="${model.record.tutval == currency}"> selected </c:if> >${currency}</option>
												</c:forEach>
						           			</select>
								    			&nbsp;<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="tutbel" id="tutbel" size="8" maxlength="8" value="${model.record.tutbel}">
								    			&nbsp;
								    			<select name="tutako" id="tutako">
				            						<option value="">-select-</option>
				 				  				<option value="A"<c:if test="${model.record.tutako == 'A'}"> selected </c:if> >A</option>
				 				  				<option value="E"<c:if test="${model.record.tutako == 'E'}"> selected </c:if> >E</option>
											</select>
								    		</td>
								    	</tr>
								    	<tr height="5"><td ></td></tr>
								 </table>	
				 				</td>
			 				</tr>
			 				<%--
			 				<tr>
			 					<td>&nbsp;</td>
			 					<td>
			 						<table style="width: 90%;">
			 						<tr>
				 						<td align="right">
					 				    <c:choose>
						 				    <c:when test="${ not empty model.record.tupro}">
						 				    		<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" onclick="javascript: form.action='transportdisp_workflow_edit.do?action=doUpdate';" value='<spring:message code="systema.transportdisp.submit.save"/>'/>
						 				    </c:when>
						 				    <c:otherwise>
						 				    		<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" onclick="javascript: form.action='transportdisp_workflow_edit.do?action=doUpdate';" value='<spring:message code="systema.transportdisp.submit.createnew"/>'/>
						 				    </c:otherwise>	
					 				    </c:choose>
					 				    </td>
				 				    </tr>
				 				    </table>
			 				    </td>
			 				</tr>
			 				 --%>
				 			<tr height="10"><td></td> </tr>
			 				<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text"></td></tr>
				 			<tr height="10"><td></td> </tr>
				 			<tr>
						 		<td valign="top" width="50%">	
						 		<table width="100" border="0" cellspacing="1" cellpadding="0">		
							 		<tr>
							 			<td class="text12">
							 				<img onMouseOver="showPop('messageNote_info');" onMouseOut="hidePop('messageNote_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="messageNote"><spring:message code="systema.transportdisp.workflow.trip.form.label.messageNotes"/></span>
							 				<div class="text11" style="position: relative;" align="left">
					 						<span style="position:absolute; left:0px; top:0px;" id="messageNote_info" class="popupWithInputText"  >
					 							<font class="text11">
								           			<b>Message/Notes</b>
								           			<div>
								           			<p>The message will be printed as shown in screen.</p>
								           			<ul>
								           				<li>Max.character per line: 70-characters</li>
								           			    <li>Max.number of lines: 12</li>
								           			</ul>	
								           			</div>
							           			</font>
											</span>
											</div>
							 			</td>
							 		</tr>
							 		<tr>	
								 		<td colspan="20">
								 			<textarea class="text11UPPERCASE" id="messageNote" name="messageNote" limit='70,12' cols="75" rows="8">${model.record.messageNote}</textarea>
								 		</td>
							 		</tr>
							 		<tr height="10"><td></td></tr>
							 		<tr>
						 				<td colspan="20" class="text12">
						 					<table class="tableBorderWithRoundCorners" width="470px" >
												<tr>
										 			<td valign="top" class="text12">
									 					<spring:message code="systema.transportdisp.workflow.trip.form.label.uploadedDocs"/>&nbsp;
									 					<div id="resultUploadedDocs">
									 					<%--
									 					<ul>
									 					<c:forEach items="${model.record.archivedDocsRecord}" var="record" varStatus="counter">
									 						<li>
									 						<a target="_blank" href="transportdisp_workflow_renderArchivedDocs.do?doclnk=${record.doclnk}">
					    		    							<img title="Archive" style="vertical-align:middle;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="PDF arch.">
					    		    							${record.doctxt}
							   								</a>&nbsp;&nbsp;&nbsp;
							   								</li>
									 					</c:forEach>
									 					</ul>
									 					 --%>
									 					</div>
									 					
									 				</td>
												</tr>
											</table>
										 	
					 						<%-- this piece below is currently never used since we construct the string in jquery because of AJAX call in this. Has been used before and could be activated
					 						<c:if test="${not empty model.record.getdoctrip}">
					 							<ul>
						 						<c:forEach var="record" items="${model.record.getdoctrip}">
						 							<li>
									                <img src="resources/images/jpg.png" border="0" width="16px" height="16px">
									                <a target="_blank" href="transportdisp_workflow_renderArchivedDocs.do?doclnk=${record.doclnk}" >${record.doctxt}</a> 
									                &nbsp;
									                </li>
									            </c:forEach>
									            </ul>
								            </c:if>
								            --%>
						 					
						 				</td>
						 			</tr>
								</table>
								</td>

				 				<td valign="top" width="50%">
					 			<table width="98%" class="tableBorderWithRoundCornersDarkGray" cellspacing="1" cellpadding="0" border="0">
							 		<tr>
									    <td valign="middle">
										    <table>
										    <tr class="tableRow">
										    		<%--
										    		<input type="hidden" name="own_tuhoyb" id="own_tuhoyb" value=""/>
										    		<input type="hidden" name="own_tuhoyh" id="own_tuhoyh" value=""/>
										    		<input type="hidden" name="own_tukvkt" id="own_tukvkt" value=""/>
										    		<input type="hidden" name="own_tutara" id="own_tutara" value=""/>
										    		<input type="hidden" name="own_tukam3" id="own_tukam3" value=""/>
										    		<input type="hidden" name="own_tukalM" id="own_tukalM" value=""/>
										    		 --%>
										    		 
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('tuhoyb_info');" onMouseOut="hidePop('tuhoyb_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="tuhoyb/Utilised"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.height.truck"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="tuhoyb_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Høyde bil</b>
										           			<div>
										           			<p>Hentes fra bilregister til info for senere lasteplanlegging
										           			</p>
										           			
										           			</div>
									           			</font>
													</span>
													</div>
										    		</td>
												<td class="tableHeaderField" align="right"><label name="tuhoyb" id="tuhoyb">&nbsp;${model.record.tuhoyb}</label></td>			
										    		<td class="tableHeaderField" align="right">&nbsp;</td>										    
								    			</tr>
										    	<tr class="tableRow">	
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('tuhoyh_info');" onMouseOut="hidePop('tuhoyh_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="tuhoyh(Capacity)"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.height.hangs"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="tuhoyh_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Høyde henger</b>
										           			<div>
										           			<p>Dersom hengeren er en egen "unit" i unitregisteret hentes høyde derfra. Til info for senere lasteplanlegging
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    			
										    			
										    		</td>
										    		<td class="tableHeaderField" align="right"><label name="tuhoyh" id="tuhoyh">&nbsp;${model.record.tuhoyh}</label></td>
										    		<td class="tableHeaderField" align="right">&nbsp;</td>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('tukvkt_info');" onMouseOut="hidePop('tukvkt_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="tukvkt/tutvkt"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.capacity"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="tukvkt_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Lasteevne(Capacity)</b>
										           			<div>
										           			<p>Samlet lastekapasitet for bil og henger i kilo last.
															  2. kolonne - hittil oppfylt (sum vekt av oppdrag så langt)
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
									    			</td>
										    		<td class="tableHeaderField" align="right"><label name="tukvkt" id="tukvkt">&nbsp;${model.record.tukvkt}</label></td>
										    		<td class="tableHeaderField" align="right"><label name="tutvkt" id="tutvkt">&nbsp;${model.record.tutvkt}</label></td>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('tutara_info');" onMouseOut="hidePop('tutara_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="tutara"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.tara"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="tutara_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Tara</b>
										           			<div>
										           			<p>Samlet egenvekt bil og henger.
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    		</td>	
										    		<td class="tableHeaderField" align="right"><label name="tutara" id="tutara">&nbsp;${model.record.tutara}</label></td>
										    		<td class="tableHeaderField" align="right">&nbsp;</td>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="tableHeaderFieldFirst">
										    			<img onMouseOver="showPop('wstov1_info');" onMouseOut="hidePop('wstov1_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="wstov1/wstov2"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.total.weight"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="wstov1_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Totalvekt</b>
										           			<div>
										           			<p>Samlet vekt inkl. last for bil og henger. 2. kolonne - hittil oppfylt (bil/henger+sum vekt av oppdrag så langt)
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
									    			</td>
									    			<c:set var="tmptukvkt" value="${fn:replace(model.record.tukvkt,',','.')}" />
									    			<c:set var="tmptutara" value="${fn:replace(model.record.tutara,',','.')}" />
									    			<c:choose>
										    			<c:when test="${tmptukvkt!='' || tmptutara!=''}">
										    				<fmt:parseNumber var="dtmptukvkt" type="number" value="${tmptukvkt}" />
										    				<fmt:parseNumber var="dtmptutara" type="number" value="${tmptutara}" />
										    				<td class="tableHeaderField" align="right"><label name="wstov1" id="wstov1">&nbsp;<c:out value="${dtmptukvkt + dtmptutara}"/></label></td>
											    			<td class="tableHeaderField" align="right"><label name="wstov2" id="wstov2">&nbsp;</label></td>
										    			</c:when>
										    			<c:otherwise>
										    				<td class="tableHeaderField" align="right"><label name="wstov1" id="wstov1">&nbsp;</label></td>
											    			<td class="tableHeaderField" align="right"><label name="wstov2" id="wstov2">&nbsp;</label></td>
										    			</c:otherwise>
									    			</c:choose>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('tukam3_info');" onMouseOut="hidePop('tukam3_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="tukam3/tutm3"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.total.m3"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="tukam3_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Total M3</b>
										           			<div>
										           			<p>Samlet kubik-kapasitet for bil og henger. 2. kolonne - hittil oppfylt (sum av oppdrag så langt hvor kubikk er fylt ut)
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    			
									    			</td>
										    		<td class="tableHeaderField" align="right"><label name="tukam3" id="tukam3">&nbsp;${model.record.tukam3}</label></td>
										    		<td class="tableHeaderField" align="right"><label name="tutm3" id="tutm3">&nbsp;${model.record.tutm3}</label></td>
										    	</tr>
										    	<tr class="tableRow">
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('tukalM_info');" onMouseOut="hidePop('tukalM_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="tukalM/tutlm"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.total.lm"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="tukalM_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Total LM</b>
										           			<div>
										           			<p>Samlet lastemeter-kapasitet for bil og henger. oppfylt (sum av oppdrag så langt hvor lastemeter er fylt ut)
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    			
										    		</td>
										    		<td class="tableHeaderField" align="right"><label name="tukalM" id="tukalM">&nbsp;${model.record.tukalM}</label></td>
										    		<td class="tableHeaderField" align="right"><label name="tutlm" id="tutlm">&nbsp;${model.record.tutlm}</label></td>
										    	</tr>
									    		<tr class="tableRow">
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('simlm_info');" onMouseOut="hidePop('simlm_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="simlm/simm3"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.total.simLMM3"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="simlm_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Simulert LM/M3</b>
										           			<div>
										           			<p>todo</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    			
										    			
										    		</td>
										    		<td class="tableHeaderField" align="right"><label name="simlm" id="simlm">&nbsp;${model.record.simlm}</label></td>
										    		<td class="tableHeaderField" align="right"><label name="simm3" id="simm3">&nbsp;${model.record.simm3}</label></td>
										    	</tr>
											</table>
										</td>
									    <td valign="middle">
										    <table class="tableNoBorderWithRoundCorners" >
										    <tr>
										    		<td class="text12" title="tuao/tuts"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.ordersColli"/></td>
										    		<td colspan="2" class="text12" title="berbud"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.estimatedTransportCost"/></td>
										    		<td class="text12"><input readonly tabindex=-1 type="text" class="inputTextMediumBlueReadOnlyMateBg" style="text-align:right;" name="berbud" id="berbud" size="8" value="${model.record.berbud}"></td>
										    	</tr>
										    	<tr>
										    		<td class="text12" nowrap>
										    			<input readonly tabindex=-1 type="text" class="inputTextMediumBlueReadOnlyMateBg" style="text-align:center;" name="tuao" id="tuao" size="6" value="${model.record.tuao}">
										    			<b>/</b>
										    			<input readonly tabindex=-1 type="text" class="inputTextMediumBlueReadOnlyMateBg" style="text-align:center;" name="tuts" id="tuts" size="6" value="${model.record.tuts}">
										    		</td>
										    		<td class="tableHeaderFieldFirst11" align="center"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.header.open"/></td>
										    		<td class="tableHeaderField11" align="center"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.header.finished"/></td>
										    		<td class="tableHeaderField11" align="right"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.header.sum"/>&nbsp;</td>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="text12" >
										    			<img onMouseOver="showPop('totiaa_info');" onMouseOut="hidePop('totiaa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 				<span title="totiaa/totioa/totisa"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.inntekt.avrgrl"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:350px;" id="totiaa_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Inntekt avregning grl.- og Inntekt øvrige</b>
										           			<div>
										           			<p>Basert på det som ligger som FAKTURALINJER på alle oppdragene summeres inntektene. 
										           			De grupperes som "FERDIGE" dersom statuskoden er "F" (Fakturert) eller høyere.
										           			</p>
										           			<p> 
										           			Lavere status akkumuleres under åpne. Inntektene klassifiseres som "avregningsgrunnlag" dersom gebyrkoden
															har kode "T" i gebyrkoderegisteret. Ellers i "øvrige".
															</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    			
										    		</td>
									    			<td class="tableCellFirst12" align="right"><label name="totiaa" id="totiaa">${model.record.totiaa}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totioa" id="totioa">${model.record.totioa}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totisa" id="totisa">${model.record.totisa}&nbsp;</label></td>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="text12" >
										    			<img onMouseOver="showPop('totiaa_info');" onMouseOut="hidePop('totiaa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 				<span title="totiag/totiog/totisg"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.inntekt.ovriga"/></span>
										    		</td>
									    			<td class="tableCellFirst12" align="right"><label name="totiag" id="totiag">${model.record.totiag}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totiog" id="totiog">${model.record.totiog}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totisg" id="totisg">${model.record.totisg}&nbsp;</label></td>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="text12">
										    			<img onMouseOver="showPop('totkaa_info');" onMouseOut="hidePop('totkaa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 				<span title="totkaa/totkoa/totksa"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.kostnad.avrtrans"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:0px; top:0px; width:250px;" id="totkaa_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Kostn. avregning/tran</b>
										           			<div>
										           			<p>Når tur ER avregnet/inng faktura kontert (RØD TEKST OPPE TIL HØYRE):Plasseres beløp i kolonne "FERDIGE". ....Ved transportører som avregnes (kode 0 bak transportørs navn), hentes
																beløp rett fra avregnings-filer. Ved transportører som sender faktura (kode 2) hentes evt. beløp fra
																turbildets <b>Pris transp</b>.
										           			</p>
										           			<p>Når tur IKKE er avregnet/kontert: Når <b>Pris transp</b> er utfylt, legges dette inn i kolonne <b>ÅPNE</b>.</p>
										           			<p>Er det IKKE utfylt må en selv estimere</p>
										           			</div>
									           			</font>
													</span>
													</div>
									    			</td>
									    			<td class="tableCellFirst12" align="right"><label name="totkaa" id="totkaa">${model.record.totkaa}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totkoa" id="totkoa">${model.record.totkoa}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totksa" id="totksa">${model.record.totksa}&nbsp;</label></td>

										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="text12">
										    			<img onMouseOver="showPop('totkao_info');" onMouseOut="hidePop('totkao_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 				<span title="totkao/totkoo/totkso"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.kostnad.ovriga"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:0px; top:0px; width:500px;" id="totkao_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Kostnad øvrige</b>
										           			<div>
										           			<p>Fra fakturalinjene akkumuleres <b>Kostnad øvrige</b> / <b>FERDIGE</b>.(Alle linjer med opprinnelseskode <b>K</b>=Kostnad, UNNTATT de som har inneholder "AVREGNING HOVEDTRANS" eller "*T*" i fakturatekst.<br>
										           			 (Disse takles under "Kostn. avr/tran")).Fra registeret "Foventede kostnader/Rekvisisjoner" (F7 i turbildet) akkumuleres til kolonnen "ÅPNE" (Men linjen som evt kommer fra "Pris transp" hoppes over, da denne alt er tatt med).
										           			</p>
										           			<p>
										           			OBS! FORVENTEDE KOSTNADER SOM ER PLUKKET TIL KOSTNADSBILAG
															(=HENFØRT, OG DERMED UTE AV LISTA OVER ÅPNE FORVENTEDE KOSTNADER),
															MEN ENNÅ IKKE OVERFØRT TIL ØKONOMI (=KOMMET INN SOM "FAKT.LINJE" PÅ
															OPPDRAGENE, OG MEDTATT UNDER "FERDIG") "FALLER MELLOM 2 STOLER".
															DISSE FORVENTEDE KOSTNADENE VIL VÆRE "SKJULT" INNTIL DE OVERFØRES.
															</p>
															<p>
															FOR AT DETTE IKKE SKAL GJELDE EN SÅ VIKTIG KOMPONENT SOM "PRIS TRANSPORTØR" (NÅR TRANSPORTØREN SENDER REGNING) ER DENNE HÅNDTERT SOM BESKREVET OVER (PLUKKET RETT FRA TURBILDET).
															DETTE INNEBÆRER EN VISS RISIKO. (DEN SOM HAR KONTERT KAN!!! HA ENDRET BELØP I FØRINGSØYEBLIKKET PÅ MÅTER SOM IKKE SYSTEMET HAR FANGET OPP.<br>
															MEN!!! SLIKE EVT ENDRINGER VIL STATISTIKK / OG ANALYSEPROGRAMMER FANGE)
										           			</p>
										           			<p>
										           			OBS 2 !!! MIDLERTIDIG!!!!
															Versjon 5 av SYSPED merket IKKE spesielt ut (med *T* som i versj 6) "fakturalinjer" skapt basert på føring av inngående transportør-faktura (=basert på plukket budsjett-post med "Pris transp").
															DET BETYR AT NÅR EN SER PÅ GAMLE TURER (der kontering/plukking av budsjettpost er skjedd under versjon 5) VIL DENNE KOSTNADEN SYNS DOBBELT OPP!!! (Igjen, her må en se på turanalyse, meny Cost pkt 18 for å få et rett bilde).
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    			
									    			</td>
									    			<td class="tableCellFirst12" align="right"><label name="totkao" id="totkao">${model.record.totkao}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totkoo" id="totkoo">${model.record.totkoo}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totkso" id="totkso">${model.record.totkso}&nbsp;</label></td>
										    	</tr>
										    	<tr class="tableRow">
										    		<td class="text12Bold" title="totopn/totovf/totsum"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.sum.resultat"/></td>
									    			<td class="tableCellFirst12" align="right"><label name="totopn" id="totopn">${model.record.totopn}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totovf" id="totovf">${model.record.totovf}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totsum" id="totsum">${model.record.totsum}&nbsp;</label></td>
										    	</tr>
											</table>
										</td>
									</tr>
									
								</table>
								</td>
							</tr>
					    		<tr height="2"><td >&nbsp;</td></tr>
		 				</table>
		 				</form>
	            		</td>
	            </tr> 
            
            
            <tr height="5"><td>&nbsp;</td></tr>
			</table> <%--wrapperTable END --%>
         </td>
         </tr>
         <tr height="10"><td>&nbsp;</td></tr>
         
        <%-- ---------------- --%>
		<%-- DIALOG SMS		  --%>
		<%-- ---------------- --%>
		<tr>
		<td>
			<div id="dialogSMS" title="Dialog">
				 	<table>
						<tr>
							<td class="text12" align="left" >Send SMS med lenke til TKeventGrabber</td>
   						</tr>
   						<tr height="8"><td></td></tr>
						<tr>
							<td class="text12" align="left" >
								<b>SMS-nummer</b>&nbsp;<input type="text" class="inputText" onKeyPress="return numberKey(event)" id="smsnr" name="smsnr" size="20" maxlength="15" value=''>
							</td>
   						</tr>

						<tr height="12"><td></td></tr>
						<tr>
							<td class="text12MediumBlue" align="left">
								Send status:&nbsp;<label id="smsStatus"></label>
							</td>
						</tr>
						
					</table>
			</div>
		</td>
		</tr>
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

