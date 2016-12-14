package com.atguigu.survey.utils;

/**
 * @author shuai xu 2016年10月17日 下午1:29:27
 */
public class GlobalMessage {
	public static final String USER_LOGIN_FAILED = "用户名、密码不正确，请重新登录！";
	public static final String USER_NAME_EXISTS = "用户名已经被占用！";
	public static final String USER_OPERATION_FORBIDDEN = "当前操作需要先登录！";
	public static final String FILE_TYPE_INVALID = "您上传的文件类型不正确，请上传图片";
	public static final String REMOVE_BAG_FAILED = "在删除此包前请先清空其下的所有数据！";
	public static final String REMOVE_SURVEY_FAILED = "在删除此调查前请先清空其下的所有数据！";
	public static final String SURVEY_EMPTY = "当前调查为空，不能完成此操作！";
	public static final String BAG_EMPTY = "当前调查中存在空的包裹，不能完成！";
	public static final String BAD_ORDER_DUPLICATE = "序号重复！";
	public static String FILE_TOO_LARGE = "您上传的文件过大，请不要大于100KB！";
	public static final String ADMIN_LOGIN_FAILED = "账号、密码不正确！";

	public static final String ADMIN_OPERATION_FORBIDDEN = "请登录后再进行这个操作！[Admin]";
	public static final String REMOVE_AUTH_FAILED = "您当前操作权限不合法";
	public static final String REMOVE_RES_FAILED = "您当前操作资源不合法";
	public static final String REMOVE_ROLE_FAILED = "您当前操作角色不合法";
	public static final String ADMIN_NAME_EXISTS = "管理员账号已存在";
	public static final String HAS_NO_AUTHORITY = "您暂无对此的操作权限";
}
