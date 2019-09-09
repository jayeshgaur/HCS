package com.cg.healthcaresystem.dto;

public class Appointment {
		private User user;
		private String appointmentId;
		private Test test;
		private DiagnosticCenter center;
		private boolean approved;
		
		public Appointment()
		{
			
		}
		
		public Appointment(User user, Test test, DiagnosticCenter center) 
		{
			super();
			this.user = user;
			this.test = test;
			this.center = center;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Test getTest() {
			return test;
		}

		public void setTest(Test test) {
			this.test = test;
		}

		public DiagnosticCenter getCenter() {
			return center;
		}

		public void setCenter(DiagnosticCenter center) {
			this.center = center;
		}
		
		
		
}
