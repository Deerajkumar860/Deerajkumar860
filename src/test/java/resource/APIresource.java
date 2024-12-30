package resource;
//enum class is special class in java which has collection of constants or methods.
public enum APIresource {

    AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
    private final String resource;
    APIresource(String resource){
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }
}
