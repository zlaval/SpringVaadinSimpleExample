package com.zlrx.service;

import com.zlrx.domain.Car;
import com.zlrx.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car save(final Car car) {
        return carRepository.save(car);
    }

    @Override
    public void delete(final Car car) {
        carRepository.delete(car);
    }

}
