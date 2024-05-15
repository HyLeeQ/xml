package baitap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bai2 {
	public static void main(String[] args) {
		// Tạo danh sách sinh viên
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student("John Smith", 20, 3.5));
		studentList.add(new Student("Jane Doe", 22, 3.8));
		studentList.add(new Student("David Johnson", 21, 3.2));

		// Ghi danh sách sinh viên vào file XML
		try (FileWriter writer = new FileWriter("student_list.xml")) {
			// Ghi thẻ mở root element
			writer.write("<students>");

			// Ghi từng sinh viên dưới dạng file XML
			for (Student student : studentList) {
				writer.write("<student>");
				writer.write("<name>" + student.getName() + "</name>");
				writer.write("<age>" + student.getAge() + "</age>");
				writer.write("<gpa>" + student.getGpa() + "</gpa>");
				writer.write("</student>");
			}

			// Ghi thẻ đóng root element
			writer.write("</students>");

			System.out.println("Danh sách sinh viên đã được lưu vào file student_list.xml.");
		} catch (IOException e) {
			System.out.println("Đã xảy ra lỗi khi ghi file XML.");
			e.printStackTrace();
		}
		try {
			// Đọc nội dung của tệp student_list.xml
			File file = new File("student_list.xml");
			Scanner scanner = new Scanner(file);

			// In từng dòng ra màn hình console
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}

			// Đóng Scanner sau khi sử dụng
			scanner.close();
		} catch (IOException e) {
			System.out.println("Đã xảy ra lỗi khi đọc file XML.");
			e.printStackTrace();
		}
	}
}

class Student {
	private String name;
	private int age;
	private double gpa;

	public Student(String name, int age, double gpa) {
		this.name = name;
		this.age = age;
		this.gpa = gpa;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getGpa() {
		return gpa;
	}
}