package bogomonuments.com.service;

import bogomonuments.com.dto.MonumentDTO;


import java.io.IOException;
import java.util.List;

public interface MonumentService {

    void createMonument(MonumentDTO monumentDTO) ;

    List<MonumentDTO> getAllMonuments();
    MonumentDTO getMonumentById(Long monumentId);
    void updateMonument(MonumentDTO monumentDTO);
    void deleteMonument(Long monumentId);



}
