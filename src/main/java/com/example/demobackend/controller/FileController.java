package com.example.demobackend.controller;

import com.example.demobackend.websocket.FileUploadWebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description
 * @Author wh
 * @Date 2025/1/22
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileUploadWebSocketHandler fileUploadWebSocketHandler;

    public FileController(FileUploadWebSocketHandler fileUploadWebSocketHandler) {
        this.fileUploadWebSocketHandler = fileUploadWebSocketHandler;
    }

    @PostMapping("/fileUpload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            long fileSize = file.getSize();
            byte[] buffer = new byte[8192];
            long totalBytesRead = 0;

            while (inputStream.read(buffer) != -1) {
                totalBytesRead += buffer.length;
                int progress = (int) ((totalBytesRead * 100) / fileSize);
                log.info("Upload Progress: {}%", progress);  // 这里可以推送进度到前端
            }
            inputStream.close();
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }

    @PostMapping("/fileUploadWebSocket")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileId") String fileId, // 获取 fileId
            @RequestParam("sessionId") String sessionId
            ) {

        long fileSize = file.getSize();
        long totalBytesRead = 0;
        byte[] buffer = new byte[8192];

        try (InputStream inputStream = file.getInputStream()) {
            while (inputStream.read(buffer) != -1) {
                totalBytesRead += buffer.length;
                int progress = (int) ((totalBytesRead * 100) / fileSize);
                progress = Math.min(progress, 100);

                // 发送包含 fileId 的 WebSocket 消息
                String message = String.format("{\"sessionId\": \"%s\", \"fileId\": \"%s\", \"progress\": %d}",
                        sessionId, fileId, progress);
                System.out.println("Upload message: " + message);
                fileUploadWebSocketHandler.sendProgress(sessionId, message);
                Thread.sleep(100); // 模拟网络延迟
            }
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.status(500).body("File upload failed");
        }

        return ResponseEntity.ok("File uploaded successfully");
    }

}
