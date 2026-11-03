package com.ruoyi.common.core.exception.file;

import com.ruoyi.common.core.utils.StringUtils;

import java.io.Serial;

/**
 * 文件名称超长限制异常类
 */
public class FileNameLengthLimitExceededException extends FileException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super(StringUtils.format("上传的文件名最长{}个字符", defaultFileNameLength));
    }
}
