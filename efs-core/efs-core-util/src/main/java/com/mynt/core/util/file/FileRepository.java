package com.mynt.core.util.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jbvillanueva on 5/26/17.
 */
public interface FileRepository {

    ResultFile getFile(String uid) throws IOException;

    boolean saveFile(File file, String uid) throws IOException;

    boolean saveFile(byte[] stream, String uid) throws IOException;

    boolean saveFile(File file, String contentType, String uid) throws IOException;

    boolean saveFile(InputStream inputStream, Long contentLength, String contentType, String uid) throws IOException;

    ResultFile deleteFile(String uid);

    boolean fileExists(String absolutePath);

    String createTempFile(String fileIdentifier);
}