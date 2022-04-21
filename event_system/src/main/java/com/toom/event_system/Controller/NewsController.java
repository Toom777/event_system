package com.toom.event_system.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Common.Result;
import com.toom.event_system.Entity.News;
import com.toom.event_system.Entity.PageInfo;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 资讯表 控制器
 * @author Toom
 */
@RestController
@RequestMapping("/news")
public class NewsController extends BaseController{

    @Autowired
    private NewsService newsService;

    /**
     * 获取资讯类型
     */
    @GetMapping("/getNewsType")
    public Result getNewsType() {
        return Result.success(newsService.selectNewsType());
    }

    /**
     * 分页条件查询资讯列表
     * @param pageCurrent
     * @param pageSize
     * @param newsType
     * @param newsTitle
     * @return
     */
    @GetMapping("/searchList")
    public PageInfo getSearchList(@RequestParam("pageCurrent") Integer pageCurrent,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("newsTitle") String newsTitle,
                                  @RequestParam("newsType") String newsType){
        Page<News> page = new Page<>(pageCurrent, pageSize);
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        if (!"".equals(newsType)){
            wrapper.like("editor", newsType);
        }
        if (!"".equals(newsTitle)){
            wrapper.like("news_title", newsTitle);
        }
        //倒序
        wrapper.orderByDesc("news_id");
        newsService.searchNewsPage(page, wrapper);
        PageInfo info = new PageInfo();
        info.setPageCurrent(pageCurrent);
        info.setPageSize(pageSize);
        info.setTotal(page.getTotal());
        info.setRows(page.getRecords());
        return info;
    }

    /**
     * 通过Id查资讯
     */
    @RequestMapping("/selectById/{newsId}")
    public Result selectById(@PathVariable Long newsId) {
        return Result.success(newsService.selectNewsById(newsId));
    }

    /**
     * 新增资讯
     * @param news
     * @return
     */
    @PostMapping("/insert")
    public Result insertNews(@RequestBody News news) {
        news.setCreateBy("admin");
        return toAjax(newsService.insertNews(news));
    }

    /**
     * 修改资讯
     */
    @RequestMapping("/update")
    public Result updateNews(@RequestBody News news){
        news.setUpdateBy("admin");
        return toAjax(newsService.updateNews(news));
    }

    /**
     * 轮播图列表
     * @return
     */
    @RequestMapping("/carouselList")
    public Result getCarouselList(){
        return Result.success(newsService.selectCarouselList());
    }

    /**
     * 选作轮播图
     * @param newsIds
     * @return
     */
    @RequestMapping("/addCarousel/{newsIds}")
    public Result carouselNews(@PathVariable Long[] newsIds){
        return toAjax(newsService.carouselNewsByIds(newsIds));
    }

    @RequestMapping("/delCarousel/{newsIds}")
    public Result delCarousel(@PathVariable Long[] newsIds) {
        return toAjax(newsService.deleteCarouselByIds(newsIds));
    }

    /**
     * 删除资讯
     */
    @DeleteMapping("/del/{newsIds}")
    public Result removeNews(@PathVariable Long[] newsIds){
        return toAjax(newsService.deleteNewsByIds(newsIds));
    }
}
