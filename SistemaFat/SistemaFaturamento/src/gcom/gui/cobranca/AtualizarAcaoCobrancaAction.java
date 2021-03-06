/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
* Anderson Italo Felinto de Lima
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cobranca;

import java.util.Collection;
import java.util.Iterator;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.ColunasTextoSMSEmail;
import gcom.cobranca.bean.CobrancaAcaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o crit�rio da cobran�a e as linhas do criterio da
 * cobran�a
 * 
 * @author S�vio Luiz
 * @date 18/09/2007
 */
public class AtualizarAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AcaoCobrancaAtualizarActionForm acaoCobrancaAtualizarActionForm = (AcaoCobrancaAtualizarActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		Fachada fachada = Fachada.getInstancia();
		
		CobrancaAcao cobrancaAcao = (CobrancaAcao)sessao.getAttribute("cobrancaAcao");
		
		
		Collection<ColunasTextoSMSEmail> colecaoColunas = (Collection<ColunasTextoSMSEmail>)sessao.getAttribute("colecaoDadosTexto");
		String idSelecionado = acaoCobrancaAtualizarActionForm.getDadosTexto();
		String textoSMS = acaoCobrancaAtualizarActionForm.getTextoSMS();
		String textoEmail = acaoCobrancaAtualizarActionForm.getTextoEmail();
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		if(Util.verificarNaoVazio(textoSMS)){
			
			//[FS0012] - Validar quantidade de colunas digitadas/selecionadas	
			//Calculando o total das tags inseridas
			int tamanho = 0;
			Iterator<ColunasTextoSMSEmail> it = colecaoColunas.iterator();
			Integer tamanhoSelecionado = 0;
			while(it.hasNext()){
				ColunasTextoSMSEmail col = it.next();
				if(col.getId().toString().equals(idSelecionado))
					tamanhoSelecionado = col.getTamanhoColuna();
				while(textoSMS.contains(col.getNomeColuna())){
					tamanho += col.getTamanhoColuna();
					int index = textoSMS.indexOf(col.getNomeColuna());
					textoSMS = textoSMS.substring(0, index) + textoSMS.substring(index + col.getNomeColuna().length());
				}
			}
			
			tamanho += tamanhoSelecionado + textoSMS.length() + 57;
			
			//Caso a contagem de caracteres digitados mais o n�mero de d�gitos do campo adicionado
			//for maior que o n�mero m�ximo de colunas da mensagem SMS
			if(tamanho > sistemaParametro.getTamanhoMaximoMensagemSms().intValue()){
				throw new ActionServletException("atencao.numero_colunas_maior_permitido_sms",
						null, sistemaParametro.getTamanhoMaximoMensagemSms().toString());
			}
			
			//[F0021] Verificar a integridade do texto de SMS
			this.validarTagsTextAreaSMSEmail(textoSMS, colecaoColunas, new Short("1"), httpServletRequest, acaoCobrancaAtualizarActionForm);
		}
		
		if(Util.verificarNaoVazio(textoEmail)){
			this.validarTagsTextAreaSMSEmail(textoEmail, colecaoColunas, new Short("2"), httpServletRequest, acaoCobrancaAtualizarActionForm);
		}
		
		
		String idAcaoPredecessora = "";
		String numeroDiasMinimoAcoes = "";
		if ( acaoCobrancaAtualizarActionForm.getIdAcaoPredecessora() != null &&
				!acaoCobrancaAtualizarActionForm.getIdAcaoPredecessora().equals("")){
			idAcaoPredecessora = acaoCobrancaAtualizarActionForm.getIdAcaoPredecessora();
			numeroDiasMinimoAcoes = acaoCobrancaAtualizarActionForm.getNumeroDiasEntreAcoes();
		}
		
		CobrancaAcaoHelper cobrancaAcaoHelper = new CobrancaAcaoHelper(
				acaoCobrancaAtualizarActionForm.getDescricaoAcao(),
				acaoCobrancaAtualizarActionForm.getIcAcaoObrigatoria(),
				acaoCobrancaAtualizarActionForm.getIcRepetidaCiclo(),
				acaoCobrancaAtualizarActionForm.getIcSuspensaoAbastecimento(),
				acaoCobrancaAtualizarActionForm.getNumeroDiasValidade(),
				acaoCobrancaAtualizarActionForm.getNumeroDiasEntreAcoes(), 
				acaoCobrancaAtualizarActionForm.getIcUso(),
				acaoCobrancaAtualizarActionForm.getIcDebitosACobrar(),
				acaoCobrancaAtualizarActionForm.getIcCreditosARealizar(),
				acaoCobrancaAtualizarActionForm.getIcNotasPromissoria(),
				acaoCobrancaAtualizarActionForm.getIcGeraTaxa(),
				acaoCobrancaAtualizarActionForm.getOrdemCronograma(),
				acaoCobrancaAtualizarActionForm.getIcAcrescimosImpontualidade(),
				acaoCobrancaAtualizarActionForm.getIdAcaoPredecessora(),
				acaoCobrancaAtualizarActionForm.getIdAcaoPredecessoraAlternativa(),
				acaoCobrancaAtualizarActionForm.getIdSituacaoLigacaoEsgoto(),
				acaoCobrancaAtualizarActionForm.getIdTipoDocumentoGerado(),
				acaoCobrancaAtualizarActionForm.getIdSituacaoLigacaoAgua(),
				acaoCobrancaAtualizarActionForm.getIdServicoTipo(),
				acaoCobrancaAtualizarActionForm.getIdCobrancaCriterio(),
				acaoCobrancaAtualizarActionForm.getIcCompoeCronograma(),
				acaoCobrancaAtualizarActionForm.getIcEmitirBoletimCadastro(),
				acaoCobrancaAtualizarActionForm.getIcDebitoInterfereAcao(),
				acaoCobrancaAtualizarActionForm.getNumeroDiasVencimento(),
				acaoCobrancaAtualizarActionForm.getDescricaoCobrancaCriterio(),
				acaoCobrancaAtualizarActionForm.getDescricaoServicoTipo(),
				acaoCobrancaAtualizarActionForm.getIcMetasCronograma(),
				acaoCobrancaAtualizarActionForm.getIcOrdenamentoCronograma(),
				acaoCobrancaAtualizarActionForm.getIcOrdenamentoEventual(), 
				acaoCobrancaAtualizarActionForm.getIcDebitoInterfereAcao(),
				acaoCobrancaAtualizarActionForm.getNumeroDiasRemuneracaoTerceiro(),
				acaoCobrancaAtualizarActionForm.getIcOrdenarMaiorValor(),
				acaoCobrancaAtualizarActionForm.getIcValidarItem(),
				acaoCobrancaAtualizarActionForm.getIcEfetuarAcaoCpfCnpjValido(),
				usuarioLogado,
				acaoCobrancaAtualizarActionForm.getIndicadorMensagemSMS(),
				acaoCobrancaAtualizarActionForm.getIndicadorMensagemEmail(),
				acaoCobrancaAtualizarActionForm.getTextoSMS(),
				acaoCobrancaAtualizarActionForm.getTextoEmail(),
				acaoCobrancaAtualizarActionForm.getNumeroMaximoTentativoEnvio()); 
		 
		
		cobrancaAcaoHelper.setTextoPersonalizado(acaoCobrancaAtualizarActionForm.getTextoPersonalizado());
		
		cobrancaAcaoHelper.setNumeroDiasMinimoCobranca(null);
		cobrancaAcaoHelper.setNumeroDiasMaximoCobranca(null);
		if (acaoCobrancaAtualizarActionForm.getIcValidarItem() != null && acaoCobrancaAtualizarActionForm.getIcValidarItem().equals("1") ) {			
			
			if (acaoCobrancaAtualizarActionForm.getNumeroDiasMinimoCobranca() != null 
					&& !acaoCobrancaAtualizarActionForm.getNumeroDiasMinimoCobranca().trim().equalsIgnoreCase("")){
				cobrancaAcaoHelper.setNumeroDiasMinimoCobranca(new Integer (acaoCobrancaAtualizarActionForm.getNumeroDiasMinimoCobranca()));
			}
			if (acaoCobrancaAtualizarActionForm.getNumeroDiasMaximoCobranca() != null 
					&& !acaoCobrancaAtualizarActionForm.getNumeroDiasMaximoCobranca().trim().equalsIgnoreCase("")){
				cobrancaAcaoHelper.setNumeroDiasMaximoCobranca(new Integer (acaoCobrancaAtualizarActionForm.getNumeroDiasMaximoCobranca()));
			}			
		} 
		
		cobrancaAcaoHelper.setIndicadorExibeEventual(ConstantesSistema.NAO);
		if (acaoCobrancaAtualizarActionForm.getIndicadorExibeEventual() != null) {
			cobrancaAcaoHelper.setIndicadorExibeEventual(new Short(acaoCobrancaAtualizarActionForm.getIndicadorExibeEventual()));
		}
		
		fachada.atualizarAcaoCobranca(cobrancaAcao,cobrancaAcaoHelper);

		sessao.removeAttribute("voltar");
		sessao.removeAttribute("cobrancaAcao");
		sessao.removeAttribute("colecaoLigacaoEsgotoSituacao");
		sessao.removeAttribute("colecaoLigacaoAguaSituacao");
		sessao.removeAttribute("colecaoDocumentoTipo");
		sessao.removeAttribute("colecaoAcaoPredecessora");

		montarPaginaSucesso(httpServletRequest, "A��o de Cobran�a "
				+ acaoCobrancaAtualizarActionForm.getDescricaoAcao() + " atualizada com sucesso.",
				"Realizar outra Manuten��o de A��o de Cobran�a",
		        "exibirFiltrarAcaoCobrancaAction.do?menu=sim");
		
		
		return retorno;
	}
	
	
	@SuppressWarnings("rawtypes")
	private void validarTagsTextAreaSMSEmail(String textoSMSEmail,
			Collection<ColunasTextoSMSEmail> colecaoColunasTextoSMSEmail,Short indicadorSMSEmail, 
			HttpServletRequest httpServletRequest,AcaoCobrancaAtualizarActionForm form){
		
		if(validarQuantidadeTagsAbertasFechadas(textoSMSEmail)){
			boolean encontrouTag = false;
			String[] arrayChavesAberta = textoSMSEmail.split("[{]");
			String[] arrayChevesFechada = null;
			for(int posicao = 0;posicao<arrayChavesAberta.length;posicao++){
				encontrouTag = false;
				if(pesquisarChaves(arrayChavesAberta[posicao])){
					arrayChevesFechada = arrayChavesAberta[posicao].split("[}]");
					Iterator iterator = colecaoColunasTextoSMSEmail.iterator();
					ColunasTextoSMSEmail colunasTextoSMSEmail = null;
					while(iterator.hasNext()){
						colunasTextoSMSEmail = (ColunasTextoSMSEmail) iterator.next();
						if(arrayChevesFechada[0].compareTo(colunasTextoSMSEmail.getNomeColuna().replace("{", "").replace("}", ""))==0){
							encontrouTag = true;
						}
					}
					if(encontrouTag==false){
						if(indicadorSMSEmail.compareTo(ConstantesSistema.SIM)==0){
							throw new ActionServletException("atencao.tag.sms.invalida",null,"{"+arrayChevesFechada[0]+"}");
						}else{
							throw new ActionServletException("atencao.tag.email.invalida",null,"{"+arrayChevesFechada[0]+"}");
						}
					}
				}
			}
		}else{
			if(indicadorSMSEmail.compareTo(ConstantesSistema.SIM)==0){
				
				throw new ActionServletException("atencao.quantidade.tags.invalida",null,"SMS");
				
			}else if(indicadorSMSEmail.compareTo(ConstantesSistema.NAO)==0){
				throw new ActionServletException("atencao.quantidade.tags.invalida",null,"Email");		
			}
		}
	}
	
	private boolean validarQuantidadeTagsAbertasFechadas(String textoSMSEmail){
		boolean quantidadeValidada = false;
		int quantidadeAbrirChaves = 0;
		int quantidadeFecharChaves = 0;
		
		char[] arrayTextoSMSEmail = textoSMSEmail.toCharArray();
		
		for(int posicao = 0;posicao<textoSMSEmail.length();posicao++){
			if(arrayTextoSMSEmail[posicao]=='{'){
				quantidadeAbrirChaves++;
			}else if(arrayTextoSMSEmail[posicao]=='}'){
				quantidadeFecharChaves++;
			}
		}
		
		if(quantidadeAbrirChaves==quantidadeFecharChaves){
			quantidadeValidada = true;
		}
		
		return quantidadeValidada;
	}
	
	private boolean pesquisarChaves(String textoQuebrado){
		boolean temAspas = false;
		char[] arrayTextoQuebrado = textoQuebrado.toCharArray();
		for(int posicao = 0;posicao<arrayTextoQuebrado.length;posicao++){
			if(arrayTextoQuebrado[posicao]=='}'){
				temAspas=true;
				break;
			}
		}
		return temAspas;
	}

}
