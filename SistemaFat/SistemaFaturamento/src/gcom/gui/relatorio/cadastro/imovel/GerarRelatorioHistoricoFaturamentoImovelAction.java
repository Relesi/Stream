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
package gcom.gui.relatorio.cadastro.imovel;

import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.RelatorioHistoricoFaturamentoImovel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *Classe com a l�gica de gera��o do relatorio de Hist�rico de Faturamento
 *do wizard de Consultar Imovel
 *
 * @author Marlon Patrick
 * @since 28/09/2009
 */
public class GerarRelatorioHistoricoFaturamentoImovelAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarImovelActionForm consultarImovelForm = (ConsultarImovelActionForm) actionForm;

		RelatorioHistoricoFaturamentoImovel relatorioHistoricoFaturamento = criarRelatorioComParametros(
				consultarImovelForm, sessao, tipoRelatorio);

		ActionForward retorno = null;

		try {
			retorno = processarExibicaoRelatorio(relatorioHistoricoFaturamento,
					tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

		} catch (SistemaException ex) {
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			reportarErros(httpServletRequest, "erro.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}
	
	/**
	 *Esse m�todo cria o objeto RelatorioDadosCadastraisImovel,
	 *adiciona os parametros necess�rios ao seu funcionamento e o retorna.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private RelatorioHistoricoFaturamentoImovel criarRelatorioComParametros(ConsultarImovelActionForm consultarImovelForm,
			HttpSession sessao, String tipoRelatorio) {
		
		RelatorioHistoricoFaturamentoImovel relatorioHistoricoFaturamento = new RelatorioHistoricoFaturamentoImovel((Usuario)sessao.getAttribute("usuarioLogado"));
		
		relatorioHistoricoFaturamento.addParametro("colecaoContaImovel",sessao.getAttribute("colecaoContaImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoContaHistoricoImovel",sessao.getAttribute("colecaoContaHistoricoImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoDebitoACobrarImovel",sessao.getAttribute("colecaoDebitoACobrarImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoDebitoACobrarHistoricoImovel",sessao.getAttribute("colecaoDebitoACobrarHistoricoImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoCreditoARealizarImovel",sessao.getAttribute("colecaoCreditoARealizarImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoCreditoARealizarHistoricoImovel",sessao.getAttribute("colecaoCreditoARealizarHistoricoImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoGuiaPagamentoImovel",sessao.getAttribute("colecaoGuiaPagamentoImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoGuiaPagamentoHistoricoImovel",sessao.getAttribute("colecaoGuiaPagamentoHistoricoImovel"));
		
		relatorioHistoricoFaturamento.addParametro("consultarImovelForm",consultarImovelForm);
		relatorioHistoricoFaturamento.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		return relatorioHistoricoFaturamento;
	}


}