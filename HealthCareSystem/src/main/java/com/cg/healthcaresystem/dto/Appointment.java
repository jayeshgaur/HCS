package com.cg.healthcaresystem.dto;

public class Appointment {
		private User user;
		private String appointmentId;
		private Test test;
		private DiagnosticCenter center;
		private boolean approved;
		// DateTime not implemented
		
		
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
		
		//Implement hashcode, tostring and equals after implementing datetime
		
}
