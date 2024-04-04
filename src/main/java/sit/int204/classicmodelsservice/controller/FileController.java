package sit.int204.classicmodelsservice.controller;


import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sit.int204.classicmodelsservice.properties.FileStorageProperties;
import sit.int204.classicmodelsservice.serviceclass.FileService;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping("/test")
    public ResponseEntity<Object> testPropertiesMapping() {
        return ResponseEntity.ok(fileService.getFileStorageLocation() + " has been created !!!");
    }

    @PostMapping("")
    public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) {
        fileService.store(file);
        return ResponseEntity.ok("You successfully uploaded" + file.getOriginalFilename());
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = fileService.loadFileAsResource(filename);
        String fileName = file.getFilename();
        int lastDotPos = fileName.lastIndexOf('.');
        String extension =fileName.substring(lastDotPos);
        System.out.println("File extension" + extension);
        if (extension.equalsIgnoreCase(".pdf")){
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(file);
        }
        else {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
        }
    }



    @DeleteMapping("/{filename:.+}")
    public ResponseEntity<Object> delecteFile(@PathVariable String filename){
        fileService.deleteFilesAsResouce(filename);
        return ResponseEntity.ok("You successfully deleted" + filename);
    }
}
