package rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldApi {

  @GetMapping(produces = "text/plain", path = "/{home}")
  public String sayHello(
    @PathVariable(value = "home") final String home,
    @RequestParam(value = "user") final String user
  ) {
    return "Hello, " + user + "!";
  }
}
