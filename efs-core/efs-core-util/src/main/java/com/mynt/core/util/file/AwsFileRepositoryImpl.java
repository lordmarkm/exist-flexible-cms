package com.mynt.core.util.file;

import static com.amazonaws.services.s3.model.Permission.Read;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;

/**
 * @author ddevera
 */
//@Component
//@Profile("prod")
public class AwsFileRepositoryImpl extends AbstractFileRepository {

    private static final Logger LOG = LoggerFactory.getLogger(AwsFileRepositoryImpl.class);

    @Value("${contentStorage.s3.bucketName}")
    private String bucketName;

    private AmazonS3 amazonS3;

    @PostConstruct
    public void awsFileRepositoryImpl() throws IOException {
        if (StringUtils.isEmpty(bucketName)) {
            return;
        }
        LOG.info("Enabling S3 AWS Instance. bucket name={}", bucketName);
        this.amazonS3 = AmazonS3ClientBuilder.defaultClient();
        if (!amazonS3.doesBucketExist(bucketName)) {
            LOG.info("Creating Bucket @ with name {}", bucketName);
            amazonS3.createBucket(bucketName);
        }
    }

    @Override
    public ResultFile getFile(String uid) {
        Assert.notNull(uid, "UID Should not be empty!");
        Assert.notNull(bucketName, "Missing s3 storage configuration");

        ResultFile resultFile = null;

        LOG.info("Preparing Data Download for unique identifier : {} on bucket : {}", uid, bucketName);

        InputStream inputStream;
        ObjectMetadata metadata;

        try {
            S3Object s3Object = amazonS3.getObject(
                    new GetObjectRequest(
                            bucketName,
                            uid));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            IOUtils.copy(s3Object.getObjectContent(), byteArrayOutputStream);

            LOG.info("Successfully retrieved S3 Object with identifier : {} on {}", uid, bucketName);

            s3Object.close();

            metadata = s3Object.getObjectMetadata();
            inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            byteArrayOutputStream.close();

            resultFile = new ResultFile();

            resultFile.setInputStream(inputStream);
            resultFile.setContentLength(metadata.getContentLength());
            resultFile.setContentType(metadata.getContentType());

        } catch (AmazonServiceException ase) {
            S3ServiceExceptionLog(ase);
        } catch (AmazonClientException | IOException ace) {
            LOG.error(ace.getMessage(), ace);
        }

        return resultFile;
    }

    @Override
    public boolean saveFile(byte[] stream, String uid) throws IOException {
        File file = new File(uid);
        FileUtils.writeByteArrayToFile(file, stream);

        String fileUrl = saveContent(
                new PutObjectRequest(
                        bucketName,
                        uid,
                        file),
                uid);

        return StringUtils.isNotEmpty(fileUrl);
    }

    @Override
    public boolean saveFile(File file, String uid) {
        String fileUrl = saveContent(
                new PutObjectRequest(
                        bucketName,
                        uid,
                        file),
                uid);

        return StringUtils.isNotEmpty(fileUrl);
    }

    @Override
    public boolean saveFile(File file, String contentType, String uid) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucketName,
                uid,
                file);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);

        putObjectRequest.setMetadata(objectMetadata);

        String fileUrl = saveContent(
                putObjectRequest,
                uid);

        return StringUtils.isNotEmpty(fileUrl);
    }

    @Override
    public boolean saveFile(InputStream inputStream, Long contentLength, String contentType, String uid) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(contentLength);
        objectMetadata.setContentType(contentType);

        String fileUrl = saveContent(
                new PutObjectRequest(
                        bucketName,
                        uid,
                        inputStream,
                        objectMetadata),
                uid);

        return StringUtils.isNotEmpty(fileUrl);
    }

    private String saveContent(PutObjectRequest putObjectRequest, String identifier) {
        Assert.notNull(bucketName,  "Missing s3 storage configuration");

        String accessUrl = null;
        try {
            AccessControlList accessControlList = new AccessControlList();
            accessControlList.grantPermission(GroupGrantee.AuthenticatedUsers, Read);

            putObjectRequest.withAccessControlList(accessControlList);
            PutObjectResult result = amazonS3.putObject(putObjectRequest);

            if (result != null) {
                LOG.info("Successfully Saved {} on {}. Retrieving Access URL.", identifier, bucketName);

                S3Object s3Object = amazonS3.getObject(
                        new GetObjectRequest(
                                bucketName,
                                identifier));

                accessUrl = s3Object
                        .getObjectContent()
                        .getHttpRequest()
                        .getURI()
                        .toString();

                s3Object.close();

            }
        } catch (AmazonServiceException ase) {
            S3ServiceExceptionLog(ase);
        } catch (AmazonClientException | IOException ace) {
            LOG.error(ace.getMessage(), ace);
        }
        return accessUrl;
    }

    @Override
    public ResultFile deleteFile(String uid) {
        Assert.notNull(uid, "UID Should not be empty!");
        Assert.notNull(bucketName, "Missing s3 storage configuration");

        ResultFile resultFile = getFile(uid);
        DeleteObjectRequest dor = new DeleteObjectRequest(bucketName, uid);
        amazonS3.deleteObject(dor);

        return resultFile;
    }

    private void S3ServiceExceptionLog(AmazonServiceException ase) {
        LOG.error("An error was encountered on the AWS S3 Server");
        LOG.error(ase.getMessage(), ase);
        LOG.error("HTTP Status Code : " + ase.getStatusCode(), ase);
        LOG.error("AWS Error Code : " + ase.getErrorCode(), ase);
        LOG.error("Error Type : " + ase.getErrorType(), ase);
        LOG.error("Request ID : " + ase.getRequestId(), ase);
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

}
