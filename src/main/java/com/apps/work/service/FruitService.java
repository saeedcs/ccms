package com.apps.work.service;

import com.apps.work.model.Fruit;
import com.apps.work.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitService {

    @Autowired
    private FruitRepository fruitRepository;

    public void saveFruit(Fruit fruit) {
        fruitRepository.save(fruit);
    }
}