package com.angel.util;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * redis key 工具类
 * @Author angel
 * @Date 19-5-4
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisKeyUtil {

    /**
     * The constant RESET_PWD_TOKEN_KEY.
     */
    private static final String RESET_PWD_TOKEN_KEY = "angelcloud:restPwd";
    private static final String ACCESS_TOKEN = "angelcloud:token:accessToken";
    private static final String UPLOAD_FILE_SIZE = "angelcloud:file:upload_file_size";
    private static final String ARTICLE_DETAIL = "angelcloud:blog:article";
    private static final String ARTICLE_BROWSE_COUNT = "angelcloud:blog:article_browse_count";
    private static final int REF_NO_MAX_LENGTH = 100;

    /**
     * 获取文章详情key
     * @param id
     * @return 常量名称
     */
    public static String getArticleDetail(Integer id) {
        Preconditions.checkArgument(id != null, "参数不能为空");
        return ARTICLE_DETAIL + ":" + id;

    }

    /**
     * 获取文章浏览数
     * @return 常量名称
     */
    public static String getArticleBrowseCount() {
        return ARTICLE_BROWSE_COUNT;

    }
}
