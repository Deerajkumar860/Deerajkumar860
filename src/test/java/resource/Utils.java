package resource;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification req;

    public String globalConfig(String url) throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("C:\\Users\\DEERAJ KUMAR\\IdeaProjects\\Api automation\\src\\test\\java\\resource\\global-config.properties");
        prop.load(file);
        return prop.getProperty(url);
    }
    public RequestSpecification requestSpecification() throws IOException
    {
        if (req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
            req = new RequestSpecBuilder().setBaseUri(globalConfig("baseURL"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public String getJsonPath(Response response, String key){
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }


}
