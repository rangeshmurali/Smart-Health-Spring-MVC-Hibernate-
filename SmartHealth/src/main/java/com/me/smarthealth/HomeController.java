package com.me.smarthealth;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.me.dao.UserDao;
import com.me.model.DailyRecord;
import com.me.model.Doctor;
import com.me.model.Hospital;
import com.me.model.HospitalFinanceManager;
import com.me.model.Invoice;
import com.me.model.Login;
import com.me.model.Message;
import com.me.model.MonthlyRecord;
import com.me.model.Report;
import com.me.model.SmartId;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	HttpSession session;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@Autowired
	@Qualifier("loginValidator")
	private Validator validator;
	
	@Autowired
	@Qualifier("dailyRecordValidator")
	private Validator dailyRecordValidator;
	
	@Autowired
	@Qualifier("doctorValidator")
	private Validator doctorValidator;
	
	@Autowired
	@Qualifier("financeManagerValidator")
	private Validator financeManagerValidator;
	
	@Autowired
	@Qualifier("hospitalValidator")
	private Validator hospitalValidator;
	
	@Autowired
	@Qualifier("invoiceValidator")
	private Validator invoiceValidator;
	
	@Autowired
	@Qualifier("messageValidator")
	private Validator messageValidator;
	
	@Autowired
	@Qualifier("monthlyRecordValidator")
	private Validator monthlyRecordValidator;
	
	@Autowired
	@Qualifier("reportValidator")
	private Validator reportValidator;
	
	@Autowired
	@Qualifier("smartIdValidator")
	private Validator smartIdValidator;

	@Autowired
	private UserDao userDao;
	
	@InitBinder("login")
	private void initLoginBinder (WebDataBinder binder){
		binder.setValidator(validator);
	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Login login = new Login();
		Login l = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length >= 2) {
			try {
				for (int i = 0; i <= cookies.length; i++) {
					l = userDao.loginUserByNameAndPassword(
							cookies[i].getValue(), cookies[i + 1].getValue());

					if (l != null) {
						session = request.getSession();
						session.setAttribute("login", l);
						model.addAttribute("login", l);
						model.addAttribute("smartId", l.getSmartId());
						return chart(model);
					}
				}
			} catch (Exception e) {

			}
		}

		model.addAttribute("login", login);

		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String submitForm(Model model, @Validated Login login,
			BindingResult result, HttpServletResponse response, HttpServletRequest request) {
		model.addAttribute("login", login);
		String remember = (request.getParameter("remember") != null ? "Remember": "No");
		String returnVal = "userHomePage";
		//if (result.hasErrors()) {
		//	return "home";
		//} else {
			try {
				Login userLogin = userDao.loginUserByNameAndPassword(
						login.getUserName(), login.getUserPassword());
				if (userLogin != null) {
					if (userLogin.getRole().equalsIgnoreCase("User")) {
						SmartId smartId = userLogin.getSmartId();
						session = request.getSession();
						model.addAttribute("smartId", smartId);
						session.setAttribute("smartId", smartId);
						chart(model);
						if (remember != null && remember.equalsIgnoreCase("Remember")) {
							Cookie userName = new Cookie("userName", login.getUserName());
							userName.setMaxAge(3600 * 24 * 7);
							response.addCookie(userName);
							Cookie userPassword = new Cookie("userPassword", login.getUserPassword());
							userPassword.setMaxAge(3600 * 24 * 7);
							response.addCookie(userPassword);
						}
						return returnVal;
					} else if (userLogin.getRole().equalsIgnoreCase("Doctor")) {
						Doctor doctor = userLogin.getDoctor();
						session = request.getSession();
						session.setAttribute("doctor", doctor);
						model.addAttribute("doctor", doctor);
						if (remember != null && remember.equalsIgnoreCase("Remember")) {
							Cookie userName = new Cookie("userName", login.getUserName());
							userName.setMaxAge(3600 * 24 * 7);
							response.addCookie(userName);
							Cookie userPassword = new Cookie("userPassword", login.getUserPassword());
							userPassword.setMaxAge(3600 * 24 * 7);
							response.addCookie(userPassword);
						}
						return goToViewPatientRecord(model);
					} else if (userLogin.getRole().equalsIgnoreCase(
							"FinanceManager")) {
						HospitalFinanceManager hospitalFinanceManager = userLogin
								.getHospitalFinanceManager();
						session = request.getSession();
						session.setAttribute("financeManager",
								hospitalFinanceManager);
						model.addAttribute("financeManger",
								hospitalFinanceManager);
						if (remember != null && remember.equalsIgnoreCase("Remember")) {
							Cookie userName = new Cookie("userName", login.getUserName());
							userName.setMaxAge(3600 * 24 * 7);
							response.addCookie(userName);
							Cookie userPassword = new Cookie("userPassword", login.getUserPassword());
							userPassword.setMaxAge(3600 * 24 * 7);
							response.addCookie(userPassword);
						}
						return goTogenerateInvoice(model);
					}

				} else {
					String error = "UserName or Password you entered is incorrect!!";
					model.addAttribute("error", error);
					return "home";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		//}

		return "home";

	}
	
	@RequestMapping(value = "index")
	public String chart(Model model) throws Exception{
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		List<DailyRecord> dailyList = userDao.getRecordGraph(smartId.getSmartId());
		String[] gendate = new String[dailyList.size()];
		Float[] walkingDistance = new Float[dailyList.size()];
		Float[] runningDistance = new Float[dailyList.size()];
		Integer[] flightsClimed = new Integer[dailyList.size()];
		for(int i=0; i<dailyList.size(); i++){
			DailyRecord dr= dailyList.get(i);
			gendate[i] = dr.getDate();
			walkingDistance[i] = dr.getWalkingDistance();
			runningDistance[i] = dr.getRunningDistance();
			flightsClimed[i] = dr.getFlightsClimed();
		}
			model.addAttribute("date", gendate);
			model.addAttribute("walkingDistance", walkingDistance);
			model.addAttribute("runningDistance", runningDistance);
			model.addAttribute("flightsClimed", flightsClimed);
			return "userHomePage";
		

	}

	@RequestMapping(value = "registration")
	public String goToRegistrationPage(Model model) {
		Login login = new Login();
		SmartId smartId = new SmartId();
		login.setSmartId(smartId);
		model.addAttribute("login", login);
		// model.addAttribute("smartId", smartId);
		return "registration";
	}

	@RequestMapping(value = "forgotPassword")
	public String goToForgotPassword(Model model) {
		Login login = new Login();
		SmartId smartId = new SmartId();
		login.setSmartId(smartId);
		model.addAttribute("login", login);
		// model.addAttribute("smartId", smartId);
		return "forgotUserName";
	}

	@RequestMapping(value = "paymentPage")
	public String goToPaymentPage(Model model,
			@RequestParam("invoiceId") int invoiceId) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		try {
			String payment = userDao.checkPayment(smartId, invoiceId);
			if (payment.equalsIgnoreCase("paid")) {
				String error = "Payment already made!";
				model.addAttribute("error", error);
				return goToViewMyInvoice(model);
			} else {
				model.addAttribute("smartId", smartId);
				model.addAttribute("invoiceId", invoiceId);
				return "paymentPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return goToViewMyInvoice(model);

	}

	@RequestMapping(value = "preferredHospital")
	public String goTopreferredHospital(Model model) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		try {
			List<Hospital> hospitalList = userDao
					.selectPreferredHospitals(smartId.getSmartId());
			if (hospitalList != null) {
				model.addAttribute("hospitalList", hospitalList);
				return "selectPreferredHospital";

			} else {
				String error = "No preferred hospital selected";
				model.addAttribute("error", error);
				return "selectPreferredHospital";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "selectPreferredHospital";
	}

	@RequestMapping(value = "messageUser")
	public String goToMessageUser(Model model) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		try {
			List<Message> messageList = userDao.viewUserMessages(smartId
					.getLogin().getUserName());
			if (messageList != null) {
				model.addAttribute("messageList", messageList);
				return "sendMessageUser";

			} else {
				String error = "No messages to display!";
				model.addAttribute("error", error);
				return "sendMessageUser";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sendMessageUser";
	}

	@RequestMapping(value = "doctorMessages")
	public String goToDoctorMessages(Model model) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("doctor", doctor);
		try {
			List<Message> messageList = userDao.viewUserMessages(doctor
					.getDoctorLogin().getUserName());
			if (messageList != null) {
				model.addAttribute("messageList", messageList);
				return "ViewDoctorMessage";

			} else {
				String error = "No messages to display!";
				model.addAttribute("error", error);
				return "ViewDoctorMessage";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ViewDoctorMessage";
	}

	@RequestMapping(value = "deleteDoctor")
	public String goToDeleteDoctorMessages(Model model,
			@RequestParam("delete") int[] delete) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("doctor", doctor);
		try {
			for (int i = 0; i < delete.length; i++) {
				userDao.deleteMessage(delete[i]);
			}
			String success = "Messages Deleted";
			model.addAttribute("success", success);
			return goToDoctorMessages(model);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ViewDoctorMessage";
	}

	@RequestMapping(value = "deleteUser")
	public String goToDeleteUserMessages(Model model,
			@RequestParam("delete") int[] delete) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		try {
			for (int i = 0; i < delete.length; i++) {
				userDao.deleteMessage(delete[i]);
			}
			String success = "Messages Deleted";
			model.addAttribute("success", success);
			return goToMessageUser(model);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sendMessageUser";
	}

	@RequestMapping(value = "sendMessageUser")
	public String goToSendMessageUser(Model model) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		Message message = new Message();
		model.addAttribute("message", message);
		return "newMessage";
	}
	
	@RequestMapping(value = "changePassword")
	public String goToChangePassword(Model model) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("login", smartId.getLogin());
		return "userChangePassword";
	}
	
	@RequestMapping(value = "changeDoctorPassword")
	public String goToChangeDoctorPassword(Model model) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("login", doctor.getDoctorLogin());
		return "changeDoctorPassword";
	}
	
	@RequestMapping(value = "changeManagerPassword")
	public String goToChangeManagerPassword(Model model) {
		HospitalFinanceManager financeManager = (HospitalFinanceManager) session.getAttribute("financeManager");
		model.addAttribute("login", financeManager.getManagerLogin());
		return "changeManagerPassword";
	}

	@RequestMapping(value = "checkCurrentPassowrd")
	public @ResponseBody String goToVerifyPassword(Model model, @RequestParam("currentPassword") String password) {
		String valid = "0";
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("login", smartId.getLogin());
		if(smartId.getLogin().getUserPassword().equals(password)){
			valid = "1";
		}
		return valid;
	}
	
	@RequestMapping(value = "checkDoctorCurrentPassowrd")
	public @ResponseBody String goToVerifyDoctorPassword(Model model, @RequestParam("currentPassword") String password) {
		String valid = "0";
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("login", doctor.getDoctorLogin());
		if(doctor.getDoctorLogin().getUserPassword().equals(password)){
			valid = "1";
		}
		return valid;
	}
	
	@RequestMapping(value = "checkManagerCurrentPassowrd")
	public @ResponseBody String goToVerifyManagerPassword(Model model, @RequestParam("currentPassword") String password) {
		String valid = "0";
		HospitalFinanceManager financeManager = (HospitalFinanceManager) session.getAttribute("financeManager");
		model.addAttribute("login", financeManager.getManagerLogin());
		if(financeManager.getManagerLogin().getUserPassword().equals(password)){
			valid = "1";
		}
		return valid;
	}
	
	
	@RequestMapping(value = "replyDoctor")
	public String goToReplyDoctor(Model model,
			@RequestParam("userUserName") String userName) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("doctor", doctor);
		Message message = new Message();
		model.addAttribute("message", message);
		model.addAttribute("userName", userName);
		return "replyDoctor";
	}

	@RequestMapping(value = "replyUser")
	public String goToReplyUser(Model model,
			@RequestParam("doctorUserName") String userName) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		Message message = new Message();
		model.addAttribute("message", message);
		model.addAttribute("userName", userName);
		return "replyUser";
	}

	@RequestMapping(value = "viewMyReport")
	public String goToViewMyReport(Model model) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		try {
			List<Report> reportList = userDao
					.viewMyReport(smartId.getSmartId());
			if (reportList != null) {
				model.addAttribute("reportList", reportList);
				return "viewMyReport";

			} else {
				String error = "No reports generated!";
				model.addAttribute("error", error);
				return "viewMyReport";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userHomePage";
	}

	@RequestMapping(value = "newMessageToDoctor")
	public String goToNsewMessageToDoctort(Model model,
			@Validated Message message, BindingResult result) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		model.addAttribute("message", message);
		if (result.hasErrors()) {
			return "newMessage";
		}
		try {
			userDao.newMessage(message, smartId.getLogin().getUserName());
			String success = "Message Sent";
			model.addAttribute("success", success);
			return goToMessageUser(model);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userHomePage";
	}

	@RequestMapping(value = "messageToUser")
	public String goToMessageToUser(Model model, @Validated Message message,
			BindingResult result) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("doctor", doctor);
		model.addAttribute("message", message);
		if (result.hasErrors()) {
			return "replyDoctor";
		}
		try {
			userDao.newMessage(message, doctor.getDoctorLogin().getUserName());
			String success = "Message Sent";
			model.addAttribute("success", success);
			return goToDoctorMessages(model);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "replyDoctor";
	}

	@RequestMapping(value = "messageToDoctor")
	public String goToMessageToDoctor(Model model, @Validated Message message,
			BindingResult result) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		model.addAttribute("message", message);
		if (result.hasErrors()) {
			return "replyUser";
		}
		try {
			userDao.newMessage(message, smartId.getLogin().getUserName());
			String success = "Message Sent";
			model.addAttribute("success", success);
			return goToMessageUser(model);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "replyUser";
	}

	@RequestMapping(value = "viewMyInvoice")
	public String goToViewMyInvoice(Model model) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		try {
			List<Invoice> invoiceList = userDao.viewMyInvoice(smartId
					.getSmartId());
			if (invoiceList != null) {
				model.addAttribute("invoiceList", invoiceList);
				return "viewMyInvoice";

			} else {
				String error = "No invoice generated!";
				model.addAttribute("error", error);
				return "viewMyInvoice";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userHomePage";
	}

	@RequestMapping(value = "analyzePatient")
	public String goToViewPatientRecord(Model model) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("doctor", doctor);
		try {
			// List<MonthlyRecord> monthly;
			Hospital hospital = doctor.getHospital();
			List<SmartId> smartIdList = hospital.getSmartId();
			List<MonthlyRecord> montlyList = new ArrayList<MonthlyRecord>();
			for (SmartId smartId : smartIdList) {
				List<MonthlyRecord> montlyList2 = userDao
						.getMonthlyRecordDoctor(smartId.getSmartId());
				for (MonthlyRecord monthlyRecord : montlyList2) {
					montlyList.add(monthlyRecord);
				}
			}
			if (montlyList != null) {
				model.addAttribute("monthlyList", montlyList);
				return "doctorLogin";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "doctorLogin";
	}

	@RequestMapping(value = "generateInvoice")
	public String goTogenerateInvoice(Model model) {
		HospitalFinanceManager hospitalFinanceManager = (HospitalFinanceManager) session
				.getAttribute("financeManager");
		model.addAttribute("financeManager", hospitalFinanceManager);
		try {
			// List<MonthlyRecord> monthly;
			Hospital hospital = hospitalFinanceManager.getHospital();
			List<SmartId> smartIdList = hospital.getSmartId();
			List<Report> reportList = new ArrayList<Report>();
			for (SmartId smartId : smartIdList) {
				List<Report> report2 = userDao.viewMyReport(smartId
						.getSmartId());
				for (Report report : report2) {
					if (report.getDoctor().getHospital().getHospitalId() == hospital
							.getHospitalId()) {
						reportList.add(report);
					}
				}
			}
			if (reportList != null) {
				model.addAttribute("reportList", reportList);
				return "HospitalFinanceLogin";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "HospitalFinanceLogin";
	}

	@RequestMapping(value = "viewReport")
	public String goToViewGeneratedReport(Model model) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("doctor", doctor);
		try {
			List<Report> reportList = userDao.viewGeneratedReport(doctor
					.getEmployeeId());
			if (reportList != null) {
				model.addAttribute("reportList", reportList);
				return "viewGeneratedReport";
			} else {
				String error = "No reports generated!";
				model.addAttribute("error", error);
				return "viewGeneratedReport";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "doctorLogin";
	}

	@RequestMapping(value = "viewManagerInvoice")
	public String goToViewManagerInvoice(Model model) {
		HospitalFinanceManager finanaceManager = (HospitalFinanceManager) session
				.getAttribute("financeManager");
		model.addAttribute("financeManager", finanaceManager);
		try {
			List<Invoice> invoiceList = userDao
					.viewGeneratedInvoice(finanaceManager.getEmployeeId());
			if (invoiceList != null) {
				model.addAttribute("invoiceList", invoiceList);
				return "viewManagerInvoice";
			} else {
				String error = "No reports generated!";
				model.addAttribute("error", error);
				return "viewGeneratedReport";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "HospitlaFinanceLogin";
	}

	@RequestMapping(value = "doctorViewPatient")
	public String goToDoctorViewPatient(Model model,
			@RequestParam("smartId") int smartId) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("doctor", doctor);
		try {
			SmartId smId = userDao.getPatientMedicalRecord(smartId);
			if (smId != null) {
				model.addAttribute("smartId", smId);
				return "medicalRecord";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "doctorLogin";
	}

	@RequestMapping(value = "assign")
	public String goToDoctorAssignSmartId(Model model,
			@RequestParam("smartId") int smartId,
			@RequestParam("month") String month) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("doctor", doctor);
		try {
			MonthlyRecord monthly = userDao.getCheckAssign(smartId, month);
			if (monthly.getAssign() != null) {
				String error = "The SmartId is already Assigned! Please assign different SmartId";
				model.addAttribute("error", error);
				return goToViewPatientRecord(model);
			} else {
				int update = userDao.getAssignDoctor(smartId,
						doctor.getEmployeeId(), month);
				if (update == 1) {
					String success = "The SmartId is Assigned to You!";
					model.addAttribute("success", success);
					return goToViewPatientRecord(model);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "doctorLogin";
	}

	@RequestMapping(value = "assignInvoice")
	public String goToDoctorAssignInvoice(Model model,
			@RequestParam("smartId") int smartId,
			@RequestParam("reportId") int reportId) {
		HospitalFinanceManager financeManager = (HospitalFinanceManager) session
				.getAttribute("financeManager");
		model.addAttribute("financeManager", financeManager);
		try {
			Report report = userDao.getCheckManagerAssign(smartId, reportId);
			if (report.getAssignedFinanceManager() != null) {
				String error = "The invoice is already Assigned! Please assign different Invoice";
				model.addAttribute("error", error);
				return goTogenerateInvoice(model);
			} else {
				int update = userDao.AssignFinanceManager(smartId, reportId,
						financeManager.getEmployeeId());
				if (update == 1) {
					String success = "The Invoice is Assigned to You!";
					model.addAttribute("success", success);
					return goTogenerateInvoice(model);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "HospitalFinanceLogin";
	}

	@RequestMapping(value = "generateReport")
	public String goTogenerateReport(Model model,
			@RequestParam("smartId") int smartId,
			@RequestParam("month") String month) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("doctor", doctor);
		try {
			MonthlyRecord monthly = userDao.getCheckAssign(smartId, month);
			if (monthly.getAssign() != null) {
				MonthlyRecord monthlyRecord = userDao.getCheckAssign(smartId,
						month);
				int doctorId = Integer.parseInt(monthly.getAssign());
				if (doctorId != doctor.getEmployeeId()) {
					String error = "The SmartId is assigned to a different doctor! Only assigned doctor can generate report!";
					model.addAttribute("error", error);
					return goToViewPatientRecord(model);
				} else if (monthlyRecord.getGenerated() == null) {
					model.addAttribute("doctor", doctor);
					model.addAttribute("montlyRecord", monthly);
					model.addAttribute("smartId", smartId);
					model.addAttribute("month", month);
					Report report = new Report();
					model.addAttribute("report", report);
					return "generateReport";

				} else {
					String error = "Report for this smartId is already generated!";
					model.addAttribute("error", error);
					return goToViewPatientRecord(model);
				}
			} else if (monthly.getAssign() == null) {
				String error = "The SmartId is not Assigned! Please assign it to you for generating report!";
				model.addAttribute("error", error);
				return goToViewPatientRecord(model);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "doctorLogin";
	}

	@RequestMapping(value = "Invoice")
	public String goToGenerateInvoice(Model model,
			@RequestParam("smartId") int smartId,
			@RequestParam("reportId") int reportId) {
		HospitalFinanceManager financeManager = (HospitalFinanceManager) session
				.getAttribute("financeManager");
		model.addAttribute("financeManager", financeManager);
		try {
			Report r = userDao.getCheckManagerAssign(smartId, reportId);
			if (r.getAssignedFinanceManager() != null) {
				Report report = userDao
						.getCheckManagerAssign(smartId, reportId);
				int fincanceManagerId = Integer.parseInt(report
						.getAssignedFinanceManager());
				if (fincanceManagerId != financeManager.getEmployeeId()) {
					String error = "The Invoice is assigned to a different Finance Manager! Only assigned Finance Manager can generate invoice!";
					model.addAttribute("error", error);
					return goTogenerateInvoice(model);
				} else if (report.getGenerated() == null) {
					model.addAttribute("financeManager", financeManager);
					model.addAttribute("report", report);
					model.addAttribute("smartId", smartId);
					Invoice invoice = new Invoice();
					model.addAttribute("invoice", invoice);
					return "generateSmartIdInvoice";

				} else {
					String error = "Invoice for this smartId is already generated!";
					model.addAttribute("error", error);
					return goTogenerateInvoice(model);
				}
			} else if (r.getAssignedFinanceManager() == null) {
				String error = "The SmartId is not Assigned! Please assign it to you for generating Invoice!";
				model.addAttribute("error", error);
				return goTogenerateInvoice(model);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return goTogenerateInvoice(model);
	}

	@RequestMapping(value = "doctorReport", method = RequestMethod.POST)
	public String goToDoctorReport(Model model, @Validated Report report,
			BindingResult result, @RequestParam("smartId") int smartId,
			@RequestParam("month") String month) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("doctor", doctor);
		model.addAttribute("report", report);

		try {
			int update = userDao.generateReport(smartId, doctor, report);
			if (update == 1) {
				int update1 = userDao.getGeneratedReport(smartId, month);
				if (update1 == 1) {
					model.addAttribute("smartId", smartId);
					model.addAttribute("doctor", doctor);
					model.addAttribute("report", report);
					return "successReport";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "generateReport";
	}

	@RequestMapping(value = "managerInvoice", method = RequestMethod.POST)
	public String goToManagerInvoice(Model model, @Validated Invoice invoice,
			BindingResult result, @RequestParam("smartId") int smartId,
			@RequestParam("doctorId") int doctorId,
			@RequestParam("hospitalId") int hospitalId,
			@RequestParam("reportId") int reportId) {
		HospitalFinanceManager financeManager = (HospitalFinanceManager) session
				.getAttribute("financeManager");
		model.addAttribute("financeManager", financeManager);
		model.addAttribute("invoice", invoice);

		try {
			int update = userDao.generateInvoice(smartId, financeManager,
					invoice, doctorId, hospitalId, reportId);
			;
			if (update == 1) {
				int update1 = userDao.updateInvoice(smartId, reportId);
				if (update1 == 1) {
					model.addAttribute("smartId", smartId);
					model.addAttribute("financeManager", financeManager);
					model.addAttribute("invoice", invoice);
					return "successInvoice";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "generateSmartIdInvoice";
	}

	@RequestMapping(value = "userHomePage")
	public String goTouserHomePage(Model model) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		return "userHomePage";
	}

	@RequestMapping(value = "searchHospital")
	public String goTosearchHospital(Model model) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		Hospital hospital = new Hospital();
		model.addAttribute("hospital", hospital);
		return "searchHospital";
	}

	@RequestMapping(value = "updateMedicalDetails")
	public String goToupdateMedicalDetails(Model model) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		return "updateMedicalDetails";
	}

	@RequestMapping(value = "enterToday")
	public String goToenterTodaysData(Model model) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		DailyRecord dailyRecord = new DailyRecord();
		model.addAttribute("dailyRecord", dailyRecord);
		return "enterTodaysData";
	}

	@RequestMapping(value = "viewRecord")
	public String goToviewRecord(Model model) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		DailyRecord dailyRecord = new DailyRecord();
		model.addAttribute("dailyRecord", dailyRecord);
		MonthlyRecord monthlyRecord = new MonthlyRecord();
		model.addAttribute("monthlyRecord", monthlyRecord);
		return "viewRecord";
	}

	@RequestMapping(value = "addHospital")
	public String goToaddHospital(Model model,
			@RequestParam("hospitalId") int hospitalId) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		List<Hospital> hospital = smartId.getHospital();
		for (Hospital hospital2 : hospital) {
			if (hospital2.getHospitalId() == hospitalId) {
				String error = "Hospital already added!!!";
				model.addAttribute("error", error);
				return goTosearchHospital(model);
			}
		}
		try {
			int update = userDao.insertPreferredHospitals(smartId.getSmartId(),
					hospitalId);
			if (update == 1) {
				String success = "Preferred Hospital Added Successfully!!";
				model.addAttribute("success", success);
				return goTosearchHospital(model);
			} else {
				String error = "Error adding hospital!!";
				model.addAttribute("error", error);
				return goTosearchHospital(model);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	@RequestMapping(value = "userRegistration", method = RequestMethod.POST)
	public String sendMessage(Model model, @Validated Login login,
			BindingResult result) {
		model.addAttribute("login", login);
		String returnVal = "userHomePage";
		if (result.hasErrors()) {
			return "registration";
		} else {
			try {
				Login login1 = userDao.getUserByName(login.getUserName());
				if (login1 != null) {
					String error = "UserName already exist. Please enter a different UserName!";
					model.addAttribute("error", error);
					return "registration";
				} else {
					int update = userDao.addNewUser(login);
					if(update == 1){
						model.addAttribute("smartId", login.getSmartId());
						return "registrationSuccess";
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "registration";

	}

	@RequestMapping(value = "forgotUser", method = RequestMethod.POST)
	public String forgotUserName(Model model, @Validated Login login,
			BindingResult result, HttpServletRequest request) {
		model.addAttribute("login", login);
		String returnVal = "forgotUserName";
		if (!login.getUserName().equalsIgnoreCase(null)) {
			try {
				Login login1 = userDao.getUserByName(login.getUserName());
				if (login1 != null) {
					model.addAttribute("login", login1);
					session = request.getSession();
					session.setAttribute("userName", login1.getUserName());
					return "forgotSecurityQuestion";
				} else {
					String error = "Invalid UserName!";
					model.addAttribute("error", error);
					return returnVal;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "forgotUserName";

	}

	@RequestMapping(value = "forgotSecurity", method = RequestMethod.POST)
	public String forgotSecurityQuestion(Model model, @Validated Login login,
			BindingResult result, @RequestParam("answer") String answer) {
		model.addAttribute("login", login);
		String userName = (String) session.getAttribute("userName");
		String returnVal = "forgotSecurityQuestion";
		try {
			Login login1 = userDao.getUserByName(userName);
			if (login1 != null) {
				if (login1.getSecurityAnswer().equalsIgnoreCase(answer)) {
					model.addAttribute("login", login1);
					return "forgotChangePassword";
				} else {
					String error = "Answer do not match!";
					model.addAttribute("login", login1);
					model.addAttribute("error", error);
					return returnVal;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnVal;

	}

	@RequestMapping(value = "forgotChange", method = RequestMethod.POST)
	public String forgotChnagePassword(Model model, @Validated Login login,
			BindingResult result, @RequestParam("password") String password) {
		model.addAttribute("login", login);
		String userName = (String) session.getAttribute("userName");
		String returnVal = "forgotChangePassword";
		try {
			Login login1 = userDao.updatePassowrd(userName, password);
			if (login1 != null) {
				model.addAttribute("userName", login1.getUserName());
				return "successChange";
			} else {
				String error = "Error updating password!";
				model.addAttribute("error", error);
				return returnVal;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnVal;

	}
	
	@RequestMapping(value = "passwordUpdate", method = RequestMethod.POST)
	public String passwordUpdate(Model model, @Validated Login login,
			BindingResult result) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("login", login);
		try {
			Login login1 = userDao.updatePassowrd(smartId.getLogin().getUserName(), login.getUserPassword());
			if (login1 != null) {
				//session.setAttribute("smartId", smartId);
				String success = "Password changed successfully!";
				session.setAttribute("smartId", login1.getSmartId());
				model.addAttribute("smartId", login1.getSmartId());
				model.addAttribute("success", success);
				return "userChangePassword";
			} else {
				String error = "Error updating password!";
				model.addAttribute("error", error);
				return "userChangePassword";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "userChangePassword";

	}
	
	@RequestMapping(value = "passwordDoctorUpdate", method = RequestMethod.POST)
	public String passwordDoctorUpdate(Model model, @Validated Login login,
			BindingResult result) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		model.addAttribute("login", login);
		try {
			Login login1 = userDao.updatePassowrd(doctor.getDoctorLogin().getUserName(), login.getUserPassword());
			if (login1 != null) {
				String success = "Password changed successfully!";
				session.setAttribute("doctor", login1.getDoctor());
				model.addAttribute("doctor", login1.getDoctor());
				model.addAttribute("success", success);
				return "changeDoctorPassword";
			} else {
				String error = "Error updating password!";
				model.addAttribute("error", error);
				return "changeDoctorPassword";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "changeDoctorPassword";

	}
	
	@RequestMapping(value = "passwordManagerUpdate", method = RequestMethod.POST)
	public String passwordManagerUpdate(Model model, @RequestParam("newPassword") String newPassword) {
		HospitalFinanceManager financeManager = (HospitalFinanceManager) session.getAttribute("financeManager");
		model.addAttribute("financeManager", financeManager);
		try {
			Login login = userDao.updatePassowrd(financeManager.getManagerLogin().getUserName(), newPassword);
			if (login != null) {
				String success = "Password changed successfully!";
				session.setAttribute("financeManager", login.getHospitalFinanceManager());
				model.addAttribute("financeManager", login.getHospitalFinanceManager());
				model.addAttribute("success", success);
				return "changeManagerPassword";
			} else {
				String error = "Error updating password!";
				model.addAttribute("error", error);
				return "changeManagerPassword";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "changeManagerPassword";

	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String searchAjax(Model model, @Validated Hospital hospital,
			BindingResult result) {
		model.addAttribute("hospital", hospital);
		if (result.hasErrors()) {
			return "registration";
		} else {
			try {
				List<Hospital> hospitalList = userDao
						.getHospitalNameAjax(hospital.getHospitalName());
				if (hospitalList != null) {
					model.addAttribute("hospitalList", hospitalList);
					return "searchHospital";
				} else {
					String error = "No results found!";
					model.addAttribute("error", error);
					return "searchHospital";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;

	}

	@RequestMapping(value = "searchByCity", method = RequestMethod.POST)
	public String searchByCity(Model model, @Validated Hospital hospital,
			BindingResult result) {
		model.addAttribute("hospital", hospital);
		if (result.hasErrors()) {
			return "registration";
		} else {
			try {
				List<Hospital> hospitalList = userDao.searchByCity(hospital
						.getCity());
				if (hospitalList != null) {
					model.addAttribute("hospitalListCity", hospitalList);
					return "searchHospital";
				} else {
					String error = "No results found!";
					model.addAttribute("error", error);
					return "searchHospital";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;

	}

	@RequestMapping(value = "deleteHospital", method = RequestMethod.POST)
	public String deleteHospital(Model model, @Validated Hospital hospital,
			BindingResult result, @RequestParam("delete") int[] hospitalId) {
		model.addAttribute("hospital", hospital);
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		String returnVal = "selectPreferredHospital";
		if (result.hasErrors()) {
			return "selectPreferredHospital";
		} else {
			try {
				int update = 0;
				for (int i = 0; i < hospitalId.length; i++) {
					// int id = Integer.parseInt(hospitalId[i]);
					update = userDao.deletePreferredHospitals(
							smartId.getSmartId(), hospitalId[i]);
					if (update == 1) {
						continue;
					} else {
						break;
					}
				}
				if (update == 1) {
					String success = "Hospital Removed successfully!";
					model.addAttribute("success", success);
					return goTopreferredHospital(model);
				} else {
					String error = "Error removing preferred hospital";
					model.addAttribute("error", error);
					return "selectPreferredHospital";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "selectPreferredHospital";

	}

	@RequestMapping(value = "dailyRecord", method = RequestMethod.POST)
	public String updateDailyRecords(Model model,
			@Validated DailyRecord dailyRecord, BindingResult result) {
		model.addAttribute("dailyRecord", dailyRecord);
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		String returnVal = "enterTodaysData";
		if (result.hasErrors()) {
			return returnVal;
		} else {
			try {
				int update = userDao.updateDailyRecords(smartId.getSmartId(),
						dailyRecord);
				if (update == 1) {
					String success = "Record Added Successfully !!";
					model.addAttribute("success", success);
					return returnVal;
				} else {
					String error = "Error occured while adding record !!";
					model.addAttribute("error", error);
					return returnVal;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return returnVal;

	}

	@RequestMapping(value = "updateRecord", method = RequestMethod.POST)
	public String updateRecords(Model model,
			@Validated DailyRecord dailyRecord, BindingResult result,
			@RequestParam String date) {
		model.addAttribute("dailyRecord", dailyRecord);
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		String returnVal = "viewRecord";
		if (result.hasErrors()) {
			return returnVal;
		} else {
			try {
				int update = userDao.updateRecords(smartId.getSmartId(),
						dailyRecord, date);
				if (update == 1) {
					String success = "Record Added Successfully !!";
					model.addAttribute("success", success);
					return goToviewRecord(model);
				} else {
					String error = "Error occured while adding record !!";
					model.addAttribute("error", error);
					return returnVal;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return returnVal;

	}

	@RequestMapping(value = "ajax", method = RequestMethod.POST)
	public String viewDailyRecords(Model model, @RequestParam String q) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		String returnVal = "viewRecord";
		try {
			DailyRecord dailyRecord = userDao.getRecordByDate(
					smartId.getSmartId(), q);
			if (dailyRecord != null) {
				model.addAttribute("dailyRecord", dailyRecord);
				return returnVal;
			} else {
				return returnVal;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "viewRecord";

	}

	@RequestMapping(value = "updateDetails", method = RequestMethod.POST)
	public String updateDetails(Model model, @Validated SmartId smartId,
			BindingResult result) {
		model.addAttribute("smartId", smartId);
		SmartId smart = (SmartId) session.getAttribute("smartId");
		String returnVal = "updateMedicalDetails";
		if (result.hasErrors()) {
			return returnVal;
		} else {
			try {
				int update = userDao.updateSmartIdDetalils(smart.getSmartId(),
						smartId);
				if (update == 1) {
					String success = "Record Updated Successfully !!";
					model.addAttribute("success", success);
					return returnVal;
				} else {
					String error = "Error occured while updating record !!";
					model.addAttribute("error", error);
					return returnVal;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return returnVal;

	}

	@RequestMapping(value = "payment", method = RequestMethod.POST)
	public String goToPayment(Model model,
			@RequestParam("invoiceId") int invoiceId) {
		SmartId smartId = (SmartId) session.getAttribute("smartId");
		model.addAttribute("smartId", smartId);
		try {
			String payment = userDao.checkPayment(smartId, invoiceId);
			if (payment.equalsIgnoreCase("paid")) {
				String error = "Payment already made!";
				model.addAttribute("error", error);
				return goToViewMyInvoice(model);
			} else {
				int update = userDao.updatePayment(smartId, invoiceId);
				if (update == 1) {
					String success = "Payment successful!";
					model.addAttribute("success", success);
					return goToViewMyInvoice(model);
				} else {
					String error = "Unsuccessful Payment!";
					model.addAttribute("error", error);
					return goToViewMyInvoice(model);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return goToViewMyInvoice(model);
	}

	@RequestMapping(value = "userLogout")
	public String logout(Model model, HttpServletResponse response) {
		// model.addAttribute("response", response);

		Cookie userName = new Cookie("userName", "");
		userName.setMaxAge(0);
		response.addCookie(userName);
		Cookie userPassword = new Cookie("userPassword", "");
		userPassword.setMaxAge(0);
		response.addCookie(userPassword);
		
		session.invalidate();

		model.addAttribute(response);
		return "userLogout";
	}
	
	
	
	@RequestMapping(value="reportPdf")
	public void reportPDF(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("reportId") int reportId){
		SmartId smartId = (SmartId) request.getSession().getAttribute("smartId");
		try {
			Report report = userDao.getCheckManagerAssign(smartId.getSmartId(), reportId);
			
			Document document = new Document();
			response.setContentType("application/pdf");
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			
			document.addHeader("Header","Smart Health");
			
			document.add(new Paragraph("Patient Name: "+ smartId.getFirstName()+" "+smartId.getLastName()));
			document.add(new Paragraph("SmartId:  "+smartId.getSmartId()));
			document.add(new Paragraph("Generated Date:  "+report.getGeneratedDate()));
			document.add(new Paragraph("Hospital Name:  "+report.getDoctor().getHospital().getHospitalName()));
			document.add(new Paragraph("Hospital Address:  "+report.getDoctor().getHospital().getStreet()+", "+report.getDoctor().getHospital().getCity()+", "+report.getDoctor().getState()+", "+report.getDoctor().getHospital().getZip()));
			document.add(Chunk.NEWLINE);
			PdfPTable table = new PdfPTable(7);
			
			PdfPCell cell1 = new PdfPCell(new Paragraph("Report ID"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Severity"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Description"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Required Medical Test"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Medicine to be Prescribed"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("Doctor Name"));
            PdfPCell cell7 = new PdfPCell(new Paragraph("Doctor ID"));

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
			table.addCell(cell7);
			
			table.addCell(String.valueOf(report.getReportId()));
			table.addCell(report.getSeverity());
			table.addCell(report.getDescription());
			table.addCell(report.getRequiredMedicalTest());
			table.addCell(report.getPrescribedMedicine());
			table.addCell(report.getDoctor().getEmployeeName());
			table.addCell(String.valueOf(report.getDoctor().getEmployeeId()));
			

			document.add(table);
			
			document.add(Chunk.NEWLINE);
			
			document.add(new Paragraph("Signature,"));
			document.add(new Paragraph(report.getDoctor().getEmployeeName()));
			document.add(new Paragraph(String.valueOf(report.getDoctor().getEmployeeId())));
			document.add(new Paragraph(report.getDoctor().getPhoneNo()));
			document.add(new Paragraph(report.getDoctor().getEmailId()));
			
			document.close();

			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="invoicePdf")
	public void invoicePdf(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("invoiceId") int invoiceId){
		SmartId smartId = (SmartId) request.getSession().getAttribute("smartId");
		try {
			Invoice invoice = userDao.pdfInvoice(smartId.getSmartId(), invoiceId);
			
			Document document = new Document();
			response.setContentType("application/pdf");
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			
			document.addHeader("Header","Smart Health");
			
			document.add(new Paragraph("Name: "+ smartId.getFirstName()+" "+smartId.getLastName()));
			document.add(new Paragraph("SmartId:  "+smartId.getSmartId()));
			document.add(new Paragraph("Generated Date:  "+invoice.getDate()));
			document.add(new Paragraph("Hospital Name:  "+invoice.getFinanceManager().getHospital().getHospitalName()));
			document.add(new Paragraph("Hospital Address:  "+invoice.getFinanceManager().getHospital().getStreet()+", "+invoice.getFinanceManager().getHospital().getCity()+", "+invoice.getFinanceManager().getState()+", "+invoice.getFinanceManager().getHospital().getZip()));
			document.add(Chunk.NEWLINE);
			PdfPTable table = new PdfPTable(5);
			
			PdfPCell cell1 = new PdfPCell(new Paragraph("Invoice ID"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Total Amount"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Invoice Description"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Report ID"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Doctor ID"));

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			
			table.addCell(String.valueOf(invoice.getInvoiceId()));
			table.addCell(String.valueOf(invoice.getTotalAmount()));
			table.addCell(invoice.getInvoiceDescription());
			table.addCell(String.valueOf(invoice.getReportId()));
			table.addCell(String.valueOf(invoice.getDoctorId()));
			

			document.add(table);
			
			document.add(Chunk.NEWLINE);
			
			document.add(new Paragraph("Signature,"));
			document.add(new Paragraph(invoice.getFinanceManager().getEmployeeName()));
			document.add(new Paragraph(String.valueOf(invoice.getFinanceManager().getEmployeeId())));
			document.add(new Paragraph(invoice.getFinanceManager().getPhoneNo()));
			document.add(new Paragraph(invoice.getFinanceManager().getEmailId()));
			
			document.close();

			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
