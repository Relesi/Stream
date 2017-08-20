<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="gcom.util.ConstantesSistema"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarHidrometroDiametroActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--


	function validarForm(formulario) {
		if (validateAtualizarHidrometroDiametroActionForm(formulario)) {
			submeterFormPadrao(formulario);
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro,
			tipoConsulta) {
		var form = document.AtualizarHidrometroDiametroActionForm;
		form.idDebito.value = codigoRegistro;
		form.descricaoDebito.value = descricaoRegistro;
		form.descricaoDebito.style.color = "#000000";
	}

	function limparDebitoTipo() {
		var form = document.AtualizarHidrometroDiametroActionForm;
		form.idDebito.value = "";
		form.descricaoDebito.value = "";
	}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarHidrometroDiametroAction.do" method="post">

	<INPUT TYPE="hidden" name="removerHidrometroDiametro">
	<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="130" valign="top" class="leftcoltext">
			<div align="center">
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/informacoes_usuario.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/mensagens.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			</div>
			</td>

			<td width="625" valign="top" class="centercoltext">

			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Atualizar Di&acirc;metro do Hidr&ocirc;metro</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar um di&acirc;metro do hidr&ocirc;metro, informe os
					dados abaixo:</td>
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:hidden property="id" /> <bean:write
						name="AtualizarHidrometroDiametroActionForm" property="id" /></td>
				</tr>

				<tr>
					<td><strong>Descri&ccedil;&atilde;o: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="30" maxlength="30" /> </span></td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoAbreviada" size="5" maxlength="5" /> </span></td>
				</tr>
				<tr>
					<td><strong>N&uacute;mero da Ordem: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="numeroOrdem" size="5" maxlength="5" onkeyup="somente_numero(this)" /> </span></td>
				</tr>
				
					<tr>
					<td width="30%"><strong>Valor a ser cobrado por depreciação: </strong></td>
					<td><strong> <html:text property="valorCobradoDepreciacao" size="6"
						style="text-align: right;"
						maxlength="6" tabindex="9" 
						onkeypress="javascript:return isCampoNumerico(event);"
						onkeyup="formataValorMonetario(this,6);"/> </strong></td>
				</tr>
				
				<tr>
					<td>
						<strong>Tipo do D&eacute;bito:</strong>
					</td>
					
					<td colspan="3">
						
						<html:text property="idDebito" 
							size="4" 
							maxlength="4"
							tabindex="2"
							onkeypress="javascript:return isCampoNumerico(event);"
							onkeydown="validaEnter(event, 'exibirAtualizarHidrometroDiametroAction.do?idRegistroAtualizacao=${request.idRegistroAtualizacao}&pesquisarDebitoTipo=sim', 'idDebito');document.getElementById('descricaoDebito').value = '';"
							styleId="idTipoDebito" />
						
						<a href="javascript:abrirPopup('exibirPesquisarTipoDebitoAction.do', 'tipoDebito', null, null, 400, 800, '',document.getElementById('idTipoDebito'));">
							<img width="23" 
								height="21"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								alt="Pesquisar" 
								border="0" /></a>
						
						
						<logic:present name="debitoTipoEncontrado" scope="session">
							<html:text property="descricaoDebito" 
								size="30" 
								maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								styleId="descricaoTipoDebito" />
						</logic:present> 
						
						<logic:notPresent name="debitoTipoEncontrado" scope="session">
							<html:text property="descricaoDebito" 
								size="30" 
								maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								styleId="descricaoTipoDebito" />
						</logic:notPresent> 
												
						<a href="javascript:limparDebitoTipo();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" />
						</a>

					</td>
				</tr>

				<tr>
					<td><strong>Indicador de uso: <font	color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorUso" value="1" tabindex="5" /><strong>Ativo
					<html:radio property="indicadorUso" value="2" tabindex="6" />Inativo</strong>
					</td>
				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
					
				</tr>

				<tr>
					<td width="40%" align="left"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><input type="button"
						onClick="javascript:validarForm(document.forms[0]);"
						name="botaoAtualizar" class="bottonRightCol" value="Atualizar"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

