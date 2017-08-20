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
package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Criptografia;
import gcom.util.ErroCriptografiaException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Action respons�vel pela pr�-exibi��o da tela de alterar a senha do usu�rio
 *
 * @author Fl�vio Cordeiro
 * @date 24/02/2006
 */
public class EfetuarAlteracaoSenhaPorMatriculaAction extends GcomAction {

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * [UC0287] - Efetuar Login
	 * 
	 * @author Flavio Cordeiro
	 * @date 24/02/2007
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

		// Prepara o retorno da a��o para a tela de lembrar senha
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		//Obt�m a inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();
		
		EfetuarAlteracaoSenhaPorMatriculaActionForm form = (EfetuarAlteracaoSenhaPorMatriculaActionForm) actionForm;
		
		//Recupera uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o usu�rio que est� solicitando o lembrete da senha
		Usuario usuarioLogado = (Usuario)sessao.getAttribute("usuarioLogado");
		
		if(form.getLogin() != null){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLogin()));
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if(!colecaoUsuario.isEmpty()){
				Usuario usuario = (Usuario)colecaoUsuario.iterator().next();
				try {
					//if(usuario.getDataNascimento() == null || form.getDataNascimento().trim().equals(Util.formatarData(usuario.getDataNascimento()))){
						usuario.setSenha(Criptografia.encriptarSenha("gsan"));
					// }else{
					// throw new
					// ActionServletException("atencao.data_nascimento.incorreta.login",
					// null, form.getLogin());
					//					}
				} catch (ErroCriptografiaException e) {
					e.printStackTrace();
				}
//				//------------ REGISTRAR TRANSA��O ----------------
//		        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//					Operacao.OPERACAO_USUARIO_ALTERAR_SENHA,
//					new UsuarioAcaoUsuarioHelper(usuarioLogado,
//				    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//			        
//			    Operacao operacao = new Operacao();
//			    operacao.setId(Operacao.OPERACAO_USUARIO_ALTERAR_SENHA);
//			
//			    OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//			    operacaoEfetuada.setOperacao(operacao);
//			    registradorOperacao.registrarOperacao(usuarioLogado);
//		    	//------------ REGISTRAR TRANSA��O ----------------
				
				// ------------ REGISTRAR TRANSA��O ----------------
				
				usuario.setUltimaAlteracao(new Date());
				
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				    Operacao.OPERACAO_USUARIO_ALTERAR_SENHA_LOGIN,
				    usuario.getId(), usuario.getId(),
				    new UsuarioAcaoUsuarioHelper(usuarioLogado,
				    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				registradorOperacao.registrarOperacao(usuario);

				// ------------ REGISTRAR TRANSA��O ----------------

				fachada.atualizar(usuario);
			}else{
				throw new ActionServletException("atencao.login_nao_existente",
						null, "" + form.getLogin() + "");
			}
		}
		
		//montando p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Senha padr�o para o login: "
				+ form.getLogin() + " gerada com sucesso.",
				"Alterar outra senha", "exibirEfetuarAlteracaoSenhaPorMatriculaAction.do?limparForm=ok");
		
		
		//Retornao mapeamento contido na vari�vel retorno
		return retorno;
	}
}
