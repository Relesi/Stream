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
package gcom.gui.financeiro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.financeiro.FiltroLancamentoOrigem;
import gcom.financeiro.lancamento.LancamentoOrigem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Gerar integra��o para contabilidade.
 *
 * @author Pedro Alexandre
 * @date 28/05/2007
 */
public class ExibirGerarIntegracaoContabilidadeAction extends GcomAction {
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
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//seta o retorno para a p�gina de gerar integra��o para a contabilidade
		ActionForward retorno = actionMapping.findForward("exibirGerarIntegracaoContabilidade");

		//cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		GerarIntegracaoContabilidadeActionForm gerarIntegracaoContabilidadeActionForm = (GerarIntegracaoContabilidadeActionForm) actionForm;

		//recupera a sess�o do usu�rio
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Pesquisa as origens de lan�amentos cadastradas no sistema    
		FiltroLancamentoOrigem filtroLancamentoOrigem = new FiltroLancamentoOrigem();
		Collection colecaoLancamentoOrigem = fachada.pesquisar(filtroLancamentoOrigem, LancamentoOrigem.class.getName());
		
		
		String idOrigem = httpServletRequest.getParameter("objetoConsulta");
		
		Collection colecaoOrigemLancamento = null;
		if(idOrigem != null && !idOrigem.equals("")){
			filtroLancamentoOrigem.adicionarParametro(new ParametroSimples(FiltroLancamentoOrigem.ID, idOrigem));
			colecaoOrigemLancamento = fachada.pesquisar(filtroLancamentoOrigem, LancamentoOrigem.class.getName());
		}
		LancamentoOrigem lancamentoOrigem = null;
		if(!Util.isVazioOrNulo(colecaoOrigemLancamento)){
			lancamentoOrigem = (LancamentoOrigem) Util.retonarObjetoDeColecao(colecaoOrigemLancamento);
		}
		
		if(lancamentoOrigem != null && lancamentoOrigem.getDescricao().equals("RECEITA")){
			gerarIntegracaoContabilidadeActionForm.setNumeroUltimoSequencial( lancamentoOrigem.getNumeroUtilmoSequencial()+"" );
			sessao.setAttribute("origemReceita", true);	
			gerarIntegracaoContabilidadeActionForm.setIdLacamentoOrigem(idOrigem);
		}else{
			sessao.removeAttribute("origemReceita");
			gerarIntegracaoContabilidadeActionForm.setNumeroUltimoSequencial( "" );
			gerarIntegracaoContabilidadeActionForm.setIdLacamentoOrigem(idOrigem);
		}
				
		//[FS0001 - Verificar exist�ncia de dados]
		//caso n�o exista nenhuma origem de lan�amento cadastrada no sistema 
		//levanta a exce��o para o usu�rio
		if(colecaoLancamentoOrigem == null || colecaoLancamentoOrigem.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,"Lan�amento Origem");
		}else{
			sessao.setAttribute("colecaoLancamentoOrigem", colecaoLancamentoOrigem);
		} 
		return retorno;
	}
}
