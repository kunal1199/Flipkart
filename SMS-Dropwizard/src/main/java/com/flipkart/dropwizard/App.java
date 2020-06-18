package com.flipkart.dropwizard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipkart.restClient.CourseRestClient;
import com.flipkart.restClient.UserRestClient;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

//main dropwizard class to initialize and configure the server
public class App extends Application<Configuration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	 
	//initialize the server
    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    //specify the rest clients and rescources
	@Override
	public void run(Configuration c, Environment e) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Registering REST resources");
        e.jersey().register(new CourseRestClient());
        e.jersey().register(new UserRestClient());
	}
	
	//main method that calls the run method
	public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
