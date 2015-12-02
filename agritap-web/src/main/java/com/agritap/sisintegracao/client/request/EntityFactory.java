package com.agritap.sisintegracao.client.request;

<<<<<<< HEAD
import com.agritap.sisintegracao.client.request.beans.ProdutorI;
import com.agritap.sisintegracao.client.request.beans.ProdutorIAdapter;
import com.agritap.sisintegracao.client.request.beans.TabelaRacaoI;
import com.agritap.sisintegracao.client.request.beans.TabelaRacaoIAdapter;
import com.agritap.sisintegracao.client.request.beans.TecnicoI;
import com.agritap.sisintegracao.client.request.beans.TecnicoIAdapter;
=======
import com.agritap.sisintegracao.client.request.beans.ErrosI;
import com.agritap.sisintegracao.client.request.beans.PessoaI;
import com.agritap.sisintegracao.client.request.beans.PessoaIAdapter;
import com.agritap.sisintegracao.client.request.beans.RecebimentoI;
import com.agritap.sisintegracao.client.request.beans.RecebimentoIAdapter;
import com.agritap.sisintegracao.client.request.beans.TipoRacaoI;
import com.agritap.sisintegracao.client.request.beans.TipoRacaoIAdapter;
import com.agritap.sisintegracao.client.request.beans.UsuarioI;
>>>>>>> a5369d748f4a44296e001de459c4d495c8e9922b
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface EntityFactory extends AutoBeanFactory {
	public static String PREFIX = "application/vnd.agritap.v1.entity.";

<<<<<<< HEAD
	AutoBean<ProdutorI> newProdutor();
	AutoBean<ProdutorIAdapter> newProdutores();
	AutoBean<TabelaRacaoI> newTabelaRacao();
	AutoBean<TabelaRacaoIAdapter> newTabelaRacaoAdapter();
	AutoBean<TecnicoI> newTecnico();
	AutoBean<TecnicoIAdapter> newTecnicoAdapter();
=======

	AutoBean<TipoRacaoI> newTipoRacao();

	AutoBean<TipoRacaoIAdapter> newTipoRacoes();

	AutoBean<PessoaI> newProdutor();

	AutoBean<PessoaIAdapter> newProdutores();
	
	AutoBean<ErrosI> newErros();
	
	AutoBean<UsuarioI> newUsuario();

	AutoBean<RecebimentoI> newRecebimento();

	AutoBean<RecebimentoIAdapter> newRecebimentos();

>>>>>>> a5369d748f4a44296e001de459c4d495c8e9922b
	
}
