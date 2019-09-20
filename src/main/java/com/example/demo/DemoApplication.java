package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import lombok.Getter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RestController
	public static class ExcelController {

		public ExcelController(UserRepository userRepository) {
			this.userRepository = userRepository;
		}

		private final UserRepository userRepository;

		@GetMapping(value = "/excel")
		public ResponseEntity<InputStreamResource> getExcel() {

			List<User> users = userRepository.findAll();

			ExcelConverter excelConverter = new ExcelConverter();
			ByteArrayOutputStream out = excelConverter
				.usersToExcel(users)
				.write();

			return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xlsx")
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(
					new InputStreamResource(new ByteArrayInputStream(out.toByteArray())));
		}

		@Getter
		static  class ExcelConverter {

			XSSFWorkbook workbook;

			ExcelConverter  usersToExcel(List<User> users) {
				workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("sheet 1");

				XSSFRow header = sheet.createRow(0);
				XSSFCell idCell = header.createCell(0);
				idCell.setCellValue("ID");
				XSSFCell nameCell = header.createCell(1);
				nameCell.setCellValue("NAME");

				for(int i=0; i< users.size(); i++) {
					User user = users.get(i);
					XSSFRow row = sheet.createRow(i + 1);
					XSSFCell idC = row.createCell(0);
					idC.setCellValue(user.getId());
					XSSFCell nameC = row.createCell(1);
					nameC.setCellValue(user.getName());
				}

				return this;
			}

            ByteArrayOutputStream write() {

				ByteArrayOutputStream out = new ByteArrayOutputStream();

				try {
					workbook.write(out);
				} catch (IOException e) {
				}

				return out;
			}
		}
	}
}
