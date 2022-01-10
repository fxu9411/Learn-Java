package com.example.calculator.basic;

import com.example.calculator.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;


@RestController
@RequestMapping("/basic")
public class BasicController {

   @GetMapping("/add")
   public Result add(@RequestParam BigDecimal a, @RequestParam BigDecimal b ) {
      return Result.getResult(a.add(b));
   }

   @GetMapping("/subtract")
   public Result subtract(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
      return Result.getResult(a.subtract(b));
   }

   @GetMapping("/multiply")
   public Result multiply(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
      return Result.getResult(a.multiply(b));
   }

   @GetMapping("/divide")
   public Result divide(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
      if (b.equals(BigDecimal.ZERO)) {
         System.out.println("Cannot divide by zero!");
      }
      return Result.getResult(a.divide(b, RoundingMode.HALF_EVEN));
   }

}
