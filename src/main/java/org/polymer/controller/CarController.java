package org.polymer.controller;

import java.util.List;

import org.polymer.model.Car;
import org.polymer.repository.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/json/")
public class CarController {
	
	@Autowired
	private CarRepository carRepository;
	
	@GetMapping("cars")
	public List<Car> list() {
		return carRepository.findAll();
	}
	
	@PostMapping("cars")
	public Car create(@RequestBody Car car) {
		return carRepository.saveAndFlush(car);
	}
	
	@GetMapping("cars/{id}")
	public Car get(@PathVariable Long id) {
		return carRepository.findOne(id);
	}
	
	@PutMapping("cars/{id}")
	public Car update(@PathVariable Long id, @RequestBody Car car) {
		Car existingCar = carRepository.findOne(id);
		BeanUtils.copyProperties(car, existingCar);
		return carRepository.saveAndFlush(existingCar);
	}
	
	@DeleteMapping("cars/{id}")
	public Car delete(@PathVariable Long id) {
		Car existingCar = carRepository.findOne(id);			
		carRepository.delete(existingCar);
		return existingCar;
	}

}
