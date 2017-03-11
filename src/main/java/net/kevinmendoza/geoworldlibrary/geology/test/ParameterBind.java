package net.kevinmendoza.geoworldlibrary.geology.test;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

public class ParameterBind extends AbstractModule implements Module {

	@Override
	protected void configure() {
		bind(ObjectParameters.class).to(Object1Parameters.class);
		bind(ObjectParameters2.class).to(Object2Parameters.class);
	}

}
