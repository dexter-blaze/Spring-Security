package com.codingNinjas.taxEase.service;

import com.codingNinjas.taxEase.dto.TaxRecordDto;
import com.codingNinjas.taxEase.exception.TaxRecordNotFoundException;
import com.codingNinjas.taxEase.exception.UserNotFoundException;
import com.codingNinjas.taxEase.model.TaxRecord;
import com.codingNinjas.taxEase.model.User;
import com.codingNinjas.taxEase.repository.TaxRecordRepository;
import com.codingNinjas.taxEase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaxRecordService {

    @Autowired
    private TaxRecordRepository taxRecordRepository;

    @Autowired
    private UserRepository userRepository;

    public TaxRecord getTaxRecordById(Long id) {
        return taxRecordRepository.findById(id)
                .orElseThrow(() -> new TaxRecordNotFoundException("Tax Record is not found for id: " + id));
    }


    public List<TaxRecord> getAllRecords() {
        return taxRecordRepository.findAll();
    }

    public void createTaxRecord(TaxRecordDto taxRecordDto,Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("No user found with given id: "+ userId));
        TaxRecord taxRecord = TaxRecord.builder().taxYear(taxRecordDto.getTaxYear()).deductions(taxRecordDto.getDeductions())
                .income(taxRecordDto.getIncome()).build();
        taxRecord.setUser(user);
        TaxRecord record = taxRecordRepository.save(taxRecord);
        List<TaxRecord> taxRecordList = new ArrayList<>();
        taxRecordList.add(record);
        user.setTaxRecords(taxRecordList);
        userRepository.save(user);
    }

    public void updateTaxRecord(TaxRecordDto taxRecordDto, Long id) {
        TaxRecord existingRecord = getTaxRecordById(id);

        if(!taxRecordDto.getTaxYear().isBlank()){
            existingRecord.setTaxYear(taxRecordDto.getTaxYear());
        }
        if(taxRecordDto.getIncome()!= 0 && taxRecordDto.getIncome() != existingRecord.getIncome()){
            existingRecord.setIncome(taxRecordDto.getIncome());
        }
        if(taxRecordDto.getDeductions() !=0 && taxRecordDto.getDeductions() != existingRecord.getDeductions()){
            existingRecord.setDeductions(taxRecordDto.getDeductions());
        }
        taxRecordRepository.save(existingRecord);
    }

    public void deleteTaxRecord(Long id) {
        taxRecordRepository.deleteById(id);
    }

    public void approveTaxFiling(Long id) {
        TaxRecord existingRecord = getTaxRecordById(id);
        existingRecord.setFilingApproved(true);
        taxRecordRepository.save(existingRecord);
    }

    public void rejectTaxFiling(Long id) {
        TaxRecord existingRecord = getTaxRecordById(id);
        existingRecord.setFilingApproved(false);
        taxRecordRepository.save(existingRecord);
    }
}
