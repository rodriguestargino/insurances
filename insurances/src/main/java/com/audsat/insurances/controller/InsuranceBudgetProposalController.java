package com.audsat.insurances.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.audsat.insurances.model.InsuranceBudget;
import com.audsat.insurances.model.request.InsuranceBudgetProposalRequest;
import com.audsat.insurances.service.InsuranceBudgetService;

@RestController
@RequestMapping("/insurance/budget")
public class InsuranceBudgetProposalController {

	private final InsuranceBudgetService insuranceBudgetProposalService;

    public InsuranceBudgetProposalController(InsuranceBudgetService insuranceBudgetProposalService) {
        this.insuranceBudgetProposalService = insuranceBudgetProposalService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InsuranceBudget> findById(@PathVariable long id) {
        InsuranceBudget insuranceBudgetProposal = insuranceBudgetProposalService.getInsuranceBudgetProposalById(id);

        if (insuranceBudgetProposal == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(insuranceBudgetProposal);
        }
    }

    @PostMapping
    public ResponseEntity<InsuranceBudget> create(@RequestBody InsuranceBudgetProposalRequest insuranceBudgetProposalRequest) {
        InsuranceBudget insuranceBudgetProposal = insuranceBudgetProposalService.createInsuranceBudgetProposal(insuranceBudgetProposalRequest.getProposedCoverage(),
                insuranceBudgetProposalRequest.getEstimatedCost(),
                insuranceBudgetProposalRequest.getJustification());
        return ResponseEntity.ok(insuranceBudgetProposal);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        insuranceBudgetProposalService.deleteInsuranceBudgetProposalById(id);
        return ResponseEntity.ok().build();
    }
    
}
