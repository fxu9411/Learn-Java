//tag::recents[]
package tacos.web.api;
//end::recents[]

import java.util.Optional;
//tag::recents[]

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
//end::recents[]
import org.springframework.http.HttpStatus;
//tag::recents[]
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//end::recents[]
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//tag::recents[]
import org.springframework.web.bind.annotation.RequestMapping;
//end::recents[]
import org.springframework.web.bind.annotation.ResponseStatus;
//tag::recents[]
import org.springframework.web.bind.annotation.RestController;

import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path="/api/tacos",                      // <1>
                produces="application/json")
@CrossOrigin(origins="*")        // <2>
public class TacoController {
  private TacoRepository tacoRepo;

  public TacoController(TacoRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
  }

  @GetMapping(params="recent")
  public Iterable<Taco> recentTacos() {                 //<3>
    PageRequest page = PageRequest.of(
            0, 12, Sort.by("createdAt").descending());
    return tacoRepo.findAll(page).getContent();
  }
  //end::recents[]
  
  //tag::postTaco[]
  @PostMapping(consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepo.save(taco);
  }
  //end::postTaco[]

  //tag::tacoByIdSimple[]
  @GetMapping("/{id}")
  public Optional<Taco> tacoById(@PathVariable("id") Long id) {
    return tacoRepo.findById(id);
  }
  //end::tacoByIdSimple[]

  /*
  //tag::tacoByIdResponseEntity[]
  @GetMapping("/{id}")
  public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
    Optional<Taco> optTaco = tacoRepo.findById(id);
    if (optTaco.isPresent()) {
      return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
  //end::tacoByIdResponseEntity[]
  */


//tag::recents[]
}
//end::recents[]
