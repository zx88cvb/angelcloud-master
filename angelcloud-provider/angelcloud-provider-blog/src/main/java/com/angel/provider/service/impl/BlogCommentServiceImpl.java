package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.BlogCommentMapper;
import com.angel.provider.model.domain.BlogComment;
import com.angel.provider.model.dto.BlogCommentDto;
import com.angel.provider.model.vo.BlogCommentVo;
import com.angel.provider.service.IBlogCommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 博客评论实现层
 * @Author angel
 * @Date 19-3-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment>
        implements IBlogCommentService {

    @Resource
    private BlogCommentMapper blogCommentMapper;

    @Override
    public ServiceResult<Integer> deleteBlogCommentById(int id) {
        BlogComment blogComment = new BlogComment();
        blogComment.setId(id);
        blogComment.setIsDel(GlobalConstant.IsDel.YES);
        blogComment.setUpdateTime(new Date());
        Integer count = blogCommentMapper.updateById(blogComment);
        if (count < 1) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<BlogCommentVo>> getBlogCommentPage(BlogCommentDto blogCommentDto) {
        // 将当前页和每页显示数量加入Page对象
        Page<BlogCommentVo> page = new Page<>();

        //查询
        IPage<BlogComment> iPage = blogCommentMapper
                .selectBlogCommentConditionPage(new Page<>(blogCommentDto.getPageNum(), blogCommentDto.getPageSize()),
                        blogCommentDto);

        List<BlogComment> blogCommentList = iPage.getRecords();

        // stram() 转换成 VO LIST
        List<BlogCommentVo> articleVoList = blogCommentList.stream().map(e -> {
            BlogCommentVo blogCommentVo = new BlogCommentVo();
            // 递归调用查询无限子评论
            // TODO 性能不高 后期加入缓存
            getTreeNodeData(e.getBlogCommentList());
            BeanUtils.copyProperties(e, blogCommentVo);

            return blogCommentVo;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(iPage, page);
        // 将数据添加到Page
        page.setRecords(articleVoList);

        return ServiceResult.of(page);
    }

    /**
     * 递归调用
     * @param list 子评论list
     */
    public void getTreeNodeData(List<BlogComment> list){
        if (list.size() > 0) {
            for (BlogComment blogComment: list) {
                List<BlogComment> blogCommentList =
                        blogCommentMapper.selectCommentByPId(blogComment.getId());

                blogComment.setBlogCommentList(blogCommentList);
                getTreeNodeData(blogCommentList);
            }
        }
    }
}
