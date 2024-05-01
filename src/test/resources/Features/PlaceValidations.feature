Feature: validating Place API's
@AddPlace
Scenario Outline: Verify if place is being Successfully added using AddPlace API
	Given Add Place payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body in "OK"
	And "scope" in response body in "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	Examples:
	|name		|	language	|	address						|
	|Sasi		|	English	  |	World cross center|
	#|Sathi	|	Hindi	    |	Sea cross center	|
	
	@DeletePlace
	Scenario: Verify if Delete place functionality is working
		Given DeletePlace payload
		When User calls "deletePlaceAPI" with "POST" http request
		Then the API call is success with status code 200
		And "status" in response body in "OK"
		