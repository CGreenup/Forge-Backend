package com.forge.revature.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.forge.revature.models.Portfolio;
import com.forge.revature.repo.PortfolioRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/portfolios")
public class PortfolioController {
    @Autowired
    PortfolioRepo portRepo;

    public PortfolioController() {
    }

    public PortfolioController(PortfolioRepo portRepo) {
        this.portRepo = portRepo;
    }

    @GetMapping
    public List<Portfolio> getAll(){
        List<Portfolio> ports = StreamSupport.stream(portRepo.findAll().spliterator(), false)
        .collect(Collectors.toList());
    return ports;
    }

    @GetMapping("/{id}")
    public Portfolio getByID(@PathVariable(name = "id") int id){
        return portRepo.findById(id).get();
    }

    @GetMapping("/users/all/{id}")
    public List<Portfolio> getPortfoliosByUserId(@PathVariable int id){
        List<Portfolio> portfolios = portRepo.findAllByUserId(id);
        return portfolios;
    }

    @PostMapping
    public Portfolio postPort(@RequestBody Portfolio port){
        return portRepo.save(port);
    }
    @PostMapping("portfolios/{id}")
    public void updateUser(@PathVariable int id , @RequestBody Portfolio updated){
        Optional<Portfolio> old = portRepo.findById(id);

        if(old.isPresent()){
            old.get().setApproved(updated.isApproved());
            old.get().setFeedback(updated.getFeedback());
            old.get().setName(updated.getName());
            old.get().setReviewed(updated.isReviewed());
            old.get().setSubmitted(updated.isSubmitted());
            old.get().setUser(updated.getUser());
        
            portRepo.save(old.get());
        }
    }
    
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deletePortfolio(@PathVariable int id) throws ResourceNotFoundException{
        Optional<Portfolio> port = portRepo.findById(id);

        if(port.isPresent()){
            portRepo.delete(port.get());
        }else{
            throw new ResourceNotFoundException("The Portfolio to be deleted could not be found");
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
