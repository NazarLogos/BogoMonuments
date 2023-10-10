package bogomonuments.com.mapper;

import bogomonuments.com.dto.MonumentDTO;
import bogomonuments.com.entity.Monument;

public class MonumetMapper {
    public static MonumentDTO mapToMonumentDto(Monument monument){
        MonumentDTO monumentDto = new MonumentDTO(
                monument.getId(),
                monument.getName(),
                monument.getKindOfMonument(),
                monument.getPrice(),
                monument.getDescription(),
                monument.getColor(),
                monument.getMaterial(),
                monument.getKindOfStone(),
                monument.getWeight(),
                monument.getHeight(),
                monument.getLength(),
                monument.getWidth(),
                monument.getPhotos()
//                monument.getEncodedImage()
                );
        return monumentDto;
    }
    public static Monument mapToMonument(MonumentDTO monumentDTO){
        Monument monument = new Monument(
                monumentDTO.getId(),
                monumentDTO.getName(),
                monumentDTO.getKindOfMonument(),
                monumentDTO.getPrice(),
                monumentDTO.getDescription(),
                monumentDTO.getColor(),
                monumentDTO.getMaterial(),
                monumentDTO.getKindOfStone(),
                monumentDTO.getWeight(),
                monumentDTO.getHeight(),
                monumentDTO.getLength(),
                monumentDTO.getWidth(),
                monumentDTO.getPhotos()
//                monumentDTO.getEncodedImage()
        );
        return monument;
    }


}
