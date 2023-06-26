package tw.hicamp.campsite.dto;

import java.util.List;

import lombok.Data;

@Data
public class CampsiteDTO {
    private Integer campsiteNo;
    private String campsiteName;
    private Integer campsiteQuantity;
    private String campsiteLocation;
    private String campsiteInfo;
    private List<byte[]> campsitePictures;
    private List<Integer> campsitePictureNo;
    private Integer campsiteFirstPictureNo;

}

