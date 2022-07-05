package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Api(tags = "腾讯云点播")
@RestController
@RequestMapping("/admin/vod")
//@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频
    @PostMapping("upload")
    public Result uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String videoId = vodService.uploadVideo(inputStream, originalFilename);
        return Result.ok(videoId);
    }

    //删除视频
    @DeleteMapping("remove/{videoSourceId}")
    public Result removeVideo( @PathVariable String videoSourceId) {
        vodService.removeVideo(videoSourceId);
        return Result.ok(null);
    }
}