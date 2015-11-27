package com.agritap.sisintegracao.client.request;

<<<<<<< HEAD
import com.agritap.sisintegracao.client.request.beans.TipoRacaoI;
import com.agritap.sisintegracao.client.request.beans.TipoRacaoIAdapter;
import com.agritap.sisintegracao.client.request.beans.ProdutorI;
import com.agritap.sisintegracao.client.request.beans.ProdutorIAdapter;
import com.agritap.sisintegracao.client.request.beans.RecebimentoI;
import com.agritap.sisintegracao.client.request.beans.RecebimentoIAdapter;
=======
import com.agritap.sisintegracao.client.request.beans.ErrosI;
import com.agritap.sisintegracao.client.request.beans.PedidosI;
import com.agritap.sisintegracao.client.request.beans.PedidosIAdapter;
import com.agritap.sisintegracao.client.request.beans.PessoaI;
import com.agritap.sisintegracao.client.request.beans.PessoaIAdapter;
import com.agritap.sisintegracao.client.request.beans.UsuarioI;
>>>>>>> Adiciona tela de login.
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface EntityFactory extends AutoBeanFactory {
	public static String PREFIX = "application/vnd.agritap.v1.entity.";

<<<<<<< HEAD
	AutoBean<ProdutorI> newProdutor();

	AutoBean<ProdutorIAdapter> newProdutores();

	AutoBean<TipoRacaoI> newTipoRacao();

	AutoBean<TipoRacaoIAdapter> newTipoRacoes();

	AutoBean<RecebimentoI> newRecebimento1();

	AutoBean<RecebimentoIAdapter> newRecebimentos();

=======
	AutoBean<PessoaI> newProdutor();
	AutoBean<PessoaIAdapter> newProdutores();
	AutoBean<PedidosI> newPedidos();
	AutoBean<PedidosIAdapter> newPedidosAdapter();
	AutoBean<ErrosI> newErros();
	AutoBean<UsuarioI> newUsuario();

	AutoBean<TipoRacaoI> newTipoRacao();

	AutoBean<TipoRacaoIAdapter> newTipoRacoes();

	AutoBean<RecebimentoI> newRecebimento1();

	AutoBean<RecebimentoIAdapter> newRecebimentos();

	
>>>>>>> Adiciona tela de login.
}
