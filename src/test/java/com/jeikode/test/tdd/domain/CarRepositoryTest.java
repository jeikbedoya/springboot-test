package com.jeikode.test.tdd.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void getCar_returnCarDetails(){
        Car carSaved = entityManager.persistAndFlush(new Car("prius", "hybrid"));

        Car car = repository.findByName("prius");

        assertThat(car.getName()).isEqualTo(carSaved.getName());
        assertThat(car.getType()).isEqualTo(carSaved.getType());

    }
}