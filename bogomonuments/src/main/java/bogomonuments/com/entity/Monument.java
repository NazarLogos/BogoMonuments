package bogomonuments.com.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "monuments")
public class Monument {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Long id ;
    @Column
    private  String name ;
    @Column
    private String kindOfMonument;
    @Column
    private double price;
    @Column
    private String description ;
    @Column
    private String color ;
    @Column
    private String material ;
    @Column
    private String kindOfStone ;
    @Column
    private double weight;
    @Column
    private double height;
    @Column
    private double length;
    @Column
    private double width;
    @Column(nullable = true, length = 64)
    private String photos;
    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/user-photos/null/"  + photos;
    }
//    @Transient
//    public String getPhotosImagePath() {
//        if (photos == null || id == null) return null;
//
//        return "/style/svg/" + photos;
//    }
////    @Lob
//    private byte[] encodedImage;

}
