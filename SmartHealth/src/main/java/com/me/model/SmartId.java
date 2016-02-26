package com.me.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SMARTID")
public class SmartId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "SMARTID")
	private int smartId;
	
	@Column(name = "FIRSTNAME")
	private String firstName;
	
	@Column(name = "LASTNAME")
	private String lastName;
	
	@Column(name = "STREET")
	private String street;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "ZIP")
	private String zip;
	
	@Column(name = "DATEOFBIRTH")
	private String dateOfBirth;
	
	@Column(name = "AGE")
	private int age;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PHONENO")
	private String phoneNo;
	
	@Column(name = "BLOODGROUP")
	private String bloodGroup;
	
	@Column(name = "HEIGHT")
	private float height;
	
	@Column(name = "WEIGHT")
	private float weight;
	
	@Column(name = "EMERGENCYCONTACTNAME")
	private String emergencyContactName;
	
	@Column(name = "EMERGENCYCONTACTPHONENO")
	private String emergencyContactPhoneNo;
	
	@Column(name = "PRESCRIBINGMEDICINE")
	private String priscribingMedicine;
	
	@Column(name = "MEDICALTESTTOOK")
	private String medicalTestTook;
	
	@Column(name = "ALLERGIES")
	private String allergies;
	
	@Column(name = "MEDICALHISTORYDESCRIPTION")
	private String medicalHistoryDecription;
	
	@Column(name = "VITALSIGNINFO")
	private String vitalSignInfo;
	
	@OneToOne
	@JoinColumn(name = "USERNAME")
	private Login login;
	
	@OneToMany(mappedBy="smartId")
	private List<Report> reportList = new ArrayList<Report>();
	
	@OneToMany(mappedBy="smartId")
	private List<Invoice> invoiceList = new ArrayList<Invoice>();
	
	@ManyToMany
	@JoinTable(name="PREFERREDHOSPITAL", joinColumns={@JoinColumn(name="SMARTID")}, inverseJoinColumns={@JoinColumn(name="HOSPITALID")})
	private List<Hospital> hospital = new List<Hospital>() {

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator<Hospital> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean add(Hospital e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends Hospital> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(int index, Collection<? extends Hospital> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Hospital get(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Hospital set(int index, Hospital element) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void add(int index, Hospital element) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Hospital remove(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int indexOf(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int lastIndexOf(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public ListIterator<Hospital> listIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ListIterator<Hospital> listIterator(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Hospital> subList(int fromIndex, int toIndex) {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public int getSmartId() {
		return smartId;
	}

	public void setSmartId(int smartId) {
		this.smartId = smartId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactPhoneNo() {
		return emergencyContactPhoneNo;
	}

	public void setEmergencyContactPhoneNo(String emergencyContactPhoneNo) {
		this.emergencyContactPhoneNo = emergencyContactPhoneNo;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getPriscribingMedicine() {
		return priscribingMedicine;
	}

	public void setPriscribingMedicine(String priscribingMedicine) {
		this.priscribingMedicine = priscribingMedicine;
	}

	public String getMedicalTestTook() {
		return medicalTestTook;
	}

	public void setMedicalTestTook(String medicalTestTook) {
		this.medicalTestTook = medicalTestTook;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getMedicalHistoryDecription() {
		return medicalHistoryDecription;
	}

	public void setMedicalHistoryDecription(String medicalHistoryDecription) {
		this.medicalHistoryDecription = medicalHistoryDecription;
	}

	public String getVitalSignInfo() {
		return vitalSignInfo;
	}

	public void setVitalSignInfo(String vitalSignInfo) {
		this.vitalSignInfo = vitalSignInfo;
	}

	public List<Hospital> getHospital() {
		return hospital;
	}

	public void setHospital(List<Hospital> hospital) {
		this.hospital = hospital;
	}

	public List<Report> getReportList() {
		return reportList;
	}

	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

	
	
	
	
	
	

}
