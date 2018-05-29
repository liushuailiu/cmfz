package com.fly.view;

import com.fly.util.FileUtils;
import com.fly.util.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    private static final String path = "E:/image/" + FileUtils.getDate() + "/";

    @GetMapping("/{file}/{name}")
    public ResponseEntity<byte[]> download(@PathVariable("file") String fileName, @PathVariable("name") String name) throws IOException {

        File file = new File("E:/image/"+fileName+"/"+name+".jpg");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(
                org.apache.commons.io.FileUtils.readFileToByteArray(file),
                httpHeaders,HttpStatus.OK);
    }

    @PostMapping(value = "/uploads")
    public String uploads(@RequestParam MultipartFile[] files) throws IOException {
        String name = "";
        if(files!=null && files.length>0){
            for (MultipartFile file:files) {
                if (!file.isEmpty()) {
                    /**
                     * 获取文件名称
                     */
                    name = file.getOriginalFilename();
                    int suffix = name.lastIndexOf(".");
                    name = name.substring(suffix);

                    File targetFile = new File(path, System.currentTimeMillis() + name);
                    if(!targetFile.exists()){
                        targetFile.mkdirs();
                    }

                    file.transferTo(targetFile);

                }
            }
        }
        return "ok";
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String name = "";

        if (!file.isEmpty()) {
            /**
             * 获取文件名称
             */
            name = file.getOriginalFilename();
            int suffix = name.lastIndexOf(".");
            name = name.substring(suffix);

            File targetFile = new File(path, System.currentTimeMillis() + name);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }

            file.transferTo(targetFile);

        }
        return name;
    }

}
