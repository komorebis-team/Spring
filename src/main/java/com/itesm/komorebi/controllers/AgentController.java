/*package com.itesm.komorebi.controllers;

import com.itesm.komorebi.models.Agent;import com.itesm.komorebi.services.AgentService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;import org.springframework.web.bind.annotation.*;
import java.util.List;import java.util.UUID;

@RestController
@RequestMapping("/v1/agent")
public class AgentController {

    @Autowired
    AgentService agentService;

    @GetMapping("/all")
    public List<Agent> getPerfil(){
        return agentService.getAll();
    }

    @GetMapping("/id/{id}")
    public Agent getPerfil(@PathVariable("id") String id){
        return agentService.getById(UUID.fromString(id));
    }

    @PostMapping("/register")
    public Agent registerAgent(@RequestBody Agent agent){
        return agentService.registerAgent(agent);
    }

    @PostMapping("/update")
    public Agent updateAgent(Agent agent){
        return agentService.updateAgent(agent);
    }
}
*/