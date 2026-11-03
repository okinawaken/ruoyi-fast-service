package com.ruoyi.common.core.exception.file;

import com.ruoyi.common.core.utils.StringUtils;

import java.io.Serial;

/**
 * 文件名大小限制异常类
 */
public class FileSizeLimitExceededException extends FileException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super(StringUtils.format("上传的文件大小超出限制！<br/>允许的文件最大大小是：{}MB！", defaultMaxSize));
    }
}
