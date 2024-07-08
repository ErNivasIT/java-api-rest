package com.example;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/api")
public class EmployeeController {
    private static String UPLOAD_DIR = "uploads"; 

    @GetMapping("/hello/{name}")
    public String Hello(@PathVariable  String name ) {
        return "Hello you passed "+name;
    }
    @PostMapping("/save")
    public String Save(@RequestBody Employee employee) {
        return "Posted Employee Obj";
    }

    @PostMapping("/uploadTextFile")
    public String handleFileUpload(@RequestParam("files") List<MultipartFile> files) {

        StringBuilder response = new StringBuilder("Files uploaded successfully:\n");


         for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; // Skip empty files
            }
            
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename());
                Files.write(path, bytes); // Write file to local filesystem

                response.append(file.getOriginalFilename()).append("\n");
            } catch (IOException e) {
                return "Failed to upload " + file.getOriginalFilename() + ": " + e.getMessage();
            }
        }

        // Logic to handle file upload
        return response.toString();
    }
}
