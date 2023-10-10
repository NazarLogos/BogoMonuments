package bogomonuments.com.dto;


import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonumentDTO {
    private Long id ;

//    @NotEmpty(message = "Must BE NOT NULL")
    private  String name ;

//    @NotEmpty(message = "Must BE NOT NULL")
    private String kindOfMonument;

//    @NotEmpty(message = "Must BE NOT NULL")
    private double price;

//    @NotEmpty(message = "Must BE NOT NULL")
    private String description ;

//    @NotEmpty(message = "Must BE NOT NULL")
    private String color ;

//    @NotEmpty(message = "Must BE NOT NULL")
    private String material ;

//    @NotEmpty(message = "Must BE NOT NULL")
    private String kindOfStone ;

//    @NotEmpty(message = "Must BE NOT NULL")
    private double weight;

//    @NotEmpty(message = "Must BE NOT NULL")
    private double height;

//    @NotEmpty(message = "Must BE NOT NULL")
    private double length;

//    @NotEmpty(message = "Must BE NOT NULL")
    private double width;

    private String photos;
    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/user-photos/null/"  + photos;
    }

//    for server project
//    @Transient
//    public String getPhotosImagePath() {
//        if (photos == null || id == null) return null;
//
//        return "/style/svg/" + photos;
//    }

//    private byte[] encodedImage;
}
