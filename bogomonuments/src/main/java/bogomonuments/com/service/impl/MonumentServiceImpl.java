package bogomonuments.com.service.impl;

import bogomonuments.com.dto.MonumentDTO;
import bogomonuments.com.entity.Monument;
import bogomonuments.com.mapper.MonumetMapper;
import bogomonuments.com.repository.MonumentsRepository;
import bogomonuments.com.service.MonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class MonumentServiceImpl implements MonumentService {

    private MonumentsRepository monumentsRepository;

    public MonumentServiceImpl(MonumentsRepository monumentsRepository) {
        this.monumentsRepository = monumentsRepository;
    }

    @Override
    public void createMonument(MonumentDTO monumentDTO)  {

        Monument monument = MonumetMapper.mapToMonument(monumentDTO);
        monumentsRepository.save(monument);

    }

    @Override
    public List<MonumentDTO> getAllMonuments() {
        List<Monument> monuments = monumentsRepository.findAll();
        List<MonumentDTO> monumentDTOS = monuments.stream().
                map(monument -> MonumetMapper.mapToMonumentDto(monument)).
                collect(Collectors.toList());
        return monumentDTOS;

    }

    @Override
    public MonumentDTO getMonumentById(Long monumentId) {
        Monument monument = monumentsRepository
                .findById(monumentId).get();
        MonumentDTO monumentDTO = MonumetMapper.mapToMonumentDto(monument);
        return monumentDTO;
    }

    @Override
    public void updateMonument(MonumentDTO monumentDTO) {
        monumentsRepository.save(MonumetMapper.mapToMonument(monumentDTO));

    }

    @Override
    public void deleteMonument(Long monumentId) {
        monumentsRepository.deleteById(monumentId);
    }
}
