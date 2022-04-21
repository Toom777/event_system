package com.toom.event_system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Common.Result;
import com.toom.event_system.Entity.Activity;
import com.toom.event_system.Entity.Confirmation;
import com.toom.event_system.Entity.News;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Entity.Vo.ConfirmActivityUserVo;
import com.toom.event_system.Mapper.ActivityMapper;
import com.toom.event_system.Mapper.ConfirmationMapper;
import com.toom.event_system.Mapper.NewsMapper;
import com.toom.event_system.Service.ConfirmationService;
import com.toom.event_system.Service.MenuService;
import com.toom.event_system.Service.RoleService;
import com.toom.event_system.Service.UserService;
import org.apache.shiro.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class EventSystemApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RoleService roleService;

	@Autowired
	private NewsMapper newsMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private MenuService menuService;


	@Autowired
	private ConfirmationService confirmationService;


	@Autowired
	private ConfirmationMapper confirmationMapper;

	@Autowired
	private ActivityMapper activityMapper;

	@Test
	void test(){
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Set<String> roles = menuService.getPermissionByUsername(user.getUsername());
		Set<String> permissions = roleService.getRoleByUsername(user.getUsername());
		Result result = Result.success();
		result.put("user", user);
		result.put("roles", roles);
		result.put("permissions", permissions);
		System.out.println(result);
	}

	@Test
	void uploadTest() {

		System.out.println(ClassUtils.getDefaultClassLoader().getResource("static").getPath());
		System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
	}

	@Test
	void comfirmTest() {
		Confirmation confirmation = confirmationMapper.selectById(1018L);


		String begin = "2022-04-14 08:15:08";
		String end = "2022-04-14 09:59:11";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );
		LocalDateTime dateTimeBegin = LocalDateTime.parse(begin,formatter);
		LocalDateTime dateTimeEnd = LocalDateTime.parse(end,formatter);

		int minutes = (int) Duration.between(dateTimeBegin, dateTimeEnd).toMinutes();
		int hours = minutes / 60;
		int hr = minutes % 60;
		System.out.println("begin：" + dateTimeBegin);
		System.out.println("end：" + dateTimeEnd);
		System.out.println("第一次时长：" + hours);
		System.out.println("分长：" + minutes);
		System.out.println("hr：" + hr);
		if (hr > 30) {
			hours++;
		}
		System.out.println("第二次时长：" + hours);
	}

	@Test
	void starTest() {
		int hours = 200;
		if (hours < 20) {
			System.out.println(1);
		} else if (hours < 60) {
			System.out.println(2);
		} else if (hours < 100) {
			System.out.println(3);
		} else if (hours < 200) {
			System.out.println(4);
		} else {
			System.out.println(5);
		}
	}

	@Test
	void carouselTest() {
		QueryWrapper<News> wrapper = new QueryWrapper<>();
		wrapper.ne("remark", "");
		System.out.println(newsMapper.selectList(wrapper));

	}
}
