package co.du.pay.vyne.controller;

public interface ControllerApiHelper {

    // Paths
    String BASE_PATH = "/transaction";
    String CREATE = "/create";
    String RETRIEVE = "/retrieve/{id}";
    String UPDATE = "/update/{id}";
    String DELETE = "/delete/{id}";

    // Headers for versioning
    String V1_HEADER = "application/co.du.pay.vyne.api.v1+json";
    String V2_HEADER = "application/co.du.pay.vyne.api.v2+json";

}
