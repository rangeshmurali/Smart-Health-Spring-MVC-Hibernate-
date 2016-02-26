package com.me.dao;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
import com.sun.org.apache.bcel.internal.generic.LLOAD;

public class UserDao extends DAO {
	
	public Login loginUserByNameAndPassword(String name, String password)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from Login where userName = :username and userPassword = :password");
            q.setString("username", name);
            q.setString("password", password);
            
            Login login = (Login) q.uniqueResult();
            
            //commit();
            return login;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + name, e);
        }
    }
	
	public Login getUserByName(String name)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from Login where userName = :username");
            q.setString("username", name);
            
            Login user = (Login) q.uniqueResult();
            
            //commit();
            return user;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + name, e);
        }
    }
	
	public Login updatePassowrd(String name, String password)
            throws Exception {
        try {
            //begin();
        	int update = 0;
            
            Session session = getSession();
    		Criteria cr = session.createCriteria(Login.class);
    		cr.add(Restrictions.eq("userName", name));
    		Login login = (Login)cr.uniqueResult();
    		login.setUserPassword(password);
    		session.beginTransaction();
    		session.update(login);
    		session.getTransaction().commit();
    		session.close();
    		return login;
            
            //commit();
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + name, e);
        }
    }
	
	public int addNewUser(Login l)
			throws Exception{
		try{
			int update = 1;
			
			Session session = getSession().getSessionFactory().openSession();
			session.beginTransaction();
			
			l.setRole("User");
		
			
			Login login = l;
			SmartId smartId = l.getSmartId();
			smartId.setLogin(login);
			smartId.setAllergies(null);
			smartId.setBloodGroup(null);
			smartId.setHeight(0);
			smartId.setHospital(null);
			smartId.setInvoiceList(null);
			smartId.setMedicalHistoryDecription(null);
			smartId.setMedicalTestTook(null);
			smartId.setPriscribingMedicine(null);
			smartId.setReportList(null);
			smartId.setVitalSignInfo(null);
			smartId.setVitalSignInfo(null);
			session.save(login);
			session.save(smartId);
			session.getTransaction().commit();
            
            return update;
            
		} catch (HibernateException e){
			throw new Exception("Could not get user " + l.getUserName(), e);
		}
	}
	
	public int updateDailyRecords(int smartId, DailyRecord dailyRecord)
            throws Exception {
        try {
            //begin();
        	int update = 0;
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	Date date = new Date();
        	String d = String.valueOf(dateFormat.format(date));
        	
        	
//        	String d = "2015-04-16";
        	
        	Session session1 = getSession().getSessionFactory().openSession();
        	session1.beginTransaction();
        	Query q = session1.createQuery("from DailyRecord where smartId = :sId and date = :dated");
            q.setInteger("sId", smartId);
            q.setString("dated", d);
            
            DailyRecord daily = (DailyRecord) q.uniqueResult();
            session1.close();
            
            if(daily == null){
            	Session session = getSession().getSessionFactory().openSession();
            	session.beginTransaction();
            	dailyRecord.setSmartId(smartId);
            	dailyRecord.setDate(d);
            	session.save(dailyRecord);
            	session.getTransaction().commit();
            	session.close();
            	update = updateMonthlyRecords(smartId, dailyRecord, date);
            	return update;
            } else {
            	float walkingDistance = daily.getWalkingDistance() + dailyRecord.getWalkingDistance();
            	float runningDistance = daily.getRunningDistance() + dailyRecord.getRunningDistance();
            	float activeCalories = (daily.getActiveCalories() + dailyRecord.getActiveCalories())/2;
            	int flightsClimed = daily.getFlightsClimed() + dailyRecord.getFlightsClimed();
            	int heartRate = (daily.getHeartRate() + dailyRecord.getHeartRate())/2;
            	
            	Session session = getSession().getSessionFactory().openSession();
            	session.beginTransaction();
            	Query q1 = session.createQuery("Update DailyRecord set walkingDistance = :wd, runningDistance = :rd, flightsClimed =  :fc, activeCalories = :ac, bloodPressure = :bp, heartRate = :hr" + " where smartId = :sId and date = :dated");
            	q1.setInteger("sId", smartId);
                q1.setString("dated", d);
                q1.setFloat("wd", walkingDistance);
                q1.setFloat("rd", runningDistance);
                q1.setFloat("ac", activeCalories);
                q1.setInteger("fc", flightsClimed);
                q1.setInteger("hr", heartRate);
                q1.setString("bp", dailyRecord.getBloodPressure());
                
                update = q1.executeUpdate();
                session.getTransaction().commit();
                session.close();
                if(update == 1){
                	update = updateMonthlyRecords(smartId, dailyRecord, date);
                }
                return update;
            }
            
            
            //commit();
            
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public int updateMonthlyRecords(int smartId, DailyRecord dailyRecord, Date date)
            throws Exception {
        try {
            //begin();
        	int update = 0;
        	DateFormat dateFormat1 = new SimpleDateFormat("MMMM");
        	String d = dateFormat1.format(date);
        	
//        	String d = "May";
        	
//        	String d = "2015-04-14";
        	
        	Session session1 = getSession().getSessionFactory().openSession();
        	session1.beginTransaction();
        	Query q = session1.createQuery("from MonthlyRecord where smartId = :sId and month = :dated");
            q.setInteger("sId", smartId);
            q.setString("dated", d);
            
            MonthlyRecord month = (MonthlyRecord) q.uniqueResult();
            session1.close();
            
            if(month == null){
            	Session session = getSession().getSessionFactory().openSession();
            	session.beginTransaction();
            	MonthlyRecord m = new MonthlyRecord();
            	m.setSmartId(smartId);
            	m.setMonth(d);
            	m.setActiveCalories(dailyRecord.getActiveCalories());
            	m.setBloodPressure(dailyRecord.getBloodPressure());
            	m.setFlightsClimed(dailyRecord.getFlightsClimed());
            	m.setHeartRate(dailyRecord.getHeartRate());
            	m.setRunningDistance(dailyRecord.getRunningDistance());
            	m.setWalkingDistance(dailyRecord.getWalkingDistance());
            	session.save(m);
            	session.getTransaction().commit();
            	session.close();
            	update = 1;
            	return update;
            } else {
            	float walkingDistance = month.getWalkingDistance() + dailyRecord.getWalkingDistance();
            	float runningDistance = month.getRunningDistance() + dailyRecord.getRunningDistance();
            	float activeCalories = (month.getActiveCalories() + dailyRecord.getActiveCalories())/2;
            	int flightsClimed = month.getFlightsClimed() + dailyRecord.getFlightsClimed();
            	int heartRate = (month.getHeartRate() + dailyRecord.getHeartRate())/2;
            	
            	Session session = getSession().getSessionFactory().openSession();
            	session.beginTransaction();
            	Query q1 = session.createQuery("Update MonthlyRecord set walkingDistance = :wd, runningDistance = :rd, flightsClimed =  :fc, activeCalories = :ac, bloodPressure = :bp, heartRate = :hr" + " where smartId = :sId and month = :dated");
            	q1.setInteger("sId", smartId);
                q1.setString("dated", d);
                q1.setFloat("wd", walkingDistance);
                q1.setFloat("rd", runningDistance);
                q1.setFloat("ac", activeCalories);
                q1.setInteger("fc", flightsClimed);
                q1.setInteger("hr", heartRate);
                q1.setString("bp", dailyRecord.getBloodPressure());
                
                update = q1.executeUpdate();
                session.getTransaction().commit();
                session.close();
                return update;
            }
            
            
            //commit();
            
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public int updateSmartIdDetalils(int sId, SmartId smartId)
            throws Exception {
        try {
            //begin();
        	
        	int update;
        	
        	Session session = getSession().getSessionFactory().openSession();
        	session.beginTransaction();
        	Query q1 = session.createQuery("Update SmartId set bloodGroup = :bg, height = :h, weight =  :w, priscribingMedicine = :pm, medicalTestTook = :mt, allergies = :a, medicalHistoryDecription = :mh, vitalSignInfo = :vs" + " where smartId = :sId");
        	q1.setString("bg", smartId.getBloodGroup());
            q1.setFloat("h", smartId.getHeight());
            q1.setFloat("w", smartId.getWeight());
            q1.setString("pm", smartId.getPriscribingMedicine());
            q1.setString("mt", smartId.getMedicalTestTook());
            q1.setString("a", smartId.getAllergies());
            q1.setString("mh", smartId.getMedicalHistoryDecription());
            q1.setString("vs", smartId.getVitalSignInfo());
            q1.setInteger("sId", sId);
            
            update = q1.executeUpdate();
            session.getTransaction().commit();
            session.close();
            return update;
            
            
            
            //commit();
            
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public DailyRecord getRecordByDate(int smartId, String date)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from DailyRecord where smartId = :id and date = :d");
            q.setInteger("id", smartId);
            q.setString("d", date);
            
            DailyRecord dailyRecord = (DailyRecord) q.uniqueResult();
            
            //commit();
            return dailyRecord;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public List<DailyRecord> getRecordGraph(int smartId)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from DailyRecord where smartId = :id");
            q.setInteger("id", smartId);
            
            List<DailyRecord> dailyRecord = (List<DailyRecord>) q.list();
            
            //commit();
            return dailyRecord;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public int updateRecords(int smartId, DailyRecord dailyRecord, String date)
            throws Exception {
        try {
            //begin();
        	int update = 0;
        	
        	
//        	String d = "2015-04-16";
        	
        	Session session1 = getSession().getSessionFactory().openSession();
        	session1.beginTransaction();
        	Query q = session1.createQuery("from DailyRecord where smartId = :sId and date = :dated");
            q.setInteger("sId", smartId);
            q.setString("dated", date);
            
            DailyRecord daily = (DailyRecord) q.uniqueResult();
            session1.close();
            
            if(daily == null){
            	Session session = getSession().getSessionFactory().openSession();
            	session.beginTransaction();
            	dailyRecord.setSmartId(smartId);
            	dailyRecord.setDate(date);
            	session.save(dailyRecord);
            	session.getTransaction().commit();
            	session.close();
//            	update = updateMonthRecords(smartId, dailyRecord, date, daily);
            	return update;
            } else {
            	float walkingDistance = dailyRecord.getWalkingDistance();
            	float runningDistance = dailyRecord.getRunningDistance();
            	float activeCalories = dailyRecord.getActiveCalories();
            	int flightsClimed = dailyRecord.getFlightsClimed();
            	int heartRate = dailyRecord.getHeartRate() + dailyRecord.getHeartRate();
            	
            	Session session = getSession().getSessionFactory().openSession();
            	session.beginTransaction();
            	Query q1 = session.createQuery("Update DailyRecord set walkingDistance = :wd, runningDistance = :rd, flightsClimed =  :fc, activeCalories = :ac, bloodPressure = :bp, heartRate = :hr" + " where smartId = :sId and date = :dated");
            	q1.setInteger("sId", smartId);
                q1.setString("dated", date);
                q1.setFloat("wd", walkingDistance);
                q1.setFloat("rd", runningDistance);
                q1.setFloat("ac", activeCalories);
                q1.setInteger("fc", flightsClimed);
                q1.setInteger("hr", heartRate);
                q1.setString("bp", dailyRecord.getBloodPressure());
                
                update = q1.executeUpdate();
                session.getTransaction().commit();
                session.close();
//                if(update == 1){
//                	update = updateMonthRecords(smartId, dailyRecord, date, daily);
//                }
                return update;
            }
            
            
            //commit();
            
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public int updateMonthRecords(int smartId, DailyRecord dailyRecord, String date, DailyRecord oldDaily)
            throws Exception {
        try {
            //begin();
        	int update = 0;
        	JOptionPane.showMessageDialog(null, date);
//        	DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
//        	Date dt = simpleDateFormat.parse(date);
//        	JOptionPane.showMessageDialog(null, dt);
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        	Date ds = formatter.parse(date);
        	DateFormat dateFormat1 = new SimpleDateFormat("MMM");
        	String d = dateFormat1.format(ds);
       	JOptionPane.showMessageDialog(null, d);
        	
//        	String d = "April";
        	
//        	String d = "2015-04-14";
        	
        	Session session1 = getSession().getSessionFactory().openSession();
        	session1.beginTransaction();
        	Query q = session1.createQuery("from MonthlyRecord where smartId = :sId and month = :dated");
            q.setInteger("sId", smartId);
            q.setString("dated", d);
            
            MonthlyRecord month = (MonthlyRecord) q.uniqueResult();
            session1.close();
            
            if(month == null){
            	Session session = getSession().getSessionFactory().openSession();
            	session.beginTransaction();
            	MonthlyRecord m = new MonthlyRecord();
            	m.setSmartId(smartId);
            	m.setMonth(d);
            	m.setActiveCalories(dailyRecord.getActiveCalories());
            	m.setBloodPressure(dailyRecord.getBloodPressure());
            	m.setFlightsClimed(dailyRecord.getFlightsClimed());
            	m.setHeartRate(dailyRecord.getHeartRate());
            	m.setRunningDistance(dailyRecord.getRunningDistance());
            	m.setWalkingDistance(dailyRecord.getWalkingDistance());
            	session.save(m);
            	session.getTransaction().commit();
            	session.close();
            	update = 1;
            	return update;
            } else {
            	float walkingDistance = (month.getWalkingDistance() - oldDaily.getWalkingDistance()) + dailyRecord.getWalkingDistance();
            	float runningDistance = (month.getRunningDistance() - oldDaily.getRunningDistance()) + dailyRecord.getRunningDistance();
            	float activeCalories = ((month.getActiveCalories() - oldDaily.getActiveCalories()) + dailyRecord.getActiveCalories())/2;
            	int flightsClimed = (month.getFlightsClimed() - oldDaily.getFlightsClimed()) + dailyRecord.getFlightsClimed();
            	int heartRate = ((month.getHeartRate() - oldDaily.getHeartRate()) + dailyRecord.getHeartRate())/2;
            	
            	Session session = getSession().getSessionFactory().openSession();
            	session.beginTransaction();
            	Query q1 = session.createQuery("Update MonthlyRecord set walkingDistance = :wd, runningDistance = :rd, flightsClimed =  :fc, activeCalories = :ac, bloodPressure = :bp, heartRate = :hr" + " where smartId = :sId and month = :dated");
            	q1.setInteger("sId", smartId);
                q1.setString("dated", d);
                q1.setFloat("wd", walkingDistance);
                q1.setFloat("rd", runningDistance);
                q1.setFloat("ac", activeCalories);
                q1.setInteger("fc", flightsClimed);
                q1.setInteger("hr", heartRate);
                q1.setString("bp", dailyRecord.getBloodPressure());
                
                update = q1.executeUpdate();
                session.getTransaction().commit();
                session.close();
                return update;
            }
            
            
            //commit();
            
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public List<Hospital> selectPreferredHospitals(int smartId)
            throws Exception {
        try {
            //begin();
        	//Query q = getSession().createQuery("from Hospital h join h.smartId s where s.smartId = :id ");
        	Query q = getSession().createQuery("select h from Hospital h join h.smartId s where s.smartId = :id");
        	q.setInteger("id", smartId);
            
        	
        	List<Hospital> hospitalList = (List<Hospital>) q.list();
        	
            return hospitalList;
            
            //commit();
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get Hospitals " , e);
        }
    }
	
	public int deletePreferredHospitals(int smartId, int hospitalId)
            throws Exception {
        try {
            //begin();
        	int update = 1;
        	Session session = getSession().getSessionFactory().openSession();
        	session.beginTransaction();
//        	Query q = session.createQuery("delete form preferredhospital where smartId = :sId and hospitalId = :hId");
//        	q.setInteger("id", smartId);
//        	q.setInteger("id", smartId);
            
        	
//        	update = q.executeUpdate();
        	SmartId smartId2 = (SmartId) session.get(SmartId.class, smartId);
        	Hospital hospital = (Hospital) session.get(Hospital.class, hospitalId);
        	smartId2.getHospital().remove(hospital);
        	
        	session.getTransaction().commit();
        	
        	session.close();
        	
            return update;
            
            //commit();
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get Hospitals " , e);
        } 
    }
	
	public List<Hospital> getHospitalNameAjax(String name)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from Hospital where hospitalName = :id");
            q.setString("id", name);
            
            List<Hospital> hospitalList = (List<Hospital>) q.list();
            
            //commit();
            return hospitalList;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + name, e);
        }
    }
	
	public List<Hospital> searchByCity(String city)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from Hospital where city = :id");
            q.setString("id", city);
       
            
            List<Hospital> hospitalList = (List<Hospital>) q.list();
            
            //commit();
            return hospitalList;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + city, e);
        }
    }
	
	public int insertPreferredHospitals(int smartId, int hospitalId)
            throws Exception {
        try {
            //begin();
        	int update = 1;
        	Session session = getSession().getSessionFactory().openSession();
        	session.beginTransaction();
        	SmartId smartId2 = (SmartId) session.get(SmartId.class, smartId);
        	Hospital hospital = (Hospital) session.get(Hospital.class, hospitalId);
        	smartId2.getHospital().add(hospital);
        	
        	session.getTransaction().commit();
        	
        	session.close();
            return update;
            
            //commit();
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get Hospitals " , e);
        } 
    }
	
	public List<MonthlyRecord> getMonthlyRecordDoctor(int smartId)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from MonthlyRecord where smartId = :id");
            q.setInteger("id", smartId);
            
            List<MonthlyRecord> monthlyList = (List<MonthlyRecord>) q.list();
            
            //commit();
            return monthlyList;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public SmartId getPatientMedicalRecord(int smartId)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from SmartId where smartId = :id");
            q.setInteger("id", smartId);
            
            SmartId s = (SmartId) q.uniqueResult();
            
            //commit();
            return s;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public MonthlyRecord getCheckAssign(int smartId, String month)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from MonthlyRecord where smartId = :id and month = :mth");
            q.setInteger("id", smartId);
            q.setString("mth", month);
            
            MonthlyRecord s = (MonthlyRecord) q.uniqueResult();
            
            //commit();
            return s;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public int getAssignDoctor(int smartId, int doctorId, String month)
            throws Exception {
        try {
            //begin();
        	int update = 1;
        	String doc = String.valueOf(doctorId);  
        	Session session = getSession().getSessionFactory().openSession();
        	session.beginTransaction();
            Query q = session.createQuery("Update MonthlyRecord set assign = :doctor where smartId = :id and month = :mth");
            q.setInteger("id", smartId);
            q.setString("doctor", doc);
            q.setString("mth", month);
            
            update = q.executeUpdate();
            session.getTransaction().commit();
            session.close();
            
            //commit();
            return update;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public int generateReport(int smartId, Doctor doctor, Report report)
            throws Exception {
        try {
            //begin();
        	int update = 1;
        	Session session = getSession().getSessionFactory().openSession();
        	session.beginTransaction();
        	SmartId smartId2 = (SmartId) session.get(SmartId.class, smartId);
        	smartId2.getReportList().add(report);
        	report.setSmartId(smartId2);
        	report.setDoctor(doctor);
        	doctor.getReportList().add(report);
        	session.save(report);
        	
        	session.getTransaction().commit();
        	
        	session.close();
            return update;
            
            //commit();
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get Hospitals " , e);
        } 
    }
	
	public int getGeneratedReport(int smartId, String month)
            throws Exception {
        try {
            //begin();
        	int update = 1; 
        	Session session = getSession().getSessionFactory().openSession();
        	session.beginTransaction();
            Query q = session.createQuery("Update MonthlyRecord set generated = :gen where smartId = :id and month = :mth");
            q.setInteger("id", smartId);
            q.setString("mth", month);
            q.setString("gen", "Generated");
            
            update = q.executeUpdate();
            session.getTransaction().commit();
            session.close();
            
            //commit();
            return update;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public List<Report> viewGeneratedReport(int doctorId)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from Report where employeeId = :id");
            q.setInteger("id", doctorId);
            
            List<Report> reportList = (List<Report>) q.list();
            
            //commit();
            return reportList;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + doctorId, e);
        }
    }
	
	public List<Report> viewMyReport(int smartId)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from Report where smartId = :id");
            q.setInteger("id", smartId);
            
            List<Report> reportList = (List<Report>) q.list();
            
            //commit();
            return reportList;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public Report getCheckManagerAssign(int smartId, int reportId)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from Report where smartId = :id and reportId = :rid");
            q.setInteger("id", smartId);
            q.setInteger("rid", reportId);
            
            Report s = (Report) q.uniqueResult();
            
            //commit();
            return s;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public int AssignFinanceManager(int smartId, int reportId, int financeManagerId)
            throws Exception {
        try {
            //begin();
        	int update = 1; 
        	Session session = getSession().getSessionFactory().openSession();
        	session.beginTransaction();
            Query q = session.createQuery("Update Report set assignedFinanceManager = :financeId where smartId = :id and reportId = :rid");
            q.setInteger("id", smartId);
            q.setInteger("rid", reportId);
            q.setInteger("financeId", financeManagerId);
            
            update = q.executeUpdate();
            session.getTransaction().commit();
            session.close();
            
            //commit();
            return update;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public int generateInvoice(int smartId, HospitalFinanceManager financeManager, Invoice invoice, int doctorId, int hospitalId, int reportId)
            throws Exception {
        try {
            //begin();
        	int update = 1;
        	Session session = getSession().getSessionFactory().openSession();
        	session.beginTransaction();
        	SmartId smartId2 = (SmartId) session.get(SmartId.class, smartId);
        	smartId2.getInvoiceList().add(invoice);
        	invoice.setPaid("Unpaid");
        	invoice.setSmartId(smartId2);
        	invoice.setReportId(reportId);
        	invoice.setDoctorId(doctorId);
        	invoice.setHospitalId(hospitalId);
        	invoice.setFinanceManager(financeManager);
        	financeManager.getInvoiceList().add(invoice);
        	session.save(invoice);
        	
        	session.getTransaction().commit();
        	session.close();
            return update;
            
            //commit();
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get Hospitals " , e);
        } 
    }
	
	public int updateInvoice(int smartId, int reportId)
            throws Exception {
        try {
            //begin();
        	int update = 1; 
        	Session session = getSession().getSessionFactory().openSession();
        	session.beginTransaction();
            Query q = session.createQuery("Update Report set generated = :gen where smartId = :id and reportId = :rid");
            q.setInteger("id", smartId);
            q.setInteger("rid", reportId);
            q.setString("gen", "Generated");
            
            update = q.executeUpdate();
            session.getTransaction().commit();
            session.close();
            
            //commit();
            return update;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public List<Invoice> viewGeneratedInvoice(int financeManagerId)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from Invoice where employeeId = :id");
            q.setInteger("id", financeManagerId);
            
            List<Invoice> invoiceList = (List<Invoice>) q.list();
            
            //commit();
            return invoiceList;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + financeManagerId, e);
        }
    }
	
	public List<Invoice> viewMyInvoice(int smartId)
            throws Exception {
        try {
            //begin();
            Query q = getSession().createQuery("from Invoice where smartId = :id");
            q.setInteger("id", smartId);
            
            List<Invoice> invoiceList = (List<Invoice>) q.list();
            
            //commit();
            return invoiceList;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
    }
	
	public int updatePayment(SmartId smartId, int invoiceId) throws Exception {
		try {
            //begin();
			int update = 1;
			Session session = getSession().getSessionFactory().openSession();
			session.beginTransaction();
			Query q = session.createQuery("update Invoice set paid = :pay where smartId = :sid and invoiceId = :iid");
            q.setInteger("sid", smartId.getSmartId());
            q.setString("pay", "Paid");
            q.setInteger("iid", invoiceId);
            
            update = q.executeUpdate();
            session.getTransaction().commit();
            session.close();
            
            //commit();
            return update;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
	}
	
	public String checkPayment(SmartId smartId, int invoiceId) throws Exception {
		try {
            //begin();
			String payment = "";
			Session session = getSession().getSessionFactory().openSession();
			session.beginTransaction();
			List<Invoice> invoiceList = smartId.getInvoiceList();
			for (Invoice invoice : invoiceList) {
				if(invoice.getInvoiceId() == invoiceId){
					payment = invoice.getPaid();
				}
			}
            
            session.close();
            
            //commit();
            return payment;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
	}
	
	public List<Message> viewUserMessages(String userName)
            throws Exception {
        try {
            //begin();
        	//Query q = getSession().createQuery("from Hospital h join h.smartId s where s.smartId = :id ");
        	Query q = getSession().createQuery("from Message where userName = :name");
        	q.setString("name", userName);
            
        	
        	List<Message> messageList = (List<Message>) q.list();
        	
            return messageList;
            
            //commit();
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get Hospitals " , e);
        }
    }
	
	public void newMessage(Message message, String userName)
            throws Exception {
        try {
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	Date date = new Date();
        	String d = String.valueOf(dateFormat.format(date));
            Session session = getSession().getSessionFactory().openSession();
            session.beginTransaction();
            message.setFromUser(userName);
            message.setMessageDate(d);
            session.save(message);
            session.getTransaction().commit();
            session.close();
        	
            
            //commit();
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get Hospitals " , e);
        }
    }
	
	public void deleteMessage(int messageId)
            throws Exception {
        try {
            Session session = getSession().getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("delete from Message where messageId = :mid");
            q.setInteger("mid", messageId);
            q.executeUpdate();
            session.getTransaction().commit();
            session.close();
        	
            
            //commit();
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get Hospitals " , e);
        }
    }
	
	public Invoice pdfInvoice(int smartId, int invoiceId) throws Exception {
		try {
            //begin();
			Query q = getSession().createQuery("from Invoice where invoiceId = :iid and smartId = :smart");
        	q.setInteger("iid", invoiceId);
        	q.setInteger("smart", smartId);
            
        	
        	Invoice invoice = (Invoice) q.uniqueResult();
        	
            return invoice;
        } catch (HibernateException e) {
            //rollback();
            throw new Exception("Could not get user " + smartId, e);
        }
	}

}
