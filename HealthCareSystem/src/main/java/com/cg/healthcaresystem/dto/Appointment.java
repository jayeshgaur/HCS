package com.cg.healthcaresystem.dto;


import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
	
		private final String prefix = "App";
		private User user;
		private String appointmentId;
		private Test test;
		private DiagnosticCenter center;
		private boolean approved;
		private LocalDate date;
		private LocalTime time;
		
		public Appointment()
		{
			
		}
		
		public Appointment(User user, Test test, DiagnosticCenter center, LocalDate date,LocalTime time) {
			super();
			this.setAppointmentId(prefix + center.getAppointmentCounter().toString());
			this.setApproved(false);
			this.user = user;
			this.test = test;
			this.center = center;
			this.date = date;
			this.time = time;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public String getAppointmentId() {
			return appointmentId;
		}

		public void setAppointmentId(String appointmentId) {
			this.appointmentId = appointmentId;
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

		public boolean isApproved() {
			return approved;
		}

		public boolean setApproved(boolean approved) {
			return this.approved = approved;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public LocalTime getTime() {
			return time;
		}

		public void setTime(LocalTime time) {
			this.time = time;
		}
		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((appointmentId == null) ? 0 : appointmentId.hashCode());
			result = prime * result + (approved ? 1231 : 1237);
			result = prime * result + ((center == null) ? 0 : center.hashCode());
			result = prime * result + ((date == null) ? 0 : date.hashCode());
			result = prime * result + ((test == null) ? 0 : test.hashCode());
			result = prime * result + ((user == null) ? 0 : user.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Appointment other = (Appointment) obj;
			if (appointmentId == null) {
				if (other.appointmentId != null)
					return false;
			} else if (!appointmentId.equals(other.appointmentId))
				return false;
			if (approved != other.approved)
				return false;
			if (center == null) {
				if (other.center != null)
					return false;
			} else if (!center.equals(other.center))
				return false;
			if (date == null) {
				if (other.date != null)
					return false;
			} else if (!date.equals(other.date))
				return false;
			if (test == null) {
				if (other.test != null)
					return false;
			} else if (!test.equals(other.test))
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Appointment [user=" + user + ", appointmentId=" + appointmentId + ", test=" + test + ", center="
					+ center + ", approved=" + approved + ", date=" + date + "]";
		}

		
		
		
		
		//Implement hashcode, tostring and equals after implementing datetime
		
}
