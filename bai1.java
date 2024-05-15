package baitap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class bai1 {
    public static void main(String[] args) {
        // Tạo đối tượng Scanner để đọc đường dẫn từ console
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập đường dẫn thư mục: ");
        String directoryPath = scanner.nextLine();

        // Tạo đối tượng File từ đường dẫn thư mục
        File directory = new File(directoryPath);

        // Kiểm tra xem đường dẫn có tồn tại và là thư mục không
        if (directory.exists() && directory.isDirectory()) {
            // Tạo đối tượng FileWriter để ghi dữ liệu XML vào file
            try (FileWriter writer = new FileWriter("directory_listing.xml")) {
                // Ghi thẻ mở root element
                writer.write("<" + directory.getName() + ">");

                // Liệt kê cây thư mục dưới dạng file XML
                listDirectory(directory, writer);

                // Ghi thẻ đóng root element
                writer.write("</" + directory.getName() + ">");

                System.out.println("Cây thư mục đã được lưu vào file directory_listing.xml.");
            } catch (IOException e) {
                System.out.println("Đã xảy ra lỗi khi ghi file XML.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Đường dẫn thư mục không tồn tại hoặc không phải là thư mục.");
        }

        // Đóng Scanner sau khi sử dụng
        scanner.close();
    }

    private static void listDirectory(File directory, FileWriter writer) throws IOException {
        // Lấy danh sách các tệp và thư mục con
        File[] files = directory.listFiles();

        // Kiểm tra xem danh sách có rỗng không
        if (files != null) {
            // Duyệt qua từng tệp và thư mục con
            for (File file : files) {
                if (file.isDirectory()) {
                    // Nếu là thư mục, gọi đệ quy để liệt kê cây thư mục con
                    writer.write("<" + file.getName() + ">");
                    listDirectory(file, writer);
                    writer.write("</" + file.getName() + ">");
                } else {
                    // Nếu là tệp, ghi thẻ file
                    writer.write("<file>" + file.getName() + "</file>");
                }
            }
        }
    }
}