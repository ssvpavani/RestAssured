package Utilities;

import java.util.ArrayList;
import java.util.List;

import pojoClasses.AddPlace;
import pojoClasses.Location;

public class TestDataBuild {
	public static AddPlace AddPlacePayload(String name,String language,String address)
	{
		AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        
        p.setLocation(l);
        p.setName(name);
        p.setPhone_number("(+91) 983 893 3937");
        
        List<String> typesList = new ArrayList<String>();
        typesList.add("shoe park");
        typesList.add("shop");
        
        p.setTypes(typesList);
        p.setWebsite("http://google.com");
        return p;
	}
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}

}
