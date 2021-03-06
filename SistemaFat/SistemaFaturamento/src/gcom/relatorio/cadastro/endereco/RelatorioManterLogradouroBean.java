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
package gcom.relatorio.cadastro.endereco;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corr�a
 * @version 1.0
 */

public class RelatorioManterLogradouroBean implements RelatorioBean {
    private String codigo;

    private String nome;

    private String titulo;

    private String tipo;

    private String municipio;

    private String indicadorUso;

    private String bairro;

    /**
     * Construtor da classe RelatorioManterLogradouroBean
     * 
     * @param codigo
     *            Descri��o do par�metro
     * @param nome
     *            Descri��o do par�metro
     * @param titulo
     *            Descri��o do par�metro
     * @param tipo
     *            Descri��o do par�metro
     * @param municipio
     *            Descri��o do par�metro
     * @param indicadorUso
     *            Descri��o do par�metro
     */

    public RelatorioManterLogradouroBean(String codigo, String nome,
            String titulo, String tipo, String municipio, String bairro,
            String indicadorUso) {
        this.codigo = codigo;
        this.nome = nome;
        this.titulo = titulo;
        this.tipo = tipo;
        this.municipio = municipio;
        this.bairro = bairro;
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de codigo
     * 
     * @return O valor de codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Seta o valor de codigo
     * 
     * @param codigo
     *            O novo valor de codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Seta o valor de municipio
     * 
     * @param municipio
     *            O novo valor de municipio
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * Seta o valor de indicadorUso
     * 
     * @param indicadorUso
     *            O novo valor de indicadorUso
     */
    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     */
    public String getIndicadorUso() {
        return indicadorUso;
    }

    /**
     * Retorna o valor de municipio
     * 
     * @return O valor de municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Retorna o valor de tipo
     * 
     * @return O valor de tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Retorna o valor de nome
     * 
     * @return O valor de nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o valor de titulo
     * 
     * @return O valor de titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Seta o valor de nome
     * 
     * @param nome
     *            O novo valor de nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Seta o valor de tipo
     * 
     * @param tipo
     *            O novo valor de tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Seta o valor de titulo
     * 
     * @param titulo
     *            O novo valor de titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

}
