import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},

	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},
	// 通知公告路由
	{
		path: '/notice_announcement/table',
		name: 'notice_announcement_table',
		component: () => import('../views/notice_announcement/table.vue')
	},
	{
		path: '/notice_announcement/view',
		name: 'notice_announcement_view',
		component: () => import('../views/notice_announcement/view.vue')
	},
	// 职员用户路由
	{
		path: '/employee_user/table',
		name: 'employee_user_table',
		component: () => import('../views/employee_user/table.vue')
	},
	{
		path: '/employee_user/view',
		name: 'employee_user_view',
		component: () => import('../views/employee_user/view.vue')
	},
	// 管理层用户路由
	{
		path: '/management_users/table',
		name: 'management_users_table',
		component: () => import('../views/management_users/table.vue')
	},
	{
		path: '/management_users/view',
		name: 'management_users_view',
		component: () => import('../views/management_users/view.vue')
	},
	// 职员考勤路由
	{
		path: '/employee_attendance/table',
		name: 'employee_attendance_table',
		component: () => import('../views/employee_attendance/table.vue')
	},
	{
		path: '/employee_attendance/view',
		name: 'employee_attendance_view',
		component: () => import('../views/employee_attendance/view.vue')
	},
	// 管理层考勤路由
	{
		path: '/management_attendance/table',
		name: 'management_attendance_table',
		component: () => import('../views/management_attendance/table.vue')
	},
	{
		path: '/management_attendance/view',
		name: 'management_attendance_view',
		component: () => import('../views/management_attendance/view.vue')
	},
	// 职员任务管理路由
	{
		path: '/employee_task_management/table',
		name: 'employee_task_management_table',
		component: () => import('../views/employee_task_management/table.vue')
	},
	{
		path: '/employee_task_management/view',
		name: 'employee_task_management_view',
		component: () => import('../views/employee_task_management/view.vue')
	},
	// 管理层任务管理路由
	{
		path: '/management_task_management/table',
		name: 'management_task_management_table',
		component: () => import('../views/management_task_management/table.vue')
	},
	{
		path: '/management_task_management/view',
		name: 'management_task_management_view',
		component: () => import('../views/management_task_management/view.vue')
	},
	// 职员OA审批路由
	{
		path: '/employee_oa_approval/table',
		name: 'employee_oa_approval_table',
		component: () => import('../views/employee_oa_approval/table.vue')
	},
	{
		path: '/employee_oa_approval/view',
		name: 'employee_oa_approval_view',
		component: () => import('../views/employee_oa_approval/view.vue')
	},
	// 管理员OA审批路由
	{
		path: '/administrator_oa_approval/table',
		name: 'administrator_oa_approval_table',
		component: () => import('../views/administrator_oa_approval/table.vue')
	},
	{
		path: '/administrator_oa_approval/view',
		name: 'administrator_oa_approval_view',
		component: () => import('../views/administrator_oa_approval/view.vue')
	},
	// 笔记心得路由
	{
		path: '/notes_and_experience/table',
		name: 'notes_and_experience_table',
		component: () => import('../views/notes_and_experience/table.vue')
	},
	{
		path: '/notes_and_experience/view',
		name: 'notes_and_experience_view',
		component: () => import('../views/notes_and_experience/view.vue')
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "OA办公管理系统-admin";
	document.title = title;
})

export default router
