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
 * R�mulo Aur�lio de Melo Souza Filho
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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalMotivo;
import gcom.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalTipo;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalMotivo;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalTipo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1527] - Inserir Ocorrencia Operacional
 * @author R�mulo Aur�lio
 * @date 10/07/2013
 */
public class ExibirInserirOcorrenciaOperacionalAction extends GcomAction {
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
	@SuppressWarnings({ "rawtypes", "unused" })
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("inserirOcorrenciaOperacional");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoLocalidade =  new ArrayList();
		Collection colecaoBairro =  new ArrayList();
		Collection colecaoOcorrenciaOperacionalMotivo =  new ArrayList();

		InserirOcorrenciaOperacionalActionForm form = (InserirOcorrenciaOperacionalActionForm) actionForm;
		
		if(form.getCodigoMunicipio() !=null && !form.getCodigoMunicipio().equals("")){
			
			//Dados Municipio
			
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, form.getCodigoMunicipio()));
			
			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
			
			if(colecaoMunicipio !=null && !colecaoMunicipio.isEmpty()){
				Municipio municipio = (Municipio) colecaoMunicipio.iterator().next();
				form.setDescricaoMunicipio(municipio.getNome());
				
				httpServletRequest.setAttribute("nomeCampo", "localidade");

				// Dados Localidade
				colecaoLocalidade = fachada.obterLocalidadesdoMunicipio(new Integer(form.getCodigoMunicipio()));
				
				// Dados Bairro
				FiltroBairro filtroBairro = new FiltroBairro();
				filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
				filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.MUNICIPIO_ID, form.getCodigoMunicipio()));
				filtroBairro.setCampoOrderBy(FiltroBairro.NOME);
				
				colecaoBairro = fachada.pesquisar(filtroBairro, Bairro.class.getName());
				
				
			}else{
				form.setCodigoMunicipio("");
				form.setDescricaoMunicipio("Munic�pio Inexistente");

				httpServletRequest.setAttribute("idMunicipioNaoEncontrado", "exception");
				httpServletRequest.setAttribute("nomeCampo", "codigoMunicipio");
			}
			
		}
		

		//Ocorrencia Operacional Tipo
		FiltroOcorrenciaOperacionalTipo filtroOcorrenciaOperacionalTipo = new FiltroOcorrenciaOperacionalTipo();
		filtroOcorrenciaOperacionalTipo.adicionarParametro(new ParametroSimples(
			FiltroOcorrenciaOperacionalTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoOcorrenciaOperacionalTipo =  fachada.pesquisar(filtroOcorrenciaOperacionalTipo, OcorrenciaOperacionalTipo.class.getName());
		
		
		//Carregamento de Ocorrencia Operacional Motivo
		if(form.getOcorrenciaTipo() != null && !form.getOcorrenciaTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FiltroOcorrenciaOperacionalMotivo filtroOcorrenciaOperacionalMotivo = new FiltroOcorrenciaOperacionalMotivo();
			filtroOcorrenciaOperacionalMotivo.adicionarParametro(new ParametroSimples(
				FiltroOcorrenciaOperacionalMotivo.OCORRENCIA_OPERACIONAL_TIPO_ID, form.getOcorrenciaTipo()));
			filtroOcorrenciaOperacionalMotivo.adicionarParametro(new ParametroSimples(
				FiltroOcorrenciaOperacionalMotivo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoOcorrenciaOperacionalMotivo =  fachada.pesquisar(
				filtroOcorrenciaOperacionalMotivo, OcorrenciaOperacionalMotivo.class.getName());
			
			
			
		}
		
		// Dados ComboBox
		httpServletRequest.setAttribute("colecaoOcorrenciaOperacionalTipo", colecaoOcorrenciaOperacionalTipo);
		httpServletRequest.setAttribute("colecaoLocalidade", colecaoLocalidade);
		httpServletRequest.setAttribute("colecaoBairro", colecaoBairro);
		httpServletRequest.setAttribute("colecaoOcorrenciaOperacionalMotivo", colecaoOcorrenciaOperacionalMotivo);
		
		return retorno;
	
	}
}
