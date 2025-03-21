package fit.se2.springboot1.controller;

import fit.se2.springboot1.model.Company;
import fit.se2.springboot1.model.Employee;
import fit.se2.springboot1.repository.CompanyRepository;
import fit.se2.springboot1.repository.EmployeeDao;
import fit.se2.springboot1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	EmployeeDao employeeDao;

	@RequestMapping(value = "/list")
	public String getAllEmployee(
			@RequestParam(value = "company", required = false, defaultValue = "0") Long comId,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "sort", required = false, defaultValue = "0") int sortMode,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			Model model) {
		final int pageSize = 1;
		Page<Employee> employees = employeeDao.filterAndSortEmployees(
				comId, gender, sortMode,
				PageRequest.of(page, pageSize)
		);
		model.addAttribute("page", page);
		model.addAttribute("pages", employees.getTotalPages());
		model.addAttribute("employees", employees.get());

		model.addAttribute("comId", comId);
		model.addAttribute("gender", gender);
		model.addAttribute("sortMode", sortMode);;
		model.addAttribute("companies", companyRepository.findAll());
		return "employeeList";
	}
	@RequestMapping(value = "/detail/{id}")
	public String getEmployeeById(@PathVariable(value = "id") Long id, Model model) {
		Employee employee = employeeRepository.getById(id);
		model.addAttribute("employee", employee);
		return "employeeDetail";
	}

	@GetMapping(value = "/update/{id}")
	public String updateEmployee(
			@PathVariable(value = "id") Long id, Model model) {
		Employee employee = employeeRepository.getById(id);
		List<Company> companies = companyRepository.findAll();
		model.addAttribute("employee",employee);
		model.addAttribute("companies",companies);
		return "employeeUpdate";
	}

	@PostMapping(value = "/save")
	public String saveUpdate(Employee employee) {
		employeeRepository.save(employee);
		return "redirect:/employee/list";
	}

	@GetMapping(value = "/add")
	public String addEmployee(Model model) {
		Employee employee = new Employee();
		List<Company> companies = companyRepository.findAll();
		model.addAttribute("employee", employee);
		model.addAttribute("companies", companies);
		return "employeeAdd";
	}

	@RequestMapping(value = "/insert")
	//mac dinh ko khai bao thi luon la GET
	//@RequestMapping(value="/insert",method="PUT") khai bao PUT
	//@GetMapping(value ="/insert")
	public String insertEmployee(Employee employee) {
		employeeRepository.save(employee);
		return "redirect:/detail/" + employee.getId();
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteEmployee(@PathVariable(value = "id") Long id) {
		if (employeeRepository.findById(id).isPresent()) {

			Employee employee = employeeRepository.findById(id).get();
			// suggestion: check if employee is null
			// if null, redirect to a 404 Not Found page
			employeeRepository.delete(employee);
		}
		return "redirect:/employee/list";
	}
}
