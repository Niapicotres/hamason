package com.core.hamason.serviceImpl;

import java.sql.SQLException;

/*
 * Para que se pueda importar la clase Script de h2, hay que comentar 
 * el <scope> de su dependencia en pom.xml:
  		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<!--
			<scope>runtime</scope>
			-->
		</dependency>
 */
import org.h2.tools.Script;


import org.springframework.stereotype.Service;

import com.core.hamason.service.ISqlScriptCreatorService;

import lombok.extern.slf4j.Slf4j;

// Clase (service) para crear el script de sql,
// alternativa a la opción mediante application.properties.
// Incluye los datos mediante claúsulas 'insert into...'.

@Service
@Slf4j
public class SqlScriptCreatorServiceImpl implements ISqlScriptCreatorService {
	
//	@PersistenceContext
//	private EntityManager entityManager;

	@Override
	public void dumpDB() {
        try {
			Script.process("jdbc:h2:mem:eventix", 
					"sas", "sas", "sql/dump.sql", "", "");
		} catch (SQLException e) {
			log.info(e.getMessage());
			//e.printStackTrace();
		}
	}

}

