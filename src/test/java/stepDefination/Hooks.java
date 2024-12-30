package stepDefination;

import io.cucumber.java.*;

import java.io.IOException;

public class Hooks {
    @Before("@deletePlace")
    public void beforeScenario() throws IOException {
        MyStepdefs step = new MyStepdefs();
        if (MyStepdefs.place_id==null) {
            step.add_place_payload_with("74", "Malancha,Bhagwanpur", "Telugu/Hindi");
            step.userCallsWithPostHttpRequest("AddPlaceAPI", "POST");
            step.verifyPlace_idCreatedMapsToWith("Telugu/Hindi", "getPlaceAPI");
        }
    }
}
