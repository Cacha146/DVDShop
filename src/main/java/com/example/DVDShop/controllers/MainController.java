package com.example.DVDShop.controllers;

import com.example.DVDShop.domain.DVDisk;
import com.example.DVDShop.services.DVDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private DVDService dvdService;


    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String home(Map<String, Object> model) {


        return "home";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<DVDisk> disks;

        if(filter!=null&&!filter.isEmpty()){
            disks= dvdService.findByName(filter);
        }else{
            disks= dvdService.findAll();
        }

        model.addAttribute("disks", disks);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/main")
    public String add(
            @Valid DVDisk dvDisk,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {


        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("DVDisk", dvDisk);

        }else{
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                dvDisk.setFilename(resultFilename);
            }
            model.addAttribute("DVDisk", null);
            dvdService.save(dvDisk);

        }


        Iterable<DVDisk> disks = dvdService.findAll();
        model.addAttribute("disks", disks);
        return "main";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/main/delete/{disk}")
    public String deleteDisk(@PathVariable DVDisk disk, Model model){

        try {
            dvdService.delete(disk);
        }
        catch (Exception e){
            return "redirect:/404";
        }
        Iterable<DVDisk> disks = dvdService.findAll();
        model.addAttribute("disks", disks);
        return "redirect:/main";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/main/update/{disk}")
    public String userEditForm(@PathVariable DVDisk disk, Model model) {
        if(disk!=null){
            model.addAttribute("disk", disk);
            return "diskEdit";
        }else{
            return "redirect:/404";
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/main/update")
    public String updateDisk(
            @RequestParam("diskId") DVDisk disk,
            @RequestParam String name,
            @RequestParam String description,
            Model model){


        dvdService.updateDisk(disk, name, description);

        Iterable<DVDisk> disks = dvdService.findAll();
        model.addAttribute("disks", disks);
        return "redirect:/main";
    }
}