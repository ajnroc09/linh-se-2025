package fit.se2.springboot1.controller;

import fit.se2.springboot1.model.Company;
import fit.se2.springboot1.model.Employee;
import fit.se2.springboot1.repository.CompanyRepository;
import jakarta.persistence.SecondaryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyRepository companyRepository;

	@RequestMapping(value = "/{id}")
	public String getCompanyById(
			@PathVariable(value = "id") Long id, Model model) {
		Company company = companyRepository.getById(id);
		List<Employee> employees = company.getEmployees();
		model.addAttribute("employees", employees);
		model.addAttribute("company",company);
		return "companyDetail";
	}
	@RequestMapping(value = "/list")
	public String getAllCompany(Model model) {
		List<Company> companies = companyRepository.findAll();
		model.addAttribute("companies", companies);
		return "companyList";
	}

	@GetMapping(value = "/update/{id}")
	public String updateCompany(
			@PathVariable(value = "id") Long id, Model model) {
		Company company = companyRepository.getById(id);
		model.addAttribute(company);
		return "companyUpdate";
	}


	@GetMapping(value = "/add")
	public String addCompany(Model model) {
		Company company = new Company();
		model.addAttribute("company",company);
		return "companyAdd";
	}
	@PostMapping(value = "/add")
	public String addCompany(Company company) {
		companyRepository.save(company);
		return "redirect:/company/list";
	}


	@GetMapping(value = "/delete/{id}")
	public String deleteCompany(@PathVariable(value = "id") Long id) {
		if (companyRepository.findById(id).isPresent()) {

			Company company = companyRepository.findById(id).get();
			// suggestion: check if employee is null
			// if null, redirect to a 404 Not Found page
			companyRepository.delete(company);
		}
		return "redirect:/company/list";
	}
}
