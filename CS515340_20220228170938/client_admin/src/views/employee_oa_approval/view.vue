<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','job_number') || $check_field('add','job_number') || $check_field('set','job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="工号" prop="job_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_job_number(form['job_number']) }}
							<!--<el-input id="business_name" v-model="form['job_number']" placeholder="请输入工号"-->
							<!--v-if="user_group === '管理员' || (form['employee_oa_approval_id'] && $check_field('set','job_number')) || (!form['employee_oa_approval_id'] && $check_field('add','job_number'))" :disabled="disabledObj['job_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','job_number')">{{form['job_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['employee_oa_approval_id'] && $check_field('set','job_number')) || (!form['employee_oa_approval_id'] && $check_field('add','job_number'))" id="job_number" v-model="form['job_number']" :disabled="disabledObj['job_number_isDisabled']">
								<el-option v-for="o in list_user_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','job_number')" id="job_number" v-model="form['job_number']" :disabled="true">
								<el-option v-for="o in list_user_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="job_number" v-model="form['job_number']" :disabled="disabledObj['job_number_isDisabled']">
							<el-option v-for="o in list_user_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','approval_type') || $check_field('add','approval_type') || $check_field('set','approval_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="审批类型" prop="approval_type">
					<el-select id="approval_type" v-model="form['approval_type']"
						v-if="user_group === '管理员' || (form['employee_oa_approval_id'] && $check_field('set','approval_type')) || (!form['employee_oa_approval_id'] && $check_field('add','approval_type'))">
						<el-option v-for="o in list_approval_type" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','approval_type')">{{form['approval_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','applicant') || $check_field('add','applicant') || $check_field('set','applicant')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="申请人" prop="applicant">
					<el-input id="applicant" v-model="form['applicant']" placeholder="请输入申请人"
							  v-if="user_group === '管理员' || (form['employee_oa_approval_id'] && $check_field('set','applicant')) || (!form['employee_oa_approval_id'] && $check_field('add','applicant'))" :disabled="disabledObj['applicant_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','applicant')">{{form['applicant']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','approval_status') || $check_field('add','approval_status') || $check_field('set','approval_status')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="审批情况" prop="approval_status">
					<el-select id="approval_status" v-model="form['approval_status']"
						v-if="user_group === '管理员' || (form['employee_oa_approval_id'] && $check_field('set','approval_status')) || (!form['employee_oa_approval_id'] && $check_field('add','approval_status'))">
						<el-option v-for="o in list_approval_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','approval_status')">{{form['approval_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','approver_reply') || $check_field('add','approver_reply') || $check_field('set','approver_reply')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="审批人回复" prop="approver_reply">
					<el-input id="approver_reply" v-model="form['approver_reply']" placeholder="请输入审批人回复"
							  v-if="user_group === '管理员' || (form['employee_oa_approval_id'] && $check_field('set','approver_reply')) || (!form['employee_oa_approval_id'] && $check_field('add','approver_reply'))" :disabled="disabledObj['approver_reply_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','approver_reply')">{{form['approver_reply']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','approver') || $check_field('add','approver') || $check_field('set','approver')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="审批人" prop="approver">
					<el-input id="approver" v-model="form['approver']" placeholder="请输入审批人"
							  v-if="user_group === '管理员' || (form['employee_oa_approval_id'] && $check_field('set','approver')) || (!form['employee_oa_approval_id'] && $check_field('add','approver'))" :disabled="disabledObj['approver_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','approver')">{{form['approver']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','date') || $check_field('add','date') || $check_field('set','date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="日期" prop="date">
					<el-date-picker :disabled="disabledObj['date_isDisabled']" v-if="user_group === '管理员' || (form['employee_oa_approval_id'] && $check_field('set','date')) || (!form['employee_oa_approval_id'] && $check_field('add','date'))" id="date"
						v-model="form['date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','date')">{{form['date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','approval_content') || $check_field('add','approval_content') || $check_field('set','approval_content')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="审批内容" prop="approval_content">
					<el-input type="textarea" id="approval_content" v-model="form['approval_content']" placeholder="请输入审批内容"
						v-if="user_group === '管理员' || (form['employee_oa_approval_id'] && $check_field('set','approval_content')) || (!form['employee_oa_approval_id'] && $check_field('add','approval_content'))" :disabled="disabledObj['approval_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','approval_content')">{{form['approval_content']}}</div>
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
				field: "employee_oa_approval_id",
				url_add: "~/api/employee_oa_approval/add?",
				url_set: "~/api/employee_oa_approval/set?",
				url_get_obj: "~/api/employee_oa_approval/get_obj?",
				url_upload: "~/api/employee_oa_approval/upload?",

				query: {
					"employee_oa_approval_id": 0,
				},

				form: {
					"job_number": 0, // 工号
					"approval_type":'', // 审批类型
					"applicant":'', // 申请人
					"approval_status":'', // 审批情况
					"approver_reply":'', // 审批人回复
					"approver":'', // 审批人
					"date":'', // 日期
					"approval_content":'', // 审批内容
					"employee_oa_approval_id": 0, // ID

				},
				disabledObj:{
					"job_number_isDisabled": false,
					"approval_type_isDisabled": false,
					"applicant_isDisabled": false,
					"approval_status_isDisabled": false,
					"approver_reply_isDisabled": false,
					"approver_isDisabled": false,
					"date_isDisabled": false,
					"approval_content_isDisabled": false,
				},
				// 用户列表
				list_user_job_number: [],
				// 用户组
				group_user_job_number: "",
				//审批类型选项列表
				list_approval_type: ['请假','报销','费用申请'],
				//审批情况选项列表
				list_approval_status: ['同意','不同意'],

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
			/**
			 * 获取职员用户用户组
			 */
			async get_group_user_job_number() {
				this.form["job_number"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=职员用户");
				if(json.result && json.result.obj){
					this.group_user_job_number = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_job_number(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_job_number.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="job_number") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
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
				if(parseInt(this.form["date"]) > 9999){
					this.form["date"] = this.$toTime(parseInt(this.form["date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/employee_oa_approval/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/employee_oa_approval/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/employee_oa_approval/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/employee_oa_approval/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/employee_oa_approval/view','get');
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
			this.get_group_user_job_number();
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
