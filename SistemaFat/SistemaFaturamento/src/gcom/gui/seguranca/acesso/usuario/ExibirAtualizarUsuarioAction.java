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
package gcom.gui.seguranca.acesso.usuario;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author S�vio Luiz
 */
public class ExibirAtualizarUsuarioAction extends GcomAction {

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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("atualizarUsuario");

		// obt�m a inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		StatusWizard statusWizard = null;

		// obtem o gerenciador de paginas na sess�o
		// gerenciadorPaginas = (GerenciadorPaginas)
		// sessao.getAttribute("gerenciadorPaginas");

		// limpa a sess�o
		sessao.removeAttribute("usuarioGrupo");
		sessao.removeAttribute("usuarioParaAtualizar");
		sessao.removeAttribute("grupo");
		sessao.removeAttribute("collEmpresa");
		sessao.removeAttribute("collUsuarioTipo");
		sessao.removeAttribute("collUsuarioAbrangencia");
		sessao.removeAttribute("collGrupo");
		sessao.removeAttribute("collGerenciaRegional");
		sessao.removeAttribute("collUnidadeNegocio");
		sessao.removeAttribute("AtualizarUsuarioDadosGeraisActionForm");
		sessao.removeAttribute("desabilitaUsuarioAbrangencia");

		String idUsuario = null;

		if (httpServletRequest.getParameter("desfazer") == null) {

			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idUsuario = (String) httpServletRequest
						.getParameter("idRegistroAtualizacao");
			} else {
				idUsuario = (String) httpServletRequest
						.getAttribute("idRegistroAtualizacao");
			}

			// verifica se chegou no atualizar imovel atraves da tela de filtrar
			// devido a um unico registro
			// ou atraves da lista de imoveis no manter imovel
			if (httpServletRequest.getAttribute("atualizar") != null) {
				statusWizard = new StatusWizard("atualizarUsuarioWizardAction",
						"concluirAtualizarUsuarioAction",
						"cancelarAtualizarUsuarioAction",
						"exibirFiltrarUsuarioAction",
						"exibirAtualizarUsuarioAction.do", idUsuario);
			} else if (httpServletRequest.getParameter("sucesso") != null) {
				statusWizard = new StatusWizard("atualizarUsuarioWizardAction",
						"concluirAtualizarUsuarioAction",
						"cancelarAtualizarUsuarioAction",
						"exibirFiltrarUsuarioAction",
						"exibirAtualizarUsuarioAction.do", idUsuario);
			} else {
				statusWizard = new StatusWizard("atualizarUsuarioWizardAction",
						"concluirAtualizarUsuarioAction",
						"cancelarAtualizarUsuarioAction",
						"exibirManterUsuarioAction",
						"exibirAtualizarUsuarioAction.do", idUsuario);
			}

			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							1, "DadosGeraisPrimeiraAbaA.gif",
							"DadosGeraisPrimeiraAbaD.gif",
							"exibirAtualizarUsuarioDadosGeraisAction",
							"atualizarUsuarioDadosGeraisAction"));
			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							2, "AcessosUsuarioUltimaAbaA.gif",
							"AcessosUsuarioUltimaAbaD.gif",
							"exibirAtualizarUsuarioAcessosUsuarioAction",
							"atualizarUsuarioAcessosUsuarioAction"));

		} else {
			statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");

			idUsuario = statusWizard.getId();
		}
		AtualizarUsuarioDadosGeraisActionForm atualizarUsuarioDadosGeraisActionForm = (AtualizarUsuarioDadosGeraisActionForm) actionForm;

		// Parte da verifica��o do filtro
		FiltroUsuario filtroUsuario = new FiltroUsuario();

		// filtroUsuario.setCampoOrderBy(FiltroUsuario.NOME_USUARIO);
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID,
				idUsuario));
		filtroUsuario
				.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional.unidadeTipo");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioTipo");
		
		filtroUsuario
				.adicionarCaminhoParaCarregamentoEntidade("funcionario.unidadeOrganizacional");
		filtroUsuario
			.adicionarCaminhoParaCarregamentoEntidade("funcionario.empresa");
		filtroUsuario
				.adicionarCaminhoParaCarregamentoEntidade("usuarioAbrangencia");
		filtroUsuario
				.adicionarCaminhoParaCarregamentoEntidade("usuarioSituacao");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("localidadeElo");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("localidade");

		Collection colecaoUsuario = Fachada.getInstancia().pesquisar(
				filtroUsuario, Usuario.class.getName());

		Usuario usuarioParaAtualizar = (Usuario) Util
				.retonarObjetoDeColecao(colecaoUsuario);
		// [FS0008] - Verificar permiss�o para atualiza��o

		UnidadeOrganizacional unidadeEmpresa = usuarioParaAtualizar
				.getUnidadeOrganizacional();
		if (unidadeEmpresa == null) {
			if (usuarioParaAtualizar.getFuncionario() != null
					&& usuarioParaAtualizar.getFuncionario().equals("")) {
				unidadeEmpresa = usuarioParaAtualizar.getFuncionario()
						.getUnidadeOrganizacional();
			}
		}

		// caso o usu�rio que esteja efetuando a inser��o n�o
		// seja
		// do grupo de administradores
		FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();

		filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
				FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
		filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
				FiltroUsuarioGrupo.GRUPO_ID, Grupo.ADMINISTRADOR));
		Collection colecaoUsuarioGrupo = Fachada.getInstancia().pesquisar(
				filtroUsuarioGrupo, UsuarioGrupo.class.getName());
		if (colecaoUsuarioGrupo == null || colecaoUsuarioGrupo.isEmpty()) {
			// se a unidade de lotacao do usuario que estiver
			// efetuando seja diferente da unidade de
			// lota��o informada
			if (usuario.getUnidadeOrganizacional() != null
					&& usuario.getUnidadeOrganizacional().getId() != null) {
				// recupera a unidade do usu�rio
				FiltroUnidadeOrganizacional filtroUnidadeEmpresaUsuario = new FiltroUnidadeOrganizacional();
				filtroUnidadeEmpresaUsuario
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.ID, usuario
										.getUnidadeOrganizacional().getId()));
				filtroUnidadeEmpresaUsuario
						.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
				Collection colecaoUnidadeEmpresa = Fachada.getInstancia()
						.pesquisar(filtroUnidadeEmpresaUsuario,
								UnidadeOrganizacional.class.getName());
				UnidadeOrganizacional unidadeEmpresaUsuario = null;
				if (colecaoUnidadeEmpresa != null
						&& !colecaoUnidadeEmpresa.isEmpty()) {
					unidadeEmpresaUsuario = (UnidadeOrganizacional) Util
							.retonarObjetoDeColecao(colecaoUnidadeEmpresa);
				}
				// se o nivel da unidade de lota��o do usu�rio
				// que
				// estiver efetuando a inser��o seja maior ou
				// igual
				// ao nivel de unidade de lota��o informada
				if (unidadeEmpresaUsuario != null) {
					if (unidadeEmpresaUsuario.getUnidadeTipo().getNivel() != null
							&& unidadeEmpresa.getUnidadeTipo().getNivel() != null) {
						if (unidadeEmpresaUsuario.getUnidadeTipo().getNivel()
								.intValue() >= unidadeEmpresa.getUnidadeTipo()
								.getNivel().intValue()) {
							throw new ActionServletException(
									"atencao.usuario.sem.permissao",
									usuario.getLogin(), 
									unidadeEmpresa.getDescricao());
						}
					} else {
						throw new ActionServletException(
								"atencao.usuario.sem.permissao", usuario
										.getLogin(), unidadeEmpresa
										.getDescricao());
					}

					// ou a unidade superior da unidade de
					// lota��o
					// informada seja diferente da unidade de
					// lota��o do usu�rio

					// enquanto o nivel superior da unidade de
					// lota��o n�o esteja no mesmo nivel da
					// unidade
					// de lota��o do usu�rio
					boolean mesmoNivel = true;
					int idNivelUsuario = unidadeEmpresaUsuario.getUnidadeTipo()
							.getNivel().intValue();
					UnidadeOrganizacional unidadeEmpresaSuperior = null;
					while (mesmoNivel) {
						Integer idUnidadeEmpresaSuperior = null;
						if (unidadeEmpresaSuperior == null) {
							if (unidadeEmpresa.getUnidadeSuperior() != null
									&& !unidadeEmpresa.getUnidadeSuperior()
											.equals("")) {
								idUnidadeEmpresaSuperior = unidadeEmpresa
										.getUnidadeSuperior().getId();
							}
						} else {
							if (unidadeEmpresaSuperior.getUnidadeSuperior() != null
									&& !unidadeEmpresaSuperior
											.getUnidadeSuperior().equals("")) {
								idUnidadeEmpresaSuperior = unidadeEmpresaSuperior
										.getUnidadeSuperior().getId();
							}
						}
						if (idUnidadeEmpresaSuperior == null) {
							throw new ActionServletException(
									"atencao.usuario.sem.permissao",
									usuario.getLogin(), unidadeEmpresa
											.getDescricao());
						}
						// recupera a unidade do usu�rio
						FiltroUnidadeOrganizacional filtroUnidadeEmpresaSuperior = new FiltroUnidadeOrganizacional();
						filtroUnidadeEmpresaSuperior
								.adicionarParametro(new ParametroSimples(
										FiltroUnidadeOrganizacional.ID,
										idUnidadeEmpresaSuperior));
						filtroUnidadeEmpresaSuperior
								.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
						Collection colecaoUnidadeEmpresaSuperior = Fachada
								.getInstancia().pesquisar(
										filtroUnidadeEmpresaSuperior,
										UnidadeOrganizacional.class.getName());
						if (colecaoUnidadeEmpresaSuperior != null
								&& !colecaoUnidadeEmpresaSuperior.isEmpty()) {
							unidadeEmpresaSuperior = (UnidadeOrganizacional) Util
									.retonarObjetoDeColecao(colecaoUnidadeEmpresaSuperior);
						}
						if (unidadeEmpresaSuperior != null) {
							if (unidadeEmpresaSuperior.getUnidadeTipo()
									.getNivel() == null
									|| unidadeEmpresaSuperior.getUnidadeTipo()
											.getNivel().equals("")) {
								throw new ActionServletException(
										"atencao.usuario.sem.permissao",
										usuario.getLogin(), unidadeEmpresa
												.getDescricao());

							}
							// caso seja o mesmo nivel
							if (unidadeEmpresaSuperior.getUnidadeTipo()
									.getNivel().intValue() == idNivelUsuario) {
								mesmoNivel = false;
								// caso o id da unidade empresa
								// informado for diferente do id
								// da
								// unidade empresa do usu�rio no
								// mesmo nivel
								if (!unidadeEmpresaSuperior.getId().equals(
										unidadeEmpresaUsuario.getId())) {
									throw new ActionServletException(
											"atencao.usuario.sem.permissao",
											usuario.getLogin(),
											unidadeEmpresa.getDescricao());
								}

							}
						}
					}

				}

			}
		}

		
		//comentado a pedido de Leonardo Vieira
		//Vivianne Sousa 06/02/2007
		// [FS0018] - Verificar situa��o do usu�rio
//		if (usuarioParaAtualizar.getUsuarioSituacao() != null
//				&& !usuarioParaAtualizar.getUsuarioSituacao().getId().equals(
//						UsuarioSituacao.ATIVO)) {
//			throw new ActionServletException("atencao.usuario.situacao", null,
//					usuarioParaAtualizar.getLogin(), usuarioParaAtualizar
//							.getUsuarioSituacao().getDescricaoUsuarioSituacao());
//		}
		atualizarUsuarioDadosGeraisActionForm.setNome(usuarioParaAtualizar
				.getNomeUsuario());
		// cria um array de string para os ids do grupo
		String[] idsGrupos = null;
		Collection colecaoGruposUsuario = Fachada.getInstancia()
				.pesquisarGruposUsuario(new Integer(idUsuario));
		if (colecaoGruposUsuario != null && !colecaoGruposUsuario.isEmpty()) {
			idsGrupos = new String[colecaoGruposUsuario.size()];
			Iterator iteratorGrupos = colecaoGruposUsuario.iterator();
			int i = 0;
			while (iteratorGrupos.hasNext()) {
				Grupo grupo = (Grupo) iteratorGrupos.next();
				idsGrupos[i] = grupo.getId().toString();
				i++;
			}
		}

		if (usuarioParaAtualizar.getId() != null
				&& !usuarioParaAtualizar.getId().equals("")) {
			statusWizard.adicionarItemHint("C�digo:", usuarioParaAtualizar
					.getId().toString());
		}
		if (usuarioParaAtualizar.getNomeUsuario() != null
				&& !usuarioParaAtualizar.getNomeUsuario().equals("")) {
			statusWizard.adicionarItemHint("Nome:", usuarioParaAtualizar
					.getNomeUsuario());
		}
		if (usuarioParaAtualizar.getUsuarioTipo() != null
				&& !usuarioParaAtualizar.getUsuarioTipo().equals("")) {
			if (usuarioParaAtualizar.getUsuarioTipo().getId() != null
					&& !usuarioParaAtualizar.getUsuarioTipo().equals("")) {
				statusWizard.adicionarItemHint("Tipo:", usuarioParaAtualizar
						.getUsuarioTipo().getDescricao());
			}
		}
		if (usuarioParaAtualizar.getUsuarioAbrangencia() != null
				&& !usuarioParaAtualizar.getUsuarioAbrangencia().equals("")) {
			if (usuarioParaAtualizar.getUsuarioAbrangencia().getId() != null
					&& !usuarioParaAtualizar.getUsuarioAbrangencia().equals("")) {
				statusWizard.adicionarItemHint("Abrang�ncia:",
						usuarioParaAtualizar.getUsuarioAbrangencia()
								.getDescricao());
			}
		}
		sessao.setAttribute("statusWizard", statusWizard);

		sessao.setAttribute("usuarioParaAtualizar", usuarioParaAtualizar);
		sessao.setAttribute("usuario", usuario);

		sessao.setAttribute("grupo", idsGrupos);
		return retorno;
	}
}
