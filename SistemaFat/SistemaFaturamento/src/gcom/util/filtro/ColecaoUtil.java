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
package gcom.util.filtro;

import gcom.util.ErroRepositorioException;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Fun��es para facilitar a manipula��o de cole��es no repositorio
 * 
 * @author rodrigo
 */
public class ColecaoUtil {

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param nomeColecoes
     *            Descri��o do par�metro
     * @param colecaoDados
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     * @exception ErroRepositorioException
     *                Descri��o da exce��o
     */
    public static Collection processaColecoesParaCarregamento(
            Collection nomeColecoes, Collection colecaoDados)
            throws ErroRepositorioException {
        Iterator iteratorNomes = nomeColecoes.iterator();

        if (!nomeColecoes.isEmpty()) {

            while (iteratorNomes.hasNext()) {
                String nomeColecao = (String) iteratorNomes.next();
                Iterator iteratorDados = colecaoDados.iterator();

                while (iteratorDados.hasNext()) {
                    Object objetoDado = iteratorDados.next();

                    try {
                        nomeColecao = nomeColecao.substring(0, 1).toUpperCase()
                                + nomeColecao
                                        .substring(1, nomeColecao.length());

                        Collection colecao = ((Collection) objetoDado
                                .getClass()
                                .getMethod("get" + nomeColecao, (Class[])null).invoke(
                                        objetoDado, (Object[])null));

                        Iterator iterator = colecao.iterator();

                        iterator.next();
                    } catch (NoSuchElementException ex) {
                        //Caso a cole��o seja vazia

                        try {
                            objetoDado.getClass().getMethod(
                                    "set" + nomeColecao,
                                    new Class[] { Set.class }).invoke(
                                    objetoDado, (Object[])null);
                        } catch (SecurityException ex2) {
                            throw new ErroRepositorioException("erro.sistema");
                        } catch (NoSuchMethodException ex2) {
                            throw new ErroRepositorioException("erro.sistema");
                        } catch (InvocationTargetException ex2) {
                            throw new ErroRepositorioException("erro.sistema");
                        } catch (IllegalArgumentException ex2) {
                            throw new ErroRepositorioException("erro.sistema");
                        } catch (IllegalAccessException ex2) {
                            throw new ErroRepositorioException("erro.sistema");
                        }
                    } catch (SecurityException ex1) {
                        throw new ErroRepositorioException("erro.sistema");
                    } catch (NoSuchMethodException ex1) {
                        throw new ErroRepositorioException("erro.sistema");
                    } catch (InvocationTargetException ex1) {
                        throw new ErroRepositorioException("erro.sistema");
                    } catch (IllegalArgumentException ex1) {
                        throw new ErroRepositorioException("erro.sistema");
                    } catch (IllegalAccessException ex1) {
                        throw new ErroRepositorioException("erro.sistema");
                    }

                }

            }
        }
        return colecaoDados;
    }

}
