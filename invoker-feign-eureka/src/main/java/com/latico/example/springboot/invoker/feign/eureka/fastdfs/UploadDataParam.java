package com.latico.example.springboot.invoker.feign.eureka.fastdfs;

import java.io.Serializable;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author latico
 * @version 1.0.0
 * @date 2020-12-30 9:42
 */
public class UploadDataParam implements Serializable {
    private String fileName;
    private String content;
    private String overwrite;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOverwrite() {
        return overwrite;
    }

    public void setOverwrite(String overwrite) {
        this.overwrite = overwrite;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UploadDataParam{");
        sb.append("fileName='").append(fileName).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", overwrite='").append(overwrite).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
