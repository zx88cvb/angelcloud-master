package com.angel.provider.service.impl;

import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.BlogArticleMapper;
import com.angel.provider.model.domain.BlogArticle;
import com.angel.provider.model.dto.BlogArticleDto;
import com.angel.provider.model.dto.BlogCategoryDto;
import com.angel.provider.model.vo.BlogCategoryVo;
import com.angel.provider.model.vo.SysUserVo;
import com.angel.provider.service.IBlogArticleService;
import com.angel.provider.service.IUserSysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements IBlogArticleService {

    @Resource
    private BlogArticleMapper blogArticleMapper;

    @Resource
    private IUserSysUserService iUserSysUserService;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<BlogArticleDto>> getBlogArticlePage(BlogArticleDto blogArticleDto) {
        // 将当前页和每页显示数量加入Page对象
        Page<BlogArticleDto> page = new Page<>();

        // DTO -> Entity
        BlogArticle blogArticle = new BlogArticle();
        BeanUtils.copyProperties(blogArticleDto, blogArticle);

        //查询
        IPage<BlogArticle> blogArticlePage = blogArticleMapper
                .selectBlogArticleConditionPage(new Page<>(blogArticleDto.getPageNum(), blogArticleDto.getPageSize()),
                blogArticle);

        List<BlogArticle> blogArticleList = blogArticlePage.getRecords();

        // stram() 转换成 VO LIST
        List<BlogArticleDto> articleVoList = blogArticleList.stream().map(e -> {
            BlogArticleDto blogArticleItem = new BlogArticleDto();
            BeanUtils.copyProperties(e, blogArticleItem);

            // Category -> CategoryVo
            BlogCategoryVo blogCategoryVoItem = new BlogCategoryVo();
            BeanUtils.copyProperties(e.getBlogCategory(), blogCategoryVoItem);
            blogArticleItem.setBlogCategoryVo(blogCategoryVoItem);

            // feign 远程调用 获取SysUserVo
            ServiceResult<SysUserVo> userVo = iUserSysUserService.getUserVo(e.getUserId());
            SysUserVo sysUserVo = userVo.getResult();
            blogArticleItem.setSysUserVo(sysUserVo);
            return blogArticleItem;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(blogArticlePage, page);
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
        if (result < 1) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(result);
    }
}
