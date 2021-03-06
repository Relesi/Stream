package gcom.relatorio.cadastro;

import gcom.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioMensagensPendentesCadastrador extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioMensagensPendentesCadastrador(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MENSAGENS_PENDENTES_CADASTRADOR);
	}
	
	@Override
	public Object executar() throws TarefaException {
		//Valor de Retorno
		byte [] retorno = null;
		
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		String nomeGerencia = (String) getParametro("nomeGerencia");
		String nomeUnidadeNegocio = (String)getParametro("nomeUnidadeNegocio");
		String codSetorInicial = (String) getParametro("codigoSetorInicial");
		String quadraInicial = (String) getParametro("quadraInicial");
		String cadastrador = (String) getParametro("cadastrador");
		String analista = (String) getParametro("analista");
		String tipoInconsistencia = (String) getParametro("tipoInconsistencia");

		
		Integer tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		DadosResumoMovimentoAtualizacaoCadastralHelper helper = 
			(DadosResumoMovimentoAtualizacaoCadastralHelper) getParametro("dadosRelatorio");
		
		// cole��o de beans do relat�rio
		List<Object> relatorioBeans = new ArrayList<Object>();
		
		Collection<RelatorioMensagensPendentesCadastradorBean> colecao =
				Fachada.getInstancia().pesquisarResumoQuantitativoMensagensPendentesCadastrador(helper);
		
		BigDecimal quantidadeTotalInconsistencia = new BigDecimal(0);
		
		if(!Util.isVazioOrNulo(colecao)){
			Iterator<?> iterator = colecao.iterator();
			while(iterator.hasNext()){
				RelatorioMensagensPendentesCadastradorBean bean = 
					(RelatorioMensagensPendentesCadastradorBean) iterator.next();
				quantidadeTotalInconsistencia = quantidadeTotalInconsistencia.add(bean.getQuantidadeImoveis());
				relatorioBeans.add(bean);
			}
		}else{
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		//Parametros do Relatorio
		Map<Object, Object> parametros = new HashMap<Object, Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		//Adiciona os parametros no relat�rio
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("nomeGerencia", nomeGerencia);
		parametros.put("nomeUnidadeNegocio",nomeUnidadeNegocio);
		parametros.put("codigoSetorInicial", codSetorInicial);
		parametros.put("quadraInicial", quadraInicial);		
		parametros.put("cadastrador", cadastrador);
		parametros.put("analista", analista);
		parametros.put("quantidadeTotalInconsistencia", quantidadeTotalInconsistencia);
		parametros.put("tipoInconsistencia", tipoInconsistencia);
		parametros.put("tipoFormatoRelatorio", "R1313");
		
		//Cria uma instancia do dataSource do Relatorio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MENSAGENS_PENDENTES_CADASTRADOR, 
				parametros, ds, tipoFormatoRelatorio);
		
		//Retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMensagensPendentesCadastrador", this);
	}

}