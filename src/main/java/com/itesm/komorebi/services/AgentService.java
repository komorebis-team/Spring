/*package com.itesm.komorebi.services;

import com.itesm.komorebi.models.Agent;
import com.itesm.komorebi.repositories.AgentRepository;import com.itesm.komorebi.repositories.RecordingRepository;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;import java.util.UUID;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    RecordingRepository recordingRepository;

    public Agent cumplirAnios(Agent p){
        p.setEdad(p.getEdad()+1);
        return p;
    }

    public List<Agent> getAll(){
        return agentRepository.findAll();
    }

    public Agent getById(UUID id){
        return agentRepository.getById(id);
    }

    public Agent registerAgent(Agent agent){
        return agentRepository.registerAgent(agent);
    }

    public Agent updateAgent(Agent agent){
        return agentRepository.registerAgent(agent);
    }
}
*/