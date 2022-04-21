package com.itesm.komorebi.repositories;

import com.itesm.komorebi.models.Agent;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;import java.util.UUID;

@Repository
public class AgentRepository {

    public List<Agent> findAll(){
        List<Agent> agents = new ArrayList<>();

        Agent p1 = new Agent("Juan", 33, UUID.fromString("61718c41-3467-42bf-bd21-d61773d8bc3d"));
        agents.add(p1);

        Agent p2 = new Agent("Andrés", 32, UUID.fromString("9e924c8b-7ead-43bf-b1be-cd37300f3509"));
        agents.add(p2);

        return agents;
    }

    public Agent getById(UUID id){
        List<Agent> agents = this.findAll();
        for (Agent a: agents){
            if (a.getId().compareTo(id) == 0){
                return a;
            }
        }
        return null;
    }

    public Agent registerAgent(Agent agent){
        return agent;
    }

    public Agent updateAgent(Agent agent){
        return agent;
    }
}
