<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','type') || $check_field('add','type') || $check_field('set','type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="类型" prop="type">
					<el-select id="type" v-model="form['type']"
						v-if="user_group === '管理员' || (form['notes_and_experience_id'] && $check_field('set','type')) || (!form['notes_and_experience_id'] && $check_field('add','type'))">
						<el-option v-for="o in list_type" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','type')">{{form['type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','share_content') || $check_field('add','share_content') || $check_field('set','share_content')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="分享内容" prop="share_content">
					<el-input id="share_content" v-model="form['share_content']" placeholder="请输入分享内容"
							  v-if="user_group === '管理员' || (form['notes_and_experience_id'] && $check_field('set','share_content')) || (!form['notes_and_experience_id'] && $check_field('add','share_content'))" :disabled="disabledObj['share_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','share_content')">{{form['share_content']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','sharer') || $check_field('add','sharer') || $check_field('set','sharer')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="分享人" prop="sharer">
					<el-input id="sharer" v-model="form['sharer']" placeholder="请输入分享人"
							  v-if="user_group === '管理员' || (form['notes_and_experience_id'] && $check_field('set','sharer')) || (!form['notes_and_experience_id'] && $check_field('add','sharer'))" :disabled="disabledObj['sharer_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','sharer')">{{form['sharer']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','enclosure') || $check_field('add','enclosure') || $check_field('set','enclosure')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="附件" prop="enclosure">
					<div v-if="disabledObj['enclosure_isDisabled']">
						<div v-if="$check_field('get','enclosure')">
							<el-button type="primary" @click="$download($fullUrl(form['enclosure']),form['enclosure'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['notes_and_experience_id'] && $check_field('set','enclosure')) || (!form['notes_and_experience_id'] && $check_field('add','enclosure'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_enclosure" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','enclosure')">
							<el-button type="primary" @click="$download($fullUrl(form['enclosure']),form['enclosure'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="备注" prop="remarks">
					<el-input id="remarks" v-model="form['remarks']" placeholder="请输入备注"
							  v-if="user_group === '管理员' || (form['notes_and_experience_id'] && $check_field('set','remarks')) || (!form['notes_and_experience_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remarks')">{{form['remarks']}}</div>
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
				field: "notes_and_experience_id",
				url_add: "~/api/notes_and_experience/add?",
				url_set: "~/api/notes_and_experience/set?",
				url_get_obj: "~/api/notes_and_experience/get_obj?",
				url_upload: "~/api/notes_and_experience/upload?",

				query: {
					"notes_and_experience_id": 0,
				},

				form: {
					"type":'', // 类型
					"share_content":'', // 分享内容
					"sharer":'', // 分享人
					"enclosure":'', // 附件
					"remarks":'', // 备注
					"notes_and_experience_id": 0, // ID

				},
				disabledObj:{
					"type_isDisabled": false,
					"share_content_isDisabled": false,
					"sharer_isDisabled": false,
					"enclosure_isDisabled": false,
					"remarks_isDisabled": false,
				},
				//类型选项列表
				list_type: ['笔记','心得'],

			}
		},
		methods: {
			/**
			 * 上传附件
			 * @param {Object} param文件参数
			 */
			upload_enclosure(param){
				this.uploadFile(param.file, "enclosure");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/notes_and_experience/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/notes_and_experience/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/notes_and_experience/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/notes_and_experience/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/notes_and_experience/view','get');
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
