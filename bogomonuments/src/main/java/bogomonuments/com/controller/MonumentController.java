package bogomonuments.com.controller;

import bogomonuments.com.dto.MonumentDTO;
import bogomonuments.com.fileU.FileUploadUtil;
import bogomonuments.com.service.MonumentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MonumentController {

    private MonumentService monumentService;

    public MonumentController(MonumentService monumentService) {
        this.monumentService = monumentService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/home";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }
    @GetMapping("/home")
    public String getHome(){
        return "home";
    }
    @GetMapping("/contacts")
    public String getContacts(){
        return "contacts";
    }
    @GetMapping("/403")
    public String getError(){
        return "403";
    }

//    @GetMapping("/monuments")
//    public String getAllStudents(Model model){
//        List<MonumentDTO> monuments = monumentService.getAllMonuments();
//        monuments.sort(Comparator.comparing(MonumentDTO::getId));
//        model.addAttribute("monuments",monuments);
//        return "monuments";
//    }

//  a method that extracts all monuments from the database and sorts them by condition
    @GetMapping("/monuments")
    public String getAllStudents(@RequestParam(name = "value", required = false) Integer value, Model model){
        List<MonumentDTO> monuments = monumentService.getAllMonuments();
        if (value != null && value == 1) {
            // Якщо значення рівне 1, то сортує за умовою 1
            monuments.sort(Comparator.comparing(MonumentDTO::getId));
        }
        if (value != null && value == 2) {
            monuments.sort(Comparator.comparing(MonumentDTO::getPrice));
        }
        if (value != null && value == 3) {
            monuments.sort(Comparator.comparing(MonumentDTO::getPrice).reversed());
        }
        if (value != null && value == 4) {
            monuments.sort(Comparator.comparing(MonumentDTO::getId).reversed());
        }
        model.addAttribute("monuments",monuments);
        return "monuments";
    }
    //    method that extracts monuments from the database and sorts them by condition
    @GetMapping("/monuments_single")
    public String getSingleStudents(@RequestParam(name = "value", required = false) Integer value, Model model){
        List<MonumentDTO> monuments = monumentService.getAllMonuments();
        List<MonumentDTO> singleMonument = monuments.stream()
                .filter(monument -> monument.getKindOfMonument().toLowerCase().equals("Одинарний".toLowerCase()))
                .collect(Collectors.toList());
        if (value != null && value == 1) {
            // Якщо значення рівне 1, то сортує за умовою 1
            singleMonument.sort(Comparator.comparing(MonumentDTO::getId));
        }
        if (value != null && value == 2) {
            singleMonument.sort(Comparator.comparing(MonumentDTO::getPrice));
        }
        if (value != null && value == 3) {
            singleMonument.sort(Comparator.comparing(MonumentDTO::getPrice).reversed());
        }
        if (value != null && value == 4) {
            singleMonument.sort(Comparator.comparing(MonumentDTO::getId).reversed());
        }
        model.addAttribute("monuments",singleMonument);
        return "monuments";
    }
    //    method that extracts monuments from the database and sorts them by condition
    @GetMapping("/monuments_double")
    public String getDoubleStudents(@RequestParam(name = "value", required = false) Integer value, Model model){
        List<MonumentDTO> monuments = monumentService.getAllMonuments();
        List<MonumentDTO> doubleMonument = monuments.stream()
                .filter(monument -> monument.getKindOfMonument().toLowerCase().equals("Подвійний".toLowerCase()))
                .collect(Collectors.toList());
        if (value != null && value == 1) {
            // Якщо значення рівне 1, то сортує за умовою 1
            doubleMonument.sort(Comparator.comparing(MonumentDTO::getId));
        }
        if (value != null && value == 2) {
            doubleMonument.sort(Comparator.comparing(MonumentDTO::getPrice));
        }
        if (value != null && value == 3) {
            doubleMonument.sort(Comparator.comparing(MonumentDTO::getPrice).reversed());
        }
        if (value != null && value == 4) {
            doubleMonument.sort(Comparator.comparing(MonumentDTO::getId).reversed());
        }
        model.addAttribute("monuments",doubleMonument);
        return "monuments";
    }
//    method that extracts monuments from the database and sorts them by condition
    @GetMapping("/monuments_children")
        public String getChildrenStudents(@RequestParam(name = "value", required = false) Integer value, Model model){
        List<MonumentDTO> monuments = monumentService.getAllMonuments();
        List<MonumentDTO> childrenMonument = monuments.stream()
                .filter(monument -> monument.getKindOfMonument().toLowerCase().equals("Дитячий".toLowerCase()))
                .collect(Collectors.toList());
        if (value != null && value == 1) {
            // Якщо значення рівне 2, то перенаправте на redirected.html
            childrenMonument.sort(Comparator.comparing(MonumentDTO::getId));
        }
        if (value != null && value == 2) {
            // Якщо значення рівне 2, то перенаправте на redirected.html
            childrenMonument.sort(Comparator.comparing(MonumentDTO::getPrice));
        }
        if (value != null && value == 3) {
            // Якщо значення рівне 2, то перенаправте на redirected.html
            childrenMonument.sort(Comparator.comparing(MonumentDTO::getPrice).reversed());
        }
        if (value != null && value == 4) {
            // Якщо значення рівне 2, то перенаправте на redirected.html
            childrenMonument.sort(Comparator.comparing(MonumentDTO::getId).reversed());
        }
        model.addAttribute("monuments",childrenMonument);
        return "monuments";
    }

    @GetMapping("/monuments/new")
    public String newMonument(Model model){
        // student model object to store student form data
        MonumentDTO monumentsDTO = new MonumentDTO();
        model.addAttribute("monument", monumentsDTO);
        return "create_monument";
    }
//    method for obtaining information about the monument and its storage
    @PostMapping("/monuments")
    public String saveMonument(@Valid  @ModelAttribute("monument")MonumentDTO monument,
                                @RequestParam("image") MultipartFile multipartFile,
                              BindingResult result,
                              Model model) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        monument.setPhotos(fileName);
        String uploadDir = "user-photos/" + monument.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        if(result.hasErrors()){
            model.addAttribute("monument", monument);
            return "create_monument";
        }
            monumentService.createMonument(monument);

        return "redirect:/monuments";
    }
    //  method to handle edit monument form submit request
    @GetMapping("/monuments/{monumentId}/edit")
    public String editMonument(@PathVariable("monumentId") Long monumentId,
                              Model model){
        MonumentDTO monument = monumentService.getMonumentById(monumentId);
        model.addAttribute("monument", monument);
        return "edit_monument";
    }

    //  method to handle edit monument form submit request
    @PostMapping("/monuments/{monumentId}")
    public String updateMonument( @Valid @PathVariable("monumentId") Long monumentId,
                                 @ModelAttribute("monument") MonumentDTO monumentDTO,
                                BindingResult result,
                                Model model){
        if(result.hasErrors()){
            model.addAttribute("monument", monumentDTO);
            return "edit_monument";
        }
        monumentDTO.setId(monumentId);
        monumentService.updateMonument(monumentDTO);
        return "redirect:/monuments";
    }
    //  method for access to the monument and inspection
    @GetMapping("/monuments/{monumentId}/view")
    public String viewMonument(@PathVariable("monumentId") Long monumentId,
                              Model model){
         MonumentDTO monumentDTO = monumentService.getMonumentById(monumentId);
        model.addAttribute("monument", monumentDTO);
        return "view_monument";
    }
//    method for removing the monument
    @GetMapping("/monuments/{monumentId}/delete")
    public String deleteMonument(@PathVariable("monumentId") Long monumentId){
        monumentService.deleteMonument(monumentId);
        return "redirect:/monuments";
    }
}
