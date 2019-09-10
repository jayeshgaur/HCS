package com.cg.healthcaresystem.dto;

import java.time.LocalTime;
import java.util.Date;

public class Appointment {
		private User user;
		private String appointmentId;
		private Test test;
		private DiagnosticCenter center;
		private boolean approved;
		private Date date;
		private LocalTime time;
		
		
		public Appointment()
		{
			
		}
		
		//Constructor
		public Appointment(User user, Test test, DiagnosticCenter center) 
		{
			super();
			this.user = user;
			this.test = test;
			this.center = center;
		}

		
		//User
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		//Test
		public Test getTest() {
			return test;
		}

		public void setTest(Test test) {
			this.test = test;
		}

		
		//DiagnosticCenter
		public DiagnosticCenter getCenter() {
			return center;
		}

		public void setCenter(DiagnosticCenter center) {
			this.center = center;
		}

		public boolean isApproved() {
			return approved;
		}

		public void setApproved(boolean approved) {
			this.approved = approved;
		}

		public String getAppointmentId() {
			return appointmentId;
		}

		public void setAppointmentId(String appointmentId) {
			this.appointmentId = appointmentId;
		}
		
		//Implement hashcode, tostring and equals after implementing datetime
		
}
