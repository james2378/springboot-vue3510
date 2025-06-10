<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','task_title') || $check_field('add','task_title') || $check_field('set','task_title')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="任务标题" prop="task_title">
					<el-input id="task_title" v-model="form['task_title']" placeholder="请输入任务标题"
							  v-if="user_group === '管理员' || (form['employee_task_management_id'] && $check_field('set','task_title')) || (!form['employee_task_management_id'] && $check_field('add','task_title'))" :disabled="disabledObj['task_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','task_title')">{{form['task_title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','job_number') || $check_field('add','job_number') || $check_field('set','job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="工号" prop="job_number">
						<el-select v-if="user_group === '管理员' || (form['employee_task_management_id'] && $check_field('set','job_number')) || (!form['employee_task_management_id'] && $check_field('add','job_number'))" id="job_number" v-model="form['job_number']" :disabled="disabledObj['job_number_isDisabled']">
							<el-option v-for="o in list_user_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','job_number')" id="job_number" v-model="form['job_number']" :disabled="true">
							<el-option v-for="o in list_user_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['employee_task_management_id'] && $check_field('set','full_name')) || (!form['employee_task_management_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','task_status') || $check_field('add','task_status') || $check_field('set','task_status')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="任务状态" prop="task_status">
					<el-select id="task_status" v-model="form['task_status']"
						v-if="user_group === '管理员' || (form['employee_task_management_id'] && $check_field('set','task_status')) || (!form['employee_task_management_id'] && $check_field('add','task_status'))">
						<el-option v-for="o in list_task_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','task_status')">{{form['task_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','closing_date') || $check_field('add','closing_date') || $check_field('set','closing_date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="截止日期" prop="closing_date">
					<el-date-picker :disabled="disabledObj['closing_date_isDisabled']" v-if="user_group === '管理员' || (form['employee_task_management_id'] && $check_field('set','closing_date')) || (!form['employee_task_management_id'] && $check_field('add','closing_date'))" id="closing_date"
						v-model="form['closing_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','closing_date')">{{form['closing_date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','task_details') || $check_field('add','task_details') || $check_field('set','task_details')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="任务详情" prop="task_details">
					<el-input type="textarea" id="task_details" v-model="form['task_details']" placeholder="请输入任务详情"
						v-if="user_group === '管理员' || (form['employee_task_management_id'] && $check_field('set','task_details')) || (!form['employee_task_management_id'] && $check_field('add','task_details'))" :disabled="disabledObj['task_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','task_details')">{{form['task_details']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "employee_task_management_id",
				url_add: "~/api/employee_task_management/add?",
				url_set: "~/api/employee_task_management/set?",
				url_get_obj: "~/api/employee_task_management/get_obj?",
				url_upload: "~/api/employee_task_management/upload?",

				query: {
					"employee_task_management_id": 0,
				},

				form: {
					"task_title":'', // 任务标题
					"job_number": 0, // 工号
					"full_name":'', // 姓名
					"task_status":'', // 任务状态
					"closing_date":'', // 截止日期
					"task_details":'', // 任务详情
					"employee_task_management_id": 0, // ID

				},
				disabledObj:{
					"task_title_isDisabled": false,
					"job_number_isDisabled": false,
					"full_name_isDisabled": false,
					"task_status_isDisabled": false,
					"closing_date_isDisabled": false,
					"task_details_isDisabled": false,
				},
				// 用户列表
				list_user_job_number: [],
				//任务状态选项列表
				list_task_status: ['已完成','未完成'],

			}
		},
		methods: {
			/**
			 * 获取职员用户用户列表
			 */
			async get_list_user_job_number() {
                // if(this.user_group !== "管理员" && this.form["job_number"] === 0) {
                //     this.form["job_number"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=职员用户");
                if(json.result && json.result.list){
                    this.list_user_job_number = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_job_number(id){
				var obj = this.list_user_job_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "工号":
							if(param["job_number"] > 0){
								param["job_number"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
        if (this.form["closing_date"].indexOf("-")===-1){
          this.form["closing_date"] = this.$toTime(parseInt(this.form["closing_date"]),"yyyy-MM-dd")
        }
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["closing_date"]) > 9999){
					this.form["closing_date"] = this.$toTime(parseInt(this.form["closing_date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/employee_task_management/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/employee_task_management/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/employee_task_management/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/employee_task_management/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/employee_task_management/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_job_number();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
