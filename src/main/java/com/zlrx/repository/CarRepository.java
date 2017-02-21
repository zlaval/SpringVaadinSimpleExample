package com.zlrx.repository;

import com.zlrx.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface CarRepository extends JpaRepository<Car, Long> {

}
