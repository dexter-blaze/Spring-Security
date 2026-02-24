package com.codingNinjas.taxEase.controller;

import com.codingNinjas.taxEase.dto.TaxRecordDto;
import com.codingNinjas.taxEase.model.TaxRecord;
import com.codingNinjas.taxEase.service.TaxRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/tax")
public class TaxRecordController {

    @Autowired
    private TaxRecordService taxRecordService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('NORMAL') or hasAuthority('NORMAL')")
    @ResponseStatus(HttpStatus.OK)
    public TaxRecord getTaxRecordById(@PathVariable Long id){
        return taxRecordService.getTaxRecordById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('NORMAL') or hasAuthority('NORMAL')")
    @ResponseStatus(HttpStatus.OK)
    public List<TaxRecord> getALlTaxRecords(){
        return taxRecordService.getAllRecords();
    }

    @PostMapping("/{userId}")
    @PreAuthorize("hasRole('NORMAL') or hasAuthority('NORMAL')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTaxRecord(@RequestBody TaxRecordDto taxRecordDto, @PathVariable Long userId){
        taxRecordService.createTaxRecord(taxRecordDto, userId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('NORMAL') or hasAuthority('NORMAL')")
    @ResponseStatus(HttpStatus.OK)
    public void updateTaxRecord(@RequestBody TaxRecordDto taxRecordDto, @PathVariable Long id){
        taxRecordService.updateTaxRecord(taxRecordDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('NORMAL') or hasAuthority('NORMAL')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTaxRecord(@PathVariable Long id){
        taxRecordService.deleteTaxRecord(id);
    }

    @PostMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void approveTaxFiling(@PathVariable Long id){
        taxRecordService.approveTaxFiling(id);
    }

    @PostMapping("/reject/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void rejectTaxFiling(@PathVariable Long id){
        taxRecordService.rejectTaxFiling(id);
    }
}
