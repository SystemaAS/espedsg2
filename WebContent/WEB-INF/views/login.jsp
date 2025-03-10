<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ================== special login header ==================-->
<jsp:include page="/WEB-INF/views/headerLogin.jsp" />
<!-- =====================end header ==========================-->
<SCRIPT type="text/javascript" src="resources/js/login.js?ver=<%= new java.util.Date().getTime()/1000 %>"></SCRIPT>	
 <%-- reCaptcha test keys from google
 	Site key: 6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI
    Secret key: 6LeIxAcTAAAAAGG-vFI1TnRWxMZNFuojJ4WifJWe --%>
 <c:if test="${not empty user.recaptchaSiteKey}">   
 	<script src="//www.google.com/recaptcha/api.js?hl=EN" async defer></script>
 </c:if>
 
 
	<div style="height: 100px;">
	 	<h3 class="text18">&nbsp;</h3>
	 	<%-- <h4></h1><a href="?lang=en">en</a>|<a href="?lang=no">no</a></h4> --%>
	</div>	
	<table align="center" width="500px" border="0" cellpadding="0">
		<tr>
		<%-- <td class="loginFrame" width="100%"> --%>
		<td class="loginFrameE2" width="100%">
			
	 		<table align="center" border="0" cellpadding="1" cellspacing="1" >
	 			<form name="loginForm" id="loginForm" method="POST" >
	 			<input type="hidden" name="p" id="p" value="${model.user.tomcatPort}" />
	 			<input type="hidden" name="host" id="host" value="${model.user.servletHostWithoutHttpPrefix}" />
	 			<input type="hidden" name="saas_2fa" id="saas_2fa" value="${model.user.key2FA}" />
	 			<tr height="1"><td>&nbsp;</td></tr>
	 			<c:choose>
	 				<%-- cust.toten.as --%>
		 			<c:when test="${model.user.servletHostWithoutHttpPrefix == 'cust.toten.as' || model.user.servletHostWithoutHttpPrefix == 'cust.multisped.no' }">
		 				<tr>
							<td align="left" colspan="2" class="text16">
								<a title="eService" id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="https://booking.toten-transport.no/totprod_a4web/ui/eservice">
									Ny eService ved booking av transport fra og med 02/01-2025
								</a>
			 				</td>
		 				</tr>
		 				<tr>
							<td title="Brukerveiledning - eService" align="left" colspan="2" class="text12">
								<c:if test="${model.user.tomcatPort != '8444'}">
									<a tabindex=-1 href="renderLocalPdfTotenLogin.do?fn=Brukerveiledning_eService_NO.pdf" target="_blank">
										<font class="text14" style="color:black;">
										Brukerveiledning eService&nbsp;
										</font>
										<img style="vertical-align:top;" width="18px" height="18px" src="resources/images/pdf2.png" border="0" alt="pdf">
									</a>
								</c:if>
								<c:if test="${model.user.tomcatPort == '8444'}">
									<a tabindex=-1 href="renderLocalPdfTotenLogin.do?fn=Brukerveiledning_eService_MS_NO.pdf" target="_blank">
										<font class="text14" style="color:black;">
										Brukerveiledning eService&nbsp;
										</font>
										<img style="vertical-align:top;" width="18px" height="18px" src="resources/images/pdf2.png" border="0" alt="pdf">
									</a>
								</c:if>
			 				</td>
		 				</tr>
		 				<tr height="1"><td>&nbsp;</td></tr>
		 			</c:when>
		 			<c:otherwise>
		 				<%--nothing --%>
		 			</c:otherwise>
	 			</c:choose>
				<tr>
					<td align="center" colspan="2" class="text28Bold">eSpedsg</td>
				</tr>
				<tr height="3"><td>&nbsp;</td></tr>
				<tr>
					<td align="right" class="text18"><spring:message code="login.user.label.name"/>&nbsp;</td>
					<td ><input type="text" class="inputText16" name="user" id="user" size="18" /></td>
				</tr>
				<tr>
					<td align="right" class="text18"><spring:message code="login.user.label.password"/>&nbsp;</td>
					<td>
						<input type="password"  class="inputText16" name="password" id="password" size="18"/>
						<i id="passStatus" class="fa fa-eye" aria-hidden="true" onClick="viewPassword()"></i>
					</td>
				</tr>
				
				<c:choose>
					<%-- put recaptchaSiteKey empty (application.properties) if you want to disable recaptcha-plugin  --%>
					<c:when test="${not empty user.recaptchaSiteKey}"> 
						<tr>
						<td colspan="2">
						<div class="g-recaptcha" data-sitekey="${user.recaptchaSiteKey}"></div>
						</td>
						</tr>
						 
						<tr>
							<td>&nbsp;<input type="text" name="alwaysEmptyAndInvisible" id="alwaysEmptyAndInvisible" style="display: none;"></td>
							<td align="right">
							<input onclick="checkRecaptcha();" type="submit" name="submit" id="submit" class="inputFormLoginSubmitGreen" value='<spring:message code="login.user.submit"/>' ></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td>&nbsp;<input type="text" name="alwaysEmptyAndInvisible" id="alwaysEmptyAndInvisible" style="display: none;"></td>
							<td align="right">
								<input onclick="submitLoginForm();" type="submit" name="submit" id="submit" class="inputFormLoginSubmitGreen" value='<spring:message code="login.user.submit"/>' >
							</td>
						</tr> 
					</c:otherwise>
				</c:choose>
				
				</form>
				<tr height="1"><td>&nbsp;</td></tr>

			</table>
			
		</td>
		</tr>
		<%-- Validation Error section --%>
		<tr>
			<td colspan="5" id="backendError">
			<table>
				<spring:hasBindErrors name="user"> <%-- name must equal the command object name in the Controller --%>
					<tr>
					<td colspan="5" class="textError">					
			            <ul>
			            <c:forEach var="error" items="${errors.allErrors}">
			                <li >
			                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
			                </li>
			            </c:forEach>
			            </ul>
					</td>
					</tr>
				</spring:hasBindErrors>
				<c:if test="${not empty model.errorMessage}">
					<tr>
					<td colspan="5" class="textError">					
			            <ul>
			            	  <li>${model.errorMessage}</li>
			              <%--<li>...</li> --%>
			            </ul>
			        </td>
			        </tr>
				</c:if>
			</table>
			</td>
		</tr>
	</table>
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->
	