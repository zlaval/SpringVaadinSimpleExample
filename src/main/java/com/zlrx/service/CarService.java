package com.zlrx.service;

import com.zlrx.domain.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car save(Car car);

    void delete(Car car);

}
