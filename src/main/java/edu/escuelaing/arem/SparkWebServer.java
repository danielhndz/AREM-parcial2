package edu.escuelaing.arem;

import static spark.Spark.*;

import edu.escuelaing.arem.services.CollatzService;
import spark.Request;
import spark.Response;

public class SparkWebServer {
    public static void main(String[] args) {
        port(getPort());
        staticFileLocation("/");
        get("/collatzsequence", SparkWebServer::collatzsequence);
        post("/collatzsequence", SparkWebServer::collatzsequence);
    }

    private static String collatzsequence(Request req, Response res) {
        res.type("application/json");
        return CollatzService
                .getInstance()
                .getResponse(
                        Integer.parseInt(
                                req.queryParams("value")));
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
