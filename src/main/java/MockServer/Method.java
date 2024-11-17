package MockServer;

public enum Method {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");


    private final String method;

    // Constructor
    Method(String method) {
        this.method = method;
    }

    // Getter for the method name
    public String getMethod() {
        return method;
    }
}

