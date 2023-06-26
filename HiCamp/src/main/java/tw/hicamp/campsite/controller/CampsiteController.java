package tw.hicamp.campsite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tw.hicamp.campsite.dto.CampsiteDTO;
import tw.hicamp.campsite.model.Campsite;
import tw.hicamp.campsite.model.CampsitePicture;
import tw.hicamp.campsite.model.CampsitePictureRepository;
import tw.hicamp.campsite.model.CampsiteRepository;
import tw.hicamp.campsite.service.CampsitePictureService;
import tw.hicamp.campsite.service.CampsiteService;

@Controller
public class CampsiteController {

    @Autowired
    private CampsiteService cService;
    
    @Autowired
    private CampsiteRepository cRepo;
    
    @Autowired
    private CampsitePictureService cpService;
    
    @Autowired
    private CampsitePictureRepository cpRepo;

    @RequestMapping("/campsite/show")
    public String showCampsite(Model model) {
        List<Campsite> campsite = cService.findAll();
        model.addAttribute("campsite", campsite);
        return "campsite/campsitePage";
    }

    @ResponseBody
    @PostMapping("/campsite/new")
    public ResponseEntity<Campsite> addCampsite(@RequestParam("campsiteNo") Integer campsiteNo,
                                                @RequestParam("campsiteName") String campsiteName,
                                                @RequestParam("campsiteQuantity") Integer campsiteQuantity,
                                                @RequestParam("campsiteLocation") String campsiteLocation,
                                                @RequestParam("campsiteInfo") String campsiteInfo,     
                                                @RequestParam("files") MultipartFile[] files) throws IOException {
        Campsite campsite = new Campsite();
        campsite.setCampsiteNo(campsiteNo);
        campsite.setCampsiteName(campsiteName);
        campsite.setCampsiteQuantity(campsiteQuantity);
        campsite.setCampsiteLocation(campsiteLocation);
        campsite.setCampsiteInfo(campsiteInfo);

        List<CampsitePicture> campsitePictureList = new ArrayList<>();

        for(MultipartFile file : files) {
            CampsitePicture campsitePicture = new CampsitePicture();
            byte[] pictureBytes = file.getBytes();
            campsitePicture.setCampsitePicture(pictureBytes); // 這個方法應該在你的 CampsitePicture 實體中定義
            campsitePicture.setCampsite(campsite);
            campsitePictureList.add(campsitePicture);
        }

        campsite.setCampsitePicture(campsitePictureList);

        Campsite savedCampsite = cRepo.save(campsite);
        
        return ResponseEntity.ok(savedCampsite);
//        return "campsite/campsitePage";
    }

    @GetMapping("/campsite/frontCampsite")
    public String showFrontCampsite() {
        return "campsite/frontCampsite";
    }



    @GetMapping("/campsite/edit")
    public String editPage(@RequestParam("campsiteNo") Integer campsiteNo, Model model) {
        Campsite campsite = cService.findById(campsiteNo);
        model.addAttribute("campsite", campsite);
        model.addAttribute("campsitePictures", campsite.getCampsitePicture());
        return "campsite/editCampsite";
    }
    @GetMapping("/campsitePicture/{id}")
    public ResponseEntity<byte[]> getCampsitePicture(@PathVariable("id") Integer campsiteNo){
        Optional<CampsitePicture> optionalCampsitePicture = cpRepo.findById(campsiteNo);
        
        if(optionalCampsitePicture.isPresent()) {
            CampsitePicture picture = optionalCampsitePicture.get();
            byte[] pictureBytes = picture.getCampsitePicture();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(pictureBytes, headers, HttpStatus.OK);
        }
        
        return ResponseEntity.notFound().build();
    }


    @ResponseBody
    @GetMapping("/campsite/data")
    public List<CampsiteDTO> getAllCampsites() {
        List<Campsite> campsites = cRepo.findAll();

        List<CampsiteDTO> campsiteDTOs = new ArrayList<>();
        for (Campsite campsite : campsites) {
            CampsiteDTO campsiteDTO = new CampsiteDTO();
            campsiteDTO.setCampsiteNo(campsite.getCampsiteNo());
            campsiteDTO.setCampsiteName(campsite.getCampsiteName());
            campsiteDTO.setCampsiteQuantity(campsite.getCampsiteQuantity());
            campsiteDTO.setCampsiteLocation(campsite.getCampsiteLocation());
            campsiteDTO.setCampsiteInfo(campsite.getCampsiteInfo());

            List<Integer> campsitePicturesNo = new ArrayList<>();
            for (CampsitePicture picture : campsite.getCampsitePicture()) {
            	campsitePicturesNo.add(picture.getCampsitePictureNo());
            }
            campsiteDTO.setCampsitePictureNo(campsitePicturesNo);

            campsiteDTOs.add(campsiteDTO);
        }

        return campsiteDTOs;
    }
    
    @PutMapping("/campsite/edit")
    public String editPost(@ModelAttribute(name="campsite") Campsite campsite) {
        cService.updateCampsiteById(campsite.getCampsiteNo(), campsite);
        
        return "redirect:/campsite/show";
    }
    
    @DeleteMapping("/campsite/delete")
    public String deletePost(@RequestParam("campsiteNo") Integer campsiteNo, Model model) {
    	cService.deleteById(campsiteNo);
    	return "redirect:/campsite/show";
    }
    
    @GetMapping("/campsite/getCampsitePicture")
    public ResponseEntity<byte[]> getCampsitePicture2(@RequestParam("id") Integer campsitePictureNo){
    	Optional<CampsitePicture> optional = cpRepo.findById(campsitePictureNo);
    	
    	if(optional.isPresent()) {
    		CampsitePicture picture = optional.get();
    		byte[] pictureFile = picture.getCampsitePicture();
    		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(pictureFile);
    	}
		return null;
    }

}
