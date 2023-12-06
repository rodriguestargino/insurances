package com.audsat.insurances.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.audsat.insurances.bean.InsuranceBudgetProposalBean;
import com.audsat.insurances.model.Car;
import com.audsat.insurances.model.Customer;
import com.audsat.insurances.model.Insurance;
import com.audsat.insurances.model.request.InsuranceBudgetProposalRequest;
import com.audsat.insurances.service.CarService;
import com.audsat.insurances.service.CustomerService;
import com.audsat.insurances.service.InsuranceBudgetService;

@RestController
@RequestMapping("/insurance/budget")
public class InsuranceBudgetProposalController {

	private final InsuranceBudgetService insuranceBudgetProposalService;
	private final CarService carService;
	private final CustomerService customerService;
	
	@Autowired
    public InsuranceBudgetProposalController(InsuranceBudgetService insuranceBudgetProposalService,
                                            CustomerService customerService, CarService carService) {
        this.insuranceBudgetProposalService = insuranceBudgetProposalService;
        this.customerService = customerService;
        this.carService = carService;
    }

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@GetMapping("/{id}")
	public ResponseEntity<Insurance> findById(@PathVariable long id) {
		Insurance insuranceBudgetProposal = insuranceBudgetProposalService.getInsuranceBudgetProposalById(id);

		if (insuranceBudgetProposal == null) {
		     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Insurance not found");
		} else {
			return ResponseEntity.ok(insuranceBudgetProposal);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Insurance>> findByAll() {
		List<Insurance> insuranceBudgetProposal = insuranceBudgetProposalService.getAllInsuranceBudgetProposals();

		if (insuranceBudgetProposal == null || insuranceBudgetProposal.isEmpty()) {
		     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Insurances not found");
		} else {
			return ResponseEntity.ok(insuranceBudgetProposal);
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Insurance> create(@RequestBody InsuranceBudgetProposalRequest insuranceBudgetProposalRequest) {
    	
    	
    	InsuranceBudgetProposalBean insuranceBean = parsetoBean(insuranceBudgetProposalRequest);
    	
    	Insurance insuranceBudgetProposal = insuranceBudgetProposalService.createInsuranceBudgetProposal(insuranceBean);
        return ResponseEntity.ok(insuranceBudgetProposal);
    }

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable long id) {
		
		Insurance insuranceBudgetProposal = insuranceBudgetProposalService.getInsuranceBudgetProposalById(id);

	    if (insuranceBudgetProposal == null) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Insurance not found");
	    }
	    
	    insuranceBudgetProposalService.deleteInsuranceBudgetProposalById(id);
		return ResponseEntity.ok().build();
	}
	
	private InsuranceBudgetProposalBean parsetoBean(InsuranceBudgetProposalRequest request) {
		  InsuranceBudgetProposalBean bean = new InsuranceBudgetProposalBean();
		  
		  Customer customer = customerService.getCustomerBycpf(request.getCustomerDocument());
		  
		  Optional<Car> car = carService.getCarByPlaca(request.getPlaca());

		  bean.setCustomer(customer); 
		  bean.setCreateDt(request.getCreateDt());
		  bean.setUpdateDt(request.getUpdateDt());

		  bean.setCar(car);

		  bean.setActive(request.isActive());
		  bean.setProposedCoverage(request.getProposedCoverage());
		  bean.setJustification(request.getJustification());

		  return bean;
	}


}
