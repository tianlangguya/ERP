package com.mxp.erp.config;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mxp.erp.api.IUserService;
import com.mxp.erp.constants.ScheduleEnum;
import com.mxp.erp.entity.UserEntity;

@Component
public class ScheduleConfig {

	private final static int SEVEN_DAY_SEC = 7 * 24 * 60 * 60;
	private final static int ONE_DAY_SEC = 24 * 60 * 60;
	private List<UserEntity> entitys;
	@Autowired
	IUserService userService;

	// @Scheduled(cron = "0 0 24 * * ? ") //每天24执行一次
	// @Scheduled(cron="0/5 * * * * ? ") //每5秒执行一次
	public void AutoResetLoginErrorTimes() {
		// 重置登陆错误次数
		entitys = userService.getAllUserByScheduleType(ScheduleEnum.LOGIN_ERROR_TIMES);
		// for (UserEntity userEntity : entitys) {
		// if(userEntity.getLoginErrorTimes() >= userEntity.getLoginErrorTimesLimit())
		// {
		// //距离上次错误尝试超过一天
		// if(userEntity.getLoginErrorTime()!= null && ((new Date().getTime() -
		// userEntity.getLoginErrorTime().getTime())/1000 >= ONE_DAY_SEC)) {
		// userEntity.setLoginErrorTimes(0);
		// userService.updateErrorPasswordTimes(userEntity);
		// System.out.println(userEntity.getUserName() + "已重置登陆错误次数！");
		// }
		// }
		// }

		// 删除离职状态的员工
		entitys = userService.getAllUserByScheduleType(ScheduleEnum.LEVEL_OFFICE);
		for (UserEntity userEntity : entitys) {
			if (userEntity.getDepartureTime() != null) {
				System.out.println(userEntity.getUserName() + "当前是离职状态！");
				if ((new Date().getTime() - userEntity.getDepartureTime().getTime()) / 1000 > SEVEN_DAY_SEC) {
					System.out.println(userEntity.getUserName() + "已删除数据！");
					userService.delete(userEntity.getId());
				}
			}
		}
	}
}
