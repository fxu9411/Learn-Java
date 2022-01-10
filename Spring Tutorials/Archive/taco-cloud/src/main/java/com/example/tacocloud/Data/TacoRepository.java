package com.example.tacocloud.Data;

import com.example.tacocloud.Domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
