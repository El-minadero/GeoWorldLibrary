package net.kevinmendoza.geoworldlibrary.geology.test;

import com.google.inject.AbstractModule;

import net.kevinmendoza.geoworldlibrary.geology.test.TestParameters.MapParameters;
import net.kevinmendoza.geoworldlibrary.geology.test.TestParameters.TestMapParameters;

public class ParameterBind extends AbstractModule {

	@Override
	protected void configure() {
		bind(MapParameters.class).to(TestMapParameters.class);
	}

}
