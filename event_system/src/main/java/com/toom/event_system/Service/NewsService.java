package com.toom.event_system.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.toom.event_system.Entity.News;

import java.util.List;
import java.util.Map;

/**
 * 资讯表 服务类
 *
 * @author Toom
 */
public interface NewsService extends IService<News> {
    /**
     * 分页查询
     * @param page
     * @param wrapper
     */
    Page<News> searchNewsPage(Page<News> page, QueryWrapper<News> wrapper);

    /**
     * 新增资讯
     * @param news
     * @return
     */
    public Boolean insertNews(News news);

    /**
     * 修改资讯信息
     */
    public Boolean updateNews(News news);

    /**
     * 删除资讯
     */
    public Boolean deleteNewsById(Long newsId);

    /**
     * 批量删除资讯
     * @param newsIds
     * @return
     */
    public Boolean deleteNewsByIds(Long[]newsIds);

    /**
     *  通过ID查找资讯
     */
    public News selectNewsById(Long newsId);

    /**
     * 获取资讯类型
     * @return
     */
    public List<Map<String, Object>> selectNewsType();
}
