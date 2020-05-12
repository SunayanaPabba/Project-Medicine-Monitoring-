package com.medicinemonitoring.medicinemonitoring.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medicinemonitoring.medicinemonitoring.dao.CustomerDao;
import com.medicinemonitoring.medicinemonitoring.models.Customer;
import com.medicinemonitoring.medicinemonitoring.models.CustomerLogin;
import com.medicinemonitoring.medicinemonitoring.models.CustomerOrders;
import com.medicinemonitoring.medicinemonitoring.models.Help;
import com.medicinemonitoring.medicinemonitoring.models.Medicine;
import com.medicinemonitoring.medicinemonitoring.models.Payment;
import com.medicinemonitoring.medicinemonitoring.models.SortMedicine;
import com.medicinemonitoring.medicinemonitoring.services.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerservice;
	@GetMapping(value="/customer")
	public String customer(Model model) {
		model.addAttribute("customer",new Customer());
		return "Customer";
	}
	@PostMapping(value="/customerreg")
	public String custreg(@ModelAttribute("customer") Customer custreg,Model model) {
		int res=customerservice.createCustomer(custreg);	
		if(res==1) {
			model.addAttribute("message","Your  are already registered.");
			return "Customer";
		}else if(res==2) {
			model.addAttribute("message","Your details are submitted successfully.");
			return "Customer";
		}else {
			model.addAttribute("message","Oops...Something went wrong.");
			return "Customer";
		}
	}
	@GetMapping(value="/customerlogin")
	public String customerlogin(Model model) {
		model.addAttribute("customerlogin", new CustomerLogin());
		return "CustomerLogin";
	}
	@PostMapping(value="/custloginverify")
	public String custloginverify(@ModelAttribute("customerlogin") CustomerLogin custlogin,Model model,HttpSession session) 
	{
		boolean b1=customerservice.loginVerification(custlogin);
		if(b1){
			session.setAttribute("username", custlogin.getUserId());
			return "CustomerHome";
		}
		model.addAttribute("message", "Invalid UserId or Password");
		return "CustomerLogin";
	}
	
	@PostMapping(value="/searchresult")
	public String searchresult(HttpServletRequest req,Model model) {
		String searchby=req.getParameter("searchby");
		String searchname=req.getParameter("searchname");
		model.addAttribute("medicinename", searchname);
		List<Medicine> list = null;
		if(searchby.equalsIgnoreCase("all")) {
			list=customerservice.medicineListByAll(searchname);
			model.addAttribute("list", list);
		}else if(searchby.equalsIgnoreCase("name")) {
			list=customerservice.medicineListByName(searchname);
			model.addAttribute("list", list);
		}else if(searchby.equalsIgnoreCase("illness")) {
			list=customerservice.medicineListByIllness(searchname);
			model.addAttribute("list", list);
		}else if(searchby.equalsIgnoreCase("brand")) {
			list=customerservice.medicineListByBrand(searchname);
			model.addAttribute("list", list);
		}else if(searchby.equalsIgnoreCase("type")) {
			list=customerservice.medicineListByType(searchname);
			model.addAttribute("list", list);
		}
		
		SortMedicine sm = new SortMedicine();
		
		  sm.setSearchBy(searchname);
		req.setAttribute("list", list);
		model.addAttribute("medlist", sm);
		return "searchresult";
	}
	
	
	@GetMapping(value="/requiredmedicine")
	public String requiredmedicine(@RequestParam("medid")int medid,Model model) {
		Medicine med=customerservice.getMedicineById(medid);
		model.addAttribute("medicine", med);
		model.addAttribute("orderdetails", new CustomerOrders());
		return "RequiredMedicine";
	}
	
	@PostMapping(value="/validatestock")
	public String validatestock(@ModelAttribute("orderdetails") CustomerOrders custorder,Model model,HttpSession session) {
		Medicine med=customerservice.getMedicineById(custorder.getMedicineid());
		if(med.getQuantity()>=custorder.getQuantity()&& custorder.getQuantity()!=0) {
			model.addAttribute("order", custorder);
			return "payment";
		}else if(custorder.getQuantity()==0) {
			model.addAttribute("orderdetails", new CustomerOrders());
			model.addAttribute("message", "Quantity must be atleast one.");
			return "RequiredMedicine";
		}
		else{
			model.addAttribute("orderdetails", new CustomerOrders());
			model.addAttribute("message", "Medicine you requested is OutofStock");
			return "RequiredMedicine";
		}
	}
	
	@PostMapping(value="/payment")
	public String orderplacment(@ModelAttribute("order") CustomerOrders custorder,Model model,HttpSession session,
			@RequestParam("CardNumber") long cardnum,@RequestParam("cvv") int cvv,@RequestParam("expirydate") String date,@RequestParam("name") String name) {
		Payment paydetails=customerservice.getPaymentDetails(cardnum);
		Medicine med=customerservice.getMedicineById(custorder.getMedicineid());
		String customerid=(String) session.getAttribute("username");
		if(paydetails==null) {
			model.addAttribute("message", "Please enter valid card details");
			return "payment";
		}else {
			boolean b1=customerservice.saveOrder(customerid, custorder);
			if(b1) {
				customerservice.updateMedicineQuantity(custorder.getMedicineid(),custorder.getQuantity());
				model.addAttribute("medicine", med);
				model.addAttribute("message", "Orderplaced");
				return "OrderPlacement";
				
			}else {
				model.addAttribute("order", custorder);
				model.addAttribute("message", "Could not place order. Please try after sometime.");
				return "payment";
			}
		}
	}
	
	
	@PostMapping(value="/sorting")
	public String sorting(@ModelAttribute("medlist") SortMedicine sm,@RequestParam("sortname") String sortname,Model model,HttpServletRequest req,HttpSession session){	
		String searchname=sm.getSearchBy();
		List<Medicine> list=customerservice.medicineListByAll(searchname);
		model.addAttribute("medicinename", searchname);
		List<Medicine> list1=null;
		if(sortname.equalsIgnoreCase("name")) {
			list1=customerservice.sortByName(list);
			model.addAttribute("list", list1);
		}else if(sortname.equalsIgnoreCase("type")){
			list1=customerservice.sortByType(list);
			model.addAttribute("list", list1);
		}else if(sortname.equalsIgnoreCase("illness")) {
			list1=customerservice.sortByIllness(list);
			model.addAttribute("list", list1);
		}else if(sortname.equalsIgnoreCase("brand")) {
			list1=customerservice.sortByBrand(list);
			model.addAttribute("list", list1);
		}	
		return "searchresult";
	}
	
	 @GetMapping(value="/help")
		public String help(Model model)
		{
			model.addAttribute("help",new Help());
			return "ReportIssue";
		}
	 
	 @PostMapping(value="/reportissue")
		public String help1(@ModelAttribute("help") Help h,BindingResult result,Model model,HttpSession session) {
			if(result.hasErrors())
			{
				return "ReportIssue";
			}
			else
			{
			String	custname=(String) session.getAttribute("username");
				int status=customerservice.reportIssue(h, custname);
				if(status==1)
				{
					model.addAttribute("message","your issue is registered");
					return "ReportIssue";
				}
				else
				{
					model.addAttribute("message","Something went wrong");
					return "ReportIssue";
				}
			}
		}
	 
	 @GetMapping(value="/forgotcuserId")
	 public String forgetId(Model model) {
	  return "ForgotCustomerUserid"; }
	 
	  @PostMapping("/getCUserId") 
	  public String getUserid(@RequestParam("contactNumber") String contactNumber,@RequestParam("secretQuestion") String question,@RequestParam("answer") String answer,Model model) {
	  
	  Customer customer =customerservice.getCustomerByContactNumber(contactNumber); 
	  if(customer==null)
	 {
		  model.addAttribute("message","Your contact number is not not registered with us ");
		  return "ForgotCustomerUserid";
	  }
	  
	  if(question.equals(customer.getSecretQuestion()) && answer.equals(customer.getAnswer()))
	  {
	 
		  model.addAttribute("message", "Your User Id is :<b>"+customer.getCustomerId());
		  return "ForgotCustomerUserid"; 
		  } 
	  else
	  {
		  model.addAttribute("message","Invalid secret question credentials ");
		  return "ForgotCustomerUserid";
	 
	 }
	  } 
	 @GetMapping(value="/forgotcpassword")
	 public String forgetPwd(Model model) {
	  return "ForgotCustomerPassword";
	  }
	  
	  @PostMapping(value="/getcpwd")
	  public String getPassword(@RequestParam("userId") String userid,@RequestParam("secretQuestion") String que,@RequestParam("answer") String ans, Model model,HttpSession session) { 
		  Customer customer =customerservice.getCustomerById(userid); 
		  if(customer==null)
			 {
				 model.addAttribute("message", "Your UserId is not not registered with us ");
				 return "ForgotCustomerPassword";
			 }
			 
			if(que.equals(customer.getSecretQuestion()) && ans.equals(customer.getAnswer()))
			 {
				// model.addAttribute("message", "Your password is :<b>"+sc.getPassword());
				
				session.setAttribute("username", customer.getCustomerId());
				 
				 return "ResetCustomerPassword";	 
			 }
			 else
			 {
				 model.addAttribute("message", "Invalid secret question credentials ");
				 return "ForgotCustomerPassword";
				
			 }

	  }
	  
	  @PostMapping("/resetcpwd")
	  public String resetPassword(@RequestParam("password")String pwd,@RequestParam("confirmationpassword")String cpwd,Model model,HttpSession session)
	  {
	  
		  String userid = (String)session.getAttribute("username"); 
		  Customer customer = customerservice.getCustomerById(userid);
		  if(pwd.equals(cpwd) && !pwd.equals(customer.getPassword())) {  
			  boolean status = customerservice.updatePassword(customer,pwd);
			  if(status == true) {  
				  model.addAttribute("message", "reset password Sucessfully");
				  return"ResetCustomerPassword"; 
			  }
			  else
			  { 
				  model.addAttribute("message", "not reset"); 
				  return "ResetCustomerPassword"; 
			}
	 
		  }else if(pwd.equals(customer.getPassword())) {
			  model.addAttribute("message","Enter New password other than old password");
			  return "ResetCustomerPassword";
		  }
		  else
		  {
			  model.addAttribute("message","new password and conformation are not same"); 
			  return "ResetCustomerPassword";
		  }
	  }

	@GetMapping(value="/viewresolutions")
	  public String viewresolutions(Model model,HttpSession session) {
		String userId=(String) session.getAttribute("username");
		List<Help> list=customerservice.getHelpListByCustomerId(userId);
		List<String> hlist=new ArrayList<String>();
		hlist.add("Issue");
		hlist.add("Description");
		hlist.add("Resolution");
		model.addAttribute("resolution","Resolution");
		model.addAttribute("headerlist",hlist);
		model.addAttribute("issuelist",list);
		return "CustomerHome";
	}
	
	@GetMapping(value="/vieworders")
	public String viewOrders(Model model,HttpSession session) {
		String userId=(String) session.getAttribute("username");
		List<CustomerOrders> list=customerservice.getOrderlistByCustomerId(userId);
		model.addAttribute("list",list);
		return"ViewOrders";
	}
	  
	  
	  
	  
	  
	  
	  
	@GetMapping(value="/customerhome")
	public String customerhome() {
		return "CustomerHome";
	}
	
	@GetMapping(value="/customerlogout")
	public String logout(HttpSession session) {
	session.invalidate();
	return "redirect:/";
	}
}

