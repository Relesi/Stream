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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author rodrigo
 */
public class ExibirMovimentarHidrometroAction extends GcomAction {

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("movimentarHidrometro");

        //Obt�m a fachada
        Fachada fachada = Fachada.getInstancia();

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        //remove objetos da sess�o vindos do filtro
        sessao.removeAttribute("colecaoHidrometroClasseMetrologica");
        sessao.removeAttribute("colecaoHidrometroMarca");
        sessao.removeAttribute("colecaoHidrometroDiametro");
        sessao.removeAttribute("colecaoHidrometroCapacidade");
        sessao.removeAttribute("colecaoHidrometroTipo");
        sessao.removeAttribute("colecaoHidrometroRelojoaria");
        sessao.removeAttribute("colecaoHidrometroFatorCorrecao");
        sessao.removeAttribute("colecaoHidrometroClassePressao");
        

        sessao.removeAttribute("ManutencaoRegistroActionForm");
        sessao.removeAttribute("ConfirmarMovimentarHidrometroActionForm");

        //Obt�m objeto enviado na sess�o
        String fixo = (String) sessao.getAttribute("fixo");
        String tombamento = (String) sessao.getAttribute("tombamento");
        Short indicadorMacromedidor = null;
        if (sessao.getAttribute("indicadorMacromedidor") != null
        		&& !sessao.getAttribute("indicadorMacromedidor").toString().trim().equals("")){
        	indicadorMacromedidor = new Short(sessao.getAttribute("indicadorMacromedidor").toString());
        }

        //Cria cole��o
        Collection colecaoHidrometro = null;
        //Verifica se o usu�rio preencheu o filtro pelo fixo e intervalo
        if (fixo != null && !fixo.equalsIgnoreCase("")) {

            String faixaInicial = (String) sessao.getAttribute("faixaInicial");
            String faixaFinal = (String) sessao.getAttribute("faixaFinal");

            colecaoHidrometro = fachada.pesquisarNumeroHidrometroFaixa(fixo,
                    faixaInicial, faixaFinal);

        } else if (tombamento != null 
        		&& !tombamento.trim().equals("")
        		&& indicadorMacromedidor != null 
        		&& !indicadorMacromedidor.toString().trim().equals("")) {

            colecaoHidrometro = fachada.pesquisarNumeroHidrometroTombamento(
            		tombamento, indicadorMacromedidor);

        } else {

        	FiltroHidrometro filtroHidrometro = (FiltroHidrometro) sessao
            	.getAttribute("filtroHidrometro");
    
        	/*FiltroHidrometro filtroHidrometro = (FiltroHidrometro) httpServletRequest
                    .getAttribute("filtroHidrometro");*/
            filtroHidrometro
            		.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
            filtroHidrometro
    				.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
            filtroHidrometro
    				.adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
            filtroHidrometro
    				.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagem");
            filtroHidrometro
    				.adicionarCaminhoParaCarregamentoEntidade("hidrometroClassePressao");
            filtroHidrometro
    				.adicionarCaminhoParaCarregamentoEntidade("hidrometroFatorCorrecao");

            int totalPesquisa = fachada.totalRegistrosPesquisa(filtroHidrometro,
            		Hidrometro.class.getName());
	        if ( totalPesquisa != 0 && totalPesquisa >= 1000 ) {
	         	throw new ActionServletException("atencao.pesquisa_maior_que_permitido", null, "" );
	        }
           
           colecaoHidrometro = fachada.pesquisar(
                    filtroHidrometro, Hidrometro.class.getName());
        }
        
       

        //Caso a cole��o seja null
        if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null,
                    "Hidr�metro");
        }

        //Envia objeto na sess�o
        sessao.setAttribute("colecaoHidrometro", colecaoHidrometro);
        
        //devolve o mapeamento de retorno
        return retorno;
    }
}