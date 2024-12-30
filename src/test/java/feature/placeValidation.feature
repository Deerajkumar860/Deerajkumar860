Feature: To verify Add place API

  @addPlace
  Scenario Outline: Verify place api using add place API
    Given Add Place payload with "<Accurancy>" "<address>" "<language>"
    When user calls "AddPlaceAPI" with "Post" http request
#    When user calls "getPlaceAPI" with "GET" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<language>" with "getPlaceAPI"

  Examples:
    |Accurancy| address | language |
    |50       |Malancha |Telegu    |
#    |65       |Bhagwanpur|Hindi    |

  @deletePlace
  Scenario: Delete place api using delete method
    Given deletePlace payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"