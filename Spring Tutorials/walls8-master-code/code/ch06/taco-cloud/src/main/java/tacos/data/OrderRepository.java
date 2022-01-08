package tacos.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import tacos.TacoOrder;
import tacos.User;

public interface OrderRepository 
         extends CrudRepository<TacoOrder, Long> {

  // tag::findByUser_paged[]
  List<TacoOrder> findByUserOrderByPlacedAtDesc(
          User user, Pageable pageable);
  // end::findByUser_paged[]

  /*
  // tag::findByUser[]
  List<Order> findByUserOrderByPlacedAtDesc(User user);
  // end::findByUser[]
   */

}
