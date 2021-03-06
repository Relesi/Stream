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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel por remover pagamentos do sistema 
 *
 * @author Pedro Alexandre
 * @date 21/03/2006
 */
public class RemoverPagamentoAction extends GcomAction {

    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * <Breve descri��o sobre o subfluxo>
     *
     * <Identificador e nome do subfluxo>	
     *
     * <Breve descri��o sobre o fluxo secund�rio>
     *
     * <Identificador e nome do fluxo secund�rio> 
     *
     * @author Pedro Alexandre
     * @date 21/03/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Recupera o form de manter pagamentos
        ManterPagamentoActionForm manterPagamentoActionForm = (ManterPagamentoActionForm) actionForm;

        //Recupera do form oarray comos c�digo de pagamentos selecionados para exclus�o
        String[] idsPagamentos = manterPagamentoActionForm.getIdRegistrosRemocao();

        //Seta o mapeamento de retorno para a tela de sucesso
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Cria uma inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);

        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");        
        
        //Caso o usu�rio n�o tenha selecionado nenhum pagamento pra exclus�o
        //Levanta uma exce��o para o usu�rio indicando que nenhum registro foi selecionado
        //Caso contr�rio chama o met�do de remover pagamentos da fachada
        if (idsPagamentos == null || idsPagamentos.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        }
        	
    	//n�o permitir a exclus�o de pagamentos cuja refer�ncia da arrecada��o seja menor que a refer�ncia da arrecada��o do sistema par�metro.
        SistemaParametro sistemaParametro = getSistemaParametro();
        
        List colecaoPagamento = Arrays.asList(idsPagamentos);  
        
        FiltroPagamento filtro = new FiltroPagamento();
        filtro.adicionarParametro(new ParametroSimplesIn(FiltroPagamento.ID, colecaoPagamento));
        Collection colecao = fachada.pesquisar(filtro, Pagamento.class.getName());
        
        if(!Util.isVazioOrNulo(colecao)){
        	Iterator it = colecao.iterator();
        	
        	while(it.hasNext()){
        		Pagamento pagamento = (Pagamento) it.next();
        		
        		if(pagamento.getAnoMesReferenciaArrecadacao() < sistemaParametro.getAnoMesArrecadacao()){
        			throw new ActionServletException("atencao.remocao_pagamento");
        		}
        	}
        }                    
    	
    	fachada.removerPagamentos(idsPagamentos,usuarioLogado);	        

        //Monta a p�gina de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest, idsPagamentos.length +
                    " Pagamento(s) removido(s) com sucesso.",
                    "Realizar outra Manuten��o de Pagamento",
                    "exibirFiltrarPagamentoAction.do?tela=manterPagamento&menu=sim");
        }

        //Retorna o mapeamento contido na vari�vel retorno
        return retorno;
    }
}
