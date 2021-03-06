/**
 * 
 */
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
package gcom.gui.atendimentopublico;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.gui.GcomAction;
import gcom.relatorio.faturamento.RelatorioDeclaracaoAnualQuitacaoDebitos;
import gcom.util.ConstantesSistema;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 22/01/2007
 */
public class ExibirSelecionarBancoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirSelecionarBancoAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String idConta = httpServletRequest.getParameter("idConta");

		sessao.setAttribute("idConta", idConta);

		Collection idsContaEP = new ArrayList();
		if (httpServletRequest.getParameter("idConta") != null
				&& !httpServletRequest.getParameter("idConta").equals("")) {
			idsContaEP.add(new Integer(""
					+ httpServletRequest.getParameter("idConta")));
		}

		Collection<EmitirContaHelper> colecaoEmitirContaHelper = fachada
				.emitir2ViaContas(idsContaEP, false,ConstantesSistema.NAO);

		EmitirContaHelper emitirContaHelper = colecaoEmitirContaHelper
				.iterator().next();
		
		/*
		 * Caso o documento selecionado seja uma ficha de compensa��o, valor maior que mil reais,
		 * o sistema ir� chamar a tela inicial do banco.
		 */
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		String nomeEmpresa = sistemaParametro.getNomeAbreviadoEmpresa();
		
		//Caso a empresa seja caer n�o aparecer o banco santander.
		if(nomeEmpresa.equalsIgnoreCase("CAER")){
			httpServletRequest.setAttribute("santander", "2");
		}
		
		if (sistemaParametro.getValorContaFichaComp() != null){
			
			if (emitirContaHelper.getValorConta().compareTo(sistemaParametro.getValorContaFichaComp()) <= 0){
				
				String representacaoNumericaCodBarraFormatada = emitirContaHelper
				.getRepresentacaoNumericaCodBarraFormatada();

				// Cortar a representacaoNumericaCodBarraFormatada separando de 12 em
				// 12, mas tirando os espacos e tra�os
			
				String cAux1 = representacaoNumericaCodBarraFormatada.substring(0, 10)
						+ representacaoNumericaCodBarraFormatada.substring(12, 13);
			
				String cAux2 = representacaoNumericaCodBarraFormatada.substring(14, 25)
						+ representacaoNumericaCodBarraFormatada.substring(26, 27);
			
				String cAux3 = representacaoNumericaCodBarraFormatada.substring(28, 39)
						+ representacaoNumericaCodBarraFormatada.substring(40, 41);
			
				String cAux4 = representacaoNumericaCodBarraFormatada.substring(42, 53)
						+ representacaoNumericaCodBarraFormatada.substring(54, 55);
				//		
				// String cAux2 =
				// String cAux3 =
				// String cAux4 =
				// codigo de barras passado pela 2. via de conta
				String codigo = cAux1 + cAux2 + cAux3 + cAux4;
			
				httpServletRequest.setAttribute("codigo", codigo);
				httpServletRequest.setAttribute("fichaCompensacao", "2");
			}
			else{
				
				httpServletRequest.setAttribute("fichaCompensacao", "1");
			}
		}
		else{
			
			String representacaoNumericaCodBarraFormatada = emitirContaHelper
			.getRepresentacaoNumericaCodBarraFormatada();

			// Cortar a representacaoNumericaCodBarraFormatada separando de 12 em
			// 12, mas tirando os espacos e tra�os
		
			String cAux1 = representacaoNumericaCodBarraFormatada.substring(0, 10)
					+ representacaoNumericaCodBarraFormatada.substring(12, 13);
		
			String cAux2 = representacaoNumericaCodBarraFormatada.substring(14, 25)
					+ representacaoNumericaCodBarraFormatada.substring(26, 27);
		
			String cAux3 = representacaoNumericaCodBarraFormatada.substring(28, 39)
					+ representacaoNumericaCodBarraFormatada.substring(40, 41);
		
			String cAux4 = representacaoNumericaCodBarraFormatada.substring(42, 53)
					+ representacaoNumericaCodBarraFormatada.substring(54, 55);
			//		
			// String cAux2 =
			// String cAux3 =
			// String cAux4 =
			// codigo de barras passado pela 2. via de conta
			String codigo = cAux1 + cAux2 + cAux3 + cAux4;
		
			httpServletRequest.setAttribute("codigo", codigo);
			httpServletRequest.setAttribute("fichaCompensacao", "2");
		}
		

		return retorno;

	}
}
