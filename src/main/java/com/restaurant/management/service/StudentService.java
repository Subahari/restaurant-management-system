package com.restaurant.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurant.management.Logindomain.Amenu;
import com.restaurant.management.repository.AmenuRepository;

@Service
public class StudentService {
    @Autowired
    private AmenuRepository repo;
    public List<Amenu> listAll() {
        return repo.findAll();
    }

    public void save(Amenu std) {
        repo.save(std);
    }

    public Amenu get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

}
