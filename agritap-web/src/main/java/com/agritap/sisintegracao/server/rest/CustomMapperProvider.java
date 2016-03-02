package com.agritap.sisintegracao.server.rest;

import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomMapperProvider implements ContextResolver<ObjectMapper> {

	private final ObjectMapper mObjectMapper;

	public CustomMapperProvider(){
		mObjectMapper = createMapper();
	}
	
	@Override
    public ObjectMapper getContext(Class<?> type) {
        return mObjectMapper;
    }
	
	protected  ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper= mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

        SimpleModule module = new SimpleModule();
        module.addSerializer(java.sql.Date.class, new SqlDateSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
