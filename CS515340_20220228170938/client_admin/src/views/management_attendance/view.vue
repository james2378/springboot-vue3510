<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','management_employee_number') || $check_field('add','management_employee_number') || $check_field('set','management_employee_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="管理员工号" prop="management_employee_number">
						<el-select v-if="user_group === '管理员' || (form['management_attendance_id'] && $check_field('set','management_employee_number')) || (!form['management_attendance_id'] && $check_field('add','management_employee_number'))" id="management_employee_number" v-model="form['management_employee_number']" :disabled="disabledObj['management_employee_number_isDisabled']">
							<el-option v-for="o in list_user_management_employee_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','management_employee_number')" id="management_employee_number" v-model="form['management_employee_number']" :disabled="true">
							<el-option v-for="o in list_user_management_employee_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['management_attendance_id'] && $check_field('set','full_name')) || (!form['management_attendance_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','sign_in') || $check_field('add','sign_in') || $check_field('set','sign_in')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="签到" prop="sign_in">
					<el-time-picker v-if="user_group === '管理员' || (form['management_attendance_id'] && $check_field('set','sign_in')) || (!form['management_attendance_id'] && $check_field('add','sign_in'))" id="sign_in"
						v-model="form['sign_in']" placeholder="选择时间">
					</el-time-picker>
					<div v-else-if="$check_field('get','sign_in')">{{form['sign_in']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','sign_out') || $check_field('add','sign_out') || $check_field('set','sign_out')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="签退" prop="sign_out">
					<el-time-picker v-if="user_group === '管理员' || (form['management_attendance_id'] && $check_field('set','sign_out')) || (!form['management_attendance_id'] && $check_field('add','sign_out'))" id="sign_out"
						v-model="form['sign_out']" placeholder="选择时间">
					</el-time-picker>
					<div v-else-if="$check_field('get','sign_out')">{{form['sign_out']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','attendance_status') || $check_field('add','attendance_status') || $check_field('set','attendance_status')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="出勤状态" prop="attendance_status">
					<el-select id="attendance_status" v-model="form['attendance_status']"
						v-if="user_group === '管理员' || (form['management_attendance_id'] && $check_field('set','attendance_status')) || (!form['management_attendance_id'] && $check_field('add','attendance_status'))">
						<el-option v-for="o in list_attendance_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','attendance_status')">{{form['attendance_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','date') || $check_field('add','date') || $check_field('set','date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="日期" prop="date">
					<el-date-picker :disabled="disabledObj['date_isDisabled']" v-if="user_group === '管理员' || (form['management_attendance_id'] && $check_field('set','date')) || (!form['management_attendance_id'] && $check_field('add','date'))" id="date"
						v-model="form['date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','date')">{{form['date']}}</div>
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
				field: "management_attendance_id",
				url_add: "~/api/management_attendance/add?",
				url_set: "~/api/management_attendance/set?",
				url_get_obj: "~/api/management_attendance/get_obj?",
				url_upload: "~/api/management_attendance/upload?",

				query: {
					"management_attendance_id": 0,
				},

				form: {
					"management_employee_number": 0, // 管理员工号
					"full_name":'', // 姓名
					"sign_in":'', // 签到
					"sign_out":'', // 签退
					"attendance_status":'', // 出勤状态
					"date":'', // 日期
					"management_attendance_id": 0, // ID

				},
				disabledObj:{
					"management_employee_number_isDisabled": false,
					"full_name_isDisabled": false,
					"sign_in_isDisabled": false,
					"sign_out_isDisabled": false,
					"attendance_status_isDisabled": false,
					"date_isDisabled": false,
				},
				// 用户列表
				list_user_management_employee_number: [],
				//出勤状态选项列表
				list_attendance_status: ['正常','迟到','早退','请假','旷工'],

			}
		},
		methods: {
			/**
			 * 获取管理层用户用户列表
			 */
			async get_list_user_management_employee_number() {
                // if(this.user_group !== "管理员" && this.form["management_employee_number"] === 0) {
                //     this.form["management_employee_number"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=管理层用户");
                if(json.result && json.result.list){
                    this.list_user_management_employee_number = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_management_employee_number(id){
				var obj = this.list_user_management_employee_number.getObj({"user_id":id});
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
						case "管理员工号":
							if(param["management_employee_number"] > 0){
								param["management_employee_number"] = this.user.user_id;
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
        if (this.form["date"].indexOf("-")===-1){
          this.form["date"] = this.$toTime(parseInt(this.form["date"]),"yyyy-MM-dd")
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
				let reg_sign_in = /^(20|21|22|23|[0-1]\d):[0-5]\d:[0-5]\d$/;
				let regExp_sign_in = new RegExp(reg_sign_in);
				if(regExp_sign_in.test(this.form["sign_in"])){
					let dateTmp = "1970-01-01 "+this.form["sign_in"].replace(/-/g,'/');
					let timestamp = Date.parse(dateTmp);
					this.form["sign_in"] = timestamp;
				}else {
					this.form["sign_in"] = this.$toTime(parseInt(this.form["sign_in"]),"hh:mm:ss");
				}
				let reg_sign_out = /^(20|21|22|23|[0-1]\d):[0-5]\d:[0-5]\d$/;
				let regExp_sign_out = new RegExp(reg_sign_out);
				if(regExp_sign_out.test(this.form["sign_out"])){
					let dateTmp = "1970-01-01 "+this.form["sign_out"].replace(/-/g,'/');
					let timestamp = Date.parse(dateTmp);
					this.form["sign_out"] = timestamp;
				}else {
					this.form["sign_out"] = this.$toTime(parseInt(this.form["sign_out"]),"hh:mm:ss");
				}
				if(parseInt(this.form["date"]) > 9999){
					this.form["date"] = this.$toTime(parseInt(this.form["date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/management_attendance/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/management_attendance/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/management_attendance/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/management_attendance/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/management_attendance/view','get');
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
			this.get_list_user_management_employee_number();
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
