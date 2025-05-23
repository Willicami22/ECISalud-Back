package eci.edu.cvds.ECISalud.Service;

import eci.edu.cvds.ECISalud.Entitity.Citation;
import eci.edu.cvds.ECISalud.Repository.CitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CitationService {
    @Autowired
    CitationRepository citationRepository;

    public boolean programate(LocalDate date,String specialization, String doctor, String location, String user, String email, String idUser){
        if(LocalDate.EPOCH.isBefore(LocalDate.now())){
            return false;
        }
        else {
            Citation cite = new Citation();
            cite.setDate(date);
            cite.setDoctor(doctor);
            cite.setEmail(email);
            cite.setLocation(location);
            cite.setSpecialization(specialization);
            cite.setUserId(idUser);
            cite.setUser(user);
            cite.setState("Confirmada");
            citationRepository.save(cite);
            return true;
        }
    }

    public Optional<Citation> listCitation(String email, String state){
        if (state == null){
            return citationRepository.findByEmail(email);
        }
        else {
            return citationRepository.findByEmailAndState(email,state);
        }
    }

    public void cancell(String Id){
        Citation cite = citationRepository.findById(Id).get();
        cite.setState("Cancelada");
        citationRepository.save(cite);
    }
}
