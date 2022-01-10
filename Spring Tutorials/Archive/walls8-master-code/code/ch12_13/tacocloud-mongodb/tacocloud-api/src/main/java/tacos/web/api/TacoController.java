package tacos.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//end::recents[]
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//tag::recents[]
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "*")
public class TacoController {
  private TacoRepository tacoRepo;

  public TacoController(TacoRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
  }

  // tag::recentTacosFlux[]
  @GetMapping(params="recent")
  public Flux<Taco> recentTacos() {
    return tacoRepo.findAll().take(12);
  }
  // end::recentTacosFlux[]

  // tag::postTaco_saveAll[]
  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Taco> postTaco(@RequestBody Mono<Taco> tacoMono) {
    return tacoRepo.saveAll(tacoMono).next();
  }
  // end::postTaco_saveAll[]

  // tag::tacoById[]
  @GetMapping("/{id}")
  public Mono<Taco> tacoById(@PathVariable("id") String id) {
    return tacoRepo.findById(id);
  }
  // end::tacoById[]

}
