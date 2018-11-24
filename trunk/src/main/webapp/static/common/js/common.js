/**
 * 根据id获取部门数据
 * @param {Object} id
 */
function getOrganizationInfoById(data,apiUrl,callback) {
	$.ajax({
		type: "get",
		dataType: "json",
		url: apiUrl,
		success: function(data) {
			if(data.ret != 0) {
				layer.open({
					title: "系统异常",
					content: data.msg
				});
			}
			callback(data);
		},
		error: function(error) {
			layer.open({
				title: "系统异常",
				content: "系统异常，请联系管理员！"
			});
		}
	});
}