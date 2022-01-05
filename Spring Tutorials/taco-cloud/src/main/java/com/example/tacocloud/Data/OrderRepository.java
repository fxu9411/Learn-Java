package com.example.tacocloud.Data;

import com.example.tacocloud.Domain.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
