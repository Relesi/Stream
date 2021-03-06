<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirCobrancaSituacaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		if (validateInserirCobrancaSituacaoActionForm(form)){
				submeterFormPadrao(form);
		}	
	}
	

</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">

<html:form action="/inserirCobrancaSituacaoAction.do"
	name="InserirCobrancaSituacaoActionForm"
	type="gcom.gui.cobranca.InserirCobrancaSituacaoActionForm"
	method="post"
	onsubmit="return validateInserirCobrancaSituacaoActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


	<table width="770" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="150" valign="top" class="leftcoltext">
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

			<!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Situa��o de Cobran�a</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a P�gina��o da Tela de Processo-->

			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para adicionar uma situa��o de cobran�a, informe o dado abaixo:</td>
				</tr>
				
				<tr>
					<td width="230"><strong>Descri��o: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricao" maxlength="50"
						size="51" />
						<br>
					</td>
				</tr>
				
				<tr>
					<td><strong>Motivo de Revis�o da Conta:</strong></td>
					<td><html:select property="contaMotivoRevisao">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoContaMotivoRevisao"
									  labelProperty="descricaoMotivoRevisaoConta" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Profiss�o:</strong></td>
					<td><html:select property="profissao">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoProfissao"
									  labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Ramo Atividade:</strong></td>
					<td><html:select property="ramoAtividade">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoRamoAtividade"
									  labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Exig�ncia de Advogado:</strong></td>
					<td><html:radio property="indicadorExigenciaAdvogado" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorExigenciaAdvogado"  value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Bloqueio de Parcelamento:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorBloqueioParcelamento" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorBloqueioParcelamento" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Bloqueio de Inclus�o de Im�vel na Situa��o: <font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorBloqueioRetirada"  value="1" /><strong>Sim</strong>
					<html:radio property="indicadorBloqueioRetirada" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Bloqueio de Retirada de Im�vel da Situa��o:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorBloqueioInclusao"  value="1" /><strong>Sim</strong>
					<html:radio property="indicadorBloqueioInclusao"  value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Sele��o da Situa��o Apenas pelos Usu�rios com Permiss�o Especial :<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorSelecaoApenasComPermissao" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorSelecaoApenasComPermissao"  value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Prescri��o para Im�veis Particulares :<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorPrescricaoImoveisParticulares" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorPrescricaoImoveisParticulares"  value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de n�o inclus�o da cobran�a por resultado :<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorNaoIncluirImoveisEmCobrancaResultado" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorNaoIncluirImoveisEmCobrancaResultado"  value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador Cancela Negativa��o do Im�vel  :<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorCancelarImovelNegativacao" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorCancelarImovelNegativacao"  value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Bloquear Altera��o de Vencimento da Conta :<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorAlterarVencimentoConta" value="1"/><strong>Sim</strong>
						<html:radio property="indicadorAlterarVencimentoConta" value="2"/><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>				
				</tr>
				
				<tr>
					<td><strong>Indicador de Bloqueio de Certid�o Negativa de D�bitos  :<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorBloqueioCertidaoDebNegativado" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorBloqueioCertidaoDebNegativado"  value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>	
					
										
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigat�rio</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirInserirCobrancaSituacaoAction.do?menu=sim'">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td width="53" height="24" align="right"><input type="button"
						name="Button2" class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm(document.forms[0])" /></td>
					<td width="12">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>
