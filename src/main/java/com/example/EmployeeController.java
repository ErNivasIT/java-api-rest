package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.Services.EmployeeServices;

import java.io.File;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class EmployeeController {
    private static String UPLOAD_DIR = "uploads";
    @Autowired
    private EmployeeServices _empServices;
    @GetMapping("/hello/{name}")
    public String Hello(@PathVariable String name) {
        return "Hello you passed " + name;
    }

    @PostMapping("/save")
    public String Save(@RequestBody Employee employee) {
        return "Posted Employee Obj";
    }

    @PostMapping("/uploadTextFile")
    public String handleFileUpload(@RequestParam("files") List<MultipartFile> files) throws AccessDeniedException {

        StringBuilder response = new StringBuilder("Files uploaded successfully:\n");

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create directory if it doesn't exist
        }

        for (MultipartFile file : files) {

            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename());
                Files.write(path, bytes); // Write file to local filesystem
            } catch (Exception e) {
                throw new AccessDeniedException("Unauthorised Access!");
            }

            response.append(file.getOriginalFilename()).append("\n");

        }

        // Logic to handle file upload
        return response.toString();
    }
    @GetMapping("GetEmps")
    public List<Employee> GetEmployees()
    {
        List<Employee> res=_empServices.GetEmployees();
        return res;
    }
}
