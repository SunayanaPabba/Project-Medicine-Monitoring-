package com.medicinemonitoring.medicinemonitoring.Controller;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.medicinemonitoring.medicinemonitoring.models.BranchAdmin;
import com.medicinemonitoring.medicinemonitoring.models.BranchAdminLogin;
import com.medicinemonitoring.medicinemonitoring.models.CustomerOrders;
import com.medicinemonitoring.medicinemonitoring.models.Medicine;
import com.medicinemonitoring.medicinemonitoring.models.RequestMedicine;
import com.medicinemonitoring.medicinemonitoring.services.BranchAdminService;

@Controller
public class BranchAdminController {
	@Autowired
	private BranchAdminService badminservice;
	@GetMapping(value="/branchadmin")
	public String branchadmin(Model model) {
		model.addAttribute("branchadmin",new BranchAdmin());
		return "BranchAdmin";
	}
	@PostMapping(value="/branchadminreg")
	public String adminreg(@ModelAttribute("branchadmin") BranchAdmin badminreg,Model model) {
		int res=badminservice.createBranchAdmin(badminreg);
		if(res==1) {
			model.addAttribute("message","This Branch was already registered.");
			return "BranchAdmin";
		}else if(res==2) {
			model.addAttribute("message","This userId already exitsts. Use different UserId");
			return "BranchAdmin";
		}else if(res==3) {
		model.addAttribute("message","Your details are submitted successfully.");
		return "BranchAdmin";
		}else {
			model.addAttribute("message","Oops...Something went wrong.");
			return "BranchAdmin";
		}
	}
	@GetMapping(value="/branchadminlogin")
	public String adminlogin(Model model) {
		model.addAttribute("branchadminlogin", new BranchAdminLogin());
		return "BranchAdminLogin";
	}
	@PostMapping(value="/branchloginverify")
	public String branchloginverify(@ModelAttribute("branchadminlogin") BranchAdminLogin badminlogin,Model model,HttpSession session) {
		int res=badminservice.loginVerification(badminlogin);
			if(res==1) {
				session.setAttribute("username", badminlogin.getUserId());
				return "BranchAdminHome";
			}else if(res==2){
				model.addAttribute("message", "Wait for admin confirmation");
				return "BranchAdminLogin";
			}else {
				model.addAttribute("message", "Invalid userId or password");
				return "BranchAdminLogin";
			}
					
	}
	
	@GetMapping(value="/branchmedicineinfo")
	public String medicineinfo(@RequestParam("id") String userid,Model model) {
		
		
		model.addAttribute("list",badminservice.MedicineList(userid));
		return "BranchMedicineInformation";
	}
	 
	@GetMapping(value="/addBranchMedicine") 
	public String medicine(Model model)
	{
		model.addAttribute("addBranchMedicine", new Medicine());
		return "AddBranchMedicine";
	}
	
	
	@PostMapping(value="/addBranchMedicineProcess")
	public String addMedicine(@ModelAttribute("addBranchMedicine") Medicine addmed,Model model,HttpSession session)
	{
		String userid=(String) session.getAttribute("username");
		boolean add=badminservice.addBranchMedicine(addmed,userid);
		if(add){
		  model.addAttribute("addBranchMedicine",new Medicine());
		  model.addAttribute("message", "Medicine added successfully");
		   return "AddBranchMedicine";
		}
		else
		{
			model.addAttribute("message", "Something went wrong");
			return "AddBranchMedicine";
		}
	}
	
	@GetMapping(value="/getBranchMedicineDetails")
	public String getMedicineDetails(@RequestParam("userid")String userid,@RequestParam("medicineId") int mid,Model model)
	{
		Medicine med= badminservice.getBranchMedicineById(userid,mid);
		model.addAttribute("branchmedicine", med);
		//req.setAttribute("medicine", med);
		return "BranchmedicineInfo";
		
	}

	

	@GetMapping("/editBranchMedicine")
	public String editMedicineInfo(@RequestParam("userid")String userid,@RequestParam("mid")int mid,Model model) {
	Medicine m = badminservice.getBranchMedicineById(userid,mid);
		model.addAttribute("editBranchMedicine",m);
		return "EditBranchMedicineInfo";
	}

	
	
	@PostMapping("/updateBranchMedicine")
	public String updateMedicine(@RequestParam("medid") int medicineid,@ModelAttribute("editBranchMedicine") Medicine med,Model model,HttpSession session) {
		String userid=(String) session.getAttribute("username");
		boolean res=badminservice.updateBranchMedicineDetails(userid, medicineid, med);
		if(res) {
			model.addAttribute("editBranchMedicine",badminservice.getBranchMedicineById(userid, medicineid));
			model.addAttribute("message","Medicine details updated successfully");
			return "EditBranchMedicineInfo";
		}else {
			return "EditBranchMedicineInfo";
		}
	}
		@PostMapping("/updateBranchMedicineStock")
		public String updateMedicineStock(@RequestParam("medid") int medicineid,@ModelAttribute("editBranchMedicine") Medicine med,Model model,HttpSession session) {
			String userid=(String) session.getAttribute("username");
			boolean res=badminservice.updateBranchMedicineDetails(userid, medicineid, med);
			if(res) {
				model.addAttribute("editBranchMedicine",badminservice.getBranchMedicineById(userid, medicineid));
				model.addAttribute("message","Medicine details updated successfully");
				return "EditBranchStock";
			}else {
				return "EditBranchStock";
			}
			
		}
	

	
	@GetMapping(value="/updatebranchstocks")
	public String updateStocks(@RequestParam("userid") String userid,Model model) {
		List<Medicine> list=badminservice.MedicineList(userid);
		model.addAttribute("list", list);
		return "UpdateBranchStocks";
	}
	
	@GetMapping(value="/editbranchstocks")
	public String update(@RequestParam("userid")String userid,@RequestParam("mid")int mid,Model model) {
		Medicine medicine = badminservice.getBranchMedicineById(userid,mid);
			model.addAttribute("editBranchMedicine",medicine);
			return "EditBranchStock";
	}
	
	

	
	
		
	@GetMapping(value="/branchStock")
	public String stockReport(@RequestParam("userid")String userid,Model model){
		List<Medicine> list=badminservice.MedicineList(userid);
		//System.out.println(list);
		model.addAttribute("list", list);
		return "BranchStockReport";
	}
	
	@GetMapping(value="/branchBrand")
	public String brandReport(@RequestParam("userid")String userid,Model model){
		List<Medicine> list1=badminservice.MedicineList(userid);
		Set<String> brands = new HashSet<String>();
		list1.stream().forEach(t-> {brands.add( t.getManufacturer());});
		//System.out.println(list1);
		model.addAttribute("list",brands);
		return "BranchBrandReport";
	}
	
	@GetMapping(value="/branchIllness")
	public String illnessReport(@RequestParam("userid")String userid,Model model){
		List<Medicine> list=badminservice.MedicineList(userid);
		//System.out.println(list);
		model.addAttribute("list", list);
		return "BranchIllnessReport";
	}
	
	@GetMapping(value="/branchExpiryDate")
	public String expiryDateReport(@RequestParam("userid")String userid,Model model){
		List<Medicine> list=badminservice.MedicineList(userid);
		//System.out.println(list);
		model.addAttribute("list", list);
		return "BranchExpiryDateReport";
	}

	
	@GetMapping(value="/branchSales")
	public String branchSaleReport() {
		return "BranchSalesReport";
	}
	
	
	@SuppressWarnings("deprecation")
	@PostMapping(value="/getsalesreportdetails")
	public String salesReportDetails(@RequestParam("sales") String sales,Model model,HttpServletRequest req,HttpSession session) {
		String userid=(String) session.getAttribute("username");
		List<CustomerOrders> list=badminservice.getCustomerOrderList();
		List<Medicine> list1=badminservice.MedicineList(userid);
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
			return "BranchSalesReport";
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
			return "BranchSalesReport";
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
			return "BranchSalesReport";
		}
		return "BranchSalesReport";
	}
	
	
	
	
	
	@GetMapping(value="/raisemedicinerequest")
	public String raisemedicinerequest(Model model) {
		model.addAttribute("addMedicineRequest", new RequestMedicine());
		return "RequestMedicine";
	}
	
	@PostMapping(value="/addMedicineRequest")
	public String addrequest(@ModelAttribute("addMedicineRequest") RequestMedicine rmed,Model model,HttpSession session) {
		String userid=(String) session.getAttribute("username");
		boolean b1=badminservice.addRequest(userid, rmed);
		model.addAttribute("addRequest", new RequestMedicine());
		if(b1)
		{
			model.addAttribute("message","Requested");
			return "RequestMedicine";
		}else {
			model.addAttribute("message","Something went wrong");
			return "RequestMedicine";

		}
		
	}
	
	
	@GetMapping(value="/forgotbuserId")
	 public String forgetId(Model model) {
	  return "ForgotBranchAdminUserid"; }
	 
	  @PostMapping("/getBUserId") 
	  public String getUserid(@RequestParam("contactNumber") String contactNumber,@RequestParam("secretQuestion") String question,@RequestParam("answer") String answer,Model model) 
	  {
	  BranchAdmin badmin =badminservice.getBranchAdminByContactNumber(contactNumber);  
	  if(badmin==null)
	 {
		  model.addAttribute("message","Your contact number is not not registered with us ");
		  return "ForgotBranchAdminUserid";
	  }
	  
	  if(question.equals(badmin.getSecretQuestion()) && answer.equals(badmin.getAnswer()))
	  {
	 
		  model.addAttribute("message", "Your User Id is :<b>"+badmin.getAdminId());
		  return "ForgotBranchAdminUserid"; 
		  } 
	  else
	  {
		  model.addAttribute("message","Invalid secret question credentials ");
		  return "ForgotBranchAdminUserid";
	 
	 }
	}
	  
	  
	 @GetMapping(value="/forgotbpassword")
	 public String forgetPwd(Model model) {
	  return "ForgotBranchAdminPassword";
	  }
	  
	 
	  @PostMapping(value="/getbpwd")
	  public String getPassword(@RequestParam("userId") String userid,@RequestParam("secretQuestion") String que,@RequestParam("answer") String ans, Model model,HttpSession session) { 
		  BranchAdmin badmin =badminservice.getBranchAdminById(userid); 
		  if(badmin==null)
			 {
				 model.addAttribute("message", "Your UserId is not not registered with us");
				 return "ForgotBranchAdminPassword";
			 }
			 
			if(que.equals(badmin.getSecretQuestion()) && ans.equals(badmin.getAnswer()))
			 {
				// model.addAttribute("message", "Your password is :<b>"+sc.getPassword());
				session.setAttribute("username", badmin.getAdminId());				 
				 return "ResetBranchAdminPassword";	 
			 }
			 else
			 {
				 model.addAttribute("message", "Invalid secret question credentials ");
				 return "ForgotBranchAdminPassword";
				
			 }

	  }
	  
	  @PostMapping("/resetbpwd")
	  public String resetPassword(@RequestParam("password")String pwd,@RequestParam("confirmationpassword")String cpwd,Model model,HttpSession session)
	  {
		  String userid = (String)session.getAttribute("username");
		  BranchAdmin badmin = badminservice.getBranchAdminById(userid);
		  if(pwd.equals(cpwd) && !pwd.equals(badmin.getPassword())) { 
			  boolean status = badminservice.updatePassword(badmin,pwd);
			  if(status == true) {  
				  model.addAttribute("message", "Password changed sucessfully");
				  return"ResetBranchAdminPassword"; 
			  }
			  else
			  { 
				  model.addAttribute("message", "Unable to update password"); 
				  return "ResetBranchAdminPassword"; 
			  }
	 
		  }else if(pwd.equals(badmin.getPassword())) {
				model.addAttribute("message","Enter New password other than old password");
				  return"ResetBranchAdminPassword"; 
			  }
		  else
		  {
			  model.addAttribute("message","New password and Confirmation must be same"); 
			  return "ResetBranchAdminPassword";
		  }
	  }

	
	
	
	@GetMapping("/branchadminhome")
	public String branchadminhome()
	{
		
		return "BranchAdminHome";
	}

	@GetMapping("/branchadminlogout")
	public String adminLogOut(HttpSession session)
	{
		session.invalidate();
		
		return "redirect:/";
	}




}
