package com.agritap.sisintegracao.client.request.clients;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.fusesource.restygwt.client.RestService;

import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.ui.configuracao.LoteViewVO;
import com.agritap.sisintegracao.client.vo.LoteVO;
import com.agritap.sisintegracao.model.Lote;

@Path("/lotes")
public interface LoteClient extends RestService{

	@GET
	@Path("/view")
	public void get(@QueryParam("id") Integer id,RestCallback<LoteViewVO> cb);

	@PUT
	@Path("/salvar")
	public void salvar(Lote lote,RestCallback<Lote> cb);
	
	@GET
	@Path("/buscar")
	public void buscar(@QueryParam("pesquisa") String pesquisa,RestCallback<List<LoteVO>> cb);



}
