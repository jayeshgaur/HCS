package com.cg.healthcaresystem.exceldownload;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.cg.healthcaresystem.dto.Appointment;

public class ExcelReportView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment;filename=\"appointments.xls\"");
		List<Appointment> appointmentList = (List<Appointment>) model.get("appointmentList");
		Sheet sheet = workbook.createSheet("Appointment List");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Appointment ID");
		header.createCell(1).setCellValue("Test Name");
		header.createCell(2).setCellValue("Center Name");
		header.createCell(3).setCellValue("DateAndTime");
		header.createCell(4).setCellValue("Status");

		int rowNum = 1;
		for (Appointment appointment : appointmentList) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(appointment.getAppointmentId().toString());
			row.createCell(1).setCellValue(appointment.getTest().getTestName());
			row.createCell(2).setCellValue(appointment.getCenter().getCenterName());
			row.createCell(3).setCellValue(appointment.getDateTime().toString());
			if (appointment.getAppointmentStatus() == 0) {
				row.createCell(4).setCellValue("Pending");
			} else {
				row.createCell(4).setCellValue("Approved");
			}
		}
	}
}
