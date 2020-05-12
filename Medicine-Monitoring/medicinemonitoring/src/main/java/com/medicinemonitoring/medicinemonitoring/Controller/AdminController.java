package com.medicinemonitoring.medicinemonitoring.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medicinemonitoring.medicinemonitoring.models.Admin;
import com.medicinemonitoring.medicinemonitoring.models.AdminLogin;
import com.medicinemonitoring.medicinemonitoring.models.BranchAdmin;
import com.medicinemonitoring.medicinemonitoring.models.CustomerOrders;
import com.medicinemonitoring.medicinemonitoring.models.Help;
import com.medicinemonitoring.medicinemonitoring.models.Medicine;
import com.medicinemonitoring.medicinemonitoring.models.RequestMedicine;
import com.medicinemonitoring.medicinemonitoring.services.AdminService;
import com.medicinemonitoring.medicinemonitoring.services.CustomerService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminservice;

	@GetMapping(value="/admin")
	 public String admin(Model model) {
		 model.addAttribute("admin",new Admin());
		 return "Admin";
	 }
	
	
	@PostMapping(value="/adminreg")
	public String adminreg(@ModelAttribute("admin") Admin adminreg,Model model) {
		int res=adminservice.createAdmin(adminreg);	
		if(res==1) {
			model.addAttribute("message","Your  are already registered.");
			return "Admin";
		}else if(res==2) {
			model.addAttribute("message","Your details are submitted successfully.");
			return "Admin";
		}else {
			model.addAttribute("message","Oops...Something went wrong.");
			return "Admin";
		}
		
	}
	
	@GetMapping(value="/adminlogin")
	public String adminlogin(Model model) {
		model.addAttribute("adminlogin", new AdminLogin());
		return "AdminLogin";
	}
	
	@PostMapping(value="/loginverify")
	public String loginverify(@ModelAttribute("adminlogin") AdminLogin adminlogin,Model model,HttpSession session) {
		boolean b1=adminservice.loginVerification(adminlogin);
		if(b1){
			session.setAttribute("name", adminlogin.getUserId());
			model.addAttribute("list", adminservice.branchlist());
			return "adminhome";
		}
		model.addAttribute("message", "Invalid UserId or Password");
		return "AdminLogin";
	}
	
	@GetMapping(value="/req")
	public String req(Model model) {	
		List<BranchAdmin> list=adminservice.requestlist();
		model.addAttribute("list",list);
		return "RequestList";
	}
	
	@GetMapping(value="/getRequestListDetails")
	public String requestlistdetails(@RequestParam("id") String branchid,Model model) {
		List<BranchAdmin> list=adminservice.requestlist();
		for(BranchAdmin badmin:list) {
			if(badmin.getBranchId().equals(branchid)) {
				model.addAttribute("branchadmin",badmin);
			}
		}
		return "RequestListDetails";
	}
	@GetMapping(value="/AcceptedList")
	public String Acceptreq(Model model)
	{
		model.addAttribute("message", "Accepted");
		List<BranchAdmin> list=adminservice.requestlist();
		List<BranchAdmin> list1=new ArrayList<BranchAdmin>();
		for(BranchAdmin bd:list) {
			if(bd.getStatus().equalsIgnoreCase("yes")){
				list1.add(bd);
			}
		}
		if(list1.isEmpty()) {
			model.addAttribute("ifempty", "Accepted list is empty");
		}
		model.addAttribute("list",list1);
		return "AcceptList";
		
	}
	@GetMapping(value="/RejectedList")
	public String Rejectreq(Model model)
	{	model.addAttribute("message","Rejected");
		List<BranchAdmin> list=adminservice.requestlist();
		List<BranchAdmin> list1=new ArrayList<BranchAdmin>();	
		for(BranchAdmin bd:list) {
			if(bd.getStatus().equalsIgnoreCase("no")){
				list1.add(bd);
			}
		}
		if(list1.isEmpty()) {
			model.addAttribute("ifempty", "Rejected list is empty");
		}
		model.addAttribute("list",list1);
		return "AcceptList";
		
	}
	
	
	@GetMapping(value="/accept")
	public String acceptreq(@RequestParam("id") String id,Model model) {
		String message="Accepted "+id.toUpperCase();
		boolean b1=adminservice.acceptRequest(id);
		if(b1==false) {
			model.addAttribute("message","Unable to update");
		}
		List<BranchAdmin> list=adminservice.requestlist();
		for(BranchAdmin badmin:list) {
			if(badmin.getBranchId().equals(id)) {
				model.addAttribute("branchadmin",badmin);
			}
		}
		model.addAttribute("message",message);
		return "RequestListDetails";
	}
	
	@GetMapping(value="/reject")
	public String rejectreq(@RequestParam("id") String id,Model model) {
		String message="Rejected "+id.toUpperCase();
		boolean b1=adminservice.rejectRequest(id);
		if(b1==false){
			model.addAttribute("message","Oops...Something went wrong");
		}
		List<BranchAdmin> list=adminservice.requestlist();
		for(BranchAdmin badmin:list) {
			if(badmin.getBranchId().equals(id)) {
				model.addAttribute("branchadmin",badmin);
			}
		}
		model.addAttribute("message",message);
		return "RequestListDetails";
	}
	
	
	
	@GetMapping(value="/medicineinfo")
	public String medicineinfo(Model model) {
		model.addAttribute("list",adminservice.medicineList());
		return "MedicineInformation";
	}
	
	@GetMapping("/getMedicineDetails")
	public String getMedicineDetails(@RequestParam("medicineId") int mid,Model model,HttpServletRequest req)
	{
		Medicine med= adminservice.getMedicineById(mid);
		model.addAttribute("medicine", med);
		//req.setAttribute("medicine", med);
		return "medicineInfo";
		
	}
	
	@GetMapping("/edit")
		public String editMedicineInfo(@RequestParam("mid")int mid,Model model) {
		Medicine m = adminservice.getMedicineById(mid);
			model.addAttribute("editMedicine",m);
			return "EditMedicineInfo";
		}
	
	
	@PostMapping("/updateMedicine")
	public String updateMedicine(@ModelAttribute("editMedicine") Medicine med,@RequestParam("id") int id,Model model) {
		boolean res=adminservice.updateMedicineDetails(id, med);
		if(res) {
			model.addAttribute("editMedicine",adminservice.getMedicineById(id));
			model.addAttribute("message","Medicine details updated successfully");
			return "EditMedicineInfo";
		}else {
			return "EditMedicineInfo";
		}
		
	}
	
	@GetMapping(value="/updatestocks")
	public String updateStocks(Model model) {
		List<Medicine> list=adminservice.medicineList();
		model.addAttribute("list", list);
		return "UpdateStocks";
	}
	//updatestocks/editstocks
	@GetMapping(value="/editstocks")
	public String update(@RequestParam("mid")int mid,Model model) {
		Medicine m = adminservice.getMedicineById(mid);
			model.addAttribute("editMedicine",m);
			return "editStock";
	}

	@GetMapping(value="/add") 
	public String medicine(Model model)
	{
		model.addAttribute("addMedicine", new Medicine());
		return "AddMedicine";
	}
	
	
	@PostMapping(value="/addMedicine")
	public String addMedicine(@ModelAttribute("addMedicine") Medicine addmed,Model model)
	{
		boolean add=adminservice.addMedicine(addmed);
		if(add)		{
		  model.addAttribute("addMedicine",new Medicine());
		  model.addAttribute("message", "Medicine added successfully");
		   return "AddMedicine";
		}
		else
		{
			model.addAttribute("message", "Something went wrong");
			return "AddMedicine";
		}
	}
	
	
	
	

	@GetMapping(value="/viewbranchstock")
	public String viewbranchstock(Model model) {
		List<BranchAdmin> list=adminservice.branchlist();
		List<Medicine> medicinelist=adminservice.medicineList();
		Set<String> typelist=new HashSet<String>();
		for(Medicine med:medicinelist) {
			typelist.add(med.getType());
		}
		model.addAttribute("list",list);
		model.addAttribute("medicinetypelist",typelist);
		return "ViewBranchStock";
	}
	
	@GetMapping(value="/viewbranchstockreport")
	public String branchstockReport(@RequestParam("branchid")String branchid,@RequestParam("type") String type,Model model){
		List<Medicine> list=adminservice.getMedicineByType(branchid, type);
		//System.out.println(list);
		model.addAttribute("branchid", branchid);
		model.addAttribute("list", list);
		return "ViewBranchStockReport";
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(value="/generatereport")
	public String generatereport(@RequestParam("bid") String bid,HttpServletRequest req,Model model,HttpSession session) {
		String reportname=req.getParameter("report");
		model.addAttribute("branchid", bid);
		List<Medicine> list=adminservice.getBranchMedicineList(bid);
		if(reportname.equalsIgnoreCase("stock")) {
			model.addAttribute("list", list);
			return "GenerateStockReport";
		}else if(reportname.equalsIgnoreCase("brand")){
			Set<String> list1 = new HashSet<String>();
			list.stream().forEach(t-> {list1.add( t.getManufacturer());});
			model.addAttribute("list",list1);
			return "GenerateBrandReport";
		}else if(reportname.equalsIgnoreCase("illness")) {
			model.addAttribute("list", list);
			return "GenerateIllnessReport";	
		}else if(reportname.equalsIgnoreCase("expirydate")){
			model.addAttribute("list", list);
			return "GenerateExpiryDateReport";
		}else {
			return "GenerateSalesReport";
			}
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(value="/getsalesdetails")
	public String salesReportDetails(@RequestParam("sales") String sales,Model model,HttpServletRequest req,HttpSession session) {
		String userid=req.getParameter("bid");
		model.addAttribute("branchid", userid);
		List<CustomerOrders> list=adminservice.getCustomerOrderList();
		List<Medicine> list1=adminservice.getBranchMedicineList(userid);
		List<CustomerOrders> orderlist=new ArrayList<CustomerOrders>();
		List<String>headerlist=new ArrayList<String>();
		headerlist.add("Medicine Id");
		headerlist.add("Quantity");
		headerlist.add("Orderdate");
		headerlist.add("CustomerName");
		model.addAttribute("headerlist",headerlist);
		if(sales.equalsIgnoreCase("monthly")) {
			int month=Integer.parseInt(req.getParameter("month"));
			int year=Integer.parseInt(req.getParameter("year"));
			if(list!=null) {
			for(CustomerOrders co:list){
				String date=co.getOrderDate().toString();
				String str[]=date.split("-");
				for(Medicine m:list1) {
				if(co.getMedicineid()==m.getMedicineId()&& Integer.parseInt(str[0])==year && Integer.parseInt(str[1])==month) {
					orderlist.add(co);
				}
			}
		}
				
	 }
			model.addAttribute("orderlist",orderlist);
			return "GenerateSalesReport";
		}else if(sales.equalsIgnoreCase("yearly")) {
			int year=Integer.parseInt(req.getParameter("year"));
				if(list!=null) {
					for(CustomerOrders co:list){
						String date=co.getOrderDate().toString();
						String str[]=date.split("-");
						for(Medicine m:list1) {
							if(co.getMedicineid()==m.getMedicineId()&& Integer.parseInt(str[0])==year) {
								orderlist.add(co);
							}
						}
					}
					
				}
			model.addAttribute("orderlist",orderlist);
			return "GenerateSalesReport";
		}else if(sales.equalsIgnoreCase("sixmonths")){
			if(list!=null) {
				for(CustomerOrders co:list) {
					Calendar cal = Calendar.getInstance();
					Calendar ordercal=Calendar.getInstance();
					ordercal.setTime(co.getOrderDate());
					for(Medicine m:list1) {
						if(co.getMedicineid()==m.getMedicineId()&& (co.getOrderDate().getMonth()-cal.getTime().getMonth())<=7){
							orderlist.add(co);
						}
					}
				}
				
			}
			model.addAttribute("orderlist",orderlist);
			return "GenerateSalesReport";
		}
		return "GenerateSalesReport";
	}
	

	
	@GetMapping(value="/viewmedicinerequest")
	public String viewRequests(Model model) {
		List<RequestMedicine> list=adminservice.getMedicineRequestList();
		model.addAttribute("list", list);
		return "MedicineRequestList";
	}

	@GetMapping(value="/getmedicinerequestdetails")
	public String medicineRequestInfo(@RequestParam("token")int token,Model model) {
		RequestMedicine medreq=adminservice.getRequestByToken(token);
		if(medreq==null) {
			model.addAttribute("message", "No requests");
			return "MedicineRequestInfo";
		}
		model.addAttribute("medreq", medreq);
		return "MedicineRequestInfo";		
	}
	
	@GetMapping(value="/approvemedicinerequest")
	public String approvemedicinerequest(@RequestParam("token") int token,Model model) {
		RequestMedicine medreq=adminservice.getRequestByToken(token);
		boolean b1=adminservice.approveMedicineRequest(token);
		if(b1) {
			String message="Approved token"+token;
			model.addAttribute("medreq", medreq);
			model.addAttribute("message", message);
			return "MedicineRequestInfo";
		}
		model.addAttribute("message", "Something went wrong");
		return "MedicineRequestInfo";
	}
	
	@GetMapping(value="/denymedicinerequest")
	public String denymedicinerequest(@RequestParam("token") int token,Model model) {
		RequestMedicine medreq=adminservice.getRequestByToken(token);
		boolean b1=adminservice.denyMedicineRequest(token);
		if(b1) {
			String message="Denied token"+token;
			model.addAttribute("medreq", medreq);
			model.addAttribute("message", message);
			return "MedicineRequestInfo";
		}
		model.addAttribute("message", "Something went wrong");
		return "MedicineRequestInfo";
	}
	
	
	@GetMapping(value="/viewissues")
	public String viewIssues(Model model) {
		List<Help> list=adminservice.viewIssues();
		model.addAttribute("list", list);
		return "CustomerIssueList";
	}
	
	@GetMapping(value="/getissuedetails")
	public String getissuedetails(@RequestParam("token")int token,Model model) {
		Help issue=adminservice.getIssueByToken(token);
		model.addAttribute("issuedetails", issue);
		return "CustomerIssueDetails";
	}
	
	@PostMapping(value="/updateresolution")
	public String updateresolution(@RequestParam("issueid")int token,@RequestParam("resolution")String resolution,Model model) {
		boolean b1=adminservice.updateIssueResolution(token, resolution);
		Help issue=adminservice.getIssueByToken(token);
		model.addAttribute("issuedetails", issue);
		if(b1) {
			model.addAttribute("message", "Sucessfully Resolved");
			return "CustomerIssueDetails";
		}else {
			model.addAttribute("message", "Unable to update.Please provide resoultion after some time.");
			return "CustomerIssueDetails";
		}
	}
	
	@GetMapping(value="/forgotuserId")
	 public String forgetId(Model model) {
	  return "ForgotUserid"; }
	 
	  @PostMapping("/getUserId") 
	  public String getUserid(@RequestParam("contactNumber") String contactNumber,@RequestParam("secretQuestion") String question,@RequestParam("answer") String answer,Model model) 
	  {
		  Admin admin =adminservice.getAdminByContactNumber(contactNumber); 
		  if(admin==null)
		  {
		  model.addAttribute("message","Your contact number is not not registered with us ");
		  return "ForgotUserid";
		  }
		  if(question.equals(admin.getSecretQuestion()) && answer.equals(admin.getAnswer()))
		  {
			  model.addAttribute("message", "Your User Id is :<b>"+admin.getAdminId());
			  return "ForgotUserid"; 
		  } 
		  else
		  {
			  model.addAttribute("message","Invalid secret question credentials ");
			  return "ForgotUserid";
	 
		  }
	  } 
	  
	  
	 @GetMapping(value="/forgotpassword")
	 public String forgetPwd(Model model) {
	  return "ForgotPassword";
	  }
	  
	 
	  @PostMapping(value="/getpwd")
	  public String getPassword(@RequestParam("userId") String userid,@RequestParam("secretQuestion") String que,@RequestParam("answer") String ans, Model model,HttpSession session) { 
		  Admin admin =adminservice.getAdminById(userid);  
		  if(admin==null)
			 {
				 model.addAttribute("message", "Your UserId is not not registered with us ");
				 return "ForgotPassword";
			 }
			 
			if(que.equals(admin.getSecretQuestion()) && ans.equals(admin.getAnswer()))
			 {
				// model.addAttribute("message", "Your password is :<b>"+sc.getPassword());
				
				session.setAttribute("userid", admin.getAdminId());
				 
				 return "ResetPassword";	 
			 }
			 else
			 {
				 model.addAttribute("message", "Invalid secret question credentials ");
				 return "ForgotPassword";
				
			 }

	  }
	  
	  @PostMapping("/resetpwd")
	  public String resetPassword(@RequestParam("password")String pwd,@RequestParam("confirmationpassword")String cpwd,Model model,HttpSession session)
	  {  
		  String userid = (String)session.getAttribute("userid"); 
		  Admin admin=adminservice.getAdminById(userid);
		  if(pwd.equals(cpwd) && !pwd.equals(admin.getPassword())) { 
			  boolean status = adminservice.updatePassword(admin,pwd);
			  if(status == true) {  
				  model.addAttribute("message", "Password changed Sucessfully");
				  return"ResetPassword"; 
			  }
			  else
			  {
				  model.addAttribute("message", "Unable to update password"); 
				  return "ResetPassword"; 
			  }
	 
		  }else if(pwd.equals(admin.getPassword())) {
			  model.addAttribute("message","Enter New password other than Old password");
			  return "ResetPassword";
		  }
		  else
		  {
			  model.addAttribute("message","New password and confirmation password must be same"); 
			  return "ResetPassword";
		  }
	  }

	
	@GetMapping(value="/adminhome")
	public String home(Model model){
		model.addAttribute("list", adminservice.branchlist());
		model.addAttribute("list",adminservice.branchlist());
		return "adminhome";
	}
	
	@GetMapping(value="/adminlogout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
}
