package com.example.DVDShop.controllers;

import com.example.DVDShop.domain.DVDisk;
import com.example.DVDShop.domain.TakenItem;
import com.example.DVDShop.domain.User;
import com.example.DVDShop.services.DVDService;
import com.example.DVDShop.services.ServiceTakenItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;

@Controller
public class TakeDiskController {

    @Autowired
    private DVDService dvdService;


    @Autowired
    private ServiceTakenItem serviceTakenItem;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/take")
    public String main(Model model) {
        Iterable<DVDisk> disks;

        disks= dvdService.findAllFree();

        model.addAttribute("disks", disks);

        return "takeDisk";
    }

    @PostMapping("/take")
    public String takeDisk(
            @AuthenticationPrincipal User user,
            @RequestParam("diskId") DVDisk disk,
            Model model
    ){

        Date date=Calendar.getInstance().getTime();
        TakenItem takenItem=new TakenItem(disk,user, date);
     try{
        serviceTakenItem.save(takenItem);

        dvdService.changAvailable(disk,false);
    }catch (Exception e){
        return "redirect:/500";
    }
        Iterable<DVDisk> disks= dvdService.findAllFree();
        model.addAttribute("disks", disks);

        return "takeDisk";
    }

    @GetMapping("/take/my")
    public String myDisk(
            @AuthenticationPrincipal User user,
            Model model
    ){
        Iterable<TakenItem> takenItems= serviceTakenItem.findAllByUser(user);
        model.addAttribute("takenItems", takenItems);

        return "takenDisks";
    }

    @PostMapping("/take/my/return")
    public String returnDisk(
            @AuthenticationPrincipal User user,
            @RequestParam("diskId") DVDisk disk,
            Model model
    ){
        try {
            disk.setIs_available(true);
            dvdService.save(disk);

            TakenItem takenItem = serviceTakenItem.findAllByDiskAndUser(disk, user);
            Date date = Calendar.getInstance().getTime();
            takenItem.setDate_returned(date);
            serviceTakenItem.save(takenItem);
        }catch (Exception e){
            return "redirect:/500";
        }

        return myDisk(user, model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/take/all")
    public String allTakenItem(Model model) {
        Iterable<TakenItem> takenItems;

        takenItems= serviceTakenItem.findAll();

        model.addAttribute("takenItems", takenItems);

        return "allOperationsTake";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/take/delete")
    public String takeDisk(
            @AuthenticationPrincipal User user,
            @RequestParam("itemId") TakenItem item,
            Model model
    ){
        try{
            serviceTakenItem.delete(item);
        }catch (Exception e){
            return "redirect:/500";
        }
        Iterable<DVDisk> disks= dvdService.findAllFree();
        model.addAttribute("disks", disks);

        return "redirect:/take/all";
    }
}
