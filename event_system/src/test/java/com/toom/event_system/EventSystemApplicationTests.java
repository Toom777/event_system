package com.toom.event_system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Common.Result;
import com.toom.event_system.Entity.Confirmation;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Entity.Vo.ConfirmActivityUserVo;
import com.toom.event_system.Mapper.ConfirmationMapper;
import com.toom.event_system.Service.ConfirmationService;
import com.toom.event_system.Service.MenuService;
import com.toom.event_system.Service.RoleService;
import com.toom.event_system.Service.UserService;
import org.apache.shiro.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;

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
	private UserService userService;

	@Autowired
	private MenuService menuService;


	@Autowired
	private ConfirmationService confirmationService;


	@Autowired
	private ConfirmationMapper confirmationMapper;


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


		System.out.println(confirmationMapper.nameList(1L));
	}


}
