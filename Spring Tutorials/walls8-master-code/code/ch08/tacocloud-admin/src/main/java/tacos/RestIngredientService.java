// tag::RestIngredientService_handlers[]
package tacos;

// end::RestIngredientService_handlers[]
import java.io.IOException;
// tag::RestIngredientService_handlers[]
import java.util.Arrays;
// end::RestIngredientService_handlers[]
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
// tag::RestIngredientService_handlers[]
import org.springframework.web.client.RestTemplate;

public class RestIngredientService implements IngredientService {

  private RestTemplate restTemplate;

  // end::RestIngredientService_handlers[]
  /*
  // tag::RestIngredientService_handlers[]
  public RestIngredientService() {
  // end::RestIngredientService_handlers[]
   */

  //tag::RestIngredientService[]
  public RestIngredientService(String accessToken) {
  // tag::RestIngredientService_handlers[]
    this.restTemplate = new RestTemplate();
    // end::RestIngredientService_handlers[]
    if (accessToken != null) {
      this.restTemplate
          .getInterceptors()
          .add(getBearerTokenInterceptor(accessToken));
    }
    // tag::RestIngredientService_handlers[]
  }
  // end::RestIngredientService[]

  @Override
  public Iterable<Ingredient> findAll() {
    return Arrays.asList(restTemplate.getForObject(
            "http://localhost:8080/api/ingredients",
            Ingredient[].class));
  }

  @Override
  public Ingredient addIngredient(Ingredient ingredient) {
    return restTemplate.postForObject(
        "http://localhost:8080/api/ingredients", 
        ingredient,
        Ingredient.class);
  }
  // end::RestIngredientService_handlers[]

// tag::RestIngredientService[]
  private ClientHttpRequestInterceptor
            getBearerTokenInterceptor(String accessToken) {
    ClientHttpRequestInterceptor interceptor =
          new ClientHttpRequestInterceptor() {
      @Override
      public ClientHttpResponse intercept(
            HttpRequest request, byte[] bytes,
            ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("Authorization", "Bearer " + accessToken);
        return execution.execute(request, bytes);
      }
    };

    return interceptor;
  }
//end::RestIngredientService[]
// tag::RestIngredientService_handlers[]

}
// end::RestIngredientService_handlers[]
