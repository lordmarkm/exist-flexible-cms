package com.mynt.core.util.file;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by jbvillanueva on 5/26/17.
 */
public abstract class AbstractFileRepository implements FileRepository {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractFileRepository.class);

    @Value("${contentStorage.tempFolder:temp-file-repository}")
    protected String tempFolder;

    protected File tempDirectory;

    @PostConstruct
    public void init() throws IOException {
        if (StringUtils.isEmpty(tempFolder)) {
            throw new IllegalArgumentException("Temp folder can't be null!");
        }

        this.tempDirectory = new File(tempFolder);
        FileUtils.forceMkdir(tempDirectory);

        LOG.debug("Temp Repository initialized at {}", tempDirectory.getAbsolutePath());
    }

    @Override
    public boolean fileExists(String absolutePath) {
        if (StringUtils.isEmpty(absolutePath)) {
            return false;
        }
        File file = new File(absolutePath);
        return file.exists() && !file.isDirectory();
    }

    @Override
    public String createTempFile(String uid) {
        try {
            ResultFile resultFile = this.getFile(uid);
            File outputFile = new File(tempDirectory.getAbsolutePath(), uid);
            FileUtils.copyInputStreamToFile(resultFile.getInputStream(), outputFile);
            return outputFile.getAbsolutePath();
        } catch (IOException e) {
            LOG.error("Error writing to file!", e);
            return null;
        }
    }
}
