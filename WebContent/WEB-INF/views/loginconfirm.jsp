<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ================== special login header ==================-->
<jsp:include page="/WEB-INF/views/headerLogin.jsp" />
<!-- =====================end header ==========================-->
<SCRIPT type="text/javascript" src="resources/js/login.js?ver=<%= new java.util.Date().getTime()/1000 %>"></SCRIPT>	
 
 
 
	<div style="height: 100px;">
	 	<h3 class="text18">&nbsp;</h3>
	 	<%-- <h4></h1><a href="?lang=en">en</a>|<a href="?lang=no">no</a></h4> --%>
	</div>	
	<table align="center" width="500px" border="0" cellpadding="0">
		<tr>
		<%-- <td class="loginFrame" width="100%"> --%>
		<td class="loginFrameE2" width="100%">
			
	 		<table align="center" border="0" cellpadding="1" cellspacing="1" >
	 			<form name="loginConfirmForm" id="loginConfirmForm" >
		 			<input type="hidden" name="user" id="user" value="${model.user.user}" />
		 			<tr height="1"><td>&nbsp;</td></tr>
					<tr>
						<td align="center" colspan="3" class="text28Bold">
						<span id="qrcode" name="qrcode" >e</span>Spedsg
						</td>
					</tr>
					<tr height="3"><td>&nbsp;</td></tr>
					<tr>
						<td align="right" class="text18">Code&nbsp;</td>
						<td ><input type="text" class="inputText16" name="code" id="code" size="10" maxlength="8" /></td>
						<td align="right" >
							<input size="8" onclick="submitLoginConfirmForm();" type="btnConfirm" name="btnConfirm" id="btnConfirm" class="inputFormLoginSubmitGreen" value='Confirm' >
													
						</td>
					</tr>
				</form>
				<tr height="1"><td>&nbsp;</td></tr>
			</table>
		</td>
		</tr>
		
	</table>
	
	<div id='somediv'></div>
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->
	