

//抽取公共方法

const crm = {

	//将token存到本地
	setToken(obj) {
		window.localStorage.setItem("TOKEN", obj);
	},
	//获取本地token
	getToken() {
		return window.localStorage.getItem("TOKEN");
	},
	//获取本地权限
	getPermission() {
		return JSON.stringify(window.localStorage.getItem("PERMISSION"));
	},
	//将权限存入到本地
	setPermission(obj) {
		window.localStorage.setItem("PERMISSION", obj);
	},
	//将用户角色存入到本地
	setRoles(obj) {
		window.localStorage.setItem("ROLES", obj);
	},
	//获取本地角色
	getRoles() {
		return window.localStorage.getItem("ROLES");
	},
	//将用户ID存入到本地
	setUserId(obj) {
		window.localStorage.setItem("USER_ID", obj);
	},
	//获取本地用户ID
	getUserId() {
		return JSON.stringify(window.localStorage.getItem("USER_ID"));
	},
	//将用户名称存入到本地
	setUserName(obj) {
		window.localStorage.setItem("USERNAME", obj);
	},
	//获取本地用户名称
	getUserName() {
		return JSON.stringify(window.localStorage.getItem("UserName"));
	}

};