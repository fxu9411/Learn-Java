//tag::allButTransient[]
package tacos;

//end::allButTransient[]
import java.util.ArrayList;
//tag::allButTransient[]
import java.util.LinkedHashSet;
//end::allButTransient[]
import java.util.List;
//tag::allButTransient[]
import java.util.Set;
import org.springframework.data.annotation.Id;
//end::allButTransient[]
import org.springframework.data.annotation.Transient;
//tag::allButTransient[]
import lombok.Data;

//tag::transientTacos[]
@Data
public class TacoOrder {
  //end::transientTacos[]
  //end::allButTransient[]
  /*
    //tag::transientTacos[]

  ...
  
    //end::transientTacos[]
   */
  //tag::allButTransient[]
  
  @Id
  private Long id;
  
  private String deliveryName;
  private String deliveryStreet;
  private String deliveryCity;
  private String deliveryState;
  private String deliveryZip;
  private String ccNumber;
  private String ccExpiration;
  private String ccCVV;
  
  private Set<Long> tacoIds = new LinkedHashSet<>();

//end::allButTransient[]
  //tag::transientTacos[]
  @Transient
  private transient List<Taco> tacos = new ArrayList<>();

  //end::transientTacos[]
  /*
  //tag::allButTransient[]
  private List<Taco> tacos = new ArrayList<>();
  //end::allButTransient[]
  */
  //tag::transientTacos[]
  //tag::allButTransient[]
  public void addTaco(Taco taco) {
    this.tacos.add(taco);
  //end::allButTransient[]
    if (taco.getId() != null) {
      this.tacoIds.add(taco.getId());
    }
  //tag::allButTransient[]
  }

}
//end::transientTacos[]
//end::allButTransient[]