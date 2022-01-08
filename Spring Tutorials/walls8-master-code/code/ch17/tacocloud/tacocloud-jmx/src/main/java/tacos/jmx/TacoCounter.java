// tag::simpleMBean[]
// tag::withNotifications[]
package tacos.jmx;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
//end::simpleMBean[]

import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import javax.management.Notification;

//tag::simpleMBean[]
import tacos.Taco;
import tacos.data.TacoRepository;

@Service
@ManagedResource
public class TacoCounter
//end::simpleMBean[]
//end::withNotifications[]
/*
//tag::simpleMBean[]
       extends AbstractRepositoryEventListener<Taco> {
//end::simpleMBean[]
*/
//tag::withNotifications[]
       extends AbstractRepositoryEventListener<Taco> 
       implements NotificationPublisherAware {

  //tag::simpleMBean[]
    
  private AtomicLong counter;
  //end::simpleMBean[]
  private NotificationPublisher np;
  
  @Override
  public void setNotificationPublisher(NotificationPublisher np) {
    this.np = np;
  }
  
//end::withNotifications[]
//tag::simpleMBean[]
  public TacoCounter(TacoRepository tacoRepo) {
    tacoRepo
        .count()
        .subscribe(initialCount -> {
            this.counter = new AtomicLong(initialCount);
        });
  }
  
  @Override
  protected void onAfterCreate(Taco entity) {
    counter.incrementAndGet();
  }
  
  @ManagedAttribute
  public long getTacoCount() {
    return counter.get();
  }
  
//end::simpleMBean[]
  
  /*
  // tag::simpleMBean[]
  @ManagedOperation
  public long increment(long delta) {
    return counter.addAndGet(delta);
  }
  // end::simpleMBean[]
  */
  
  /*
// tag::withNotifications[]
  ...
  
// end::withNotifications[]
   */
  
//tag::withNotifications[]
  @ManagedOperation
  public long increment(long delta) {
    long before = counter.get();
    long after = counter.addAndGet(delta);
    if ((after / 100) > (before / 100)) {
      Notification notification = new Notification(
          "taco.count", this,
          before, after + "th taco created!");
      np.sendNotification(notification);
    }
    return after;
  }
  // tag::simpleMBean[]
  
}
// end::simpleMBean[]
//end::withNotifications[]