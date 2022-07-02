package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.vod.service.FileService;
import com.atguigu.ggkt.vod.utils.ConstantPropertiesUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Missile
 * @Date 2022-07-01-13:23
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
        String secretId = ConstantPropertiesUtil.ACCESS_KEY_ID;

        String secretKey = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);

        Region region = new Region(ConstantPropertiesUtil.END_POINT);

        ClientConfig clientConfig = new ClientConfig(region);

        clientConfig.setHttpProtocol(HttpProtocol.https);

        COSClient cosClient = new COSClient(cred, clientConfig);

        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        String key = UUID.randomUUID().toString().replace("-","")+file.getOriginalFilename();

        String dateTime = new DateTime().toString("yyyy/MM/dd");

        key=dateTime+"/"+key;
        try {
            InputStream inputStream = file.getInputStream();

            ObjectMetadata objectMetadata = new ObjectMetadata();

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);

            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cosClient.getObjectUrl(bucketName, key).toString();
//        return "https://"+bucketName+".cos."+region+".myqcloud.com"+"/"+key;
    }
}
