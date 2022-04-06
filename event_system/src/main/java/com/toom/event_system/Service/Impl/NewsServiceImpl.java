package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.News;
import com.toom.event_system.Entity.News;
import com.toom.event_system.Mapper.NewsMapper;
import com.toom.event_system.Mapper.NewsMapper;
import com.toom.event_system.Service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 资讯表 服务实现类
 *
 * @author Toom
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    private NewsMapper newsMapper;
    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public Page<News> searchNewsPage(Page<News> page, QueryWrapper<News> wrapper) {
        return newsMapper.selectPage(page, wrapper);
    }

    /**
     * 新增资讯
     * @param news
     * @return
     */
    @Override
    public Boolean insertNews(News news) {
        return newsMapper.insert(news) > 0 ? true : false;
    }

    /**
     * 修改资讯
     * @param news
     * @return
     */
    @Override
    public Boolean updateNews(News news) {
        news.setUpdateTime(new Date());
        return newsMapper.updateById(news) > 0 ? true : false;
    }

    /**
     * 删除资讯
     * @param newsId
     * @return
     */
    @Override
    public Boolean deleteNewsById(Long newsId) {
        return newsMapper.deleteById(newsId) > 0 ? true : false;
    }

    /**
     * 批量删除资讯
     * @param newsIds
     * @return
     */
    @Override
    public Boolean deleteNewsByIds(Long[] newsIds) {
        List<Long> list = new ArrayList<>();
        for (long id : newsIds) {
            list.add(id);
        }
        return newsMapper.deleteBatchIds(list) > 0 ? true : false;
    }

    /**
     * 通过ID找资讯
     * @param newsId
     * @return
     */
    @Override
    public News selectNewsById(Long newsId) {
        return newsMapper.selectById(newsId);
    }

    /**
     * 获取资讯类型
     * @return
     */
    @Override
    public List<Map<String, Object>> selectNewsType() {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT news_type");
        List<Map<String, Object>> typeList = new ArrayList<>();
        for (News type : newsMapper.selectList(wrapper)){
            Map<String, Object> map = new HashMap<>();
            map.put("typeNum", type.getNewsType());
            map.put("typeName", type.getNewsType());
            typeList.add(map);
        }
        return typeList;
    }
}
