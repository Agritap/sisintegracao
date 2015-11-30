package com.agritap.sisintegracao;

import java.io.IOException;


public class JPAExchemaExport {

	public static void main(String[] args) throws IOException {
//		execute(args[0], args[1]);
		execute("sisIntegracaoUnit", "target/sql/create-schema2.sql");
//		System.exit(0);
	}

	public static void execute(String persistenceUnitName, String destination) {
		System.out.println("Generating DDL create script to : " + destination);

//		final Properties persistenceProperties = new Properties();
//		persistenceProperties.setProperty(org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO, "");
//		persistenceProperties.setProperty(AvailableSettings.SCHEMA_GEN_DATABASE_ACTION, "none");
//		persistenceProperties.setProperty(AvailableSettings.SCHEMA_GEN_SCRIPTS_ACTION, "create");
//		persistenceProperties.setProperty(AvailableSettings.SCHEMA_GEN_CREATE_SOURCE, "metadata");
//		persistenceProperties.setProperty(AvailableSettings.SCHEMA_GEN_SCRIPTS_CREATE_TARGET, destination);
//
//		Persistence.generateSchema(persistenceUnitName, persistenceProperties);
	}
	
}
