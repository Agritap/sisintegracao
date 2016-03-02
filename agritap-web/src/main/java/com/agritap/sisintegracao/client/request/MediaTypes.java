package com.agritap.sisintegracao.client.request;

import java.util.HashMap;
import java.util.Map;

import com.agritap.sisintegracao.client.request.beans.ErrosI;
import com.agritap.sisintegracao.client.request.beans.ModulosI;
import com.agritap.sisintegracao.client.request.beans.ModulosIAdapter;
import com.agritap.sisintegracao.client.request.beans.TabelaRacaoI;
import com.agritap.sisintegracao.client.request.beans.TabelaRacaoIAdapter;
import com.agritap.sisintegracao.client.request.beans.TipoRacaoI;
import com.agritap.sisintegracao.client.request.beans.TipoRacaoIAdapter;
/**
 * 
 * Vergonhosamente copiado de 
 * https://github.com/Craftware/Kornell
 * 
 *
 */
public class MediaTypes {

	static final MediaTypes instance = new MediaTypes();

	Map<String, Class<?>> type2class = new HashMap<String, Class<?>>();
	Map<Class<?>, String> class2type = new HashMap<Class<?>, String>();

	public MediaTypes() {
		//TODO: Consider using a GWT Generator instead of manual declaration
		registerEntities();
		registerTOs();
	}

	private void registerEntities() {
		register(TabelaRacaoI.TYPE, TabelaRacaoI.class);
		register(TipoRacaoI.TYPE, TipoRacaoI.class);
		register(ModulosI.TYPE, ModulosI.class );
	}


	private void registerTOs() {
		register(TabelaRacaoIAdapter.TYPE, TabelaRacaoIAdapter.class);
//		register(EnrollmentLaunchTO.TYPE, EnrollmentLaunchTO.class);
//		// When auth filter sends 401, it adds the charset and we can't do
//		// anything about it
		register(ErrosI.TYPE,ErrosI.class);
		register(TipoRacaoIAdapter.TYPE, TipoRacaoIAdapter.class);
		register(ModulosIAdapter.TYPE, ModulosIAdapter.class);

	}

	private void register(String type, Class<?> clazz) {
		type2class.put(type.toLowerCase(), clazz);
		//Maldito tartaruga em cima de uma arvore. pq esse lowercase?
		class2type.put(clazz, type.toLowerCase());
	}

	public static MediaTypes get() {
		return instance;
	}

	public Class<?> classOf(String type) {
		if(type==null){
			return null;
		}
		return type2class.get(type.toLowerCase());
	}

	public String typeOf(Class<?> clazz) {
		return class2type.get(clazz);
	}

	public boolean containsType(String type) {
		if(type==null){
			return false;
		}
		return type2class.containsKey(type.toLowerCase());
	}

	public boolean containsClass(Class<?> clazz) {
		return class2type.containsKey(clazz);
	}
}
