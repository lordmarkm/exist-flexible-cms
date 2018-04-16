package com.mynt.core.util.file;

import com.mynt.core.util.EncryptUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 * Created by jbvillanueva on 5/26/17.
 */
@Component
//@Profile("!prod")
public class SystemFileRepositoryImpl extends AbstractFileRepository {

    private static final Logger LOG = LoggerFactory.getLogger(SystemFileRepositoryImpl.class);

    @Value("${contentStorage.file.rootFolder}")
    private String rootFolder;

    @Value("${contentStorage.file.depth:6}")
    private Integer depth;

    private File baseDirectory;

    @PostConstruct
    public void fileRepository() throws IOException {
        if (StringUtils.isEmpty(rootFolder)) {
            return;
        }

        this.baseDirectory = new File(rootFolder);
        FileUtils.forceMkdir(baseDirectory);

        LOG.debug("File Repository initialized at {}", baseDirectory.getAbsolutePath());
    }

    @Override
    public ResultFile getFile(String uid) throws IOException {
        Assert.notNull(uid, "UID Should not be empty!");

        File file = getFileDirectory(uid);
        ResultFile resultFile = null;
        if (file.exists()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            FileInputStream fileInputStream = new FileInputStream(file);

            IOUtils.copy(fileInputStream, byteArrayOutputStream);


            resultFile = new ResultFile();
            resultFile.setInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            resultFile.setContentType(Files
                    .probeContentType(
                            FileSystems
                                    .getDefault()
                                    .getPath(file.getAbsolutePath())));
            resultFile.setContentLength(file.length());
            resultFile.setAbsolutePath(file.getAbsolutePath());

            IOUtils.closeQuietly(fileInputStream);
            IOUtils.closeQuietly(byteArrayOutputStream);
        } else {
            LOG.warn("Unable to locate File Image: {}", uid);
            resultFile = new ResultFile();
        }

        return resultFile;
    }

    @Override
    public boolean saveFile(byte[] stream, String uid) throws IOException {
        File outputFile = getFileDirectory(uid);
        LOG.info("Saving file @ : {}", outputFile.getAbsolutePath());
        FileUtils.writeByteArrayToFile(outputFile, stream);

        return true;
    }

    @Override
    public boolean saveFile(File inputFile, String uid) throws IOException {
        return saveFile(FileUtils.readFileToByteArray(inputFile), uid);
    }

    @Override
    public boolean saveFile(File file, String contentType, String uid) throws IOException {
        return saveFile(file, uid);
    }

    @Override
    public boolean saveFile(InputStream inputStream, Long contentLength, String contentType, String uid) throws IOException {
        File outputFile = getFileDirectory(uid);

        FileUtils.writeByteArrayToFile(outputFile, IOUtils.toByteArray(inputStream));
        IOUtils.closeQuietly(inputStream);

        return true;
    }

    private File getFileDirectory(String uid) throws IOException {
        Assert.notNull(baseDirectory, "Missing file storage configuration");

        String encryption = EncryptUtil.encryptDataStore(uid).replaceAll("[^A-Za-z0-9 ]", "");
        String folder = encryption.substring(0, depth);
        String[] subFolders = folder.split("");

        File file = new File(this.rootFolder);
        for (String subFolder : subFolders) {
            file = new File(file.getAbsolutePath(), subFolder);
        }

        file = new File(file.getAbsolutePath(), uid);

        return file;
    }

    @Override
    public ResultFile deleteFile(String uid) {
        try {
            ResultFile resultFile = getFile(uid);
            FileUtils.deleteQuietly(new File(resultFile.getAbsolutePath()));
            return resultFile;
        } catch (IOException e) {
            LOG.warn("Could not delete file! uid={}", uid, e);
            return null;
        }
    }
}
