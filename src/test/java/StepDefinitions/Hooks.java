package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//write the code that will give you place id
		//execute  this code only place id is null
		PlaceHttpStepDefinitions m=new PlaceHttpStepDefinitions();
		if(PlaceHttpStepDefinitions.place_id==null)
		{
			m.add_place_payload("Nani", "Hindi", "Asia");
			m.user_calls_with_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Nani", "getPlaceAPI");
		
		}
	}
}
