package gcom.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC0339] Consultar Dados Di�rios da Arrecada��o
 * 
 * Gerar Relat�rio Dados Di�rios da Arrecada��o - Agente
 * 
 * @author Mariana Victor
 * @date 03/02/2011
 */
public class RelatorioDadosDiariosAgente extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioDadosDiariosAgente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_AGENTE);
	}

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		BigDecimal valorTotal = null;
		String referencia = null;
		String dadosMesInformado = null;
		String dadosAtual = null;
		String faturamentoCobradoEmConta = null;
		Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = null;

		if (getParametro("valorTotal") != null) {
			valorTotal = (BigDecimal) getParametro("valorTotal");
		}
		if (getParametro("referencia") != null) {
			referencia = (String) getParametro("referencia");
		}
		if (getParametro("dadosMesInformado") != null) {
			dadosMesInformado = (String) getParametro("dadosMesInformado");
		}
		if (getParametro("dadosAtual") != null) {
			dadosAtual = (String) getParametro("dadosAtual");
		}
		if (getParametro("faturamentoCobradoEmConta") != null) {
			faturamentoCobradoEmConta = (String) getParametro("faturamentoCobradoEmConta");
		}
		if (getParametro("colecaoDadosDiarios") != null) {
			colecaoDadosDiarios = (Collection<FiltrarDadosDiariosArrecadacaoHelper>) getParametro("colecaoDadosDiarios");
		}
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();

		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		// Usuario que emite o relatorio
		Usuario usuario = this.getUsuario();

		String nomeUsuario = usuario.getNomeUsuario();
		
		parametros.put("nomeUsuario", nomeUsuario);
		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("enderecoEmpresa", sistemaParametro.getEnderecoFormatado());
		parametros.put("cepEmpresa", sistemaParametro.getCep().getCepFormatado());
		parametros.put("telefoneGeral", sistemaParametro.getNumeroTelefone());
		
		Collection<RelatorioDadosDiariosAgenteBean> colecaoBean = this
			.inicializarBeanRelatorio(valorTotal, referencia, dadosMesInformado,
					dadosAtual, faturamentoCobradoEmConta, colecaoDadosDiarios);

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this
				.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_AGENTE,
						parametros, ds, tipoFormatoRelatorio);

		// retorna o relat�rio gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDadosDiariosAgente", this);
	}
	
	private Collection<RelatorioDadosDiariosAgenteBean> inicializarBeanRelatorio(
			BigDecimal valorTotal, String referencia, String dadosMesInformado,
			String dadosAtual, String faturamentoCobradoEmConta,
			Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios){
		
		Collection<RelatorioDadosDiariosAgenteBean> colecaoBean = new ArrayList<RelatorioDadosDiariosAgenteBean>();

		Integer qtdTotalDocumentos = 0;
		Integer qtdTotalPagamentos = 0;
		BigDecimal valorTotalDebitosBancos = new BigDecimal("0.0");
		BigDecimal valorTotalDescontosBancos = new BigDecimal("0.0");
		BigDecimal valorTotalArrecadadoBancos = new BigDecimal("0.0");
		BigDecimal valorTotalDevolucoesBancos = new BigDecimal("0.0");
		BigDecimal valorTotalLiquidoBancos = new BigDecimal("0.0");
		
		if (colecaoDadosDiarios != null && !colecaoDadosDiarios.isEmpty()){
			Iterator iterator = colecaoDadosDiarios.iterator();
			
			while (iterator.hasNext()) {
				FiltrarDadosDiariosArrecadacaoHelper itemHelper = (FiltrarDadosDiariosArrecadacaoHelper) iterator.next();
				RelatorioDadosDiariosAgenteBean bean = new RelatorioDadosDiariosAgenteBean();
				BigDecimal valorArrecadado = itemHelper.getValorArrecadacao();
				
				Arrecadador arrecadador = (Arrecadador) itemHelper.getItemAgrupado();
				
				qtdTotalDocumentos += itemHelper.getQuantidadeDocumentos();
				qtdTotalPagamentos += itemHelper.getQuantidadePagamentos();
				valorTotalDebitosBancos = valorTotalDebitosBancos.add(itemHelper.getValorDebitos());
				valorTotalDescontosBancos = valorTotalDescontosBancos.add(itemHelper.getValorDescontos());
				valorTotalArrecadadoBancos = valorTotalArrecadadoBancos.add(valorArrecadado);
				valorTotalDevolucoesBancos = valorTotalDevolucoesBancos.add(itemHelper.getValorDevolucoes());						
				valorTotalLiquidoBancos = valorTotalLiquidoBancos.add(itemHelper.getValorArrecadacaoLiquida());					
				
				bean.setProcessamentoDefinitivo(dadosMesInformado);
				bean.setMesAno(Util.formatarAnoMesParaMesAno(referencia));
				bean.setUltimoProcessamentoAtual(dadosAtual);
				bean.setFaturamentoCobradoEmConta(faturamentoCobradoEmConta);
				bean.setValor(Util.formatarMoedaReal(valorTotal));
				
				bean.setBanco(arrecadador.getCliente().getNome());
				bean.setQuantidadeDocumentos(Util.agruparNumeroEmMilhares(itemHelper.getQuantidadeDocumentos()));
				bean.setQuantidadePagamentos(Util.agruparNumeroEmMilhares(itemHelper.getQuantidadePagamentos()));
				bean.setDebitos(Util.formatarMoedaReal(itemHelper.getValorDebitos()));
				bean.setDescontos(Util.formatarMoedaReal(itemHelper.getValorDescontos()));
				bean.setValorArrecadado(Util.formatarMoedaReal(itemHelper.getValorArrecadacao()));
				bean.setDevolucao(Util.formatarMoedaReal(itemHelper.getValorDevolucoes()));
				bean.setArrecadacaoLiquida(Util.formatarMoedaReal(itemHelper.getValorArrecadacaoLiquida()));
				bean.setPercentualMes(Util.formatarMoedaReal(itemHelper.getPercentual()));
				
				colecaoBean.add(bean);
			}
			
			RelatorioDadosDiariosAgenteBean bean = new RelatorioDadosDiariosAgenteBean();
			
			bean.setProcessamentoDefinitivo(dadosMesInformado);
			bean.setMesAno(Util.formatarAnoMesParaMesAno(referencia));
			bean.setUltimoProcessamentoAtual(dadosAtual);
			bean.setFaturamentoCobradoEmConta(faturamentoCobradoEmConta);
			bean.setValor(Util.formatarMoedaReal(valorTotal));
			
			bean.setBanco("TODOS");
			bean.setQuantidadeDocumentos(Util.agruparNumeroEmMilhares(qtdTotalDocumentos));
			bean.setQuantidadePagamentos(Util.agruparNumeroEmMilhares(qtdTotalPagamentos));
			bean.setDebitos(Util.formatarMoedaReal(valorTotalDebitosBancos));
			bean.setDescontos(Util.formatarMoedaReal(valorTotalDescontosBancos));
			bean.setValorArrecadado(Util.formatarMoedaReal(valorTotalArrecadadoBancos));
			bean.setDevolucao(Util.formatarMoedaReal(valorTotalDevolucoesBancos));
			bean.setArrecadacaoLiquida(Util.formatarMoedaReal(valorTotalLiquidoBancos));
			bean.setPercentualMes("100,00 %");
			
			colecaoBean.add(bean);
		}
		
		return colecaoBean;
	}
}
