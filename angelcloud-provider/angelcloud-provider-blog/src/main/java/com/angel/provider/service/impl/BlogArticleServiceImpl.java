package com.angel.provider.service.impl;

import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.BlogArticleMapper;
import com.angel.provider.model.domain.BlogArticle;
import com.angel.provider.model.dto.BlogArticleDto;
import com.angel.provider.model.vo.BlogArticleVo;
import com.angel.provider.model.vo.SysUserVo;
import com.angel.provider.service.IBlogArticleService;
import com.angel.provider.service.IUserSysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 博客文章表 服务实现类
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements IBlogArticleService {

    @Resource
    private BlogArticleMapper blogArticleMapper;

    @Resource
    private IUserSysUserService iUserSysUserService;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<BlogArticleVo>> getBlogArticlePage(BlogArticleDto blogArticleDto) {
        // 将当前页和每页显示数量加入Page对象
        Page<BlogArticleVo> page = new Page<>(blogArticleDto.getPageNum(), blogArticleDto.getPageSize());

        // DTO -> Entity
        BlogArticle blogArticle = new BlogArticle();
        BeanUtils.copyProperties(blogArticleDto, blogArticle);

        //查询
        IPage<BlogArticle> blogArticlePage = blogArticleMapper.selectBlogArticleConditionPage(page, blogArticle);

        List<BlogArticle> blogArticleList = blogArticlePage.getRecords();

        // stram() 转换成 VO LIST
        List<BlogArticleVo> articleVoList = blogArticleList.stream().map(e -> {
            BlogArticleVo blogArticleVo = new BlogArticleVo();
            BeanUtils.copyProperties(e, blogArticleVo);

            // feign 远程调用 获取SysUserVo
            ServiceResult<SysUserVo> userVo = iUserSysUserService.getUserVo(e.getUserId());
            SysUserVo sysUserVo = userVo.getResult();
            blogArticleVo.setSysUserVo(sysUserVo);
            return blogArticleVo;
        }).collect(Collectors.toList());

        // 将数据添加到Page
        page.setRecords(articleVoList);

        return ServiceResult.of(page);
    }

    /**
     * 新增博客文章
     * @param blogArticleDto 条件实体类DTO
     * @return 返回成功个数
     */
    @Override
    public ServiceResult<Integer> insertBlogArticle(BlogArticleDto blogArticleDto) {
        BlogArticle blogArticle = new BlogArticle();
        BeanUtils.copyProperties(blogArticleDto, blogArticle);
        Integer result = blogArticleMapper.insert(blogArticle);
        return ServiceResult.of(result);
    }
}
