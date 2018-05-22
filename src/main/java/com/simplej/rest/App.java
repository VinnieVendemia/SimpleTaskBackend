package com.simplej.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.simplej.rest.controller.EventRESTController;
import com.simplej.rest.controller.ProjectRESTController;
import com.simplej.rest.dao.EventDB;
import com.simplej.rest.entity.Event;
import com.simplej.rest.entity.Project;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	private final HibernateBundle<AppConfiguration> hibernate = new HibernateBundle<AppConfiguration>(Event.class,
			Project.class) {
		@Override
		public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};

	private final MigrationsBundle<AppConfiguration> migration = new MigrationsBundle<AppConfiguration>() {
		@Override
		public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};

	@Override
	public void initialize(Bootstrap<AppConfiguration> b) {
		b.addBundle(hibernate);
		b.addBundle(migration);
	}

	@Override
	public void run(AppConfiguration c, Environment e) throws Exception {
		LOGGER.info("Registering REST resources");
		e.jersey().register(new ProjectRESTController(e.getValidator()));

		final EventDB dao = new EventDB(hibernate.getSessionFactory());
		e.jersey().register(new EventRESTController(e.getValidator(), dao));
	}

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
}
