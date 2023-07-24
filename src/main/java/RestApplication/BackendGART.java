package RestApplication;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { 
		"GARTApiV1Component",
		"GARTApiV1AasComponent",
		"GARTApiV1SubmodelComponent",
		"GARTApiV1SubmodelBomComponent"
		} )
public abstract class BackendGART {
		public BackendGART() {
		
	}
}