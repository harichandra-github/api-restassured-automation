package filters;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggingFilters implements Filter{
    private final Logger logger=LogManager.getLogger(LoggingFilters.class);

    /**
     * This method intercepts the request & response to log full details.
     * This method is used to filter the request and response for logging purposes.
     * It can be customized to log request and response details as needed.
     *
     * @param requestSpec The request specification to be filtered.
     * @param responseSpec The response specification to be filtered.
     * @param filterContext The context of the filter.
     * @return The response after filtering.
     */
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        logRequest(requestSpec);

        long startTime = System.currentTimeMillis();
        Response response = filterContext.next(requestSpec, responseSpec);
        long endTime = System.currentTimeMillis();

        logResponse(response, endTime - startTime);

        return response;
    }

    /**
     * Logs detailed request information.
     */

    private void logRequest(FilterableRequestSpecification requestSpec) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==================== API REQUEST ====================\n")
                .append("Method      : ").append(requestSpec.getMethod()).append("\n")
                .append("URI         : ").append(requestSpec.getURI()).append("\n");


        if (!requestSpec.getHeaders().asList().isEmpty()) {
            sb.append("Headers     : ").append(requestSpec.getHeaders()).append("\n");
        }
        if (!requestSpec.getQueryParams().isEmpty()) {
            sb.append("Query Params: ").append(requestSpec.getQueryParams()).append("\n");
        }
        if (requestSpec.getBody() != null) {
            sb.append("Body        : ").append(requestSpec.getBody().toString()).append("\n");
        }

        sb.append("======================================================");
        logger.info(sb.toString());
    }

    /**
     * Logs detailed response information.
     */
    private void logResponse(Response response, long timeTaken) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==================== API RESPONSE ===================\n")
                .append("Status Code : ").append(response.getStatusCode()).append("\n")
                .append("Status Line : ").append(response.getStatusLine()).append("\n")
                .append("Time Taken  : ").append(timeTaken).append(" ms\n");

        if (!response.getHeaders().asList().isEmpty()) {
            sb.append("Headers     : ").append(response.getHeaders()).append("\n");
        }

        // Extract and log "message" from JSON if available
        try {
            String message = response.jsonPath().getString("message");
            if (message != null) {
                sb.append("Message     : ").append(message).append("\n");
            }
        } catch (Exception e) {
            sb.append("Message     : [Not a JSON or 'message' field missing]\n");
        }

        if (response.getBody() != null && !response.getBody().asPrettyString().isEmpty()) {
            sb.append("Body        : ").append(response.getBody().asPrettyString()).append("\n");
        }

        sb.append("======================================================");
        logger.info(sb.toString());
    }
}




